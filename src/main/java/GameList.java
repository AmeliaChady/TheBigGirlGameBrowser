import java.util.ArrayList;
import java.util.List;

public class GameList {
    private String name;
    private List<String> gameList;

    /**
     * Constructor - Create an empty game list with a title
     * @param name - name of the game list
     * @throws IllegalArgumentException if name is invalid
     */
    public GameList(String name) throws IllegalArgumentException {
        if (name.length() == 0)
            throw new IllegalArgumentException("Please provide a name for this game list");
        this.name = name;
        this.gameList = new ArrayList<String>();
    }

    /**
     * Constructor - Load an existing game list
     * @param name - name of the game list
     * @param gameTitles - a list of game titles for the pre-existing list
     * @throws IllegalArgumentException if name is invalid
     */
    public GameList(String name, List<String> gameTitles) throws IllegalArgumentException {
        if (name.length() == 0)
            throw new IllegalArgumentException("Please provide a name for this game list");
        this.name = name;
        this.gameList = gameTitles;
    }

    /**
     * Add a new game
     * @param gameTitle - a new game
     */
    public void includeGame(String gameTitle) { gameList.add(gameTitle); }

    /**
     * Remove a game
     * @param title - title of the game to remove
     * @return Game - the game removed
     */
    public String removeGame(String title) {
        String gameTitleFound = null;

        for (String gt : gameList) {
            if (gt.equals(title)) {
                gameTitleFound = gt;
                break;
            }
        }
        if (gameTitleFound != null) gameList.remove(gameTitleFound);

        return gameTitleFound;
    }

    /**
     * Find a game in this list
     * @param title - title of desired game
     * @return Game - Desired game in list
     */
    public String getGame(String title) {
        String gameTitleFound = null;

        for (String gt : gameList) {
            if (gt.equals(title)) {
                gameTitleFound = gt;
                break;
            }
        }
        return gameTitleFound;
    }

    //*********TODO MOVE TO GameBrowser
//    public void displayAllGames(){
//        System.out.println(name + ":\n");
//
//        if (getGameCount()==0){
//            System.out.println("This list is empty");
//        }
//        else {
//            for (int i = 0; i < getGameCount(); i++) {
//                gameList.get(i).displayableGame();
//            }
//        }
//    }
//
//    public void displayListNameAndGameTitles() {
//        String display = name + ": ";
//        if(gameList.size()<1){
//            display += "This list is empty";
//        }
//        else{
//            for (int i = 0; i < getGameCount() - 1; i++) {
//                display += gameList.get(i).getTitle() + ", ";
//            }
//            display += gameList.get(gameList.size() - 1).getTitle();
//        }
//        System.out.println(display);
//    }
//
//
//    public void displayGamesGivenStatus(Status status) {
//        System.out.println(name + "(" + status +"):\n");
//
//        if (getGameCount()==0){
//            System.out.println("This list is empty");
//        }
//        else {
//            for (int i = 0; i < getGameCount(); i++) {
//                if (gameList.get(i).getStatus()==status) {
//                    gameList.get(i).displayableGame();
//                }
//            }
//        }
//    }
//
//    public GameList getGamesGivenStatus(Status status) {
//        if (getGameCount()==0){
//            return new GameList("empty");
//        }
//        else {
//            GameList list = new GameList(status.toString());
//            for (int i = 0; i < getGameCount(); i++) {
//                if (gameList.get(i).getStatus()==status) {
//                    list.includeGame(gameList.get(i));
//                }
//            }
//            return list;
//        }
//    }
//
//    public void displayGameTitlesNumberedList(){
//        if(gameList.size()==0){
//            System.out.println("There are no games to display");
//        }
//        else{
//            for(int i = 0; i <gameList.size(); i++){
//                System.out.println((i+1) + ". " + gameList.get(i).getTitle());
//            }
//        }
//    }
//
//    public void displayNumberedListOfGamesGivenStatus(Status status){
//        System.out.println(name + "(" + status +"):\n");
//
//        if (getGameCount()==0){
//            System.out.println("This list is empty");
//        }
//        else {
//            int counter = 1; //tracks the number to be printed
//            for (int i = 0; i < getGameCount(); i++) {
//                if (gameList.get(i).getStatus()==status) {
//                    System.out.println(counter + ":");
//                    gameList.get(i).displayableGame();
//                    counter +=1;
//                }
//            }
//        }
//    }
//
//    public Game keepListOfGamesGivenStatus(Status status, int gamePlace){
//        //System.out.println(name + "(" + status +"):\n");
//        if (getGameCount()!=0){
//            int counter = 0; //tracks the number to be printed
//            for (int i = 0; i < getGameCount(); i++) {
//                if (gameList.get(i).getStatus()==status) {
//                    counter +=1;
//                }
//                if(counter == gamePlace){
//                    return gameList.get(i);
//                }
//
//            }
//        }
//        return null;
//    }
//
//    public GameList keepListOfGamesGivenStatusAndDev(Status status, Developer testDev){
//        GameList devsStatusGames = new GameList("devsStatusGames");
//        if (getGameCount()!=0){
//            int counter = 0; //tracks the number to be printed
//            for (int i = 0; i < getGameCount(); i++) {
//                if(gameList.get(i).getDevelopers() == testDev && gameList.get(i).getStatus()==Status.ACCEPTED ||
//                        gameList.get(i).getDevelopers() == testDev && gameList.get(i).getStatus()==Status.PENDING){
//                    counter += 1;
//                    devsStatusGames.includeGame(gameList.get(i));
//                }
//
//            }
//        }
//        return devsStatusGames;
//
//    }

    // ------GETTERS------
    public String getName() { return name; }

    public List<String> getGames() { return gameList; }

    public int getGameCount() { return gameList.size(); }
}
