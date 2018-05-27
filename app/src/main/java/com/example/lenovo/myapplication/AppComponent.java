package com.example.lenovo.myapplication;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class}) //class that provides injection
public interface AppComponent {
    //where the injection needs to happen
    void inject(MainActivity mainActivity);
}
//clearly we can see how this component connects the class which gives dependency and the one which
//needs dependency.