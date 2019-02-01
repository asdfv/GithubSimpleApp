package by.grodno.vasili.domain.repository;

import java.util.List;

import by.grodno.vasili.domain.model.User;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Implement this interface for work with User
 */
public interface UserRepository {
    /**
     * Takes one {@link User} from datasource by id
     * @param id {@link User} identifier
     * @return Single observable {@link User}
     */
    Maybe<User> getOne(String id);

    /**
     * Takes all {@link User}`s from datasource
     * @param since The integer ID of the last User that you've seen
     * @return list of {@link User}
     */
    Single<List<User>> getAll(int since);
}
