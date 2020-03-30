import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameBrowserTest {

    @Test
    public void constructorTest() {
        GameBrowser gameBrowser;

        // Empty constructor use (invalid)
        try {
            gameBrowser = new GameBrowser();
            fail("Using empty constructor should fail test.");
        } catch(Exception e) {
            assertEquals("java.lang.Exception", e.getClass().getName());
            assertEquals( "Invalid constructor. Supply a data source file.", e.getMessage());
        }

        // Default constructor with empty non-existent file path (invalid)
        assertThrows(IllegalArgumentException.class, () -> new GameBrowser(""));

        // Default constructor with non-existent file path (invalid)
        assertThrows(IllegalArgumentException.class, () -> new GameBrowser("../some_file"));

        // Default constructor with exisiting file path
        gameBrowser = new GameBrowser("testing.db");
        int expectedGameCount = 8;
        assertNotEquals(null, gameBrowser.getGameList());
        assertEquals(expectedGameCount, gameBrowser.getGameList().getGameCount());

        // TODO check developers (once devs can be loaded from db)
        // TODO check administrators (once admins can be loaded from db)
    }

    @Test
    public void addGameTest(){
        GameBrowser gb = new GameBrowser("testing.db");

        assertEquals(8, gb.getGameList().getGameCount());

        //add game
        Developer dev = new Developer("Anita");
        Game gameToAdd = new Game("Candy Crush","My mom plays a lot of candy crush", dev, Status.PENDING);
        gb.addGame(gameToAdd);
        assertEquals(9, gb.getGameList().getGameCount());
        assertEquals("Candy Crush", gb.getGameList().getGameList().get(gb.getGameList().getGameCount()-1).getTitle());
        assertEquals("My mom plays a lot of candy crush", gb.getGameList().getGameList().get(gb.getGameList().getGameCount()-1).getDescription());
        assertEquals(dev, gb.getGameList().getGameList().get(gb.getGameList().getGameCount()-1).getDevelopers().get(0));
        assertEquals(Status.PENDING, gb.getGameList().getGameList().get(gb.getGameList().getGameCount()-1).getStatus());

        //pass game properties
        Developer dev2 = new Developer("Robert");
        List<Developer> devList = new ArrayList<Developer>();
        devList.add(dev2);
        gb.addGame("Clash of clans", "My dad plays a lot of clash of clans", devList, Status.PENDING);

        assertEquals(10, gb.getGameList().getGameCount());
        assertEquals("Clash of clans", gb.getGameList().getGameList().get(gb.getGameList().getGameCount()-1).getTitle());
        assertEquals("My dad plays a lot of clash of clans", gb.getGameList().getGameList().get(gb.getGameList().getGameCount()-1).getDescription());
        assertEquals(devList.get(0), gb.getGameList().getGameList().get(gb.getGameList().getGameCount()-1).getDevelopers().get(0));
        assertEquals(Status.PENDING, gb.getGameList().getGameList().get(gb.getGameList().getGameCount()-1).getStatus());

    }
}
