package com.example.lenovo.myapplication;

import android.app.Application;
/*
Helper class to create a Dagger2 Instace.
Basically done so that every time we need dagger 2 instance we don't write the below boiler plate code
again and again in every activity, rather now we can just call the getAppComponent() method of this class
and get a Dagger2 component.
See MainActivity.class for usage
 */
public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
