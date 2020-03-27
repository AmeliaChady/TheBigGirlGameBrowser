import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    @Test
    public void constructorTest() {
        // Username created and set
        assertEquals("the_administrator", new Administrator("the_administrator").getUsername());

        // Username not created (invalid)
        assertThrows(IllegalArgumentException.class, () -> new Administrator(""));
    }

    @Test
    public void reviewGameTest() {
        Administrator administrator = new Administrator("the_administrator");
        GameList gameList = new GameList("A great list of games");
        Game game = new Game("My very first game", new Developer("Some Dev"));

        // Add a new Game
        administrator.reviewGame(gameList, game);
        assertEquals(1, gameList.getGameCount());
    }
}
