import org.junit.jupiter.api.Test;

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
        gameList.includeGame(new Game("game 1", new Developer()));
        assertEquals(1, gameList.getGameCount());

        // Add another new game
        gameList.includeGame(new Game("game 2", new Developer()));
        assertEquals(2, gameList.getGameCount());
    }
}
