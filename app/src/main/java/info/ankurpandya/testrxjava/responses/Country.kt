package info.ankurpandya.testrxjava.responses

import com.google.gson.annotations.SerializedName

/**
 * Create by Ankur @ Worktable.sg
 */
class Country(
    @SerializedName("name") val country: String,
    @SerializedName("code") val region: String
)