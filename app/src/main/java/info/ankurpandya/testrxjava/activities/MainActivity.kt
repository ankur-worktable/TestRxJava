package info.ankurpandya.testrxjava.activities

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import info.ankurpandya.testrxjava.R
import info.ankurpandya.testrxjava.api.responses.Country
import info.ankurpandya.testrxjava.fragments.homecountrylist.FragmentCountryListDirections
import info.ankurpandya.testrxjava.fragments.splash.FragmentSplashDirections

class MainActivity : BaseActivity(), MainCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPresentationCompleted() {
        val directions = FragmentSplashDirections.actionShowHomepage()
        //findNavController(R.id.nav_host_fragment).popBackStack()
        findNavController(R.id.nav_host_fragment).navigate(directions)
    }

    override fun onCountrySelected(country: Country, countryItemView: View) {
        val emailCardDetailTransitionName = "email_card_detail_transition_name"
        val extras = FragmentNavigatorExtras(countryItemView to emailCardDetailTransitionName)
        val directions = FragmentCountryListDirections.actionOpenCountryDetail(country)
        findNavController(R.id.nav_host_fragment).navigate(directions, extras)
    }
}