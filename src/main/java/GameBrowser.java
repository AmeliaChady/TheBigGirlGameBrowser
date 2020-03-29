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
    public GameBrowser(String dataFilePath) {
        // TODO remove after a DataSource.loadGameList has been made
        String[] gameTitlesInDb = {
                "LoadGameTest1", "LoadGameTest2", "testGame", "Test-zx the Game",
                "Toot Scooters", "testGame1", "testGame2", "testGame3"
        };
    }

    /**
     * Invalid Default Constructor
     * @throws Exception - a path to a data file is always needed
     */
    public GameBrowser() throws Exception {}

    // ------GETTERS------
    public GameList getGameList() { return gameList; }
}
