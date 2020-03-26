public class Developer {

    private String name;
    private GameList gameList;

    public Developer(String nameIn){
        name = nameIn;
        gameList = new GameList("Developer's Games");
    }

    public String getName(){
        return name;
    }

    public GameList getGameList(){
        return gameList;
    }
}
