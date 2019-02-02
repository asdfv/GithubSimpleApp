package by.grodno.vasili.githubsimpleapp.feature.users;

/**
 * User model for presentation in list of Users
 */
public class UserItem {
    public String id;
    public String login;
    public String avatarUrl;

    UserItem(String id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
}
