package com.example.pandas.homes.pandaculture.panda_culture;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.config.VideoUtils;
import com.example.pandas.homes.pandaculture.adapter.VideoXRecylerAdapter;
import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;
import com.example.pandas.homes.pandaculture.bean.VideoStartBean;
import com.example.pandas.homes.pandaculture.contract.CultureContract;
import com.example.pandas.homes.pandaculture.contract.PandaCulturePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class RollDtialActivity extends BaseActivity implements CultureContract.View {
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
    ArrayList<CCTVBaen.VideoBean> itemlist;
    CultureContract.Presenter presenter;
    @Bind(R.id.goback_butt)
    ImageView gobackButt;
    PandaCulturePresenter pandaCulturePresenter;
    List<CCTVBaen.VideoBean> video;
    @Bind(R.id.culture_cctv_video)
    JCVideoPlayerStandard cultureCctvVideo;
    List<PlayVideo.VideoBean.Chapters2Bean> videourllist;
    @Bind(R.id.detils_pullto)
    XRecyclerView detilsPullto;
    VideoXRecylerAdapter adapter;
    int p=1;
    int pos=1;
    boolean flags=true;
    String des;
    @Override
    public int getLayoutId() {
        return R.layout.rollvideo_details;
    }

    @Override
    public void initview() {
        pandaCulturePresenter = new PandaCulturePresenter(this);
        itemlist = new ArrayList<>();
        videourllist = new ArrayList<>();
        adapter = new VideoXRecylerAdapter(itemlist,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        detilsPullto.setLayoutManager(manager);
        detilsPullto.setAdapter(adapter);
        detilsPullto.setPullRefreshEnabled(true);
        detilsPullto.setLoadingMoreEnabled(false);
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
                presenter.loadDate("6", "VSET100311356635", p+"", "panda","1");
                detilsPullto.loadMoreComplete();
            }
        });
        adapter.setOnClick(new VideoXRecylerAdapter.setOnClick() {
            @Override
            public void mSetOnClick(View v, int postion) {
//                Toast.makeText(RollDtialActivity.this,"吐司一下"+postion,Toast.LENGTH_SHORT).show();
                presenter.playVideo(itemlist.get(postion-1).getVid());
                pos=postion;
            }
        });


        collectNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flags=true){
                    flags=false;
                    collectNo.setImageResource(R.drawable.collect_yes);
                    Toast.makeText(RollDtialActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                    Intent it=new Intent();
                    it.setAction("culturcollect");
                    it.putExtra("image",itemlist.get(pos).getImg());
                    it.putExtra("title",itemlist.get(pos).getT());
                    sendBroadcast(it);
                }else{
                    collectNo.setImageResource(R.drawable.collect_no);
                    Toast.makeText(RollDtialActivity.this,"取消收藏",Toast.LENGTH_SHORT).show();
                    Intent it=new Intent();
                    it.setAction("quxiaoculturcollect");
                    sendBroadcast(it);
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(RollDtialActivity.this).setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.SINA)
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

            }
        });
        gobackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        presenter.loadDate("6", "VSET100311356635", p+"", "panda","1");

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
        des= cctvBaen.getVideoset().get_$0().getDesc();
        video = cctvBaen.getVideo();
        if(cctvBaen.getVideo()!=null){
            itemlist.addAll(video);
        }
        adapter.notifyDataSetChanged();

        if(itemlist.size()>0){
            presenter.playVideo(itemlist.get(0).getVid());
        }
    }

    @Override
    public void setvideoURl(PlayVideo playVideo) {
        if(videourllist.size()>0) {
            videourllist.clear();
        }
        videourllist.addAll(playVideo.getVideo().getChapters2());

        if(videourllist.size()>1&&itemlist.size()>1){
            VideoUtils.getUtils().playVideo(cultureCctvVideo, videourllist.get(0).getUrl(), "", itemlist.get(pos-1).getImg());
        }

    }

    @Override
    public void setStartVideoURL(VideoStartBean videoStartBean) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(CultureContract.Presenter presenter) {
        this.presenter = presenter;
    }
}