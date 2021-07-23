package info.ankurpandya.testrxjava.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.List;

import info.ankurpandya.testrxjava.api.RetrofitClient;
import info.ankurpandya.testrxjava.api.responses.Country;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Create by Ankur @ Worktable.sg
 */
public class CountryListViewModel extends ViewModel {

    private Observable<List<Country>> observable;

    public Observable<List<Country>> getCountries() {
        if (observable == null) {
            observable = RetrofitClient.getInstance().getMyApi().getCountries()
                    .subscribeOn(Schedulers.io());
        }
        return observable;
    }

}
