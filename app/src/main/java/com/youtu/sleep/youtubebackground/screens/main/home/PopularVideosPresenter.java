package com.youtu.sleep.youtubebackground.screens.main.home;
import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.data.source.PopularVideosDataSource;
import com.youtu.sleep.youtubebackground.data.source.remote.PopularVideosRemoteDataSource;
import java.util.List;

/**
 * Created by thuy on 06/08/2018.
 */
public class PopularVideosPresenter implements PopularVideosContract.Presenter {

    private PopularVideosContract.View mView;
    private PopularVideosDataSource.RemoteDataSource mModel;

    public PopularVideosPresenter(PopularVideosContract.View mView) {
        this.mView = mView;
        this.mModel = new PopularVideosRemoteDataSource();
    }

    @Override
    public void getPopularVideos() {
        mModel.getPopularVideos(new PopularVideosDataSource.OnGetPopularVideosListener() {
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
}
