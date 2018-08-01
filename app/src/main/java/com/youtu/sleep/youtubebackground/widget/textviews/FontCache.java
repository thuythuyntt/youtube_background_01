package com.youtu.sleep.youtubebackground.widget.textviews;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by DaiPhongPC on 7/31/2018.
 */

public class FontCache {
    public static final String PATH_FONT = "fonts/";
    private static HashMap<String, Typeface> sFontCache = new HashMap<>();

    public static Typeface getTypeface(Context context, String fontName) {
        Typeface typeface = sFontCache.get(fontName);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), PATH_FONT + fontName);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            sFontCache.put(fontName, typeface);
        }
        return typeface;
    }
}
