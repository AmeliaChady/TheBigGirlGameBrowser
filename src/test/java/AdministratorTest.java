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
}
