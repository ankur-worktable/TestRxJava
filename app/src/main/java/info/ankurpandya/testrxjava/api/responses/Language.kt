package info.ankurpandya.testrxjava.api.responses

import com.google.gson.annotations.SerializedName

/**
 * Create by Ankur @ Worktable.sg
 */
data class Language(
    @SerializedName("iso639_1") val iso639_1: String,
    @SerializedName("iso639_2") val iso639_2: String,
    @SerializedName("name") val name: String,
    @SerializedName("nativeName") val nativeName: String,
)