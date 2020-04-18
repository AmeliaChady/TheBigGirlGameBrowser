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

    public void addToWishList(Game gameToAdd){
        wishList.includeGame(gameToAdd);
    }

    public void removeFromWishList(Game gameToRemove){
        wishList.removeGame(gameToRemove.getTitle());
    }

    public void addToOwnedGames(Game gameToAdd){
        ownedGames.includeGame(gameToAdd);
        gameToAdd.increaseOwnedCount();

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
