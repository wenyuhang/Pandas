package com.example.pandas.homes.homepandalive.wonderfu_moment;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.ACache;
import com.example.pandas.config.CultureSpActivity;
import com.example.pandas.homes.homepandalive.OtherTabContract;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.PlayBean;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 熊猫直播中其他  tablayout
 */
public class MarvellousFragment extends BaseFragment implements OtherTabContract.View, OtherTabAdapter.OnclickGoPlay {
    @Bind(R.id.pandaLive_xrecycler)
    XRecyclerView xRecyclerView;
    @Bind(R.id.pandalive_marvell_probar)
    ProgressBar pandaliveMarvellProbar;
    @Bind(R.id.pandalive_marvell_relalayout)
    RelativeLayout pandaliveMarvellRelalayout;

    private OtherTabContract.Presenter presenter;
    private List<OtherTabDetail.VideoBean> otherTabDetails;
    private OtherTabAdapter otherTabAdapter;
    private String vsid;
    private int p = 1;
    private String chapters;
    private String lowChapters;

    @Override
    protected int getLayoutId() {
        return R.layout.pandlive_recycler;
    }

    @Override
    protected void init(View view) {
        otherTabDetails = new ArrayList<>();
        showProgressDialog();
    }

    @Override
    protected void loadData() {
        vsid = getArguments().getString("vsid");

        presenter.loadMore(vsid, "7", "panda", "desc", "time", String.valueOf(p));

        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        otherTabAdapter = new OtherTabAdapter(getActivity(), otherTabDetails);
        otherTabAdapter.setOnclickGoPlay(this);
        xRecyclerView.setAdapter(otherTabAdapter);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        View foot = LayoutInflater.from(getActivity()).inflate(R.layout.xrecycler_footer, null);
        xRecyclerView.setFootView(foot);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecyclerView.refreshComplete();
                otherTabDetails.clear();
                presenter.loadMore(vsid, "7", "panda", "desc", "time", "1");
                otherTabAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {

                xRecyclerView.loadMoreComplete();
                presenter.loadMore(vsid, "7", "panda", "desc", "time", String.valueOf(p));
                otherTabAdapter.notifyDataSetChanged();
                p++;
            }
        });

    }

    @Override
    public void showProgressDialog() {
        pandaliveMarvellRelalayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressDialog() {
        pandaliveMarvellRelalayout.setVisibility(View.GONE);
    }


    @Override
    public void setOtherTabBean(OtherTabDetail bean) {
//        if(bean !) {
//
//        }
        otherTabDetails.addAll(bean.getVideo());
        otherTabAdapter.notifyDataSetChanged();

        ACache aCache = ACache.get(getActivity());
//        aCache.put("",bean);
    }

    @Override
    public void setPlayBean(PlayBean bean) {
        chapters = bean.getVideo().getChapters().get(0).getUrl();
        lowChapters = bean.getVideo().getLowChapters().get(0).getUrl();
    }

    @Override
    public void setShowMessage(String message) {

    }

    @Override
    public void setPresenter(OtherTabContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void goPlay(int pos) {
        presenter.getPlay(otherTabDetails.get(pos).getVid());
        if (chapters != null) {

            Intent intent = new Intent(getActivity(), CultureSpActivity.class);
            intent.putExtra("title", otherTabDetails.get(pos).getT());
            intent.putExtra("url", chapters);
            getActivity().startActivity(intent);
        }
    }
}
