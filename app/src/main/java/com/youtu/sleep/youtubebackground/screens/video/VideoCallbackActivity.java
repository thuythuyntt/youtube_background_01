package com.youtu.sleep.youtubebackground.screens.video;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.youtu.sleep.youtubebackground.R;
import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.data.repository.UrlVideoRepository;
import com.youtu.sleep.youtubebackground.data.source.remote.UrlVideoRemoteDataSource;
import com.youtu.sleep.youtubebackground.screens.BaseActivity;
import com.youtu.sleep.youtubebackground.utils.Contants;
import com.youtu.sleep.youtubebackground.utils.common.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class VideoCallbackActivity extends BaseActivity implements VideoContract.View,
        SurfaceHolder.Callback,
        VideoCallback {
    public static final int DELAY = 1000;
    private ImageView mImagePlay, mImageNext, mImagePre, mImageFa, mImageLoop, mImageBack;
    private TextView mTextTitle, mTextTimeVideo;
    private ProgressBar mProgressBar;
    private SeekBar mSeekbarVideo;
    private SurfaceView mSurfaceVideo;
    private SurfaceHolder mHolderVideo;
    private VideoContract.Presenter mPresenter;
    private VideoService mBoundServiceVideo;
    private Runnable mRunnableSeekbar;
    private boolean mBound = false;
    private int mPositionVideo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        UrlVideoRepository repository = UrlVideoRepository.getInstance(UrlVideoRemoteDataSource.getInstance(this));
        mPresenter = new VideoPresenter(repository);
        mPresenter.setView(this);
        setupView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public int getCurrentPositionVideo() {
        return mPositionVideo != -1 ? mPositionVideo :
                getIntent().getIntExtra(Contants.EXTRA_POS_VIDEO, 0);
    }

    @Override
    public List<Video> getListVideo() {
        return getIntent().getParcelableArrayListExtra(Contants.EXTRA_LIST_VIDEO);
    }

    @Override
    public String getIdVideo(int position) {
        return getListVideo().get(position).getId();
    }

    @Override
    public void showVideo(String url) {
        if (mBound) {
            mBoundServiceVideo.getListVideos().get(mPositionVideo).setUrl(url);
            mBoundServiceVideo.setPosition(mPositionVideo);
            mBoundServiceVideo.playVideo();
        } else {
            startBindService(url);
        }

    }

    @Override
    public void showExtraUrlVideoErrorMessage() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mHolderVideo = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    /**
     * setup View
     */
    public void setupView() {
        mTextTimeVideo = findViewById(R.id.text_curent);
        mProgressBar = findViewById(R.id.progress);
        setupToolbar();
        setupControlPlayer();
        setupVideoView();
    }

    @Override
    public void alreadyPlayVideo() {
        mProgressBar.setVisibility(View.GONE);
        mSeekbarVideo.setMax(mBoundServiceVideo.getDurationVideo());
        mSeekbarVideo.postDelayed(mRunnableSeekbar = new Runnable() {
            @Override
            public void run() {
                mSeekbarVideo.setProgress(mBoundServiceVideo.getCurrentDurationVideo());
                mTextTimeVideo.setText(StringConverter.convertTimestampToHhmmss(mSeekbarVideo.getProgress() / 1000));
                mSeekbarVideo.postDelayed(mRunnableSeekbar, DELAY);
            }
        }, DELAY);
    }

    @Override
    public void addHolderSurface(MediaPlayer mp) {
        updateLayoutParameter(mp.getVideoWidth(), mp.getVideoHeight());
        mp.setDisplay(mHolderVideo);
    }

    @Override
    public void updateStatusVideo(boolean status) {
        if (status) {
            mImagePlay.setImageResource(R.drawable.ic_pause);
        } else {
            mImagePlay.setImageResource(R.drawable.ic_play);
        }
    }

    @Override
    public void showMessagePlayVideoError() {
        Toast.makeText(this, getString(R.string.load_video_data_fail_message), Toast.LENGTH_SHORT).show();
    }

    /**
     * setup toolbar
     */
    public void setupToolbar() {
        mImageBack = findViewById(R.id.image_back);
        mTextTitle = findViewById(R.id.text_title);
        mImageBack.setOnClickListener(on_click);
    }

    /**
     * setup video view
     */
    public void setupVideoView() {
        mSurfaceVideo = findViewById(R.id.sf_video);
        mSeekbarVideo = findViewById(R.id.seekbar_video);
        mSeekbarVideo.setOnSeekBarChangeListener(onSeekbarChange);
        mSurfaceVideo.getHolder().addCallback(this);
    }

    /**
     * On Duration Video change
     */
    private SeekBar.OnSeekBarChangeListener onSeekbarChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (b) {
                mBoundServiceVideo.changeCurrenttime(i);
            }
            seekBar.setProgress(i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    /**
     * setup control play video
     */
    public void setupControlPlayer() {
        mImagePlay = findViewById(R.id.image_play);
        mImagePre = findViewById(R.id.image_prev);
        mImageNext = findViewById(R.id.image_next);
        mImageLoop = findViewById(R.id.image_loop);
        mImageFa = findViewById(R.id.image_favourite);
        mImagePlay.setOnClickListener(on_click);
        mImageNext.setOnClickListener(on_click);
        mImagePre.setOnClickListener(on_click);
        mImageFa.setOnClickListener(on_click);
        mImageLoop.setOnClickListener(on_click);
    }

    /**
     * onclick
     */
    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_play:
                    mBoundServiceVideo.playOrPauseVideo();
                    break;
                case R.id.image_next:
                    mPositionVideo = getVideoNext();
                    stopSeekBar();
                    mBoundServiceVideo.stopVideo();
                    mPresenter.onStart();
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case R.id.image_prev:
                    mPositionVideo = getVideoPrevious();
                    stopSeekBar();
                    mPresenter.onStart();
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case R.id.image_loop:
                    mPresenter.updateSettingLoopVideo();
                    break;
                case R.id.image_favourite:
                    mPresenter.updateVideoFavourite(mBoundServiceVideo.getCurrentVideo());
                    break;
                case R.id.image_back:
                    break;
            }
        }
    };

    /**
     * start bind service
     */
    public void startBindService(String url) {
        VideoService.mVideoCallback = this;
        List<Video> list = getListVideo();
        mPositionVideo = getCurrentPositionVideo();
        list.get(mPositionVideo).setUrl(url);
        Intent intent = new Intent(VideoCallbackActivity.this, VideoService.class);
        intent.putExtra(Contants.EXTRA_POSS, mPositionVideo);
        intent.putParcelableArrayListExtra(Contants.EXTRA_LIST_VIDEOS, (ArrayList<? extends Parcelable>) list);
        intent.setAction(ActionVideo.PLAY_NEW);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * update layoutParameter Surface
     */
    public void updateLayoutParameter(int wVideo, int hVideo) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mSurfaceVideo.getLayoutParams();
        if (wVideo > hVideo) {
            params.width = width;
            float scale = width / (float) wVideo;
            params.height = (int) (hVideo * scale);
        } else {
            params.height = height;
            float scale = height / (float) hVideo;
            params.width = (int) (wVideo * scale);
        }
        mSurfaceVideo.setLayoutParams(params);
    }

    /**
     * cancel Handle;
     */
    public void stopSeekBar() {
        mSeekbarVideo.removeCallbacks(mRunnableSeekbar);
        mRunnableSeekbar = null;
    }

    /**
     * get Video Next
     */
    public int getVideoNext() {
        if (mPositionVideo < (mBoundServiceVideo.getListVideos().size() - 1)) {
            return ++mPositionVideo;
        }
        return mPositionVideo;
    }

    /**
     * get Video Previous
     */
    public int getVideoPrevious() {
        if (mPositionVideo > 1) {
            return --mPositionVideo;
        }
        return mPositionVideo;
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            VideoService.MyBind myBind = (VideoService.MyBind) iBinder;
            mBoundServiceVideo = myBind.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


}
