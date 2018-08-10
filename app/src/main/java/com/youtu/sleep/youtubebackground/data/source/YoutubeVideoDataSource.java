package com.youtu.sleep.youtubebackground.data.source;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;

import java.util.List;

/**
 * Created by thuy on 06/08/2018.
 */
public interface YoutubeVideoDataSource {

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
        interface OnActionRemoteListener {
            void onSuccess(List<Video> videos);

            void onFail(String message);
        }

        void getPopularVideos(OnActionRemoteListener listener);

        void searchVideos(String query, OnActionRemoteListener listener);
    }
}
