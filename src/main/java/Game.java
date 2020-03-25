public class Game {
    private String title;
    private String description;
    private Developer developer;

    public Game(){
        title = "testGame";
        description = "This is a test to create a new game object";
        developer = null;
    }

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
