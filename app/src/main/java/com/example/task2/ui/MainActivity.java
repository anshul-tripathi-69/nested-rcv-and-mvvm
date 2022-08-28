package com.example.task2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.task2.R;
import com.example.task2.model.repository.Repository;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getViewTypeList().observe(this, viewTypesList -> {
            Log.d(TAG, "onCreated: " + viewTypesList.size() + " " + viewTypesList);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}