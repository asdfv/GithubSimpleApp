package by.grodno.vasili.data.datasource.retrofit

import by.grodno.vasili.data.response.OrganizationResponse
import by.grodno.vasili.data.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service for work with users.
 */
interface UserApiService {
    /**
     * Get all users.
     * [since] - The integer ID of the last User that you've seen.
     */
    @GET("users")
    suspend fun getUsers(@Query("since") since: Int): List<UserResponse>

    /**
     * Get single user.
     * [username] Username of user.
     */
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): UserResponse

    /**
     * Get organization for user.
     * [username] Username of user.
     */
    @GET("users/{username}/orgs")
    suspend fun getOrganizations(@Path("username") username: String): List<OrganizationResponse>
}
