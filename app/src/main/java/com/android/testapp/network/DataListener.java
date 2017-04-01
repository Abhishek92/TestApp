package com.android.testapp.network;

/**
 * Created by hp pc on 03-12-2016.
 */
public interface DataListener<T> {

    public void onDataLoaded(T t);
    public void onFailed(RestError error);
}
