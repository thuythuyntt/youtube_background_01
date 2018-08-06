package com.youtu.sleep.youtubebackground.screens.main;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.youtu.sleep.youtubebackground.R;
import com.youtu.sleep.youtubebackground.screens.main.home.HomeFragment;

import java.util.List;

/**
 * Created by DaiPhongPC on 8/1/2018.
 */
public class ViewPagerTabAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments;

    public ViewPagerTabAdapter(Context context, FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position) == null ? new HomeFragment() : mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(@Tab int position) {
        switch (position) {
            case Tab.HOME:
                return mContext.getResources().getString(R.string.tab_home);
            case Tab.RECENT:
                return mContext.getResources().getString(R.string.tab_recent);
            case Tab.FAVOURITE:
                return mContext.getResources().getString(R.string.tab_favourite);
        }
        return null;
    }

    @IntDef({Tab.HOME, Tab.RECENT, Tab.FAVOURITE})
    @interface Tab {
        int HOME = 0;
        int RECENT = 1;
        int FAVOURITE = 2;
    }
}
