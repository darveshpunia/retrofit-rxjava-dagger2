package com.example.lenovo.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiManager {

    @GET("users/fs-opensource")
    Observable<User> showInfo(); //wrapping in observable since we are using RxJava
}
