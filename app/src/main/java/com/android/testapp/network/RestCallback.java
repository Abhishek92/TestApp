package com.android.testapp.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp pc on 12-11-2016.
 */

public abstract class RestCallback<T> implements Callback<T> {

    abstract public void onSuccess(T t);

    abstract public void onFailure(RestError restError);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            RestError restError = new RestError();
            restError.setErrorMsg(response.message());
            restError.setUrl(call.request().url().toString());
            restError.setStatusCode(response.code());
            onFailure(restError);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        RestError restError = new RestError();
        restError.setErrorMsg(t.getMessage());
        restError.setUrl(call.request().url().toString());
        restError.setStatusCode(503);
        onFailure(restError);
    }
}
