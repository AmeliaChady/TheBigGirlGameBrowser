import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameBrowserUIPluginTest {
    public static final String DATABASE = "src/databases/Test_UIPluginCLI.db";

    @Test
    public static void PullAndHasGame() throws DataSourceException{
        System.out.println("NEEDS SETUP!");
        System.out.println("Games:\n" +
                "Title | Developer | Description | Status\n" +
                "phgame | amelia | PullAndHasGameTest | ACCEPTED\n" +
                "oogame | amelia | PullAndHasGameTest | PENDING\n" +
                "and no game called phnegame");
        UIPluginCLI uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);

        // Game Exists
        assertTrue(gb.pullGame("phgame"));
        assertTrue(uiplug.hasGame());

        // Game Exists Overwriting Old Game
        assertTrue(gb.pullGame("oogame"));
        assertTrue(uiplug.hasGame());

        // Game Doesn't Exist
        assertFalse(gb.pullGame("phnegame"));
        assertFalse(uiplug.hasGame());

        // String is null
        assertFalse(gb.pullGame(null));
        assertFalse(uiplug.hasGame());

        gb.close();
        // throw exception if uiplug is null
        GameBrowser gbn = new GameBrowser(DATABASE, null);
        assertThrows(IllegalAccessError.class, () -> gbn.pullGame("phgame"));
    }

    @Test
    public static void PullAndHasGameList() throws DataSourceException{
        System.out.println("NEEDS SETUP!");
        System.out.println("GameLists:\n" +
                "GameLists: phgl, oogl\n" +
                "and no GameList called phnegl");
        UIPlugin uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);

        // GameList Exists
        assertTrue(gb.pullGameList("phgl"));
        assertTrue(uiplug.hasGameList());

        // GameList Exists Overwriting Old GameList
        assertTrue(gb.pullGameList("oogl"));
        assertTrue(uiplug.hasGameList());

        // GameList Doesn't Exist
        assertFalse(gb.pullGameList("phnegl"));
        assertFalse(uiplug.hasGameList());

        // String is null
        assertFalse(gb.pullGameList(null));
        assertFalse(uiplug.hasGameList());

        gb.close();
        // throw exception if uiplug is null
        GameBrowser gbn = new GameBrowser(DATABASE, null);
        assertThrows(IllegalAccessError.class, () -> gbn.pullGameList("phgl"));
    }

    @Test
    public static void PullAndHasDeveloper() throws DataSourceException{
        System.out.println("NEEDS SETUP!");
        System.out.println("Developers:\n" +
                "Developers: phd, ood\n" +
                "and no Developer called phned");
        UIPlugin uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);

        // Developer Exists
        assertTrue(gb.pullDeveloper("phd"));
        assertTrue(uiplug.hasDeveloper());

        // Developer Exists Overwriting Old Developer
        assertTrue(gb.pullDeveloper("ood"));
        assertTrue(uiplug.hasDeveloper());

        // Developer Doesn't Exist
        assertFalse(gb.pullDeveloper("phned"));
        assertFalse(uiplug.hasDeveloper());

        // String is null
        assertFalse(gb.pullDeveloper(null));
        assertFalse(uiplug.hasDeveloper());

        gb.close();
        // throw exception if uiplug is null
        GameBrowser gbn = new GameBrowser(DATABASE, null);
        assertThrows(IllegalAccessError.class, () -> gbn.pullDeveloper("phd"));
    }

    @Test
    public static void ConstructorGameBrowserUIPlugin() throws DataSourceException{
        UIPlugin uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);
        assertTrue(uiplug.hasGameBrowser());
        assertTrue(gb.hasUIPlugin());
        gb.close();
    }
}