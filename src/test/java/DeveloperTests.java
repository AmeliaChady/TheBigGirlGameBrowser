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
        Developer d1 = new Developer("Milk Dad");
        Developer d2 = new Developer("The Sunshine Gang");
        Developer d3 = new Developer("I just want to go to sleep");
        Developer d4 = new Developer("The Korona Krew");
        Developer d5 = new Developer("Brett Michaels");
        Developer d6 = new Developer("six");
        Developer d7 = new Developer("seven");
        Developer d8 = new Developer("eight");


        GameList gameListTest = new GameList("all games");

        //add games to array list
        d6.submitGame("Billy Bob Goes to The Moon", gameListTest);
        d6.submitGame("Why are there like four different sizes of gatorade", gameListTest);
        d7.submitGame("Fight Your Dad Simulator", gameListTest);
        d8.submitGame("beep in traffic", gameListTest);
        d4.submitGame("jeff bezos takes over the world", gameListTest);


        //all games in gameList, none in developer list
        assertEquals(5, gameListTest.getGameCount());
        assertEquals(2, d6.getGameList().getGameCount());

        d6.submitGame("Billy Bob Goes to The Moon", gameListTest);

        //games list with one added in games list and in d6's list
        assertEquals(5, gameListTest.getGameCount());
        assertEquals(2, d6.getGameList().getGameCount());

        //add another game with new developer
        d7.submitGame("title6", gameListTest);

        assertEquals(6, gameListTest.getGameCount());
        assertEquals(2, d7.getGameList().getGameCount());
        //developer 6 list should remain unaffected
        assertEquals(2, d6.getGameList().getGameCount());

        //add another game with new developer

        d8.submitGame("title7", gameListTest);

        assertEquals(7, gameListTest.getGameCount());
        assertEquals(2, d8.getGameList().getGameCount());
        //other developer lists should remain unaffected
        assertEquals(2, d6.getGameList().getGameCount());
        assertEquals(2, d7.getGameList().getGameCount());


        //add another game to an existing developers list
        d6.submitGame("title9", gameListTest);

        assertEquals(8, gameListTest.getGameCount());
        assertEquals(3, d6.getGameList().getGameCount());
    }

    //TODO: move to gameBrowser
//    public void displayDeveloperTest(){
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        //0 games
//        Developer d1 = new Developer("George Washington");
//        d1.displayDeveloper();
//        assertEquals("Name: George Washington\nGeorge Washington's Games: This list is empty\n", outContent.toString());
//
//        outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        //1 game
//        Game game = new Game();
//        d1.getGameList().includeGame(game);
//        d1.displayDeveloper();
//        assertEquals("Name: George Washington\nGeorge Washington's Games: testGame\n", outContent.toString());
//
//        outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        //2 games
//        Game game2 = new Game();
//        d1.getGameList().includeGame(game2);
//        d1.displayDeveloper();
//
//        assertEquals("Name: George Washington\nGeorge Washington's Games: testGame, testGame\n", outContent.toString());
//
//    }
}
