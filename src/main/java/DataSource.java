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
     * Takes a GameList object and saves its properties
     * @param gameList the gameList to save
     * @throws IllegalArgumentException if game is null
     * @throws DataSourceException if underlying data source throws an exception
     */
    public void saveGameList(GameList gameList) throws IllegalArgumentException, DataSourceException;

    /**
     * Searches Data Source for a GameList based off its name. Games are created.
     * @param name the name of the GameList
     * @return GameList if matching a database entry, otherwise null
     * @throws DataSourceException if underlying data source throws an exception
     */
    public GameList loadGameList(String name) throws DataSourceException;

    /**
     * Takes a Developer object and saves its properties
     * @param dev the gameList to save
     * @throws IllegalArgumentException if game is null
     * @throws DataSourceException if underlying data source throws an exception
     */
    public void saveDeveloper(Developer dev) throws IllegalArgumentException, DataSourceException;

    /**
     * Searches Data Source for a Developer based off their name. A GameList is created.
     * @param dev the name of the Developer
     * @return Developer if matching a database entry, otherwise null
     * @throws DataSourceException if underlying data source throws an exception
     */
    public Developer loadDeveloper(String dev) throws DataSourceException;

    public void setInTransaction(boolean bool);

}
