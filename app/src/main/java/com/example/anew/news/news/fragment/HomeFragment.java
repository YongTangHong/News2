package com.example.anew.news.news.fragment;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.anew.news.R;
import com.example.anew.news.news.widget.GovernmentTabPage;
import com.example.anew.news.news.widget.HomeTabPage;
import com.example.anew.news.news.widget.NewCenterTabPage;
import com.example.anew.news.news.widget.SettingTabPage;
import com.example.anew.news.news.widget.SmartServiceTabPage;
import com.example.anew.news.news.widget.TabPage;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/17.
 */

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.tab_page_content)
    FrameLayout mFrameLayout;

    @BindView(R.id.tab_contianer)
    RadioGroup mRadioGroup;

    private SparseArray<TabPage> mTabPageCache = new SparseArray<>();//TabPage的内存缓存

    private OnHomeChangeListener mChangeListener;

    private TabPage mTabPage;

    @Override
    public int getLayoutId() {

        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        super.init();

        //设置radiogroup点击事件
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //查找内存缓存中有没有缓存,有就用缓存,没有就创建
                TabPage tabPage = null;
                if (mTabPageCache.get(checkedId) != null) {
                    tabPage = mTabPageCache.get(checkedId);

                } else {
                    tabPage = getTabPage(checkedId);



                }

                mTabPage = tabPage;
                //移除原来所有的tabpage
                mFrameLayout.removeAllViews();

                mFrameLayout.addView(tabPage);
                //通知外界事件的切换
                if(mChangeListener != null) {
                    mChangeListener.onTabSwicth(checkedId);
                }
            }

            @NonNull
            private TabPage getTabPage(int checkedId) {
                //添加tabPage到FrameLayout

                //隐藏首页和设置中心的图标
                TabPage tabPage = null;
                switch (checkedId) {
                    case R.id.rb_tab_home:  //首页
                        tabPage = new HomeTabPage(getContext());
                        tabPage.hideMenu();
                        tabPage.setTitle("首页");
                        break;

                    case R.id.rb_tab_news:  //新闻中心
                        tabPage = new NewCenterTabPage(getContext());
                        tabPage.setTitle("新闻中心");
                        break;

                    case R.id.rb_tab_service:  //服务
                        tabPage = new SmartServiceTabPage(getContext());
                        tabPage.setTitle("智慧服务");
                        break;

                    case R.id.rb_tab_setting:   //设置
                        tabPage = new SettingTabPage(getContext());
                        tabPage.hideMenu();
                        tabPage.setTitle("设置中心");

                        break;

                    case R.id.rb_tab_zhenwu:       //政务
                        tabPage = new GovernmentTabPage(getContext());
                        tabPage.setTitle("政务");
                        break;

                }
                //创建一个新的缓存到内存中
                mTabPageCache.put(checkedId, tabPage);

                //在创建TabPage的时候设置监听器
                tabPage.setOnTabPageChangeListener(new TabPage.OnTabPageChangeListener() {
                    @Override
                    public void OnTabPageClick() {
                        Toast.makeText(getContext(), "事件传递到HomeFragment", Toast.LENGTH_SHORT).show();
                        //通知
                        if(mChangeListener != null) {
                            mChangeListener.onTabPageMenu();
                        }
                    }
                });


                return tabPage;
            }
        });


        //默认显示首页,不能放在点击事件前面
        mRadioGroup.check(R.id.rb_tab_home);
    }

    public void onMenuSwitch(int position) {
       // Toast.makeText(getContext(), "HomeFragment获取到菜单选项的切换", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMenuSwitch: "+position);
        mTabPage.onMenuSwitch(position);
    }

    //接口回调
    public interface OnHomeChangeListener{


        void onTabSwicth(int checkId);

        //菜单按钮的点击事件
        void onTabPageMenu();

    }
    public void setOnHomeChangeListener(OnHomeChangeListener listener) {

        mChangeListener = listener;
    }
}
