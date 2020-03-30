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
    public void reviewGame(Game game) {
        game.changeStatus(Status.ACCEPTED);
    }

    /**
     * Changes a games status to 'Rejected' if 'Pending'
     * Changes a games status to 'Limbo' if already 'Accepted'
     * @param game - game to reject
     */
    public void rejectGame(Game game) {
        if (game.getStatus() == Status.PENDING) {
            game.changeStatus(Status.REJECTED);
        }
        else if (game.getStatus() == Status.ACCEPTED) {
            game.changeStatus(Status.LIMBO);
        }
    }

    // ------GETTERS------
    public String getUsername() { return username; }
}
