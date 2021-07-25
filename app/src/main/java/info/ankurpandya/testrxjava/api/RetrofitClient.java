package info.ankurpandya.testrxjava.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import info.ankurpandya.testrxjava.MyApplication;
import info.ankurpandya.testrxjava.utils.AppLogHelper;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by Ankur @ Worktable.sg
 */
public class RetrofitClient {

    private static final String TAG = "TESTRX";
    String BASE_URL = "https://restcountries.eu/rest/v2/";

    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_PRAGMA = "Pragma";
    private static final long CACHE_SIZE = 50 * 1024 * 1024;

    private static RetrofitClient instance = null;
    private Api myApi;

    private RetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(generateOkHttpClient())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    private static OkHttpClient generateOkHttpClient() {
        return new OkHttpClient
                .Builder()
                .cache(generateCacheConfig())
                .addInterceptor(generateLoggingInterceptor())
                .addNetworkInterceptor(generateNetworkInterceptor())
                .addInterceptor(generateOfflineInterceptor())
                .build();
    }

    private static Cache generateCacheConfig() {
        return new Cache(
                new File(MyApplication.getInstance().getCacheDir(), "networkcache"),
                CACHE_SIZE
        );
    }

    private static Interceptor generateLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String message) {
                AppLogHelper.Companion.log(TAG, message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private static Interceptor generateNetworkInterceptor() {
        return new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                AppLogHelper.Companion.log(TAG, "network interceptor: called.");
                Response response = chain.proceed(chain.request());
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(1, TimeUnit.DAYS)
                        .build();
                return response.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .addHeader(HEADER_CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    private static Interceptor generateOfflineInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                AppLogHelper.Companion.log(TAG, "offline interceptor: called.");
                Request request = chain.request();

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!MyApplication.hasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(30, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader(HEADER_PRAGMA)
                            .removeHeader(HEADER_CACHE_CONTROL)
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    public Api getMyApi() {
        return myApi;
    }
}
