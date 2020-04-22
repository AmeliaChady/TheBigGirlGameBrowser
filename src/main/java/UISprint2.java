import java.text.ParseException;
import java.util.Iterator;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UISprint2 {

    GameBrowser gameBrowser;



    public UISprint2(String filepath) throws IllegalArgumentException, DataSourceException {
        gameBrowser = new GameBrowser(filepath, new UIPluginCLI());
    }

    private void login() throws DataSourceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Big Girl Game Library!");
        System.out.println("Please enter your username: ");

        String usernameEnter = in.nextLine();

        System.out.println("Please enter your password: ");

        String passwordEnter = in.nextLine();

        try {
            Accounts userAccounts = gameBrowser.login(usernameEnter, passwordEnter);


            //admin login
            if (userAccounts.admin != null) {
                System.out.println("Logged IN! Welcome " + userAccounts.admin.getUsername());
                administratorTakeAction(userAccounts.admin);
            }
            //user
            else if (userAccounts.user != null && userAccounts.dev == null) {
                System.out.println("Logged IN! you own " + userAccounts.user.getOwnedGames().getGames().size() + " Games!");
                commercialUserTakeAction(userAccounts.user, null, false);
            }
            //developer
            else if (userAccounts.user == null && userAccounts.dev != null) {
                System.out.println("Logged IN! Welcome " + userAccounts.dev.getName());
                developerTakeAction(userAccounts.dev, null, false);
            }


            //user and dev dual account
            else if (userAccounts.user != null && userAccounts.dev != null) {
                System.out.println("You have both a User and a Developer account, which would you like to login as?");
                System.out.println("1. User");
                System.out.println("2. Dev");

                int choice = in.nextInt();

                while(choice > 2 || choice < 1){
                    System.out.println("Invalid choice.");
                    System.out.println("Would you like to login as a user or dev?");
                    System.out.println("1. User");
                    System.out.println("2. Dev");

                    choice = in.nextInt();
                }

                if (choice == 1){
                    commercialUserTakeAction(userAccounts.user, userAccounts.dev, true);
                }

                else{
                    developerTakeAction(userAccounts.dev, userAccounts.user, true);
                }
            } else {
                System.out.println("I'm sorry, either your username or password is incorrect.");
                login();

                //password recovery code moved below main for now
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\nPlease try again");
            login();
        }
    }

    public boolean isInt( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    //ADMINISTRATOR UI

    private void administratorTakeAction(Administrator adminAccount) throws DataSourceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please choose what action you'd like to take:");
        System.out.println("1: Review Pending Games");
        System.out.println("2: Review Approved Games");
        System.out.println("3: View All Games in Library");
        System.out.println("4: Logout");

        String adminChoice = in.nextLine();
        while (!isInt(adminChoice) || (parseInt(adminChoice) < 1 || parseInt(adminChoice) > 4)) {
            System.out.println("Please enter a valid choice");
            adminChoice = in.nextLine();
        }
//---------------pending-------------------
        if (parseInt(adminChoice) == 1) {
            //display pending games
            gameBrowser.pullGameList("Master Game List");
            System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.PENDING));
            GameList pendingGames = gameBrowser.getGamesGivenStatus(Status.PENDING);
            if(pendingGames.getGameCount()<1){
                System.out.println("No pending games");
                administratorTakeAction(adminAccount);
            }
            else {
                System.out.println("Please choose which pending game you'd like to review, or press 0 to exit:");

                String devReviewGameChoice = in.nextLine();
                while (!isInt(devReviewGameChoice) || (parseInt(devReviewGameChoice) < 0 || parseInt(devReviewGameChoice) > gameBrowser.getGameList().getGameCount())) {
                    System.out.println("Please enter a valid choice");
                    System.out.println("Please choose which pending game you'd like to review, or press 0 to exit:");
                    devReviewGameChoice = in.nextLine();
                }

                if (parseInt(devReviewGameChoice) == 0) {
                    administratorTakeAction(adminAccount);

                } else if (parseInt(devReviewGameChoice) >= 1 && parseInt(devReviewGameChoice) <= pendingGames.getGames().size()) {

                    String devReviewGame = pendingGames.getGames().get(parseInt(devReviewGameChoice) - 1);

                    //get game
                    Game chosenGame = gameBrowser.loadGame(devReviewGame);

                    System.out.println("Would you like to accept or reject this game?, or press any other number to quit");
                    System.out.println("1: Approve");
                    System.out.println("2: Reject");

                    int devApproveReject = in.nextInt();

                    if (devApproveReject == 1) {
                        chosenGame.changeStatus(Status.ACCEPTED);
                        gameBrowser.saveGame(chosenGame);
                        System.out.println("Game has been approved");
                        administratorTakeAction(adminAccount);
                    } else if (devApproveReject == 2) {
                        chosenGame.changeStatus(Status.REJECTED);
                        gameBrowser.saveGame(chosenGame);
                        System.out.println("Game has been rejected.");
                        administratorTakeAction(adminAccount);
                    } else {
                        System.out.println("Game status not changed");
                        administratorTakeAction(adminAccount);
                    }
                }
            }

        }
