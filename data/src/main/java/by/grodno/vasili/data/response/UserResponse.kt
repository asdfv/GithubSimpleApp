package by.grodno.vasili.data.response

import by.grodno.vasili.domain.model.User
import com.google.gson.annotations.SerializedName

/**
 * Represent User model in data layer.
 */
data class UserResponse(
        val id: String,
        val login: String,
        val name: String? = null,
        @SerializedName("avatar_url") val avatarUrl: String? = null,
        val email: String? = null,
        @SerializedName("following") val followingCount: Int? = null,
        @SerializedName("followers") val followersCount: Int? = null,
        @SerializedName("created_at") val created: String? = null,
) {

    /**
     * Convert to domain model.
     */
    fun toDomainModel() = User(
            this.id,
            this.login,
            this.name ?: "-",
            this.avatarUrl!!,
            this.email ?: "-",
            emptyList(),
            this.followingCount ?: 0,
            this.followersCount ?: 0,
            this.created ?: "-"
    )
}
