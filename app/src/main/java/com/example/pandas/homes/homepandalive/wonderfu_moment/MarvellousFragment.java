package com.example.pandas.homes.homepandalive.wonderfu_moment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.homes.homepandalive.PandaLivePresent;
import com.example.pandas.homes.homepandalive.SendingContract;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
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

    @Override
    protected int getLayoutId() {
        return R.layout.pandlive_recycler;
    }

    @Override
    protected void init(View view) {
        new PandaLivePresent(this);
        otherTabDetails = new ArrayList<>();
    }

    @Override
    protected void loadData() {
        presenter.strat();
        vsid =  getArguments().getString("vsid");

        presenter.loadMore(vsid,"7","panda","desc","time",String.valueOf(p));

        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        otherTabAdapter = new OtherTabAdapter(getActivity(),otherTabDetails);
        xRecyclerView.setAdapter(otherTabAdapter);

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

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {

    }

    @Override
    public void setLiveTabBean(LiveTabBean bean) {

    }


    @Override
    public void setOtherTabBean(OtherTabDetail bean) {
        otherTabDetails.addAll(bean.getVideo());
        otherTabAdapter.notifyDataSetChanged();
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
