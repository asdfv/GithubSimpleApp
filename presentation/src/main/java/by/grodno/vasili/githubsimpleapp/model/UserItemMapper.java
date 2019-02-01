package by.grodno.vasili.githubsimpleapp.model;

import by.grodno.vasili.domain.mapper.Mapper;
import by.grodno.vasili.domain.model.User;

public class UserItemMapper extends Mapper<User, UserItem> {
    @Override
    public UserItem map(User user) {
        return new UserItem(user.avatarUrl);
    }
}
