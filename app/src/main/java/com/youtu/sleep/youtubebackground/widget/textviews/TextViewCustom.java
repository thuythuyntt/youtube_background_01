package com.youtu.sleep.youtubebackground.widget.textviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.youtu.sleep.youtubebackground.utils.Contants;

/**
 * Created by DaiPhongPC on 7/31/2018.
 */

public class TextViewCustom extends TextView {
    public TextViewCustom(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextViewCustom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextViewCustom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    public void applyCustomFont(Context context) {
        Typeface typeface = FontCache.getTypeface(context, Contants.NAME_FONT);
        setTypeface(typeface);
    }
}
