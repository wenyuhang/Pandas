package com.example.pandas.homes.homepandalive.oher_tab;


import android.view.View;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.homes.homepandalive.SendingContract;
import com.example.pandas.homes.homepandalive.wonderfu_moment.OtherTabAdapter;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;

public class LiveOtherTabFragment extends BaseFragment {
    @Bind(R.id.pandaLive_xrecycler)
    XRecyclerView xRecyclerView;
    private SendingContract.Presenter presenter;
    private List<OtherTabDetail> otherTabDetails;
    private OtherTabAdapter otherTabAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.pandlive_recycler;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {
//        Map<String, String> map = new HashMap<>();
//        map.put("", "");
//        presenter.setParams(map);
    }
}
