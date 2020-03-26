import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Sprint1Tests {

    @Test
    public void gameConstructorTest(){
        Game game = new Game();

        assertEquals("testGame", game.getTitle());
        assertEquals("This is a test to create a new game object", game.getDescription());
        assertNull(game.getDeveloper());

        Game newGame = new Game("daBears", new Developer("Sheila"));

        assertEquals("daBears", newGame.getTitle());
        assertEquals("No Description Given", newGame.getDescription());
        assertNotNull(newGame.getDeveloper());

        Game describedGame = new Game("badTitle", "Bad Description", new Developer("Kelly"));
        assertEquals("badTitle", describedGame.getTitle());
        assertEquals("Bad Description", describedGame.getDescription());
        assertNotNull(describedGame.getDeveloper());
    }

    @Test
    public void changeDescriptionTest(){
        Game game = new Game("daBears", new Developer("Wendell"));

        assertEquals("No Description Given", game.getDescription());

        String aDescription = "Hey Fans! Checkout this awesome 'New Game' that I just released on the Big Girls Game Browser!!!";

        game.changeDescription(aDescription);

        assertEquals(aDescription, game.getDescription());
    }

    @Test
    public void changeTitleTest(){
        Game game = new Game("badTitle", "Bad Description", new Developer("Winston"));

        assertEquals("badTitle", game.getTitle());

        game.changeTitle("goodTitle");

        assertEquals("goodTitle", game.getTitle());
    }
}