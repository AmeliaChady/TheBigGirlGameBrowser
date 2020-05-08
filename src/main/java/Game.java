import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

enum Status {PENDING, ACCEPTED, REJECTED, LIMBO}

public class Game {
    private String title;
    private String description;
    private List<String> developers;
    private List<Review> reviews;
    private Status status;
    private int ownedCount;
    private double averageRating = -1;

    /**
     * Default constructor
     */
    public Game(){
        title = "testGame";
        description = "This is a test to create a new game object";
        this.developers = new ArrayList<String>();
        status = Status.PENDING;
    }

    /**
     * THIS CONSTRUCTOR IS WHAT THE Loader SHOULD USE
     * @param title game title
     * @param description descriptor for game
     * @param developers link to developer object
     */
    public Game(String title, String description, List<Review> reviews, List<String> developers, Status status){
        this.title = title;
        this.description = description;
        this.developers = developers;
        this.reviews = reviews;
        this.status = status;
        loadAverageRating();
    }

    /**
     * THIS Constructor is for UI
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description, String developer) {
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<String>();
        developers.add(developer);
        this.reviews = new LinkedList<Review>();
        this.status = Status.PENDING;
        this.ownedCount = 0;

    }

    /**
     *  Full constructor
     *  THIS CONSTRUCTOR IS WHAT
     * @param title game title
     * @param description descriptor for game
     * @param developers list of developer names
     * @param status game status
     * @param ownedCount number of people who own game
     * @param reviews list of reviews
     */
    public Game(String title, String description,  List<String> developers, Status status, int ownedCount, List<Review> reviews){
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<String>();
        developers.addAll(developers);
        this.reviews = reviews;
        this.ownedCount = ownedCount;
        this.status = status;

    }

    /**
     * FullFull constructor (should only be used for backend tests)
     * @param title game title
     * @param description descriptor for game
     * @param developers link to developer object
     */
    public Game(String title, String description, List<String> developers, Status status) {
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<String>(developers);
        this.reviews = new LinkedList<Review>();
        this.status = status;
    }

    /**
     * FullFull constructor (should only be used for backend tests)
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description, String developer, Status status) {
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<String>();
        developers.add(developer);
        this.reviews =new LinkedList<Review>();
        this.status = status;
    }


    /**
     * Deprecated Constructor
     * @param title game title
     * @param description descriptor for game
     * @param developers link to developer object
     */
    public Game(String title, String description,  List<String> developers){
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<String>(developers);
        this.status = Status.PENDING;
    }

    /**
     * Game constructor for when you don't know the description yet
     * @param title game title
     * @param developers link to developer object
     */
    public Game(String title, List<String> developers){
        this.title = title;
        this.developers = new ArrayList<String>(developers);
        this.description = "No Description Given";
        this.status = Status.PENDING;
    }

    /**
     * Game constructor for when you don't know the description yet
    git * @param title game title
     * @param developer link to developer object
     */
    public Game(String title, String developer){
        this.title = title;
        this.developers = new ArrayList<String>();
        this.developers.add(developer);
        this.description = "No Description Given";
        this.status = Status.PENDING;
    }

    /**
     * Allows for description to be replaced
     * TODO maybe we want to be able to add or do something other than overwrite a currently existing description?
     * @param description descriptor for game
     */
    public void changeDescription(String description){
        this.description = description;
    }

    /**
     * Allows for title to be replaced
     * TODO maybe we want to be able to add or do something other than overwrite a currently existing title?
     * @param title descriptor for game
     */
    public void changeTitle(String title){
        this.title = title;
    }

    /**
     * Allows for game status to be updated (should only be used by admin class)
     * @param status Game status (Approved, Pending, or Rejected)
     */
    public void changeStatus(Status status){
        this.status = status;
    }

    /**
     * Allows for the addition of multiple developers
     * Adds Game to Developers GameList
     * @param developer a user who develops games for general users
     */
    public void addDeveloper(String developer) {
        this.developers.add(developer);
    }


    /**
     * assumes author of review is primary key
     * @param review
     */
    public void addReview(Review review){
        if(review == null)
            throw new NullPointerException("Review is null");
        if(review.getAuthor() == null)
            throw new NullPointerException("Author is null");
        // index indicates if in reviews
        int index = reviews.indexOf(review);
        if(index < 0)
            reviews.add(review);
        else
            reviews.set(index, review);
        setAverageRatingFromNewRating(review.getRating());
    }

    /**
     * An existing reviews rating value is updated and games avgRating is updated
     * @param author the reviews author
     * @param newRating the new rating
     * @throws IllegalArgumentException
     */
    public void updateReviewRating(String author, int newRating) throws IllegalArgumentException {
        Review exists = null;
        for (Review review : reviews){
            if (review.getAuthor() == author){
                exists = review;
            }
        }
        if (exists != null) {
            int oldRating = exists.getRating();
            exists.setRating(newRating);
            setAverageRatingFromUpdatedRating(newRating, oldRating);
        } else {
            String msg;
            if (exists == null && author != null){
                msg = "The author does not have a review for this game.";
            }
            else{
                msg = "Please provide an author.";
            }
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * An existing reviews summary is updated
     * @param author
     * @param newComment
     * @throws IllegalArgumentException
     */
    public void updateReviewComment(String author, String newComment) throws IllegalArgumentException {
        Review exists = null;
        for (Review review : reviews){
            if (review.getAuthor() == author){
                exists = review;
            }
        }
        if (exists != null) {
            exists.setSummary(newComment);
        } else {
            String msg;
            if (exists == null && author != null){
                msg = "The author does not have a review for this game.";
            }
            else{
                msg = "Please provide an author.";
            }
            throw new IllegalArgumentException(msg);
        }
    }

    private void setAverageRatingFromNewRating(int newRating) {
        double totalRatings = reviews.size();
        averageRating = (averageRating * (totalRatings-1) + newRating) / totalRatings;
    }

    private void setAverageRatingFromUpdatedRating(int newRating, int oldRating) {
        double totalRatings = reviews.size();
        averageRating = (averageRating * totalRatings - oldRating + newRating) / totalRatings;
    }

    private void loadAverageRating() {
        double total = 0;
        for (Review r: reviews) total += r.getRating();
        averageRating = total > 0 ? total/(double)reviews.size() : total;
    }

    // TODO: move to GameBrowserDisplay
//    public void displayableGame() {
//        String display = "Title: " + title + "\nDescription: " + description + "\nDeveloper(s): ";
//        if (developers.size()==0){
//            display += "None";
//        }
//        else{
//            for(int i = 0; i < developers.size()-1; i++){
//                display += developers.get(i) + ", ";
//            }
//            display += developers.get(developers.size()-1);
//        }
//        display += "\nStatus: "+ status.toString() + "\n";
//        System.out.println(display);
//    }
    public void increaseOwnedCount(){
        ownedCount = ownedCount + 1;
    }

    public void decreaseOwnedCount() {
        if (ownedCount > 0) ownedCount -= 1;
    }


    // ------GETTERS------

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public Status getStatus() {
        return status;
    }


    public List<Review> getReviews() {
        // gonna be honest, not like we care that much about hiding inner objects right now anyways.
        return reviews;
    }

    public int getOwnedCount(){return ownedCount;}

    public double getAverageRating() { return averageRating; }
}
