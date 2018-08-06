package com.youtu.sleep.youtubebackground.screens.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.youtu.sleep.youtubebackground.R;
import com.youtu.sleep.youtubebackground.screens.main.favourite.FavouriteFragment;
import com.youtu.sleep.youtubebackground.screens.main.home.HomeFragment;
import com.youtu.sleep.youtubebackground.screens.main.recent.RecentFragment;
import com.youtu.sleep.youtubebackground.widget.tablayout.IconTabLayoutCustom;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        ViewPager.OnPageChangeListener {
    private IconTabLayoutCustom mTablayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private ViewPagerTabAdapter mTabAdapter;
    private TextView mTextViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTextViewTitle.setText(mTabAdapter.getPageTitle(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * setupView
     */
    public void setupView() {
        mTextViewTitle = findViewById(R.id.text_title);
        setupViewPager();
        setupTabLayout();
    }

    /**
     * setup Tablayout
     */
    public void setupTabLayout() {
        mTablayout = (IconTabLayoutCustom) findViewById(R.id.tablayout);
        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.setTabsFromPagerAdapter(mTabAdapter);
    }

    /**
     * setup tablayout + view pager
     */
    public void setupViewPager() {
        mViewPager = findViewById(R.id.viewpager);
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new RecentFragment());
        mFragments.add(new FavouriteFragment());
        mTabAdapter = new ViewPagerTabAdapter(getBaseContext(), getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mTabAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

}
