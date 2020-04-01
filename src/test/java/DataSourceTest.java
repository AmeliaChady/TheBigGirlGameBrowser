import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataSourceTest {
    public static void dataSourceSaveGameTest(DataSource ds) throws DataSourceException{
        System.out.println("Warning: DataSource must be empty for correct testing");
        System.out.println("Warning: Used Combined View to verify");
        Game cooltestgame = new Game("testGame", "This is a test to save a game object", new Developer("Frank"));



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
        cooltestgame = new Game("Test-zx the Game","ahhhhhhhhh", new Developer("Ted"));
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
                                new Developer("Ted"));
        cooltestgame.addDeveloper(new Developer("CorgiLover87"));

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

    public static void dataSourceLoadGameTest(DataSource ds) throws DataSourceException{
        System.out.println("Note, there cannot be a game with the title 'LoadGameTest3', otherwise tests will break");

        // Basic
        // Adding Two Test Games
        Game g = new Game("LoadGameTest1", "description", new Developer("LGT_A"));
        ds.saveGame(g);
        g = new Game("LoadGameTest2", "noitpircsed",new Developer("LGT_B"));
        g.addDeveloper(new Developer("LGT_C"));
        g.changeStatus(Status.ACCEPTED);
        ds.saveGame(g);

        // Can Find A Game
        g = ds.loadGame("LoadGameTest1");
        assertNotNull(g);
        assertEquals("description", g.getDescription());
        assertEquals("LGT_A", g.getDevelopers().get(0).getName());
        assertEquals(Status.PENDING, g.getStatus());

        // Can Find A Game with Multiple Developers
        g = ds.loadGame("LoadGameTest2");
        assertNotNull(g);
        assertEquals("noitpircsed", g.getDescription());
        assertEquals("LGT_B", g.getDevelopers().get(0).getName());
        assertEquals("LGT_C", g.getDevelopers().get(1).getName());
        assertEquals(Status.ACCEPTED, g.getStatus());

        // Putting null gives null
        assertNull(ds.loadGame(null));

        // Putting bogus gives null
        assertNull(ds.loadGame("LoadGameTest3"));
    }

    public static void dataSourceSaveGameListTest(DataSource ds) throws DataSourceException{
        System.out.println("Warning: DataSource must be empty for correct testing");
        System.out.println("Warning: Used Combined View to verify");

        Developer bobby = new Developer("Bobby");
        Game game1 = new Game("testGame1", "this Game is a Test game", bobby);
        Game game2 = new Game("testGame2", "this Game is a Test game", bobby);

        GameList gameList = new GameList("TestList");
        gameList.includeGame(game1);
        gameList.includeGame(game2); // Now gameList has 2 games

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

        Game game3 = new Game("testGame3", "this Game is a Test game", bobby);
        ds.saveGame(game3);
        gameList.includeGame(game3);

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

    public static void dataSourceLoadGameListTest(DataSource ds) throws DataSourceException{
        System.out.println("Note, there cannot be a GameList called 'BogusList', otherwise tests will break");

        Game g1 = new Game("Crossing Mammals", new Developer("HoarderOfBells"));
        Game g2 = new Game("Confusion Level Increasing", new Developer("Amelia Chady"));

        ds.saveGame(g1);
        ds.saveGame(g2);

        GameList gl1 = new GameList("Games I Have Time To Play");
        ds.saveGameList(gl1);

        GameList gl2 = new GameList("Everyone Else is Playing");
        gl2.includeGame(g1);
        ds.saveGameList(gl2);

        GameList gl3 = new GameList("The Kerry Anne Experience");
        gl3.includeGame(g1);
        gl3.includeGame(g2);
        ds.saveGameList(gl3);

        // Load Game List with No Games
        assertEquals(0, ds.loadGameList("Games I Have Time To Play").getGameCount());

        // Load Game List with One Game
        GameList loaded = ds.loadGameList("Everyone Else is Playing");
        assertEquals(1, loaded.getGameCount());

        // Load Game List with Two Games
        assertEquals(2, ds.loadGameList("The Kerry Anne Experience").getGameCount());

        // Games Hold Their Names
        assertEquals("Crossing Mammals", loaded.getGames().get(0).getTitle());

        // Games Hold Developers (Using loadGame so only need simple connection test)
        assertEquals("HoarderOfBells", loaded.getGames().get(0).getDeveloperNames().get(0));

        // Sending Null Returns Null
        assertNull(ds.loadGameList(null));

        // Sending Bogus Returns Null
        assertNull(ds.loadGameList("BogusList"));
    }

    public static void dataSourceSaveDevelopersTest(DataSource ds) throws DataSourceException{
        System.out.println("Warning: DataSource must be empty for correct testing");
        System.out.println("Warning: Used Combined View to verify");

        Developer bobby = new Developer("Bobby");

        Game game1 = new Game("game1", bobby);
        Game game2 = new Game("game2", bobby);
        Game game3 = new Game("game3", bobby);

        //ds.saveGameList(bobby.getGameList());

        // At this point db should be aware of bob and have a relationship between bob and these games

        Developer gef = new Developer("jim");
        game1.addDeveloper(gef);

        game3.addDeveloper(gef);

        gef.getGameList().includeGame(game1);
        gef.getGameList().includeGame(game3);
        ds.saveDeveloper(gef);
        ds.saveDeveloper(bobby);
    }

    public static void dataSourceLoadDevelopersTest(DataSource ds) throws DataSourceException{
        System.out.println("Note, there cannot be a GameList called 'LoadDeveloperBogusTest', otherwise tests will break");
        Developer save = new Developer("LoadDeveloperTest");
        save.getGameList().includeGame(new Game("LoadDeveloperTestGame", "aa", save));
        ds.saveDeveloper(save);

        // Load Works
        Developer d = ds.loadDeveloper("LoadDeveloperTest");
        assertNotNull(d);

        // Confirm GameList Connection
        assertEquals(1, d.getGameList().getGameCount());

        // Null Gives Null
        assertNull(ds.loadDeveloper(null));

        // Bogus Gives Null
        assertNull(ds.loadDeveloper("LoadDeveloperBogusTest"));
    }

    public static void dataSourceLoadDeveloperListTest(DataSource ds) throws DataSourceException {
        // create and save devs
        for (int i = 0; i < 5; i++)
            ds.saveDeveloper( new Developer("test dev "+i) );
        // load them from db
        List<Developer> developers = ds.loadDeveloperList();

        // Load Works
        assertNotNull(developers);

        // All developers were loaded
        for (int i = 0; i < 5; i++){
            boolean found = false;
            Iterator<Developer> devs = developers.iterator();
            while (devs.hasNext() && !found){
                Developer curr = devs.next();
                if(curr.getName().equals("test dev "+i))
                    found = true;
            }
            assertTrue(found);

        }

    }

}
