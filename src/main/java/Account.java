public class Account {

    private String username;
    private String email;
    private String password;

    public Account(String usernameIn, String emailIn, String passwordIn){

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
        return false;
    }
}
