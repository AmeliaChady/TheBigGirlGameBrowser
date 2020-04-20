import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteSourceTests {
    public static String DB_BASE_PATH = "src/databases/Test_SQLiteSource/";
    public static String SCRIPT_BASE_PATH = "src/scripts/";

    @Test
    public void SQLiteSourceRunSQL() throws IOException {
        System.out.println("Requires Manual Check");
        // Correct Paths
        SQLiteSource.RunSQL(DB_BASE_PATH + "RunSQL.db", SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(DB_BASE_PATH + "RunSQL.db", SCRIPT_BASE_PATH + "Test_SQLiteSource/RunSQL.sql");

        System.out.println("Check that RunSQL.db has correct schema and has one entry (woofframe) in the Games table.");

        // Incorrect DB Path
        assertThrows(IllegalArgumentException.class,
                () -> SQLiteSource.RunSQL("no", SCRIPT_BASE_PATH + "Test_SQLiteSource/RunSQL.sql"));
        assertThrows(IllegalArgumentException.class,
                () -> SQLiteSource.RunSQL(null, SCRIPT_BASE_PATH + "Test_SQLiteSource/RunSQL.sql"));

        // Incorrect SQL Path
        assertThrows(IllegalArgumentException.class,
                () -> SQLiteSource.RunSQL(DB_BASE_PATH + "Test_SQLiteSource/RunSQL.db", "no"));
        assertThrows(IllegalArgumentException.class,
                () -> SQLiteSource.RunSQL(DB_BASE_PATH + "Test_SQLiteSource/RunSQL.db", null));

    }

    @Test
    public void SQLiteSourceConstructor() { // also
        try {
            SQLiteSource s = new SQLiteSource(DB_BASE_PATH + "RunSQL.db");
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
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "SaveGame.db",
                SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "SaveGame.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/SaveGame.sql");
        SQLiteSource s = new SQLiteSource(DB_BASE_PATH+ "SaveGame.db");
        DataSourceTest.dataSourceSaveGameTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLoadGame() throws IOException, DataSourceException{
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "LoadGame.db",
                SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "LoadGame.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/LoadGame.sql");
        SQLiteSource s = new SQLiteSource(DB_BASE_PATH+ "LoadGame.db");
        DataSourceTest.dataSourceLoadGameTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceSaveGameList() throws IOException, DataSourceException{
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "SaveGameList.db",
                SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "SaveGameList.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/SaveGameList.sql");
        SQLiteSource s = new SQLiteSource(DB_BASE_PATH+"SaveGameList.db");
        DataSourceTest.dataSourceSaveGameListTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLoadGameList() throws IOException, DataSourceException{
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "LoadGameList.db",
                SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "LoadGameList.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/LoadGameList.sql");
        SQLiteSource s = new SQLiteSource(DB_BASE_PATH+"LoadGameList.db");
        DataSourceTest.dataSourceLoadGameListTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceSaveDeveloper() throws IOException, DataSourceException{
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "SaveDevelopers.db",
                SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "SaveDevelopers.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/SaveDeveloper.sql");
        SQLiteSource s = new SQLiteSource(DB_BASE_PATH+"SaveDevelopers.db");
        DataSourceTest.dataSourceSaveDevelopersTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLoadDeveloper() throws IOException , DataSourceException{
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "LoadDevelopers.db",
                SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "LoadDevelopers.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/LoadDeveloper.sql");
        SQLiteSource s = new SQLiteSource(DB_BASE_PATH + "LoadDevelopers.db");
        DataSourceTest.dataSourceLoadDevelopersTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLoadDeveloperList() throws IOException, DataSourceException{
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "LoadDeveloperList.db",
                SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "LoadDeveloperList.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/LoadDeveloperList.sql");
        SQLiteSource s = new SQLiteSource(DB_BASE_PATH+"LoadDeveloperList.db");
        DataSourceTest.dataSourceLoadDeveloperListTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceLogin() throws IOException, DataSourceException{
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "Login.db",
                SCRIPT_BASE_PATH + "DDL.sql");
        SQLiteSource.RunSQL(
                DB_BASE_PATH + "Login.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/Login.sql");
        SQLiteSource s = new SQLiteSource(DB_BASE_PATH+"Login.db");
        DataSourceTest.dataSourceLoginTest(s);
        s.close();
    }

    @Test
    public void SQLiteSourceSaveUser() throws IOException, DataSourceException {
        SQLiteSource.RunSQL(
            DB_BASE_PATH + "SaveUser.db",
            SCRIPT_BASE_PATH + "DDL.sql"
        );

        SQLiteSource.RunSQL(
                DB_BASE_PATH + "SaveUser.db",
                SCRIPT_BASE_PATH + "Test_SQLiteSource/SaveUser.sql"
        );

        SQLiteSource s = new SQLiteSource(DB_BASE_PATH+ "SaveUser.db");
        DataSourceTest.dataSourceSaveUserTest(s);
        s.close();
    }
}
