import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
        gameList.includeGame(new Game("game 1", new Developer("Mikey")));
        assertEquals(1, gameList.getGameCount());

        // Add another new game
        gameList.includeGame(new Game("game 2", new Developer("Mary")));
        assertEquals(2, gameList.getGameCount());
    }

    @Test
    public void removeGameTest() {
        GameList gameList = new GameList("My Game List");

        gameList.includeGame(new Game("game 1", new Developer("Barney")));
        gameList.includeGame(new Game("game 2", new Developer("Link")));

        // Remove existing game
        Game removedGame = gameList.removeGame("game 1");
        assertEquals(1, gameList.getGameCount());
        assertEquals("Game", removedGame.getClass().getName());
        assertEquals("game 1", removedGame.getTitle());

        // Remove non-existing game (invalid)
        gameList.removeGame("game 0");
        assertEquals(1, gameList.getGameCount());
    }

    @Test
    public void getGame() {
        GameList gameList = new GameList("My Game List");

        gameList.includeGame(new Game("game 1", new Developer("Roger")));
        gameList.includeGame(new Game("game 2", new Developer("Wilbur")));

        // Get existing game
        Game foundGame = gameList.getGame("game 1");
        assertEquals("Game", foundGame.getClass().getName());
        assertEquals("game 1", foundGame.getTitle());

        // Get non-existing game (invalid)
        foundGame = gameList.getGame("game 0");
        assertEquals(null, foundGame);
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
        assertEquals("coolKidzList:\n\nTitle: testGame\nDescription: This is a test to create a new game object\nDeveloper(s): None\nStatus: PENDING\n\nTitle: Best game\nDescription: This is the best game ever!\nDeveloper(s): kerry\nStatus: PENDING\n\nTitle: Cooking Mama\nDescription: No Description Given\nDeveloper(s): kerry anne, kelsey\nStatus: PENDING\n\nTitle: Animal Crossing New Horizons\nDescription: Live as the only human, sell seashells to survive, and be in constant debt.\nDeveloper(s): kerry anne, kelsey, grace t. dury\nStatus: PENDING\n\nTitle: camp rock 4\nDescription: kevin sells real estate now\nDeveloper(s): kevin jonas\nStatus: ACCEPTED\n\nTitle: cutest dog <3\nDescription: she is my dog. I hate her name but she's still cute\nDeveloper(s): bertha\nStatus: REJECTED\n\n", outContent.toString());


        //0 games
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GameList gameList1 = new GameList("Waldo's List");
        gameList1.displayAllGames();
        assertEquals(0, gameList1.getGameCount());
        assertEquals("Waldo's List:\n\nThis list is empty\n", outContent.toString());

        //1 game
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GameList gameList2 = new GameList("Kerry Anne's List");
        gameList2.includeGame(g4);
        gameList2.displayAllGames();
        assertEquals(1, gameList2.getGameCount());
        assertEquals("Kerry Anne's List:\n\nTitle: Animal Crossing New Horizons\nDescription: Live as the only human, sell seashells to survive, and be in constant debt.\nDeveloper(s): kerry anne, kelsey, grace t. dury\nStatus: PENDING\n\n", outContent.toString());
    }

    @Test
    public void displayListNameAndGameTitlesTest(){
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

        gameList.displayListNameAndGameTitles();


        assertEquals(6, gameList.getGameCount());
        assertEquals("coolKidzList: testGame, Best game, Cooking Mama, Animal Crossing New Horizons, camp rock 4, cutest dog <3\n", outContent.toString());

        //0 games
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GameList gameList1 = new GameList("Waldo's List");
        gameList1.displayListNameAndGameTitles();
        assertEquals(0, gameList1.getGameCount());
        assertEquals("Waldo's List: This list is empty\n", outContent.toString());

        //1 game
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GameList gameList2 = new GameList("Kerry Anne's List");
        gameList2.includeGame(g4);
        gameList2.displayListNameAndGameTitles();
        assertEquals(1, gameList2.getGameCount());
        assertEquals("Kerry Anne's List: Animal Crossing New Horizons\n", outContent.toString());

    }

    @Test
    public void displayGamesGivenStatusTest(){
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

        //pending
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        gameList.displayGamesGivenStatus(Status.PENDING);

        //accepted
        assertEquals("coolKidzList(PENDING):\n\nTitle: testGame\nDescription: This is a test to create a new game object\nDeveloper(s): None\nStatus: PENDING\n\nTitle: Best game\nDescription: This is the best game ever!\nDeveloper(s): kerry\nStatus: PENDING\n\nTitle: Cooking Mama\nDescription: No Description Given\nDeveloper(s): kerry anne, kelsey\nStatus: PENDING\n\nTitle: Animal Crossing New Horizons\nDescription: Live as the only human, sell seashells to survive, and be in constant debt.\nDeveloper(s): kerry anne, kelsey, grace t. dury\nStatus: PENDING\n\n", outContent.toString());
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        gameList.displayGamesGivenStatus(Status.ACCEPTED);
        assertEquals("coolKidzList(ACCEPTED):\n\nTitle: camp rock 4\nDescription: kevin sells real estate now\nDeveloper(s): kevin jonas\nStatus: ACCEPTED\n\n", outContent.toString());

        //rejected
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        gameList.displayGamesGivenStatus(Status.REJECTED);
        assertEquals("coolKidzList(REJECTED):\n\nTitle: cutest dog <3\nDescription: she is my dog. I hate her name but she's still cute\nDeveloper(s): bertha\nStatus: REJECTED\n\n", outContent.toString());
    }

    @Test
    public void displayGameTitlesNumberedListTest(){
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

        gameList.displayGameTitlesNumberedList();
        assertEquals("1. testGame\n2. Best game\n3. Cooking Mama\n4. Animal Crossing New Horizons\n5. camp rock 4\n6. cutest dog <3\n", outContent.toString());

        //0 games
        GameList gameList2 = new GameList("dumbKidzList");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        gameList2.displayGameTitlesNumberedList();
        assertEquals("There are no games to display", outContent.toString());

        //1 game
        GameList gameList3 = new GameList("mediocreKidzList");
        gameList3.includeGame(g5);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        gameList3.displayGameTitlesNumberedList();
        assertEquals("1. camp rock 4\n", outContent.toString());
    }
}
