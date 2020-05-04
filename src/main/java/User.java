import java.util.List;

public class User {

    private String name;
    private GameList ownedGames;
    private GameList wishList;
    private List<String> reviews;
    private List<String> comments;

    public User(String nameIn) { name = nameIn; }

    public User(String nameIn, GameList ownedGamesIn, GameList wishListIn){
        name = nameIn;
        ownedGames = ownedGamesIn;
        wishList = wishListIn;
    }

    public User(String nameIn, GameList ownedGamesIn, GameList wishListIn, List<String> reviewsIn, List<String> commentsIn){
        name = nameIn;
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

    public String getName() { return name; }

    //end getters

}
