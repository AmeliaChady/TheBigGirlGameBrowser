import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteSourceTests {
    // TODO: make universal!!!
    public static String CORRECT_PATH = "testing.db";

    @Test
    public void SQLiteSourceConstructor() throws SQLException { // also
        try {
            SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        }catch (Exception e){
            fail("Threw Exception when it shouldn't");
        }

        // Tests for illegal paths
        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource("/looks/like/path"));
        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource(""));
        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource(null));
        assertThrows(IllegalArgumentException.class, () -> new SQLiteSource("fuck confusing tests"));
    }

    @Test
    public void SQLiteSourceSaveGame() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceSaveGameTest(s);
    }

    @Test
    public void SQLiteSourceLoadGame() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceLoadGameTest(s);
    }

    @Test
    public void SQLiteSourceSaveGameList() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceSaveGameListTest(s);
    }

    @Test
    public void SQLiteSourceLoadGameList() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceLoadGameListTest(s);
    }

    @Test
    public void SQLiteSourceSaveDeveloper() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceSaveDevelopersTest(s);
    }


}
