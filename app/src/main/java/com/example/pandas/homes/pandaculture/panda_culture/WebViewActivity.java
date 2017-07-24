package com.example.pandas.homes.pandaculture.panda_culture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;

public class WebViewActivity extends BaseActivity  {

    @Bind(R.id.culture_webview)
    WebView cultureWebview;
    String url;
    @Bind(R.id.fanhui)
    ImageView fanhui;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initview() {
        Intent it = getIntent();
        url = it.getStringExtra("url");
        WebSettings webSettings = cultureWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);//支持缩放
        webSettings.setBuiltInZoomControls(true);
        cultureWebview.loadUrl(url);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        Log.e("webview", url.toString());
        cultureWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (url != null) {
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
        cultureWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }
        });
       fanhui.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

    }

}
