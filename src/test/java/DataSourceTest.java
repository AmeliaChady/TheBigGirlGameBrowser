import java.util.*;

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
        // TODO: UPDATE FOR EMAILS
        System.out.println("NEEDS SETUP!:" +
                        "\n  Source Has: Account(username, email, password) w/ account types" +
                        "\n    Account(userTest, userTest@test.com, user) w/ User" +
                        "\n    Account(devTest, devTest@test.com, dev) w/ Developer" +
                        "\n    Account(adminTest, adminTest@test.com, admin) w/ Administrator" +
                        "\n    Account(doubleTest, doubleTest@test.com, double) w/ User & Developer" +
                        "\n    Account(nothingTest, nothingTest@test.com, nothing) w/ nothing" +
                        "\n    Account(fails, fails@test.com, failure)" +
                        "\n Source Doesnt Have:" +
                        "\n    Account(usernameFail, failure)" +
                        "\n    Account(fails, passwordFail)" +
                        "\n    Account(usernameFail, passwordFail)");

        // Correct (returns)
        Accounts userAccounts = ds.login("userTest", "user");
        assertEquals("userTest", userAccounts.getUsername());
        assertEquals("userTest@test.com", userAccounts.getEmail());
        assertEquals("user", userAccounts.getPassword());
        assertNotNull(userAccounts.user);
        assertNull(userAccounts.admin);
        assertNull(userAccounts.dev);

        Accounts devAccounts = ds.login("devTest", "dev");
        assertEquals("devTest", devAccounts.getUsername());
        assertEquals("devTest@test.com", devAccounts.getEmail());
        assertEquals("dev", devAccounts.getPassword());
        assertNotNull(devAccounts.dev);
        assertNull(devAccounts.user);
        assertNull(devAccounts.admin);

        Accounts adminAccounts = ds.login("adminTest", "admin");
        assertEquals("adminTest", adminAccounts.getUsername());
        assertEquals("adminTest@test.com", adminAccounts.getEmail());
        assertEquals("admin", adminAccounts.getPassword());
        assertNotNull(adminAccounts.admin);
        assertNull(adminAccounts.user);
        assertNull(adminAccounts.dev);

        Accounts doubleAccounts = ds.login("doubleTest", "double");
        assertEquals("doubleTest", doubleAccounts.getUsername());
        assertEquals("doubleTest@test.com", doubleAccounts.getEmail());
        assertEquals("double", doubleAccounts.getPassword());
        assertNotNull(doubleAccounts.user);
        assertNotNull(doubleAccounts.dev);
        assertNull(doubleAccounts.admin);

        Accounts nothingAccounts = ds.login("nothingTest", "nothing");
        assertEquals("nothingTest", nothingAccounts.getUsername());
        assertEquals("nothingTest@test.com", nothingAccounts.getEmail());
        assertEquals("nothing", nothingAccounts.getPassword());
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
        Accounts account = new Accounts("user0", "user0@mail.com", "password");
        account.user = user;

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
        Map<AccountSavingAccounts, AccountSavingFlags> flagmap;
        // new developer account (NO Existing User)
        Accounts dev = new Accounts("developer","developer1@mail.com", "password");
        dev.dev = new Developer("developer");
        flagmap = ds.saveAccount(dev);

        assertEquals(flagmap.get(AccountSavingAccounts.ACCT), AccountSavingFlags.PASS);
        assertEquals(flagmap.get(AccountSavingAccounts.DEV), AccountSavingFlags.PASS);
        assertEquals(flagmap.get(AccountSavingAccounts.USER), AccountSavingFlags.NOTHING);
        assertEquals(dev.dev.getGameListName(),
                ds.loadDeveloper(dev.dev.getName()).getGameListName());


        // new user account (NO Existing Developer)
        Accounts user = new Accounts("user","user@mail.com", "password");
        user.user = new User("user");
        flagmap = ds.saveAccount(user);

        assertEquals(flagmap.get(AccountSavingAccounts.ACCT), AccountSavingFlags.PASS);
        assertEquals(flagmap.get(AccountSavingAccounts.USER), AccountSavingFlags.PASS);
        assertEquals(flagmap.get(AccountSavingAccounts.DEV), AccountSavingFlags.NOTHING);
        assertEquals(user.user.getOwnedGames().getName(),
                ds.loadUser(user.user.getName()).getOwnedGames().getName());

        // new user & dev accounts
        Accounts doubleacct = new Accounts("double1", "double1@mail.com", "password");
        doubleacct.dev = new Developer("double1");
        doubleacct.user = new User("double1");
        flagmap = ds.saveAccount(doubleacct);

        assertEquals(flagmap.get(AccountSavingAccounts.ACCT), AccountSavingFlags.PASS);
        assertEquals(flagmap.get(AccountSavingAccounts.USER), AccountSavingFlags.PASS);
        assertEquals(flagmap.get(AccountSavingAccounts.DEV), AccountSavingFlags.PASS);
        assertEquals(doubleacct.dev.getGameListName(),
                ds.loadDeveloper(doubleacct.dev.getName()).getGameListName());
        assertEquals(doubleacct.user.getOwnedGames().getName(),
                ds.loadUser(doubleacct.user.getName()).getOwnedGames().getName());

        // old user creates new developer account
        user.dev = new Developer(user.getUsername());
        flagmap = ds.saveAccount(user);

        assertEquals(flagmap.get(AccountSavingAccounts.ACCT), AccountSavingFlags.DUPLICATE);
        assertEquals(flagmap.get(AccountSavingAccounts.USER), AccountSavingFlags.DUPLICATE);
        assertEquals(flagmap.get(AccountSavingAccounts.DEV), AccountSavingFlags.PASS);
        assertEquals(user.user.getOwnedGames().getName(),
                ds.loadUser(user.user.getName()).getOwnedGames().getName());
        assertEquals(user.dev.getGameListName(),
                ds.loadDeveloper(user.dev.getName()).getGameListName());

        // old developer creates new user account
        dev.user = new User(dev.getUsername());
        flagmap = ds.saveAccount(dev);

        assertEquals(flagmap.get(AccountSavingAccounts.ACCT), AccountSavingFlags.DUPLICATE);
        assertEquals(flagmap.get(AccountSavingAccounts.USER), AccountSavingFlags.DUPLICATE);
        assertEquals(flagmap.get(AccountSavingAccounts.DEV), AccountSavingFlags.PASS);
        assertEquals(dev.user.getOwnedGames().getName(),
                ds.loadUser(dev.user.getName()).getOwnedGames().getName());
        assertEquals(dev.dev.getGameListName(),
                ds.loadDeveloper(dev.dev.getName()).getGameListName());


        // Double Dupe
        flagmap = ds.saveAccount(doubleacct);
        assertEquals(flagmap.get(AccountSavingAccounts.ACCT), AccountSavingFlags.DUPLICATE);
        assertEquals(flagmap.get(AccountSavingAccounts.USER), AccountSavingFlags.DUPLICATE);
        assertEquals(flagmap.get(AccountSavingAccounts.DEV), AccountSavingFlags.DUPLICATE);
        assertEquals(doubleacct.dev.getGameListName(),
                ds.loadDeveloper(doubleacct.dev.getName()).getGameListName());
        assertEquals(doubleacct.user.getOwnedGames().getName(),
                ds.loadUser(doubleacct.user.getName()).getOwnedGames().getName());

        // Null
        Accounts nulls = new Accounts("nulls", "nulls@mail.com", "password");
        flagmap = ds.saveAccount(nulls);

        assertEquals(flagmap.get(AccountSavingAccounts.ACCT), AccountSavingFlags.PASS);
        assertEquals(flagmap.get(AccountSavingAccounts.USER), AccountSavingFlags.NOTHING);
        assertEquals(flagmap.get(AccountSavingAccounts.DEV), AccountSavingFlags.NOTHING);

        // Account is null
        assertThrows(IllegalArgumentException.class, () -> ds.saveAccount(null));
    }
}
