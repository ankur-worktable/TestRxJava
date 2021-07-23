package info.ankurpandya.testrxjava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Create by Ankur @ Worktable.sg
 */
public abstract class BaseFragment extends Fragment {
    private final List<Disposable> disposableList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        bindViews(view);
        view.setClickable(true);
        return view;
    }

    public void register(Disposable disposable) {
        disposableList.add(disposable);
    }

    @Override
    public void onDestroy() {
        for (Disposable disposable : disposableList) {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        super.onDestroy();
    }

    protected abstract @LayoutRes
    int getLayout();

    protected abstract void bindViews(View rootView);
}
