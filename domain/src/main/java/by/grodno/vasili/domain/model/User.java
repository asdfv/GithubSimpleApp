package by.grodno.vasili.domain.model;

public class User {
    public String name;
    public String email;
    public String avatarUrl;

    public User(String name, String email, String avatarUrl) {
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }
}
