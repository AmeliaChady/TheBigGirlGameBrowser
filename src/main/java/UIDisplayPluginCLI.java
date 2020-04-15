public class UIDisplayPluginCLI implements UIDisplayPluginBase{
    Game g;
    GameList gl;
    Developer d;
    GameBrowser gb;

    UIDisplayPluginCLI(){
        g = null;
        gl = null;
        d = null;
    }

    @Override
    public void pullGame(Game game) {
        g = game;
    }

    @Override
    public void pullGameList(GameList gl) {
        this.gl = gl;
    }

    @Override
    public void pullDeveloper(Developer dev) {
        d = dev;
    }

    @Override
    public void pullGameBrowser(GameBrowser gb){
        this.gb = gb;
    }

    public String displayGame() {
        String gameString = "Title: " + g.getTitle() + "\n";
        gameString += "Description: " + g.getDescription() + "\n";
        if(g.getDevelopers().isEmpty()) {
            gameString += "Developer(s): " + "None" + "\n";
        }
        else{
            StringBuffer devs = new StringBuffer();
            for(String d : g.getDevelopers()){
                devs.append(d).append(", ");
            }
            gameString += "Developer(s): " + devs.substring(0, devs.length()-2)+ "\n";
        }
        gameString += "Status: " + g.getStatus() + "\n\n";
        return gameString;
    }

    public String displayDeveloper() {
//        System.out.println("Name: " + name );
//        developerGameList.displayListNameAndGameTitles();
        return null;
    }

    public String displayAllGames(){
//        System.out.println(name + ":\n");
//
//        if (getGameCount()==0){
//            System.out.println("This list is empty");
//        }
//        else {
//            for (int i = 0; i < getGameCount(); i++) {
//                gameList.get(i).displayGame();
//            }
//        }
        return null;
    }

    public String displayListNameAndGameTitles() {
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
        return null;
    }


    public String displayGamesGivenStatus(Status status) {
//        System.out.println(name + "(" + status +"):\n");
//
//        if (getGameCount()==0){
//            System.out.println("This list is empty");
//        }
//        else {
//            for (int i = 0; i < getGameCount(); i++) {
//                if (gameList.get(i).getStatus()==status) {
//                    gameList.get(i).displayGame();
//                }
//            }
//        }
        return null;
    }

    public String displayGameTitlesNumberedList(){
//        if(gameList.size()==0){
//            System.out.println("There are no games to display");
//        }
//        else{
//            for(int i = 0; i <gameList.size(); i++){
//                System.out.println((i+1) + ". " + gameList.get(i).getTitle());
//            }
//        }
        return null;
    }


    public String displayNumberedListOfGamesGivenStatus(Status status){
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
//                    gameList.get(i).displayGame();
//                    counter +=1;
//                }
//            }
//        }
        return null;
    }

}
