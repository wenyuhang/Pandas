package com.example.pandas.homes.pandaculture.panda_culture;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.config.ACacheUtils;
import com.example.pandas.config.JCVideoPlayerStandard;
import com.example.pandas.config.VideoUtils;
import com.example.pandas.config.database.SqlUtils;
import com.example.pandas.homes.pandaculture.adapter.VideoXRecylerAdapter;
import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;
import com.example.pandas.homes.pandaculture.contract.ActivityContract;
import com.example.pandas.homes.pandaculture.contract.ActivityPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class RollDtialActivity extends BaseActivity implements ActivityContract.View,View.OnClickListener{
    @Bind(R.id.rollvideo_details_show_image)
    ImageView rollvideoDetailsShowImage;
    @Bind(R.id.visibility_linear)
    LinearLayout visibilityLinear;
    @Bind(R.id.detiles_text)
    TextView detilesText;
    @Bind(R.id.collect_no)
    ImageView collectNo;
    @Bind(R.id.share)
    ImageView share;
    @Bind(R.id.goback_butt)
    ImageView gobackButt;
    @Bind(R.id.culture_cctv_video)
    JCVideoPlayerStandard cultureCctvVideo;
    @Bind(R.id.detils_pullto)
    XRecyclerView detilsPullto;
    @Bind(R.id.culture_insiad_probar)
    ProgressBar cultureInsiadProbar;
    @Bind(R.id.culture_insiad_relalayout)
    RelativeLayout cultureInsiadRelalayout;
    ActivityContract.Presenter presenter;
    ActivityPresenter activityPresenter;
//    请求视频集的集合
    ArrayList<CCTVBaen.VideoBean> itemlist;
    List<CCTVBaen.VideoBean> video;
    List<PlayVideo.VideoBean.Chapters2Bean> videourllist;
    List<PlayVideo.VideoBean.Chapters4Bean> otherurl;
    VideoXRecylerAdapter adapter;
    int p = 1;
    int pos = 1;
    boolean flags=false;
    String des;
    @Override
    public int getLayoutId() {
        return R.layout.rollvideo_details;
    }

    @Override
    public void initview() {
        cultureInsiadRelalayout.setVisibility(View.VISIBLE);
        activityPresenter = new ActivityPresenter(this);
        itemlist = new ArrayList<>();
        otherurl=new ArrayList<>();
        videourllist = new ArrayList<>();
        collectNo.setOnClickListener(this);
        share.setOnClickListener(this);
        gobackButt.setOnClickListener(this);
        adapter = new VideoXRecylerAdapter(itemlist, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        detilsPullto.setLayoutManager(manager);
        detilsPullto.setAdapter(adapter);
        detilsPullto.setPullRefreshEnabled(true);
        detilsPullto.setLoadingMoreEnabled(true);
        adapter.setOnClick(new VideoXRecylerAdapter.setOnClick() {
            @Override
            public void mSetOnClick(View v, int postion) {
                presenter.playVideo(itemlist.get(postion - 1).getVid());
                pos = postion;
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date=format.format(new Date());
                SqlUtils.getInstance().add(0,itemlist.get(pos - 1).getImg(),itemlist.get(pos-1).getPtime(),itemlist.get(pos-1).getT(),date, videourllist.get(0).getUrl());
            }
        });
        rollvideoDetailsShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                i = visibilityLinear.getVisibility();
                if (i == 8) {
                    visibilityLinear.setVisibility(View.VISIBLE);
                    detilesText.setText(des);
                    rollvideoDetailsShowImage.setImageResource(R.drawable.lpanda_show);
                } else {
                    visibilityLinear.setVisibility(View.GONE);
                    rollvideoDetailsShowImage.setImageResource(R.drawable.lpanda_off);
                }
            }
        });

        presenter.loadDate("6", "VSET100311356635", p + "", "panda", "1");

    }

    @Override
    public void setVideoResult(CCTVBaen cctvBaen) {
        des = cctvBaen.getVideoset().get_$0().getDesc();
        video = cctvBaen.getVideo();
        if (cctvBaen.getVideo() != null) {
            itemlist.addAll(video);
        }
        adapter.notifyDataSetChanged();

        if (itemlist.size() > 0) {
            presenter.playVideo(itemlist.get(0).getVid());
        }
        detilsPullto.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                itemlist.clear();
                itemlist.addAll(video);
                adapter.notifyDataSetChanged();
                detilsPullto.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                p++;
                presenter.loadDate("6", "VSET100311356635", p + "", "panda", "1");
                detilsPullto.loadMoreComplete();
            }
        });
    }

    @Override
    public void setvideoURl(PlayVideo playVideo) {
        if(playVideo!=null){
            cultureInsiadRelalayout.setVisibility(View.GONE);
        }
        if (videourllist.size() > 0) {
            videourllist.clear();
        }
        videourllist.addAll(playVideo.getVideo().getChapters2());
        otherurl.addAll(playVideo.getVideo().getChapters4());
        if (videourllist.size() > 1 && itemlist.size() > 1) {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String videodate=format.format(new Date());
            VideoUtils.getUtils().playVideo(cultureCctvVideo, videourllist.get(0).getUrl(), "", itemlist.get(pos - 1).getImg());
            ACacheUtils.getUtils().setStorage(itemlist.get(pos - 1).getImg(),itemlist.get(pos-1).getPtime(),itemlist.get(pos-1).getT(),videodate,videourllist.get(0).getUrl(),otherurl.get(0).getUrl());
        }
    }


    @Override
    public void showMessage(String msg) {

    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void setPresenter(ActivityContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.collect_no:
               if (flags) {
                   collectNo.setImageResource(R.drawable.collect_no);
                   Toast.makeText(RollDtialActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                   ACacheUtils.getUtils().deleteStorage(itemlist.get(pos-1).getT());
                   flags = false;
               }else {
                   flags=true;
                   collectNo.setImageResource(R.drawable.collect_yes);
                   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   Date curadte=new Date(System.currentTimeMillis());
                   String datetime=format.format(curadte);
                   ACacheUtils.getUtils().setStorage(itemlist.get(pos - 1).getImg(),itemlist.get(pos-1).getPtime(),itemlist.get(pos-1).getT(),datetime, videourllist.get(0).getUrl(),otherurl.get(0).getUrl());
                   Toast.makeText(RollDtialActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();

               }
            break;
            case  R.id.share:
                new ShareAction(RollDtialActivity.this).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.SINA)
                        .withText("hello")
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {
                                Toast.makeText(RollDtialActivity.this, share_media + " 分享成功啦", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                Toast.makeText(RollDtialActivity.this, share_media + " 分享失败啦", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Toast.makeText(RollDtialActivity.this, share_media + " 分享取消啦", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })
                        .open();
                break;
            case  R.id.goback_butt:
                finish();
                break;

        }
    }
}