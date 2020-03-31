import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class GameBrowser {
    private DataSource dataSource;
    private GameList gameList;
    private List<Administrator> administrators;
    private List<Developer> developers;

    /**
     * Constructor (
     * @param dataFilePath - file path to load all data from
     */
    public GameBrowser(String dataFilePath) throws IllegalArgumentException {
        // TODO remove after a DataSource.loadGameList has been made
        String[] gameTitlesInDb = {
                "LoadGameTest1", "LoadGameTest2", "testGame", "Test-zx the Game",
                "Toot Scooters", "testGame1", "testGame2", "testGame3"
        };

        if (dataFilePath.length() == 0)
            throw new IllegalArgumentException("Please supply a filename.");

        dataSource = new SQLiteSource(dataFilePath);
        gameList = new GameList("Master List");
        developers = new ArrayList<Developer>();

        // load games from data source
        try {
            for (int i = 0; i < gameTitlesInDb.length; i++)
                gameList.includeGame( dataSource.loadGame(gameTitlesInDb[i]) );
        } catch(DataSourceException dse) {
            System.out.println(dse.getMessage());
        }
    }

    /**
     * Invalid Default Constructor
     * @throws Exception - a path to a data file is always needed
     */
    public GameBrowser() throws Exception {
        throw new Exception("Invalid constructor. Supply a data source file.");
    }

    public void addGame(Game game){
        gameList.includeGame(game);
    }

    public void addGame(String title, String description, List<Developer> developer, Status status){
        Game game = new Game(title, description, developer, status);
        gameList.includeGame(game);
    }

    public Game removeGame(String title) {
        return gameList.removeGame(title);
    }


    /**
     * A new developer is created and added to the developer list
     * @param username - the username of the developer
     */
    public void addDeveloper(String username) {
        developers.add( new Developer(username) );
    }

    public Developer removeDeveloper(String username) { return null; }

    // ------GETTERS------
    public GameList getGameList() { return gameList; }

    public List<Developer> getDevelopers() { return developers; }
}
