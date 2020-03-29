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
     * Takes a GameList object and saves its properties
     * @param gameList the gameList to save
     * @throws IllegalArgumentException if game is null
     * @throws DataSourceException if underlying data source throws an exception
     */
    public void saveGameList(GameList gameList) throws IllegalArgumentException, DataSourceException;

    /**
     * Takes a Developer object and saves its properties
     * @param dev the gameList to save
     * @throws IllegalArgumentException if game is null
     * @throws DataSourceException if underlying data source throws an exception
     */
    public void saveDeveloper(Developer dev) throws IllegalArgumentException, DataSourceException;

    /**
     * Searches Data Source for a Game based off its title. Developers are created.
     * @param title the title to look for
     * @return Game if the DataSource has a matching title. Otherwise null.
     * @throws DataSourceException if underlying data source throws an exception
     */
    public Game loadGame(String title) throws DataSourceException;
}
