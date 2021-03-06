package com.example.anew.news.news.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.anew.news.R;
import com.example.anew.news.news.app.Constans;
import com.example.anew.news.news.bean.CategoriesBean;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/19.
 */

public class NewsPage extends RelativeLayout {
    // private static final String[] CONTENT = new String[] { "体育", "娱乐", "新闻", "音乐", "游戏", "电影","电视","美食","旅游" };
    @BindView(R.id.tp_tabPager)
    TabPageIndicator mIndicator;

    @BindView(R.id.vp_viewPager)
    ViewPager mViewPager;
    private CategoriesBean.DataBean mData;

    public NewsPage(Context context) {
        this(context, null);
    }

    public NewsPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.news_page, this);
        ButterKnife.bind(this, this);
        mViewPager.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mViewPager);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            //return CONTENT.length;
            if(mData != null) {
               return mData.getChildren().size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           /* TextView textView = new TextView(getContext());
            textView.setText(CONTENT[position]);
            container.addView(textView);
            return textView;*/
            NewsPullToRefreshlistView newsPullToRefreshlistView = new NewsPullToRefreshlistView(getContext());
            String url = Constans.Host +mData.getChildren().get(position).getUrl();
            newsPullToRefreshlistView.setUrl(url);
            container.addView(newsPullToRefreshlistView);
            return newsPullToRefreshlistView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }

        //返回页面的标题
        //返回页面的标题
        @Override
        public CharSequence getPageTitle(int position) {

            //return CONTENT[position];
          return  mData.getChildren().get(position).getTitle();
        }
    };

    public void setData(CategoriesBean.DataBean data) {

        mData = data;
        //通知tabPage刷新数据
        mIndicator.notifyDataSetChanged();

        mPagerAdapter.notifyDataSetChanged();
    }
}
