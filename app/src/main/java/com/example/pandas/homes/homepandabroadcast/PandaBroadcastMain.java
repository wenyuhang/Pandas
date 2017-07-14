package com.example.pandas.homes.homepandabroadcast;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.model.datebean.PdBBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by 联想 on 2017/7/10.
 */

public class PandaBroadcastMain extends BaseFragment implements PandaBroadcastContract.View {
    PandaBroadcastContract.Presenter presenter;
    @Bind(R.id.pandaBroadcast_login)
    ImageView pandaBroadcastLogin;
    @Bind(R.id.pdb_PullToRefreshRecyclerView)
    PullToRefreshRecyclerView pdbPullToRefreshRecyclerView;

    private String path = "iphoneInterface/general/getArticleAndVideoListInfo.json";
    private String primary_id = "PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE1451473628934144";
    private String serviceId = "panda";
    private TextView textView;
    private List<PdBBean.ListBean> list;
    private ArrayList<PdBBean.ListBean> listBeen;
    private PandaBroadcastAdapter pandaBroadcastAdapter;
    private ImageView pdb_up_image;

    @Override
    protected int getLayoutId() {
        return R.layout.main_pandabroadcast;
    }

    @Override
    protected void init(View view) {
        listBeen = new ArrayList<>();
    }
//
    @Override
    protected void loadData() {
        View inflate = View.inflate(getContext(), R.layout.pandabroadcast_up_item, null);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        inflate.setLayoutParams(params);
        textView = (TextView) inflate.findViewById(R.id.pdb_up_image_title);
        pdb_up_image = (ImageView) inflate.findViewById(R.id.pdb_up_image);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        pdbPullToRefreshRecyclerView.setLayoutManager(manager);
        pandaBroadcastAdapter = new PandaBroadcastAdapter(getContext(), listBeen);
        pdbPullToRefreshRecyclerView.setAdapter(pandaBroadcastAdapter);
        pdbPullToRefreshRecyclerView.addHeaderView(inflate);
        presenter.lodeMore(path, primary_id, serviceId);
        pdbPullToRefreshRecyclerView.setPullRefreshEnabled(true);
        pdbPullToRefreshRecyclerView.setLoadingMoreEnabled(false);
        pdbPullToRefreshRecyclerView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pdbPullToRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pdbPullToRefreshRecyclerView.setRefreshComplete();
                        pandaBroadcastAdapter.notifyDataSetChanged();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {

            }
        });
        presenter.strat();


    }


    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(PdBBean pdBBean) {
        list = pdBBean.getList();
        listBeen.addAll(list);
        pandaBroadcastAdapter.notifyDataSetChanged();
        textView.setText(list.get(0).getTitle());
        Glide.with(getContext()).load(list.get(0).getPicurl()).into(pdb_up_image);


    }

    @Override
    public void showMessage(String msg) {


    }

    @Override
    public void setPresenter(PandaBroadcastContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
