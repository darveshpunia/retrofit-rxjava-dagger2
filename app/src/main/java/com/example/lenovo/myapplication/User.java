package com.example.lenovo.myapplication;

import com.google.gson.annotations.SerializedName;

public class User {
    //mapping JSON to Java, taking only one field from the JSON returned, you can add more.
    private String login;

    public String getId() {
        return login;
    }
}
