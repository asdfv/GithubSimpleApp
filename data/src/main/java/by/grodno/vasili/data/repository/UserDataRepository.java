package by.grodno.vasili.data.repository;

import java.util.List;

import by.grodno.vasili.data.datasource.UserDatasource;
import by.grodno.vasili.data.response.OrganizationMapper;
import by.grodno.vasili.data.response.UserMapper;
import by.grodno.vasili.domain.model.Organization;
import by.grodno.vasili.domain.model.User;
import by.grodno.vasili.domain.repository.UserRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Repository for work with {@link User}
 */
public class UserDataRepository implements UserRepository {
    private final UserDatasource userDatasource;
    private final UserMapper userMapper;
    private final OrganizationMapper organizationMapper;

    public UserDataRepository(UserDatasource userDatasource, UserMapper userMapper, OrganizationMapper organizationMapper) {
        this.userDatasource = userDatasource;
        this.userMapper = userMapper;
        this.organizationMapper = organizationMapper;
    }

    @Override
    public Maybe<User> getOne(String username) {
        return userDatasource.one(username).map(userMapper::map);
    }

    @Override
    public Single<List<User>> getAll(int since) {
        return userDatasource.all(since).map(userMapper::mapList);
    }

    @Override
    public Single<List<Organization>> getOrganizations(String username) {
        return userDatasource.organizations(username).map(organizationMapper::mapList);
    }
}
