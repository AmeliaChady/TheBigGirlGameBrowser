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
        gameList.includeGame("game 1");
        assertEquals(1, gameList.getGameCount());

        // Add another new game
        gameList.includeGame("game 2");
        assertEquals(2, gameList.getGameCount());
    }

    @Test
    public void removeGameTest() {
        GameList gameList = new GameList("My Game List");

        gameList.includeGame("game 1");
        gameList.includeGame("game 2");

        // Remove existing game
        String removedGame = gameList.removeGame("game 1");
        assertEquals(1, gameList.getGameCount());
        assertEquals("game 1", removedGame);

        // Remove non-existing game (invalid)
        gameList.removeGame("game 0");
        assertEquals(1, gameList.getGameCount());
    }

    @Test
    public void getGame() {
        GameList gameList = new GameList("My Game List");

        gameList.includeGame("game 1");
        gameList.includeGame("game 2");

        // Get existing game
        String foundGame = gameList.getGame("game 1");
        assertEquals("game 1", foundGame);

        // Get non-existing game (invalid)
        foundGame = gameList.getGame("game 0");
        assertEquals(null, foundGame);
    }

    @Test
    public void displayAllGamesTest(){

        //---------game creation---------
        String g1 = "";

        List<String> developer = new ArrayList<String>();
        String dev = "kerry";
        developer.add(dev);
        String g2 = "Best game";

        List<String> developers = new ArrayList<String>();
        String dev1 = "kerry anne";
        developers.add(dev1);
        String dev2 = "kelsey";
        developers.add(dev2);
        String g3 = "Cooking Mama";

        developers = new ArrayList<String>();
        dev1 = "kerry anne";
        developers.add(dev1);
        dev2 = "kelsey";
        developers.add(dev2);
        String dev3 = "grace t. dury";
        developers.add(dev3);
        String g4 = "Animal Crossing New Horizons";

        developer = new ArrayList<String>();
        dev1 = "kevin jonas";
        developer.add(dev1);
        String g5 = "camp rock 4";

        developer = new ArrayList<String>();
        dev1 = "bertha";
        developer.add(dev1);
        String g6 = "cutest dog <3";
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
        assertEquals("coolKidzList:\n\nBest game\nCooking Mama\nAnimal Crossing New Horizons\ncamp rock 4\ncutest dog <3\n",
                outContent.toString());



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
        assertEquals("Kerry Anne's List:\nAnimal Crossing New Horizons\n", outContent.toString());
        //TODO: finish test with assertEquals for above, as well as test for 0 and 1 game.
    }
}
