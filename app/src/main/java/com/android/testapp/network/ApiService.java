package com.android.testapp.network;


import com.android.testapp.network.model.Organisation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hp pc on 13-11-2016.
 */

public interface ApiService {
    @GET("/organizations")
    public Call<List<Organisation>> getListOfOrganisation(@Query("since") int since);
}
