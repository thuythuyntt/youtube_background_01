package com.youtu.sleep.youtubebackground.data.source;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;

import java.util.List;

/**
 * Created by thuy on 06/08/2018.
 */
public interface PopularVideosDataSource {

    interface OnGetPopularVideosListener {
        void onSuccess(List<Video> videos);
        void onFail(String message);
    }

    void getPopularVideos(OnGetPopularVideosListener listener);

    /**
     * Local data for videos
     */

    interface LocalDataSource extends PopularVideosDataSource{
    }

    /**
     * Remote data for videos
     */

    interface RemoteDataSource extends PopularVideosDataSource{
    }
}
