package info.ankurpandya.testrxjava.activities;

import android.view.View;

import info.ankurpandya.testrxjava.api.responses.Country;

/**
 * Create by Ankur @ Worktable.sg
 */
public interface MainCallBack {
    void onCountrySelected(Country country, View countryItemView);

    void onPresentationCompleted();
}
