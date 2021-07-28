package info.ankurpandya.testrxjava.fragments.countrydetails;

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
import info.ankurpandya.testrxjava.api.responses.Country;
import info.ankurpandya.testrxjava.customviews.KeyValuePairView;
import info.ankurpandya.testrxjava.fragments.BaseFragment;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

/**
 * Create by Ankur @ Worktable.sg
 */
public class FragmentCountryDetail extends BaseFragment {

    private static final String ARG_COUNTRY = "country";

    private ImageView img_country;
    private TextView txt_country_name;
    private KeyValuePairView txt_cioc;
    private KeyValuePairView txt_ios2;
    private KeyValuePairView txt_ios3;
    private KeyValuePairView txt_population;
    private KeyValuePairView txt_capital;
    private KeyValuePairView txt_region;
    private KeyValuePairView txt_subregion;
    private KeyValuePairView txt_demonym;
    private KeyValuePairView txt_area;
    private KeyValuePairView txt_gini;

    private Country country;

    private CountryDetailViewModel viewModel;

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
        viewModel = new CountryDetailViewModel(country);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        register(
                viewModel.getCountryObserver()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(theCountry -> renderCountry(theCountry))
        );
        register(
                viewModel.getErrorObserver()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(exception -> showMessage(exception.getMessage()))
        );
        view.setTransitionName(country.getAlpha3Code());
        loadCountryDetails(country);
    }

    @Override
    public void bindViews(View rootView) {
        img_country = rootView.findViewById(R.id.img_country);
        txt_country_name = rootView.findViewById(R.id.txt_country_name);
        txt_cioc = rootView.findViewById(R.id.txt_cioc);
        txt_ios2 = rootView.findViewById(R.id.txt_ios2);
        txt_ios3 = rootView.findViewById(R.id.txt_ios3);
        txt_population = rootView.findViewById(R.id.txt_population);
        txt_capital = rootView.findViewById(R.id.txt_capital);
        txt_region = rootView.findViewById(R.id.txt_region);
        txt_subregion = rootView.findViewById(R.id.txt_subregion);
        txt_demonym = rootView.findViewById(R.id.txt_demonym);
        txt_area = rootView.findViewById(R.id.txt_area);
        txt_gini = rootView.findViewById(R.id.txt_gini);
    }

    @Override
    public @LayoutRes
    int getLayout() {
        return R.layout.fragment_list_detail;
    }

    private void loadCountryDetails(Country country) {
        register(
                viewModel.loadCountryDetails()
                        .subscribe(
                                data -> {
                                },
                                error -> {
                                }
                        )
        );
    }

    private void renderCountry(Country country) {
        //img_banner;
        txt_country_name.setText(country.getName());
        txt_cioc.setText(country.getCioc());
        txt_ios2.setText(country.getAlpha2Code());
        txt_ios3.setText(country.getAlpha3Code());
        txt_population.setText(String.valueOf(country.getPopulation()));
        txt_capital.setText(country.getCapital());
        txt_region.setText(country.getRegion());
        txt_subregion.setText(country.getSubregion());
        txt_demonym.setText(country.getDemonym());
        txt_area.setText(country.getArea() + " Sq Feet");
        txt_gini.setText(String.valueOf(country.getGini()));

        Glide.with(getContext())
                .load(country.getFlag())
                .placeholder(R.drawable.img_default)
                .error(R.drawable.img_error)
                .into(img_country);
    }
}
