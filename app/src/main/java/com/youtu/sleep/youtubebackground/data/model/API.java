package com.youtu.sleep.youtubebackground.data.model;


import com.youtu.sleep.youtubebackground.data.model.popularvideo.MostPopularVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.youtu.sleep.youtubebackground.utils.Contants.SUB_GET_MOST_POPULAR_VIDEO_URL;


/**
 * Created by thuy on 30/07/2018.
 */
public interface API {
    @GET(SUB_GET_MOST_POPULAR_VIDEO_URL)
    Call<MostPopularVideos> getMostPopularVideos(@Query("part") String part,
                                                 @Query("chart") String chart,
                                                 @Query("regionCode") String regionCode,
                                                 @Query("key") String key);
}
