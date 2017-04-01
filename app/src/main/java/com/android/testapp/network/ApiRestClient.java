package com.android.testapp.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhishek on 10/11/16.
 */

public enum ApiRestClient
{
    API_INSTANCE;

    private final long CACHE_SIZE = 2 * 1024 * 1024; //2MB
    private String BASE_URL = "https://api.github.com";
    private Retrofit restClient;
    private ApiService apiService;

    public void initRestClient(Context context, boolean debug)
    {
        Cache cache = new Cache(new File(context.getCacheDir(), "http"), CACHE_SIZE);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.retryOnConnectionFailure(true);
        httpClient.readTimeout(90, TimeUnit.SECONDS);
        httpClient.connectTimeout(90, TimeUnit.SECONDS);
        httpClient.cache(cache);
        if(debug)
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        OkHttpClient client = httpClient.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(client);
        Gson gson = new GsonBuilder().setLenient().create();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        restClient = builder.build();
        apiService = restClient.create(ApiService.class);
    }



    public ApiService getApiService()
    {
        return apiService;
    }
}
