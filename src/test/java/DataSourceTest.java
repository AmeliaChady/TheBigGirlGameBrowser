import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataSourceTest {
    public static void dataSourceSaveGameTest(DataSource ds) throws DataSourceException{
        System.out.println("Warning: DataSource must be empty for correct testing");
        System.out.println("Warning: Used Combined View to verify");
        Game cooltestgame = new Game("testGame", "This is a test to save a game object", "Frank");

        // Saving a game
        try {
            ds.saveGame(cooltestgame);
            System.out.println("Warning: Requires Manual Check");
            System.out.println("Values Should Be: " +
                    "\n      title = 'testGame'" +
                    "\n  developer = 'Frank'" +
                    "\ndescription = 'This is a test to save a game object'" +
                    "\n     status = 'PENDING'");
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }

        // Resaving same game does not throw an error
        try {
            ds.saveGame(cooltestgame);
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }

        // Updating a saved game works - Description
        cooltestgame = new Game("Test-zx the Game","ahhhhhhhhh","Ted");
        ds.saveGame(cooltestgame);
        cooltestgame.changeDescription("cool robot game");
        ds.saveGame(cooltestgame);

        // Updating a saved game works - Status
        cooltestgame.changeStatus(Status.ACCEPTED);
        ds.saveGame(cooltestgame);

        System.out.println("Warning: Requires Manual Check");
        System.out.println("Values Should Be: " +
                "\n      title = 'Test-zx the Game'" +
                "\n  developer = 'Ted'" +
                "\ndescription = 'cool robot game'" +
                "\n     status = 'ACCEPTED'");

        // Handles Games with Multiple Developers

        cooltestgame = new Game("Toot Scooters",
                      "mario kart hack where all sounds are balloons popping",
                                "Ted");
        cooltestgame.addDeveloper("CorgiLover87");

        ds.saveGame(cooltestgame);
        System.out.println("Warning: Requires Manual Check");
        System.out.println("Values Should Be: " +
                "\n      title = 'Toot Scooters'" +
                "\n  developer = 'Ted', 'CorgiLover87'" +
                "\ndescription = 'mario kart hack where all sounds are balloons popping'" +
                "\n     status = 'PENDING'");

        // Can't give a null game
        assertThrows(IllegalArgumentException.class, () -> ds.saveGame(null));
    }

    public static void dataSourceSaveGameListTest(DataSource ds) throws DataSourceException{
        System.out.println("Warning: DataSource must be empty for correct testing");
        System.out.println("Warning: Used Combined View to verify");

        Game game1 = new Game("testGame1", "this Game is a Test game", "Bobby");
        ds.saveGame(game1);
        Game game2 = new Game("testGame2", "this Game is a Test game", "Bobby");
        ds.saveGame(game2);
        GameList gameList = new GameList("TestList");

        gameList.includeGame(game1.getTitle());
        gameList.includeGame(game2.getTitle()); // Now gameList has 2 games

        // Saving a gameList
        try {
            ds.saveGameList(gameList);
            System.out.println("Warning: Requires Manual Check");
            System.out.println("GameValues Should Be: " +
                    "\n      'TestList' | 'testGame1" +
                    "\n      'TestList' | 'testGame2");
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }

        // Resaving same gameList does not throw an error
        try {
            ds.saveGameList(gameList);
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }

        Game game3 = new Game("testGame3", "this Game is a Test game", "Bobby");
        ds.saveGame(game3);
        gameList.includeGame(game3.getTitle());

        // Updating Gamelist works
        try {
            ds.saveGameList(gameList);
            System.out.println("Warning: Requires Manual Check");
            System.out.println("GameValues Should Be: " +
                    "\n      'TestList' | 'testGame1" +
                    "\n      'TestList' | 'testGame2" +
                    "\n      'TestList' | 'testGame3");
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }
    }

    public static void dataSourceLoadGameTest(DataSource ds) throws DataSourceException{
        System.out.println("Note, there cannot be a game with the title 'LoadGameTest3', otherwise tests will break");

        // Basic
        // Adding Two Test Games
        Game g = new Game("LoadGameTest1", "description", "LGT_A");
        ds.saveGame(g);
        g = new Game("LoadGameTest2", "noitpircsed","LGT_B");
        g.addDeveloper("LGT_C");
        g.changeStatus(Status.ACCEPTED);
        ds.saveGame(g);

        // Can Find A Game
        g = ds.loadGame("LoadGameTest1");
        assertNotNull(g);
        assertEquals("description", g.getDescription());
        assertEquals("LGT_A", g.getDevelopers().get(0));
        assertEquals(Status.PENDING, g.getStatus());

        // Can Find A Game with Multiple Developers
        g = ds.loadGame("LoadGameTest2");
        assertNotNull(g);
        assertEquals("noitpircsed", g.getDescription());
        assertEquals("LGT_B", g.getDevelopers().get(0));
        assertEquals("LGT_C", g.getDevelopers().get(1));
        assertEquals(Status.ACCEPTED, g.getStatus());

        // Putting null gives null
        assertNull(ds.loadGame(null));

        // Putting bogus gives null
        assertNull(ds.loadGame("LoadGameTest3"));

        // Developer has game in gamelist
        g = ds.loadGame("LoadGameTest1");
        assertEquals(g, g.getDevelopers().get(0));

    }
}
