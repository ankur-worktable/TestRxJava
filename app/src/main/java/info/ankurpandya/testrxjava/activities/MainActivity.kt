package info.ankurpandya.testrxjava.activities

import android.os.Bundle
import info.ankurpandya.testrxjava.R
import info.ankurpandya.testrxjava.api.responses.Country
import info.ankurpandya.testrxjava.fragments.FragmentCountryDetail
import info.ankurpandya.testrxjava.fragments.FragmentCountryList

class MainActivity : BaseActivity(), MainCallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(FragmentCountryList.getInstance())
    }

    override fun onCountrySelected(country: Country) {
        addFragment(FragmentCountryDetail.getInstance(country))
    }
}