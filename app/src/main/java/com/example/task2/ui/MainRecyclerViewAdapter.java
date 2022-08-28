package com.example.task2.ui;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2.R;
import com.example.task2.model.data.ViewType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MainRecyclerViewAdapter";

    private final List<ViewType> viewTypeList;
    private final Map<String, Integer> viewTypes;

    public MainRecyclerViewAdapter(List<ViewType> viewTypeList) {
        assert viewTypeList != null;

        for (ViewType viewType : viewTypeList) {
            Log.d(TAG, "MainRecyclerViewAdapter: " + viewType.getViewType());
        }

        this.viewTypeList = viewTypeList;
        viewTypes = new HashMap<>();
        mapViewTypesToInts();
    }

    private void mapViewTypesToInts() {
        viewTypes.put("bannerImage", 0);
        viewTypes.put("carousel", 1);
        viewTypes.put("vertical_list", 2);
    }

    @Override
    public int getItemViewType(int position) {
        if (viewTypeList != null && viewTypeList.size() > 0) {
            Log.d(TAG, "getItemViewType: " + position);
            String viewTypeName = viewTypeList.get(position).getViewType();
            int viewType = viewTypes.getOrDefault(viewTypeList.get(position).getViewType(), -1);
            Log.d(TAG, "getItemViewType: " + viewTypeName + " " + viewType);
            return viewType;
        }

        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                Log.d(TAG, "onCreateViewHolder: banneritemview");
                View bannerItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_image, parent, false);
                return new BannerViewHolder(bannerItemView);

            case 1:
                Log.d(TAG, "onCreateViewHolder: carouseitemview");
                View carouselItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_view_type, parent, false);
                return new CarouselViewHolder(carouselItemView);

            case 2:
                View innerListItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_list, parent, false);
                return new InnerListViewTypeViewHolder(innerListItemView);

            // TODO(): figure out what to do with the default statement
            default:
                bannerItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_image, parent, false);
                return new BannerViewHolder(bannerItemView);
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

    private static class BannerViewHolder extends RecyclerView.ViewHolder {
        View view;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void bind(ViewType viewType) {
            Log.d(TAG, "bind --> BannerViewHolder");
            ImageView bannerImageView = view.findViewById(R.id.img_banner_image);

            Glide
                .with(view.getContext())
                .load(viewType.getImageUrl())
                .into(bannerImageView);
        }
    }

    private static class CarouselViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView carouselTitleTv;
        RecyclerView carouselItemRcv;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            carouselTitleTv = itemView.findViewById(R.id.carousel_title_tv);
            carouselItemRcv = itemView.findViewById(R.id.carousel_item_rcv);
        }

        public void bind(ViewType viewType) {
            Log.d(TAG, "bind --> CarouselViewHolder");
            carouselTitleTv.setText(viewType.getTitle());
            CarouselRecyclerViewAdapter carouselItemRcvAdapter = new CarouselRecyclerViewAdapter(viewType.getViewItems());
            carouselItemRcv.setAdapter(carouselItemRcvAdapter);
        }
    }

    private static class InnerListViewTypeViewHolder extends RecyclerView.ViewHolder {

        TextView innerListTitleTv;
        RecyclerView innerListRcv;

        public InnerListViewTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            innerListTitleTv = itemView.findViewById(R.id.inner_list_item_title_tv);
            innerListRcv = itemView.findViewById(R.id.inner_list_rcv);
        }

        public void bind(ViewType viewType) {
            innerListTitleTv.setText(viewType.getTitle());

            InnerListRecyclerViewAdapter innerListAdapter = new InnerListRecyclerViewAdapter(viewType.getViewItems());
            innerListRcv.setAdapter(innerListAdapter);
        }
    }
}
