package info.ankurpandya.testrxjava.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import info.ankurpandya.testrxjava.R;

/**
 * Create by Ankur @ Worktable.sg
 */
public abstract class BaseActivity extends AppCompatActivity {

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }
}
