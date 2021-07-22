package info.ankurpandya.testrxjava.responses

import com.google.gson.annotations.SerializedName
import info.ankurpandya.testrxjava.Country

/**
 * Create by Ankur @ Worktable.sg
 */
data class CountryResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("result") val countries: List<Country>
)