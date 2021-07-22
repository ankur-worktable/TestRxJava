package info.ankurpandya.testrxjava.api;

import info.ankurpandya.testrxjava.responses.CountryResponse;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * Create by Ankur @ Worktable.sg
 */
public interface Api {

    String BASE_URL = "https://api.printful.com/";

    @GET("countries")
    Observable<CountryResponse> getCountries();
}
