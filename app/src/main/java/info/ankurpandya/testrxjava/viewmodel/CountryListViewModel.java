package info.ankurpandya.testrxjava.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import info.ankurpandya.testrxjava.api.RetrofitClient;
import info.ankurpandya.testrxjava.api.responses.Country;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/**
 * Create by Ankur @ Worktable.sg
 */
public class CountryListViewModel extends ViewModel {

    private BehaviorSubject<List<Country>> countrySubject = BehaviorSubject.create();
    private BehaviorSubject<Boolean> loadingSubject = BehaviorSubject.create();

    private Observable<List<Country>> observable;

    public CountryListViewModel() {
        loadingSubject.onNext(false);
        countrySubject.onNext(new ArrayList<>());
    }

    public Observable<List<Country>> getCountries() {
        if (loadingSubject != null && loadingSubject.getValue()) {
            return Observable.empty();
        }
        countrySubject.onNext(new ArrayList<>());
        loadingSubject.onNext(true);
        if (observable == null) {
            observable = RetrofitClient.getInstance().getMyApi().getCountries()
                    .subscribeOn(Schedulers.io())
                    .doOnNext(list -> {
                        List<Country> fullList = new ArrayList<>(countrySubject.getValue());
                        fullList.addAll(list);
                        countrySubject.onNext(fullList);
                    })
                    .doOnTerminate(() -> loadingSubject.onNext(false));
        }
        return observable;
    }

    public Observable<List<Country>> getCountryObserver() {
        return countrySubject.share();
    }

    public Observable<Boolean> getLoadingObserver() {
        return loadingSubject.share();
    }

}
