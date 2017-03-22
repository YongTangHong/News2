package com.example.anew.news.news.app;

import android.app.Application;

import com.example.anew.news.news.network.NetWorkManger;

/**
 * Created by Administrator on 2017/3/22.
 * app进程对应一个application类
 */

public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManger.init(getApplicationContext());
    }

}
