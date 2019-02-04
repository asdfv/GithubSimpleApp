package by.grodno.vasili.githubsimpleapp.feature.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Basic class for activities
 *
 * @param <VDB> databinding class
 */
public abstract class BaseActivity<VDB extends ViewDataBinding> extends AppCompatActivity {
    protected VDB binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        binding = DataBindingUtil.setContentView(this, getContentView());
    }

    protected abstract int getContentView();

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
