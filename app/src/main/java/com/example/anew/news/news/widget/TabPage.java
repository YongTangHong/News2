package com.example.anew.news.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anew.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/18.
 */

public class TabPage extends RelativeLayout {


    private static final String TAG = "TabPage";

    @BindView(R.id.iv_menu)
    ImageView mMenu;

    @BindView(R.id.tv_tilte)
    TextView mTitle;
    @BindView(R.id.fl_tab_frame)
    FrameLayout mFrameLayout;

    private OnTabPageChangeListener mChangeListener;


    public TabPage(Context context) {
        this(context, null);

    }

    public TabPage(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        View.inflate(getContext(), R.layout.view_tab_page, this);
        ButterKnife.bind(this, this);//绑定butterknife


    }

    @OnClick(R.id.iv_menu)
    public void onClick() {
        //通知外界发生了菜单按钮的点击事件
        if (mChangeListener != null) {
            mChangeListener.OnTabPageClick();
        }

    }

    public void hideMenu() {

        mMenu.setVisibility(View.GONE);
    }

    public void setTitle(String title) {

        mTitle.setText(title);
    }

    //左侧菜单选项切换事件
    public void onMenuSwitch(int position) {
        Log.d(TAG, "onMenuSwitch: " + position);
        TextView textView = new TextView(getContext());
        switch (position) {
            case 0:
                textView.setText("新闻");
                break;
            case 1:
                textView.setText("专题");
                break;
            case 2:
                textView.setText("组图");
                break;
            case 3:
                textView.setText("互动");
                break;


        }
        mFrameLayout.removeAllViews();
        mFrameLayout.addView(textView);
    }

    /**
     * 因为每个页面可能都要加载数据,所以在基类封装,由子类各自实现加载数据
     */
    public void loadDataFragment() {


    }


    //通知外界tabpage的变化
    public interface OnTabPageChangeListener {
        //菜单按钮的点击事件
        void OnTabPageClick();


    }

    public void setOnTabPageChangeListener(OnTabPageChangeListener listener) {

        mChangeListener = listener;

    }
}
