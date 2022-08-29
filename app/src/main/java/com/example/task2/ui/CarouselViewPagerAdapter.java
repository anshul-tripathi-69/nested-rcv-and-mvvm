package com.example.task2.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.task2.model.data.ViewItems;

import java.util.List;

public class CarouselViewPagerAdapter extends FragmentStateAdapter {

    private final List<ViewItems> viewItemsList;
    ViewTypeImageClickListener onImageClickListener;

    public CarouselViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<ViewItems> viewItemsList, ViewTypeImageClickListener onImageClickListener) {
        super(fragmentActivity);

        this.viewItemsList = viewItemsList;
        this.onImageClickListener = onImageClickListener;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new CarouselItemFragment(viewItemsList.get(position).getImageUrl(), onImageClickListener);
    }

    @Override
    public int getItemCount() {
        return viewItemsList.size();
    }
}
