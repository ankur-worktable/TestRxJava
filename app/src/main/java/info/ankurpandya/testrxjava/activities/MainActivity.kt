package info.ankurpandya.testrxjava.activities

import android.os.Bundle
import androidx.navigation.findNavController
import info.ankurpandya.testrxjava.R
import info.ankurpandya.testrxjava.api.responses.Country
import info.ankurpandya.testrxjava.fragments.homecountrylist.FragmentCountryListDirections

class MainActivity : BaseActivity(), MainCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCountrySelected(country: Country) {
        val directions = FragmentCountryListDirections.actionOpenCountryDetail(country)
        findNavController(R.id.nav_host_fragment).navigate(directions)
    }
}