package com.youtu.sleep.youtubebackground.data.model.popularvideo;

/**
 * Created by thuy on 07/08/2018.
 */
public class Video {
    private String mTitle, mChannelTitle, mDescription, mUrlThumbnail;

    public Video(String title, String channelTitle, String description, String urlThumbnail) {
        this.mTitle = title;
        this.mChannelTitle = channelTitle;
        this.mDescription = description;
        this.mUrlThumbnail = urlThumbnail;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getChannelTitle() {
        return mChannelTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrlThumbnail() {
        return mUrlThumbnail;
    }
}
