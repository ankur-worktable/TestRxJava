package info.ankurpandya.testrxjava.responses

import com.google.gson.annotations.SerializedName

/**
 * Create by Ankur @ Worktable.sg
 */
class CountryResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("result") val countries: List<Country>
)