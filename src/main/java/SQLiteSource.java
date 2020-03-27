import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;

public class SQLiteSource implements DataSource{
    String path;
    Connection conn;

    /***
     * Creates a datasource based off a SQLite Database
     * @param path path to sqlite database
     * @throws IllegalArgumentException if path DNE
     */
    SQLiteSource(String path) throws IllegalArgumentException, SQLException{
        if(path == null || path.equalsIgnoreCase(""))
            throw new IllegalArgumentException("Incorrect Path");
        try {
            Paths.get(path);
        }catch (Exception e){
            throw new IllegalArgumentException("Incorrect Path");
        }

        this.path = "jdbc:sqlite:" + path;
        try {
            conn = DriverManager.getConnection(this.path);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Incorrect Path");
        }
    }

    @Override
    public void saveGame(Game game) throws IllegalArgumentException, DataSourceException{
        if(game == null){
            throw new IllegalArgumentException("Game is null");
        }
        try {
            String sql = "INSERT INTO Games(title, developer, description, status) VALUES(" +
                    "\"" + game.getTitle() + "\", " +
                    "\"" + game.getDeveloper().getName() + "\", " +
                    "\"" + game.getDescription() + "\", " +
                    "\"" + game.getStatus() + "\");";
            Statement s = conn.createStatement();
            System.out.println(sql);
            s.execute(sql);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }

    /***
     * @return connection state of sqlite db
     */
    public boolean isConnected() {
        try {
            return !conn.isClosed();
        }catch (SQLException e){
            return false;
        }
    }
}
