import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Sprint1Tests {

    @Test
    public void gameConstructorTest(){
        Game game = new Game();

        assertEquals("testGame", game.getTitle());
        assertEquals("This is a test to create a new game object", game.getDescription());
        assertNull(game.getDeveloper());

        Game newGame = new Game("daBears", new Developer());

        assertEquals("daBears", newGame.getTitle());
        assertEquals("No Description Given", newGame.getDescription());
        assertNotNull(newGame.getDeveloper());

    }
}
