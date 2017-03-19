package com.example.anew.news.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/17.
 */

public class SpUitls {

    private static final String FILE_NAME = "news";


    //保存布尔值
    public static void  saveBoolean(Context context, String key, boolean value) {

        SharedPreferences sharedPreferences  = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,value).apply();
    }
    //返回布尔值
    public static boolean getBoolean(Context context,String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key,false);
    }
}
