package info.ankurpandya.testrxjava.api;

import info.ankurpandya.testrxjava.responses.CountryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Create by Ankur @ Worktable.sg
 */
public interface Api {

    String BASE_URL = "https://api.printful.com/";

    @GET("countries")
    Call<CountryResponse> getCountries();
}
