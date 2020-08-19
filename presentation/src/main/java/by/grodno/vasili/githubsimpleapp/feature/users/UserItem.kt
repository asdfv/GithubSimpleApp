package by.grodno.vasili.githubsimpleapp.feature.users

import by.grodno.vasili.domain.model.User

/**
 * User model for presentation layer.
 */
data class UserItem(
        @JvmField var id: String,
        @JvmField var login: String,
        @JvmField var avatarUrl: String
) {
    constructor(user: User) : this(
            id = user.id,
            login = user.login,
            avatarUrl = user.avatarUrl
    )
}
