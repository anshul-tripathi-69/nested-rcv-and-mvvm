package com.example.task2.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewItems {
    @SerializedName("img")
    @Expose
    private String imageUrl;

    @SerializedName("title")
    @Expose
    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
