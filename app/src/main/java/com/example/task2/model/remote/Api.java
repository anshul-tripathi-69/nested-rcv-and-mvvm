package com.example.task2.model.remote;

import com.example.task2.model.data.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://jsonkeeper.com/b/";

    @GET("GXOC")
    Call<ApiResponse> getApiResponse();
}
