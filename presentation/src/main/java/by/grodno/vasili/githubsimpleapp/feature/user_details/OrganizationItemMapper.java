package by.grodno.vasili.githubsimpleapp.feature.user_details;

import by.grodno.vasili.domain.mapper.Mapper;
import by.grodno.vasili.domain.model.Organization;

/**
 * Converter for Organization from domain-layer to presentation layer
 */
public class OrganizationItemMapper extends Mapper<Organization, OrganizationItem> {

    @Override
    public OrganizationItem map(Organization organization) {
        return new OrganizationItem(organization.login);
    }
}
