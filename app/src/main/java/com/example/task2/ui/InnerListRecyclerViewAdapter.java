package com.example.task2.ui;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2.databinding.ItemInnerListBinding;
import com.example.task2.model.data.ViewItems;

import java.util.List;

public class InnerListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ViewItems> viewItemsList;
    private final ViewTypeImageClickListener onImageClickListener;

    public InnerListRecyclerViewAdapter(List<ViewItems> viewItemsList, ViewTypeImageClickListener onImageClickListener) {
        this.viewItemsList = viewItemsList;
        this.onImageClickListener = onImageClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInnerListBinding itemInnerListBinding = ItemInnerListBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent,
            false
        );
        return new InnerListViewHolder(itemInnerListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((InnerListViewHolder) holder).bind(viewItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return viewItemsList.size();
    }

    private final class InnerListViewHolder extends RecyclerView.ViewHolder {
        private final ItemInnerListBinding binding;

        public InnerListViewHolder(ItemInnerListBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(ViewItems viewItems) {
            binding.innerListItemTitleTv.setText(viewItems.getTitle());

            binding.innerListItemIv.setOnClickListener(v -> {
                onImageClickListener.onImageClicked(viewItems.getImageUrl());
            });

            Glide
                .with(binding.getRoot().getContext())
                .load(viewItems.getImageUrl())
                .into(binding.innerListItemIv);
        }
    }
}
