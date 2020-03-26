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
            if (game.getTitle() == title) {
                gameFound = game;
                break;
            }
        }
        if (gameFound != null) gameList.remove(gameFound);

        return gameFound;
    }

    // ------GETTERS------
    public String getName() { return name; }

    public int getGameCount() { return gameList.size(); }
}
