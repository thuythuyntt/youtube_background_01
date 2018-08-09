package com.youtu.sleep.youtubebackground.screens.main.favourite;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.data.repository.YoutubeVideoRepository;
import com.youtu.sleep.youtubebackground.data.source.YoutubeVideoDataSource;

import java.util.List;

/**
 * Created by thuy on 09/08/2018.
 */
public class FavouriteVideosPresenter implements FavouriteVideosContract.Presenter {

    private FavouriteVideosContract.View mView;
    private YoutubeVideoRepository mModel;

    public FavouriteVideosPresenter(FavouriteVideosContract.View mView, YoutubeVideoRepository repository) {
        this.mView = mView;
        this.mModel = repository;
    }

    @Override
    public void getFavouriteVideos() {
        mModel.getFavouriteVideos(new YoutubeVideoDataSource.LocalDataSource.OnActionLocalListener() {
            @Override
            public void onSuccess() {
                //nothing todo
            }

            @Override
            public void onSuccess(List<Video> videos) {
                mView.showFavouriteVideos(videos);
            }

            @Override
            public void onFail() {
                mView.showFavouriteVideosErrorMessage();
            }
        });
    }
}
