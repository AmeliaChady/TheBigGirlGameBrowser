import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class GameBrowser {
    private DataSource dataSource;
    private String gameListName;
    private GameList gameList;
    private List<Administrator> administrators;
    private List<Developer> developers;

    /**
     * Constructor (
     * @param dataFilePath - file path to load all data from
     */
    public GameBrowser(String dataFilePath) throws IllegalArgumentException, DataSourceException {
        if (dataFilePath.length() == 0)
            throw new IllegalArgumentException("Please supply a filename.");

        dataSource = new SQLiteSource(dataFilePath);
        gameListName = "Master List";
        gameList = new GameList("Master List");
        developers = new ArrayList<Developer>();

        // load games from data source
        loadAllGames();
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

    /**
     * Removes a developer from the developer list
     * @param username - the username of the developer
     * @return Developer - the developer that was removed
     */
    public Developer removeDeveloper(String username) {
        Developer developer = null;

        for (Developer d : developers) {
            if (d.getName().equals(username)) {
                developer = d;
                developers.remove(developer);
                break;
            }
        }
        return developer;
    }

    // ------HELPERS------
    private void loadAllGames() throws DataSourceException {
    }

    // ------GETTERS------
    public GameList getGameList() { return gameList; }

    public List<Developer> getDevelopers() { return developers; }
}
