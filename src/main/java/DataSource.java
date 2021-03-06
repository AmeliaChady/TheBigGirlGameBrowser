import java.util.List;
import java.util.Map;

enum AccountSavingFlags{FAIL, PASS, DUPLICATE, NOTHING}
enum AccountSavingAccounts{ACCT, DEV, USER}

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

    public List<String> loadDeveloperList() throws DataSourceException;

    void removeDeveloper(Developer developer) throws DataSourceException;

    /**
     * Logs in a user and returns the accounts they have connected
     * @param username overall account username
     * @param password overall account password
     * @return the accounts in an Accounts object
     * @throws IllegalArgumentException if overall account doesn't exist
     */
    Accounts login(String username, String password) throws DataSourceException, IllegalArgumentException;

    /**
     * Creates a new account for a user or a developer
     * @param account
     * @return map {'a': account status, 'd': dev status, 'u': user status}
     * @throws DataSourceException
     * @throws IllegalArgumentException
     */
    Map<AccountSavingAccounts, AccountSavingFlags> saveAccount(Accounts account) throws DataSourceException, IllegalArgumentException;

    void saveUser(Accounts account) throws DataSourceException, IllegalArgumentException;

    public User loadUser(String user) throws DataSourceException;

    void close();
}
