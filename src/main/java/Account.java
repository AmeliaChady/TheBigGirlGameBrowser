

enum AccountType {ADMINISTRATOR, MODERATOR, DEVELOPER, TYPICAlUSER, COMBO }

public class Account {

    private String username;
    private String email;
    private String password;
    private AccountType accountType;

    public static final String prefix_invalid_endings = "-._";
    public static final String invalid_overall = "!#$%^&*()+=,<>/?;:'\"[{]}\\|";

    public Account(String usernameIn, String emailIn, String passwordIn, AccountType accountTypeIn){
        if (isEmailValid(emailIn)){
            username = usernameIn;
            email = emailIn;
            password = passwordIn;
            accountType = accountTypeIn;
        }
        else{
            throw new IllegalArgumentException(emailIn + " is not a valid email address");
        }
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public AccountType getAccountType() {return accountType; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //end getters and setters

    public static boolean isEmailValid(String email){
        if(email.split("@").length != 2){
            return false;
        }

        for(char i : invalid_overall.toCharArray()){
            if(email.contains(i+"")){
                return false;
            }
        }

        String[] a = email.split("@");
        String prefix = a[0];
        String suffix = a[1];

        /// Prefix Tests
        if(prefix.length()==0){
            return false;
        }

        for(char i : prefix_invalid_endings.toCharArray()){
            if(prefix.endsWith(i+"")){
                return false;
            }
        }

        /// Suffix Tests
        String[] suffixsplit = suffix.split("\\.");

        if(suffixsplit.length < 2){
            return false;
        }

        if(suffixsplit[suffixsplit.length - 1].length() < 2) {
            return false;
        }

        return true;
    }

    public static boolean isUsernameValid(String username){
        if (username.length()<1 || username.length()>32){
            return false;
        }
        for (int i = 0; i < username.length(); i++){
            if(!isCharacterValid(username.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isPasswordValid(String password) {
        if (password.length()<1 || password.length()>128){
            return false;
        }
        for (int i = 0; i < password.length(); i++){
            if(!isCharacterValid(password.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isCharacterValid(char character){
        String current = String.valueOf(character);
        if (",'\"#;".contains(current)){
            return false;
        }
        return true;
    }
}
