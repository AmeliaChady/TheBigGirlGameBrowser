public interface UIPlugin {
    /**
     * Brings game into the UIPlugin
     * @param game game to pull into
     */
    void pullGame(Game game);

    /**
     * Brings gamelist into the UIPlugin
     * @param gl the gamelist
     */
    void pullGameList(GameList gl);

    /**
     * Brings developer into the UIPlugin
     * @param dev developer to bring in
     */
    void pullDeveloper(Developer dev);

    /**
     * Brings gameBrowser into the UIPlugin
     * @param gb gamebrowser to pull in
     */
    void pullGameBrowser(GameBrowser gb);

    /**
     * Brings Review into the UIPlugin
     * @param r1 review to pull in
     */
    void pullReview(Review r1);

    /**
     * @return false if game null, otherwise true
     */
    boolean hasGame();

    /**
     * @return false if gamelist null, otherwise true
     */
    boolean hasGameList();

    /**
     * @return false if developer null, otherwise true
     */
    boolean hasDeveloper();

    /**
     * @return false if gamebrowser null, otherwise true
     */
    boolean hasGameBrowser();

    /**
     *
     * @return string of game
     */
    String displayableGame();

    /**
     * @return string of Developer
     */
    String displayDeveloper();

    /**
     * @return string of all games
     */
    String displayAllGames();

    /**
     * @return string of all numbered games
     */
    String displayListNameAndGameTitles();

    /**
     * @return string of all games of a certain status
     */
    String displayGamesGivenStatus(Status status);

    /**
     * @return string of numbered list of all games
     */
    String displayGameTitlesNumberedList();

    /**
     * @return string of numbered list of all games of a certain status
     */
    String displayNumberedListOfGamesGivenStatus(Status status) throws DataSourceException;

    /**
     * @return list of all games of a certain status
     */
    GameList getGamesGivenStatus(Status status) throws DataSourceException;

    String displayableNumberedListOfFullGames()  throws DataSourceException;

    String displayableReview();
}