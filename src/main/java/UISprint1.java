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
            //Developer devNameEnter = in.nextLine();
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
            //submitGame(testGame, gamelist);

            System.out.println("Thank you! Your game has been submitted and is under review.");
            System.out.println("Expect a response in your inbox shortly.");

            //back to developer selection menu
        }

        else if(devChoice == 2){
            System.out.println("Please select the game that you'd like to update:");
            //how to display developer list????
        }

        else if(devChoice == 3){
            //do we even have a function right now for an inbox????
        }

        else if (devChoice == 4){
            System.out.println("Thank you for using the Big Girl Game Library");
            System.out.println("See you soon!");
            //login();
        }

    }

    private void adminTakeAction(){
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please choose what action you'd like to take:");
        System.out.println("1: Review Submitted Games");
        System.out.println("2: Review Approved Games");
        System.out.println("3: Logout");

        int adminChoice = in.nextInt();

        if(adminChoice == 1){
        //is there a function that displays submitted games?
        }

        else if(adminChoice == 2){
        //is there a function that displays the library?
        }

        else if(adminChoice == 3){
            System.out.println("Thank you for using the Big Girl Game Library.");
            System.out.println("See you soon!");
            //login();
        }

    }

    public void displayGameTitlesNumberedList(GameList gameList){
        gameBrowser.displayGameTitlesNumberedList(gameList);
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
        //yeehaw bicth

    }


}
