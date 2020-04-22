import java.text.ParseException;
import java.util.Iterator;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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


            if (userAccounts.admin != null) {
                System.out.println("Logged IN! Welcome " + userAccounts.admin.getUsername());
                administratorTakeAction(userAccounts.admin);
            } else if (userAccounts.user != null && userAccounts.dev == null) {
                System.out.println("Logged IN! you own " + userAccounts.user.getOwnedGames().getGames().size() + " Games!");
                //commercialUserTakeAction(userAccounts.user); TODO
            } else if (userAccounts.user == null && userAccounts.dev != null) {
                System.out.println("Logged IN! Welcome " + userAccounts.dev.getName());
                //developerTakeAction(userAccounts.user); TODO
            } else if (userAccounts.user != null && userAccounts.dev != null) {
                System.out.println("Please enter the number corresponding with the account you'd like " +
                        "to use:");
                System.out.println("1: Developer");
                System.out.println("2: Commercial User");

                int comboChoice = 0;
                while (comboChoice != 1 && comboChoice != 2) {
                    comboChoice = in.nextInt();
                    if (comboChoice == 1) {
                        System.out.println("Logged IN! Welcome " + userAccounts.dev.getName());
                        //developerTakeAction(userAccounts.user); TODO
                    } else if (comboChoice == 2) {
                        System.out.println("Logged IN! you own " + userAccounts.user.getOwnedGames().getGames().size() + " Games!");
                        //commercialUserTakeAction(userAccounts.user); TODO
                    } else {
                        System.out.println("ERROR: Not valid input");
                    }
                }
            } else {
                System.out.println("I'm sorry, either your username or password is incorrect.");
                System.out.println("Please select from the following options: ");
                System.out.println("1: Retry Login");
                //System.out.println("2: Forgot Password?");

                int loginAttemptChoice = in.nextInt();

                if (loginAttemptChoice == 1) {
                    //back to beginning
                }

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

                else {
                    System.out.println("ERROR: Invalid Input.");
                    System.out.println("You are being redirected to the login page.");
                }
                //back to beginning
                login();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\nPlease try again");
            login();
        }
    }

    //ADMINISTRATOR UI

    private void administratorTakeAction(Administrator adminAccount) throws DataSourceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please choose what action you'd like to take:");
        System.out.println("1: Review Pending Games");
        System.out.println("2: Review Approved Games");
        System.out.println("3: Logout");

        int adminChoice = in.nextInt();

        while (adminChoice < 1 || adminChoice > 3) {
            System.out.println("Please enter a valid choice");
            adminChoice = in.nextInt();
        }

        if (adminChoice == 1) {

            //display pending games
            gameBrowser.pullGameList("Master Game List");
            System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.PENDING));

            System.out.println("Please choose which pending game you'd like to review, or press 0 to exit:");

            int devReviewGameChoice = in.nextInt();
            while (devReviewGameChoice < 0 || devReviewGameChoice > gameBrowser.getGameList().getGameCount()) {
                System.out.println("Please enter a valid choice");
                System.out.println("Please choose which pending game you'd like to review, or press 0 to exit:");
                devReviewGameChoice = in.nextInt();
            }

            if (devReviewGameChoice == 0) {
                administratorTakeAction(adminAccount);
                //TODO: change else if to the amount of games in the list
            } else if (devReviewGameChoice >= 1 && devReviewGameChoice <= 10) {

                GameList pendingGames = gameBrowser.getGamesGivenStatus(Status.PENDING);
                String devReviewGame = pendingGames.getGames().get(devReviewGameChoice);

                Game chosenGame = gameBrowser.loadGame(devReviewGame);

                System.out.println("Would you like to accept or reject this game?");
                System.out.println("1: Approve");
                System.out.println("2: Reject");

                int devApproveReject = in.nextInt();

                if (devApproveReject == 1) {
                    chosenGame.changeStatus(Status.ACCEPTED);
                    gameBrowser.saveGame(chosenGame);
                    System.out.println("Game has been approved");
                    administratorTakeAction(adminAccount);
                } else if (devApproveReject == 2) {
                    String removeGameTitle = chosenGame.getTitle();
                    chosenGame.changeStatus(Status.REJECTED);
                    gameBrowser.saveGame(chosenGame);
                    gameBrowser.removeGame(removeGameTitle);
                    //save gamelist
                    System.out.println("Game has been rejected.");
                    administratorTakeAction(adminAccount);
                } else {
                    System.out.println("ERROR: Invalid Response");
                    administratorTakeAction(adminAccount);
                }
            }

        } else if (adminChoice == 2) {
            System.out.println("Approved Games:");

            //display games with an accepted status
            gameBrowser.pullGameList("Master Game List");
            System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.ACCEPTED));

