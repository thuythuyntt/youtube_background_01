package com.youtu.sleep.youtubebackground.screens.video;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.utils.Contants;

import java.io.IOException;
import java.util.List;

/**
 * Created by DaiPhongPC on 8/8/2018.
 */

public class VideoService extends Service implements MediaPlayer.OnPreparedListener {
    public static VideoCallback mCallbackVideo;
    private int mPosition;
    private List<Video> mListVideos;
    private MediaPlayer mMediaPlayer;
    private IBinder mIbind = new BinderService();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        receiveIntent(intent);
        return mIbind;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        mCallbackVideo.alreadyPlayVideo();
        mCallbackVideo.addHolderSurface(mediaPlayer);
        mCallbackVideo.updateStatusVideo(mediaPlayer.isPlaying());
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public List<Video> getListVideos() {
        return mListVideos;
    }

    public Video getCurrentVideo() {
        return mListVideos.get(mPosition);
    }

    /**
     * receive Intent
     */
    public void receiveIntent(Intent intent) {
        switch (intent.getAction()) {
            case ActionVideo.PLAY_NEW:
                mPosition = intent.getIntExtra(Contants.EXTRA_POSS, 0);
                mListVideos = intent.getParcelableArrayListExtra(Contants.EXTRA_LIST_VIDEOS);
                playVideo();
                break;
        }
    }

    /**
     * play new video
     */
    public void playVideo() {
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(mListVideos.get(mPosition).getUrl());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
            mCallbackVideo.showMessagePlayVideoError();
        }
    }

    /**
     * play or pause video
     */
    public void changeMediaStatus() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        } else {
            mMediaPlayer.start();
        }
        mCallbackVideo.updateStatusVideo(mMediaPlayer.isPlaying());
    }

    /**
     * stop Video
     */
    public void stopVideo() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    /**
     * get current duration video
     */
    public int getCurrentDurationVideo() {
        return mMediaPlayer != null ? mMediaPlayer.getCurrentPosition() : 0;
    }

    /**
     * get  duration video
     */
    public int getDurationVideo() {
        return mMediaPlayer != null ? mMediaPlayer.getDuration() : 0;
    }

    /**
     * change current time of video
     */
    public void changeCurrenttime(int mms) {
        mMediaPlayer.seekTo(mms);
    }

    public class BinderService extends Binder {
        VideoService getService() {
            return VideoService.this;
        }
    }
}
