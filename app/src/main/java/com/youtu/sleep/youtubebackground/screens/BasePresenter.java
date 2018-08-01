package com.youtu.sleep.youtubebackground.screens;

/**
 * Created by DaiPhongPC on 8/1/2018.
 */

public interface BasePresenter<T> {
    void setView(T view);

    void onStart();

    void onStop();

}
