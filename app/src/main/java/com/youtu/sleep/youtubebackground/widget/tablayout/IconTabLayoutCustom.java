package com.youtu.sleep.youtubebackground.widget.tablayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;

import com.youtu.sleep.youtubebackground.R;

/**
 * Created by DaiPhongPC on 8/1/2018.
 */

public class IconTabLayoutCustom extends TabLayout {
    public IconTabLayoutCustom(Context context) {
        super(context);
    }

    public IconTabLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IconTabLayoutCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTabsFromPagerAdapter(@Nullable PagerAdapter adapter) {
        this.removeAllTabs();
        Integer arr[] = {R.drawable.home_selector, R.drawable.recent_selector, R.drawable.favourite_selector};
        for (int i = 0; i < adapter.getCount(); i++) {
            this.addTab(this.newTab().setCustomView(R.layout.icon_tablayout).setIcon(arr[i]));
        }
    }
}
