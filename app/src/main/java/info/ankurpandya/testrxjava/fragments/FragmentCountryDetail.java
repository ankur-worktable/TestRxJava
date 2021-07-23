package info.ankurpandya.testrxjava.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import info.ankurpandya.testrxjava.R;
import info.ankurpandya.testrxjava.adapter.CountryHandler;
import info.ankurpandya.testrxjava.api.RetrofitClient;
import info.ankurpandya.testrxjava.api.responses.Country;
import info.ankurpandya.testrxjava.customviews.KeyValuePairView;
import info.ankurpandya.testrxjava.utils.StringUtils;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Create by Ankur @ Worktable.sg
 */
public class FragmentCountryDetail extends BaseFragment implements CountryHandler {

    private static final String ARG_COUNTRY = "arg_country";

    private ImageView img_banner;
    private ImageView img_country;
    private TextView txt_country_name;
    private KeyValuePairView txt_cioc;
    private KeyValuePairView txt_ios2;
    private KeyValuePairView txt_ios3;

    private Country country;

    public static FragmentCountryDetail getInstance(Country country) {
        FragmentCountryDetail fragment = new FragmentCountryDetail();
        Bundle args = new Bundle();
        args.putSerializable(ARG_COUNTRY, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            country = (Country) getArguments().getSerializable(ARG_COUNTRY);
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderCountry(country);
        if (!StringUtils.isValidText(country.getAlpha2Code())) {
            loadCountryDetails(country);
        }
    }

    @Override
    public void bindViews(View rootView) {
        img_banner = rootView.findViewById(R.id.img_banner);
        img_country = rootView.findViewById(R.id.img_country);
        txt_country_name = rootView.findViewById(R.id.txt_country_name);
        txt_cioc = rootView.findViewById(R.id.txt_cioc);
        txt_ios2 = rootView.findViewById(R.id.txt_ios2);
        txt_ios3 = rootView.findViewById(R.id.txt_ios3);
    }

    @Override
    public @LayoutRes
    int getLayout() {
        return R.layout.fragment_list_detail;
    }

    @Override
    public void onCountrySelected(@NotNull Country country) {

    }

    private void loadCountryDetails(Country country) {
        register(
                RetrofitClient.getInstance().getMyApi().getCountryDetail(country.getAlpha3Code())
                        .subscribeOn(Schedulers.io())
//                        .map(response -> {
//                            //adapter.addItems(response.getCountries());
//                            updateMessage();
//                            return true;
//                        })
                        //.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //country.copy(response);
                                            renderCountry(response);
                                        }
                                    });
                                },
                                this::handleAPIFail
                        )
        );
    }

    private void handleAPIFail(Throwable error) {
        System.out.println("API call failed");
        error.printStackTrace();
        //Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
        updateMessage();
    }

    private void updateMessage() {

    }

    private void renderCountry(Country country) {
        //img_banner;
        txt_country_name.setText(country.getName());
        txt_cioc.setText(country.getCioc());
        txt_ios2.setText(country.getAlpha2Code());
        txt_ios3.setText(country.getAlpha3Code());
        Glide.with(getContext())
                .load(country.getFlag())
                .placeholder(R.drawable.img_default)
                .error(R.drawable.img_error)
                .into(img_country);
    }
}