//            if (gameBrowser.getGameList().getGamesGivenStatus(Status.ACCEPTED).getGameCount()>0){
            System.out.println("Would you like to remove any games?");
            System.out.println("1: Yes");
            System.out.println("2: No");
            int adminApprovedChoice = in.nextInt();
            while (adminApprovedChoice < 1 || adminApprovedChoice > 2) {
                System.out.println("Please enter a valid choice");
                adminApprovedChoice = in.nextInt();
//                }

                if (adminApprovedChoice == 1) {
                    System.out.println("Please select the game you would like to remove, or 0 to cancel");
                    gameBrowser.pullGameList("Master Game List");
                    System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.ACCEPTED));
                    int adminRemoveChoice = in.nextInt();
                    //TODO: while (adminRemoveChoice<0 || adminRemoveChoice>gameBrowser.getGameList().getGamesGivenStatus(Status.ACCEPTED).getGameCount()){
                    System.out.println("Please enter a valid choice");
                    System.out.println("Please select the game you would like to remove, or 0 to cancel");
                    adminRemoveChoice = in.nextInt();
                }
                //TODO: Game adminRemoveGame = keepListOfGamesGivenStatus(Status.ACCEPTED, adminRemoveChoice, gameBrowser.getGameList());

                //TODO: String removeGameTitle = adminRemoveGame.getTitle();
//                    if(adminRemoveChoice!=0){
//                        //TODO: gameBrowser.removeGame(removeGameTitle);
                System.out.println("The game has been removed.");
                System.out.println("Thank you!");
            }
            administratorTakeAction(adminAccount);

            administratorTakeAction(adminAccount);

