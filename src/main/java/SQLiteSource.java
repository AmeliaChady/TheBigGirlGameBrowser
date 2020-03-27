public class SQLiteSource implements DataSource{
    /***
     * Creates a datasource based off a SQLite Database
     * @param path path to sqlite database
     * @throws IllegalArgumentException if path DNE
     */
    SQLiteSource(String path) throws IllegalArgumentException{
    }

    @Override
    public void saveGame(Game game) {

    }

    public boolean isConnected(){
        return false;
    }
}
