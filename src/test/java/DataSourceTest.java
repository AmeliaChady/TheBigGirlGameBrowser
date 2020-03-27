import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataSourceTest {
    public static void DataSourceSaveGameTest(DataSource ds){
        System.out.println("Warning: DataSource must be empty for correct testing");
        Game cooltestgame = new Game("testGame", "This is a test to save a game object", new Developer("Frank"));

        // Saving a game
        try {
            ds.saveGame(cooltestgame);
            System.out.println("Warning: Requires Manual Check");
            System.out.println("Values Should Be: " +
                    "\n      title = 'testGame'" +
                    "\n  developer = 'Frank'" +
                    "\ndescription = 'This is a test to save a game object'" +
                    "\n     status = '0'");
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }

        // Resaving same game does not throw an error
        try {
            ds.saveGame(new Game("testGame", "This is a test to save a game object", new Developer("Frank")));
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }

        // Updating a saved game works - Description
        // Updating a saved game works - Status

        // Can't give a null game
        assertThrows(IllegalArgumentException.class, () -> ds.saveGame(null));
    }
}
