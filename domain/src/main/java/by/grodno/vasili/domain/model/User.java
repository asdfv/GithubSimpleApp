package by.grodno.vasili.domain.model;

import java.util.List;

public class User {
    public String id;
    public String login;
    public String name;
    public String avatarUrl;
    public String email;
    public List<Organization> organizations;
    public Integer followingCount;
    public Integer followersCount;
    public String created;

    public User(
            String id,
            String login,
            String name,
            String avatarUrl,
            String email,
            List<Organization> organizations,
            Integer followingCount,
            Integer followersCount,
            String created
    ) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.organizations = organizations;
        this.followingCount = followingCount;
        this.followersCount = followersCount;
        this.created = created;
    }
}
