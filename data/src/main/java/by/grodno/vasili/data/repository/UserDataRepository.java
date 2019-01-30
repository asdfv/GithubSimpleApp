package by.grodno.vasili.data.repository;

import java.util.List;

import by.grodno.vasili.data.datasource.UserDatasource;
import by.grodno.vasili.data.response.UserMapper;
import by.grodno.vasili.domain.model.User;
import by.grodno.vasili.domain.repository.UserRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class UserDataRepository implements UserRepository {
    private final UserDatasource userDatasource;
    private final UserMapper mapper;

    public UserDataRepository(UserDatasource userDatasource, UserMapper mapper) {
        this.userDatasource = userDatasource;
        this.mapper = mapper;
    }

    @Override
    public Maybe<User> getOne(String username) {
        return userDatasource.one(username).map(mapper::map);
    }

    @Override
    public Single<List<User>> getAll(int since) {
        return userDatasource.all(since).map(mapper::mapList);
    }
}
