package com.example.pandas.personal.homeinteractive;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.model.datebean.homebean.InteractiveInfoBean;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;

public class InteractiveMainActivity extends BaseActivity implements InteractiveContract.View {

    @Bind(R.id.interactive_xrecyclerview)
    XRecyclerView interactiveXrecyclerview;
    @Bind(R.id.interactive_finish)
    ImageView interactiveFinish;

    private InteractiveContract.Presenter presenter;
    private ArrayList<InteractiveInfoBean.InteractiveBean> interactiveList=new ArrayList<>();
    private InteractiveAdapter interactiveAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_interactive_main;
    }

    @Override
    public void initview() {
        new InteractivePresenter(this);
        presenter.strat();
        interactiveFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(final InteractiveInfoBean interactiveInfoBean) {
        interactiveList.addAll(interactiveInfoBean.getInteractive());
        interactiveXrecyclerview.setHasFixedSize(true);
        interactiveXrecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        interactiveXrecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        interactiveXrecyclerview.setArrowImageView(R.mipmap.xlistview_arrow);
        interactiveXrecyclerview.setLoadingMoreEnabled(false);
        interactiveXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                interactiveList.clear();
                interactiveList.addAll(interactiveInfoBean.getInteractive());
                interactiveXrecyclerview.refreshComplete();
                interactiveAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {

            }
        });
        interactiveAdapter = new InteractiveAdapter(this,interactiveList);
        interactiveXrecyclerview.setAdapter(interactiveAdapter);
        interactiveAdapter.setClickListener(new InteractiveAdapter.ClickListener() {
            @Override
            public void onClickListener(int pos) {
                Intent intent=new Intent(InteractiveMainActivity.this,InteractiveInfoActivity.class);
                intent.putParcelableArrayListExtra("data",interactiveList);
                intent.putExtra("pos",pos);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(InteractiveContract.Presenter presenter) {
        this.presenter=presenter;
    }
}
