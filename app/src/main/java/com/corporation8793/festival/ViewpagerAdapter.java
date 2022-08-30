package com.corporation8793.festival;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewpagerAdapter extends FragmentStateAdapter {

    public ViewpagerAdapter(@NonNull FindActivity findActivity) {
        super(findActivity);
        //this.mPageCount = pageCount;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       return FragmentPractice.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
