public class Game {
    private String title;
    private String description;
    private Developer developer;

    /**
     * Default constructor
     */
    public Game(){
        title = "testGame";
        description = "This is a test to create a new game object";
        developer = null;
    }

    /**
     * Full constructor
     * @param title game title
     * @param description descriptor for game
     * @param developer link to developer object
     */
    public Game(String title, String description, Developer developer){
        this.title = title;
        this.description = description;
        this.developer = developer;
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
}
