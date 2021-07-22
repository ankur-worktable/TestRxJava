package info.ankurpandya.testrxjava;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import info.ankurpandya.testrxjava.api.RetrofitClient;
import info.ankurpandya.testrxjava.responses.CountryResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.list);
        adapter = new CountryAdapter(new ArrayList<>());
        list.setAdapter(adapter);
        //adapter.addItems(createCountries());
        loadCountries();
    }

    private void loadCountries() {
        Call<CountryResponse> call = RetrofitClient.getInstance().getMyApi().getCountries();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                CountryResponse countryResponse = response.body();
                adapter.addItems(countryResponse.getCountries());
                updateMessage();
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                updateMessage();
            }
        });
    }

    private void updateMessage() {

    }

//        List<People> myPeople = new ArrayList<>();

//        Flowable.<People>create(emitter -> emitPeople(emitter), BackpressureStrategy.BUFFER)
//                .filter(people -> people.getAge() < 30)
//                .map(people -> {
//                    //myPeople.add(people);
//                    printPeople(people);
//                    return true;
//                })
//                //.first(people -> printPeople(people))
//                .subscribe(
//                        success -> System.out.println("Operation success :" + success),
//                        error -> System.out.println("Error :" + error),
//                        () -> System.out.println("Operation Completed")
//                );


//        Disposable subscribe = Observable.<People>create(this::emitPeople)
//                .filter(people -> people.getAge() > 70)
//                .map(this::printPeople)
//                .subscribe(
//                        success -> System.out.println("Operation succeed? :" + success)
//                );

//        Single.<People>create(emitter -> emitPeople(emitter))
//                .filter(people -> people.getAge() < 30)
//                //.first(people -> printPeople(people))
//                .subscribe(
//                        people -> printPeople(people),
//                        error -> System.out.println("Error :" + error),
//                        () -> System.out.println("Operation Completed")
//                );

//        //Flowable.just()
//        System.out.println(
//                createPeople().stream()
//                        .filter(people -> people.getAge() < 20)
//                        .map(people -> people.toString())
//                        .findFirst()
//                        .orElse("No people found")
//        );
//    }

//    private void emitPeople(FlowableEmitter<People> peopleFlowableEmitter) {
//        for (People people : createPeople()) {
//            peopleFlowableEmitter.onNext(people);
//        }
//    }

//    private void emitPeople(ObservableEmitter<People> peopleFlowableEmitter) {
//        for (People people : createPeople()) {
//            peopleFlowableEmitter.onNext(people);
//        }
//    }
//
//    private void emitPeople(SingleEmitter<People> peopleFlowableEmitter) {
//        for (People people : createPeople()) {
//            peopleFlowableEmitter.onSuccess(people);
//        }
//    }
//
//    public boolean printPeople(People people) {
//        Disposable hello_world = Flowable.just(people.toString()).subscribe(System.out::println);
//        return true;
//    }
//
//    public static class People {
//        String name;
//        int age;
//
//        public People(String name, int age) {
//            this.name = name;
//            this.age = age;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        @Override
//        public String toString() {
//            return "Person [ " + name + " ] age [ " + age + " ]";
//        }
//    }
}