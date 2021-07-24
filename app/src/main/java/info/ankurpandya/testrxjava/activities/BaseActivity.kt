package info.ankurpandya.testrxjava.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import info.ankurpandya.testrxjava.R

/**
 * Create by Ankur @ Worktable.sg
 */
abstract class BaseActivity : AppCompatActivity() {

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(fragment.javaClass.name)
            .commit()
    }
}