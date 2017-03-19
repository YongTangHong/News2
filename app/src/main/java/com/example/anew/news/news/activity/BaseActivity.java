package com.example.anew.news.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 基类 封装公共方法
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        //集成buttenknife,绑定activity
        ButterKnife.bind(this);

        //初始化
        init();
    }

    //公共的方法
    protected  void init() {

    }


    //子类实现方法
    public abstract int getLayoutId();

    public void jumpActivity(Class activity) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        finish();
    }
}
