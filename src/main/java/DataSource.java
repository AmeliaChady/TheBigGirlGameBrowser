public interface DataSource {
    /***
     * Takes a Game object and saves its properties
     * @param game the game to save
     * @throws IllegalArgumentException if game is null
     * @throws DataSourceException if underlying data source throws an exception
     */
    public void saveGame(Game game) throws IllegalArgumentException, DataSourceException;
}
