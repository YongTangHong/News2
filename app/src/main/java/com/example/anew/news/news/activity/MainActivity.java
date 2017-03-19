package com.example.anew.news.news.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.anew.news.R;
import com.example.anew.news.news.fragment.HomeFragment;
import com.example.anew.news.news.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    private HomeFragment mHomeFragment;
    private MenuFragment mMenuFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLeftMenu(); //初始化左边的菜单

        intSlidnMenu(); //配置菜单

        initContent();  //设置内容区域


        initEvent();   //事件监听

    }

    private void initEvent() {
        mHomeFragment.setOnHomeChangeListener(new HomeFragment.OnHomeChangeListener() {
            @Override
            public void onTabSwicth(int checkId) {
                //首页和设置中心不能拉出侧滑菜单

                if(checkId == R.id.rb_tab_home || checkId == R.id.rb_tab_setting) {
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }else {
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.LEFT);   //其他可以边缘拉出
                }

            }

            @Override
            public void onTabPageMenu() {
                Toast.makeText(MainActivity.this, "事件从Home传到mainActivity", Toast.LENGTH_SHORT).show();
                //打开或者关闭菜单按钮
                getSlidingMenu().toggle();
            }
        });
        mMenuFragment.setOnMenuChangeListener(new MenuFragment.OnMenuChangeListener() {
            @Override
            public void OnMenuItemSwitch(int position,boolean isSwitch) {
                //关闭菜单选项
                getSlidingMenu().toggle();
                if(isSwitch) {
                    mHomeFragment.onMenuSwitch(position);
                }
            }
        });

    }


    //初始化内容区域
    private void initContent() {
                mHomeFragment = new HomeFragment();
                setContentView(R.layout.content_frame);
                getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mHomeFragment)
                .commit();


    }
    //配置侧滑菜单

    private void intSlidnMenu() {
        SlidingMenu sm = getSlidingMenu();
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置从边缘拉出
        getSlidingMenu().setMode(SlidingMenu.LEFT);


    }

    //初始化左边的菜单
    private void initLeftMenu() {


            setBehindContentView(R.layout.menu_frame);

            FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
            mMenuFragment = new MenuFragment();
            t.replace(R.id.menu_frame, mMenuFragment);
            t.commit();

    }
}
