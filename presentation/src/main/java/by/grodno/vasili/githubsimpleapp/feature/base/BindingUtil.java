package by.grodno.vasili.githubsimpleapp.feature.base;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

import by.grodno.vasili.githubsimpleapp.feature.user_details.OrganizationItem;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;


/**
 * Class for bindings utils and adapters
 */
public class BindingUtil {
    private static final String SEPARATOR = ", ";
    private static final String EMPTY_PLACEHOLDER = "-";

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url != null) {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }
    }

    @BindingAdapter("organizations")
    public static void setOrganization(TextView view, List<OrganizationItem> organizations) {
        if (isNotEmpty(organizations)) {
            final List<String> logins = new LinkedList<>();
            for (OrganizationItem organization : organizations) {
                logins.add(organization.login);
            }
            view.setText(StringUtils.join(logins, SEPARATOR));
        } else {
            view.setText(EMPTY_PLACEHOLDER);
        }
    }
}
