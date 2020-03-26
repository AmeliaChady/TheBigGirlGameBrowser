import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeveloperTests {

    @Test
    public void developerConstructorTest(){
        Developer d1 = new Developer("Bob (the cat)");
        assertNotNull(d1.getName());
        assertNotNull(d1.getGameList());
        assertEquals(0, d1.getGameList().getGameCount());
        assertEquals("Bob (the cat)", d1.getName());

        Developer d2 = new Developer("Bob (my dad)");
        assertNotNull(d2.getName());
        assertNotNull(d2.getGameList());
        assertEquals(0, d2.getGameList().getGameCount());
        assertEquals("Bob (my dad)", d2.getName());


    }
}
