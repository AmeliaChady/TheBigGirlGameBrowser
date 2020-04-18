import java.util.ArrayList;
import java.util.List;

public class GameBrowser {
    private DataSource dataSource; // the connection to the database
    private final String gameListName = "Master Game List"; // is this necessary?
    private GameList gameList; // The local master game list!
    private ReviewList allReviews;
    private List<String> administrators; // list of keys to administrator
    private List<String> developers; // list of keys to devs
    private List<String> allGameLists; // list of keys to gameLists
    private UIPlugin uiplug;

    /**
     * Constructor (
     * @param dataFilePath - file path to load all data from
     */
    public GameBrowser(String dataFilePath) throws IllegalArgumentException, DataSourceException {
        if (dataFilePath.length() == 0)
            throw new IllegalArgumentException("Please supply a filename.");

        dataSource = new SQLiteSource(dataFilePath);
        loadAllGames(); // loads master gameList into memory
        developers = new ArrayList<String>();
        loadAllDevelopers(); // loads developers keys from db
        allGameLists = new ArrayList<String>();
        // TODO loadAllLists helper functions
        // TODO loadAllAdministrators // loads administrators keys from db
        allReviews = null; // TODO load all reviews
        uiplug = null;
    }

    public GameBrowser(String dataFilePath, UIPlugin uiplugin) throws IllegalArgumentException, DataSourceException {
        if (dataFilePath.length() == 0)
            throw new IllegalArgumentException("Please supply a filename.");

        dataSource = new SQLiteSource(dataFilePath);
        loadAllGames(); // loads master gameList into memory
        developers = new ArrayList<String>();
        loadAllDevelopers(); // loads developers keys from db
        allGameLists = new ArrayList<String>();
        // TODO loadAllLists helper functions
        // TODO loadAllAdministrators // loads administrators keys from db

        uiplug = uiplugin;
        if(uiplug != null)
            uiplug.pullGameBrowser(this);
    }

    /**
     * Invalid Default Constructor
     * @throws Exception - a path to a data file is always needed
     */
    public GameBrowser() throws Exception {
        throw new Exception("Invalid constructor. Supply a data source file.");
    }

    public void addGame(Game game) throws DataSourceException {
        gameList.includeGame(game.getTitle());
        dataSource.saveGame(game);
    }

    public void addGame(String title, String description, List<String> developer, Status status) throws DataSourceException{
        Game game = new Game(title, description, developer, status);
        dataSource.saveGame(game); // add game to DB

        gameList.includeGame(game.getTitle()); // Don't save master list until end of runtime
    }

    //TODO this will be tough
    public Game removeGame(String title) throws DataSourceException{
        gameList.removeGame(title);
        Game game = dataSource.loadGame(title);
        // TODO Search for all reference of game and remove...
        return game;
    }


    /**
     * A new developer is created and added to the developer list
     * @param dev - the developer
     */
    public void addDeveloper(Developer dev)  throws IllegalArgumentException, DataSourceException {
        developers.add(dev.getName());
        dataSource.saveDeveloper(dev);
    }

    /**
     * Removes a developer from the developer list
     * @param username - the username of the developer
     * @return Developer - the developer that was removed
     */
    public String removeDeveloper(String username) throws DataSourceException {
        String developer = null;

        for (String d : developers) {
            if (d.equals(username)) {
                developer = d;
                System.out.println("we here");
                developers.remove(d);
                break;
            }
        }
        try {
            dataSource.removeDeveloper(dataSource.loadDeveloper(developer));
        }catch (IllegalArgumentException e){
            System.out.println(e);
            return null;
        }
        return developer;
    }

    /**
     * Adds a game list to allGameLists
     * @param gameList
     */
    public void addGameList(GameList gameList) throws DataSourceException {
        allGameLists.add(gameList.getName());
        dataSource.saveGameList(gameList);
        // Do we need to check that the games in gameList are saved too?
    }

    //TODO Remove a GameList

