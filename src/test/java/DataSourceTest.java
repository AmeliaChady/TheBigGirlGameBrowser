import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataSourceTest {
    public static void DataSourceSaveGameTest(DataSource ds) throws DataSourceException{
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
                    "\n     status = 'PENDING'");
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }

        // Resaving same game does not throw an error
        try {
            ds.saveGame(cooltestgame);
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Should not throw exception");
        }

        // Updating a saved game works - Description
        cooltestgame = new Game("Test-zx the Game","ahhhhhhhhh",new Developer("Ted"));
        ds.saveGame(cooltestgame);
        cooltestgame.changeDescription("cool robot game");
        ds.saveGame(cooltestgame);

        // Updating a saved game works - Status
        cooltestgame.changeStatus(Status.ACCEPTED);
        ds.saveGame(cooltestgame);

        System.out.println("Warning: Requires Manual Check");
        System.out.println("Values Should Be: " +
                "\n      title = 'Test-zx the Game'" +
                "\n  developer = 'Ted'" +
                "\ndescription = 'cool robot game'" +
                "\n     status = 'ACCEPTED'");

        // Handles Games with Multiple Developers


        // Can't give a null game
        assertThrows(IllegalArgumentException.class, () -> ds.saveGame(null));
    }
}
