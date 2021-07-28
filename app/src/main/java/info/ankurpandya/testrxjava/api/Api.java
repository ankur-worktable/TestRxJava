package info.ankurpandya.testrxjava.api;

import java.util.List;

import info.ankurpandya.testrxjava.api.responses.Country;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Create by Ankur @ Worktable.sg
 */
public interface Api {

    //String BASE_URL = "https://api.printful.com/";

    //@GET("countries")
    @GET("all?fields=name;flag;cioc;alpha3Code;alpha2Code")
    Observable<List<Country>> getCountries();

    @GET("alpha/{countryCode}")
    Observable<Country> getCountryDetail(@Path("countryCode") String countryCode);
}
