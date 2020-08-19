package by.grodno.vasili.data.datasource

import by.grodno.vasili.data.response.OrganizationResponse
import by.grodno.vasili.data.response.UserResponse

/**
 * Interface for work with User data.
 */
interface UserDatasource {
    /**
     * Take all.
     *
     * [since] The integer ID of the last User that you've seen.
     */
    suspend fun all(since: Int): List<UserResponse>

    /**
     * Take user by username
     *
     * [username] Username of user.
     */
    suspend fun one(username: String): UserResponse

    /**
     * Take organizations for user
     *
     * [username] Username of user.
     */
    suspend fun organizations(username: String): List<OrganizationResponse>
}
