import java.util.ArrayList;
import java.util.List;

public class GameList {
    private String name;
    private List<String> gameList;

    /**
     * Default constructor
     * @param name - name of the game list
     * @throws IllegalArgumentException if name is invalid
     */
    public GameList(String name) throws IllegalArgumentException {
        if (name.length() == 0)
            throw new IllegalArgumentException("Please provide a name for this game list");
        this.name = name;
        this.gameList = new ArrayList<String>();
    }

    /**
     * Add a new game
     * @param game - a new game
     */
    public void includeGame(String game) {
        gameList.add(game);
    }

    /**
     * Remove a game
     * @param title - title of the game to remove
     * @return Game - the game removed
     */
    public String removeGame(String title) {
        String gameFound = null;

        for (String game : gameList) {
            if (game.equals(title)) {
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
    public String getGame(String title) {
        String gameFound = null;

        for (String game : gameList) {
            if (game.equals(title)) {
                gameFound = game;
                break;
            }
        }
        return gameFound;
    }

    public void displayAllGames(){
        System.out.println(name+":");

        if (getGameCount()==0){
            System.out.println("\nThis list is empty");
        }
        else {
            for (int i = 0; i < getGameCount(); i++) {
                System.out.println(gameList.get(i));
            }
        }
    }

    // ------GETTERS------
    public String getName() { return name; }

    public int getGameCount() { return gameList.size(); }

    public List<String> getGames() {
        return gameList;
    }
}
