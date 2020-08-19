package by.grodno.vasili.data.datasource

import by.grodno.vasili.data.response.OrganizationResponse
import by.grodno.vasili.data.response.UserResponse
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Interface for work with User data.
 */
interface UserDatasource {
    /**
     * Take all.
     *
     * @param since The integer ID of the last User that you've seen.
     * @return Observable list of [UserResponse]`s.
     */
    fun all(since: Int): Single<List<UserResponse>>

    /**
     * Take user by username
     *
     * @param username Username of user.
     * @return Observable [UserResponse].
     */
    fun one(username: String): Maybe<UserResponse>

    /**
     * Take organizations for user
     *
     * @param username Username of user.
     * @return Single observable list of [OrganizationResponse]`s.
     */
    fun organizations(username: String): Single<List<OrganizationResponse>>
}
