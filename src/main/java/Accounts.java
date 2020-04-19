public class Accounts {
    public Developer dev;
    public User user;
    public Administrator admin;

    Accounts(Developer dev, User user){
        this.dev = dev;
        this.user = user;
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
}
