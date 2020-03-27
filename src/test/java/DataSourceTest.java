import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataSourceTest {
    public static void DataSourceSaveGameTest(DataSource ds){
        try {
            ds.saveGame(new Game("testGame", "This is a test to save a game object", new Developer("Frank")));
            System.out.println("Warning: Requires Manual Check");
            System.out.println("Values Should Be: " +
                    "\n      title = 'testGame'" +
                    "\n  developer = 'Frank'" +
                    "\ndescription = This is a test to save a game object" +
                    "\n     status = '0'");
        }catch (Exception e){
            fail("Should not throw exception");
        }

        assertThrows(IllegalArgumentException.class, () -> ds.saveGame(null));
    }
}
