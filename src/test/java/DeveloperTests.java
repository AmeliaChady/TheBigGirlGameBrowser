import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DeveloperTests {

    @Test
    public void developerConstructorTest(){
        Developer d1 = new Developer("Bob (the cat)");
        assertNotNull(d1.getName());
        assertNotNull(d1.getGameList());
        assertEquals(0, d1.getGameList().getGameCount());
        assertEquals("Bob (the cat)", d1.getName());

        Developer d2 = new Developer("Bob (my dad)");
        assertNotNull(d2.getName());
        assertNotNull(d2.getGameList());
        assertEquals(0, d2.getGameList().getGameCount());
        assertEquals("Bob (my dad)", d2.getName());
    }

    @Test
    public void displayDeveloperTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //0 games
        Developer d1 = new Developer("George Washington");
        d1.displayDeveloper();
        assertEquals("Name: George Washington\nGeorge Washington's Games: This list is empty", outContent.toString());

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //1 game
        Game game = new Game();
        d1.getGameList().includeGame(game);
        d1.displayDeveloper();
        assertEquals("Name: George Washington\nGames: testGame\n\n", outContent.toString());


        //2 games
        Game game2 = new Game();
        d1.getGameList().includeGame(game2);

        assertEquals("Name: George Washington\nGames: testGame, testGame\n\n", outContent.toString());
    }
}
