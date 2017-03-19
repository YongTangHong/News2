package com.example.anew.news.news.widget;

import android.content.Context;
import android.util.AttributeSet;
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
        if(mChangeListener != null) {
            mChangeListener.OnTabPageClick();
        }

    }

    public void hideMenu() {

        mMenu.setVisibility(View.GONE);
    }

    public void setTitle(String title) {

        mTitle.setText(title);
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
