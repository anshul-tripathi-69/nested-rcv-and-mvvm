package com.example.task2.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiResponse {
    @SerializedName("results")
    @Expose
    private ArrayList<ViewType> results;

    public ArrayList<ViewType> getResults() {
        return results;
    }
}
