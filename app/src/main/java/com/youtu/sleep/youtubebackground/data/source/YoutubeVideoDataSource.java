package com.youtu.sleep.youtubebackground.data.source;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;

import java.util.List;

/**
 * Created by thuy on 06/08/2018.
 */
public interface YoutubeVideoDataSource {

    interface CallBack<T>{
        void onSuccess(T data);
        void onFail(String message);
    }

    /**
     * Local data for videos
     */

    interface LocalDataSource extends YoutubeVideoDataSource {
        interface OnActionLocalListener {
            void onSuccess();
            void onSuccess(List<Video> list);

            void onFail();
        }

        void addToFavouriteVideoList(Video video, OnActionLocalListener listener);

        void getFavouriteVideos(OnActionLocalListener listener);

        void removeFromFavouriteVideoList(Video video, OnActionLocalListener listener);
    }

    /**
     * Remote data for videos
     */

    interface RemoteDataSource extends YoutubeVideoDataSource {

        void getPopularVideos(CallBack<List<Video>> callBack);

        void searchVideos(String query, CallBack<List<Video>> callBack);
    }
}
