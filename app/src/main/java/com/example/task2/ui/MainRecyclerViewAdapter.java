package com.example.task2.ui;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.task2.R;
import com.example.task2.databinding.CarouselViewTypeBinding;
import com.example.task2.databinding.InnerListBinding;
import com.example.task2.databinding.ItemBannerImageBinding;
import com.example.task2.model.data.ViewType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MainRecyclerViewAdapter";

    private final List<ViewType> viewTypeList;
    private final ViewTypeImageClickListener onImageClickedListener;
    private final FragmentActivity fragmentActivity;

    public MainRecyclerViewAdapter(
        List<ViewType> viewTypeList,
        @NonNull FragmentActivity fragmentActivity,
        ViewTypeImageClickListener onImageClickListener
    ) {
        assert viewTypeList != null;
        assert onImageClickListener != null;

        this.viewTypeList = viewTypeList;
        this.onImageClickedListener = onImageClickListener;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public int getItemViewType(int position) {

        switch (viewTypeList.get(position).getViewType()) {
            case "bannerImage": return 0;
            case "carousel": return 1;
            case "vertical_list": return 2;
            default: return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                ItemBannerImageBinding itemBannerImageBinding = ItemBannerImageBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
                );
                return new BannerViewHolder(itemBannerImageBinding);

            case 1:
                CarouselViewTypeBinding carouselViewTypeBinding = CarouselViewTypeBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
                );
                return new CarouselViewHolder(carouselViewTypeBinding);

            case 2:
                InnerListBinding innerListBinding = InnerListBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
                );
                return new InnerListViewTypeViewHolder(innerListBinding);

            // TODO(): figure out what to do with the default statement
            default:
                itemBannerImageBinding = ItemBannerImageBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
                );
                return new BannerViewHolder(itemBannerImageBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                ((BannerViewHolder) holder).bind(viewTypeList.get(position));
                break;

            case 1:
                ((CarouselViewHolder) holder).bind(viewTypeList.get(position));
                break;

            case 2:
                ((InnerListViewTypeViewHolder) holder).bind(viewTypeList.get(position));
                break;

            default:
                Log.d(TAG, "onBindViewHolder: hehe");
        }
    }

    @Override
    public int getItemCount() {
        return viewTypeList.size();
    }

    private class InnerListViewTypeViewHolder extends RecyclerView.ViewHolder {

        private final InnerListBinding binding;

        public InnerListViewTypeViewHolder(InnerListBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(ViewType viewType) {
            binding.innerListItemTitleTv.setText(viewType.getTitle());

            InnerListRecyclerViewAdapter innerListAdapter =
                new InnerListRecyclerViewAdapter(viewType.getViewItems(), onImageClickedListener);
            binding.innerListRcv.setAdapter(innerListAdapter);
        }
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final ItemBannerImageBinding binding;

        public BannerViewHolder(ItemBannerImageBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(ViewType viewType) {
            Log.d(TAG, "bind --> BannerViewHolder");
            binding.imgBannerImage.setOnClickListener(v -> {
                onImageClickedListener.onImageClicked(viewType.getImageUrl());
            });

            Glide
                .with(binding.getRoot().getContext())
                .load(viewType.getImageUrl())
                .into(binding.imgBannerImage);
        }
    }

    private class CarouselViewHolder extends RecyclerView.ViewHolder {
        CarouselViewTypeBinding binding;

        public CarouselViewHolder(CarouselViewTypeBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(ViewType viewType) {
            Log.d(TAG, "bind --> CarouselViewHolder");
            binding.carouselTitleTv.setText(viewType.getTitle());
            CarouselViewPagerAdapter carouselViewPagerAdapter = new CarouselViewPagerAdapter(
                fragmentActivity, viewType.getViewItems(),
                onImageClickedListener
            );
            binding.carouselItemViewPager.setAdapter(carouselViewPagerAdapter);
        }
    }
}
