import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UISprint1 {

    GameBrowser gameBrowser;

    public UISprint1(String filepath) throws IllegalArgumentException, DataSourceException {
        gameBrowser = new GameBrowser(filepath);
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
        while (devChoice<1 || devChoice>4){
            System.out.println("Please enter a vlid choice");
            devChoice = in.nextInt();
        }

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
            while (devUpdateChoice<0 || devUpdateChoice>testDev.getGameList().getGameCount()){
                System.out.println("Please pick a valid choice\n");
                System.out.println("Please select the game that you'd like to update, or press 0 to cancel:");
                devUpdateChoice = in.nextInt();
            }
            if(devUpdateChoice == 0){
                developerTakeAction(testDev);
            }

            Game updatingGame = devGameList.getGames().get(devUpdateChoice-1);


            System.out.println("Please select one of the following options:");
            System.out.println("1: Update Title");
            System.out.println("2: Update Bio");

            int devModifyChoice = in.nextInt();
            while (devModifyChoice<1 || devModifyChoice>2){
                System.out.println("Please pick a valid choice\n");
                System.out.println("Please select one of the following options:");
                System.out.println("1: Update Title");
                System.out.println("2: Update Bio");

                devModifyChoice = in.nextInt();
            }

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
            testDev.getGameList().displayAllGames();

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
            while(devReviewGameChoice < 0 || devReviewGameChoice > gameBrowser.getGameList().getGameCount()){
                System.out.println("Please enter a valid choice");
                System.out.println("Please choose which pending game you'd like to review, or press 0 to exit:");
                devReviewGameChoice = in.nextInt();
            }

            if(devReviewGameChoice == 0){
                adminTakeAction();
            }

            else if(devReviewGameChoice >= 1 && devReviewGameChoice < 10) {

                Game devReviewGame = keepListOfGamesGivenStatus(Status.PENDING, devReviewGameChoice, gameBrowser.getGameList());

                System.out.println("Would you like to accept or reject this game?");
                System.out.println("1: Approve");
                System.out.println("2: Reject");

                int devApproveReject = in.nextInt();

                if (devApproveReject == 1) {
                    devReviewGame.changeStatus(Status.ACCEPTED);
                    System.out.println("Game has been approved");
                    adminTakeAction();
                } else if (devApproveReject == 2) {
                    String removeGameTitle = devReviewGame.getTitle();
                    devReviewGame.changeStatus(Status.REJECTED);
                    removeGame(removeGameTitle);
                    System.out.println("Game has been rejected.");
                    adminTakeAction();
                } else {
                    System.out.println("ERROR: Invalid Response");
                    adminTakeAction();
                }
            }

        }

        else if(adminChoice == 2){
            System.out.println("Approved Games:");
            //display games with an accepted status
            displayGamesGivenStatus(gameBrowser.getGameList(), Status.ACCEPTED);
            if (gameBrowser.getGameList().getGamesGivenStatus(Status.ACCEPTED).getGameCount()>0){
                System.out.println("Would you like to remove any games?");
                System.out.println("1: Yes");
                System.out.println("2: No");
                int adminApprovedChoice = in.nextInt();
                while(adminApprovedChoice<1 || adminApprovedChoice>2){
                    System.out.println("Please enter a valid choice");
                    adminApprovedChoice = in.nextInt();
                }

                if(adminApprovedChoice == 1){
                    System.out.println("Please select the game you would like to remove, or 0 to cancel");
                    displayNumberedListOfGamesGivenStatus(gameBrowser.getGameList(), Status.ACCEPTED);
                    int adminRemoveChoice = in.nextInt();
                    while (adminRemoveChoice<0 || adminRemoveChoice>gameBrowser.getGameList().getGamesGivenStatus(Status.ACCEPTED).getGameCount()){
                        System.out.println("Please enter a valid choice");
                        System.out.println("Please select the game you would like to remove, or 0 to cancel");
                        adminRemoveChoice = in.nextInt();
                    }
                    Game adminRemoveGame = keepListOfGamesGivenStatus(Status.ACCEPTED, adminRemoveChoice, gameBrowser.getGameList());

                    String removeGameTitle = adminRemoveGame.getTitle();
                    if(adminRemoveChoice!=0){
                        removeGame(removeGameTitle);
                        System.out.println("The game has been removed.");
                        System.out.println("Thank you!");
                    }

                    adminTakeAction();
                }
                adminTakeAction();
            }
            else{
                System.out.println("No approved games\n");
                adminTakeAction();
            }

        }

        else if(adminChoice == 3){
            System.out.println("Thank you for using the Big Girl Game Library.");
            System.out.println("See you soon!");
            //login();
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
        myBGGLTest.login();

    }


}
