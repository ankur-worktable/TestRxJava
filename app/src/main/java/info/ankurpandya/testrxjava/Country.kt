package info.ankurpandya.testrxjava

import com.google.gson.annotations.SerializedName

/**
 * Create by Ankur @ Worktable.sg
 */
data class Country(
    @SerializedName("name") val country: String,
    @SerializedName("code") val region: String
)