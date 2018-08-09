package com.youtu.sleep.youtubebackground.data.model.popularvideo;

import android.os.Parcel;
import android.os.Parcelable;

import static com.youtu.sleep.youtubebackground.utils.Contants.FALSE;

/**
 * Created by thuy on 07/08/2018.
 */
public class Video implements Parcelable{
    private String mVideoId, mTitle, mChannelTitle, mDescription, mUrlThumbnail, mUrl;
    private int mIsRecent, mIsFavourite = FALSE;

    public Video(String videoId, String title, String channelTitle, String description, String urlThumbnail) {
        this.mVideoId = videoId;
        this.mTitle = title;
        this.mChannelTitle = channelTitle;
        this.mDescription = description;
        this.mUrlThumbnail = urlThumbnail;
    }

    public Video(String videoId, String title, String channelTitle, String description,
                 String urlThumbnail, int isRecent, int isFavourite) {
        this.mVideoId = videoId;
        this.mTitle = title;
        this.mChannelTitle = channelTitle;
        this.mDescription = description;
        this.mUrlThumbnail = urlThumbnail;
        this.mIsRecent = isRecent;
        this.mIsFavourite = isFavourite;
    }

    protected Video(Parcel in) {
        mVideoId = in.readString();
        mTitle = in.readString();
        mChannelTitle = in.readString();
        mDescription = in.readString();
        mUrlThumbnail = in.readString();
        mUrl = in.readString();
        mIsRecent = in.readInt();
        mIsFavourite = in.readInt();
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

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getVideoId() {
        return mVideoId;
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

    public int getIsRecent() {
        return mIsRecent;
    }

    public int getIsFavourite() {
        return mIsFavourite;
    }

    public void setIsRecent(int isRecent) {
        this.mIsRecent = isRecent;
    }

    public void setIsFavourite(int isFavourite) {
        this.mIsFavourite = isFavourite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mVideoId);
        parcel.writeString(mTitle);
        parcel.writeString(mChannelTitle);
        parcel.writeString(mDescription);
        parcel.writeString(mUrlThumbnail);
        parcel.writeString(mUrl);
        parcel.writeInt(mIsRecent);
        parcel.writeInt(mIsFavourite);
    }
}
