package by.grodno.vasili.githubsimpleapp.model;

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
