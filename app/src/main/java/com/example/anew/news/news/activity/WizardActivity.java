package com.example.anew.news.news.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.anew.news.R;
import com.example.anew.news.news.utils.SpUitls;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/16.
 */
public class WizardActivity extends BaseActivity{

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.sup)
    Button mBtnSup;
    @BindView(R.id.indicator)
    CirclePageIndicator mIndicator;

    private int[] images = {R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};
    @Override
    public int getLayoutId() {
        return R.layout.activity_wizard;
    }


    //初始化界面
    @Override
    protected void init() {
        super.init();
        //初始化adapter
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mOnPager);

        //关联vewPager
        mIndicator.setViewPager(mViewPager);
    }

    //滑动到最后一页
    private ViewPager.OnPageChangeListener mOnPager = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            //滑动到最后一页
            if(images.length-1 == position) {
                mBtnSup.setVisibility(View.VISIBLE);//滑动到最后一页显示按钮
            }else {
                mBtnSup.setVisibility(View.GONE);  //其他隐藏
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(WizardActivity.this);
            imageView.setImageResource(images[position]); //图片位置
            imageView.setScaleType(ImageView.ScaleType.FIT_XY); //填充
            container.addView(imageView);
            //返回页面的标记
            return imageView;
        }


        //消除
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }
    };

    @OnClick(R.id.sup)
   public void  OnClick() {

       jumpActivity(MainActivity.class);
        //保存看过的想到界面
        SpUitls.saveBoolean(this,"sta",true);




    }
}
