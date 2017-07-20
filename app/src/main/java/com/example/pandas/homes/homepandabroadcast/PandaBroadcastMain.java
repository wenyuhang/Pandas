package com.example.pandas.homes.homepandabroadcast;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by 联想 on 2017/7/10.
 */

public class PandaBroadcastMain extends BaseFragment implements PandaBroadcastContract.View {
    PandaBroadcastContract.Presenter presenter;
    @Bind(R.id.pdb_PullToRefreshRecyclerView)
    PullToRefreshRecyclerView pdbPullToRefreshRecyclerView;
    @Bind(R.id.pandabroadcast_probar)
    ProgressBar pandabroadcastProbar;
    @Bind(R.id.pandabroadcast_relalayout)
    RelativeLayout pandabroadcastRelalayout;
    private TextView textView;
    private List<PdBBean.ListBean> list;
    private ArrayList<PdBBean.ListBean> listBeen;
    private PandaBroadcastAdapter pandaBroadcastAdapter;
    private ImageView pdb_up_image;
    private List<TitleBean.DataBean.BigImgBean> bigImg;
    private View inflate;

    @Override
    protected int getLayoutId() {
        return R.layout.main_pandabroadcast;
    }

    @Override
    protected void init(View view) {
        pandabroadcastRelalayout.setVisibility(View.VISIBLE);
        listBeen = new ArrayList<>();
        inflate = View.inflate(getContext(), R.layout.pandabroadcast_up_item, null);
        textView = (TextView) inflate.findViewById(R.id.pdb_up_image_title);
        pdb_up_image = (ImageView) inflate.findViewById(R.id.pdb_up_image);
        pdbPullToRefreshRecyclerView.addHeaderView(inflate);
        pdbPullToRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pandaBroadcastAdapter = new PandaBroadcastAdapter(getContext(), listBeen);
        pdbPullToRefreshRecyclerView.setAdapter(pandaBroadcastAdapter);
        pdbPullToRefreshRecyclerView.setPullRefreshEnabled(true);
        pdbPullToRefreshRecyclerView.setLoadingMoreEnabled(true);
        pdbPullToRefreshRecyclerView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pdbPullToRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listBeen.clear();
                        listBeen.addAll(list);
                        pdbPullToRefreshRecyclerView.setRefreshComplete();
                        pandaBroadcastAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                pdbPullToRefreshRecyclerView.setLoadMoreComplete();
                pdbPullToRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pdbPullToRefreshRecyclerView.setLoadMoreComplete();
                        pandaBroadcastAdapter.notifyDataSetChanged();
                    }
                }, 2000);

            }
        });
    }

    //
    @Override
    protected void loadData() {
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

    }

    @Override
    public void setResult1(TitleBean titleBean) {

        if (titleBean != null) {
            pandabroadcastRelalayout.setVisibility(View.GONE);
        }

        bigImg = titleBean.getData().getBigImg();
        textView.setText(bigImg.get(0).getTitle());
        Glide.with(getContext()).load(bigImg.get(0).getImage()).into(pdb_up_image);
        pandaBroadcastAdapter.notifyDataSetChanged();
        final String url = bigImg.get(0).getUrl();
        Log.d("PandaBroadcastMain", url);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MWebView.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMessage(String msg) {

    }


    @Override
    public void setPresenter(PandaBroadcastContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
