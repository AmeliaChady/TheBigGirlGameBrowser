public class Account {

    private String username;
    private String email;
    private String password;

    public static final String prefix_invalid_endings = "-._";
    public static final String invalid_overall = "!#$%^&*()+=,<>/?;:'\"[{]}\\|";

    public Account(String usernameIn, String emailIn, String passwordIn){
        if (isEmailValid(emailIn)){
            username = usernameIn;
            email = emailIn;
            password = passwordIn;
        }
        else{
            throw new IllegalArgumentException(emailIn + " is not a valid email address");
        }
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

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
        return false;
    }
}
