package by.grodno.vasili.domain.model;

public class User {
    public String id;
    public String name;
    public String avatarUrl;

    public User(String id, String name, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }
}
