package com.youtu.sleep.youtubebackground.screens.main.home;
import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import java.util.List;

/**
 * Created by thuy on 06/08/2018.
 */
public interface PopularVideosContract {
    interface View {
        void showPopularVideos(List<Video> videos);
        void showGetPopularVideosErrorMessage(String message);
    }

    interface Presenter {
        void getPopularVideos();
    }
}
