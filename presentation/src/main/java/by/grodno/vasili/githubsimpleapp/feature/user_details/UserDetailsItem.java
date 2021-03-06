package by.grodno.vasili.githubsimpleapp.feature.user_details;

import java.util.List;

/**
 * User model for representing in details activity
 */
public class UserDetailsItem {
    public String id;
    public String login;
    public String name;
    public String avatarUrl;
    public String email;
    public List<OrganizationItem> organizations;
    public Integer followingCount;
    public Integer followersCount;
    public String created;

    UserDetailsItem(String id, String login, String name, String avatarUrl, String email, List<OrganizationItem> organizations, Integer followingCount, Integer followersCount, String created) {
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
