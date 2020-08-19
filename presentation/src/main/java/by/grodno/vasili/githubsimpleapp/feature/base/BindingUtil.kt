package by.grodno.vasili.githubsimpleapp.feature.base

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import by.grodno.vasili.githubsimpleapp.feature.user_details.OrganizationItem
import com.bumptech.glide.Glide
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils
import java.util.*

/**
 * Class for bindings utils and adapters
 */
object BindingUtil {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("organizations")
    fun setOrganization(view: TextView, organizations: List<OrganizationItem?>?) {
        if (organizations?.isNotEmpty() == true) {
            val logins: MutableList<String?> = LinkedList()
            for (organization in organizations) {
                logins.add(organization!!.login)
            }
            view.text = StringUtils.join(logins, ", ")
        } else {
            view.text = "-"
        }
    }
}
