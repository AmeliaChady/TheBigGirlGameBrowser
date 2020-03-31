import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameBrowserTest {

    @Test
    public void constructorTest() {
        GameBrowser gameBrowser;

        //-----------load games to a 'Master Game List'-----------------
        //
        SQLiteSource testDataSource = new SQLiteSource("testing.db");
        GameList testGameList = new GameList("Master Game List");
        Game testGame;
        Developer testDev;
        int gameCount = 10;

        for (int i = 1; i < gameCount+1; i++) {
            testDev = new Developer("dev "+i);
            testGame = new Game("game "+i, testDev);
            testGameList.includeGame(testGame);
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
            gameBrowser = new GameBrowser("testing.db");
            // Game list was loaded and length of list is as expected
            int expectedGameCount = gameCount;
            assertNotNull(gameBrowser.getGameList());
            assertEquals(expectedGameCount, gameBrowser.getGameList().getGameCount());

            // check that expected games from the master list were loaded
            int i = 0;
            GameList masterGameList = gameBrowser.getGameList();
            String expectedGameTitle;
            while (i < masterGameList.getGameCount()) {
                expectedGameTitle = "game "+(i+++1);
                assertEquals(expectedGameTitle, masterGameList.getGame(expectedGameTitle).getTitle());
            }

            // TODO check developers (once devs can be loaded from db)
            // TODO check administrators (once admins can be loaded from db)
        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void addGameTest(){
        try {
            GameBrowser gb = new GameBrowser("testing.db");

            assertEquals(8, gb.getGameList().getGameCount());

            //add game
            Developer dev = new Developer("Anita");
            Game gameToAdd = new Game("Candy Crush","My mom plays a lot of candy crush", dev, Status.PENDING);
            gb.addGame(gameToAdd);
            assertEquals(9, gb.getGameList().getGameCount());
            assertEquals("Candy Crush", gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getTitle());
            assertEquals("My mom plays a lot of candy crush", gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getDescription());
            assertEquals(dev, gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getDevelopers().get(0));
            assertEquals(Status.PENDING, gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getStatus());

            //pass game properties
            Developer dev2 = new Developer("Robert");
            List<Developer> devList = new ArrayList<Developer>();
            devList.add(dev2);
            gb.addGame("Clash of clans", "My dad plays a lot of clash of clans", devList, Status.PENDING);

            assertEquals(10, gb.getGameList().getGameCount());
            assertEquals("Clash of clans", gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getTitle());
            assertEquals("My dad plays a lot of clash of clans", gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getDescription());
            assertEquals(devList.get(0), gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getDevelopers().get(0));
            assertEquals(Status.PENDING, gb.getGameList().getGames().get(gb.getGameList().getGameCount()-1).getStatus());
        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void removeGameTest(){
        try {
            GameBrowser gb = new GameBrowser("testing.db");

            assertEquals(8, gb.getGameList().getGames().size());

            //non existent
            Game g1 = gb.removeGame("gcfhvjb");
            assertEquals(8, gb.getGameList().getGames().size());
            assertNull(g1);

            //existing game
            Game g2 = gb.removeGame("Toot Scooters");
            assertEquals(7, gb.getGameList().getGames().size());
            assertEquals("Toot Scooters", g2.getTitle());

            //non existent now, just removed
            Game g3 = gb.removeGame("Toot Scooters");
            assertEquals(7, gb.getGameList().getGames().size());
            assertNull(g3);

            //existing game
            Game g4 = gb.removeGame("testGame3");
            assertEquals(6, gb.getGameList().getGames().size());
            assertEquals("testGame3", g4.getTitle());

            //non existent now, removed
            Game g5 = gb.removeGame("Toot Scooters");
            assertEquals(6, gb.getGameList().getGames().size());
            assertNull(g5);

        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void addDeveloperTest() {
        try {
            GameBrowser gameBrowser = new GameBrowser("testing.db");

            // create a new developer
            gameBrowser.addDeveloper("dev1");
            assertEquals(1, gameBrowser.getDevelopers().size());
            assertEquals("dev1", gameBrowser.getDevelopers().get(0).getName());

            // create another
            gameBrowser.addDeveloper("dev2");
            assertEquals(2, gameBrowser.getDevelopers().size());
            assertEquals("dev2", gameBrowser.getDevelopers().get(1).getName());
        } catch(DataSourceException dse) {
            fail(dse.getMessage());
        }
    }

    @Test
    public void removeDeveloperTest() { // assumes a passing addDeveloperTest
        try {
            GameBrowser gameBrowser = new GameBrowser("testing.db");

            // remove one developer from list of only one dev
            gameBrowser.addDeveloper("dev1");
            assertEquals("dev1", gameBrowser.removeDeveloper("dev1").getName());
            assertEquals(0, gameBrowser.getDevelopers().size());

            // remove one developer from dev list > 1
            gameBrowser.addDeveloper("dev1");
            gameBrowser.addDeveloper("dev2");
            assertEquals("dev2", gameBrowser.removeDeveloper("dev2").getName());
            assertEquals(1, gameBrowser.getDevelopers().size());

            // remove non-existent developer
            assertNull(gameBrowser.removeDeveloper("dev2"));
            assertEquals(1, gameBrowser.getDevelopers().size());
        } catch(DataSourceException dse) {
            fail(dse.getMessage());
            return;
        }
    }
}
