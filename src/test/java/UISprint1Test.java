import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UISprint1Test {

    @Test
    public void displayGameTitlesNumberedListTest() throws DataSourceException {

        UISprint1 ui = new UISprint1("testing.db");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGameTitlesNumberedList(ui.gameBrowser.getGameList());
        assertEquals("1. LoadGameTest1\n" +
                "2. LoadGameTest2\n" +
                "3. testGame\n" +
                "4. Test-zx the Game\n" +
                "5. Toot Scooters\n" +
                "6. testGame1\n" +
                "7. testGame2\n" +
                "8. testGame3\n", outContent.toString());

        //1 game
        ui.removeGame("LoadGameTest1");
        ui.removeGame("LoadGameTest2");
        ui.removeGame("testGame");
        ui.removeGame("Test-zx the Game");
        ui.removeGame("Toot Scooters");
        ui.removeGame("testGame1");
        ui.removeGame("testGame2");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGameTitlesNumberedList(ui.gameBrowser.getGameList());
        assertEquals("1. testGame3\n", outContent.toString());

        //0 games
        ui.removeGame("testGame3");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGameTitlesNumberedList(ui.gameBrowser.getGameList());
        assertEquals("There are no games to display\n", outContent.toString());

    }

    @Test public void displayGamesGivenStatusTest() throws DataSourceException {
        UISprint1 ui = new UISprint1("testing.db");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGamesGivenStatus(ui.gameBrowser.getGameList(), Status.PENDING);
        assertEquals("Master List(PENDING):\n" +
                "\n" +
                "Title: LoadGameTest1\n" +
                "Description: description\n" +
                "Developer(s): LGT_A\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: testGame\n" +
                "Description: This is a test to save a game object\n" +
                "Developer(s): Frank\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: Toot Scooters\n" +
                "Description: mario kart hack where all sounds are balloons popping\n" +
                "Developer(s): CorgiLover87, Ted\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: testGame1\n" +
                "Description: this Game is a Test game\n" +
                "Developer(s): Bobby\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: testGame2\n" +
                "Description: this Game is a Test game\n" +
                "Developer(s): Bobby\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: testGame3\n" +
                "Description: this Game is a Test game\n" +
                "Developer(s): Bobby\n" +
                "Status: PENDING\n\n", outContent.toString());

        //1 game
        ui.removeGame("LoadGameTest1");
        ui.removeGame("LoadGameTest2");
        ui.removeGame("testGame");
        ui.removeGame("Test-zx the Game");
        ui.removeGame("Toot Scooters");
        ui.removeGame("testGame1");
        ui.removeGame("testGame2");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGamesGivenStatus(ui.gameBrowser.getGameList(), Status.PENDING);
        assertEquals("Master List(PENDING):\n\nTitle: testGame3\nDescription: this Game is a Test game\nDeveloper(s): Bobby\nStatus: PENDING\n\n", outContent.toString());

        //0 games
        ui.removeGame("testGame3");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGamesGivenStatus(ui.gameBrowser.getGameList(), Status.PENDING);
        assertEquals("Master List(PENDING):\n\nThis list is empty\n", outContent.toString());
    }

    @Test public void displayListNameAndGameTitlesTest() throws DataSourceException {
        UISprint1 ui = new UISprint1("testing.db");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayListNameAndGameTitles(ui.gameBrowser.getGameList());
        assertEquals("Master List: LoadGameTest1, LoadGameTest2, testGame, Test-zx the Game, Toot Scooters, testGame1, testGame2, testGame3\n", outContent.toString());

        //1 game
        ui.removeGame("LoadGameTest1");
        ui.removeGame("LoadGameTest2");
        ui.removeGame("testGame");
        ui.removeGame("Test-zx the Game");
        ui.removeGame("Toot Scooters");
        ui.removeGame("testGame1");
        ui.removeGame("testGame2");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayListNameAndGameTitles(ui.gameBrowser.getGameList());
        assertEquals("Master List: testGame3\n", outContent.toString());

        //0 games
        ui.removeGame("testGame3");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayListNameAndGameTitles(ui.gameBrowser.getGameList());
        assertEquals("Master List: This list is empty\n", outContent.toString());
    }

    @Test
    public void displayAllGamesTest() throws DataSourceException {
        UISprint1 ui = new UISprint1("testing.db");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayAllGames(ui.gameBrowser.getGameList());
        assertEquals("Master List:\n" +
                "\n" +
                "Title: LoadGameTest1\n" +
                "Description: description\n" +
                "Developer(s): LGT_A\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: LoadGameTest2\n" +
                "Description: noitpircsed\n" +
                "Developer(s): LGT_B, LGT_C\n" +
                "Status: ACCEPTED\n" +
                "\n" +
                "Title: testGame\n" +
                "Description: This is a test to save a game object\n" +
                "Developer(s): Frank\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: Test-zx the Game\n" +
                "Description: cool robot game\n" +
                "Developer(s): Ted\n" +
                "Status: ACCEPTED\n" +
                "\n" +
                "Title: Toot Scooters\n" +
                "Description: mario kart hack where all sounds are balloons popping\n" +
                "Developer(s): CorgiLover87, Ted\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: testGame1\n" +
                "Description: this Game is a Test game\n" +
                "Developer(s): Bobby\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: testGame2\n" +
                "Description: this Game is a Test game\n" +
                "Developer(s): Bobby\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: testGame3\n" +
                "Description: this Game is a Test game\n" +
                "Developer(s): Bobby\n" +
                "Status: PENDING\n" +
                "\n", outContent.toString());

        //1 game
        ui.removeGame("LoadGameTest1");
        ui.removeGame("LoadGameTest2");
        ui.removeGame("testGame");
        ui.removeGame("Test-zx the Game");
        ui.removeGame("Toot Scooters");
        ui.removeGame("testGame1");
        ui.removeGame("testGame2");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayAllGames(ui.gameBrowser.getGameList());
        assertEquals("Master List:\n" +
                "\n" +
                "Title: testGame3\n" +
                "Description: this Game is a Test game\n" +
                "Developer(s): Bobby\n" +
                "Status: PENDING\n" +
                "\n", outContent.toString());

        //0 games
        ui.removeGame("testGame3");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayAllGames(ui.gameBrowser.getGameList());
        assertEquals("Master List:\n\nThis list is empty\n", outContent.toString());
    }


}
