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
        //assertEquals(,);

        // Game Exists Overwriting Old Game
        assertTrue(gb.pullGame("phgame"));
        assertTrue(uiplug.hasGame());
        //assertEquals(,);

        // Game Doesn't Exist
        assertFalse(gb.pullGame("phnegame"));
        //assertEquals(,);

        // String is null
        assertFalse(gb.pullGame(null));

        // throw exception if uiplug is null
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