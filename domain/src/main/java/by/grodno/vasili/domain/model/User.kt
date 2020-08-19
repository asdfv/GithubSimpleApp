package by.grodno.vasili.domain.model

class User(
        val id: String,
        val login: String,
        val name: String,
        val avatarUrl: String,
        val email: String,
        @JvmField var organizations: List<Organization> = emptyList(),
        val followingCount: Int,
        val followersCount: Int,
        val created: String
)
