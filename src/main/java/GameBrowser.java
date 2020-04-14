import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class GameBrowser {
    private DataSource dataSource; // the connection to the database
    private final String gameListName = "Master Game List"; // is this necessary?
    private GameList gameList; // The local master game list!
    private List<String> administrators; // list of keys to administrator
    private List<String> developers; // list of keys to devs
    private List<String> allGameLists; // list of keys to gameLists

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

    }

    /**
     * Invalid Default Constructor
     * @throws Exception - a path to a data file is always needed
     */
    public GameBrowser() throws Exception {
        throw new Exception("Invalid constructor. Supply a data source file.");
    }

    public void addGame(Game game){
        gameList.includeGame(game.getTitle());
        // TODO Update Database
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
     * @param username - the username of the developer
     */
    public void addDeveloper(String username)  throws IllegalArgumentException, DataSourceException {
        developers.add(username);
        dataSource.saveDeveloper(username);
        // TODO: Update Database
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
        dataSource.removeDeveloper(dataSource.loadDeveloper(developer));
        return developer;
        // TODO: Update Database
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
        //TODO WIPE GAME LIST SUCH THAT REMOVALS GET NOTICED
        dataSource.saveGameList(gameList); // Save Master List
        for (String developer: developers) {
            dataSource.saveDeveloper(developer); // Save Developers
//            dataSource.setInTransaction(false);
        }
        for (String gameList : allGameLists){
            dataSource.saveGameList(dataSource.loadGameList(gameList)); // Save additional gameLists
        }
        //TODO save Administrators doesn't exist yet
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

    // -----SETTERS-----
    public void setGameList(GameList gameListTurnIn) {
        this.gameList = gameListTurnIn;
    }

}
