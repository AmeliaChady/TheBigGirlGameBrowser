import javax.xml.crypto.Data;
import java.io.IOException;
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

    @Override
    public void close(){
        try {
            if (this.isConnected())
                conn.close();
                inTransaction = false;
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
            // TODO: Hard Coded not accounting closed.
            int gid = s.getResultSet().getInt(1);

            // Developers Set Up
            Iterator<String> devs = game.getDevelopers().iterator();
            int did;

            // Dev Iterator
            while (devs.hasNext()) {
                // Developer Set Up
                String d = devs.next();
                //saveDeveloper(d);

                //safeUpsertDevelopers(d.getName(), s);

                // Getting Developer ID
                sql = "SELECT did FROM Developers WHERE name=\"" + d + "\";";
                s.execute(sql);
                // TODO: Hard Coded not accounting closed.
                did = s.getResultSet().getInt(1);

                // Connect Game to Dev
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
                if(lg!=null) {
                    conn.releaseSavepoint(lg);
                    inTransaction = false;
                }
                s.close();
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
            ArrayList<String> devs = new ArrayList<String>();
            ResultSet d = s.getResultSet();
            boolean hasNext = !d.isClosed();
            if(hasNext){
                d.next();
                while (hasNext){
                    devs.add(d.getString("name"));
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

            purgeGameListGames(gameList, s);

            // GamesList ID
            String sql = "SELECT glid FROM GameLists WHERE name=\""+gameList.getName()+"\";";
            s.execute(sql);
            int glid = s.getResultSet().getInt(1);

            // Games Set Up
            Iterator<String> gameTitles = gameList.getGames().iterator();
            int gid;

            // Game Iterator
            while (gameTitles.hasNext()) {
                // Game Set Up
                String gameTitle = gameTitles.next();
                Game game;
                if (gameTitle != null) {
                    game = loadGame(gameTitle);
                    saveGame(game);

                    // Getting Game ID
                    sql = "SELECT gid FROM Games WHERE title=\"" + game.getTitle() + "\";";
                    s.execute(sql);
                    gid = s.getResultSet().getInt(1);

                    // Connect gameList to Game
                    safeUpsertGameListsGames(glid, gid, s);
                }
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
    public GameList loadGameList(String gameListName) throws DataSourceException {
        if (gameListName == null) return null;

        Savepoint lgl = null, lgts  = null;
        try {
            if(!inTransaction) {
                lgl = conn.setSavepoint();
                inTransaction = true;
            }
            Statement s = conn.createStatement();

            // See If Saved
            String sql = "SELECT * FROM GameLists WHERE name=\""+gameListName+"\"; ";
            s.execute(sql);

            ResultSet nameResult = s.getResultSet();
            if(!nameResult.next()) {
                if(lgl!=null) {
                    conn.releaseSavepoint(lgl);
                    inTransaction = false;
                }
                s.close();
                return null;
            }

            if (!inTransaction) {
                lgts = conn.setSavepoint();
                inTransaction = true;
            }

            // Get game titles
            sql = "SELECT title FROM GameListsGames INNER JOIN GameLists USING(glid) " +
                    "INNER JOIN Games USING(gid) WHERE name="+"\""+gameListName+"\";";
            s.execute(sql);

            ResultSet titlesResult = s.getResultSet();
            List<String> gameTitles = new ArrayList<String>();
            boolean hasNext = !titlesResult.isClosed();

            if (hasNext) {
                titlesResult.next();
                while (hasNext) {
                    gameTitles.add( titlesResult.getString("title"));
                    hasNext = titlesResult.next();
                }
            }
            s.close();
            if(lgts != null){
                conn.commit();
                inTransaction = false;
            }
            return new GameList(gameListName, gameTitles);

        } catch(SQLException e) {
            try {
                if (lgts != null) {
                    conn.rollback(lgts);
                    conn.releaseSavepoint(lgts);
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

            String sql = "SELECT * FROM Developers WHERE name =\""+ dev.getName() +"\";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            //This stops infinite loop while capturing all necessary information
            /*if (exists){
                GameList devsGames = dev.getGameList();
                saveGameList(devsGames);
                s.close();
                if(sd != null){
                    conn.commit();
                    inTransaction = false;
                }
                return;
            }*/
            if(!exists)
                safeUpsertDevelopers(dev, s);
            // TODO PURGE GAME CONNECTIONS?

            // Games Set Up
            saveGameList(dev.getGameList());
            Iterator<String> games = dev.getGameList().getGames().iterator();
            int did = getDid(dev.getName(), s);
            while (games.hasNext()){
                String game = games.next();
                int gid = getGid(game, s);
                safeUpsertGameDevelopers(gid, did, s);
            }

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
            String sql = "SELECT glid FROM Developers WHERE name=\""+dev+"\";";
            s.execute(sql);
            ResultSet rs = s.getResultSet();
            if(!rs.next()){
                if(ld!=null) {
                    conn.releaseSavepoint(ld);
                    inTransaction = false;
                }
                s.close();
                return null;
            }

            // Fill GameList
            GameList g = loadGameList(getGameListName(rs.getInt("glid"), s));

            // Return Dev Object
            s.close();
            if(ld != null){
                conn.commit();
                inTransaction = false;
            }
            return new Developer(dev, g, -1);
            //return new Developer(dev);
        }catch (SQLException e){
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

    @Override
    public List<String> loadDeveloperList() throws DataSourceException{
        Savepoint lds = null;
        try{
            // Setup
            if(!inTransaction) {
                lds = conn.setSavepoint();
                inTransaction = true;
            }
            Statement s = conn.createStatement();

            // Get developers name
            String sql = "SELECT name FROM Developers";
            s.execute(sql);

            ResultSet devNames = s.getResultSet();
            List<String> developers = new ArrayList<>();
            boolean hasNext = !devNames.isClosed();

            if (hasNext) {
                devNames.next();
                while (hasNext) {
                    developers.add(devNames.getString("name"));
                    hasNext = devNames.next();
                }
            }
            s.close();
            if(lds != null){
                conn.commit();
                inTransaction = false;
            }
            return developers;
        }catch (SQLException e){
            try {
                if(lds != null) {
                    conn.rollback(lds);
                    conn.releaseSavepoint(lds);
                    inTransaction = false;
                }
            }catch (SQLException ignored){}
            throw new DataSourceException(e.getMessage());
        }
    }

    public void setInTransaction(boolean bool){
        inTransaction = bool;
    }

    @Override
    public void removeDeveloper(Developer developer) throws DataSourceException{
        if(developer == null){
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

            int did = getDid(developer, s);
            GameList devsGL = loadGameList(developer.getGameListName());
            int glid = getGlid(devsGL, s);

            // remove DevsGamelist from Gamelists
            String sql = "DELETE FROM GameListsGames WHERE glid="+glid+";";
            s.execute(sql);

            // remove DevsGameList from DevelopersGameLists
            sql = "DELETE FROM DevelopersGameLists WHERE glid="+glid+";";
            s.execute(sql);

            // remove Developer from Developers
            sql = "DELETE FROM Developers WHERE did="+did+";";
            s.execute(sql);

            // TODO: We need to think about what happens to games whos devs are deleted
            // maybe we have an error handler that says something like, the dev of this game
            //      no longer has an account with us.

        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }

    // underlying DB calls
    // Upsert -> Insert/Update depending on existence
    private void safeUpsertGame(Game game, Statement s) throws DataSourceException{
        if(game==null)
            return;
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
    private void safeUpsertDevelopers(Developer d, Statement s) throws DataSourceException{
        safeUpsertGameList(d.getGameListName(), s);
        try {
            String sql = "SELECT * FROM Developers WHERE name =\""+ d +"\";";
            s.execute(sql);
            boolean exists = !s.getResultSet().isClosed();

            if (!exists){
                sql = "INSERT INTO Developers(aid, name, glid) VALUES(" +
                        "\"" + d.getAid() + "\", " +
                        "\"" + d.getName() + "\", " +
                        "\"" + getGlid(d.getGameListName(), s) + "\");";
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
        safeUpsertGameList(gameList.getName(), s);
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

    private int getGlid(GameList list, Statement s) throws DataSourceException{
        return getGlid(list.getName(), s);
    }
    private int getGlid(String listName, Statement s) throws DataSourceException{
        try {
            String sql = "SELECT glid FROM GameLists WHERE name=\""+listName+"\";";
            s.execute(sql);
            return s.getResultSet().getInt(1);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }
    private String getGameListName(int glid, Statement s) throws SQLException{
        String sql = "SELECT name FROM GameLists WHERE glid="+glid+";";
        s.execute(sql);
        ResultSet rs = s.getResultSet();
        if(!rs.next())
            return null;
        return rs.getString("name");
    }

    private int getGid(String game, Statement s) throws DataSourceException, IllegalArgumentException{
        if(game==null){
            throw new IllegalArgumentException("game is null");
        }
        try {
            String sql = "SELECT gid FROM Games WHERE title=\""+game+"\";";
            s.execute(sql);
            return s.getResultSet().getInt(1);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }

    private int getDid(Developer developer, Statement s) throws DataSourceException{
        return getDid(developer.getName(), s);
    }
    private int getDid(String devname, Statement s) throws DataSourceException{
        try {
            String sql = "SELECT did FROM Developers WHERE name=\""+devname+"\";";
            s.execute(sql);
            return s.getResultSet().getInt(1);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }

    private void purgeGameListGames(GameList gl, Statement s) throws DataSourceException{
        int glid = getGlid(gl, s);

        try {
            String sql = "DELETE FROM GameListsGames WHERE glid="+glid+";";
            s.execute(sql);
        }catch (SQLException e){
            throw new DataSourceException(e.getMessage());
        }
    }

    public static void RunSQL(String databasePath, String sqlPath) throws IOException{
        String absPath = System.getProperty("user.dir");

        if (databasePath == null || databasePath.equalsIgnoreCase(""))
            throw new IllegalArgumentException("Incorrect Database Path");
        if (Files.notExists(Paths.get(absPath+"/"+databasePath)))
            throw new IllegalArgumentException("Incorrect Database Path");
        if (sqlPath == null || sqlPath.equalsIgnoreCase(""))
            throw new IllegalArgumentException("Incorrect SQL Path");
        if (Files.notExists(Paths.get(absPath+"/"+sqlPath)))
            throw new IllegalArgumentException("Incorrect SQL Path");

        System.out.println(absPath);
        ProcessBuilder pb = new ProcessBuilder("sqlite3", absPath+"/"+databasePath, ".read " + absPath+"/"+sqlPath + "");
        Process pr = pb.start();
        System.out.println(new String(pr.getErrorStream().readAllBytes()));
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
