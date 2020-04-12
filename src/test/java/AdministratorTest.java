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
        Game game = new Game("My very first game", "Some Dev");

        // Allow a game to be be available
        administrator.reviewGame(game);
        assertEquals("ACCEPTED", game.getStatus().toString());
    }

    @Test
    public void rejectGameTest() { // assumes a passing reviewGameTest
        Administrator administrator = new Administrator("the_administrator_bob");
        Game game = new Game("My very second game", "Some other Dev");
        administrator.reviewGame(game);

        // Send an accepted game to Limbo
        administrator.rejectGame(game);
        assertEquals("LIMBO", game.getStatus().toString());

        //Let's pretend that "Some other Dev" re-submits the game
        game.changeStatus(Status.PENDING);
        administrator.rejectGame(game);
        assertEquals(Status.REJECTED, game.getStatus());
    }
}
