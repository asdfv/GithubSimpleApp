package by.grodno.vasili.githubsimpleapp.feature.user_details

import by.grodno.vasili.domain.model.Organization

// todo remove @JvmField after full Kotlin comes
/**
 * Organization model in presentation layer.
 */
data class OrganizationItem(@JvmField var login: String) {
    constructor(organization: Organization) : this(
            login = organization.login
    )
}
