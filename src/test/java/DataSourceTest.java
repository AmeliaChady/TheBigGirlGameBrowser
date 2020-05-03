import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.geom.IllegalPathStateException;
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
        cooltestgame = new Game("Test-zx the Game","ahhhhhhhhh", "Ted");
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
                      "mario kart hack where all sounds are balloons popping", "Ted");
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

    public static void dataSourceLoadGameTest(DataSource ds) throws DataSourceException{
        System.out.println("Note, there cannot be a game with the title 'LoadGameTest3', otherwise tests will break");

        // Basic
        // Adding Two Test Games
        Game g;

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
    }

    public static void dataSourceSaveGameListTest(DataSource ds) throws DataSourceException{
        System.out.println("Warning: DataSource must be empty for correct testing");
        System.out.println("Warning: Used Combined View to verify");

        String bobby = "Bobby";
        Game game1 = new Game("testGame1", "this Game is a Test game", bobby);
        Game game2 = new Game("testGame2", "this Game is a Test game", bobby);

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

        Game game3 = new Game("testGame3", "this Game is a Test game", bobby);
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

    public static void dataSourceLoadGameListTest(DataSource ds) throws DataSourceException{
        System.out.println("Note, there cannot be a GameList called 'BogusList', otherwise tests will break");

        // Load Game List with No Games
        assertEquals(0, ds.loadGameList("Games I Have Time To Play").getGameCount());

        // Load Game List with One Game
        GameList loaded = ds.loadGameList("Everyone Else is Playing");
        assertEquals(1, loaded.getGameCount());

        // Load Game List with Two Games
        assertEquals(2, ds.loadGameList("The Kerry Anne Experience").getGameCount());

        // Games Hold Their Names
        assertEquals("Crossing Mammals", loaded.getGames().get(0));

        // Games Hold Developers (Using loadGame so only need simple connection test)
        Game loadedGame = ds.loadGame(loaded.getGames().get(0));
        assertEquals("HoarderOfBells", loadedGame.getDevelopers().get(0));

        // Sending Null Returns Null
        assertNull(ds.loadGameList(null));

        // Sending Bogus Returns Null
        assertNull(ds.loadGameList("BogusList"));
    }

    public static void dataSourceSaveDevelopersTest(DataSource ds) throws DataSourceException{
        System.out.println("Warning: DataSource must be empty for correct testing");
        System.out.println("Warning: Used Combined View to verify");
        Developer bobby = new Developer("Bobby", 1);

        bobby.getGameList().includeGame("game1");
        bobby.getGameList().includeGame("game2");
        bobby.getGameList().includeGame("game3");

        // At this point db should be aware of bob and have a relationship between bob and these games

        Developer gef = new Developer("jim", 2);

        gef.getGameList().includeGame("game1");
        gef.getGameList().includeGame("game3");
        ds.saveDeveloper(bobby);
        ds.saveDeveloper(gef);
    }

    public static void dataSourceLoadDevelopersTest(DataSource ds) throws DataSourceException{
        //todo  add checks for correct aid
        System.out.println("Note, there cannot be a GameList called 'LoadDeveloperBogusTest', otherwise tests will break");
//        Developer save = new Developer("LoadDeveloperTest", 1);
//        Game aGame = new Game("LoadDeveloperTestGame", "aa", save.getName());
//        ds.  saveGame(aGame);
//        save.getGameList().includeGame(aGame.getTitle());
//        ds.saveDeveloper(save);
//        ds.saveGameList(save.getGameList());

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
        // load them from db
        List<String> developers = ds.loadDeveloperList();

        // Load Works
        assertNotNull(developers);

        // All developers were loaded
        for (int i = 0; i < 5; i++){
            boolean found = false;
            Iterator<String> devs = developers.iterator();
            while (devs.hasNext() && !found){
                String curr = devs.next();
                if(curr.equals("test dev "+i))
                    found = true;
            }
            assertTrue(found);

        }

    }

    public static void dataSourceLoginTest(DataSource ds) throws DataSourceException{
        System.out.println("NEEDS SETUP!:" +
                        "\n  Source Has: Account(username, password) w/ account types" +
                        "\n    Account(userTest, user) w/ User" +
                        "\n    Account(devTest, dev) w/ Developer" +
                        "\n    Account(adminTest, admin) w/ Administrator" +
                        "\n    Account(doubleTest, double) w/ User & Developer" +
                        "\n    Account(nothingTest, nothing) w/ nothing" +
                        "\n    Account(fails, failure)" +
                        "\n Source Doesnt Have:" +
                        "\n    Account(usernameFail, failure)" +
                        "\n    Account(fails, passwordFail)" +
                        "\n    Account(usernameFail, passwordFail)");

        // Correct (returns)
        Accounts userAccounts = ds.login("userTest", "user");
        assertNotNull(userAccounts.user);
        assertNull(userAccounts.admin);
        assertNull(userAccounts.dev);

        Accounts devAccounts = ds.login("devTest", "dev");
        assertNotNull(devAccounts.dev);
        assertNull(devAccounts.user);
        assertNull(devAccounts.admin);

        Accounts adminAccounts = ds.login("adminTest", "admin");
        assertNotNull(adminAccounts.admin);
        assertNull(adminAccounts.user);
        assertNull(adminAccounts.dev);

        Accounts doubleAccounts = ds.login("doubleTest", "double");
        assertNotNull(doubleAccounts.user);
        assertNotNull(doubleAccounts.dev);
        assertNull(doubleAccounts.admin);

        Accounts nothingAccounts = ds.login("nothingTest", "nothing");
        assertNull(nothingAccounts.admin);
        assertNull(nothingAccounts.dev);
        assertNull(nothingAccounts.user);

        // Incorrect (exception)
        assertThrows(IllegalArgumentException.class, () -> ds.login("usernameFail", "failure"));
        assertThrows(IllegalArgumentException.class, () -> ds.login("fails", "passwordFail"));
        assertThrows(IllegalArgumentException.class, () -> ds.login("usernameFail", "passwordFail"));
        assertThrows(IllegalArgumentException.class, () -> ds.login(null, "failure"));
        assertThrows(IllegalArgumentException.class, () -> ds.login("fails", null));
        assertThrows(IllegalArgumentException.class, () -> ds.login(null, null));
    }

    public static void dataSourceSaveUserTest(DataSource ds) throws DataSourceException {
        User user = new User("user0", ds.loadGameList("user0 Game List"), null );
        Accounts account = new Accounts(user,"user0", "user0@mail.com", "password");

        // pass null user
        assertThrows(IllegalArgumentException.class, () -> ds.saveUser(null));

        // add game to user's game list
        user.addToOwnedGames("Game 4");
        System.out.println(user.getOwnedGames().getGame("Game 4"));
        ds.saveUser(account);
        assertEquals(4, ds.loadGameList("user0 Game List").getGameCount());

        // remove game from user's game list
        user.removeFromOwnedGames("Game 4");
        ds.saveUser(account);
    }

    public static void dataSourceLoadUserTest(DataSource ds) throws DataSourceException {
        User u = ds.loadUser("kerry");
        //object exists
        assertNotNull(u);

        //gameList length same
        assertEquals(u.getOwnedGames().getGameCount(), 2);

        //non-existing user
        assertNull(ds.loadUser("notRealUser"));
    }

    public static void dataSourceSaveAccountTest(DataSource ds) throws DataSourceException {
        // new developer account
        Developer developer1 = new Developer("developer1");
        String developer1Name = developer1.getName();
        Accounts developerAccount1 = new Accounts(developer1, "developer1@mail.com", developer1Name, "p");
        ds.saveAccount(developerAccount1);
        assertEquals(developer1Name, ds.loadDeveloper(developer1Name).getName());

        // new user account
        User user1 = new User("user1");
        String user1Name = user1.getName();
        Accounts userAccount1 = new Accounts(user1, "user1@mail.com", user1Name, "p");
        ds.saveAccount(userAccount1);
        ds.loadUser(user1Name);
        assertEquals(user1Name, ds.loadUser(user1Name).getName());

        // user creates new developer account
        Developer user1Developer = new Developer("user1");
        String user1DeveloperName = user1Developer.getName();
        Accounts user1DeveloperAccount = new Accounts(user1Developer,
                                                "user1@mail.com", user1DeveloperName, "p");
        ds.saveAccount(user1DeveloperAccount);
        ds.loadUser(user1DeveloperName);
        assertEquals(user1DeveloperName, ds.loadUser(user1Name).getName());

        // developer creates new user account
        User developer1User = new User("developer1");
        String developer1UserName = developer1User.getName();
        Accounts developer1UserAccount = new Accounts(developer1User,
                                                "developer1@mail.com", developer1UserName, "p");
        ds.saveAccount(developer1UserAccount);
        ds.loadUser(developer1UserName);
        assertEquals(developer1Name, ds.loadUser(developer1Name).getName());

        // user creates user account (duplicate)
        User userDuplicate = new User("user0");
        Accounts userDuplicateAccount = new Accounts(userDuplicate, "e",
                                                     userDuplicate.getName(), "password");
        try {
            ds.saveAccount(userDuplicateAccount);
            fail("Duplicate user account was created.");
        } catch(Exception e) {
            assertEquals("This user already exists!", e.getMessage());
        }

        // developer creates developer (duplicate)
        Developer developerDuplicate = new Developer("developer0");
        Accounts developerDuplicateAccount = new Accounts(developerDuplicate, "e",
                                                          developerDuplicate.getName(), "password");
        try {
            ds.saveAccount(developerDuplicateAccount);
            fail("Duplicate developer account was created.");
        } catch(Exception e) {
            assertEquals("This developer already exists!", e.getMessage());
        }

        // create new account with invalid credentials
        User invalidUser = new User("");
        String invalidUserName = "";
        Accounts invalidAccount = new Accounts(invalidUser, invalidUserName, "", "");
        try {
            ds.saveAccount(invalidAccount);
            fail("A user with invalid credentials was created.");
        } catch(Exception e) {
            assertEquals("Please provide proper credentials", e.getMessage());
        }
    }
}
