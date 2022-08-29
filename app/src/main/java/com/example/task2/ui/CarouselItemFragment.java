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
import com.example.task2.databinding.ItemCarouselBinding;

public class CarouselItemFragment extends Fragment {

    private String imageUrl;
    private ViewTypeImageClickListener onImageClickListener;
    private ItemCarouselBinding binding;

    public CarouselItemFragment() {
    }

    public CarouselItemFragment(String imageUrl, ViewTypeImageClickListener onImageClickListener) {
        this.imageUrl = imageUrl;
        this.onImageClickListener = onImageClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ItemCarouselBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView carouseImageIv = view.findViewById(R.id.img_banner_iv);
        carouseImageIv.setOnClickListener(v -> {
            onImageClickListener.onImageClicked(this.imageUrl);
        });
        Glide
            .with(binding.getRoot().getContext())
            .load(imageUrl)
            .into(binding.imgBannerIv);
    }
}
