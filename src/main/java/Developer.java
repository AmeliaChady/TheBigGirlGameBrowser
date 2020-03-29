import java.util.*;

public class Developer {

    private String name;
    private GameList developerGameList;

    public Developer(String nameIn){
        name = nameIn;
        developerGameList = new GameList(nameIn+"'s Games");
    }

    public String getName(){
        return name;
    }

    public GameList getGameList(){
        return developerGameList;
    }

    public void submitGame(Game gameToSubmit, GameList completeList){
        //needs to add game to developer list
        //needs to add game to gameList

        //adds to developers list
        developerGameList.includeGame(gameToSubmit);

        //adds to overall gameList
        completeList.includeGame(gameToSubmit);
    }
}
