public class Developer {

    private String name;
    private GameList gameList;

    public Developer(String nameIn){
        name = nameIn;
        gameList = new GameList(nameIn+"'s Games");
    }

    public String getName(){
        return name;
    }

    public GameList getGameList(){
        return gameList;
    }

    public String getGameListName() {
        return gameList.getName();
    }
}
