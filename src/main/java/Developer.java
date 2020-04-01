public class Developer {

    private String name;
    private GameList gameList;

    public Developer(String nameIn, GameList gameListIn){
        if (nameIn == null){
            throw new IllegalArgumentException("Name is null");
        }
        name = nameIn;
        if(gameListIn==null){
            throw new IllegalArgumentException("GameList is null");
        }
        gameList = gameListIn;
    }

    public Developer(String nameIn){
        name = nameIn;
        gameList = new GameList(nameIn+"'s Games");
    }

    public void displayDeveloper() {
        System.out.println("Name: " + name );
        gameList.displayListNameAndGameTitles();
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
