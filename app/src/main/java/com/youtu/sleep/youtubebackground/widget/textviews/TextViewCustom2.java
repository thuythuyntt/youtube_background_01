package com.youtu.sleep.youtubebackground.widget.textviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.youtu.sleep.youtubebackground.utils.Contants;

/**
 * Created by thuy on 06/08/2018.
 */
public class TextViewCustom2 extends android.support.v7.widget.AppCompatTextView {
    public TextViewCustom2(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextViewCustom2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextViewCustom2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    public void applyCustomFont(Context context) {
        Typeface typeface = FontCache.getTypeface(context, Contants.NAME_FONT_2);
        setTypeface(typeface);
    }
}
