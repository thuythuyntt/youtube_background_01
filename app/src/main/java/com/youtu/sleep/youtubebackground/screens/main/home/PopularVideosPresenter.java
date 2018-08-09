package com.youtu.sleep.youtubebackground.screens.main.home;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.data.repository.YoutubeVideoRepository;
import com.youtu.sleep.youtubebackground.data.source.YoutubeVideoDataSource;

import java.util.List;

/**
 * Created by thuy on 06/08/2018.
 */
public class PopularVideosPresenter implements PopularVideosContract.Presenter {

    private PopularVideosContract.View mView;
    private YoutubeVideoRepository mModel;

    public PopularVideosPresenter(PopularVideosContract.View mView, YoutubeVideoRepository repository) {
        this.mView = mView;
        this.mModel = repository;
    }

    @Override
    public void getPopularVideos() {
        mModel.getPopularVideos(new YoutubeVideoDataSource.RemoteDataSource.OnGetPopularVideosListener() {
            @Override
            public void onSuccess(List<Video> videos) {
                mView.showPopularVideos(videos);
            }

            @Override
            public void onFail(String message) {
                mView.showGetPopularVideosErrorMessage(message);
            }
        });
    }

    @Override
    public void insertVideoList(Video video) {
        mModel.addToFavouriteVideoList(video, new YoutubeVideoDataSource.LocalDataSource.OnActionLocalListener() {
            @Override
            public void onSuccess() {
                mView.insertVideoListSuccessfully();
            }

            @Override
            public void onSuccess(List<Video> list) {
                //nothing todo
            }

            @Override
            public void onFail() {
                mView.insertVideoListUnsuccessfully();
            }
        });
    }

    @Override
    public void removeVideoList(Video video) {

    }
}