//----------------------------------
        else if (parseInt(adminChoice) == 2) {

            System.out.println("Approved Games:");

            //nothing in list
            if (gameBrowser.getGameList().getGames().size() > 1) {
                System.out.println("No approved games\n");
                administratorTakeAction(adminAccount);
            }

            else {
                System.out.println("Would you like to remove any games?");
                System.out.println("1: Yes");
                System.out.println("2: No");
                String adminApprovedChoice = in.nextLine();
                while (!isInt(adminApprovedChoice) || (parseInt(adminApprovedChoice) < 1 || parseInt(adminApprovedChoice) > 2)) {
                    System.out.println("Please enter a valid choice");
                    adminApprovedChoice = in.nextLine();
                }

                //yes
                if (parseInt(adminApprovedChoice) == 1) {
                    System.out.println("Please select the game you would like to remove, or 0 to cancel");

                    System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.ACCEPTED));

                    gameBrowser.pullGameList("Master Game List");
                    GameList approvedGames = gameBrowser.getGamesGivenStatus(Status.ACCEPTED);

                    String adminRemoveChoice = in.nextLine();
                    while (!isInt(adminRemoveChoice) || (parseInt(adminRemoveChoice) < 1 || parseInt(adminRemoveChoice) > gameBrowser.getGameList().getGameCount())) {
                        System.out.println("Please enter a valid choice");
                        adminRemoveChoice = in.nextLine();
                    }

                    Game chosenGame = gameBrowser.loadGame(approvedGames.getGames().get(parseInt(adminRemoveChoice) - 1));
                    chosenGame.changeStatus(Status.REJECTED);
                    gameBrowser.saveGame(chosenGame);
                    System.out.println("Game has been rejected.");
                    administratorTakeAction(adminAccount);

                }
            }
        }
//----------------------------------
        else if (parseInt(adminChoice)==3){
            gameBrowser.pullGameList("Master Game List");
            System.out.println(gameBrowser.displayableNumberedListOfFullGames());

            administratorTakeAction(adminAccount);
        }
//----------------------------------log out
        //log out
        else {
            System.out.println("Thank you for using the Big Girl Game Library.");
            System.out.println("See you soon!");
            login();
        }

    }


    //DEVELOPER UI
    private void developerTakeAction(Developer devAccount, User userAccount, boolean dual) throws DataSourceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please choose what action you'd like to take:");
        System.out.println("1: Submit Game");
        System.out.println("2: Update Game");
        System.out.println("3: View Your Game List");
        System.out.println("4: Logout");
        if(dual){
            System.out.println("5: Switch Accounts");
        }

        String devChoice = in.nextLine();

        if(!dual){
            while (!isInt(devChoice) || (parseInt(devChoice) < 1 || parseInt(devChoice) > 4)) {
                System.out.println("Please enter a valid choice");
                devChoice = in.nextLine();
            }
        }
        else{
            while (!isInt(devChoice) || (parseInt(devChoice) < 1 || parseInt(devChoice) > 5)) {
                System.out.println("Please enter a valid choice");
                devChoice = in.nextLine();
            }
        }

//-----------------------submit
        if (parseInt(devChoice) == 1) {
            Scanner in1 = new Scanner(System.in);
            System.out.println("Please enter the title of your game:");
            String gameName = in1.nextLine();

            System.out.println("Please enter the description of your game:");
            String gameDescription = in1.nextLine();

            //submit
            Game newGame = new Game(gameName, gameDescription, devAccount.getName(), Status.PENDING);
            //adds to master list and saves in database
            gameBrowser.addGame(newGame);
            //includes game in dev's gamelist
            devAccount.submitGame(newGame.getTitle());
            //saves dev's gamelist
            gameBrowser.saveGameList(devAccount.getGameList());

            System.out.println("Thank you! Your game has been submitted and is under review.");
            //System.out.println("Expect a response in your inbox shortly.");

            //back to menu screen
            developerTakeAction(devAccount, userAccount, dual);
        }
