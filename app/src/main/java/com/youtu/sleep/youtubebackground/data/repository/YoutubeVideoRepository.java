package com.youtu.sleep.youtubebackground.data.repository;

import android.content.Context;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.data.source.YoutubeVideoDataSource;
import com.youtu.sleep.youtubebackground.data.source.local.YoutubeVideoLocalDataSource;
import com.youtu.sleep.youtubebackground.data.source.remote.listvideo.HomeVideoRemoteDataSource;

/**
 * Created by thuy on 09/08/2018.
 */
public class YoutubeVideoRepository implements YoutubeVideoDataSource.RemoteDataSource, YoutubeVideoDataSource.LocalDataSource {

    private static YoutubeVideoRepository sInstance;

    private HomeVideoRemoteDataSource mYoutubeVideoRemoteDataSource;
    private YoutubeVideoLocalDataSource mYoutubeVideoLocalDataSource;

    private YoutubeVideoRepository(Context context) {
        mYoutubeVideoRemoteDataSource = HomeVideoRemoteDataSource.getInstance();
        mYoutubeVideoLocalDataSource = YoutubeVideoLocalDataSource.getInstance(context);
    }

    public static YoutubeVideoRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new YoutubeVideoRepository(context);
        }
        return sInstance;
    }

    @Override
    public void addToFavouriteVideoList(Video video, YoutubeVideoDataSource.LocalDataSource.OnActionLocalListener listener) {
        mYoutubeVideoLocalDataSource.addToFavouriteVideoList(video, listener);
    }

    @Override
    public void getFavouriteVideos(OnActionLocalListener listener) {
        mYoutubeVideoLocalDataSource.getFavouriteVideos(listener);
    }

    @Override
    public void getPopularVideos(OnActionRemoteListener listener) {
        mYoutubeVideoRemoteDataSource.getPopularVideos(listener);
    }

    @Override
    public void removeFromFavouriteVideoList(Video video, OnActionLocalListener listener) {
        mYoutubeVideoLocalDataSource.removeFromFavouriteVideoList(video, listener);
    }

    @Override
    public void searchVideos(String query, OnActionRemoteListener listener) {
        mYoutubeVideoRemoteDataSource.searchVideos(query, listener);
    }
}
