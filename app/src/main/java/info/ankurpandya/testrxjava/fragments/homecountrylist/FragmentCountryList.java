package info.ankurpandya.testrxjava.fragments.homecountrylist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
import info.ankurpandya.testrxjava.fragments.BaseFragment;
import info.ankurpandya.testrxjava.utils.StringUtils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

/**
 * Create by Ankur @ Worktable.sg
 */
public class FragmentCountryList extends BaseFragment implements CountryHandler {

    private CountryAdapter adapter;
    private CountryListViewModel viewModel;
    private View view_progress;
    private TextView txt_progress;
    private View action_button;

    public static FragmentCountryList getInstance() {
        return new FragmentCountryList();
    }

    @Override
    public void bindViews(View rootView) {
        RecyclerView list = rootView.findViewById(R.id.list);
        view_progress = rootView.findViewById(R.id.view_progress);
        txt_progress = rootView.findViewById(R.id.txt_progress);
        action_button = rootView.findViewById(R.id.action_button);
        adapter = new CountryAdapter(new ArrayList<>(), this);
        list.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new CountryListViewModel();
        action_button.setOnClickListener(v -> loadCountries());

        register(
                viewModel.getCountryObserver()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(list -> adapter.replaceItems(list))
        );
        register(
                viewModel.getLoadingObserver()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(isLoading -> {
                            if (isLoading) {
                                showProgress("Loading countries..");
                            } else {
                                hideProgess();
                            }
                        })
        );
        register(
                viewModel.getErrorObserver()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(exception -> showMessage(exception.getMessage()))
        );

        hideProgess();
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
                        .subscribe(
                                data -> {
                                },
                                error -> {
                                }
                        )
        );
    }

    private void showProgress(String text) {
        view_progress.setVisibility(View.VISIBLE);
        if (StringUtils.isValidText(text)) {
            txt_progress.setVisibility(View.VISIBLE);
            txt_progress.setText(text);
        } else {
            txt_progress.setVisibility(View.GONE);
        }
    }

    private void hideProgess() {
        view_progress.setVisibility(View.GONE);
    }

    private void handleAPIFail(Throwable error) {
        System.out.println("API call failed");
        error.printStackTrace();
        //Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
        updateMessage();
        hideProgess();
    }

    private void updateMessage() {

    }
}