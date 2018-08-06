package com.youtu.sleep.youtubebackground.data.model;


import static com.youtu.sleep.youtubebackground.utils.Contants.BASE_URL;

/**
 * Created by thuy on 30/07/2018.
 */
public class APIUtils {
    public static API getApiInterface(){
        return RetrofitClient.getClient(BASE_URL).create(API.class);
    }
}
