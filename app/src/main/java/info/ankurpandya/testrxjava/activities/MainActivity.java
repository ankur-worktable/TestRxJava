package info.ankurpandya.testrxjava.activities;

import android.os.Bundle;

import info.ankurpandya.testrxjava.R;
import info.ankurpandya.testrxjava.fragments.FragmentCountryList;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(FragmentCountryList.getInstance());
    }
}