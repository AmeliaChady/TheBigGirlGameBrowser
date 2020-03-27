public interface DataSource {
    /***
     * Takes a Game object and saves its properties
     * @param game the game to save
     * @throws IllegalArgumentException if game is null
     */
    public void saveGame(Game game);
}
