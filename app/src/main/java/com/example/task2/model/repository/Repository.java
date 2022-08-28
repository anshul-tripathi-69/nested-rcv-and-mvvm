package com.example.task2.model.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.task2.model.data.ApiResponse;
import com.example.task2.model.data.ViewItems;
import com.example.task2.model.data.ViewType;
import com.example.task2.model.remote.Api;
import com.example.task2.ui.MainViewModelCallbacks;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static final String TAG = "Repository";

    private static Repository instance = null;
    private final Api myApi;

    private Repository() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }

        return instance;
    }

    public void getApiResponse(MainViewModelCallbacks onResponse) {
        Call<ApiResponse> apiResponse = myApi.getApiResponse();
        apiResponse.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                assert response.body() != null;
                List<ViewType> viewTypeList = response.body().getResults();
                Log.d(TAG, "onResponse: success! size - " + viewTypeList.toString());
                onResponse.onApiResponse(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: there was some failure");
                onResponse.onApiResponse(Collections.emptyList());
            }
        });
    }
}
