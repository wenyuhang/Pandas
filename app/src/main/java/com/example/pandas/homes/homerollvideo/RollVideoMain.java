package com.example.pandas.homes.homerollvideo;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.homes.homerollvideo.adapter.OutPullAdapter;
import com.example.pandas.model.datebean.RollvideoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;


/**
 * Created by 联想 on 2017/7/10.
 */

public class RollVideoMain extends BaseFragment implements RollVideoContract.View{

    @Bind(R.id.panda_curtule_pullto)
    PullToRefreshRecyclerView pandaCurtulePullto;
    private RollVideoContract.Presenter presenter;
//下面列表的数据集合
    ArrayList<RollvideoBean.ListBean> listdate;
//   上面头部轮播图的数据集合
    ArrayList<RollvideoBean.BigImgBean> bigImglist;
//    pulltorecyclerview的适配器
    OutPullAdapter madapter;
    ViewPager topviewpager;
    String[] str=new String[4];
    View header;
    RelativeLayout viewplinerlayout;
    List<ImageView> imaglist;


    @Override
    protected int getLayoutId() {
        return R.layout.main_rollvideo;
    }

    @Override
    protected void init(View view) {
        listdate=new ArrayList<>();
        bigImglist=new ArrayList<>();
        imaglist=new ArrayList<>();
        header = LayoutInflater.from(getActivity()).inflate(R.layout.rellovideo_pullto_header_view, null);
        viewplinerlayout = (RelativeLayout) header.findViewById(R.id.header_linerlayout);
        topviewpager = (ViewPager) header.findViewById(R.id.header_viewpager);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        pandaCurtulePullto.setLayoutManager(manager);
        madapter=new OutPullAdapter(getActivity(),listdate);
        pandaCurtulePullto.setAdapter(madapter);
        pandaCurtulePullto.setPullRefreshEnabled(true);
        pandaCurtulePullto.setLoadingMoreEnabled(false);
        pandaCurtulePullto.addHeaderView(header);
        //设置是否显示上次刷新的时间
        pandaCurtulePullto.displayLastRefreshTime(true);
        //设置刷新回调
        pandaCurtulePullto.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pandaCurtulePullto.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pandaCurtulePullto.setRefreshComplete();
                        listdate.clear();
                         loadData();
                    }
                }, 2000);

            }
            @Override
            public void onLoadMore() {

            }
        });


    }
    //轮播计时器
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (topviewpager.getCurrentItem() >= (str.length - 1)) {
                        topviewpager.setCurrentItem(0);
                    } else {
                        topviewpager.setCurrentItem(topviewpager.getCurrentItem() + 1);
                    }

                }
            });
        }
    };


    public void dismissDialog() {

    }


    public void setResult(RollvideoBean rollvideoBean) {
        List<RollvideoBean.ListBean> list = rollvideoBean.getList();
        listdate.addAll(list);
        madapter.notifyDataSetChanged();
        List<RollvideoBean.BigImgBean> bigImg = rollvideoBean.getBigImg();
        bigImglist.addAll(bigImg);

        for(int i=0;i<str.length-1;i++){
            str[i]=bigImglist.get(i).getImage();
        }

        ViewPagerUtils.getIntance().setpager(getActivity(), str, viewplinerlayout, topviewpager);
        Timer timer = new Timer(true);
        timer.schedule(timerTask, 2000, 2000);
    }


    public void showMessage(String msg) {
    }

    public void setPresenter(RollVideoContract.Presenter presenter) {
       this.presenter=presenter;
    }

    @Override
    protected void loadData() {
        presenter.strat();
    }



    public void showProgressDialog() {

    }
}
