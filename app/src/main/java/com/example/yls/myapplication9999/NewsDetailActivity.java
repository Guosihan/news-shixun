package com.example.yls.myapplication9999;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by yls on 2017/6/29.
 */

public class NewsDetailActivity extends BaseActivity {

    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.acitivity_news_detail;
    }

    @Override
    public void initView() {

        mProgressBar = (ProgressBar) findViewById(R.id.pro);
        initWebView();
    }

    private void initWebView() {

        mWebView = (WebView) findViewById(R.id.web_view);
        //禁止用其他浏览器打开页面
        mWebView.setWebChromeClient(new WebChromeClient());
        //设置webview支持javascript脚本
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 当WebView加载网页时，显示加载网页的进度
        mWebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress==100){
                    mProgressBar.setVisibility(View.GONE);
                }else{
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        NewsEntity.ResultBean newsBean = (NewsEntity.ResultBean)
                getIntent().getSerializableExtra("news");
        mWebView.loadUrl(newsBean.getUrl());
        //左上角返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(newsBean.getTitle());
    } public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();   // 标题栏左上角的返回按钮，退出当前界面
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
