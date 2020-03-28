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
    public void acceptGameTest() {
        Administrator administrator = new Administrator("the_administrator");
        Game game = new Game("My very first game", "Some Dev");

        // Allow a game to be be available
        administrator.acceptGame(game);
        assertEquals("ACCEPTED", game.getStatus().toString());
    }

    @Test
    public void rejectGameTest() {
        Administrator administrator = new Administrator("the_administrator");
        Game game = new Game("My very second game", "Some Dev");

        // Allow a game to be be available
        administrator.rejectGame(game);
        assertEquals("REJECTED", game.getStatus().toString());
    }
}
