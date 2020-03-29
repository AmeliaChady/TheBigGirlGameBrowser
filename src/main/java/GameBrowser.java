import javax.xml.crypto.Data;
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

    // ------GETTERS------
    public GameList getGameList() { return gameList; }
}
