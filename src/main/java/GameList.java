import java.util.ArrayList;
import java.util.List;

public class GameList {
    private String name;
    private List<Game> gameList;

    /**
     * Default constructor
     * @param name - name of the game list
     * @throws IllegalArgumentException if name is invalid
     */
    public GameList(String name) throws IllegalArgumentException {
        if (name.length() == 0)
            throw new IllegalArgumentException("Please provide a name for this game list");
        this.name = name;
        this.gameList = new ArrayList<Game>();
    }

    /**
     * Add a new game
     * @param game - a new game
     */
    public void includeGame(Game game) {
        gameList.add(game);
    }

    /**
     * Remove a game
     * @param title - title of the game to remove
     * @return Game - the game removed
     */
    public Game removeGame(String title) {
        Game gameFound = null;

        for (Game game : gameList) {
            if (game.getTitle().equals(title)) {
                gameFound = game;
                break;
            }
        }
        if (gameFound != null) gameList.remove(gameFound);

        return gameFound;
    }

    /**
     * Find a game in this list
     * @param title - title of desired game
     * @return Game - Desired game in list
     */
    public Game getGame(String title) {
        Game gameFound = null;

        for (Game game : gameList) {
            if (game.getTitle().equals(title)) {
                gameFound = game;
                break;
            }
        }
        return gameFound;
    }

    public List<String> getGameTitles(){
        ArrayList<String> titles = new ArrayList<String>();
        for (Game game : gameList){
            titles.add(game.getTitle());
        }
        return titles;
    }

    public void displayAllGames(){
        System.out.println(name + ":\n");

        if (getGameCount()==0){
            System.out.println("This list is empty");
        }
        else {
            for (int i = 0; i < getGameCount(); i++) {
                gameList.get(i).displayGame();
            }
        }
    }

    public void displayListNameAndGameTitles() {
        String display = name + ": ";
        if(gameList.size()<1){
            display += "This list is empty";
        }
        else{
            for (int i = 0; i < getGameCount() - 1; i++) {
                display += gameList.get(i).getTitle() + ", ";
            }
            display += gameList.get(gameList.size() - 1).getTitle();
        }
        System.out.println(display);
    }


    public void displayGamesGivenStatus(Status status) {
        System.out.println(name + "(" + status +"):\n");

        if (getGameCount()==0){
            System.out.println("This list is empty");
        }
        else {
            for (int i = 0; i < getGameCount(); i++) {
                if (gameList.get(i).getStatus()==status) {
                    gameList.get(i).displayGame();
                }
            }
        }
    }

    // ------GETTERS------
    public String getName() { return name; }

    public List<Game> getGames() { return gameList; }

    public int getGameCount() { return gameList.size(); }
}
