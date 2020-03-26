
enum Status {PENDING, ACCEPTED, REJECTED}

public class Game {
    private String title;
    private String description;
    private Developer developer;
    private Status status;

    /**
     * Default constructor
     */
    public Game(){
        title = "testGame";
        description = "This is a test to create a new game object";
        developer = null;
        status = Status.PENDING;
    }

    /**
     * FullFull constructor (should only be used for backend tests)
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description, Developer developer, Status status){
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.status = status;
    }

    /**
     *  Full constructor
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description, Developer developer){
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.status = Status.PENDING;
    }

    /**
     * Game constructor for when you don't know the description yet
     * @param title game title
     * @param developer link to developer object
     */
    public Game(String title, Developer developer){
        this.title = title;
        this.developer = developer;
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

    // ------GETTERS------

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public Status getStatus() {
        return status;
    }
}
