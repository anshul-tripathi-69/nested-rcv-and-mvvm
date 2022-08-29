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
                Log.d(TAG, "onCreateViewHolder: banneritemview");
                View bannerItemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_image, parent, false);
                return new BannerViewHolder(bannerItemView);

            case 1:
                Log.d(TAG, "onCreateViewHolder: carouseitemview");
                View carouselItemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_view_type, parent, false);
                return new CarouselViewHolder(carouselItemView);

            case 2:
                View innerListItemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_list, parent, false);
                return new InnerListViewTypeViewHolder(innerListItemView);

            // TODO(): figure out what to do with the default statement
            default:
                bannerItemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_image, parent, false);
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

    private class InnerListViewTypeViewHolder extends RecyclerView.ViewHolder {

        TextView innerListTitleTv;
        RecyclerView innerListRcv;

        public InnerListViewTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            innerListTitleTv = itemView.findViewById(R.id.inner_list_item_title_tv);
            innerListRcv = itemView.findViewById(R.id.inner_list_rcv);
        }

        public void bind(ViewType viewType) {
            innerListTitleTv.setText(viewType.getTitle());

            InnerListRecyclerViewAdapter innerListAdapter =
                new InnerListRecyclerViewAdapter(viewType.getViewItems(), onImageClickedListener);
            innerListRcv.setAdapter(innerListAdapter);
        }
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView bannerImageView;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            bannerImageView = view.findViewById(R.id.img_banner_image);
        }

        public void bind(ViewType viewType) {
            Log.d(TAG, "bind --> BannerViewHolder");
            bannerImageView.setOnClickListener(v -> {
                onImageClickedListener.onImageClicked(viewType.getImageUrl());
            });

            Glide
                .with(view.getContext())
                .load(viewType.getImageUrl())
                .into(bannerImageView);
        }
    }

    private class CarouselViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView carouselTitleTv;
        ViewPager2 carouselItemViewPager;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            carouselTitleTv = itemView.findViewById(R.id.carousel_title_tv);
            carouselItemViewPager = itemView.findViewById(R.id.carousel_item_view_pager);
        }

        public void bind(ViewType viewType) {
            Log.d(TAG, "bind --> CarouselViewHolder");
            carouselTitleTv.setText(viewType.getTitle());
            CarouselViewPagerAdapter carouselViewPagerAdapter = new CarouselViewPagerAdapter(
                fragmentActivity, viewType.getViewItems(),
                onImageClickedListener
            );
            carouselItemViewPager.setAdapter(carouselViewPagerAdapter);
        }
    }
}
