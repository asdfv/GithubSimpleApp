package by.grodno.vasili.data.response;

import by.grodno.vasili.domain.mapper.Mapper;
import by.grodno.vasili.domain.model.User;

/**
 * Mapper for conversation objects from data-layer into objects in domain-layer
 */
public class UserMapper extends Mapper<UserResponse, User> {
    @Override
    public User map(UserResponse response) {
        return new User(
                response.id,
                response.login,
                response.name,
                response.avatarUrl,
                response.email,
                response.organizationsUrl,
                response.followingCount,
                response.followersCount,
                response.created
        );
    }
}
