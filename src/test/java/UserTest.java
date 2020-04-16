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
        assertNull(user.getOwnedGames());
        assertNull(user.getWishList());

    }

    @Test
    public void userAddToWishlistTest(){
        User user2 = new User(new GameList("ownedGames"), new GameList("wishList"));
        assertNull(user2.getWishList());
        user2.addToWishList(game1);
        assertNotNull(user2.getWishList());

        assertEquals(user2.getWishList().getGameCount(), 1);


    }
}
