import java.util.*;

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

    public void submitGame(Game gameToSubmit){
        gameList = getGameList();

        //GOING TO ADD HERE TO CHECK AND MAKE SURE GAME NOT ALREADY IN GAME LIST

        gameList.includeGame(gameToSubmit);
    }
}