//            else{
//                System.out.println("No approved games\n");
//                administratorTakeAction(adminAccount);
//            }
//
//            else if (adminChoice == 3) {
//                System.out.println("Thank you for using the Big Girl Game Library.");
//                System.out.println("See you soon!");
//                login();
//            }
        }

    }


    //DEVELOPER UI


    private void developerTakeAction(Developer devAccount) throws DataSourceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please choose what action you'd like to take:");
        System.out.println("1: Submit Game");
        System.out.println("2: Update Game");
        System.out.println("3: View Your Game List");
        System.out.println("4: Logout");

        int devChoice = in.nextInt();
        while (devChoice < 1 || devChoice > 4) {
            System.out.println("Please enter a valid choice");
            devChoice = in.nextInt();
        }

        if (devChoice == 1) {
            System.out.println("Please enter the title of your game:");
            in.nextLine();
            String gameName = in.nextLine();
            System.out.println("Please enter the description of your game:");

            String gameDescription = in.nextLine();

            Game testGame = new Game(gameName, gameDescription, devAccount.getName(), Status.PENDING);
            gameBrowser.addGame(testGame);

            System.out.println("Thank you! Your game has been submitted and is under review.");
            System.out.println("Expect a response in your inbox shortly.");

            //back to menu screen
            developerTakeAction(devAccount);
        } else if (devChoice == 2) {

            gameBrowser.pullGameList(devAccount.getGameList().getName());
            System.out.println(gameBrowser.displayableAllGames());

            System.out.println("Please select the game that you'd like to update, or press 0 to cancel:");
            int devUpdateChoice = in.nextInt();
            while (devUpdateChoice < 0 || devUpdateChoice > devAccount.getGameList().getGameCount()) {
                System.out.println("Please pick a valid choice\n");
                System.out.println("Please select the game that you'd like to update, or press 0 to cancel:");
                devUpdateChoice = in.nextInt();
            }
            if (devUpdateChoice == 0) {
                developerTakeAction(devAccount);
            }

            Game updatingGame = gameBrowser.loadGame(devAccount.getGameList().getGames().get(devUpdateChoice - 1));


            System.out.println("Please select one of the following options:");
            System.out.println("1: Update Title");
            System.out.println("2: Update Bio");

            int devModifyChoice = in.nextInt();
            while (devModifyChoice < 1 || devModifyChoice > 2) {
                System.out.println("Please pick a valid choice\n");
                System.out.println("Please select one of the following options:");
                System.out.println("1: Update Title");
                System.out.println("2: Update Bio");

                devModifyChoice = in.nextInt();
            }

            if (devModifyChoice == 1) {
                System.out.println("Please enter the new game title:");
                in.nextLine();
                String updatedTitle = in.nextLine();
                updatingGame.changeTitle(updatedTitle);
                gameBrowser.addGame(updatingGame);

                System.out.println("Title Updated!");
                developerTakeAction(devAccount);
            } else if (devModifyChoice == 2) {
                System.out.println("Please enter the new game bio:");
                in.nextLine();
                String updatedBio = in.nextLine();
                updatingGame.changeDescription(updatedBio);
                gameBrowser.addGame(updatingGame);

                System.out.println("Bio updated!");
                developerTakeAction(devAccount);
            } else {
                System.out.println("ERROR: Invalid answer.");
                System.out.println("Please try again.");

                developerTakeAction(devAccount);
            }

        } else if (devChoice == 3) {

            gameBrowser.pullGameList(devAccount.getGameList().getName());
            System.out.println(gameBrowser.displayableAllGames());

            developerTakeAction(devAccount);

        }

        else if (devChoice == 4) {
            System.out.println("Thank you for using the Big Girl Game Library");
            System.out.println("See you soon!");

            login();
        } else {
            System.out.println("ERROR: Invalid answer.");
            System.out.println("Please try again.");
            //back to the top of the list
            developerTakeAction(devAccount);
        }

    }


    private void commercialUserTakeAction(User userAccount) throws DataSourceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please choose the action you'd like to take");
        System.out.println("1: View All Games");
        System.out.println("2: Add Game to Owned Games List");
        System.out.println("3: Remove Game from Owned Games List");
        System.out.println("4: View Owned Games List");
        System.out.println("5: Logout");

        int userChoice = in.nextInt();
        while (userChoice < 1 || userChoice > 4) {
            System.out.println("Please enter a valid choice");
            userChoice = in.nextInt();
        }

        if (userChoice == 1) {
            gameBrowser.pullGameList("Master Game List");
            System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.ACCEPTED));
            commercialUserTakeAction(userAccount);
        }

        if (userChoice == 2) {
            gameBrowser.pullGameList("Master Game List");
            System.out.println(gameBrowser.displayableNumberedListOfGamesGivenStatus(Status.ACCEPTED));

            System.out.println("Please enter the number of the game you'd like to add to your owned list");
            System.out.println("Or press 0 to exit.");

            int userAddChoice = in.nextInt();

            if (userAddChoice == 0) {
                //back to user menu
                commercialUserTakeAction(userAccount);
                //TODO: make sure this length of approved games list, not all games list
            } else if (userAddChoice > 1 || userAddChoice < gameBrowser.getGameList().getGameCount()) {

                //get game based on user input
                GameList approvedGames = gameBrowser.getGamesGivenStatus(Status.ACCEPTED);
                String devReviewGame = approvedGames.getGames().get(userAddChoice);
                Game gameToAdd = gameBrowser.loadGame(devReviewGame);

                gameBrowser.addGameToUserGameList(userAccount, gameToAdd);

                System.out.println("Game has successfully been added to your list.");
                commercialUserTakeAction(userAccount);
            }

        }
        if (userChoice == 3) {
            System.out.println("Owned Games:");
            gameBrowser.pullGameList(userAccount.getOwnedGames().getName());
            System.out.println(gameBrowser.displayableAllGames());

            System.out.println("Please select the game that you'd like to remove, or press 0 to cancel:");
            int removeOwnedListChoice = in.nextInt();
            if (removeOwnedListChoice == 0){
                commercialUserTakeAction(userAccount);
            }
            while (removeOwnedListChoice < 0 || removeOwnedListChoice > userAccount.getOwnedGames().getGameCount()){

                int removeListChoice = in.nextInt();
            }


            //Game updatingGame = gameBrowser.loadGame(devAccount.getGameList().getGames().get(devUpdateChoice - 1));


        }

        if (userChoice == 4) {
            gameBrowser.pullGameList(userAccount.getOwnedGames().getName());
            System.out.println(gameBrowser.displayableAllGames());
        }

        if(userChoice == 5){
            System.out.println("Thank you for using the Big Girl Game Browser.");
            System.out.println("Have a good one!");
            login();
        }


    }


    public static void main(String[] args) throws IOException, ParseException, DataSourceException {
        UISprint2 myBGGLTest = new UISprint2("src/databases/Test_SQLiteSource/login.db");
        myBGGLTest.login();

    }
}
