import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class DeveloperTests {

    @Test
    public void developerConstructorTest(){
        Developer d1 = new Developer("Bob (the cat)", 0);
        assertNotNull(d1.getName());
        assertNotNull(d1.getGameList());
        assertEquals(0, d1.getGameList().getGameCount());
        assertEquals("Bob (the cat)", d1.getName());
        assertEquals( 0, d1.getAid());

        Developer d2 = new Developer("Bob (my dad)", 1 );
        assertNotNull(d2.getName());
        assertNotNull(d2.getGameList());
        assertEquals(0, d2.getGameList().getGameCount());
        assertEquals("Bob (my dad)", d2.getName());
        assertEquals( 1, d2.getAid());
    }

    @Test
    public void developerSubmitGameTest() {
        //make all the developers for testing
        Developer d1 = new Developer("Milk Dad", 1);
        Developer d2 = new Developer("The Sunshine Gang", 2);
        Developer d3 = new Developer("I just want to go to sleep", 3);
        Developer d4 = new Developer("The Korona Krew", 4);
        Developer d5 = new Developer("Brett Michaels", 5 );
        Developer d6 = new Developer("six", 6);
        Developer d7 = new Developer("seven", 7);
        Developer d8 = new Developer("eight",8);

        //add games to array list
        d6.submitGame("Billy Bob Goes to The Moon");
        d6.submitGame("Why are there like four different sizes of gatorade");
        d7.submitGame("Fight Your Dad Simulator");
        d8.submitGame("beep in traffic");
        d4.submitGame("jeff bezos takes over the world");

        //in developer list
        assertEquals(2, d6.getGameList().getGameCount());

        d6.submitGame("Billy Bob Goes to The Moon");

        //games list with one added in games list and in d6's list
        assertEquals(2, d6.getGameList().getGameCount());

        //add another game with new developer
        d7.submitGame("title6");

        assertEquals(2, d7.getGameList().getGameCount());
        //developer 6 list should remain unaffected
        assertEquals(2, d6.getGameList().getGameCount());

        //add another game with new developer

        d8.submitGame("title7");

        assertEquals(2, d8.getGameList().getGameCount());
        //other developer lists should remain unaffected
        assertEquals(2, d6.getGameList().getGameCount());
        assertEquals(2, d7.getGameList().getGameCount());


        //add another game to an existing developers list
        d6.submitGame("title9");

        assertEquals(3, d6.getGameList().getGameCount());
    }
}
