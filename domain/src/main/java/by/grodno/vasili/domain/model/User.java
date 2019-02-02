package by.grodno.vasili.domain.model;

public class User {
    public String id;
    public String login;
    public String name;
    public String avatarUrl;
    public String email;
    public String organizationsUrl;
    public Integer followingCount;
    public Integer followersCount;
    public String created;

    public User(
            String id,
            String login,
            String name,
            String avatarUrl,
            String email,
            String organizationsUrl,
            Integer followingCount,
            Integer followersCount,
            String created
    ) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.organizationsUrl = organizationsUrl;
        this.followingCount = followingCount;
        this.followersCount = followersCount;
        this.created = created;
    }
}
