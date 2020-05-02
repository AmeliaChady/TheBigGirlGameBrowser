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


    public void addReview(Review review){
        this.reviews.add(review);
    }

    public double calculateAverageRating() { return -1; }

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

}
