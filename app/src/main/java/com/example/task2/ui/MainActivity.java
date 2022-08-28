package com.example.task2.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2.R;

public class MainActivity extends AppCompatActivity implements ViewTypeImageClickListener {

    private static final String TAG = "MainActivity";
    MainViewModel viewModel;
    MainRecyclerViewAdapter mainRcvAdapter;
    RecyclerView mainRcv;
    FrameLayout imageDetailFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainRcv = findViewById(R.id.main_rcv);
        imageDetailFragmentContainer = findViewById(R.id.detailed_image_fragment_container);
        viewModel.getViewTypeList().observe(this, viewTypesList -> {
            if (viewTypesList.size() != 0) {
                Log.d(TAG, "onCreate: " + viewTypesList.size());
                mainRcvAdapter = new MainRecyclerViewAdapter(viewTypesList, this);
                mainRcv.setAdapter(mainRcvAdapter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onImageClicked(String imageUrl) {
        imageDetailFragmentContainer.setVisibility(View.VISIBLE);
        mainRcv.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        bundle.putString(ImageDetailFragment.IMAGE_URL_ARG, imageUrl);
        getSupportFragmentManager()
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.detailed_image_fragment_container, ImageDetailFragment.class, bundle)
            .addToBackStack("detail_image")
            .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        imageDetailFragmentContainer.setVisibility(View.GONE);
        mainRcv.setVisibility(View.VISIBLE);
        getSupportFragmentManager().popBackStack();
    }
}