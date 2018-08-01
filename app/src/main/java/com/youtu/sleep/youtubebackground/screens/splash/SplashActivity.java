package com.youtu.sleep.youtubebackground.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youtu.sleep.youtubebackground.R;
import com.youtu.sleep.youtubebackground.screens.BaseActivity;
import com.youtu.sleep.youtubebackground.screens.main.MainActivity;
import com.youtu.sleep.youtubebackground.utils.navigator.Navigator;

/**
 * Created by thuy on 01/08/2018.
 */
public class SplashActivity extends BaseActivity {
    /**
     * show time of splash screen
     */
    public static final int TIME_DELAY = 1500;

    private ImageView mSleepImage;
    private ImageView mMusicImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initializeView();
        loadImage();
        handleSplash();
    }

    private void loadImage() {
        Glide.with(this).load(R.drawable.ic_sleep).into(mSleepImage);
        Glide.with(this).load(R.drawable.ic_music).into(mMusicImage);
    }

    private void initializeView() {
        mSleepImage = (ImageView) findViewById(R.id.image_sleep);
        mMusicImage = (ImageView) findViewById(R.id.image_music);
    }

    private void handleSplash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                Navigator navigator = new Navigator(SplashActivity.this);
                navigator.startActivity(intent);
            }
        }, TIME_DELAY);
    }
}
