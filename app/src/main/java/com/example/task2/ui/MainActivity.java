package com.example.task2.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2.R;
import com.example.task2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ViewTypeImageClickListener {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private MainRecyclerViewAdapter mainRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Log.d(TAG, "onCreate: back stack items -> " + getSupportFragmentManager().getBackStackEntryCount());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        subscribeToViewModelState();
    }

    @Override
    protected void onStart() {
        super.onStart();

        stopImageDetailFragment();
    }

    @Override
    public void onImageClicked(String imageUrl) {
        viewModel.setDetailImageUrl(imageUrl);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        viewModel.setDetailImageUrl(null);
        stopImageDetailFragment();
    }

    private void subscribeToViewModelState() {
        viewModel.getViewTypeList().observe(this, viewTypesList -> {
            if (viewTypesList != null && viewTypesList.size() != 0) {
                Log.d(TAG, "onCreate: " + viewTypesList.size());
                mainRcvAdapter =
                    new MainRecyclerViewAdapter(viewTypesList, this, this);
                binding.mainRcv.setAdapter(mainRcvAdapter);
            }
        });

        viewModel.getDetailImageUrl().observe(this, detailImageUrl -> {
            if (detailImageUrl != null) {
                startImageDetailFragment(detailImageUrl);
            } else {
                stopImageDetailFragment();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void startImageDetailFragment(String imageUrl) {
        binding.detailedImageFragmentContainer.setVisibility(View.VISIBLE);
        binding.mainRcv.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        bundle.putString(ImageDetailFragment.IMAGE_URL_ARG, imageUrl);
        getSupportFragmentManager()
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.detailed_image_fragment_container, ImageDetailFragment.class, bundle)
            .addToBackStack("detail_image")
            .commit();
    }

    private void stopImageDetailFragment() {
        binding.detailedImageFragmentContainer.setVisibility(View.GONE);
        binding.mainRcv.setVisibility(View.VISIBLE);

        Log.d(TAG, "stopImageDetailFragment: popping back stack, number of items -> " + getSupportFragmentManager().getBackStackEntryCount());

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }
}