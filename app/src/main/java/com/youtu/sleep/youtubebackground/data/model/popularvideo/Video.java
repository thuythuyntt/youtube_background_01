package com.youtu.sleep.youtubebackground.data.model.popularvideo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thuy on 07/08/2018.
 */
public class Video implements Parcelable {
    private String mId, mTitle, mChannelTitle, mDescription, mUrlThumbnail, mUrl;

    public Video(String id, String title,
                 String channelTitle, String description,
                 String urlThumbnail) {
        this.mId = id;
        this.mTitle = title;
        this.mChannelTitle = channelTitle;
        this.mDescription = description;
        this.mUrlThumbnail = urlThumbnail;
    }

    protected Video(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mChannelTitle = in.readString();
        mDescription = in.readString();
        mUrlThumbnail = in.readString();
        mUrl = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getId() {
        return mId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mChannelTitle);
        parcel.writeString(mDescription);
        parcel.writeString(mUrlThumbnail);
        parcel.writeString(mUrl);
    }
}
