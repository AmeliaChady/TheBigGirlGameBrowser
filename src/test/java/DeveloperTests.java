import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeveloperTests {

    @Test
    public void developerConstructorTest(){
        Developer d1 = new Developer("Bob (the cat)");
        assertNotNull(d1.getName());
        assertNotNull(d1.getGameListTitle());
        assertEquals("Bob (the cat)'s Games", d1.getGameListTitle());
        assertEquals("Bob (the cat)", d1.getName());
        assertEquals(0, d1.getGameList().getGameCount());


        Developer d2 = new Developer("Bob (my dad)");
        assertNotNull(d2.getName());
        assertNotNull(d2.getGameListTitle());
        assertEquals("Bob (my dad)'s Games", d2.getGameListTitle());
        assertEquals("Bob (my dad)", d2.getName());
        assertEquals(0, d2.getGameList().getGameCount());
    }
}
