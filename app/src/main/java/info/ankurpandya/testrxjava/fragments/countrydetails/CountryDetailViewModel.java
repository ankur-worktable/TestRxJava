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

    BehaviorSubject<Boolean> loadingSubject = BehaviorSubject.create();
    BehaviorSubject<Country> countryDetails = BehaviorSubject.create();
    private Country country;

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

    public Observable<Country> loadCountryDetails() {
        if (loadingSubject != null && loadingSubject.getValue()) {
            return Observable.empty();
        }
        loadingSubject.onNext(true);
        countryDetails.onNext(country);
        return RetrofitClient.getInstance().getMyApi().getCountryDetail(country.getAlpha3Code())
                .subscribeOn(Schedulers.io())
                .doOnNext(country -> {
                    CountryDetailViewModel.this.country = country;
                    countryDetails.onNext(country);
                })
                .doOnTerminate(() -> loadingSubject.onNext(false));
    }
}
