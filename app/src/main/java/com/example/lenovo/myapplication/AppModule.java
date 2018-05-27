package com.example.lenovo.myapplication;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    /* this is the dependency that is being provide to all the objects of retrofit which will has @inject like:
    @Inject Retrofit retrofit; all such declarations during the run time will be like:
    Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
     if we do this without dagger (i.e. without @inject) then everytime we need retrofit we would require to
     write new Retofit.Builder()... in all activities which is nothing but unnecessary biolerplate code.
     */

    /* an interesting thing to note here is that this method itself depends on Okhttpclient (see argument of func)
    so this also requires a dependency injection, now if we remove the below method provideokHttpClient()
    the code wouldn't run since it requires a new instance of okhttpclient, so during run time it would be:
    Retrofit provideRetrofit(OkHttpClient okHttpClient = new OkHttpClient())
    Interesting thing here is, normally for dependency inject we write @Inject, but here even when we skip writing
    @Inject the DI happens automatically, so one can write:

    @Singleton
    @Provides
    @Inject
    Retrofit provideRetrofit(OkHttpClient okHttpClient){

    or like below (without @Inject) the code works fine.

     */
    //singleton used so that in various activities we might require retrofit object, so Dagger will just give
    //the same object that it has created for any activity for the first time.
    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
    }
    /*
      required by the above Retrofit function, without this the app fails to compile
   */
    @Provides
    @Singleton
    OkHttpClient provideokHttpClient(){
        return new OkHttpClient();
    }

}
