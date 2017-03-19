package com.example.anew.news.news.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anew.news.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MenuFragment extends BaseFragment {
    @BindView(R.id.lv_listview)
    ListView mListView;

    private String[] mMenuTiles = {"新闻", "专题", "组图", "互动"};
    private int mInt = 0;
    private OnMenuChangeListener mMenuChangeListener;

    @Override
    public int getLayoutId() {
        return R.layout.fram_menu;
    }

    @Override
    public void init() {
        super.init();

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mClickListener);


    }

    private AdapterView.OnItemClickListener mClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            boolean isSwitch = (position != mInt);
            //菜单选项发生变化通知外界
            if (mMenuChangeListener != null) {
                mMenuChangeListener.OnMenuItemSwitch(position, isSwitch);
            }

            //如果是本身就不能点击
            if (mInt == position) {
                return;
            }
            //点击切换颜色
            View mView = parent.getChildAt(position);
            mView.setEnabled(true);
            //将之前的变白
            View childAt = parent.getChildAt(mInt);
            childAt.setEnabled(false);
            mInt = position;


        }
    };
    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {

            return mMenuTiles.length;
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
                convertView = View.inflate(getContext(), R.layout.view_item, null);


            }
            ((TextView) convertView).setText(mMenuTiles[position]);

            if (position == 0) {
                convertView.setEnabled(true);
            } else {
                convertView.setEnabled(false);
            }


            return convertView;
        }
    };

    public interface OnMenuChangeListener {
        //菜单选项切换
        void OnMenuItemSwitch(int position, boolean isSwitch);
    }

    public void setOnMenuChangeListener(OnMenuChangeListener listener) {
        mMenuChangeListener = listener;


    }
}
