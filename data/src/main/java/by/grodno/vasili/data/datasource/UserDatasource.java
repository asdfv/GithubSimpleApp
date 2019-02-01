package by.grodno.vasili.data.datasource;

import java.util.List;

import by.grodno.vasili.data.response.UserResponse;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Interface for work with {@link UserResponse}
 */
public interface UserDatasource {
    /**
     * Take all
     * @param since The integer ID of the last User that you've seen.
     */
    Single<List<UserResponse>> all(int since);

    /**
     * Take user by username
     * @param username Username of user
     */
    Maybe<UserResponse> one(String username);
}
