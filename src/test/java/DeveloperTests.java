import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    public void developerSubmitGameTest() {
        //make all the developers for testing
        String d1 = new Developer("Milk Dad");
        String d2 = new Developer("The Sunshine Gang");
        String d3 = new Developer("I just want to go to sleep");
        String d4 = new Developer("The Korona Krew");
        String d5 = new Developer("Brett Michaels");
        String d6 = new Developer("six");
        String d7 = new Developer("seven");
        String d8 = new Developer("eight");

        //make all the games for testing
        Game g1 = new Game("Billy Bob Goes to The Moon", "a fun game yeehaw", d1, Status.PENDING);
        Game g2 = new Game("Why are there like four different sizes of gatorade", "im so tired",
                d2, Status.ACCEPTED);
        Game g3 = new Game("Fight Your Dad Simulator", "u kno what it's about", d3, Status.PENDING);
        Game g4 = new Game("beep in traffic", "the cars never move", d4, Status.ACCEPTED);
        Game g5 = new Game("jeff bezos takes over the world", "oh wait that's already happening",
                d5, Status.REJECTED);

        GameList gameListTest = new GameList("all games");

        //add games to array list
        gameListTest.includeGame(g1);
        gameListTest.includeGame(g2);
        gameListTest.includeGame(g3);
        gameListTest.includeGame(g4);
        gameListTest.includeGame(g5);

        //NOW WE CAN ACTUALLY TEST THE FUNCTION BABIE

        Game g6 = new Game("title6", "description6", d6, Status.PENDING);
        Game g7 = new Game("title7", "description7", d7, Status.PENDING);
        Game g8 = new Game("title8", "description8", d8, Status.PENDING);
        Game g9 = new Game("title9", "description9", d6, Status.PENDING);


        //all games in gameList, none in developer list
        assertEquals(5, gameListTest.getGameCount());
        assertEquals(2, d6.getGameList().getGameCount());

        d6.submitGame(g6, gameListTest);

        //games list with one added in games list and in d1's list
        assertEquals(6, gameListTest.getGameCount());
        assertEquals(2, d6.getGameList().getGameCount());

        //add another game with new developer
        d7.submitGame(g7, gameListTest);

        assertEquals(7, gameListTest.getGameCount());
        assertEquals(1, d7.getGameList().getGameCount());
        //developer 6 list should remain unaffected
        assertEquals(2, d6.getGameList().getGameCount());

        //add another game with new developer

        d8.submitGame(g8, gameListTest);

        assertEquals(8, gameListTest.getGameCount());
        assertEquals(1, d8.getGameList().getGameCount());
        //other developer lists should remain unaffected
        assertEquals(2, d6.getGameList().getGameCount());
        assertEquals(1, d7.getGameList().getGameCount());


        //add another game to an existing developers list
        d6.submitGame(g9, gameListTest);

        assertEquals(9, gameListTest.getGameCount());
        assertEquals(2, d6.getGameList().getGameCount());
    }

    public void displayDeveloperTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //0 games
        Developer d1 = new Developer("George Washington");
        d1.displayDeveloper();
        assertEquals("Name: George Washington\nGeorge Washington's Games: This list is empty\n", outContent.toString());

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //1 game
        Game game = new Game();
        d1.getGameList().includeGame(game);
        d1.displayDeveloper();
        assertEquals("Name: George Washington\nGeorge Washington's Games: testGame\n", outContent.toString());

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //2 games
        Game game2 = new Game();
        d1.getGameList().includeGame(game2);
        d1.displayDeveloper();

        assertEquals("Name: George Washington\nGeorge Washington's Games: testGame, testGame\n", outContent.toString());

    }
}
