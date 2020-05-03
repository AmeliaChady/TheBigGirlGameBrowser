public class Accounts {
    private String email;
    private String username;
    private String password;
    public Developer dev;
    public User user;
    public Administrator admin;

    Accounts(Developer dev, User user, Administrator admin){
        this.dev = dev;
        this.admin = admin;
        this.user = user;
    }

    Accounts(Developer dev, User user){
        this.dev = dev;
        this.user = user;
        admin = null;
    }

    Accounts(User user, String email, String username, String password) {
        this.user = user;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dev = null;
        admin = null;
    }
    Accounts(Developer dev, String email, String username, String password) {
        this.dev = dev;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dev = null;
        admin = null;
    }

    Accounts(Administrator admin){
        this.admin = admin;
        dev = null;
        user = null;
    }

    Accounts(){
        dev = null;
        user = null;
        admin = null;
    }

    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
}
