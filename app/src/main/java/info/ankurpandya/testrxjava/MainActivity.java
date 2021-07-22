package info.ankurpandya.testrxjava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import info.ankurpandya.testrxjava.api.RetrofitClient;
import info.ankurpandya.testrxjava.responses.CountryResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private CountryAdapter adapter;
    private final List<Disposable> disposableList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.list);
        adapter = new CountryAdapter(new ArrayList<>());
        list.setAdapter(adapter);
        loadCountries();
    }

    private void loadCountries() {
        Observable<CountryResponse> observable = RetrofitClient.getInstance().getMyApi().getCountries();
        disposableList.add(
                observable
                        .subscribeOn(Schedulers.io())
//                        .map(response -> {
//                            //adapter.addItems(response.getCountries());
//                            updateMessage();
//                            return true;
//                        })
                        //.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.addItems(response.getCountries());
                                        }
                                    });
                                },
                                this::handleAPIFail
                        )
        );
    }

    @Override
    protected void onDestroy() {
        for (Disposable disposable : disposableList) {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        super.onDestroy();
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