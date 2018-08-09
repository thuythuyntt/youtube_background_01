package com.youtu.sleep.youtubebackground.data.repository;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.data.source.UrlVideoDataSource;
import com.youtu.sleep.youtubebackground.data.source.remote.UrlVideoRemoteDataSource;
import com.youtu.sleep.youtubebackground.screens.video.OnGetListUrlVideoYoutube;

import java.util.List;

/**
 * Created by DaiPhongPC on 8/8/2018.
 */

public class UrlVideoRepository implements UrlVideoDataSource.RemoteDataSource {
    private static UrlVideoRepository sInstance;
    private static UrlVideoRemoteDataSource sRemote;

    private UrlVideoRepository(UrlVideoRemoteDataSource remote) {
        this.sRemote = remote;
    }

    public static UrlVideoRepository getInstance(UrlVideoRemoteDataSource remote) {
        if (sInstance == null) {
            sInstance=new UrlVideoRepository(remote);
        }
        return sInstance;
    }


    @Override
    public void getListUrlVideo(List<Video> videos, OnGetListUrlVideoYoutube onListener) {
        sRemote.getListUrlVideo(videos, onListener);
    }
}
