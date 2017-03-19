package com.example.anew.news.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/19.
 */

public class NewCenterTabPage extends TabPage {
    private static final String TAG = "NewCenterTabPage";
    public NewCenterTabPage(Context context) {
        super(context);
    }


    public NewCenterTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuSwitch(int position) {
        Log.d(TAG, "onMenuSwitch: "+position);
        //TextView textView = new TextView(getContext());
        View view = null;
        switch (position) {
            case 0:
               // textView.setText("新闻");
                NewsPage newsPage = new NewsPage(getContext());
                view = newsPage;
                break;
            case 1:
                TextView textView = new TextView(getContext());
                textView.setText("专题");
                view = textView;
                break;
            case 2:
                TextView zutu = new TextView(getContext());
                zutu.setText("组图");
                view = zutu;
                break;
            case 3:
                TextView hudong = new TextView(getContext());
                hudong.setText("互动");
                view = hudong;
                break;
        }
        mFrameLayout.removeAllViews();
        mFrameLayout.addView(view);
    }
}
