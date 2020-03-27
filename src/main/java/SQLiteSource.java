public class SQLiteSource implements DataSource{
    /***
     * Creates a datasource based off a SQLite Database
     * @param path path to sqlite database
     * @throws IllegalArgumentException if path DNE
     */
    SQLiteSource(String path) throws IllegalArgumentException{
    }

    @Override
    public void saveGame(Game game) throws IllegalArgumentException, DataSourceException{

    }

    /***
     * @return connection state of sqlite db
     */
    public boolean isConnected(){
        return false;
    }
}
