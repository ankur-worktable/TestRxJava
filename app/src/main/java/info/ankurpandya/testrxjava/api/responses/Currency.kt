package info.ankurpandya.testrxjava.api.responses

import com.google.gson.annotations.SerializedName

/**
 * Create by Ankur @ Worktable.sg
 */
data class Currency(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
)