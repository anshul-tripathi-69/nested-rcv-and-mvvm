package com.example.task2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.task2.R;
import com.example.task2.model.repository.Repository;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    MainViewModel viewModel;
    MainRecyclerViewAdapter mainRcvAdapter;
    RecyclerView mainRcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainRcv = findViewById(R.id.main_rcv);
        viewModel.getViewTypeList().observe(this, viewTypesList -> {
            if (viewTypesList.size() != 0) {
                Log.d(TAG, "onCreate: " + viewTypesList.size());
                mainRcvAdapter = new MainRecyclerViewAdapter(viewTypesList);
                mainRcv.setAdapter(mainRcvAdapter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}