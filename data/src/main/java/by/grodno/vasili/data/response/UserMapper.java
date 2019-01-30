package by.grodno.vasili.data.response;

import by.grodno.vasili.domain.mapper.Mapper;
import by.grodno.vasili.domain.model.User;

public class UserMapper extends Mapper<UserResponse, User> {
    @Override
    public User map(UserResponse userResponse) {
        return new User(userResponse.name, userResponse.email);
    }
}
