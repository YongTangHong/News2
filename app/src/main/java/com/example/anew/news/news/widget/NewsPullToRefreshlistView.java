package com.example.anew.news.news.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.anew.news.R;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.leon.loopviewpagerlib.FunBanner;

/**
 * Created by Administrator on 2017/3/20.
 */

public class NewsPullToRefreshlistView extends PullToRefreshListView {
    private int[] imageResIds = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};
    public NewsPullToRefreshlistView(Context context) {
        this(context, null);
    }

    public NewsPullToRefreshlistView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();

    }

    private void init() {
        setAdapter(mAdapter);
        setMode(Mode.BOTH); //既能上拉也能上拉
        //创建轮播图
        FunBanner.Builder builder = new FunBanner.Builder(getContext());
        FunBanner funBanner = builder.setEnableAutoLoop(true)
                .setImageResIds(imageResIds)        //
                .setDotSelectedColor(Color.RED)
                .setHeightWidthRatio(0.5556f) //图片宽高比,高除以宽
                .setLoopInterval(5000)      //每隔5秒
                .setEnableAutoLoop(true)  //设置启动轮播
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .build();
        getRefreshableView().addHeaderView(funBanner);

    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.new_listview, null);

            }
            return convertView;
        }
    };


}
