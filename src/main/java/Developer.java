import java.util.ArrayList;
import java.util.List;

public class Developer {

    private String name;
    private String gameListTitle;
    private GameList gameList;


    public Developer(String nameIn){
        name = nameIn;
        gameListTitle = nameIn+"'s Games";
        gameList = new GameList(gameListTitle);
    }

    public Developer(String name, String gameListTitle, GameList gameList){
        this.name = name;
        this.gameListTitle = gameListTitle;
        this.gameList = gameList;
    }

    public String getName(){
        return name;
    }

    public String getGameListTitle(){
        return gameListTitle;
    }

    public GameList getGameList(){
        return gameList;
    }
}
