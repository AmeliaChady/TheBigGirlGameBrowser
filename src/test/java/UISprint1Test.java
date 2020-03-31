import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UISprint1Test {

    @Test
    public void displayGameTitlesNumberedListTest(){

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
}
