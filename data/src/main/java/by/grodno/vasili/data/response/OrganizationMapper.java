package by.grodno.vasili.data.response;

import by.grodno.vasili.domain.mapper.Mapper;
import by.grodno.vasili.domain.model.Organization;

/**
 * Mapper for conversation objects from data-layer into objects in domain-layer
 */
public class OrganizationMapper extends Mapper<OrganizationResponse, Organization> {
    @Override
    public Organization map(OrganizationResponse organizationResponse) {
        return new Organization(organizationResponse.login);
    }
}
