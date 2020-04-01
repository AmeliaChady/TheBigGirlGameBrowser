import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UISprint1 {

    GameBrowser gameBrowser;

    public UISprint1(String filepath) throws IllegalArgumentException, DataSourceException {
        gameBrowser = new GameBrowser(filepath);

        Developer d1 = new Developer("Tyler");
        Developer d2 = new Developer("Frank");
        Developer d3 = new Developer("Ted");
        Developer d4 = new Developer("Zoe");
        Developer d5 = new Developer("Natalie");


        gameBrowser.addDeveloper("Tyler");
        gameBrowser.addDeveloper("Frank");
        gameBrowser.addDeveloper("Ted");
        gameBrowser.addDeveloper("Zoe");
        gameBrowser.addDeveloper("Natalie");

        Game g1 = new Game("Billy Bob Goes to The Moon", "a fun game yeehaw", d1, Status.PENDING);
        Game g2 = new Game("Why are there like four different sizes of gatorade", "im so tired",
                d2, Status.ACCEPTED);
        Game g3 = new Game("Fight Your Dad Simulator", "u kno what it's about", d3, Status.PENDING);
        Game g4 = new Game("beep in traffic", "the cars never move", d4, Status.ACCEPTED);
        Game g5 = new Game("jeff bezos takes over the world", "oh wait that's already happening",
                d5, Status.REJECTED);
        Game g6 = new Game("Prisoners in west virginia have to pay five cents a minute to read an ebook",
                "Prisoners in West virginia make less than a dollar an hour", d1, Status.ACCEPTED);
        Game g7 = new Game("only one koch brother is dead", "lets change that", d2, Status.PENDING);

        GameList testGameList = new GameList("Test Games");

        gameBrowser.setGameList(testGameList);

        d1.submitGame(g1, testGameList);
        d2.submitGame(g2, testGameList);
        d3.submitGame(g3, testGameList);
        d4.submitGame(g4, testGameList);
        d5.submitGame(g5, testGameList);
        d1.submitGame(g6, testGameList);
        d2.submitGame(g7, testGameList);

//        testGameList.includeGame(g1);
//        testGameList.includeGame(g2);
//        testGameList.includeGame(g3);
//        testGameList.includeGame(g4);
//        testGameList.includeGame(g5);
//        testGameList.includeGame(g6);
//        testGameList.includeGame(g7);

        d1.displayDeveloper();



    }


    private void login(){
        Scanner in = new Scanner(System.in);
        Developer iteratingDev;
        System.out.println("Welcome to the Big Girl Game Library!");
        System.out.println("Please enter your role:");
        System.out.println("1: Administrator");
        System.out.println("2: Developer");
        int roleChoice = in.nextInt();

        if (roleChoice == 1) {
            adminTakeAction();
        } else if (roleChoice == 2) {
            System.out.println("Please enter your name:");
            in.nextLine();
            String devNameEnter = in.nextLine();

            //gameBrowser.getDevelopers().get(0).getGameList().displayAllGames();
            List<Developer> developersLoginList = gameBrowser.getDevelopers();
            //developersLoginList.get(0).getGameList().displayAllGames();

            Iterator<Developer> i = developersLoginList.iterator();

//            System.out.println(devNameEnter);
//            System.out.println(i.hasNext());
//            System.out.println(developersLoginList.size());
            while(i.hasNext()){
                iteratingDev = i.next();
                //System.out.println(iteratingDev.getName());
                if(iteratingDev.getName().equalsIgnoreCase(devNameEnter)){
                    developerTakeAction(iteratingDev);
                    return;
                }
            }

            System.out.println("ERROR: Name Not Recognized");

            login();

        } else {
            System.out.println("ERROR: Invalid choice. Please try again");
            login();
        }
    }


    private void developerTakeAction(Developer testDev){
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please choose what action you'd like to take:");
        System.out.println("1: Submit Game");
        System.out.println("2: Update Game");
        System.out.println("3: View Your Game List");
        System.out.println("4: Logout");

        int devChoice = in.nextInt();

        if (devChoice == 1){
            System.out.println("Please enter the title of your game:");
            in.nextLine();
            String gameName = in.nextLine();
            System.out.println("Please enter the description of your game:");
            //in.nextLine();
            String gameDescription = in.nextLine();

            Game testGame = new Game(gameName, gameDescription, testDev, Status.PENDING);

            testDev.submitGame(testGame, gameBrowser.getGameList());
            //System.out.println(gameBrowser.getGameList().getGameCount());

            System.out.println("Thank you! Your game has been submitted and is under review.");
            System.out.println("Expect a response in your inbox shortly.");

            //back to menu screen
            developerTakeAction(testDev);
        }

        else if(devChoice == 2){
            GameList devGameList = testDev.getGameList();
            displayGameTitlesNumberedList(testDev.getGameList());
            System.out.println("Please select the game that you'd like to update, or press 0 to cancel:");
            int devUpdateChoice = in.nextInt();
            if(devUpdateChoice == 0){
                developerTakeAction(testDev);
            }
            Game updatingGame = devGameList.getGames().get(devUpdateChoice-1);


            System.out.println("Please select one of the following options:");
            System.out.println("1: Update Title");
            System.out.println("2: Update Bio");

            int devModifyChoice = in.nextInt();

            if(devModifyChoice == 1){
                System.out.println("Please enter the new game title:");
                in.nextLine();
                String updatedTitle = in.nextLine();
                updatingGame.changeTitle(updatedTitle);

                System.out.println("Title Updated!");
                developerTakeAction(testDev);
            }

            else if(devModifyChoice == 2){
                System.out.println("Please enter the new game bio:");
                in.nextLine();
                String updatedBio = in.nextLine();
                updatingGame.changeDescription(updatedBio);

                System.out.println("Bio updated!");
                developerTakeAction(testDev);
            }

            else{
                System.out.println("ERROR: Invalid answer.");
                System.out.println("Please try again.");

                developerTakeAction(testDev);
            }

        }

        else if (devChoice == 3){
            //displayGameTitlesNumberedList(testDev.getGameList());
            displayGameTitlesNumberedList(keepListOfGamesGivenStatusAndDev(Status.ACCEPTED, testDev, gameBrowser.getGameList()));

            //testDev.displayDeveloper();
            developerTakeAction(testDev);

        }

        else if(devChoice == 4){
            System.out.println("Thank you for using the Big Girl Game Library");
            System.out.println("See you soon!");

            login();
        }

        else{
            System.out.println("ERROR: Invalid answer.");
            System.out.println("Please try again.");
            //back to the top of the list
            developerTakeAction(testDev);
        }

    }

    private void adminTakeAction(){
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please choose what action you'd like to take:");
        System.out.println("1: Review Pending Games");
        System.out.println("2: Review Approved Games");
        System.out.println("3: Logout");

        int adminChoice = in.nextInt();

        if(adminChoice == 1){

            //display pending games
            displayNumberedListOfGamesGivenStatus(gameBrowser.getGameList(), Status.PENDING);
            System.out.println("Please choose which pending game you'd like to review, or press 0 to exit:");

            int devReviewGameChoice = in.nextInt();

            if(devReviewGameChoice == 0){
                adminTakeAction();
            }

            Game devReviewGame = keepListOfGamesGivenStatus(Status.PENDING, devReviewGameChoice, gameBrowser.getGameList());

            System.out.println("Would you like to accept or reject this game?");
            System.out.println("1: Approve");
            System.out.println("2: Reject");

            int devApproveReject = in.nextInt();

            if(devApproveReject == 1){
                devReviewGame.changeStatus(Status.ACCEPTED);
                includeGame(devReviewGame);
                System.out.println("Game has been approved");
                adminTakeAction();
            }

            else if(devApproveReject == 2){
                String removeGameTitle = devReviewGame.getTitle();
                devReviewGame.changeStatus(Status.REJECTED);
                removeGame(removeGameTitle);
                System.out.println("Game has been rejected.");
                adminTakeAction();
            }
            else{
                System.out.println("ERROR: Invalid Response");
                adminTakeAction();
            }

        }

        else if(adminChoice == 2){
            System.out.println("Approved Games:");
            //display games with an accepted status
            displayGamesGivenStatus(gameBrowser.getGameList(), Status.ACCEPTED);
            System.out.println("Would you like to remove any games?");
            System.out.println("1: Yes");
            System.out.println("2: No");
            int adminApprovedChoice = in.nextInt();

            if(adminApprovedChoice == 1){
                System.out.println("Please select the game you would like to remove");
                displayNumberedListOfGamesGivenStatus(gameBrowser.getGameList(), Status.ACCEPTED);
                int adminRemoveChoice = in.nextInt();
                Game adminRemoveGame = keepListOfGamesGivenStatus(Status.ACCEPTED, adminRemoveChoice, gameBrowser.getGameList());

                String removeGameTitle = adminRemoveGame.getTitle();
                removeGame(removeGameTitle);

                System.out.println("The game has been removed.");
                System.out.println("Thank you!");

                adminTakeAction();

            }
            else{
                adminTakeAction();
            }

        }

        else if(adminChoice == 3){
            System.out.println("Thank you for using the Big Girl Game Library.");
            System.out.println("See you soon!");
            //login();
        }

        else{
            adminTakeAction();
        }

    }

    public void displayGameTitlesNumberedList(GameList gameList){
        gameList.displayGameTitlesNumberedList();
    }

    public void displayGamesGivenStatus(GameList gameList, Status status){
        gameList.displayGamesGivenStatus(status);
    }

    public void displayNumberedListOfGamesGivenStatus(GameList gameList, Status status){
        gameList.displayNumberedListOfGamesGivenStatus(status);
    }


    public void displayListNameAndGameTitles(GameList gameList){
        gameList.displayListNameAndGameTitles();
    }

    public void displayAllGames(GameList gameList){
        gameList.displayAllGames();
    }

    public void includeGame(Game game){
        gameBrowser.addGame(game);
    }

    public void removeGame(String title){
        gameBrowser.removeGame(title);
    }

    public Game keepListOfGamesGivenStatus(Status status, int gamePlace, GameList gameList){
        return gameList.keepListOfGamesGivenStatus(status, gamePlace);

    }

    public GameList keepListOfGamesGivenStatusAndDev(Status status, Developer dev, GameList gameList){
        return gameList.keepListOfGamesGivenStatusAndDev(status, dev);
    }


    public static void main(String[] args) throws IOException, ParseException, DataSourceException {
        UISprint1 myBGGLTest = new UISprint1("testing.db");
        System.out.println(myBGGLTest.gameBrowser.getGameList().getGames());
        myBGGLTest.login();

    }


}
