package com.youtu.sleep.youtubebackground.data.model.popularvideo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thuy on 01/08/2018.
 */
public class MostPopularVideos {
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

}
