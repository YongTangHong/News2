package com.example.anew.news.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/19.
 */

public class SmartServiceTabPage extends TabPage {
    private static final String TAG = "SmartServiceTabPage";

    public SmartServiceTabPage(Context context) {
        super(context);
    }

    public SmartServiceTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuSwitch(int position) {
        Log.d(TAG, "onMenuSwitch: "+position);
    }
}
