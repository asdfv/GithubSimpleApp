package by.grodno.vasili.githubsimpleapp.feature.user_details

import by.grodno.vasili.domain.model.Organization

/**
 * Organization model in presentation layer.
 */
data class OrganizationItem(var login: String?) {
    constructor(organization: Organization) : this(
            login = organization.login
    )
}
