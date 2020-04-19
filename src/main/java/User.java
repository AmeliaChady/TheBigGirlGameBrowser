import java.util.List;

public class User {

    //private Account
    private GameList ownedGames;
    private GameList wishList;
    private List<String> reviews;
    private List<String> comments;


    public User(GameList ownedGamesIn, GameList wishListIn){
        ownedGames = ownedGamesIn;
        wishList = wishListIn;
    }

    public User(GameList ownedGamesIn, GameList wishListIn, List<String> reviewsIn, List<String> commentsIn){
        ownedGames = ownedGamesIn;
        wishList = wishListIn;
        reviews = reviewsIn;
        comments = commentsIn;
    }

    public void addToWishList(String gameToAdd){
        wishList.includeGame(gameToAdd);
    }

    public void removeFromWishList(String gameToRemove){
        wishList.removeGame(gameToRemove);
    }

    public void addToOwnedGames(String gameToAdd){
        ownedGames.includeGame(gameToAdd);
    }

    public String removeFromOwnedGames(String gameToRemove) {
        return ownedGames.removeGame(gameToRemove);
    }

    //WRITE REVIEW

    //WRITE COMMENT

    //GETTERS
    public GameList getOwnedGames(){
        return ownedGames;
    }

    public GameList getWishList(){
        return wishList;
    }

    public List<String> getReviews(){
        return reviews;
    }

    public List<String> getComments(){ return comments; }

    //end getters

}
