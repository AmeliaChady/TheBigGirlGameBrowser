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
     * Changes a games status to 'accepted'
     * @param game - game to approve
     */
    public void reviewGame(Game game) { }

    // ------GETTERS------
    public String getUsername() { return username; }
}
