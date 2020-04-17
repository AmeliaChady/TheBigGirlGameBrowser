import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {



    Game game1 = new Game("game1", "game1 description", new Developer("Jack"),Status.ACCEPTED);
    Game game2 = new Game("game2", "game2 description", new Developer ("Marcy"), Status.ACCEPTED);
    Game game3 = new Game("game3", "game3 description", new Developer("Henry"), Status.ACCEPTED);




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
        user2.addToWishList(game1);
        assertNotNull(user2.getWishList());

        assertEquals(user2.getWishList().getGameCount(), 1);

        user2.addToWishList(game2);
        user2.addToWishList(game3);

        assertEquals(user2.getWishList().getGameCount(), 3);


    }

    @Test
    public void userRemoveFromWishListTest(){
        User user3 = new User(new GameList("ownedGames"), new GameList("wishList"));
        assertEquals(user3.getWishList().getGameCount(), 0);
        user3.addToWishList(game1);
        user3.addToWishList(game2);
        user3.addToWishList(game3);

        assertEquals(user3.getWishList().getGameCount(), 3);

        user3.removeFromWishList(game2);
        assertEquals(user3.getWishList().getGameCount(), 2);

        user3.removeFromWishList(game1);
        user3.removeFromWishList(game3);

        assertEquals(user3.getWishList().getGameCount(), 0);
    }

    @Test
    public void userAddToOwnedGamesTest(){
        Game countGameTest = new Game("test game", "test game description",
                new ArrayList<>() , Status.ACCEPTED, 0);
        User user4 = new User(new GameList("ownedGames"), new GameList("wishList"));
        User user5 = new User(new GameList("ownedGames"), new GameList("wishList"));
        User user6 = new User(new GameList("ownedGames"), new GameList("wishList"));
        assertEquals(user4.getOwnedGames().getGameCount(), 0);
        user4.addToOwnedGames(game1);
        assertNotNull(user4.getOwnedGames());

        assertEquals(user4.getOwnedGames().getGameCount(), 1);

        user4.addToOwnedGames(game2);
        user4.addToOwnedGames(game3);

        assertEquals(user4.getOwnedGames().getGameCount(), 3);

        assertEquals(countGameTest.getOwnedCount(), 0);

        user4.addToOwnedGames(countGameTest);

        assertEquals(countGameTest.getOwnedCount(), 1);

        user5.addToOwnedGames(countGameTest);
        user6.addToOwnedGames(countGameTest);

        assertEquals(countGameTest.getOwnedCount(), 3);



    }
}
