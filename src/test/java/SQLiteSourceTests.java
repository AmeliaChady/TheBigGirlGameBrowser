import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteSourceTests {
    public static String CORRECT_PATH = "src/databases/Test_SQLiteSource.db";
    public static String SQL_BASE_PATH = "src/scripts/";

    @BeforeAll
    public static void setUpDB() throws IOException{
        System.out.println("SQLiteSourceRunSQL must work for all tests to work!");
        SQLiteSource.RunSQL(CORRECT_PATH, SQL_BASE_PATH+"DDL.sql");
    }

    @Test
    public void SQLiteSourceRunSQL() throws IOException {
        System.out.println("Requires Manual Check (Drop All Tables in DB Before Running)");
        // Correct Paths
        SQLiteSource.RunSQL("src/databases/Test_RunSQL.db", "src/scripts/DDL.sql");
        SQLiteSource.RunSQL("src/databases/Test_RunSQL.db", "src/scripts/Test_SQLiteSource_RunSQLScript.sql");

        System.out.println("Check that Test_RunSQL.db has correct schema and has one entry (woofframe) in the Games table.");

        // Incorrect DB Path
        assertThrows(IllegalArgumentException.class, () -> SQLiteSource.RunSQL("no", "src/scripts/Test_SQLiteSource_RunSQLScript.sql"));
        assertThrows(IllegalArgumentException.class, () -> SQLiteSource.RunSQL(null, "src/scripts/Test_SQLiteSource_RunSQLScript.sql"));

        // Incorrect SQL Path
        assertThrows(IllegalArgumentException.class, () -> SQLiteSource.RunSQL("src/databases/Test_RunSQL.db", "no"));
        assertThrows(IllegalArgumentException.class, () -> SQLiteSource.RunSQL("src/databases/Test_RunSQL.db", null));

    }

    @Test
    public void SQLiteSourceConstructor() { // also
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
    public void SQLiteSourceSaveGame() throws IOException, DataSourceException{
        SQLiteSource.RunSQL(CORRECT_PATH, SQL_BASE_PATH+"Test_SQLiteSource_SaveGame.sql");
        SQLiteSource s = new SQLiteSource(CORRECT_PATH);
        DataSourceTest.dataSourceSaveGameTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLoadGame() throws IOException, DataSourceException{
        SQLiteSource.RunSQL(CORRECT_PATH, SQL_BASE_PATH+"Test_SQLiteSource_LoadGame.sql");
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
