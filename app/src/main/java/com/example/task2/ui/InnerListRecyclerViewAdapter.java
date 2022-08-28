package com.example.task2.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2.R;
import com.example.task2.model.data.ViewItems;

import java.util.List;

public class InnerListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ViewItems> viewItemsList;

    public InnerListRecyclerViewAdapter(List<ViewItems> viewItemsList) {
        this.viewItemsList = viewItemsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inner_list, parent, false);
        return new InnerListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((InnerListViewHolder) holder).bind(viewItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return viewItemsList.size();
    }

    private static final class InnerListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        ImageView imageView;

        public InnerListViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.inner_list_item_title_tv);
            imageView = itemView.findViewById(R.id.inner_list_item_iv);
        }

        public void bind(ViewItems viewItems) {
            titleTv.setText(viewItems.getTitle());
            Glide
                .with(imageView.getContext())
                .load(viewItems.getImageUrl())
                .into(imageView);
        }
    }
}
