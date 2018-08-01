package com.youtu.sleep.youtubebackground.utils.navigator;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.youtu.sleep.youtubebackground.R;

/**
 * Created by DaiPhongPC on 8/1/2018.
 */

public class Navigator {
    @NonNull
    private Activity mActivity;

    public Navigator(@NonNull Activity activity) {
        this.mActivity = activity;
    }

    public void startActivity(Intent intent) {
        mActivity.startActivity(intent);
        setActivityTransactionAnimation(ActivityTransition.START);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        mActivity.startActivityForResult(intent, requestCode);
        setActivityTransactionAnimation(ActivityTransition.START);
    }

    public void finishActivity() {
        mActivity.finish();
        setActivityTransactionAnimation(ActivityTransition.FINISH);
    }

    public void finishActivityWithResult(Intent intent, int requestCode) {
        mActivity.setResult(requestCode, intent);
        finishActivity();
    }

    private void setActivityTransactionAnimation(@ActivityTransition int animation) {
        switch (animation) {
            case ActivityTransition.NONE:
                break;
            case ActivityTransition.START:
                mActivity.overridePendingTransition(R.anim.anim_right, R.anim.anim_left);
                break;
            case ActivityTransition.FINISH:
                mActivity.overridePendingTransition(R.anim.enter_anim_rtl, R.anim.exit_anim_rtl);
                break;
        }
    }

    @IntDef({ActivityTransition.NONE, ActivityTransition.START, ActivityTransition.FINISH})
    @interface ActivityTransition {
        int NONE = 0;
        int START = 1;
        int FINISH = 2;
    }
}
