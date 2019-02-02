package by.grodno.vasili.githubsimpleapp.model;

import by.grodno.vasili.domain.mapper.Mapper;
import by.grodno.vasili.domain.model.User;

/**
 * Mapper for conversation objects from data-layer into objects in domain-layer
 */
public class UserItemMapper extends Mapper<User, UserItem> {
    @Override
    public UserItem map(User user) {
        return new UserItem(user.id, user.login, user.avatarUrl);
    }
}
