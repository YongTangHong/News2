package com.example.anew.news.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.anew.news.news.bean.CategoriesBean;
import com.example.anew.news.news.network.NetWorkManger;
import com.example.anew.news.news.network.NetsWorkListeren;
import com.example.anew.news.news.network.NewsRequest;

/**
 * Created by Administrator on 2017/3/19.
 */

public class NewCenterTabPage extends TabPage {
    private static final String TAG = "NewCenterTabPage";
    private CategoriesBean mCategoriesBean;

    public NewCenterTabPage(Context context) {
        super(context);
    }


    public NewCenterTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuSwitch(int position) {
        Log.d(TAG, "onMenuSwitch: " + position);
        //TextView textView = new TextView(getContext());
        View view = null;
        switch (position) {
            case 0:
                // textView.setText("新闻");
                NewsPage newsPage = new NewsPage(getContext());
                newsPage.setData(mCategoriesBean.getData().get(0)); //设置新闻数据
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
    //实现父类方法,加载新闻中心的数据


    @Override
    public void loadDataFragment() {
        //创建一个网络请
        String url = "http://10.0.2.2:8080/zhbj/categories.json";
        NewsRequest<CategoriesBean> newsRequest = new NewsRequest<CategoriesBean>(CategoriesBean.class, url, mWorkListeren);
        //发送网络请求
       // Volley.newRequestQueue(getContext()).add(newsRequest);
        NetWorkManger.sendRequest(newsRequest);
    }

    private NetsWorkListeren<CategoriesBean> mWorkListeren = new NetsWorkListeren<CategoriesBean>(){
        @Override
        public void onResponse(CategoriesBean response) {
            mCategoriesBean = response;
            //保存网络结果
            onMenuSwitch(0);
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);
        }
    };



    /*      //正确的监听
    private Response.Listener<CategoriesBean> mListener = new Response.Listener<CategoriesBean>() {



            @Override
        public void onResponse(CategoriesBean response) {
           // Log.d(TAG, "onResponse: "+response.getData().get(0).getTitle());
            //网络数据获取后,默认切换到新闻页面
                mCategoriesBean = response; //保存网络结果
                onMenuSwitch(0);

        }
    };
    //错误的监听
    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG, "onErrorResponse: ");
        }
    };
}*/
}