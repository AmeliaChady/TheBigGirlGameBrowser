import java.util.*;

public class Developer {

    private String name;
    private GameList developerGameList;

    public Developer(String nameIn, GameList gameListIn){
        if (nameIn == null){
            throw new IllegalArgumentException("Name is null");
        }
        name = nameIn;
        if(gameListIn==null){
            throw new IllegalArgumentException("GameList is null");
        }
        developerGameList = gameListIn;
    }

    public Developer(String nameIn){
        name = nameIn;
        developerGameList = new GameList(nameIn+"'s Games");
    }

    //TODO: move to gameBrowser
//    public void displayDeveloper() {
//        System.out.println("Name: " + name );
//        developerGameList.displayListNameAndGameTitles();
//    }

    public String getName(){
        return name;
    }

    public GameList getGameList(){
        return developerGameList;
    }

    public void submitGame(String gameToSubmit){
        //needs to add game to developer list
        //needs to add game to gameList

        //adds to developer's personal list
        //developerGameList.includeGame(gameToSubmit);
        //adds to overall gameList

        //if not already in list
        if (!developerGameList.getGames().contains(gameToSubmit))
            developerGameList.includeGame(gameToSubmit);
    }

    public String getGameListName() {
        return developerGameList.getName();
    }
}
