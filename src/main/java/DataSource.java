import java.util.List;

public interface DataSource {
    /**
     * Takes a Game object and saves its properties
     * @param game the game to save
     * @throws IllegalArgumentException if game is null
     * @throws DataSourceException if underlying data source throws an exception
     */
    public void saveGame(Game game) throws IllegalArgumentException, DataSourceException;

    /**
     * Searches Data Source for a Game based off its title. Developers are created.
     * @param title the title to look for
     * @return Game if the DataSource has a matching title. Otherwise null.
     * @throws DataSourceException if underlying data source throws an exception
     */
    public Game loadGame(String title) throws DataSourceException;

    /**
     * Searches Data Source for a Game based off its title. Developers are created if needed.
     * @param title the title to look for
     * @return Game if the DataSource has a matching title. Otherwise null.
     * @throws DataSourceException if underlying data source throws an exception
     */
    public Game loadGame(String title, List<Developer> developers) throws DataSourceException;
}
