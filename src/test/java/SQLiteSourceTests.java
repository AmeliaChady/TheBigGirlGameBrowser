import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteSourceTests {
    public static String CORRECT_PATH = "/home/amelia/Documents/Coding/TheBigGirlGameBrowser/testin.db";

    @Test
    public void SQLiteSourceConstructor() throws SQLException { // also
        try {
            SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        }catch (Exception e){
            fail("Threw Exception when it shouldn't");
        }

        // Tests for illegal paths
        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource("/looks/like/path"));
        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource("fuck confusing tests"));
        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource(""));
    }

    @Test
    public void SQLiteSourceSaveGame() throws SQLException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.DataSourceSaveGameTest(s);
    }
}
