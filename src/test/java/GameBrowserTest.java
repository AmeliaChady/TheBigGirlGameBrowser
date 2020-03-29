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
}
