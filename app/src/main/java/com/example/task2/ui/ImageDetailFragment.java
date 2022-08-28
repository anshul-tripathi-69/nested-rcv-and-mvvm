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

public class ImageDetailFragment extends Fragment {

    public static final String IMAGE_URL_ARG = "image_url_arg";

    ImageView mDetailImageView;

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
        return inflater.inflate(R.layout.fragment_image_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDetailImageView = view.findViewById(R.id.image_detail_iv);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getArguments() != null) {
            String imageUrl = getArguments().getString(IMAGE_URL_ARG);
            Glide
                .with(this)
                .load(imageUrl)
                .into(mDetailImageView);
        }
    }
}