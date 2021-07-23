package info.ankurpandya.testrxjava.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import info.ankurpandya.testrxjava.R;
import info.ankurpandya.testrxjava.adapter.CountryAdapter;
import info.ankurpandya.testrxjava.adapter.CountryHandler;
import info.ankurpandya.testrxjava.api.responses.Country;
import info.ankurpandya.testrxjava.viewmodel.CountryListViewModel;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Create by Ankur @ Worktable.sg
 */
public class FragmentCountryList extends BaseFragment implements CountryHandler {

    private CountryAdapter adapter;
    private CountryListViewModel viewModel;

    public static FragmentCountryList getInstance() {
        return new FragmentCountryList();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new CountryListViewModel();
        loadCountries();
    }

    @Override
    public void bindViews(View rootView) {
        RecyclerView list = rootView.findViewById(R.id.list);
        adapter = new CountryAdapter(new ArrayList<>(), this);
        list.setAdapter(adapter);
    }

    @Override
    public @LayoutRes
    int getLayout() {
        return R.layout.fragment_list_country;
    }

    @Override
    public void onCountrySelected(@NotNull Country country) {
        mainCallBack.onCountrySelected(country);
    }

    private void loadCountries() {
        register(
                viewModel.getCountries()
                        .observeOn(Schedulers.single())
                        .map(countries -> {
                            adapter.addItems(countries);
                            return true;
                        })
                        .doOnComplete(this::updateMessage)
                        .subscribe(
                                success -> {
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
}
