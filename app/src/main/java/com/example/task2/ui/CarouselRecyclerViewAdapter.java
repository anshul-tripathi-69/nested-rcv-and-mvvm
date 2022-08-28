package com.example.task2.ui;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2.R;
import com.example.task2.model.data.ViewItems;

import java.util.List;

public class CarouselRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CarouselRecyclerViewAdapter";

    private final List<ViewItems> viewItemsList;

    public CarouselRecyclerViewAdapter(List<ViewItems> viewItemsList) {
        Log.d(TAG, "CarouselRecyclerViewAdapter: " + viewItemsList.size());
        this.viewItemsList = viewItemsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carousel, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CarouselViewHolder) holder).bind(viewItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return viewItemsList.size();
    }

    private static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        View view;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            carouselImage = view.findViewById(R.id.img_banner_iv);
        }

        public void bind(ViewItems viewItems) {
            Log.d(TAG, "bind: " + viewItems.getImageUrl());
            Glide
                .with(carouselImage.getContext())
                .load(viewItems.getImageUrl())
                .into(carouselImage);
        }
    }
}
