package com.example.pandas.homes.homepandalive;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.model.datebean.pandasending.SendingBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/7/10.
 */

public class PandaLiveMain extends BaseFragment implements SendingContract.View {

    @Bind(R.id.pandaLive_login)
    ImageView pandaLiveLogin;
    @Bind(R.id.pandaLive_radioGroup)
    RadioGroup pandaLiveRadioGroup;
    @Bind(R.id.pandaLive_ptrr)
    PullToRefreshRecyclerView pandaLivePtrr;

    private SendingContract.Presenter present;
    private List<SendingBean> sendingBeanList;
    private MyAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_pandalive;
    }

    @Override
    protected void init(View view) {
        new PandaLivePresent(this);
        sendingBeanList = new ArrayList<>();
        setTabText();
        pandaLivePtrr.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyAdapter(getActivity(),sendingBeanList);
        pandaLivePtrr.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        present.strat();

    }

    public void setTabText() {
        //        直播
//        精彩一刻
//        当熊不让
//        超萌滚滚秀
//        熊猫档案
//       熊猫top榜
//        熊猫那些事
//        特别节目
//        原创新闻
    }

    @OnClick(R.id.pandaLive_login)
    public void onViewClicked() {

    }

    @Override
    public void setPresenter(SendingContract.Presenter presenter) {
        this.present = presenter;
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {
        Log.d("PandaLiveMain", "bean.getLive().size():" + bean.getLive().size());
    }

    @Override
    public void setShowMessage(String message) {

    }
}
