package info.ankurpandya.testrxjava.api.responses

import com.google.gson.annotations.SerializedName

/**
 * Create by Ankur @ Worktable.sg
 */
data class RegionalBloc(
    @SerializedName("acronym") val acronym: String,
    @SerializedName("otherAcronyms") val otherAcronyms: List<Object>,
    @SerializedName("name") val name: String,
    @SerializedName("otherNames") val otherNames: List<Object>
)