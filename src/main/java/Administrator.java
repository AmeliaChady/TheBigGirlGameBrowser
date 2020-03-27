public class Administrator {
    private String username;

    /**
     * Default constructor
     * @param username - a username to identify the administrator
     * @throws IllegalArgumentException if username is invalid
     */
    public Administrator(String username) throws IllegalArgumentException {
        if (username.length() == 0)
            throw new IllegalArgumentException("Please provide a name for this game list");
        this.username = username;
    }

    /**
     * Add or a reject a game from a list
     * @param gameList - game list to add a game to
     * @param game - game to be reviewed
     */
    public void reviewGame(GameList gameList, Game game) {}

    // ------GETTERS------
    public String getUsername() { return username; }
}
