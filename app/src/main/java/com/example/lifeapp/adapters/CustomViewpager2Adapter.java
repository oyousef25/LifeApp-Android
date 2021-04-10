package com.example.lifeapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lifeapp.R;
import com.example.lifeapp.fragments.HomeViewpagerFragment;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 20th 2021
 *
 * CustomViewpager2 Adapter:
 * It is going to be the adapter for our home page viewpager
 */
public class CustomViewpager2Adapter extends FragmentStateAdapter {
    public CustomViewpager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return HomeViewpagerFragment.newInstance(R.drawable.cover_image);
            case 1:
                return HomeViewpagerFragment.newInstance(R.drawable.pager_recipes);
            case 2:
                return HomeViewpagerFragment.newInstance(R.drawable.pager_exercises);
            case 3:
                return HomeViewpagerFragment.newInstance(R.drawable.pager_locations);
            case 4:
                return HomeViewpagerFragment.newInstance(R.drawable.pager_stories);
            default:
                return HomeViewpagerFragment.newInstance(R.drawable.pager_exercises);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
