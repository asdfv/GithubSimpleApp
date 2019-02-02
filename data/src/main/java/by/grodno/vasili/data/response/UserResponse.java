package by.grodno.vasili.data.response;

import com.google.gson.annotations.SerializedName;

/**
 * The entity represent UserResponse model in data layer
 */
public class UserResponse {
    String id;
    String login;
    @SerializedName("avatar_url") String avatarUrl;
}
