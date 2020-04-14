import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UISprint2 {

    GameBrowser gameBrowser;

    public UISprint2(String filepath) throws IllegalArgumentException, DataSourceException {
        gameBrowser = new GameBrowser(filepath);
    }

    private void login() {
        Scanner in = new Scanner(System.in);
        Account iteratingUser;
        Account iteratingUser2;
        System.out.println("Welcome to the Big Girl Game Library!");
        System.out.println("Please enter your username: ");

        String usernameEnter = in.nextLine();

        System.out.println("Please enter your password: ");

        String passwordEnter = in.nextLine();

        List<Account> userLoginList = gameBrowser.getUsers();

        Iterator<Account> i = userLoginList.iterator();
        Iterator<Account> i2 = userLoginList.iterator();

        while (i.hasNext()) {
            iteratingUser = i.next();
            if (iteratingUser.getUsername().equalsIgnoreCase(usernameEnter) && iteratingUser.getUsername()
                    .equalsIgnoreCase(passwordEnter)) {
                //call to appropriate function
                //then return
            } else {
                System.out.println("I'm sorry, either your username or password is incorrect.");
                System.out.println("Please select from the following options: ");
                System.out.println("1: Retry Login");
                System.out.println("2: Forgot Password?");

                int loginAttemptChoice = in.nextInt();

                if (loginAttemptChoice == 1){
                    //back to beginning
                    login();
                }

                else if (loginAttemptChoice == 2){
                    System.out.println("Please enter your username:");
                    String recoveryUsername = in.nextLine();

                    while(i2.hasNext()){
                        iteratingUser2 = i2.next();
                        if(iteratingUser2.getUsername.equalsIgnoreCase(recoveryUsername)){
                            String recoveryEmail = iteratingUser2.getEmail();
                            System.out.println("We have sent password recovery information to " + recoveryEmail);
                            System.out.println("You will now be redirected back to the login screen.");

                            login();
                        }
                        else{
                            System.out.println("I'm sorry, we don't have any information associated with" +
                                    "that username");
                            System.out.println("You will now be redirected back to the login screen.");
                            login();
                        }


                    }


                }

                else{
                    System.out.println("ERROR: Invalid Input.");
                    System.out.println("You are being redirected to the login page.");
                    //back to beginning
                    login();
                }
            }


        }
    }

    public static void main(String[] args) throws IOException, ParseException, DataSourceException {
        UISprint2 myBGGLTest = new UISprint2("src/databases/testing.db");
        myBGGLTest.login();

    }
}