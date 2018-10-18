package com.example.android.firebasetesting;

import android.app.Application;

import com.firebase.client.Firebase;

public class FireBaseTesting extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
