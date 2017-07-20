package com.example.pandas.homes.pandaculture.panda_culture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;
import com.example.pandas.homes.pandaculture.bean.VideoStartBean;
import com.example.pandas.homes.pandaculture.contract.CultureContract;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity implements CultureContract.View {


    @Bind(R.id.culture_webview)
    WebView cultureWebview;
    String url;
    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initview() {
        Intent it=getIntent();
        url=it.getStringExtra("url");
        WebSettings webSettings=cultureWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);//支持缩放
        webSettings.setBuiltInZoomControls(true);
        cultureWebview.loadUrl(url);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        Log.e("webview",url.toString());
        cultureWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if(url!=null){
                    cultureWebview.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                progressDialog = ProgressDialog.show(WebViewActivity.this,"提示","加载中！！！");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                progressDialog.cancel();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                cultureWebview.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

            }
        });
        cultureWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(PandaCultureEntity pandaCultureEntity) {

    }

    @Override
    public void setVideoResult(CCTVBaen cctvBaen) {

    }

    @Override
    public void setvideoURl(PlayVideo playVideo) {

    }

    @Override
    public void setStartVideoURL(VideoStartBean videoStartBean) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(CultureContract.Presenter presenter) {

    }
}
