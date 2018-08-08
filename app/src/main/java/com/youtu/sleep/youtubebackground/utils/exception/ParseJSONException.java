package com.youtu.sleep.youtubebackground.utils.exception;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thuy on 08/08/2018.
 */
public class ParseJSONException extends Exception {
    public ParseJSONException(JSONException e){
        super(e);
    }
}
