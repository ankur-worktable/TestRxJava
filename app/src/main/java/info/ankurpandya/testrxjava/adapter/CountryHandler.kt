package info.ankurpandya.testrxjava.adapter

import info.ankurpandya.testrxjava.api.responses.Country

/**
 * Create by Ankur @ Worktable.sg
 */
interface CountryHandler {
    fun onCountrySelected(country: Country)
}