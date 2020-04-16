import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameListTest {

    @Test
    public void constructorTest() {
        // Create list with a name
        GameList gameList = new GameList("My Game List");
        assertEquals("My Game List", gameList.getName());

        // Create list without a name (invalid)
        assertThrows( IllegalArgumentException.class, () -> new GameList(""));
    }

    @Test
    public void includeGameTest() {
        GameList gameList = new GameList("My Game List");

        // Add one new game
        gameList.includeGame("game 1");
        assertEquals(1, gameList.getGameCount());

        // Add another new game
        gameList.includeGame("game 2");
        assertEquals(2, gameList.getGameCount());
    }

    @Test
    public void removeGameTest() {
        GameList gameList = new GameList("My Game List");

        gameList.includeGame("game 1");
        gameList.includeGame("game 2");

        // Remove existing game
        String removedGame = gameList.removeGame("game 1");
        assertEquals(1, gameList.getGameCount());
        assertEquals("game 1", removedGame);

        // Remove non-existing game (invalid)
        gameList.removeGame("game 0");
        assertEquals(1, gameList.getGameCount());
    }

    @Test
    public void getGame() {
        GameList gameList = new GameList("My Game List");

        gameList.includeGame("game 1");
        gameList.includeGame("game 2");

        // Get existing game
        String foundGame = gameList.getGame("game 1");
        assertEquals("game 1", foundGame);

        // Get non-existing game (invalid)
        foundGame = gameList.getGame("game 0");
        assertEquals(null, foundGame);
    }
}
