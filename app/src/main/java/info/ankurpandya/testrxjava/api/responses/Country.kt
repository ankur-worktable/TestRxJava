package info.ankurpandya.testrxjava.api.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Create by Ankur @ Worktable.sg
 */
data class Country(
    @SerializedName("flag") val flag: String,
    @SerializedName("cioc") val cioc: String,
    @SerializedName("name") val name: String,
    @SerializedName("alpha3Code") val alpha3Code: String,

    @SerializedName("population") val population: Int,
    @SerializedName("alpha2Code") val alpha2Code: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String,
    @SerializedName("demonym") val demonym: String,
    @SerializedName("area") val area: Double,
    @SerializedName("gini") val gini: Double,
    @SerializedName("latlng") val latlng: List<Double>,
    @SerializedName("topLevelDomain") val topLevelDomain: List<String>,
    @SerializedName("callingCodes") val callingCodes: List<String>,
    @SerializedName("altSpellings") val altSpellings: List<String>,
    @SerializedName("timezones") val timezones: List<String>,
    @SerializedName("borders") val borders: List<String>,
    @SerializedName("nativeName") val nativeName: String,
    @SerializedName("numericCode") val numericCode: String,
    @SerializedName("currencies") val currencies: List<Currency>,
    @SerializedName("languages") val languages: List<Language>,
    @SerializedName("regionalBlocs") val regionalBlocs: List<RegionalBloc>,
) : Serializable