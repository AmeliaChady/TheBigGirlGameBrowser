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
        UIDisplayPluginCLI uiplug = new UIDisplayPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);

        // Game Exists
        assertTrue(gb.pullGame("phgame"));
        assertTrue(uiplug.hasGame());
        assertEquals("Title: phgame\n" +
                "Description: PullAndHasGameTest\n" +
                "Developer(s): amelia\n" +
                "Status: ACCEPTED\n\n", uiplug.displayGame());

        // Game Exists Overwriting Old Game
        assertTrue(gb.pullGame("oogame"));
        assertTrue(uiplug.hasGame());
        assertEquals("Title: oogame\n" +
                "Description: PullAndHasGameTest\n" +
                "Developer(s): amelia\n" +
                "Status: PENDING\n\n", uiplug.displayGame());

        // Game Doesn't Exist
        assertFalse(gb.pullGame("phnegame"));
        assertEquals("Title: oogame\n" +
                "Description: PullAndHasGameTest\n" +
                "Developer(s): amelia\n" +
                "Status: PENDING\n\n", uiplug.displayGame());

        // String is null
        assertFalse(gb.pullGame(null));
        assertEquals("Title: oogame\n" +
                "Description: PullAndHasGameTest\n" +
                "Developer(s): amelia\n" +
                "Status: PENDING\n\n", uiplug.displayGame());

        gb.close();
        // throw exception if uiplug is null
        GameBrowser gbn = new GameBrowser(DATABASE, null);
        assertThrows(IllegalAccessError.class, () -> gbn.pullGame("phgame"));
    }

    @Test
    public static void PullAndHasGameList(){
        // GameList Exists

        // GameList Exists Overwriting Old GameList

        // GameList Doesn't Exist


        // throw exception if uiplug is null
    }

    @Test
    public static void PullAndHasDeveloper(){
        // Developer Exists

        // Developer Exists Overwriting Old Developer

        // Developer Doesn't Exist


        // throw exception if uiplug is null
    }

    @Test
    public static void ConstructorPullAndHasGameBrowser(){

    }
}