//-------------------------update game
        else if (parseInt(devChoice) == 2) {

            gameBrowser.pullGameList(devAccount.getGameList().getName());
            System.out.println(gameBrowser.displayableGameTitlesNumberedList());

            //only will do things if list size is at least 1.
            if (gameBrowser.getGameList().getGameCount()>0) {
                System.out.println("Please select the game that you'd like to update, or press 0 to cancel:");
                String devUpdateChoice = in.nextLine();
                while (!isInt(devUpdateChoice) || (parseInt(devUpdateChoice) < 0 || parseInt(devUpdateChoice) > devAccount.getGameList().getGameCount())) {
                    System.out.println("Please pick a valid choice\n");
                    System.out.println("Please select the game that you'd like to update, or press 0 to cancel:");
                    devUpdateChoice = in.nextLine();
                }
                if (parseInt(devUpdateChoice) == 0) {
                    developerTakeAction(devAccount, userAccount, dual);
                } else {

                    Game updatingGame = gameBrowser.loadGame(devAccount.getGameList().getGames().get(parseInt(devUpdateChoice) - 1));


                    System.out.println("Please select one of the following options:");
                    System.out.println("1: Update Title");
                    System.out.println("2: Update Description");

                    String devModifyChoice = in.nextLine();
                    while (!isInt(devModifyChoice) || (parseInt(devModifyChoice) < 1 || parseInt(devModifyChoice) > 2)) {
                        System.out.println("Please select one of the following options:");
                        System.out.println("1: Update Title");
                        System.out.println("2: Update Description");

                        devModifyChoice = in.nextLine();
                    }

                    //update title
                    if (parseInt(devModifyChoice) == 1) {
                        //get old game title
                        String oldTitle = updatingGame.getTitle();
                        System.out.println("Please enter the new game title:");
                        String updatedTitle = in.nextLine();
                        gameBrowser.changeTitle(updatingGame, updatedTitle);
                        System.out.println(gameBrowser.getGameList().getGames());
                        gameBrowser.addGameToDevGameList(devAccount, updatingGame);
                        //remove old title copy
                        gameBrowser.removeGame(oldTitle);
                        gameBrowser.saveGameList(devAccount.getGameList());
                        System.out.println("Title Updated!");
                        developerTakeAction(devAccount, userAccount, dual);
                    }
                    //update description
                    else if (parseInt(devModifyChoice) == 2) {
                        Scanner in3 = new Scanner(System.in);
                        System.out.println("Please enter the new game description:");
                        //in3.nextLine();
                        String updatedBio = in3.nextLine();
                        updatingGame.changeDescription(updatedBio);
                        gameBrowser.saveGame(updatingGame);

                        System.out.println("Description updated!");
                        developerTakeAction(devAccount, userAccount, dual);
                    } else {
                        System.out.println("ERROR: Invalid answer.");
                        developerTakeAction(devAccount, userAccount, dual);
                    }
                }
            }
            developerTakeAction(devAccount, userAccount, dual);
        }
//---------------------view gameList
        else if (parseInt(devChoice) == 3) {

            gameBrowser.pullGameList(devAccount.getGameList().getName());
            System.out.println(gameBrowser.displayableNumberedListOfFullGames());

            developerTakeAction(devAccount, userAccount, dual);

        }
