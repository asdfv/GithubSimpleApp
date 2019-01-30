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
     */
    Single<List<UserResponse>> all(int since);

    /**
     * Take by id
     */
    Maybe<UserResponse> one(String username);
}
