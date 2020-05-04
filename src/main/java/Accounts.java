public class Accounts {
    private String email;
    private String username;
    private String password;
    public Developer dev;
    public User user;
    public Administrator admin;

    Accounts(String username, String email , String password){
        if(username==null || !isUsernameValid(username)) throw new IllegalArgumentException("invalid username");
        if(email==null || !isEmailValid(email)) throw new IllegalArgumentException("invalid email");
        if(password==null || !isPasswordValid(password)) throw new IllegalArgumentException("invalid password");

        this.email = email;
        this.username = username;
        this.password = password;
        dev = null;
        user = null;
        admin = null;
    }

    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public static final String prefix_invalid_endings = "-._";
    public static final String invalid_overall = "!#$%^&*()+=,<>/?;:'\"[{]}\\|";

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
