package info.ankurpandya.testrxjava.fragments.countrydetails;

import info.ankurpandya.testrxjava.api.RetrofitClient;
import info.ankurpandya.testrxjava.api.responses.Country;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/**
 * Create by Ankur @ Worktable.sg
 */
public class CountryDetailViewModel {

    private BehaviorSubject<Boolean> loadingSubject = BehaviorSubject.create();
    private BehaviorSubject<Country> countryDetails = BehaviorSubject.create();
    private BehaviorSubject<Throwable> errorDetails = BehaviorSubject.create();
    private Country country;

    private Observable<Country> observable;

    public CountryDetailViewModel(Country country) {
        this.country = country;
        loadingSubject.onNext(false);
        countryDetails.onNext(country);
    }

    public Observable<Country> getCountryObserver() {
        return countryDetails.share();
    }

    public Observable<Boolean> getLoadingObserver() {
        return loadingSubject.share();
    }

    public Observable<Throwable> getErrorObserver() {
        return errorDetails.share();
    }

    public Observable<Country> loadCountryDetails() {
        if (loadingSubject != null && loadingSubject.getValue()) {
            return Observable.empty();
        }
        loadingSubject.onNext(true);
        countryDetails.onNext(country);
        if (observable == null) {
            observable = RetrofitClient.getInstance().getMyApi().getCountryDetail(country.getAlpha3Code())
                    .subscribeOn(Schedulers.io())
                    .doOnNext(country -> {
                        CountryDetailViewModel.this.country = country;
                        countryDetails.onNext(country);
                    })
                    .doOnError(exception -> errorDetails.onNext(exception))
                    .doOnTerminate(() -> loadingSubject.onNext(false));
        }
        return observable;
    }
}