    // ------HELPERS------
    private void loadAllGames() throws DataSourceException {
        try {
            gameList = dataSource.loadGameList(gameListName);
            if(gameList==null){
                gameList = new GameList(gameListName);
            }
        } catch(DataSourceException dse) {
            System.out.println(dse.getMessage());
            throw new DataSourceException(dse.getMessage());
        }
    }

    /**
     * Saves all the local contents into the database
     * @throws DataSourceException
     */
    public void save() throws DataSourceException {
        dataSource.saveGameList(gameList); // Save Master List
        for (String developer: developers) {
            if(dataSource.loadDeveloper(developer).equals(null)){ //Checks that all devs are saved already
                throw new  DataSourceException("Developer "+ developer+" not saved correctly");
            }
        }
        for (String gameList : allGameLists){
            if(dataSource.loadGameList(gameList).equals(null)){ //Checks that all gameLists are saved already
                throw new  DataSourceException("GameList "+ gameList+" not saved correctly");
            }
        }
        for (String game : gameList.getGames()){
            if(dataSource.loadGame(game).equals(null)){
                throw new  DataSourceException("Game "+ game+" not saved correctly");
            }
        }
    }
  
    private void loadAllDevelopers() throws DataSourceException {
        try {
            developers = dataSource.loadDeveloperList();
        } catch(DataSourceException dse) {
            System.out.println(dse.getMessage());
            throw new DataSourceException(dse.getMessage());
        }
    }


    // ------GETTERS------
    public GameList getGameList() { return gameList; }
    public List<String> getDevelopers() { return developers; }

    public Developer loadDeveloper(String dev) throws DataSourceException {
        try {
            Developer developer = dataSource.loadDeveloper(dev);
            return developer;
        } catch(DataSourceException dse) {
            System.out.println(dse.getMessage());
            throw new DataSourceException(dse.getMessage());
        }
    }

    public Game loadGame(String title) throws DataSourceException{
        return dataSource.loadGame(title);
    }

    public GameList loadGameList(String name) throws DataSourceException{
        return dataSource.loadGameList(name);
    }

    // -----SETTERS-----
    public void setGameList(GameList gameListTurnIn) {
        this.gameList = gameListTurnIn;
    }

    // -----UIPlugin-----
    /**
     * @return false if uiplug is null. Otherwise true.
     */
    public boolean hasUIPlugin(){
        return uiplug!=null;
    }
    public void setUIPlugin(UIPlugin uiplugin){
        uiplug = uiplugin;
    }

    /**
     * loads game and passes it to UIPlugin
     * @param title title of game
     * @return false if pulls a null. true otherwise.
     */
    public boolean pullGame(String title){
        if(uiplug==null){
            throw new IllegalStateException("no UIPlugin");
        }
        try {
            Game g = loadGame(title);
            uiplug.pullGame(g);
            return g != null;
        }catch (DataSourceException e){
            uiplug.pullGame(null);
            return false;
        }
    }

    /**
     * loads gamelist and passes it to UIPlugin
     * @param name name of gamelist
     * @return false if pulls a null. true otherwise.
     */
    public boolean pullGameList(String name){
        if(uiplug==null){
            throw new IllegalStateException("no UIPlugin");
        }
        try {
            GameList gl = loadGameList(name);
            uiplug.pullGameList(gl);
            return gl != null;
        }catch (DataSourceException e){
            uiplug.pullGameList(null);
            return false;
        }
    }

    /**
     * loads developer and passes it to UIPlugin
     * @param name name of developer
     * @return false if pulls a null. true otherwise.
     */
    public boolean pullDeveloper(String name){
        if(uiplug==null){
            throw new IllegalStateException("no UIPlugin");
        }
        try {
            Developer d = loadDeveloper(name);
            uiplug.pullDeveloper(d);
            return d != null;
        }catch (DataSourceException e){
            uiplug.pullDeveloper(null);
            return false;
        }
    }

    // -----DB HELPFUL-----
    public void close(){
        dataSource.close();
    }

}
