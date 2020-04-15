public class UIDisplayPluginCLI implements UIDisplayPluginBase{
    Game g;
    GameList gl;
    Developer d;

    UIDisplayPluginCLI(){
        g = null;
        gl = null;
        d = null;

    }

    @Override
    public void pullGame(Game game) {

    }

    @Override
    public void pullGameList(GameList gl) {

    }

    @Override
    public void pullDeveloper(Developer dev) {

    }

    public void displayGame() {
        String display = "Title: " + g.getTitle() + "\nDescription: " + g.getDescription() + "\nDeveloper(s): ";
        if (developers.size()==0){
            display += "None";
        }
        else{
            for(int i = 0; i < developers.size()-1; i++){
                display += developers.get(i) + ", ";
            }
            display += developers.get(developers.size()-1);
        }
        display += "\nStatus: "+ status.toString() + "\n";
        System.out.println(display);
    }

    public void displayDeveloper() {
        System.out.println("Name: " + name );
        developerGameList.displayListNameAndGameTitles();
    }

    public void displayAllGames(){
        System.out.println(name + ":\n");

        if (getGameCount()==0){
            System.out.println("This list is empty");
        }
        else {
            for (int i = 0; i < getGameCount(); i++) {
                gameList.get(i).displayGame();
            }
        }
    }

    public void displayListNameAndGameTitles() {
        String display = name + ": ";
        if(gameList.size()<1){
            display += "This list is empty";
        }
        else{
            for (int i = 0; i < getGameCount() - 1; i++) {
                display += gameList.get(i).getTitle() + ", ";
            }
            display += gameList.get(gameList.size() - 1).getTitle();
        }
        System.out.println(display);
    }


    public void displayGamesGivenStatus(Status status) {
        System.out.println(name + "(" + status +"):\n");

        if (getGameCount()==0){
            System.out.println("This list is empty");
        }
        else {
            for (int i = 0; i < getGameCount(); i++) {
                if (gameList.get(i).getStatus()==status) {
                    gameList.get(i).displayGame();
                }
            }
        }
    }


    public void displayGameTitlesNumberedList(){
        if(gameList.size()==0){
            System.out.println("There are no games to display");
        }
        else{
            for(int i = 0; i <gameList.size(); i++){
                System.out.println((i+1) + ". " + gameList.get(i).getTitle());
            }
        }
    }

    public void displayNumberedListOfGamesGivenStatus(Status status){
        System.out.println(name + "(" + status +"):\n");

        if (getGameCount()==0){
            System.out.println("This list is empty");
        }
        else {
            int counter = 1; //tracks the number to be printed
            for (int i = 0; i < getGameCount(); i++) {
                if (gameList.get(i).getStatus()==status) {
                    System.out.println(counter + ":");
                    gameList.get(i).displayGame();
                    counter +=1;
                }
            }
        }
    }

}
