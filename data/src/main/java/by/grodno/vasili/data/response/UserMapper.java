package by.grodno.vasili.data.response;

import by.grodno.vasili.domain.mapper.Mapper;
import by.grodno.vasili.domain.model.User;

/**
 * Mapper for conversation objects from data-layer into objects in domain-layer
 */
public class UserMapper extends Mapper<UserResponse, User> {
    @Override
    public User map(UserResponse userResponse) {
        return new User(userResponse.id, userResponse.login, userResponse.avatarUrl);
    }
}
