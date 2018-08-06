package com.youtu.sleep.youtubebackground.data.source.remote;


import com.youtu.sleep.youtubebackground.data.model.API;
import com.youtu.sleep.youtubebackground.data.model.APIUtils;
import com.youtu.sleep.youtubebackground.data.model.popularvideo.MostPopularVideos;
import com.youtu.sleep.youtubebackground.data.source.PopularVideosDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.youtu.sleep.youtubebackground.BuildConfig.API_KEY;
import static com.youtu.sleep.youtubebackground.utils.Contants.PARAMETER_REGION_CODE;

/**
 * Created by thuy on 06/08/2018.
 */
public class PopularVideosRemoteDataSource implements PopularVideosDataSource.RemoteDataSource {

    API service = APIUtils.getApiInterface();

    @Override
    public void getPopularVideos(String part, String chart, final OnGetPopularVideosListener listener) {
        service.getMostPopularVideos(part, chart, PARAMETER_REGION_CODE, API_KEY).enqueue(new Callback<MostPopularVideos>() {
            @Override
            public void onResponse(Call<MostPopularVideos> call, Response<MostPopularVideos> response) {
                if(response.body() != null) listener.onSuccess(response.body().getItems());
            }

            @Override
            public void onFailure(Call<MostPopularVideos> call, Throwable t) {
                listener.onFail(call.toString());
            }
        });
    }
}
