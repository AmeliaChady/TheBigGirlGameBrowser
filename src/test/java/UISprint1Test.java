import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UISprint1Test {

    @Test
    public void displayGameTitlesNumberedListTest() throws DataSourceException {

        UISprint1 ui = new UISprint1("UITests.db");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGameTitlesNumberedList(ui.gameBrowser.getGameList());
        assertEquals("1. robsGame\n" +
                "2. game 1\n" +
                "3. game 2\n" +
                "4. game 4\n" +
                "5. game 5\n" +
                "6. game 6\n" +
                "7. game 7\n" +
                "8. game 8\n" +
                "9. game 9\n" +
                "10. Candy Crush\n" +
                "11. Clash of clans\n", outContent.toString());

        //1 game
        ui.removeGame("game 1");
        ui.removeGame("game 2");
        ui.removeGame("game 3");
        ui.removeGame("game 4");
        ui.removeGame("game 5");
        ui.removeGame("game 6");
        ui.removeGame("game 6");
        ui.removeGame("game 7");
        ui.removeGame("game 8");
        ui.removeGame("game 9");
        ui.removeGame("robsGame");
        ui.removeGame("Candy Crush");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGameTitlesNumberedList(ui.gameBrowser.getGameList());
        assertEquals("1. Clash of clans\n", outContent.toString());

        //0 games
        ui.removeGame("Clash of clans");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGameTitlesNumberedList(ui.gameBrowser.getGameList());
        assertEquals("There are no games to display\n", outContent.toString());

    }

    @Test public void displayGamesGivenStatusTest() throws DataSourceException {
        UISprint1 ui = new UISprint1("UITests.db");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGamesGivenStatus(ui.gameBrowser.getGameList(), Status.PENDING);
        assertEquals("Master Game List(PENDING):\n" +
                "\n" +
                "Title: robsGame\n" +
                "Description: HEY LOOK IM IN A DataBase\n" +
                "Developer(s): Rob\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 1\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 1\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 2\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 2\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 4\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 4\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 5\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 5\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 6\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 6\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 7\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 7\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 8\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 8\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 9\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 9\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: Candy Crush\n" +
                "Description: My mom plays a lot of candy crush\n" +
                "Developer(s): Anita\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: Clash of clans\n" +
                "Description: My dad plays a lot of clash of clans\n" +
                "Developer(s): Robert\n" +
                "Status: PENDING\n\n", outContent.toString());

        //1 game
        ui.removeGame("game 1");
        ui.removeGame("game 2");
        ui.removeGame("game 3");
        ui.removeGame("game 4");
        ui.removeGame("game 5");
        ui.removeGame("game 6");
        ui.removeGame("game 6");
        ui.removeGame("game 7");
        ui.removeGame("game 8");
        ui.removeGame("game 9");
        ui.removeGame("robsGame");
        ui.removeGame("Candy Crush");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGamesGivenStatus(ui.gameBrowser.getGameList(), Status.PENDING);
        assertEquals("Master Game List(PENDING):\n\nTitle: Clash of clans\nDescription: My dad plays a lot of clash of clans\nDeveloper(s): Robert\nStatus: PENDING\n\n", outContent.toString());

        //0 games
        ui.removeGame("Clash of clans");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayGamesGivenStatus(ui.gameBrowser.getGameList(), Status.PENDING);
        assertEquals("Master Game List(PENDING):\n\nThis list is empty\n", outContent.toString());
    }

    @Test public void displayListNameAndGameTitlesTest() throws DataSourceException {
        UISprint1 ui = new UISprint1("UITests.db");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayListNameAndGameTitles(ui.gameBrowser.getGameList());
        assertEquals("Master Game List: robsGame, game 1, game 2, game 4, game 5, game 6, game 7, game 8, game 9, Candy Crush, Clash of clans\n", outContent.toString());

        //1 game
        ui.removeGame("game 1");
        ui.removeGame("game 2");
        ui.removeGame("game 3");
        ui.removeGame("game 4");
        ui.removeGame("game 5");
        ui.removeGame("game 6");
        ui.removeGame("game 6");
        ui.removeGame("game 7");
        ui.removeGame("game 8");
        ui.removeGame("game 9");
        ui.removeGame("robsGame");
        ui.removeGame("Candy Crush");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayListNameAndGameTitles(ui.gameBrowser.getGameList());
        assertEquals("Master Game List: Clash of clans\n", outContent.toString());

        //0 games
        ui.removeGame("Clash of clans");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayListNameAndGameTitles(ui.gameBrowser.getGameList());
        assertEquals("Master Game List: This list is empty\n", outContent.toString());
    }

    @Test
    public void displayAllGamesTest() throws DataSourceException {
        UISprint1 ui = new UISprint1("UITests.db");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayAllGames(ui.gameBrowser.getGameList());
        assertEquals("Master Game List:\n" +
                "\n" +
                "Title: robsGame\n" +
                "Description: HEY LOOK IM IN A DataBase\n" +
                "Developer(s): Rob\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 1\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 1\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 2\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 2\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 4\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 4\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 5\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 5\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 6\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 6\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 7\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 7\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 8\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 8\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: game 9\n" +
                "Description: No Description Given\n" +
                "Developer(s): dev 9\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: Candy Crush\n" +
                "Description: My mom plays a lot of candy crush\n" +
                "Developer(s): Anita\n" +
                "Status: PENDING\n" +
                "\n" +
                "Title: Clash of clans\n" +
                "Description: My dad plays a lot of clash of clans\n" +
                "Developer(s): Robert\n" +
                "Status: PENDING\n" +
                "\n", outContent.toString());

        //1 game
        ui.removeGame("game 1");
        ui.removeGame("game 2");
        ui.removeGame("game 3");
        ui.removeGame("game 4");
        ui.removeGame("game 5");
        ui.removeGame("game 6");
        ui.removeGame("game 6");
        ui.removeGame("game 7");
        ui.removeGame("game 8");
        ui.removeGame("game 9");
        ui.removeGame("robsGame");
        ui.removeGame("Candy Crush");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayAllGames(ui.gameBrowser.getGameList());
        assertEquals("Master Game List:\n\nTitle: Clash of clans\n" +
        "Description: My dad plays a lot of clash of clans\n" +
                "Developer(s): Robert\n" +
                "Status: PENDING\n" +
                "\n", outContent.toString());

        //0 games
        ui.removeGame("Clash of clans");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.displayAllGames(ui.gameBrowser.getGameList());
        assertEquals("Master Game List:\n\nThis list is empty\n", outContent.toString());
    }


}
