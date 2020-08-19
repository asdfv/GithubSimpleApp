package by.grodno.vasili.data.repository

import by.grodno.vasili.data.datasource.UserDatasource
import by.grodno.vasili.domain.model.Organization
import by.grodno.vasili.domain.model.User
import by.grodno.vasili.domain.repository.UserRepository

/**
 * Repository for work with [User].
 */
class UserDataRepository(
        private val userDatasource: UserDatasource
) : UserRepository {
    override suspend fun getOne(id: String): User = userDatasource.one(id).toDomainModel()

    override suspend fun getAll(since: Int): List<User> = userDatasource.all(since).map { it.toDomainModel() }

    override suspend fun getOrganizations(username: String): List<Organization> = userDatasource.organizations(username).map { it.toDomainModel() }
}
