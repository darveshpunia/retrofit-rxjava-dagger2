package com.example.lenovo.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AndroidException;
import android.util.Log;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

/*
        When you write @Inject, this (Retrofit in this case) will say that Dagger I need my required dependency,
        so what dagger will do is look for the dependency and inject it here.
        Now where will dagger look, dagger will look in the class which will have @Module on top of
        it and what should it inject now that is has found the class, it will inject the method
        which has @Provides written on it and is of the same type as the object
        written with @Inject (here object type is Retrofit).
     */

    @Inject
    Retrofit retrofit;
    /* During run time it would be like this:

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();


            How is it all connected? Here the workflow is:
            1. Look for @inject keyword
            2. Find the component interface class which connects this class to the Module class
            3. From the component class find the Module class(can be found from @component tag)
            4. Finally look for method which has @provides and same return type.

         */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* this here is a helper class that gives us the Dagger2 instance, by which we are able to connect
        to the component interface which further connects us with the Module class, which finally returns us
        the dependency. See App.class for more details
         */
        ((App) getApplication()).getAppComponent().inject(this);

        ApiManager apiManager = retrofit.create(ApiManager.class);

        Observable<User> call = apiManager.showInfo();

        //without lambda expression, for that need retrolambda
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d("qwerty", ""+user.getId());
                    }
                });

    }
}
