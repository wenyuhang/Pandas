package com.example.pandas.homes.homepandalive.wonderfu_moment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.ACache;
import com.example.pandas.homes.homepandalive.PandaLivePresent;
import com.example.pandas.homes.homepandalive.SendingContract;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
import com.example.pandas.utils.PopupWindowUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 熊猫直播中其他  tablayout
 */
public class MarvellousFragment extends BaseFragment implements SendingContract.View {
    @Bind(R.id.pandaLive_xrecycler)
    XRecyclerView xRecyclerView;

    private SendingContract.Presenter presenter;
    private List<OtherTabDetail.VideoBean> otherTabDetails;
    private OtherTabAdapter otherTabAdapter;
    private String vsid;
    private int p=1;
    private PopupWindowUtils pop;

    @Override
    protected int getLayoutId() {
        return R.layout.pandlive_recycler;
    }

    @Override
    protected void init(View view) {
        new PandaLivePresent(this);
        otherTabDetails = new ArrayList<>();
        showProgressDialog();
    }

    @Override
    protected void loadData() {
        presenter.strat();
        vsid =  getArguments().getString("vsid");

        presenter.loadMore(vsid,"7","panda","desc","time",String.valueOf(p));

//        PopupWindow
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        otherTabAdapter = new OtherTabAdapter(getActivity(),otherTabDetails);
        xRecyclerView.setAdapter(otherTabAdapter);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        View foot = LayoutInflater.from(getActivity()).inflate(R.layout.xrecycler_footer,null);
        xRecyclerView.setFootView(foot);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecyclerView.refreshComplete();
                otherTabDetails.clear();
                presenter.loadMore(vsid,"7","panda","desc","time","1");
                otherTabAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {

                xRecyclerView.loadMoreComplete();
                presenter.loadMore(vsid,"7","panda","desc","time",String.valueOf(p));
                otherTabAdapter.notifyDataSetChanged();
                p++;
            }
        });

    }

    @Override
    public void setPresenter(SendingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressDialog() {
        pop = PopupWindowUtils.getInstance(getActivity());
        pop.startPopup();
    }

    @Override
    public void dismissProgressDialog() {
        pop.stopPopup();
    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {

    }

    @Override
    public void setLiveTabBean(LiveTabBean bean) {

    }


    @Override
    public void setOtherTabBean(OtherTabDetail bean) {
        if(bean !=null) {
            dismissProgressDialog();
}
        otherTabDetails.addAll(bean.getVideo());
                otherTabAdapter.notifyDataSetChanged();

                ACache aCache = ACache.get(getActivity());
//        aCache.put("",bean);
                }

@Override
    public void setShowMessage(String message) {

    }

    @Override
    public void setMultipleBean(MultipleBean bean) {

    }

    @Override
    public void setWatchAtBean(WatchChatBean bean) {

    }

}
