package com.youtu.sleep.youtubebackground.screens.video;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;

import java.util.List;

/**
 * Created by DaiPhongPC on 8/9/2018.
 */

public interface OnGetListUrlVideoYoutube {
    void onSuccess(List<Video> videos);

    void onError(String mess);
}
