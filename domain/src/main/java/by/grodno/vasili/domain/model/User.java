package by.grodno.vasili.domain.model;

public class User {
    public String id;
    public String login;
    public String avatarUrl;

    public User(String id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
}
