package by.grodno.vasili.data.datasource.retrofit;

import java.util.List;

import by.grodno.vasili.data.response.OrganizationResponse;
import by.grodno.vasili.data.response.UserResponse;
import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApiService {
    /**
     * Get all users
     * @param since The integer ID of the last User that you've seen
     * @return Observable list of {@link UserResponse}
     */
    @GET("users")
    Single<List<UserResponse>> getUsers(@Query("since") int since);

    /**
     * Get one user
     * @param username Username of user
     * @return Observable {@link UserResponse}
     */
    @GET("users/{username}")
    Maybe<UserResponse> getUser(@Path("username") String username);

    /**
     * Get organization for user
     * @param username Username of user
     * @return Single observable list of {@link OrganizationResponse}
     */
    @GET("users/{username}/orgs")
    Single<List<OrganizationResponse>> getOrganizations(@Path("username") String username);
}
