package by.grodno.vasili.domain.repository

import by.grodno.vasili.domain.model.Organization
import by.grodno.vasili.domain.model.User

/**
 * Implement this interface for work with User
 */
interface UserRepository {
    /**
     * Takes one [User] from datasource by id.
     * [id] [User] identifier.
     */
    suspend fun getOne(id: String): User

    /**
     * Takes all [User]`s from datasource.
     * [since] The integer ID of the last User that you've seen.
     */
    suspend fun getAll(since: Int): List<User>

    /**
     * Take organizations for user from datasource.
     *
     * [username] Username of user.
     */
    suspend fun getOrganizations(username: String): List<Organization>
}
