package com.example.pandas.homes.homepandalive.main_live.live_fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.homes.homepandalive.SendingContract;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
import com.example.pandas.utils.LogUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 多视角直播
 */
public class MultiAngleFragment extends BaseFragment implements SendingContract.View {
    @Bind(R.id.panda_live_grid)
    GridView pandaLiveGrid;

    private SendingContract.Presenter presenter;
    private MultipleGridAdapter adapter;
    private ArrayList<MultipleBean.ListBean> multipleList;
    private String url;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            multipleList.clear();
            presenter.strat();
            adapter.notifyDataSetChanged();
            LogUtils.setLog("Multi",multipleList.size()+"::");
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.pandalive_multiple;
    }

    @Override
    protected void init(View view) {

        multipleList = new ArrayList<>();

        IntentFilter filter = new IntentFilter();
        filter.addAction("can.refresh");
        getActivity().registerReceiver(receiver,filter);

        adapter = new MultipleGridAdapter(getActivity(),multipleList);
        pandaLiveGrid.setAdapter(adapter);

        pandaLiveGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("com.sending_url");
                intent.putExtra("image_url",multipleList.get(position).getImage());
                intent.putExtra("url",multipleList.get(position).getUrl());
                getActivity().sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.strat();
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
    public void setShowMessage(String message) {

    }

    @Override
    public void setMultipleBean(MultipleBean bean) {
        LogUtils.setLog("maf",bean.getList().size()+"");
        multipleList.addAll(bean.getList());

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setWatchAtBean(WatchChatBean bean) {

    }
}
