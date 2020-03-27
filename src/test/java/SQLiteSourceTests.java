import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SQLiteSourceTests {
    public static String CORRECT_PATH = "I'll be honest I don't know yet";

    @Test
    public void SQLiteSourceConstructor(){
        try {
            SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        }catch (Exception e){
            fail("Threw Exception when it shouldn't");
        }

        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource("fuck confusing tests"));
        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource(""));
    }

    @Test
    public void SQLiteSourceSaveGame(){
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.DataSourceSaveGameTest(s);
    }
}
