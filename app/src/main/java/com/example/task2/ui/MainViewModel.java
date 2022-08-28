package com.example.task2.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task2.model.data.ViewType;
import com.example.task2.model.repository.Repository;

import java.util.Collections;
import java.util.List;

public class MainViewModel extends ViewModel implements MainViewModelCallbacks {

    private final Repository repository;
    private final MutableLiveData<List<ViewType>> viewTypeList;

    public MainViewModel() {
        repository = Repository.getInstance();
        viewTypeList = new MutableLiveData<>(Collections.emptyList());
        getApiResponse();
    }

    public LiveData<List<ViewType>> getViewTypeList() {
        return viewTypeList;
    }

    @Override
    public void onApiResponse(List<ViewType> response) {
        viewTypeList.postValue(response);
    }

    private void getApiResponse() {
        repository.getApiResponse(this);
    }
}
