import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteSourceTests {
    public static String CORRECT_PATH = "src/databases/testing.db";
/***
    @Test
    public void SQLiteSourceRunSQL() throws IOException {
        System.out.println("Requires Manual Check");
        // Correct Paths
        SQLiteSource.RunSQL("src/databases/sqlscriptrunningtest.db", "src/scripts/DDL.sql");
        SQLiteSource.RunSQL("src/databases/sqlscriptrunningtest.db", "src/scripts/sqlscriptrunningtest.sql");

        System.out.println("Check that sqlscriptrunningtest.db has correct schema and has one entry (woofframe) in the Games table.");

        // Incorrect DB Path
        assertThrows(IllegalArgumentException.class, () -> SQLiteSource.RunSQL("no", "src/scripts/sqlscriptrunningtest.sql"));
        assertThrows(IllegalArgumentException.class, () -> SQLiteSource.RunSQL(null, "src/scripts/sqlscriptrunningtest.sql"));

        // Incorrect SQL Path
        assertThrows(IllegalArgumentException.class, () -> SQLiteSource.RunSQL("src/databases/sqlscriptrunningtest.db", "no"));
        assertThrows(IllegalArgumentException.class, () -> SQLiteSource.RunSQL("src/databases/sqlscriptrunningtest.db", null));

    }
**/
    @Test
    public void SQLiteSourceConstructor() throws SQLException { // also
        try {
            SQLiteSource s = new SQLiteSource(CORRECT_PATH);
            s.close();
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
        s.close();
    }

    @Test
    public void SQLiteSourceLoadGame() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceLoadGameTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceSaveGameList() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceSaveGameListTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLoadGameList() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceLoadGameListTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceSaveDeveloper() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceSaveDevelopersTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLoadDeveloper() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceLoadDevelopersTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLoadDeveloperList() throws SQLException, DataSourceException{
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceLoadDeveloperListTest(s);
        s.close();
    }
}
