import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameBrowserTest {

    //rm empty.db && sqlite3 empty.db < src/DDL.sql
    @Test
    public void constructorTest() throws DataSourceException {
        GameBrowser gameBrowser;
        Game testGame;
        Developer testDev;

        System.out.println("!!!!---DB Must Be Empty BEFORE TEST---!!!");

        //-----------load devs and games to a 'Master Game List'-----------------
        //
        SQLiteSource testDataSource = new SQLiteSource("src/databases/empty.db");
        GameList testGameList = new GameList("Master Game List");

        int gameCount = 9;

        for (int i = 1; i < gameCount+1; i++) {
            testDev = new Developer("dev "+i, i);
            testDataSource.saveDeveloper(testDev);
            testGame = new Game("game "+i, testDev.getName());
            testDataSource.saveGame(testGame);
            testGameList.includeGame(testGame.getTitle());
        }
        try {
            testDataSource.saveGameList(testGameList);
            testDataSource.close();
            testDataSource = null;
        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
        //--------------------------------------------------

        // Empty constructor use (invalid)
        try {
            gameBrowser = new GameBrowser();
            fail("Using empty constructor should fail test.");
        } catch(Exception e) {
            assertEquals("java.lang.Exception", e.getClass().getName());
            assertEquals( "Invalid constructor. Supply a data source file.", e.getMessage());
        }

        // Default constructor with empty non-existent file path (invalid)
        assertThrows(IllegalArgumentException.class, () -> new GameBrowser(""));

        // Default constructor with non-existent file path (invalid)
        assertThrows(IllegalArgumentException.class, () -> new GameBrowser("../some_file"));

        // Default constructor with existing file path
        try {
            gameBrowser = new GameBrowser("src/databases/empty.db");
            // Game list was loaded and length of list is as expected
            int expectedGameCount = gameCount;
            assertNotNull(gameBrowser.getGameList());
            assertEquals(expectedGameCount, gameBrowser.getGameList().getGameCount());

            // check that expected games from the master list were loaded
            int i = 0;
            GameList masterGameList = gameBrowser.getGameList();
            String expectedGameTitle;
            while (i < masterGameList.getGameCount()) {
                expectedGameTitle = "game "+(i+1);
                assertEquals(expectedGameTitle, masterGameList.getGames().get(i));
                i++;
            }

            // Developer list was loaded and length of list is as expected
            int expectedDevCount = gameCount;
            assertNotNull(gameBrowser.getDevelopers());
            assertEquals(expectedDevCount, gameBrowser.getDevelopers().size());
            // check that expected devs were loaded

            i = 0;
            List<String> developers = gameBrowser.getDevelopers();
            String expectedDevName;
            while (i < developers.size()) {
                expectedDevName = "dev "+(i+1);
                assertEquals(expectedDevName, developers.get(i));
                i++;
            }

            gameBrowser.close();
            // TODO check administrators (once admins can be loaded from db)
        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void addGameTest() throws IOException{
        try {
            SQLiteSource.RunSQL("src/databases/testing.db","src/scripts/DDL.sql");
            SQLiteSource.RunSQL("src/databases/testing.db","src/scripts/GameBrowserAddGame.sql");
            GameBrowser gb = new GameBrowser("src/databases/testing.db");

            int baseNumber = gb.getGameList().getGameCount();

            //add game
            Developer dev = new Developer("Anita", 1);
            Game gameToAdd = new Game("Candy Crush","My mom plays a lot of candy crush", dev.getName(), Status.PENDING);
            gb.addGame(gameToAdd);
            assertEquals(baseNumber+1, gb.getGameList().getGameCount());
            assertEquals("Candy Crush", gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1));
            //assertEquals("My mom plays a lot of candy crush", gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getDescription());
            //assertEquals(dev, gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getDevelopers().get(0));
            //assertEquals(Status.PENDING, gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getStatus());

            //pass game properties
            Developer dev2 = new Developer("Robert", 2);
            List<String> devList = new ArrayList<String>();
            devList.add(dev2.getName());
            gb.addGame("Clash of clans", "My dad plays a lot of clash of clans", devList, Status.PENDING);

            assertEquals(baseNumber+2, gb.getGameList().getGameCount());
            assertEquals("Clash of clans", gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1));
            //assertEquals("My dad plays a lot of clash of clans", gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getDescription());
            //assertEquals(devList.get(0), gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getDevelopers().get(0));
            //assertEquals(Status.PENDING, gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getStatus());


            gb.close();

        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void removeGameTest() throws IOException{
        try {
            SQLiteSource.RunSQL("src/databases/testing.db","src/scripts/DDL.sql");
            GameBrowser gb = new GameBrowser("src/databases/testing.db");
            gb.addDeveloper(new Developer("Something",1 ));

            List<String> devs = new LinkedList<>();
            devs.add("Something");

            gb.getGameList().removeGame("Cows V. Aliens");
            gb.getGameList().removeGame("game 3");

            gb.addGame("Cows V. Aliens","", devs, Status.REJECTED);
            gb.addGame("game 3","", devs, Status.REJECTED);

            int baseNumber = gb.getGameList().getGameCount();

            //non existent
            Game g1 = gb.removeGame("gcfhvjb");
            assertEquals(baseNumber, gb.getGameList().getGames().size());
            assertNull(g1);

            //existing game
            Game g2 = gb.removeGame("Cows V. Aliens");
            assertEquals(baseNumber-1, gb.getGameList().getGameCount());
            assertEquals("Cows V. Aliens", g2.getTitle());

            //non existent now, just removed
            Game g3 = gb.removeGame("Cows V. Aliens");
            assertEquals(baseNumber-1, gb.getGameList().getGameCount());
            assertEquals("Cows V. Aliens", g3.getTitle());

            //existing game
            Game g4 = gb.removeGame("game 3");
            assertEquals(baseNumber-2, gb.getGameList().getGameCount());
            assertEquals("game 3", g4.getTitle());

            //non existent now, removed
            Game g5 = gb.removeGame("game 3");
            assertEquals(baseNumber-2, gb.getGameList().getGameCount());
            assertNotNull(g5);

            gb.close();

        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void addDeveloperTest() throws IOException {
        try {
            SQLiteSource.RunSQL("src/databases/testing.db","src/scripts/DDL.sql");
            GameBrowser gameBrowser = new GameBrowser("src/databases/testing.db");

            int baseNumber = gameBrowser.getDevelopers().size();
            // create a new developer
            Developer dev = new Developer("dev1", 1);
            gameBrowser.addDeveloper(dev);
            assertEquals(baseNumber+1, gameBrowser.getDevelopers().size());
            boolean contains = false;
            Iterator<String> devs = gameBrowser.getDevelopers().iterator();
            while (devs.hasNext() && !contains){
                String d = devs.next();
                if(d.equals("dev1"))
                    contains = true;
            }
            assertTrue(contains);


            // create another
            dev = new Developer("dev2",2);
            gameBrowser.addDeveloper(dev);
            assertEquals(baseNumber+2, gameBrowser.getDevelopers().size());
            contains = false;
            devs = gameBrowser.getDevelopers().iterator();
            while (devs.hasNext() && !contains){
                String d = devs.next();
                if(d.equals("dev2"))
                    contains = true;
            }
            assertTrue(contains);

            System.out.println("Visual Check");
            for(String developer : gameBrowser.getDevelopers()){
                //developer.displayDeveloper();
            }

            gameBrowser.close();

        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void removeDeveloperTest() throws IOException{ // assumes a passing addDeveloperTest
        try {
            SQLiteSource.RunSQL("src/databases/testing.db","src/scripts/DDL.sql");
            GameBrowser gb = new GameBrowser("src/databases/testing.db");

            gb.removeDeveloper("dev1");
            gb.removeDeveloper("dev2");
            gb.removeDeveloper("dev1");
            gb.removeDeveloper("dev2");

            int baseNumber = gb.getDevelopers().size();

            System.out.println(gb.getDevelopers());
            // remove one developer from list of only one dev
            Developer dev = new Developer("dev1", 1);
            gb.addDeveloper(dev);

            assertEquals("dev1", gb.removeDeveloper("dev1"));
            System.out.println(gb.getDevelopers());
            assertEquals(baseNumber, gb.getDevelopers().size());

            // remove one developer from dev list > 1
            gb.addDeveloper(dev);
            gb.addDeveloper(new Developer("dev2", 2));
            assertEquals("dev2", gb.removeDeveloper("dev2"));
            assertEquals(baseNumber+1, gb.getDevelopers().size());

            // remove non-existent developer
            assertNull(gb.removeDeveloper("dev2"));
            assertEquals(baseNumber+1, gb.getDevelopers().size());

            gb.close();
        } catch(DataSourceException dse) {
            fail(dse.getMessage());
            return;
        }
    }

    @Test
    public void saveTest() throws IOException{
        try {
            SQLiteSource.RunSQL("src/databases/testing.db","src/scripts/DDL.sql");
            GameBrowser gameBrowser = new GameBrowser("src/databases/testing.db");
            Developer rob =  new Developer("Rob", 1);
            gameBrowser.addDeveloper(rob);

            Game game = new Game("robsGame", "HEY LOOK IM IN A DataBase", rob.getName());

            gameBrowser.addGame(game);

            // takes an already existing game and includes it into rob's gameList
            rob.getGameList().includeGame(gameBrowser.getGameList().getGame("game 1"));


            // Setup for below
            if (gameBrowser.getGameList().getGames().size() < 8) {
                int num = 0;
                Developer d = new Developer("Amelia's Fix Dude", 2);
                gameBrowser.addDeveloper(d);
                while (gameBrowser.getGameList().getGames().size() < 8) {
                    Game xtraGame = new Game("xtra"+num, d.getName());
                    gameBrowser.addGame(xtraGame);
                    num++;
                }
            }

            // makes a new list of games from those already existing in browser
            // (just like a user might
            GameList subGameList = new GameList("Rob's Games NEWLIST");
            List<String> sublist = gameBrowser.getGameList().getGames().subList(2,7);
            for(String aGame : sublist){
                subGameList.includeGame(aGame);
            }
            gameBrowser.addGameList(subGameList);

            //So Now, We have added a new list, dev (with personal gameList), and game to the gameBrowser!
            //TIME TO SAVE

            gameBrowser.save();

            System.out.println("Visibly Check DB for \"robsGame\", \"rob's Games\", " +
                    "\nthe gameList \"Rob's Games NEWLIST\", and developer \"Rob\"");

            gameBrowser.close();


        }catch (DataSourceException dse){
            fail(dse.getMessage());
            return;
        }
    }

    @Test
    public void addGameToUserGameListTest() throws IOException {
        try {
            SQLiteSource.RunSQL("src/databases/testing.db","src/scripts/DDL.sql");
            GameBrowser gameBrowser = new GameBrowser("src/databases/testing.db");
            User user1 = new User("user1", new GameList("user1"), null),
                 user2 = new User("user2", new GameList("user2"), null);
            Developer dev = new Developer("Amelia Chady", 0);
            Game game = new Game("Crossing Mammals", dev.getName());

            gameBrowser.addDeveloper(dev);
            gameBrowser.addGame(game);

            // unowned game
            assertEquals(0, game.getOwnedCount());

            // owned game by one user
            gameBrowser.addGameToUserGameList(user1, game);
            assertEquals(1, game.getOwnedCount());

            // owned game by more than one user;
            gameBrowser.addGameToUserGameList(user2, game);
            assertEquals(2, game.getOwnedCount());

            gameBrowser.close();
        } catch (DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void removeGameFromUserGameListTest() throws IOException {
        try {
            SQLiteSource.RunSQL("src/databases/testing.db","src/scripts/DDL.sql");
            GameBrowser gameBrowser = new GameBrowser("src/databases/testing.db");
            User user1 = new User("user1", new GameList("user1"), null),
                 user2 = new User("user2", new GameList("user2"), null);
            Developer dev = new Developer("Amelia Chady", 0);
            Game game = new Game("Crossing Mammals", dev.getName());

            gameBrowser.addDeveloper(dev);
            gameBrowser.addGame(game);

            // removal of non-owned game is null
            assertNull(gameBrowser.removeGameFromUserGameList(user1, game));

            // owned game removed by one user
            gameBrowser.addGameToUserGameList(user1, game);
            assertEquals("Crossing Mammals", gameBrowser.removeGameFromUserGameList(user1, game));
            assertEquals(0, game.getOwnedCount());

            // owned game by more than one user removed once;
            gameBrowser.addGameToUserGameList(user1, game);
            gameBrowser.addGameToUserGameList(user2, game);
            gameBrowser.removeGameFromUserGameList(user1, game);
            assertEquals(1, game.getOwnedCount());

            gameBrowser.close();
        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }
}
