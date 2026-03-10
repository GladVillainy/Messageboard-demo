package app.entities;

public class User {
    private long user_ID;

    private String username;
    private String password;

    public User(long user_ID, String username, String password) {
        this.user_ID = user_ID;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
