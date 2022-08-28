package com.example.task2.ui;

import com.example.task2.model.data.ViewType;

import java.util.List;

public interface MainViewModelCallbacks {
    public void onApiResponse(List<ViewType> response);
}
