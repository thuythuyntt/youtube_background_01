package com.youtu.sleep.youtubebackground.screens.video;

import android.media.MediaPlayer;

/**
 * Created by DaiPhongPC on 8/8/2018.
 */

public interface VideoCallback {
    void alreadyPlayVideo();

    void addHolderSurface(MediaPlayer mp);

    void updateStatusVideo(boolean status);

    void showMessagePlayVideoError();
}
