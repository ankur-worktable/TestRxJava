package info.ankurpandya.testrxjava.fragments.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;

import info.ankurpandya.testrxjava.R;
import info.ankurpandya.testrxjava.fragments.BaseFragment;

/**
 * Create by Ankur @ Worktable.sg
 */
public class FragmentSplash extends BaseFragment {

    SplashViewModel viewModel;
    LottieAnimationView animationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new SplashViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        register(
//                viewModel.getPresentationObservable()
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(obj -> onPresentationCompleted())
//        );
//        register(
//                viewModel.presentSplash()
//                        .subscribe()
//        );

        animationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                onPresentationCompleted();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void onPresentationCompleted() {
        mainCallBack.onPresentationCompleted();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void bindViews(View rootView) {
        animationView = rootView.findViewById(R.id.animationView);
    }
}
