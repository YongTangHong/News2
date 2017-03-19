package com.example.anew.news.news.activity;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.anew.news.R;
import com.example.anew.news.news.utils.SpUitls;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/16.
 */

public class SplashActivity  extends BaseActivity{


    private static final int  ANIMATION_DURRATION = 5000;
    @BindView(R.id.splash)
    ImageView mImageView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;

    }

    @Override
    protected void init() {
        super.init();
        //启动动画
        startAnimation();
    }

    private void startAnimation() {
        //动画集合
        AnimationSet set = new AnimationSet(false);



        //旋转动画
        RotateAnimation roAnimation = new RotateAnimation(
                0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        roAnimation.setDuration(ANIMATION_DURRATION);
        set.addAnimation(roAnimation);

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setDuration(ANIMATION_DURRATION);
        set.addAnimation(scaleAnimation);
        //透明动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(ANIMATION_DURRATION);
        set.addAnimation(alphaAnimation);
        //监听器
        set.setAnimationListener(mAnimationListener);
        mImageView.startAnimation(set);
    }
    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }
            //跳转到向导界面
        @Override
        public void onAnimationEnd(Animation aion) {

            /*Intent intent = new Intent(SplashActivity.this,WizardActivity.class);
            startActivity(intent);*/

            //判断是否进过想到界面
            if(SpUitls.getBoolean(SplashActivity.this,"sta")) {
                jumpActivity(MainActivity.class);
            }else {
                jumpActivity(WizardActivity.class);
            }



        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}