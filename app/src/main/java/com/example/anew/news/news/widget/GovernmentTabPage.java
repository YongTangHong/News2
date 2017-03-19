package com.example.anew.news.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/19.
 */

public class GovernmentTabPage extends TabPage {
    private static final String TAG = "GovernmentTabPage";
        
    public GovernmentTabPage(Context context) {
        super(context);
    }

    public GovernmentTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuSwitch(int position) {
        Log.d(TAG, "onMenuSwitch: "+position);
    }
}
