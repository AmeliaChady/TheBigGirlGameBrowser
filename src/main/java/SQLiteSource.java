import javax.xml.crypto.Data;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class SQLiteSource implements DataSource{
    String path;
    Connection conn;
    boolean inTransaction;

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
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Incorrect Path");
        }
        inTransaction = false;

    }

    void close(){
        try {
            if (this.isConnected())
                conn.close();
        }catch (SQLException ignored){}
    }

    @Override
    public void saveGame(Game game) throws IllegalArgumentException, DataSourceException{
        if(game == null){
            throw new IllegalArgumentException("Game is null");
        }
        
        Savepoint sg = null;
        try{
            // Set up statement
            if(!inTransaction) {
                sg = conn.setSavepoint();
                inTransaction = true;
            }
            Statement s = conn.createStatement();

            safeUpsertGame(game, s);

            // Games ID
            String sql = "SELECT gid FROM Games WHERE title=\""+game.getTitle()+"\";";
            s.execute(sql);
            int gid = s.getResultSet().getInt(1);

            // Developers Set Up
            Iterator<String> devs = game.getDeveloperNames().iterator();
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

            s.close();
            if(sg != null){
                conn.commit();
                inTransaction = false;
            }
        }catch (SQLException | DataSourceException e){
            try {
                if(sg != null) {
                    conn.rollback(sg);
                    conn.releaseSavepoint(sg);
                    inTransaction = false;
                }
            }catch (SQLException ignored){}
            throw new DataSourceException(e.getMessage());
        }
    }

    @Override
    public Game loadGame(String title) throws DataSourceException {
        if(title==null){
            return null;
        }

        Savepoint lg = null;
        try {
            if(!inTransaction) {
                lg = conn.setSavepoint();
                inTransaction = true;
            }
            Statement s = conn.createStatement();

            String sql = "SELECT * FROM Games INNER JOIN GameStatuses USING(gsid) WHERE title='" + title + "';";
            boolean hasResults = s.execute(sql);

            ResultSet gameResult = s.getResultSet();
            if(!gameResult.next()){
                return null;
            }

            int gid = gameResult.getInt("gid");
            String description = gameResult.getString("description");
            String status = gameResult.getString("status");

            /*
            System.out.println("\ngid : "+ gid);
            System.out.println("title : " + title);
            System.out.println("description : " + description);
            System.out.println("status : " + status);
            */

            sql = "SELECT title, name " +
                    "FROM GameDevelopers GD " +
                    "INNER JOIN Games G on GD.gid = G.gid " +
                    "INNER JOIN Developers D on GD.did = D.did " +
                    "WHERE title = '"+title+"';";
            s.execute(sql);
            ArrayList<Developer> devs = new ArrayList<Developer>();
            ResultSet d = s.getResultSet();
            boolean hasNext = !d.isClosed();
            if(hasNext){
                d.next();
                while (hasNext){
                    devs.add(new Developer(d.getString("name")));
                    hasNext = d.next();
                }
            }

            s.close();
            if(lg != null){
                conn.commit();
                inTransaction = false;
            }
            return new Game(title,description,devs,Status.valueOf(status));
        }catch (SQLException e){
            try {
                if(lg != null) {
                    conn.rollback(lg);
                    conn.releaseSavepoint(lg);
                    inTransaction = false;
                }
            }catch (SQLException ignored){}
            throw new DataSourceException(e.getMessage());
        }
    }

    @Override
    public void saveGameList(GameList gameList) throws IllegalArgumentException, DataSourceException{
        if(gameList == null){
            throw new IllegalArgumentException("GameList is null");
        }

        Savepoint sgl = null;
        try{
            // Set up statement
            if(!inTransaction) {
                sgl = conn.setSavepoint();
                inTransaction = true;
            }
            Statement s = conn.createStatement();

            safeUpsertGameList(gameList, s);

            // GamesList ID
            String sql = "SELECT glid FROM GameLists WHERE name=\""+gameList.getName()+"\";";
            s.execute(sql);
            int glid = s.getResultSet().getInt(1);

            // Games Set Up
            Iterator<Game> games = gameList.getGames().iterator();
            int gid;

            // Game Iterator
            while (games.hasNext()) {
                // Game Set Up
                Game g = games.next();
                saveGame(g);

                // Getting Game ID
                sql = "SELECT gid FROM Games WHERE title=\"" + g.getTitle() + "\";";
                s.execute(sql);
                gid = s.getResultSet().getInt(1);

                // Connect to Game
                safeUpsertGameListsGames(glid, gid, s);
            }

            // Finalize

            s.close();
            if(sgl != null){
                conn.commit();
                inTransaction = false;
            }
        }catch (SQLException | DataSourceException e){
            try {
                if(sgl != null) {
                    conn.rollback(sgl);
                    conn.releaseSavepoint(sgl);
                    inTransaction = false;
                }
            }catch (SQLException ignored){}
            throw new DataSourceException(e.getMessage());
        }

    }

    @Override
    public GameList loadGameList(String name) throws DataSourceException{
        if(name==null){
            return null;
        }

        Savepoint lgl = null;
        try {
            if(!inTransaction) {
                lgl = conn.setSavepoint();
                inTransaction = true;
            }
            Statement s = conn.createStatement();

            // See If Saved
            String sql = "SELECT * FROM GameLists WHERE name=\""+name+"\"; ";
            s.execute(sql);

            ResultSet nameResult = s.getResultSet();
            if(!nameResult.next()){
                return null;
            }

            // Instantiate GameList
            GameList gl = new GameList(name);

            // Obtain List of Game Names
            sql = "SELECT title " +
                    "FROM GameListsGames " +
                        "INNER JOIN GameLists USING(glid) " +
                        "INNER JOIN Games USING(gid) " +
                    "WHERE name=\""+name+"\"; ";
            s.execute(sql);

            // Load Each Game and Add
            ResultSet gameNames = s.getResultSet();
            boolean hasNext = !gameNames.isClosed();
            if(hasNext){
                gameNames.next();
                while (hasNext){
                    gl.includeGame(this.loadGame(gameNames.getString("title")));
                    hasNext = gameNames.next();
                }
            }

            // Finalize
            s.close();
            if(lgl != null){
                conn.commit();
                inTransaction = false;
            }
            return gl;
        }catch (SQLException | DataSourceException e){
            try {
                if(lgl != null) {
                    conn.rollback(lgl);
                    conn.releaseSavepoint(lgl);
                    inTransaction = false;
                }
            }catch (SQLException ignored){}
            throw new DataSourceException(e.getMessage());
        }
    }

    @Override
    public void saveDeveloper(Developer dev) throws IllegalArgumentException, DataSourceException{
        if(dev == null){
            throw new IllegalArgumentException("Developer is null");
        }

        Savepoint sd = null;
        try{
            // Set up statement
            if(!inTransaction) {
                sd = conn.setSavepoint();
                inTransaction = true;
            }
            Statement s = conn.createStatement();

            safeUpsertDevelopers(dev.getName(), s);

            // Games Set Up
            safeUpsertDevelopersGameLists(dev, s);

            // Finalize

            s.close();
            if(sd != null){
                conn.commit();
                inTransaction = false;
            }
        }catch (SQLException e){
            try {
                if(sd != null) {
                    conn.rollback(sd);
                    conn.releaseSavepoint(sd);
                    inTransaction = false;
                }
            }catch (SQLException ignored){}
            throw new DataSourceException(e.getMessage());
        }
    }

    @Override
    public Developer loadDeveloper(String dev) throws DataSourceException{
        if(dev == null){
            return null;
        }

        Savepoint ld = null;
        try{
            // Setup
            if(!inTransaction) {
                ld = conn.setSavepoint();
                inTransaction = true;
            }
            Statement s = conn.createStatement();

            // Get GameList name
            String sql = "SELECT listName FROM Developers WHERE name='"+dev+"';";
            s.execute(sql);
            ResultSet rs = s.getResultSet();
            if(!rs.next()){
                return null;
            }

            // Fill GameList
            GameList g = loadGameList(rs.getString("listName"));

            // Return Dev Object
            s.close();
            if(ld != null){
                conn.commit();
                inTransaction = false;
            }
            return new Developer(dev, g);
        }catch (SQLException | DataSourceException e){
            try {
                if(ld != null) {
                    conn.rollback(ld);
                    conn.releaseSavepoint(ld);
                    inTransaction = false;
                }
            }catch (SQLException ignored){}
            throw new DataSourceException(e.getMessage());
        }
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
        String devListName = d + "'s Games";
        safeUpsertGameList(devListName, s);
        try {
            String sql = "SELECT * FROM Developers WHERE name =\""+ d +"\";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if (!exists){
                sql = "INSERT INTO Developers(name, listName) VALUES(" +
                        "\"" + d + "\", " +
                        "\"" + devListName + "\");";
                s.execute(sql);
            }
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }
    private void safeUpsertDevelopersGameLists(Developer dev, Statement s) throws DataSourceException{
        try{
            String sql = "SELECT did FROM Developers WHERE name=\"" + dev.getName() + "\";";
            s.execute(sql);
            int did = s.getResultSet().getInt(1);

            sql = "SELECT glid FROM GameLists WHERE name=\"" + dev.getGameListName() + "\";";
            s.execute(sql);
            int glid = s.getResultSet().getInt(1);

            sql = "SELECT * FROM DevelopersGameLists WHERE" +
                    " did="+ did +
                    " AND glid="+ glid + ";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();
            if (!exists){
                sql = "INSERT INTO DevelopersGameLists VALUES ("+did+", "+glid+");";
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
                        "'" + gameList.getName()+ "');";
            }
            s.execute(sql);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }
    private void safeUpsertGameList(String listName, Statement s) throws DataSourceException{
        // Get gameList Name
        try{
            String sql = "SELECT * FROM GameLists WHERE name=\""+listName+"\";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if (!exists){
                sql = "INSERT INTO GameLists(name) VALUES(" +
                        "\"" + listName+ "\");";
            }
            s.execute(sql);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }
    private void safeUpsertGameListsGames(int glid, int gid, Statement s) throws DataSourceException{
        try{
            String sql = "SELECT * FROM GameListsGames WHERE" +
                    " glid=" + glid +
                    " AND gid=" + gid + ";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if(!exists){
                sql = "INSERT INTO GameListsGames VALUES ("+glid+", "+gid+");";
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
