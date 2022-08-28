package com.example.task2.model.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewType {
    @SerializedName("viewType")
    @Expose
    private String viewType;

    @SerializedName("img")
    @Expose
    private String imageUrl;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("items")
    @Expose
    private ArrayList<ViewItems> viewItems;

    public String getViewType() {
        return viewType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<ViewItems> getViewItems() {
        return viewItems;
    }

    @NonNull
    @Override
    public String toString() {
        String temp = this.viewType + " && " + this.imageUrl + " && " + this.title + " && " + this.viewType;
        String toString;
        if (viewItems != null) {
            toString = temp + this.viewItems.get(0).getTitle();
        } else {
            toString = temp;
        }
        return toString;
    }
}
