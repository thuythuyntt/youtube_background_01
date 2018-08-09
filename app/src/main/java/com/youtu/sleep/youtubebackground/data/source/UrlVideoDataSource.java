package com.youtu.sleep.youtubebackground.data.source;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.screens.video.OnGetListUrlVideoYoutube;

import java.util.List;

/**
 * Created by DaiPhongPC on 8/8/2018.
 */

public interface UrlVideoDataSource {

    /**
     * local url video
     */
    interface LocalDataSource extends UrlVideoDataSource {

    }

    /**
     * remote url video
     */
    interface RemoteDataSource extends UrlVideoDataSource {
        void getListUrlVideo(List<Video> videos, OnGetListUrlVideoYoutube onListener);
    }
}
