package bean;

public class User {

    private String nickname;
    private String email;
    private int id;

    public User() {
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void setNickname(String s) {
        this.nickname = s;
    }

    public void setEmail(String s) {
        this.email = s;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
