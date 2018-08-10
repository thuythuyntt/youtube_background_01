package com.youtu.sleep.youtubebackground.data.source.local;

import android.content.Context;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.data.source.YoutubeVideoDataSource;
import com.youtu.sleep.youtubebackground.data.source.local.db.DatabaseManager;

import java.util.ArrayList;

/**
 * Created by thuy on 08/08/2018.
 */
public class YoutubeVideoLocalDataSource implements YoutubeVideoDataSource.LocalDataSource {

    private static YoutubeVideoLocalDataSource sInstance;

    private static DatabaseManager mDatabaseManager;
    private OnActionLocalListener mListener;

    public static YoutubeVideoLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new YoutubeVideoLocalDataSource();
            mDatabaseManager = new DatabaseManager(context);
        }
        return sInstance;
    }

    @Override
    public void addToFavouriteVideoList(Video video, OnActionLocalListener listener) {
        this.mListener = listener;
        if (mDatabaseManager.insertAFavouriteVideo(video)) {
            this.mListener.onSuccess();
        } else this.mListener.onFail();
    }

    @Override
    public void getFavouriteVideos(OnActionLocalListener listener) {
        this.mListener = listener;
        ArrayList<Video> videos = mDatabaseManager.getFavouriteVideos();
        if (videos == null || videos.isEmpty()) {
            this.mListener.onFail();
        } else {
            this.mListener.onSuccess(videos);
        }
    }

    @Override
    public void removeFromFavouriteVideoList(Video video, OnActionLocalListener listener) {
        this.mListener = listener;
        mDatabaseManager.removeAFavouriteVideo(video.getVideoId());
        this.mListener.onSuccess();
    }
}
