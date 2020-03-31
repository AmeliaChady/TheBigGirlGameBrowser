import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class UISprint1 {

    GameBrowser gameBrowser;

    public UISprint1(String filepath) throws IllegalArgumentException{
        gameBrowser = new GameBrowser(filepath);
    }

    private void login() throws IOException, ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Big Girl Game Library!");
        System.out.println("Please enter your role:");
        System.out.println("1: Administrator");
        System.out.println("2: Developer");
        int roleChoice = in.nextInt();

        if(roleChoice == 1){
            adminTakeAction();
        }

        else if(roleChoice == 2){
            System.out.println("Please enter your name:");
            String devNameEnter = in.nextLine();


            //developerTakeAction(devNameEnter);

        }

        else{
            System.out.println("ERROR: Invalid choice. Please try again");

            //login();
        }
    }

    private void developerTakeAction(Developer testDev){
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please choose what action you'd like to take:");
        System.out.println("1: Submit Game");
        System.out.println("2: Update Game");
        System.out.println("3: Review Statuses");
        System.out.println("4: Logout");

        int devChoice = in.nextInt();

        if (devChoice == 1){
            System.out.println("Please enter the title of your game:");
            String gameName = in.nextLine();
            System.out.println("Please enter the description of your game:");
            String gameDescription = in.nextLine();

            Game testGame = new Game(gameName, gameDescription, testDev, Status.PENDING);

            testDev.submitGame(testGame, gameBrowser.getGameList());

            System.out.println("Thank you! Your game has been submitted and is under review.");
            System.out.println("Expect a response in your inbox shortly.");

            //back to menu screen
            developerTakeAction(testDev);
        }

        else if(devChoice == 2){
            GameList devGameList = testDev.getGameList();
            displayGameTitlesNumberedList(testDev.getGameList());
            System.out.println("Please select the game that you'd like to update:");
            int devUpdateChoice = in.nextInt();
            Game updatingGame = devGameList.getGames().get(devUpdateChoice-1);

            System.out.println("Please select one of the following options:");
            System.out.println("1: Update Title");
            System.out.println("2: Update Bio");

            int devModifyChoice = in.nextInt();

            if(devModifyChoice == 1){
                System.out.println("Please enter the new game title:");
                String updatedTitle = in.nextLine();
                updatingGame.changeTitle(updatedTitle);

                System.out.println("Title Updated!");
                developerTakeAction(testDev);
            }

            else if(devModifyChoice == 2){
                System.out.println("Please enter the new game bio:");
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

        else if(devChoice == 3){
            System.out.println("This option currently under construction.");
            System.out.println("Please check back later.");
            developerTakeAction(testDev);
        }

        else if (devChoice == 4){
            System.out.println("Thank you for using the Big Girl Game Library");
            System.out.println("See you soon!");

            //login();
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
            System.out.println("Please choose which pending game you'd like to review.");
            //display pending games
            displayGamesGivenStatus(gameBrowser.getGameList(), Status.PENDING);
            //is there a function that displays submitted games?
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


    public static void main(String[] args) throws IOException, ParseException {
        UISprint1 myBGGLTest = new UISprint1("testing.db");
        myBGGLTest.login();

    }


}
