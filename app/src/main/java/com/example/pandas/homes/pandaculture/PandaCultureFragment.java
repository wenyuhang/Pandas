package com.example.pandas.homes.pandaculture;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.CultureSpActivity;
import com.example.pandas.config.database.SqlUtils;
import com.example.pandas.homes.pandaculture.adapter.PandaCultureBannerAdapter;
import com.example.pandas.homes.pandaculture.adapter.PandaCultureItemAdapter;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.bean.VideoStartBean;
import com.example.pandas.homes.pandaculture.contract.CultureContract;
import com.example.pandas.homes.pandaculture.contract.PandaCulturePresenter;
import com.example.pandas.homes.pandaculture.panda_culture.WebViewActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaCultureFragment extends BaseFragment implements CultureContract.View {

    @Bind(R.id.culture_pullrecycler)
    PullToRefreshRecyclerView culturePullrecycler;
    @Bind(R.id.culture_out_probar)
    ProgressBar cultureOutProbar;
    @Bind(R.id.culture_out_relalayout)
    RelativeLayout cultureOutRelalayout;
    private PandaCulturePresenter pandaCulturePersenter;
    private PandaCultureBannerAdapter pandaCultureBannerAdapter;
    private ViewPager pandaCultureViewPagerView;
    private TextView pandaCultureBannerTitle;
    private PandaCultureItemAdapter itemAdapter;
    private int currentPosition = 10000;
    private ViewGroup pointsLinearLayout;
    ImageView imageView;
    CultureContract.Presenter presenter;
    String titles,images,times;

    private List<CircleImageView> points;
    private List<PandaCultureEntity.BigImgBean> dataBeanList;
    private List<ImageView> imgs;
    private List<PandaCultureEntity.ListBean> listBeanList;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_culture;
    }

    @Override
    protected void init(View view) {
        cultureOutRelalayout.setVisibility(View.VISIBLE);
        imgs = new ArrayList<>();
        points = new ArrayList<>();
        listBeanList = new ArrayList<>();
        pandaCulturePersenter = new PandaCulturePresenter(this);
        dataBeanList = new ArrayList<>();
        itemAdapter = new PandaCultureItemAdapter(getActivity(), listBeanList);
        culturePullrecycler.setAdapter(itemAdapter);
        View pandaCultureView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_panda_culture_banner, null);
        pandaCultureViewPagerView = (ViewPager) pandaCultureView.findViewById(R.id.panda_culture_banner);
        pandaCultureBannerTitle = (TextView) pandaCultureView.findViewById(R.id.panda_culture_banner_title);
        pointsLinearLayout = (ViewGroup) pandaCultureView.findViewById(R.id.pointsLinearLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        culturePullrecycler.setLayoutManager(linearLayoutManager);
        culturePullrecycler.addHeaderView(pandaCultureView);
        culturePullrecycler.setPullRefreshEnabled(true);
        culturePullrecycler.setLoadingMoreEnabled(false);
        //设置是否显示上次刷新的时间
        culturePullrecycler.displayLastRefreshTime(true);

        itemAdapter.msetonlistener(new PandaCultureItemAdapter.setOnclick() {
            @Override
            public void setonlistener(String pid, String title, String image, String time) {
                presenter.startVideo(pid);
                titles = title;
                times=time;
                images=image;

            }
        });

    }

    @Override
    protected void loadData() {
        new PandaCulturePresenter(this);
        pandaCulturePersenter.strat();
        pandaCultureViewPagerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                for (CircleImageView circleImageView : points) {
                    circleImageView.setImageResource(R.drawable.white_point);
                }
                points.get(position % points.size()).setImageResource(R.drawable.gray_point);
                pandaCultureBannerTitle.setText(dataBeanList.get(position % points.size()).getTitle());
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pandaCultureViewPagerView.setOnTouchListener(montouch);

    }

    View.OnTouchListener montouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            int startX;
            int startY;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", dataBeanList.get(currentPosition % 4).getUrl());
                    startActivity(intent);
                    break;
            }

            return false;
        }
    };


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    currentPosition = currentPosition + 1;
                    pandaCultureViewPagerView.setCurrentItem(currentPosition);
                    sendEmptyMessageDelayed(1, 3000);
                    break;

            }
        }
    };

    private void createImg(PandaCultureEntity entity) {
        List<PandaCultureEntity.BigImgBean> tab = entity.getBigImg();
        int pointPosition = 0;
        for (int i = 0; i < tab.size(); i++) {
            PandaCultureEntity.BigImgBean bigImgBean = tab.get(i);
            String image = bigImgBean.getImage();
            imageView = new ImageView(getActivity());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(image).into(imageView);
            imgs.add(imageView);
            CircleImageView circleImageView = new CircleImageView(getActivity());
            circleImageView.setCircleModel(CircleImageView.POINT);
            RelativeLayout.LayoutParams pointViewParams = new RelativeLayout.LayoutParams(dp2Px(8), dp2Px(8));
            circleImageView.setLayoutParams(pointViewParams);
            circleImageView.setTag(pointPosition);
            circleImageView.setImageResource(R.drawable.white_point);
            points.add(circleImageView);
            pointsLinearLayout.addView(circleImageView);
            pointPosition++;

        }
        pandaCultureBannerAdapter = new PandaCultureBannerAdapter(imgs);
        points.get(0).setImageResource(R.drawable.gray_point);
        pandaCultureViewPagerView.setAdapter(pandaCultureBannerAdapter);
        pandaCultureBannerAdapter.notifyDataSetChanged();
        pandaCultureViewPagerView.setCurrentItem(currentPosition);
        handler.sendEmptyMessageDelayed(1, 3000);

    }

    public int dp2Px(int dpValue) {

        return (int) (getActivity().getResources().getDisplayMetrics().density * dpValue + 0.5f);

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(final PandaCultureEntity pandaCultureEntity) {
        dataBeanList.addAll(pandaCultureEntity.getBigImg());
        listBeanList.addAll(pandaCultureEntity.getList());
        createImg(pandaCultureEntity);
        itemAdapter.notifyDataSetChanged();
        if(pandaCultureEntity!=null){
            cultureOutRelalayout.setVisibility(View.GONE);
        }
        culturePullrecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                culturePullrecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        culturePullrecycler.setRefreshComplete();
                        dataBeanList.clear();
                        listBeanList.clear();
                        dataBeanList.addAll(pandaCultureEntity.getBigImg());
                        listBeanList.addAll(pandaCultureEntity.getList());
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void setStartVideoURL(VideoStartBean videoStartBean) {
        Log.e("playvideolist", videoStartBean.getVideo().getLowChapters().get(0).getUrl() + "请求有没有数据");
        if (videoStartBean.getVideo().getChapters().get(0).getUrl() != null) {
            Intent it = new Intent(getActivity(), CultureSpActivity.class);
            it.putExtra("url", videoStartBean.getVideo().getChapters().get(0).getUrl());
            it.putExtra("title", titles);
            it.putExtra("imgurl",images);
            it.putExtra("movietime",times);
            it.putExtra("otherurl", videoStartBean.getVideo().getChapters4().get(0).getUrl());
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=format.format(new Date());
            SqlUtils.getInstance().add(0,images,times,titles,date,videoStartBean.getVideo().getChapters().get(0).getUrl());
            startActivity(it);
        }
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(CultureContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
