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
        Game game = new Game("My very first game", new Developer("Some Dev"));

        // Allow a game to be be available
        administrator.reviewGame(game);
        assertEquals("ACCEPTED", game.getStatus().toString());
    }

    @Test
    public void rejectGameTest() { // assumes a passing reviewGameTest
        Administrator administrator = new Administrator("the_administrator_bob");
        Game game = new Game("My very second game", new Developer("Some other Dev"));
        administrator.reviewGame(game);

        // Reject an accepted game
        administrator.rejectGame(game);
        assertEquals("REJECTED", game.getStatus().toString());
    }
}
