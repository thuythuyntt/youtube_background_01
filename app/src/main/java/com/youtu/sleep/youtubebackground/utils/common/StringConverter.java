package com.youtu.sleep.youtubebackground.utils.common;

/**
 * Created by DaiPhongPC on 8/8/2018.
 */

public class StringConverter {
    public static final String URL = "https://youtube-url.herokuapp.com/?url=https://www.youtube.com/watch?v=";

    public static String convertIdVideo(String idVideo) {
        StringBuilder sb = new StringBuilder(URL);
        sb.append(idVideo);
        return sb.toString();
    }

    public static String convertTimestampToHhmmss(int timestamp) {
        int hr = timestamp / 3600;
        int rem = timestamp % 3600;
        int mn = rem / 60;
        int sec = rem % 60;
        return String.format("%02d:%02d:%02d", hr, mn, sec);
    }

}
