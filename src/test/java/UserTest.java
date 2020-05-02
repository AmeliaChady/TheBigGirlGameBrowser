import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {



    Game game1 = new Game("game1", "game1 description","Jack",Status.ACCEPTED);
    Game game2 = new Game("game2", "game2 description","Marcy", Status.ACCEPTED);
    Game game3 = new Game("game3", "game3 description", "Henry", Status.ACCEPTED);




    @Test
    public void userConstructorTest(){
        User user = new User(new GameList("ownedGames"), new GameList("wishList"));
        assertEquals(user.getOwnedGames().getGameCount(), 0);
        assertEquals(user.getWishList().getGameCount(),0);

    }

    @Test
    public void userAddToWishListTest(){
        User user2 = new User(new GameList("ownedGames"), new GameList("wishList"));
        assertEquals(user2.getWishList().getGameCount(), 0);
        user2.addToWishList(game1.getTitle());
        assertNotNull(user2.getWishList());

        assertEquals(user2.getWishList().getGameCount(), 1);

        user2.addToWishList(game2.getTitle());
        user2.addToWishList(game3.getTitle());

        assertEquals(user2.getWishList().getGameCount(), 3);


    }

    @Test
    public void userRemoveFromWishListTest(){
        User user3 = new User(new GameList("ownedGames"), new GameList("wishList"));
        assertEquals(user3.getWishList().getGameCount(), 0);
        user3.addToWishList(game1.getTitle());
        user3.addToWishList(game2.getTitle());
        user3.addToWishList(game3.getTitle());

        assertEquals(user3.getWishList().getGameCount(), 3);

        user3.removeFromWishList(game2.getTitle());
        assertEquals(user3.getWishList().getGameCount(), 2);

        user3.removeFromWishList(game1.getTitle());
        user3.removeFromWishList(game3.getTitle());

        assertEquals(user3.getWishList().getGameCount(), 0);
    }

    @Test
    public void userAddToOwnedGamesTest(){
        Game countGameTest = new Game("test game", "test game description",
                new ArrayList<String>() , Status.ACCEPTED);
        User user4 = new User(new GameList("ownedGames"), new GameList("wishList"));
        User user5 = new User(new GameList("ownedGames"), new GameList("wishList"));
        User user6 = new User(new GameList("ownedGames"), new GameList("wishList"));
        assertEquals(user4.getOwnedGames().getGameCount(), 0);
        user4.addToOwnedGames(game1.getTitle());
        assertNotNull(user4.getOwnedGames());

        assertEquals(user4.getOwnedGames().getGameCount(), 1);

        user4.addToOwnedGames(game2.getTitle());
        user4.addToOwnedGames(game3.getTitle());

        assertEquals(user4.getOwnedGames().getGameCount(), 3);

        assertEquals(0, countGameTest.getOwnedCount());

        user4.addToOwnedGames(countGameTest.getTitle());

        user5.addToOwnedGames(countGameTest.getTitle());
        user6.addToOwnedGames(countGameTest.getTitle());
    }

    @Test
    public void userRemoveFromOwnedGamesTest() {
        User user = new User(new GameList("ownedGames"), null);
        user.addToOwnedGames("Cows V. Aliens");

        //non existent
        String g1 = user.removeFromOwnedGames("gcfhvjb");
        assertEquals(1, user.getOwnedGames().getGameCount());
        assertNull(g1);

        //existing game
        String g2 = user.removeFromOwnedGames("Cows V. Aliens");
        assertEquals(0, user.getOwnedGames().getGameCount());
        assertEquals("Cows V. Aliens", g2);

        //non existent now, just removed
        String g3 = user.removeFromOwnedGames("Cows V. Aliens");
        assertEquals(0, user.getOwnedGames().getGameCount());
        assertNull(g3);
    }
}
