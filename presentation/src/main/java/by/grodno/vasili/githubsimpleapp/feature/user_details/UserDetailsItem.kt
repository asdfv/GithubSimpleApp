package by.grodno.vasili.githubsimpleapp.feature.user_details

import by.grodno.vasili.domain.model.User

/**
 * User model for representing in details activity.
 */
data class UserDetailsItem(
        val id: String,
        val login: String,
        val name: String,
        val avatarUrl: String,
        val email: String,
        val organizations: List<OrganizationItem>,
        val followingCount: Int,
        val followersCount: Int,
        val created: String
) {
    constructor(user: User) : this(
            id = user.id,
            login = user.login,
            name = user.name,
            avatarUrl = user.avatarUrl,
            email = user.email,
            organizations = user.organizations.map { OrganizationItem(it) },
            followingCount = user.followingCount,
            followersCount = user.followersCount,
            created = user.created
    )
}
