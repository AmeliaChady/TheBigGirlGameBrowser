public class UIPluginCLI implements UIPlugin {
    Game g;
    GameList gl;
    Developer d;
    GameBrowser gb;

    UIPluginCLI(){
        g = null;
        gl = null;
        d = null;
        gb = null;
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

    @Override
    public boolean hasGame() {
        return g!=null;
    }

    @Override
    public boolean hasGameList() {
        return gl!=null;
    }

    @Override
    public boolean hasDeveloper() {
        return d!=null;
    }

    @Override
    public boolean hasGameBrowser() {
        return gb!=null;
    }

    //TODO: Throw error if game is null
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

    //TODO: Throw error if developer is null
    public String displayDeveloper() {
        String devString = "Name: " + d.getName() + "\n";

        // allows use of GameList displaying while keeping current GameList
        GameList temp = gl;
        gl = d.getGameList();
        devString +=  displayListNameAndGameTitles();
        gl = temp;

        return devString;
    }

    // TODO: Throw error if gamelist or gamebrowser is null
    public String displayAllGames(){
        /*String allGames = gl.getName() + ":\n";

        if (gl.getGameCount()==0){
            System.out.println("This list is empty");
        }
        else {
            Game temp = g;

            Iterator<String> games = gl.getGames().iterator();
            while (games.hasNext()){
                String gameName = games.next();
                g = gb.
            }

            g = temp;
        }*/
        return null;
    }

    // TODO: Throw error if gamelist
    public String displayListNameAndGameTitles() {
        String display = gl.getName() + ": ";
        if(gl.getGameCount()<1){
            display += "This list is empty";
        }
        else{
            for (int i = 0; i < gl.getGameCount() - 1; i++) {
                display += gl.getGames().get(i) + ", ";
            }
            display += gl.getGames().get(gl.getGames().size() - 1);
        }
        display += "\n";
        return display;
    }

    // TODO: Throw error if gamelist or gamebrowser is null
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

    // TODO: Throw error if gamelist
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


//    public String displayNumberedListOfGamesGivenStatus(Status status){
////        System.out.println(name + "(" + status +"):\n");
////
////        if (getGameCount()==0){
////            System.out.println("This list is empty");
////        }
////        else {
////            int counter = 1; //tracks the number to be printed
////            for (int i = 0; i < getGameCount(); i++) {
////                if (gameList.get(i).getStatus()==status) {
////                    System.out.println(counter + ":");
////                    gameList.get(i).displayGame();
////                    counter +=1;
////                }
////            }
////        }
//        return null;
//    }

}
