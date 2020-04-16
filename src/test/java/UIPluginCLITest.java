import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UIPluginCLITest {
    public static final String DATABASE = "src/databases/Test_UIPluginCLI.db";

    @Test
    public void displayGameTest(){
        UIPluginCLI uiplug= new UIPluginCLI();

        //0 developers, pending status
        Game g = new Game();
        uiplug.pullGame(g);
        assertEquals("Title: testGame\n" +
                     "Description: This is a test to create a new game object\n" +
                     "Developer(s): None\n" +
                     "Status: PENDING\n\n", uiplug.displayableGame());


        //1 developer, pending status
        g = new Game("Best game", "This is the best game ever!", "kerry", Status.PENDING);
        uiplug.pullGame(g);
        assertEquals("Title: Best game\n" +
                     "Description: This is the best game ever!\n" +
                     "Developer(s): kerry\n" +
                     "Status: PENDING\n\n", uiplug.displayableGame());

        //2 developers, pending status, no description
        List<String> developers = new ArrayList<>();
        developers.add("kerry anne");
        developers.add("kelsey");
        g = new Game("Cooking Mama",  developers);
        uiplug.pullGame(g);
        assertEquals("Title: Cooking Mama\n" +
                     "Description: No Description Given\n" +
                     "Developer(s): kerry anne, kelsey\n" +
                     "Status: PENDING\n\n", uiplug.displayableGame());

        //3 developers, pending status=
        // keeps devs from before + 1
        developers.add("grace t. dury");
        g = new Game("Animal Crossing New Horizons",
                    "Live as the only human, sell seashells to survive, and be in constant debt.",
                    developers, Status.PENDING);
        uiplug.pullGame(g);
        assertEquals("Title: Animal Crossing New Horizons\n" +
                     "Description: Live as the only human, sell seashells to survive, and be in constant debt.\n" +
                     "Developer(s): kerry anne, kelsey, grace t. dury\n" +
                     "Status: PENDING\n\n", uiplug.displayableGame());

        //1 developer, accepted
        g = new Game("camp rock 4", "kevin sells real estate now", "kevin jonas", Status.ACCEPTED);
        uiplug.pullGame(g);
        assertEquals("Title: camp rock 4\n" +
                     "Description: kevin sells real estate now\n" +
                     "Developer(s): kevin jonas\n" +
                     "Status: ACCEPTED\n\n", uiplug.displayableGame());

        //1 developer, rejected
        g = new Game("cutest dog <3",
                    "she is my dog. I hate her name but she's still cute",
                    "bertha", Status.REJECTED);
        uiplug.pullGame(g);
        assertEquals("Title: cutest dog <3\n" +
                     "Description: she is my dog. I hate her name but she's still cute\n" +
                     "Developer(s): bertha\n" +
                     "Status: REJECTED\n\n", uiplug.displayableGame());

        // null game
        uiplug.pullGame(null);
        assertThrows(IllegalStateException.class, uiplug::displayableGame);

    }

    @Test
    public void displayDeveloperTest(){
        UIPluginCLI uiplug = new UIPluginCLI();

        //0 games
        Developer d = new Developer("George Washington");
        uiplug.pullDeveloper(d);
        assertEquals("Name: George Washington\n" +
                     "George Washington's Games: This list is empty\n", uiplug.displayDeveloper());

        //1 game
        d.getGameList().includeGame("testGame");
        uiplug.pullDeveloper(d);
        assertEquals("Name: George Washington\n" +
                     "George Washington's Games: testGame\n", uiplug.displayDeveloper());

        //2 games
        d.getGameList().includeGame("testGame2");
        uiplug.pullDeveloper(d);
        assertEquals("Name: George Washington\n" +
                     "George Washington's Games: testGame, testGame2\n", uiplug.displayDeveloper());

        uiplug.pullDeveloper(null);
        assertThrows(IllegalStateException.class, uiplug::displayDeveloper);

    }

    @Test
    public void displayGameTitlesNumberedListTest() {
        UIPluginCLI uiplug = new UIPluginCLI();
        GameList gl = new GameList("test");

        //0 games
        uiplug.pullGameList(gl);
        assertEquals("There are no games to display\n", uiplug.displayGameTitlesNumberedList());

        //1 game
        gl.includeGame("Adventurer Kelsey");
        assertEquals("1. Adventurer Kelsey\n", uiplug.displayGameTitlesNumberedList());

        //2 games
        gl.includeGame("Adventurer Amelia");
        assertEquals("1. Adventurer Kelsey\n2. Adventurer Amelia\n", uiplug.displayGameTitlesNumberedList());

        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, uiplug::displayGameTitlesNumberedList);
    }

    @Test
    public void displayListNameAndGameTitlesTest() {
        UIPluginCLI uiplug = new UIPluginCLI();
        GameList gl = new GameList("testList");

        // 0 Games
        uiplug.pullGameList(gl);
        assertEquals("testList: This list is empty\n", uiplug.displayListNameAndGameTitles());

        // 1 Game
        gl.includeGame("testGame1");
        assertEquals("testList: testGame1\n", uiplug.displayListNameAndGameTitles());

        // 2 Games
        gl.includeGame("testGame2");
        assertEquals("testList: testGame1, testGame2\n", uiplug.displayListNameAndGameTitles());

        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, uiplug::displayListNameAndGameTitles);
    }

    @Test
    public void displayGamesGivenStatusTest() throws DataSourceException {
        // Needs GameBrowser
        System.out.println("NEEDS SETUP!");
        System.out.println("Games:\n" +
                "Title | Developer | Description | Status\n" +
                "ggstga | amelia | displayGamesGivenStatusTest | ACCEPTED\n" +
                "ggstgp1 | amelia | displayGamesGivenStatusTest | PENDING\n" +
                "ggstgp2 | amelia | displayGamesGivenStatusTest | PENDING\n");
        UIPluginCLI uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);
        GameList gl = new GameList("testList-ggst");

        uiplug.pullGameList(gl);
        uiplug.pullGameBrowser(gb);

        // 0 Games
        assertEquals("testList-ggst(PENDING):\n\nThis list is empty\n",
                uiplug.displayGamesGivenStatus(Status.PENDING));

        // 0 + 1 Other Status
        gl.includeGame("ggstga");
        assertEquals("testList-ggst(PENDING):\n\nThis list is empty\n",
                uiplug.displayGamesGivenStatus(Status.PENDING));

        // 1 Game
        gl.includeGame("ggstgp1");
        assertEquals("testList-ggst(PENDING):\n\n" +
                    "Title: ggstgp1\n" +
                    "Description: displayGamesGivenStatusTest\n" +
                    "Developer(s): amelia\n" +
                    "Status: PENDING\n\n",
                    uiplug.displayGamesGivenStatus(Status.PENDING));

        // 2 Games
        gl.includeGame("ggstgp2");
        assertEquals("testList-ggst(PENDING):\n\n" +
                        "Title: ggstgp1\n" +
                        "Description: displayGamesGivenStatusTest\n" +
                        "Developer(s): amelia\n" +
                        "Status: PENDING\n\n" +
                        "Title: ggstgp2\n" +
                        "Description: displayGamesGivenStatusTest\n" +
                        "Developer(s): amelia\n" +
                        "Status: PENDING\n\n",
                uiplug.displayGamesGivenStatus(Status.PENDING));

        // Accepted Status
        assertEquals("testList-ggst(ACCEPTED):\n\n" +
                        "Title: ggstga\n" +
                        "Description: displayGamesGivenStatusTest\n" +
                        "Developer(s): amelia\n" +
                        "Status: ACCEPTED\n\n",
                uiplug.displayGamesGivenStatus(Status.ACCEPTED));

        // Null Game List.
        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, () -> uiplug.displayGamesGivenStatus(Status.PENDING));

        gb.close();
        // Null GameBrowser
        uiplug.pullGameBrowser(null);
        uiplug.pullGameList(gl);
        assertThrows(IllegalStateException.class, () -> uiplug.displayGamesGivenStatus(Status.PENDING));
    }

    @Test
    public void displayAllGamesTest() throws DataSourceException {
        // Needs GameBrowser
        System.out.println("NEEDS SETUP!");
        System.out.println("Games:\n" +
                "Title | Developer | Description | Status\n" +
                "agtg1 | amelia | displayAllGamesTest | ACCEPTED\n" +
                "agtg2 | amelia | displayAllGamesTest | PENDING\n");
        UIPluginCLI uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);
        GameList gl = new GameList("testList-agt");

        uiplug.pullGameList(gl);
        uiplug.pullGameBrowser(gb);

        // 0 Games
        assertEquals("testList-agt:\n\nThis list is empty\n", uiplug.displayAllGames());

        // 1 Game
        gl.includeGame("agtg1");
        assertEquals( "testList-agt:\n\n" +
                "Title: agtg1\n" +
                "Description: displayAllGamesTest\n" +
                "Developer(s): amelia\n" +
                "Status: ACCEPTED\n\n",
                uiplug.displayAllGames());

        // 2 Games
        gl.includeGame("agtg2");
        assertEquals( "testList-agt:\n\n" +
                "Title: agtg1\n" +
                "Description: displayAllGamesTest\n" +
                "Developer(s): amelia\n" +
                "Status: ACCEPTED\n\n" +
                "Title: agtg2\n" +
                "Description: displayAllGamesTest\n" +
                "Developer(s): amelia\n" +
                "Status: PENDING\n\n",
                uiplug.displayAllGames());

        // Null Game List.
        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, uiplug::displayAllGames);

        gb.close();
        // Null GameBrowser
        uiplug.pullGameBrowser(null);
        uiplug.pullGameList(gl);
        assertThrows(IllegalStateException.class, uiplug::displayAllGames);
    }
}
