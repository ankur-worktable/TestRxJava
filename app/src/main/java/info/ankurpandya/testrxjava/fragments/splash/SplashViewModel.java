package info.ankurpandya.testrxjava.fragments.splash;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/**
 * Create by Ankur @ Worktable.sg
 */
public class SplashViewModel {

    BehaviorSubject<Object> splashPresentation;

    public SplashViewModel() {
        splashPresentation = BehaviorSubject.create();
    }

    public Observable<Object> getPresentationObservable() {
        return splashPresentation.share();
    }

    public Observable<Object> presentSplash() {
        return Observable.fromCallable(() -> {
            Thread.sleep(2000);
            notifySplashDelay();
            return new Object();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single());
    }

    private void notifySplashDelay() {
        splashPresentation.onNext(new Object());
    }
}
