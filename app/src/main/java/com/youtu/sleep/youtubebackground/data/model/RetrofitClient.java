package com.youtu.sleep.youtubebackground.data.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thuy on 30/07/2018.
 */
public class RetrofitClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(String baseUrl){
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
