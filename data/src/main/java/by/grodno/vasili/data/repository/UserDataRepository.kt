package by.grodno.vasili.data.repository

import by.grodno.vasili.data.datasource.UserDatasource
import by.grodno.vasili.data.response.OrganizationResponse
import by.grodno.vasili.data.response.UserResponse
import by.grodno.vasili.domain.model.Organization
import by.grodno.vasili.domain.model.User
import by.grodno.vasili.domain.repository.UserRepository
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Repository for work with [User].
 */
class UserDataRepository(private val userDatasource: UserDatasource) : UserRepository {
    override fun getOne(username: String): Maybe<User> {
        return userDatasource.one(username)
                .map { it.toDomainModel() }
    }

    override fun getAll(since: Int): Single<List<User>> {
        return userDatasource.all(since)
                .map { usersResponse -> usersResponse.map { it.toDomainModel() } }
    }

    override fun getOrganizations(username: String): Single<List<Organization>> {
        return userDatasource.organizations(username)
                .map { organizationsResponse -> organizationsResponse.map { it.toDomainModel() }
        }
    }
}