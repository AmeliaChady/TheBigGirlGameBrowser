public interface UIPlugin {
    void pullGame(Game game);
    void pullGameList(GameList gl);
    void pullDeveloper(Developer dev);
    void pullGameBrowser(GameBrowser gb);
    boolean hasGame();
    boolean hasGameList();
    boolean hasDeveloper();
    boolean hasGameBrowser();
}