public class Game {
    private String title;
    private String description;
    private Developer developer;

    public Game(){
        title = "testGame";
        description = "This is a test to create a new game object";
        developer = null;
    }

    public Game(String title, String description, Developer developer){
        this.title = title;
        this.description = description;
        this.developer = developer;
    }

    public Game(String title, Developer developer){
        this.title = title;
        this.developer = developer;
        this.description = "No Description Given";
    }

    public void changeDescription(String description){
        this.description = description;
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
