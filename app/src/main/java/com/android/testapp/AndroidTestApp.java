package com.android.testapp;

import android.app.Application;

import com.android.testapp.network.ApiRestClient;


/**
 * Created by hp pc on 24-03-2017.
 */

public class AndroidTestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiRestClient.API_INSTANCE.initRestClient(this, BuildConfig.DEBUG);
    }
}
