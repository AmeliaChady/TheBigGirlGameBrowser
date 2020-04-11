import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    public static String CORRECT_PATH = "testing.db";

    @Test
    public void gameConstructorTest(){
        SQLiteSource source = new SQLiteSource(CORRECT_PATH);
        Game game = new Game();

        assertEquals("testGame", game.getTitle());
        assertEquals("This is a test to create a new game object", game.getDescription());
        assertNotNull(game.getDevelopers());

        Game newGame = new Game("daBears", "Sheila", source);

        assertEquals("daBears", newGame.getTitle());
        assertEquals("No Description Given", newGame.getDescription());
        assertNotNull(newGame.getDevelopers());

        Game describedGame = new Game("badTitle", "Bad Description", "Kelly", source);
        assertEquals("badTitle", describedGame.getTitle());
        assertEquals("Bad Description", describedGame.getDescription());
        assertNotNull(describedGame.getDevelopers());

        //TODO: Check that source has also been updated
    }

    @Test
    public void changeDescriptionTest(){
        SQLiteSource source = new SQLiteSource(CORRECT_PATH);

        Game game = new Game("daBears", "Wendell", source);

        assertEquals("No Description Given", game.getDescription());

        String aDescription = "Hey Fans! Checkout this awesome 'New Game' that I just released on the Big Girls Game Browser!!!";

        game.changeDescription(aDescription);

        assertEquals(aDescription, game.getDescription());

        //TODO: Check that source has also been updated
    }

    @Test
    public void changeTitleTest(){
        SQLiteSource source = new SQLiteSource(CORRECT_PATH);

        Game game = new Game("badTitle", "Bad Description", "Winston", source);

        assertEquals("badTitle", game.getTitle());

        game.changeTitle("goodTitle");

        assertEquals("goodTitle", game.getTitle());

        //TODO: Check that source has also been updated
        //TODO: game called "badTitle" is gone from DB and now there is a game called "goodTitle"
    }

    @Test
    public void enumTest(){
        SQLiteSource source = new SQLiteSource(CORRECT_PATH);

        Game game = new Game("title", "description", "Jimmy", Status.ACCEPTED, source);

        assertEquals(Status.ACCEPTED, game.getStatus()); //checks that the fullfull constructor works

        game.changeStatus(Status.PENDING);
        assertEquals(Status.PENDING, game.getStatus()); //checks assignment

        game.changeStatus(Status.REJECTED);
        assertEquals(Status.REJECTED, game.getStatus());//checks assignment

        game = new Game("title", "description", "Carter", source);

        assertEquals(Status.PENDING, game.getStatus()); //Checks other constructors default assignment

        game.changeStatus(Status.LIMBO);
        assertEquals(Status.LIMBO, game.getStatus());//checks assignment

        //TODO: Check that source has also been updated
    }

    @Test
    public void addDeveloperTest(){
        SQLiteSource source = new SQLiteSource(CORRECT_PATH);

        List<String> developers = new ArrayList<>();
        developers.add("Fluffy");
        developers.add("Nut");
        developers.add("Face");

        Game multiDevGame = new Game("FlufferNutter",
                "The adventures of a cat and their Penutbutter", developers, Status.ACCEPTED, source);
        assertEquals(developers, multiDevGame.getDevelopers());

        String thatGuy = "Pual";

        multiDevGame.addDeveloper(thatGuy, source);
        developers.add(thatGuy);

        assertEquals(developers, multiDevGame.getDevelopers());

        //TODO: Check that source has also been updated
    }

    @Test
    public void displayGameTest(){
        SQLiteSource source = new SQLiteSource(CORRECT_PATH);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //0 developers, pending status
        Game g1 = new Game();
        g1.displayGame();
        assertEquals("Title: testGame\nDescription: This is a test to create a new game object\nDeveloper(s): None\nStatus: PENDING\n\n", outContent.toString());


        //1 developer, pending status
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        Game g2 = new Game("Best game", "This is the best game ever!", "kerry", Status.PENDING, source);
        g2.displayGame();
        assertEquals("Title: Best game\nDescription: This is the best game ever!\nDeveloper(s): kerry\nStatus: PENDING\n\n", outContent2.toString());

        //2 developers, pending status, no description
        ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent3));
        List<String> developers = new ArrayList<>();
        Developer dev1 = new Developer("kerry anne");
        developers.add(dev1.getName());
        Developer dev2 = new Developer("kelsey");
        developers.add(dev2.getName());
        Game g3 = new Game("Cooking Mama",  developers, source);
        g3.displayGame();
        assertEquals("Title: Cooking Mama\nDescription: No Description Given\nDeveloper(s): kerry anne, kelsey\nStatus: PENDING\n\n", outContent3.toString());

        //3 developers, pending status
        ByteArrayOutputStream outContent4 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent4));
        developers = new ArrayList<>();
        dev1 = new Developer("kerry anne");
        developers.add(dev1.getName());
        dev2 = new Developer("kelsey");
        developers.add(dev2.getName());
        Developer dev3 = new Developer("grace t. dury");
        developers.add(dev3.getName());
        Game g4 = new Game("Animal Crossing New Horizons", "Live as the only human, sell seashells to survive, and be in constant debt.", developers, Status.PENDING, source);
        g4.displayGame();
        assertEquals("Title: Animal Crossing New Horizons\nDescription: Live as the only human, sell seashells to survive, and be in constant debt.\nDeveloper(s): kerry anne, kelsey, grace t. dury\nStatus: PENDING\n\n", outContent4.toString());

        //1 developer, accepted
        ByteArrayOutputStream outContent5 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent5));
        List<String> developer = new ArrayList<>();
        dev1 = new Developer("kevin jonas");
        developer.add(dev1.getName());
        Game g5 = new Game("camp rock 4", "kevin sells real estate now", developer, Status.ACCEPTED, source);
        g5.displayGame();
        assertEquals("Title: camp rock 4\nDescription: kevin sells real estate now\nDeveloper(s): kevin jonas\nStatus: ACCEPTED\n\n", outContent5.toString());

        //1 developer, rejected
        ByteArrayOutputStream outContent6 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent6));
        developer = new ArrayList<>();
        dev1 = new Developer("bertha");
        developer.add(dev1.getName());
        Game g6 = new Game("cutest dog <3", "she is my dog. I hate her name but she's still cute", developer, Status.REJECTED, source);
        g6.displayGame();
        assertEquals("Title: cutest dog <3\nDescription: she is my dog. I hate her name but she's still cute\nDeveloper(s): bertha\nStatus: REJECTED\n\n", outContent6.toString());

    }


}
