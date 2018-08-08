package com.youtu.sleep.youtubebackground.data.model.popularvideo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by thuy on 01/08/2018.
 */
public class Item {
    @SerializedName("snippet")
    @Expose
    private Snippet snippet;

    public Snippet getSnippet() {
        return snippet;
    }

}
