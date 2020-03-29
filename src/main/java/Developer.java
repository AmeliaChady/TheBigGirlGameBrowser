import java.util.ArrayList;
import java.util.List;

public class Developer {

    private String name;
    private GameList gameList;


    public Developer(String nameIn){
        name = nameIn;
        gameList = new GameList(nameIn+"'s Games");
    }

    public Developer(String name, GameList gameList){
        this.name = name;
        this.gameList = gameList;
    }

    public String getName(){
        return name;
    }

    public String getGameListTitle(){
        return name+"'s Games";
    }

    public GameList getGameList(){
        return gameList;
    }
}
