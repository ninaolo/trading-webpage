package bean;

public class User {

    private String nickname;
    private String email;

    public User() {}

    // Setters

    public void setNicname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters
    
    public String getNickname() {
	   return nickname;
    }

    public String getEmail() {
	   return email;
    }

}
