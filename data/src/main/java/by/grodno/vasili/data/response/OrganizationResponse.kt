package by.grodno.vasili.data.response

import by.grodno.vasili.domain.model.Organization

/**
 * Represent Organization model in data layer.
 */
data class OrganizationResponse(val login: String? = null) {
    /**
     * Convert to domain model.
     */
    fun toDomainModel() = Organization(login)
}
