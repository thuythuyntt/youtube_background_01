package com.youtu.sleep.youtubebackground.data.source.remote;

import android.os.AsyncTask;
import android.support.annotation.StringDef;

import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.screens.video.OnGetListUrlVideoYoutube;
import com.youtu.sleep.youtubebackground.utils.common.StringConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by DaiPhongPC on 8/8/2018.
 */

public class GetUrlVideoAsyncTask extends AsyncTask<List<Video>, Void, List<Video>> {
    public static final String TAG = "ExtraUrl";
    public static final String METHOD_GET = "GET";
    public static final String QUALITY_VIDEO = "medium";
    private OnGetListUrlVideoYoutube mListener;
    private Exception exception;

    public GetUrlVideoAsyncTask(OnGetListUrlVideoYoutube mListener) {
        this.mListener = mListener;
    }


    @Override
    protected List<Video> doInBackground(List<Video>[] lists) {
        List<Video> videos = lists[0];
        for (int i = 0; i < videos.size(); i++) {
            String s = StringConverter.convertIdVideo(videos.get(i).getVideoId());
            try {
                URL url = new URL(s);
                String result = parsingJsonToList(getJsonFromUrl(url));
                videos.get(i).setUrl(result);
            } catch (IOException | JSONException e) {
                exception = e;
                return null;
            }
        }
        return videos;
    }


    @Override
    protected void onPostExecute(List<Video> videos) {
        if (exception == null) {
            mListener.onSuccess(videos);
        } else {
            mListener.onError(exception.getMessage());
        }
    }

    /**
     * get json from url
     */
    public String getJsonFromUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(METHOD_GET);
        InputStream is = new BufferedInputStream(connection.getInputStream());
        return convertStreamToString(is);
    }

    /**
     * convert input stream to string
     */
    public String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    /**
     * convert json to List
     */

    public String parsingJsonToList(String json) throws JSONException {
        String url = null;
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String quality = jsonObject.getString(ParameterVideo.QUALITY);
            url = jsonObject.getString(ParameterVideo.URL);
            if (quality.equals(QUALITY_VIDEO)) return url;
        }
        return url;
    }

    @StringDef({ParameterVideo.URL, ParameterVideo.QUALITY})
    @interface ParameterVideo {
        String URL = "url";
        String QUALITY = "quality";
    }
}
