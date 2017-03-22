package com.example.anew.news.news.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.example.anew.news.R;
import com.example.anew.news.news.activity.NewsDataActivity;
import com.example.anew.news.news.app.Constans;
import com.example.anew.news.news.bean.NewsBeans;
import com.example.anew.news.news.network.NetWorkManger;
import com.example.anew.news.news.network.NetsWorkListeren;
import com.example.anew.news.news.network.NewsRequest;
import com.example.anew.news.news.utils.SpUitls;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.leon.loopviewpagerlib.FunBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/20.
 */

public class NewsPullToRefreshlistView extends PullToRefreshListView {
    private static final String TAG = "NewsPullToRefreshlistVi";
   // private int[] imageResIds = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};
    private String mUrl;
    private String mMore;
    private NewsBeans mNewsBeans;
    private FunBanner mFunBanner;


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

        int height = getResources().getDimensionPixelOffset(R.dimen.indicator_height);
        //创建轮播图
        FunBanner.Builder builder = new FunBanner.Builder(getContext());

        mFunBanner = builder.setEnableAutoLoop(true)

                .setDotSelectedColor(Color.RED)
                .setHeightWidthRatio(0.5f) //图片宽高比,高除以宽
                .setLoopInterval(5000)      //每隔5秒
                .setEnableAutoLoop(true)  //设置启动轮播
                .setIndicatorBarHeight(height)
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .build();
        getRefreshableView().addHeaderView(mFunBanner);
        //设置下拉刷新的监听
        setOnRefreshListener(mListener2);

        //设置列表选项的点击事件
        setOnItemClickListener(mItemClickListener);
    }
    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position-2;
            Intent intent = new Intent(getContext(),NewsDataActivity.class);
            String url = mNewsBeans.getData().getNews().get(position).getUrl();
            intent.putExtra("url",url);
            getContext().startActivity(intent);

            //把新闻标记已读,持久化存储
            int newsBeanId = mNewsBeans.getData().getNews().get(position).getId();
            SpUitls.saveBoolean(getContext(),String.valueOf(newsBeanId),true);

            //刷新列表
            mAdapter.notifyDataSetChanged();

        }
    };
    private OnRefreshListener2<ListView> mListener2 = new OnRefreshListener2<ListView>() {


        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
            //重新发送网络请求获取最新数据
            NewsRequest<NewsBeans> newsRequest = new NewsRequest<NewsBeans>(NewsBeans.class,mUrl,mNetsWorkListeren);
            NetWorkManger.sendRequest(newsRequest);
        }
        @Override

        public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
            //上拉加载更多
            if(mMore.length() > 0) {
                //还有更多数据,加载更多
                String url = Constans.Host +mMore;
                NewsRequest<NewsBeans> request = new NewsRequest<NewsBeans>(NewsBeans.class,url,mWorkListeren);
                NetWorkManger.sendRequest(request);

            }else {
                Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                //延迟刷新
                post(new Runnable() {
                    @Override
                    public void run() {
                            onRefreshComplete(); //重置
                    }
                });
            }
        }
    };
    private NetsWorkListeren<NewsBeans> mWorkListeren = new NetsWorkListeren<NewsBeans>(){
        @Override
        public void onResponse(NewsBeans response) {
            mMore = response.getData().getMore();
            //获取更多新闻数据,添加到
            mNewsBeans.getData().getNews().addAll(response.getData().getNews());
            mAdapter.notifyDataSetChanged();
            onRefreshComplete();
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);
        }
    };

    private NetsWorkListeren<NewsBeans> mNetsWorkListeren = new NetsWorkListeren<NewsBeans>() {
        @Override
        public void onResponse(NewsBeans response) {
            //刷新列表
            updataNewsList(mNewsBeans);
            onRefreshComplete();//下拉刷新结束
        }


        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);
        }
    };
    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if(mNewsBeans != null) {

                return mNewsBeans.getData().getNews().size();
            }
            return 0;
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
                ViewHolder holder = null;
            if (convertView == null) {
                convertView = android.view.View.inflate(getContext(), R.layout.new_listview, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            NewsBeans.DataBean.NewsBean newsBean = mNewsBeans.getData().getNews().get(position);
            //刷新标题
            holder.mTitle.setText(newsBean.getTitle());
            //如果新闻已读就设置未灰色
            if(SpUitls.getBoolean(getContext(),String.valueOf(newsBean.getId()))) {
                holder.mTitle.setTextColor(Color.GRAY);
            }else {
                holder.mTitle.setTextColor(Color.BLACK);
            }
            //刷新时间
            holder.mTime.setText(newsBean.getPubdate());

            //刷新网络图片
            holder.mNetworkImageView.setImageUrl(newsBean.getListimage(),NetWorkManger.getImageLoader());

            return convertView;
        }
    };
    public class ViewHolder {
        @BindView(R.id.tv_news_title)
        TextView mTitle;
        @BindView(R.id.tv_time)
        TextView mTime;
        @BindView(R.id.iv_new_pull)
        NetworkImageView mNetworkImageView;
        public ViewHolder(View root) {
           // mTitle = (TextView) root.findViewById(R.id.tv_news_title);
            //mTime = (TextView)root.findViewById(R.id.tv_time);
            ButterKnife.bind(this,root);
        }
    }

    //设置新闻列表的URL
    public void setUrl(String url) {
        mUrl = url;
        NewsRequest<NewsBeans> request = new NewsRequest<NewsBeans>(
                NewsBeans.class,
                url,
                mListeren);
        //发送网络请求
       // Volley.newRequestQueue(getContext()).add(request);
        NetWorkManger.sendRequest(request);
    }
    private NetsWorkListeren<NewsBeans> mListeren = new NetsWorkListeren<NewsBeans>(){
        @Override
        public void onResponse(NewsBeans response) {

            updataNewsList(response);
        }



        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };
    private  void updataNewsList(NewsBeans response) {
        mNewsBeans = response;
        mAdapter.notifyDataSetChanged();
        //刷新轮播图

        List<String> imageUrls = new ArrayList<>();
        List<String> title = new ArrayList<>();

        List<NewsBeans.DataBean.TopnewsBean> topnewsBeen = mNewsBeans.getData().getTopnews();

        for(int i = 0; i < topnewsBeen.size();i++) {
            imageUrls.add(topnewsBeen.get(i).getTopimage());
            title.add(topnewsBeen.get(i).getTitle());
        }
        // mFunBanner.setImageUrls(imageUrls);
        mFunBanner.setImageUrlsAndTitles(imageUrls,title); //title
    }
   /* private Response.Listener<NewsBeans> mListener = new Response.Listener<NewsBeans>() {



        @Override
        public void onResponse(NewsBeans response) {
            //Log.d(TAG, "onResponse: "+response.getData().getNews().get(0).getTitle());
            //保存网络数据结果
            mNewsBeans = response;
            //通知listview刷新数据
            mAdapter.notifyDataSetChanged();
        }
    };
    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };*/
}
