package com.youtu.sleep.youtubebackground.data.source;


import com.youtu.sleep.youtubebackground.data.model.popularvideo.Item;

import java.util.List;

/**
 * Created by thuy on 06/08/2018.
 */
public interface PopularVideosDataSource {

    interface OnGetPopularVideosListener {
        void onSuccess(List<Item> list);
        void onFail(String message);
    }

    /**
     * Local data for videos
     */
    interface LocalDataSource extends PopularVideosDataSource{
        void getPopularVideos(OnGetPopularVideosListener listener);
    }

    /**
     * Remote data for videos
     */
    interface RemoteDataSource extends PopularVideosDataSource{
        void getPopularVideos(String part, String chart, OnGetPopularVideosListener listener);
    }
}
