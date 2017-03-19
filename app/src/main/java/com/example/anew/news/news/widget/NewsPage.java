package com.example.anew.news.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.anew.news.R;

/**
 * Created by Administrator on 2017/3/19.
 */

public class NewsPage extends RelativeLayout {

    public NewsPage(Context context) {
        this(context,null );
    }

    public NewsPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.news_page,this);
    }
}
