package com.example.pandas.personal.homeinteractive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.InteractiveInfoBean;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InteractiveInfoActivity extends BaseActivity {

    @Bind(R.id.interactive_webview)
    WebView interactiveWebview;
    WebSettings settings;
    @Bind(R.id.interactiveinfo_finish)
    ImageView interactiveinfoFinish;
    @Bind(R.id.interactiveinfo_title)
    TextView interactiveinfoTitle;
    @Bind(R.id.interactiveinfo_share)
    ImageView interactiveinfoShare;
    private ArrayList<InteractiveInfoBean.InteractiveBean> list;
    private ArrayList<HomePageBean.DataBean.InteractiveBean.InteractiveoneBean> lists;
    private ArrayList<HomePageBean.DataBean.BigImgBean> bigImgList;
    private int pos,poss,position;
    private ProgressDialog dialog;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_interactive_info;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        dialog=new ProgressDialog(this);

        intent = getIntent();

        list= intent.getParcelableArrayListExtra("data");
        pos= intent.getIntExtra("pos",0);

        lists= intent.getParcelableArrayListExtra("datas");
        poss= intent.getIntExtra("poss",0);

        bigImgList=intent.getParcelableArrayListExtra("bigImgList");
        position=intent.getIntExtra("position",0);

        settings = interactiveWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        if(intent.getParcelableArrayListExtra("data")!=null && intent.getParcelableArrayListExtra("data").containsAll(list)){
            interactiveinfoTitle.setText(list.get(pos).getTitle());
            interactiveWebview.loadUrl(list.get(pos).getUrl());
        }else if(intent.getParcelableArrayListExtra("datas")!=null && intent.getParcelableArrayListExtra("datas").containsAll(lists)){
            interactiveinfoTitle.setText(lists.get(poss).getTitle());
            interactiveWebview.loadUrl(lists.get(poss).getUrl());
        }else{
            interactiveWebview.loadUrl(bigImgList.get(position).getUrl());
        }


    }


    private UMShareListener umShareListener=new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(InteractiveInfoActivity.this, share_media + "分享成功了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(InteractiveInfoActivity.this, share_media + "分享失败了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(InteractiveInfoActivity.this, share_media + "分享取消了", Toast.LENGTH_SHORT).show();
        }
    };



    @OnClick({R.id.interactiveinfo_finish, R.id.interactiveinfo_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.interactiveinfo_finish:
                finish();
                break;
            case R.id.interactiveinfo_share:
                if(intent.getParcelableArrayListExtra("data")!=null && intent.getParcelableArrayListExtra("data").containsAll(list)){
                    UMImage umImage=new UMImage(InteractiveInfoActivity.this,list.get(pos).getImage());
                    UMImage thumb = new UMImage(InteractiveInfoActivity.this, list.get(pos).getImage());
                    umImage.setThumb(thumb);
                    UMWeb umWeb=new UMWeb(list.get(pos).getUrl());
                    umWeb.setTitle(list.get(pos).getUrl());
                    umWeb.setThumb(thumb);

                    new ShareAction(InteractiveInfoActivity.this).setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.SINA)
                            .withText(list.get(pos).getTitle())
                            .withMedia(umImage)
                            .withMedia(umWeb)
                            .setCallback(umShareListener)
                            .open();
                }else if(intent.getParcelableArrayListExtra("datas")!=null && intent.getParcelableArrayListExtra("datas").containsAll(lists)){
                    UMImage umImage=new UMImage(InteractiveInfoActivity.this,lists.get(poss).getImage());
                    UMImage thumb = new UMImage(InteractiveInfoActivity.this, lists.get(poss).getImage());
                    umImage.setThumb(thumb);
                    UMWeb umWeb=new UMWeb(lists.get(poss).getUrl());
                    umWeb.setTitle(lists.get(poss).getUrl());
                    umWeb.setThumb(thumb);

                    new ShareAction(InteractiveInfoActivity.this).setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.SINA)
                            .withText(lists.get(poss).getTitle())
                            .withMedia(umImage)
                            .withMedia(umWeb)
                            .setCallback(umShareListener)
                            .open();
                }else{
                    UMImage umImage=new UMImage(InteractiveInfoActivity.this,bigImgList.get(position).getImage());
                    UMImage thumb = new UMImage(InteractiveInfoActivity.this, bigImgList.get(position).getImage());
                    umImage.setThumb(thumb);
                    UMWeb umWeb=new UMWeb(bigImgList.get(position).getUrl());
                    umWeb.setTitle(bigImgList.get(position).getUrl());
                    umWeb.setThumb(thumb);

                    new ShareAction(InteractiveInfoActivity.this).setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.SINA)
                            .withText(bigImgList.get(position).getTitle())
                            .withMedia(umImage)
                            .withMedia(umWeb)
                            .setCallback(umShareListener)
                            .open();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
