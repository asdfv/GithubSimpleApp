package by.grodno.vasili.data.datasource.retrofit

import by.grodno.vasili.data.response.OrganizationResponse
import by.grodno.vasili.data.response.UserResponse
import io.reactivex.Maybe
import io.reactivex.Single
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
     * @return Observable list of [UserResponse].
     */
    @GET("users")
    fun getUsers(@Query("since") since: Int): Single<List<UserResponse>>

    /**
     * Get one user
     * [username] Username of user.
     * @return Observable [UserResponse].
     */
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Maybe<UserResponse>

    /**
     * Get organization for user.
     * [username] Username of user.
     * @return Single observable list of [OrganizationResponse].
     */
    @GET("users/{username}/orgs")
    fun getOrganizations(@Path("username") username: String): Single<List<OrganizationResponse>>
}
