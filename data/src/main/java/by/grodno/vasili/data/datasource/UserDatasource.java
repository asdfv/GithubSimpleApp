package by.grodno.vasili.data.datasource;

import java.util.List;

import by.grodno.vasili.data.response.OrganizationResponse;
import by.grodno.vasili.data.response.UserResponse;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Interface for work with User data
 */
public interface UserDatasource {
    /**
     * Take all
     *
     * @param since The integer ID of the last User that you've seen.
     * @return Observable list of {@link UserResponse}`s
     */
    Single<List<UserResponse>> all(int since);

    /**
     * Take user by username
     *
     * @param username Username of user
     * @return Observable {@link UserResponse}
     */
    Maybe<UserResponse> one(String username);

    /**
     * Take organizations for user
     *
     * @param username Username of user
     * @return Single observable list of {@link OrganizationResponse}`s
     */
    Single<List<OrganizationResponse>> organizations(String username);
}
