import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SQLiteSource implements DataSource{
    String path;
    Connection conn;

    /***
     * Creates a datasource based off a SQLite Database
     * @param path path to sqlite database
     * @throws IllegalArgumentException if path DNE
     */
    SQLiteSource(String path) throws IllegalArgumentException{
        if(path == null || path.equalsIgnoreCase(""))
            throw new IllegalArgumentException("Incorrect Path");
        //System.out.println(Files.exists(Paths.get(path)));
        if (!Files.exists(Paths.get(path)))
            throw new IllegalArgumentException("Incorrect Path");

        this.path = "jdbc:sqlite:" + path;
        try {
            conn = DriverManager.getConnection(this.path);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect Path");
        }
    }

    @Override
    public void saveGame(Game game) throws IllegalArgumentException, DataSourceException{
        if(game == null){
            throw new IllegalArgumentException("Game is null");
        }

        try{
            // Set up statement
            Statement s = conn.createStatement();
            s.execute("BEGIN TRANSACTION;");

            safeUpsertGame(game, s);

            // Games ID
            String sql = "SELECT gid FROM Games WHERE title=\""+game.getTitle()+"\";";
            s.execute(sql);
            int gid = s.getResultSet().getInt(1);

            // Developers Set Up
            Iterator<String> devs = game.getDevelopers().iterator();
            int did;

            // Dev Iterator
            while (devs.hasNext()) {
                // Developer Set Up
                String d = devs.next();
                safeUpsertDevelopers(d, s);

                // Getting Developer ID
                sql = "SELECT did FROM Developers WHERE name=\"" + d + "\";";
                s.execute(sql);
                did = s.getResultSet().getInt(1);

                // Connect to Game
                safeUpsertGameDevelopers(gid, did, s);
            }


            // Finalize
            s.execute("COMMIT;");
            s.close();
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }

    }

    /**
     * THIS FUNCTIONS ASSUMING THAT GAMES IN LIST HAVE ALREADY BEEN SAVED
     * @param gameList the gameList to save
     * @throws IllegalArgumentException
     * @throws DataSourceException
     */
    @Override
    public void saveGameList(GameList gameList) throws IllegalArgumentException, DataSourceException{
        if(gameList == null){
            throw new IllegalArgumentException("GameList is null");
        }

        try{
            // Set up statement
            Statement s = conn.createStatement();
            s.execute("BEGIN TRANSACTION;");

            safeUpsertGameList(gameList, s);

            // GamesList ID
            String sql = "SELECT glid FROM GameLists WHERE title=\""+gameList.getName()+"\";";
            s.execute(sql);
            int glid = s.getResultSet().getInt(1);

            // Games Set Up
            Iterator<String> games = gameList.getGames().iterator();
            int gid;

            // Game Iterator
            while (games.hasNext()) {
                // Developer Set Up
                String g = games.next();

                // Getting Developer ID
                sql = "SELECT gid FROM Games WHERE name=\"" + g + "\";";
                s.execute(sql);
                gid = s.getResultSet().getInt(1);

                // Connect to Game
                safeUpsertGameListGames(glid, gid, s);
            }

            // Finalize
            s.execute("COMMIT;");
            s.close();
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }

    }

    @Override
    public Game loadGame(String title) throws DataSourceException {
        if(title==null){
            return null;
        }

        try {
            Statement s = conn.createStatement();
            String sql = "placeholder";



            s.close();
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }






        return null;
    }

    // underlying DB calls
    // Upsert -> Insert/Update depending on existence
    private void safeUpsertGame(Game game, Statement s) throws DataSourceException{
        // Get Game Status ID
        String sql = "SELECT gsid FROM GameStatuses WHERE "
                + "status = \"" + game.getStatus() + "\"";
        int gsid;
        try {
            s.execute(sql);
            gsid = s.getResultSet().getInt(1);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }


        // Replace Into Game Table
        try {
            sql = "SELECT * FROM Games WHERE title=\""+game.getTitle()+"\";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if (exists){
                sql = "UPDATE Games SET " +
                        "description=\"" + game.getDescription() + "\", " +
                        "gsid=" + gsid +
                        " WHERE title=\""+ game.getTitle() + "\";";
            }
            else {
                sql = "INSERT INTO Games(title, description, gsid) VALUES(" +
                        "\"" + game.getTitle() + "\", " +
                        "\"" + game.getDescription() + "\", " +
                        gsid + ");";
            }
            s.execute(sql);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }
    private void safeUpsertDevelopers(String d, Statement s) throws DataSourceException{
        try {
            String sql = "SELECT * FROM Developers WHERE name =\""+ d +"\";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if (!exists){
                sql = "INSERT INTO Developers(name) VALUES(" +
                        "\"" + d + "\");";
                s.execute(sql);
            }
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }
    private void safeUpsertGameDevelopers(int gid, int did, Statement s) throws DataSourceException{
        try {
            String sql = "SELECT * FROM GameDevelopers WHERE" +
                    " gid=" + gid +
                    " AND did=" + did + ";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if (!exists){
                sql = "INSERT INTO GameDevelopers VALUES ("+gid+", "+did+");";
                s.execute(sql);
            }

        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }

    private void safeUpsertGameList(GameList gameList, Statement s) throws DataSourceException{
        // Get gameList Name
        try{
            String sql = "SELECT * FROM GameLists WHERE name=\""+gameList.getName()+"\";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if (!exists){
                sql = "INSERT INTO GameLists(name) VALUES(" +
                        "\"" + gameList.getName()+ "\");";
            }
            s.execute(sql);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }

    private void safeUpsertGameListGames(int glid, int gid, Statement s) throws DataSourceException{
        try{
            String sql = "SELECT * FROM GameListGames WHERE" +
                    " glid=" + glid +
                    " AND gid=" + gid + ";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if(!exists){
                sql = "INSERT INTO GameListGames VALUES ("+glid+", "+gid+");";
                s.execute(sql);
            }

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
