package by.grodno.vasili.githubsimpleapp.feature.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Basic class for activities
 *
 * @param <VDB> databinding class
 */
abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: VDB

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        binding = DataBindingUtil.setContentView(this, contentView)
    }

    protected abstract val contentView: Int
    protected fun showToast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
