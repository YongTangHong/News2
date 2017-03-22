package com.example.anew.news.news.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.anew.news.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/22.
 */
public class NewsDataActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mBack;
    @BindView(R.id.iv_textsize)
    ImageView mTextSize;
    @BindView(R.id.iv_share)
    ImageView mShare;
    @BindView(R.id.wv_webview)
    WebView mWebView;

    @BindView(R.id.pb_progressBar)
    ProgressBar mProgressBar;
    private WebSettings mSetting;
    private CharSequence[] options = {"最小", "较小", "正常", "较大", "最大"};
    private int checkedPosition = 2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;

    }

    @Override
    protected void init() {

        super.init();
        //开启js
        mSetting = mWebView.getSettings();
        mSetting.setJavaScriptEnabled(true);


        //加载网页
        String url = getIntent().getStringExtra("url");
        mWebView.loadUrl(url);
        //显示进度,设置监听
        mWebView.setWebChromeClient(mClient);


    }

    private WebChromeClient mClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //网页加载完.隐藏
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            }

        }
    };

    @OnClick({R.id.iv_textsize, R.id.iv_share, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:

                break;
            case R.id.iv_textsize:
                showChangedTextDialog();
                break;

        }
    }

    private void showChangedTextDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("字体大小")
                .setSingleChoiceItems(options, checkedPosition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //消除
                        dialog.dismiss();
                        //g更新选中的位置
                        checkedPosition = which;

                        //改变字体大小
                        changeTextSize();
                    }


                });
                        builder.show();//显示对话框
    }

    private void changeTextSize() {

        switch (checkedPosition) {
            case 0:
                mSetting.setTextSize(WebSettings.TextSize.SMALLEST);
                break;
            case 1:
                mSetting.setTextSize(WebSettings.TextSize.SMALLER);
                break;
            case 2:
                mSetting.setTextSize(WebSettings.TextSize.NORMAL);
                break;
            case 3:
                mSetting.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case 4:
                mSetting.setTextSize(WebSettings.TextSize.LARGEST);
                break;
        }
    }
}
