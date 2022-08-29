package com.example.task2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.task2.R;
import com.example.task2.databinding.FragmentImageDetailBinding;

public class ImageDetailFragment extends Fragment {

    public static final String IMAGE_URL_ARG = "image_url_arg";

    private FragmentImageDetailBinding binding;

    public ImageDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentImageDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getArguments() != null) {
            String imageUrl = getArguments().getString(IMAGE_URL_ARG);
            Glide
                .with(this)
                .load(imageUrl)
                .into(binding.imageDetailIv);
        }
    }
}