//----------------------log out
        else if (parseInt(devChoice) == 4) {
            System.out.println("Thank you for using the Big Girl Game Library");
            System.out.println("See you soon!");

            login();
        }

        else if(parseInt(devChoice) == 5){
            System.out.println("You will now be logged into your Commercial User account.");
            commercialUserTakeAction(userAccount, devAccount, dual);
        }

        else {
            System.out.println("ERROR: Invalid answer.");
            System.out.println("Please try again.");
            //back to the top of the list
            developerTakeAction(devAccount, userAccount, dual);
        }

    }


    private void commercialUserTakeAction(User userAccount, Developer devAccount, boolean dual) throws DataSourceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please choose the action you'd like to take");
        System.out.println("1: View All Games");
        System.out.println("2: Add Game to Owned Games List");
        System.out.println("3: Remove Game from Owned Games List");
        System.out.println("4: View Owned Games List");
        System.out.println("5: Logout");
        if(dual){
            System.out.println("6: Switch Accounts");
        }

        String userChoice = in.nextLine();
        if(!dual){
            while (!isInt(userChoice) || (parseInt(userChoice) < 1 || parseInt(userChoice) > 5)) {
                System.out.println("Please enter a valid choice:");
                userChoice = in.nextLine();
            }
        }
        else{
            while (!isInt(userChoice) || (parseInt(userChoice) < 1 || parseInt(userChoice) > 6)) {
                System.out.println("Please enter a valid choice:");
                userChoice = in.nextLine();
            }
        }

        if (parseInt(userChoice) == 1) {
            System.out.println("Big Girl Game Browser Game List:");
            gameBrowser.pullGameList("Master Game List");
            System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.ACCEPTED));
            commercialUserTakeAction(userAccount, devAccount, dual);
        }

        else if (parseInt(userChoice) == 2) {
            gameBrowser.pullGameList("Master Game List");
            System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.ACCEPTED));

            System.out.println("Please enter the number of the game you'd like to add to your owned list");
            System.out.println("Or press 0 to exit.");

            int userAddChoice = in.nextInt();

            if (userAddChoice == 0) {
                //back to user menu
                commercialUserTakeAction(userAccount, devAccount, dual);

            } else if (userAddChoice > 1 || userAddChoice < userAccount.getOwnedGames().getGameCount()) {

                //get game based on user input
                GameList approvedGames = gameBrowser.getGamesGivenStatus(Status.ACCEPTED);
                String userGameToAdd = approvedGames.getGames().get(userAddChoice - 1);
                Game gameToAdd = gameBrowser.loadGame(userGameToAdd);

                gameBrowser.addGameToUserGameList(userAccount, gameToAdd);
                gameBrowser.saveGameList(userAccount.getOwnedGames());

                System.out.println("Game has successfully been added to your list.");
                commercialUserTakeAction(userAccount, devAccount, dual);
            }

        }
        else if (parseInt(userChoice) == 3) {
            System.out.println("Owned Games:");
            gameBrowser.pullGameList(userAccount.getOwnedGames().getName());
            System.out.println(gameBrowser.displayableGameTitlesNumberedList());

            System.out.println("Please select the game that you'd like to remove, or press 0 to cancel:");
            int removeOwnedListChoice = in.nextInt();
            if (removeOwnedListChoice > 0 || removeOwnedListChoice <= userAccount.getOwnedGames().getGameCount()){

                Game updatingGame = gameBrowser.loadGame(userAccount.getOwnedGames().getGames().get(removeOwnedListChoice - 1));
                gameBrowser.removeGameFromUserGameList(userAccount, updatingGame);
                gameBrowser.saveGameList(userAccount.getOwnedGames());
            }

            else{
                System.out.println("ERROR: Not a valid input.");
                System.out.println("You will now be returned to the User Menu.");
            }
            commercialUserTakeAction(userAccount, devAccount, dual);
        }

        else if (parseInt(userChoice) == 4) {
            gameBrowser.pullGameList(userAccount.getOwnedGames().getName());
            System.out.println(gameBrowser.displayableAllGames());
            commercialUserTakeAction(userAccount, devAccount, dual);
        }

        else if(parseInt(userChoice) == 5){
            System.out.println("Thank you for using the Big Girl Game Browser.");
            System.out.println("Have a good one!");
            login();
        }

        else if(dual && parseInt(userChoice) == 6){
            System.out.println("You will now be logged in as a developer.");
            developerTakeAction(devAccount, userAccount, true);
        }

    }


    public static void main(String[] args) throws IOException, ParseException, DataSourceException {
        UISprint2 myBGGLTest = new UISprint2("src/databases/Test_SQLiteSource/Login.db");
        myBGGLTest.login();

    }
    // password recovery code moved below for mow
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //System.out.println("Please select from the following options: ");
    //System.out.println("1: Retry Login");
    //System.out.println("2: Forgot Password?");

    //int loginAttemptChoice = in.nextInt();

//                if (loginAttemptChoice == 1) {
//                    //back to beginning
//                }

    //            else if (loginAttemptChoice == 2){
    //                System.out.println("Please enter your username:");
    //                String recoveryUsername = in.nextLine();
    //
    //                while(i2.hasNext()){
    //                    iteratingUser2 = i2.next();
    //                    if(iteratingUser2.getUsername().equalsIgnoreCase(recoveryUsername)){
    //                        String recoveryEmail = iteratingUser2.getEmail();
    //                        System.out.println("We have sent password recovery information to " + recoveryEmail);
    //                        System.out.println("You will now be redirected back to the login screen.");
    //
    //                        login();
    //                    }
    //                    else{
    //                        System.out.println("I'm sorry, we don't have any information associated with" +
    //                                "that username");
    //                        System.out.println("You will now be redirected back to the login screen.");
    //                        login();
    //                    }
    //
    //
    //                }
    //
    //
    //            }
}
