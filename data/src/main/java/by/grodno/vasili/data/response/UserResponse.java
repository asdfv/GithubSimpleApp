package by.grodno.vasili.data.response;

import com.google.gson.annotations.SerializedName;

/**
 * The entity represent UserResponse model in data layer
 */
public class UserResponse {
    String id;
    String login;
    String name;
    @SerializedName("avatar_url") String avatarUrl;
    String email;
    @SerializedName("organizations_url") String organizationsUrl;
    @SerializedName("following") Integer followingCount;
    @SerializedName("followers") Integer followersCount;
    @SerializedName("created_at") String created;
}
