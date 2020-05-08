import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UIPluginCLITest {
    public static final String DATABASE = "src/databases/Test_UIPlugin/UIPluginCLI.db";

    @Test
    public void displayReviewTest(){
        UIPluginCLI uiplug= new UIPluginCLI();

        Review r1 = new Review(5, "Pretty cool!", "Kerry Anne");
        uiplug.pullReview(r1);

        assertEquals("Rating: 5\n" +
                "Summary: Pretty cool!\n" +
                "Author: Kerry Anne\n\n", uiplug.displayableReview());

        r1 = new Review(3, "Not my favorite", "bertha");
        uiplug.pullReview(r1);

        assertEquals("Rating: 3\n" +
                "Summary: Not my favorite\n" +
                "Author: bertha\n\n", uiplug.displayableReview());

        r1 = new Review(1, "Hate it", "smellypants");
        uiplug.pullReview(r1);

        assertEquals("Rating: 1\n" +
                "Summary: Hate it\n" +
                "Author: smellypants\n\n", uiplug.displayableReview());

        r1 = null;
        uiplug.pullReview(r1);
        assertEquals("No review to display", uiplug.displayableReview());
    }

    @Test
    public void displayReviewListTest(){
        UIPluginCLI uiplug= new UIPluginCLI();

        //null
        assertEquals("No review list", uiplug.displayableReviewList());

        //make review list
        List<Review> revList = new ArrayList<>();

        //empty
        uiplug.pullReviewList(revList);
        assertEquals("Review list empty", uiplug.displayableReviewList());

        //1 review
        Review r1 = new Review(5, "Pretty cool!", "Kerry Anne");
        revList.add(r1);
        uiplug.pullReviewList(revList);

        assertEquals("Rating: 5\n" +
                "Summary: Pretty cool!\n" +
                "Author: Kerry Anne\n\n", uiplug.displayableReviewList());

        //2 reviews
        Review r2 = new Review(3, "Not my favorite", "bertha");
        revList.add(r2);
        uiplug.pullReviewList(revList);

        assertEquals("Rating: 5\n" +
                "Summary: Pretty cool!\n" +
                "Author: Kerry Anne\n\n" + "Rating: 3\n" +
                "Summary: Not my favorite\n" +
                "Author: bertha\n\n", uiplug.displayableReviewList());

        //3 reviews
        Review r3 = new Review(1, "Hate it", "smellypants");
        revList.add(r3);
        uiplug.pullReviewList(revList);

        assertEquals("Rating: 5\n" +
                "Summary: Pretty cool!\n" +
                "Author: Kerry Anne\n\n" + "Rating: 3\n" +
                "Summary: Not my favorite\n" +
                "Author: bertha\n\n" + "Rating: 1\n" +
                "Summary: Hate it\n" +
                "Author: smellypants\n\n", uiplug.displayableReviewList());

    }


    @Test
    public void displayGameTest(){
        UIPluginCLI uiplug= new UIPluginCLI();

        //0 developers, pending status
        Game g = new Game();
        uiplug.pullGame(g);
        assertEquals("Title: testGame\n" +
                     "Description: This is a test to create a new game object\n" +
                     "Developer(s): None\n" +
                     "Status: PENDING\nNo reviews\n\n", uiplug.displayableGame());


        //1 developer, pending status
        g = new Game("Best game", "This is the best game ever!", "kerry", Status.PENDING);
        uiplug.pullGame(g);
        assertEquals("Title: Best game\n" +
                     "Description: This is the best game ever!\n" +
                     "Developer(s): kerry\n" +
                     "Status: PENDING\nNo reviews\n\n", uiplug.displayableGame());

        //2 developers, pending status, no description
        List<String> developers = new ArrayList<>();
        developers.add("kerry anne");
        developers.add("kelsey");
        g = new Game("Cooking Mama",  developers);
        uiplug.pullGame(g);
        assertEquals("Title: Cooking Mama\n" +
                     "Description: No Description Given\n" +
                     "Developer(s): kerry anne, kelsey\n" +
                     "Status: PENDING\nNo reviews\n\n", uiplug.displayableGame());

        //3 developers, pending status=
        // keeps devs from before + 1
        developers.add("grace t. dury");
        g = new Game("Animal Crossing New Horizons",
                    "Live as the only human, sell seashells to survive, and be in constant debt.",
                    developers, Status.PENDING);
        uiplug.pullGame(g);
        assertEquals("Title: Animal Crossing New Horizons\n" +
                     "Description: Live as the only human, sell seashells to survive, and be in constant debt.\n" +
                     "Developer(s): kerry anne, kelsey, grace t. dury\n" +
                     "Status: PENDING\nNo reviews\n\n", uiplug.displayableGame());

        //1 developer, accepted
        g = new Game("camp rock 4", "kevin sells real estate now", "kevin jonas", Status.ACCEPTED);
        uiplug.pullGame(g);
        assertEquals("Title: camp rock 4\n" +
                     "Description: kevin sells real estate now\n" +
                     "Developer(s): kevin jonas\n" +
                     "Status: ACCEPTED\nNo reviews\n\n", uiplug.displayableGame());

        //1 developer, rejected
        g = new Game("cutest dog <3",
                    "she is my dog. I hate her name but she's still cute",
                    "bertha", Status.REJECTED);
        uiplug.pullGame(g);
        assertEquals("Title: cutest dog <3\n" +
                     "Description: she is my dog. I hate her name but she's still cute\n" +
                     "Developer(s): bertha\n" +
                     "Status: REJECTED\nNo reviews\n\n", uiplug.displayableGame());

        // null game
        uiplug.pullGame(null);
        assertThrows(IllegalStateException.class, uiplug::displayableGame);

//----game with reviews----
        uiplug.pullGame(g);
        //1 review
        g.addReview(new Review(4, "Pretty cool!", "Kerry Anne"));
        assertEquals("Title: cutest dog <3\n" +
                "Description: she is my dog. I hate her name but she's still cute\n" +
                "Developer(s): bertha\n" +
                "Status: REJECTED\nAverage rating: 4.0\n\n", uiplug.displayableGame());


        //2 reviews
        g.addReview(new Review(2, "Not my favorite", "Abby"));
        assertEquals("Title: cutest dog <3\n" +
                "Description: she is my dog. I hate her name but she's still cute\n" +
                "Developer(s): bertha\n" +
                "Status: REJECTED\nAverage rating: 3.0\n\n", uiplug.displayableGame());

        //3 reviews
        g.addReview(new Review(3, "Hate it", "Aiko"));
        assertEquals("Title: cutest dog <3\n" +
                "Description: she is my dog. I hate her name but she's still cute\n" +
                "Developer(s): bertha\n" +
                "Status: REJECTED\nAverage rating: 3.0\n\n", uiplug.displayableGame());

        //4 reviews
        g.addReview(new Review(5, "I actually love this dog more than my 2 daughters, and it shows.", "Anita"));
        assertEquals("Title: cutest dog <3\n" +
                "Description: she is my dog. I hate her name but she's still cute\n" +
                "Developer(s): bertha\n" +
                "Status: REJECTED\nAverage rating: 3.5\n\n", uiplug.displayableGame());



    }

    @Test
    public void displayGameAndReviewsTest(){
        UIPluginCLI uiplug= new UIPluginCLI();

        //no reviews
        Game g = new Game();
        uiplug.pullGame(g);
        assertEquals("Title: testGame\n" +
                "Description: This is a test to create a new game object\n" +
                "Developer(s): None\n" +
                "Status: PENDING\nNo reviews\n\n", uiplug.displayableGameandReviews());


        //1 review
        g = new Game("Best game", "This is the best game ever!", "kerry", Status.PENDING);
        g.addReview(new Review(2, "Not my favorite", "Abby"));
        uiplug.pullGame(g);
        assertEquals("Title: Best game\n" +
                "Description: This is the best game ever!\n" +
                "Developer(s): kerry\n" +
                "Status: PENDING\n" +
                "Average rating: 2.0\n" +
                "\n" +
                "Reviews:\n" +
                "Rating: 2\n" +
                "Summary: Not my favorite\n" +
                "Author: Abby\n\n", uiplug.displayableGameandReviews());

        //2 reviews
        List<String> developers = new ArrayList<>();
        developers.add("kerry anne");
        developers.add("kelsey");
        g = new Game("Cooking Mama",  developers);
        g.addReview(new Review(3, "Hate it", "Aiko"));
        g.addReview(new Review(4, "Pretty cool!", "Kerry Anne"));
        uiplug.pullGame(g);
        assertEquals("Title: Cooking Mama\n" +
                "Description: No Description Given\n" +
                "Developer(s): kerry anne, kelsey\n" +
                "Status: PENDING\n" +
                "Average rating: 3.5\n" +
                "\n" +
                "Reviews:\n" +
                "Rating: 3\n" +
                "Summary: Hate it\n" +
                "Author: Aiko\n" +
                "\n" +
                "Rating: 4\n" +
                "Summary: Pretty cool!\n" +
                "Author: Kerry Anne\n" +
                "\n", uiplug.displayableGameandReviews());

        //3 reviews
        developers.add("grace t. dury");
        g = new Game("Animal Crossing New Horizons",
                "Live as the only human, sell seashells to survive, and be in constant debt.",
                developers, Status.PENDING);
        g.addReview(new Review(5, "I love this game more than my ex-husband, and that says something", "Sonja Morgan"));
        g.addReview(new Review(5, "Better than the lower level of Ramona's Hamptons house, that's for sure", "countess luann"));
        g.addReview(new Review(5, "I love this more than I love my house in the hamptons", "Ramona Singer"));
        uiplug.pullGame(g);
        assertEquals("Title: Animal Crossing New Horizons\n" +
                "Description: Live as the only human, sell seashells to survive, and be in constant debt.\n" +
                "Developer(s): kerry anne, kelsey, grace t. dury\n" +
                "Status: PENDING\n" +
                "Average rating: 5.0\n" +
                "\n" +
                "Reviews:\n" +
                "Rating: 5\n" +
                "Summary: I love this game more than my ex-husband, and that says something\n" +
                "Author: Sonja Morgan\n" +
                "\n" +
                "Rating: 5\n" +
                "Summary: Better than the lower level of Ramona's Hamptons house, that's for sure\n" +
                "Author: countess luann\n" +
                "\n" +
                "Rating: 5\n" +
                "Summary: I love this more than I love my house in the hamptons\n" +
                "Author: Ramona Singer\n\n", uiplug.displayableGameandReviews());
    }

    @Test
    public void displayDeveloperTest(){
        UIPluginCLI uiplug = new UIPluginCLI();

        //0 games
        Developer d = new Developer("George Washington", 1);
        uiplug.pullDeveloper(d);
        assertEquals("Name: George Washington\n" +
                     "George Washingtons DevGames: This list is empty\n", uiplug.displayDeveloper());

        //1 game
        d.getGameList().includeGame("testGame");
        uiplug.pullDeveloper(d);
        assertEquals("Name: George Washington\n" +
                     "George Washingtons DevGames: testGame\n", uiplug.displayDeveloper());

        //2 games
        d.getGameList().includeGame("testGame2");
        uiplug.pullDeveloper(d);
        assertEquals("Name: George Washington\n" +
                     "George Washingtons DevGames: testGame, testGame2\n", uiplug.displayDeveloper());

        uiplug.pullDeveloper(null);
        assertThrows(IllegalStateException.class, uiplug::displayDeveloper);

    }

    @Test
    public void displayGameTitlesNumberedListTest() {
        UIPluginCLI uiplug = new UIPluginCLI();
        GameList gl = new GameList("test");

        //0 games
        uiplug.pullGameList(gl);
        assertEquals("There are no games to display\n", uiplug.displayGameTitlesNumberedList());

        //1 game
        gl.includeGame("Adventurer Kelsey");
        assertEquals("1. Adventurer Kelsey\n", uiplug.displayGameTitlesNumberedList());

        //2 games
        gl.includeGame("Adventurer Amelia");
        assertEquals("1. Adventurer Kelsey\n2. Adventurer Amelia\n", uiplug.displayGameTitlesNumberedList());

        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, uiplug::displayGameTitlesNumberedList);
    }

    @Test
    public void displayListNameAndGameTitlesTest() {
        UIPluginCLI uiplug = new UIPluginCLI();
        GameList gl = new GameList("testList");

        // 0 Games
        uiplug.pullGameList(gl);
        assertEquals("testList: This list is empty\n", uiplug.displayListNameAndGameTitles());

        // 1 Game
        gl.includeGame("testGame1");
        assertEquals("testList: testGame1\n", uiplug.displayListNameAndGameTitles());

        // 2 Games
        gl.includeGame("testGame2");
        assertEquals("testList: testGame1, testGame2\n", uiplug.displayListNameAndGameTitles());

        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, uiplug::displayListNameAndGameTitles);
    }

    @Test
    public void displayGamesGivenStatusTest() throws DataSourceException {
        // Needs GameBrowser
        System.out.println("NEEDS SETUP!");
        System.out.println("Games:\n" +
                "Title | Developer | Description | Status\n" +
                "ggstga | amelia | displayGamesGivenStatusTest | ACCEPTED\n" +
                "ggstgp1 | amelia | displayGamesGivenStatusTest | PENDING\n" +
                "ggstgp2 | amelia | displayGamesGivenStatusTest | PENDING\n");
        UIPluginCLI uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);
        GameList gl = new GameList("testList-ggst");

        uiplug.pullGameList(gl);
        uiplug.pullGameBrowser(gb);

        // 0 Games
        assertEquals("testList-ggst(PENDING):\n\nThis list is empty\n",
                uiplug.displayGamesGivenStatus(Status.PENDING));

        // 0 + 1 Other Status
        gl.includeGame("ggstga");
        assertEquals("testList-ggst(PENDING):\n\nThis list is empty\n",
                uiplug.displayGamesGivenStatus(Status.PENDING));

        // 1 Game
        gl.includeGame("ggstgp1");
        assertEquals("testList-ggst(PENDING):\n\n" +
                    "Title: ggstgp1\n" +
                    "Description: displayGamesGivenStatusTest\n" +
                    "Developer(s): amelia\n" +
                    "Status: PENDING\n"+ "No reviews\n\n",
                    uiplug.displayGamesGivenStatus(Status.PENDING));

        // 2 Games
        gl.includeGame("ggstgp2");
        assertEquals("testList-ggst(PENDING):\n\n" +
                        "Title: ggstgp1\n" +
                        "Description: displayGamesGivenStatusTest\n" +
                        "Developer(s): amelia\n" +
                        "Status: PENDING\n"+ "No reviews\n\n" +
                        "Title: ggstgp2\n" +
                        "Description: displayGamesGivenStatusTest\n" +
                        "Developer(s): amelia\n" +
                        "Status: PENDING\n"+ "No reviews\n\n",
                uiplug.displayGamesGivenStatus(Status.PENDING));

        // Accepted Status
        assertEquals("testList-ggst(ACCEPTED):\n\n" +
                        "Title: ggstga\n" +
                        "Description: displayGamesGivenStatusTest\n" +
                        "Developer(s): amelia\n" +
                        "Status: ACCEPTED\n"+ "No reviews\n\n",
                uiplug.displayGamesGivenStatus(Status.ACCEPTED));

        // Null Game List.
        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, () -> uiplug.displayGamesGivenStatus(Status.PENDING));

        gb.close();
        // Null GameBrowser
        uiplug.pullGameBrowser(null);
        uiplug.pullGameList(gl);
        assertThrows(IllegalStateException.class, () -> uiplug.displayGamesGivenStatus(Status.PENDING));
    }

    @Test
    public void displayAllGamesTest() throws DataSourceException {
        // Needs GameBrowser
        System.out.println("NEEDS SETUP!");
        System.out.println("Games:\n" +
                "Title | Developer | Description | Status\n" +
                "agtg1 | amelia | displayAllGamesTest | ACCEPTED\n" +
                "agtg2 | amelia | displayAllGamesTest | PENDING\n");
        UIPluginCLI uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);
        GameList gl = new GameList("testList-agt");

        uiplug.pullGameList(gl);
        uiplug.pullGameBrowser(gb);

        // 0 Games
        assertEquals("testList-agt:\n\nThis list is empty\n", uiplug.displayAllGames());

        // 1 Game
        gl.includeGame("agtg1");
        assertEquals( "testList-agt:\n\n" +
                "Title: agtg1\n" +
                "Description: displayAllGamesTest\n" +
                "Developer(s): amelia\n" +
                "Status: ACCEPTED\n" + "No reviews\n\n",
                uiplug.displayAllGames());

        // 2 Games
        gl.includeGame("agtg2");
        assertEquals( "testList-agt:\n\n" +
                "Title: agtg1\n" +
                "Description: displayAllGamesTest\n" +
                "Developer(s): amelia\n" +
                "Status: ACCEPTED\n" + "No reviews\n\n"+
                "Title: agtg2\n" +
                "Description: displayAllGamesTest\n" +
                "Developer(s): amelia\n" +
                "Status: PENDING\n"+ "No reviews\n\n",
                uiplug.displayAllGames());

        // Null Game List.
        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, uiplug::displayAllGames);

        gb.close();
        // Null GameBrowser
        uiplug.pullGameBrowser(null);
        uiplug.pullGameList(gl);
        assertThrows(IllegalStateException.class, uiplug::displayAllGames);
    }

    @Test
    public void displayNumberedListOfGamesGivenStatusTest() throws DataSourceException{
        UIPluginCLI uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);
        GameList gl = new GameList("test");
        uiplug.pullGameList(gl);
        uiplug.pullGameBrowser(gb);

        //0 games
        assertEquals("There are no games to display\n", uiplug.displayNumberedListOfGamesGivenStatus(Status.ACCEPTED));

        //dev list
        List<String> devs = new ArrayList<String>();
        devs.add("snoop dog");
        gb.addDeveloper(new Developer("snoop dog", 0));

        //1 game
        gb.addGame("Adventurer Kelsey","kels goes on an adventure", devs, Status.PENDING);
        gl.includeGame("Adventurer Kelsey");

        assertEquals("1. Adventurer Kelsey\n", uiplug.displayNumberedListOfGamesGivenStatus(Status.PENDING));

        //2 games
        gb.addGame("Adventurer Amelia","Amelia goes on an adventure", devs, Status.PENDING);
        gl.includeGame("Adventurer Amelia");

        assertEquals("1. Adventurer Kelsey\n2. Adventurer Amelia\n", uiplug.displayNumberedListOfGamesGivenStatus(Status.PENDING));

        //Accepted game added, still only 2 games showing
        gb.addGame("Adventurer Kerry","Amelia goes on an adventure", devs, Status.ACCEPTED);
        gl.includeGame("Adventurer Kerry");

        assertEquals("1. Adventurer Kelsey\n2. Adventurer Amelia\n", uiplug.displayNumberedListOfGamesGivenStatus(Status.PENDING));

        //show only accepted game, not accepted
        assertEquals("1. Adventurer Kerry\n", uiplug.displayNumberedListOfGamesGivenStatus(Status.ACCEPTED));


        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, () -> uiplug.displayNumberedListOfGamesGivenStatus(Status.PENDING));
    }

    @Test
    public void getGamesGivenStatusTest() throws DataSourceException{
        UIPluginCLI uiplug = new UIPluginCLI();
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);
        GameList gl = new GameList("test");
        GameList compareGL = new GameList("test");
        uiplug.pullGameList(gl);
        uiplug.pullGameBrowser(gb);

        //0 games
        assertEquals("There are no games to display\n", uiplug.displayNumberedListOfGamesGivenStatus(Status.ACCEPTED));

        //dev list
        List<String> devs = new ArrayList<String>();
        devs.add("snoop dog");
        gb.addDeveloper(new Developer("snoop dog", 0));

        //1 game
        gb.addGame("Adventurer Kelsey","kels goes on an adventure", devs, Status.PENDING);
        gl.includeGame("Adventurer Kelsey");
        compareGL.includeGame("Adventurer Kelsey");

        assertEquals(compareGL.getGames(), uiplug.getGamesGivenStatus(Status.PENDING).getGames());

        //2 games
        gb.addGame("Adventurer Amelia","Amelia goes on an adventure", devs, Status.PENDING);
        gl.includeGame("Adventurer Amelia");
        compareGL.includeGame("Adventurer Amelia");


        assertEquals(compareGL.getGames(), uiplug.getGamesGivenStatus(Status.PENDING).getGames());

        //Accepted game added, still only 2 games showing
        gb.addGame("Adventurer Kerry","Amelia goes on an adventure", devs, Status.ACCEPTED);
        gl.includeGame("Adventurer Kerry");

        assertEquals(compareGL.getGames(), uiplug.getGamesGivenStatus(Status.PENDING).getGames());

        compareGL.includeGame("Adventurer Kerry");
        compareGL.removeGame("Adventurer Kelsey");
        compareGL.removeGame("Adventurer Amelia");
        //show only accepted game, not accepted
        assertEquals(compareGL.getGames(), uiplug.getGamesGivenStatus(Status.ACCEPTED).getGames());


        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, () -> uiplug.getGamesGivenStatus(Status.PENDING).getGames());
    }

    @Test
    public void displayableNumberedListOfFullGamesTest() throws DataSourceException{
        UIPluginCLI uiplug= new UIPluginCLI();
        GameList gl = new GameList("test");
        uiplug.pullGameList(gl);
        GameBrowser gb = new GameBrowser(DATABASE, uiplug);

        List<String> developers = new ArrayList<>();
        developers.add("snoop dog");
        gb.addDeveloper(new Developer("snoop dog", 0));

        //1 game
        Game g = new Game();
        gb.addDeveloper(new Developer("snoop dog", 1));
        gb.addGame(g);
        gl.includeGame(g.getTitle());
        assertEquals("1. Title: testGame\n" +
                "Description: This is a test to create a new game object\n" +
                "Developer(s): None\n" +
                "Status: PENDING\n" +
                "No reviews\n\n", uiplug.displayableNumberedListOfFullGames());


        //2 games
        developers.add("kerry");
        gb.addDeveloper(new Developer("kerry", 2));
        g = new Game("Best game", "This is the best game ever!", "kerry", Status.PENDING);
        gl.includeGame(g.getTitle());
        gb.addGame(g);
        assertEquals("1. Title: testGame\n" +
                "Description: This is a test to create a new game object\n" +
                "Developer(s): None\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "2. Title: Best game\n" +
                "Description: This is the best game ever!\n" +
                "Developer(s): kerry\n" +
                "Status: PENDING\n" +
                "No reviews\n\n", uiplug.displayableNumberedListOfFullGames());

        //3 games
        developers.add("kerry anne");
        developers.add("kelsey");
        gb.addDeveloper(new Developer("kerry anne", 3));
        gb.addDeveloper(new Developer("kelsey", 4));
        g = new Game("Cooking Mama",  developers);
        gb.addGame(g);
        gl.includeGame(g.getTitle());
        assertEquals("1. Title: testGame\n" +
                "Description: This is a test to create a new game object\n" +
                "Developer(s): None\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "2. Title: Best game\n" +
                "Description: This is the best game ever!\n" +
                "Developer(s): kerry\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "3. Title: Cooking Mama\n" +
                "Description: No Description Given\n" +
                "Developer(s): kelsey, kerry, kerry anne, snoop dog\n" +
                "Status: PENDING\n" +
                "No reviews\n\n", uiplug.displayableNumberedListOfFullGames());

        //4 games
        developers.add("grace t. dury");
        gb.addDeveloper(new Developer("grace t. dury", 5));
        g = new Game("Animal Crossing New Horizons",
                "Live as the only human, sell seashells to survive, and be in constant debt.",
                developers, Status.PENDING);
        gb.addGame(g);
        gl.includeGame(g.getTitle());
        assertEquals("1. Title: testGame\n" +
                "Description: This is a test to create a new game object\n" +
                "Developer(s): None\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "2. Title: Best game\n" +
                "Description: This is the best game ever!\n" +
                "Developer(s): kerry\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "3. Title: Cooking Mama\n" +
                "Description: No Description Given\n" +
                "Developer(s): kelsey, kerry, kerry anne, snoop dog\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "4. Title: Animal Crossing New Horizons\n" +
                "Description: Live as the only human, sell seashells to survive, and be in constant debt.\n" +
                "Developer(s): grace t. dury, kelsey, kerry, kerry anne, snoop dog\n" +
                "Status: PENDING\n" +
                "No reviews\n\n", uiplug.displayableNumberedListOfFullGames());

        //5 games
        developers.add("kevin jonas");
        gb.addDeveloper(new Developer("kevin jonas", 6));
        g = new Game("camp rock 4", "kevin sells real estate now", "kevin jonas", Status.ACCEPTED);
        gl.includeGame(g.getTitle());
        gb.addGame(g);
        assertEquals("1. Title: testGame\n" +
                "Description: This is a test to create a new game object\n" +
                "Developer(s): None\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "2. Title: Best game\n" +
                "Description: This is the best game ever!\n" +
                "Developer(s): kerry\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "3. Title: Cooking Mama\n" +
                "Description: No Description Given\n" +
                "Developer(s): kelsey, kerry, kerry anne, snoop dog\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "4. Title: Animal Crossing New Horizons\n" +
                "Description: Live as the only human, sell seashells to survive, and be in constant debt.\n" +
                "Developer(s): grace t. dury, kelsey, kerry, kerry anne, snoop dog\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "5. Title: camp rock 4\n" +
                "Description: kevin sells real estate now\n" +
                "Developer(s): kevin jonas\n" +
                "Status: ACCEPTED\n" +
                "No reviews\n\n", uiplug.displayableNumberedListOfFullGames());

        //6 games
        developers.add("bertha");
        gb.addDeveloper(new Developer("bertha", 7));
        g = new Game("cutest dog <3",
                "she is my dog. I hate her name but shes still cute",
                "bertha", Status.REJECTED);
        gl.includeGame(g.getTitle());
        gb.addGame(g);
        assertEquals("1. Title: testGame\n" +
                "Description: This is a test to create a new game object\n" +
                "Developer(s): None\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "2. Title: Best game\n" +
                "Description: This is the best game ever!\n" +
                "Developer(s): kerry\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "3. Title: Cooking Mama\n" +
                "Description: No Description Given\n" +
                "Developer(s): kelsey, kerry, kerry anne, snoop dog\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "4. Title: Animal Crossing New Horizons\n" +
                "Description: Live as the only human, sell seashells to survive, and be in constant debt.\n" +
                "Developer(s): grace t. dury, kelsey, kerry, kerry anne, snoop dog\n" +
                "Status: PENDING\n" +
                "No reviews\n\n" +
                "5. Title: camp rock 4\n" +
                "Description: kevin sells real estate now\n" +
                "Developer(s): kevin jonas\n" +
                "Status: ACCEPTED\n" +
                "No reviews\n\n" +
                "6. Title: cutest dog <3\n" +
                "Description: she is my dog. I hate her name but shes still cute\n" +
                "Developer(s): bertha\n" +
                "Status: REJECTED\n" +
                "No reviews\n\n", uiplug.displayableNumberedListOfFullGames());

        // null game
        uiplug.pullGameList(null);
        assertThrows(IllegalStateException.class, ()-> uiplug.displayableNumberedListOfFullGames());

    }
}
