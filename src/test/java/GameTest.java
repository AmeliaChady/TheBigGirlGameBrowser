import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void gameConstructorTest(){
        Game game = new Game();

        assertEquals("testGame", game.getTitle());
        assertEquals("This is a test to create a new game object", game.getDescription());
        assertNotNull(game.getDevelopers());

        Game newGame = new Game("daBears", new Developer("Sheila"));

        assertEquals("daBears", newGame.getTitle());
        assertEquals("No Description Given", newGame.getDescription());
        assertNotNull(newGame.getDevelopers());

        Game describedGame = new Game("badTitle", "Bad Description", new Developer("Kelly"));
        assertEquals("badTitle", describedGame.getTitle());
        assertEquals("Bad Description", describedGame.getDescription());
        assertNotNull(describedGame.getDevelopers());
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

    @Test
    public void enumTest(){
        Game game = new Game("title", "description", new Developer("Jimmy"), Status.ACCEPTED);
        assertEquals(Status.ACCEPTED, game.getStatus()); //checks that the fullfull constructor works

        game.changeStatus(Status.PENDING);
        assertEquals(Status.PENDING, game.getStatus()); //checks assignment

        game.changeStatus(Status.REJECTED);
        assertEquals(Status.REJECTED, game.getStatus());//checks assignment

        game = new Game("title", "description", new Developer("Carter"));
        assertEquals(Status.PENDING, game.getStatus()); //Checks other constructors default assignment
    }

    @Test
    public void addDeveloperTest(){
        List<Developer> developers = new ArrayList<Developer>();
        developers.add(new Developer("Fluffy"));
        developers.add(new Developer("Nut"));
        developers.add(new Developer("Face"));

        Game multiDevGame = new Game("FlufferNutter",
                "The adventures of a cat and their Penutbutter", developers, Status.ACCEPTED);
        assertEquals(developers, multiDevGame.getDevelopers());

        Developer thatGuy = new Developer("Pual");

        multiDevGame.addDeveloper(thatGuy);
        developers.add(thatGuy);

        assertEquals(developers, multiDevGame.getDevelopers());
    }

    @Test
    public void displayGameTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //0 developers, pending status
        Game g1 = new Game();
        g1.displayGame();
        assertEquals("Title: testGame\nDescription: This is a test to create a new game object\nDeveloper(s): None\nStatus: PENDING\n", outContent.toString());


        //1 developer, pending status
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        List<Developer> developer = new ArrayList<>();
        Developer dev = new Developer("kerry");
        developer.add(dev);
        Game g2 = new Game("Best game", "This is the best game ever!", developer, Status.PENDING);
        g2.displayGame();
        assertEquals("Title: Best game\nDescription: This is the best game ever!\nDeveloper(s): kerry\nStatus: PENDING\n", outContent2.toString());

        //2 developers, pending status, no description
        ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent3));
        List<Developer> developers = new ArrayList<>();
        Developer dev1 = new Developer("kerry anne");
        developers.add(dev1);
        Developer dev2 = new Developer("kelsey");
        developers.add(dev2);
        Game g3 = new Game("Cooking Mama",  developers);
        g3.displayGame();
        assertEquals("Title: Cooking Mama\nDescription: No Description Given\nDeveloper(s): kerry anne, kelsey\nStatus: PENDING\n", outContent3.toString());
        outContent = new ByteArrayOutputStream();

        //3 developers, pending status
        ByteArrayOutputStream outContent4 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent4));
        developers = new ArrayList<>();
        dev1 = new Developer("kerry anne");
        developers.add(dev1);
        dev2 = new Developer("kelsey");
        developers.add(dev2);
        Developer dev3 = new Developer("grace t. dury");
        developers.add(dev3);
        Game g4 = new Game("Animal Crossing New Horizons", "Live as the only human, sell seashells to survive, and be in constant debt.", developers, Status.PENDING);
        g4.displayGame();
        assertEquals("Title: Animal Crossing New Horizons\nDescription: Live as the only human, sell seashells to survive, and be in constant debt.\nDeveloper(s): kerry anne, kelsey, grace t. dury\nStatus: PENDING\n", outContent4.toString());
        outContent = new ByteArrayOutputStream();

        //1 developer, accepted
        ByteArrayOutputStream outContent5 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent5));
        developer = new ArrayList<>();
        dev1 = new Developer("kevin jonas");
        developer.add(dev1);
        Game g5 = new Game("camp rock 4", "kevin sells real estate now", developer, Status.ACCEPTED);
        g5.displayGame();
        assertEquals("Title: camp rock 4\nDescription: kevin sells real estate now\nDeveloper(s): kevin jonas\nStatus: ACCEPTED\n", outContent5.toString());
        outContent = new ByteArrayOutputStream();

        //1 developer, rejected
        ByteArrayOutputStream outContent6 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent6));
        developer = new ArrayList<>();
        dev1 = new Developer("bertha");
        developer.add(dev1);
        Game g6 = new Game("cutest dog <3", "she is my dog. I hate her name but she's still cute", developer, Status.REJECTED);
        g6.displayGame();
        assertEquals("Title: cutest dog <3\nDescription: she is my dog. I hate her name but she's still cute\nDeveloper(s): bertha\nStatus: REJECTED\n", outContent6.toString());

    }

    @Test
    public void displayAllGamesTest(){

        //---------game creation---------
        Game g1 = new Game();

        List<Developer> developer = new ArrayList<>();
        Developer dev = new Developer("kerry");
        developer.add(dev);
        Game g2 = new Game("Best game", "This is the best game ever!", developer, Status.PENDING);

        List<Developer> developers = new ArrayList<>();
        Developer dev1 = new Developer("kerry anne");
        developers.add(dev1);
        Developer dev2 = new Developer("kelsey");
        developers.add(dev2);
        Game g3 = new Game("Cooking Mama",  developers);

        developers = new ArrayList<>();
        dev1 = new Developer("kerry anne");
        developers.add(dev1);
        dev2 = new Developer("kelsey");
        developers.add(dev2);
        Developer dev3 = new Developer("grace t. dury");
        developers.add(dev3);
        Game g4 = new Game("Animal Crossing New Horizons", "Live as the only human, sell seashells to survive, and be in constant debt.", developers, Status.PENDING);

        developer = new ArrayList<>();
        dev1 = new Developer("kevin jonas");
        developer.add(dev1);
        Game g5 = new Game("camp rock 4", "kevin sells real estate now", developer, Status.ACCEPTED);

        developer = new ArrayList<>();
        dev1 = new Developer("bertha");
        developer.add(dev1);
        Game g6 = new Game("cutest dog <3", "she is my dog. I hate her name but she's still cute", developer, Status.REJECTED);
        //--------- end of game creation---------

        //6 games
        GameList gameList = new GameList("coolKidzList");
        gameList.includeGame(g1);
        gameList.includeGame(g2);
        gameList.includeGame(g3);
        gameList.includeGame(g4);
        gameList.includeGame(g5);
        gameList.includeGame(g6);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        gameList.displayAllGames();
        assertEquals(6, gameList.getGameCount());
        assertEquals("coolKidzList:\n\nTitle: testGame\nDescription: This is a test to create a new game object\nDeveloper(s): None\nStatus: PENDING\n\nTitle: Best game\nDescription: This is the best game ever!\nDeveloper(s): kerry\nStatus: PENDING\n\nTitle: Cooking Mama\nDescription: No Description Given\nDeveloper(s): kerry anne, kelsey\nStatus: PENDING\n\nTitle: Animal Crossing New Horizons\nDescription: Live as the only human, sell seashells to survive, and be in constant debt.\nDeveloper(s): kerry anne, kelsey, grace t. dury\nStatus: PENDING\n\nTitle: camp rock 4\nDescription: kevin sells real estate now\nDeveloper(s): kevin jonas\nStatus: ACCEPTED\n\nTitle: cutest dog <3\nDescription: she is my dog. I hate her name but she's still cute\nDeveloper(s): bertha\nStatus: REJECTED\n", outContent.toString());


        //0 games
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GameList gameList1 = new GameList("Waldo's List");
        gameList1.displayAllGames();
        assertEquals(0, gameList1.getGameCount());
        assertEquals("Waldo's list:\nThis list is empty", outContent.toString());

        //1 game
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GameList gameList2 = new GameList("Kerry Anne's List");
        gameList.includeGame(g4);
        gameList2.displayAllGames();
        assertEquals(1, gameList2.getGameCount());
        assertEquals("Kerry Anne's list:\n\nTitle: Animal Crossing New Horizons\nDescription: Live as the only human, sell seashells to survive, and be in constant debt.\nDeveloper(s): kerry anne, kelsey, grace t. dury\n", outContent.toString());
        //TODO: finish test with assertEquals for above, as well as test for 0 and 1 game.
    }
}
