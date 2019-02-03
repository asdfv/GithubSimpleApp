package by.grodno.vasili.githubsimpleapp.feature.user_details;

import by.grodno.vasili.domain.mapper.Mapper;
import by.grodno.vasili.domain.model.User;

/**
 * Convert {@link User} from domain-layer to {@link UserDetailsItem} in presentation-layer
 */
public class UserDetailsItemMapper extends Mapper<User, UserDetailsItem> {
    private final OrganizationItemMapper organizationItemMapper;

    UserDetailsItemMapper(OrganizationItemMapper organizationItemMapper) {
        this.organizationItemMapper = organizationItemMapper;
    }

    @Override
    public UserDetailsItem map(User user) {
        return new UserDetailsItem(
                user.id,
                user.login,
                user.name,
                user.avatarUrl,
                user.email,
                organizationItemMapper.mapList(user.organizations),
                user.followingCount,
                user.followersCount,
                user.created
        );
    }
}
