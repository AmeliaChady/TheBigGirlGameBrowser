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
}