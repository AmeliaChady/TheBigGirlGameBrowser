import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

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
    public void developerSumbitGameTest(){
        //make all the developers for testing
        Developer d1 = new Developer("Milk Dad");
        Developer d2 = new Developer("The Sunshine Gang");
        Developer d3 = new Developer("I just want to go to sleep");
        Developer d4 = new Developer("The Korona Krew");
        Developer d5 = new Developer("Brett Michaels");

        //make all the games for testing
        Game g1 = new Game("Billy Bob Goes to The Moon", "a fun game yeehaw", d1, Status.PENDING);
        Game g2 = new Game("Why are there like four different sizes of gatorade", "im so tired",
                d2, Status.ACCEPTED);
        Game g3 = new Game("Fight Your Dad Simulator", "u kno what it's about", d3, Status.PENDING);
        Game g4 = new Game("beep in traffic", "the cars never move", d4, Status.ACCEPTED);
        Game g5 = new Game("jeff bezos takes over the world", "oh wait that's already happening",
                d5, Status.REJECTED);

        //create test array list
        List<Game> gameListTest = new ArrayList<Game>();

        //add games to array list
        gameListTest.add(g1);
        gameListTest.add(g2);
        gameListTest.add(g3);
        gameListTest.add(g4);
        gameListTest.add(g5);

        //NOW WE CAN ACTUALLY TEST THE FUNCTION BABIE

        Game g6 = new Game("title6", "description6", d1, Status.PENDING);

        


    }
}
