import java.util.ArrayList;
import java.util.List;

enum Status {PENDING, ACCEPTED, REJECTED, LIMBO}

public class Game {
    private String title;
    private String description;
    private List<String> developers;
    private Status status;

    /**
     * Default constructor
     */
    public Game(){
        title = "testGame";
        description = "This is a test to create a new game object";
        this.developers = new ArrayList<Developer>();
        status = Status.PENDING;
    }

    /**
     * FullFull constructor (should only be used for backend tests)
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description, List<String> developer, Status status){
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<Developer>(developer);
        this.status = status;
    }

    /**
     *  Full constructor
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description,  List<String> developer){
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<Developer>(developer);
        this.status = Status.PENDING;
    }

    /**
     * Game constructor for when you don't know the description yet
     * @param title game title
     * @param developer link to developer object
     */
    public Game(String title, List<String> developer){
        this.title = title;
        this.developers = new ArrayList<Developer>(developer);
        this.description = "No Description Given";
        this.status = Status.PENDING;
    }


    /**
     * FullFull constructor (should only be used for backend tests)
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description, String developer, Status status){
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<Developer>();
        this.developers.add(developer);
        this.status = status;
    }

    /**
     *  Full constructor
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description, String developer){
        this.title = title;
        this.description = description;
        this.developers = new ArrayList<Developer>();
        this.developers.add(developer);
        this.status = Status.PENDING;
    }

    /**
     * Game constructor for when you don't know the description yet
     * @param title game title
     * @param developer link to developer object
     */
    public Game(String title, String developer){
        this.title = title;
        this.developers = new ArrayList<Developer>();
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
     * @param developer a user who develops games for general users
     */
    public void addDeveloper(String developer) {this.developers.add(developer);}

    public void displayGame() {
        String display = "Title: " + title + "\nDescription: " + description + "\nDeveloper(s): ";
        if (developers.size()==0){
            display += "None";
        }
        else{
            // TODO: iterator?
            for(int i = 0; i < developers.size()-1; i++){
                display += developers.get(i) + ", ";
            }
            display += developers.get(developers.size()-1);
        }
        display += "\nStatus: "+ status.toString() + "\n";
        System.out.println(display);
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

}
