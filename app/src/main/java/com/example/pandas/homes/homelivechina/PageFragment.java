package com.example.pandas.homes.homelivechina;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.model.datebean.livechina.LiveChinaBean;
import com.example.pandas.model.datebean.livechina.SceneryBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 联想 on 2017/7/13.
 */

public class PageFragment extends BaseFragment implements LiveChinaContract.View {
    @Bind(R.id.pagefragment_xrlview)
    XRecyclerView pagefragmentXrlview;
    String s;
    private LiveChinaContract.Presenter presenter;
    private XrecyclerviewAdapter xrecyclerviewAdapter;
    private ArrayList<LiveChinaBean.LiveBean> liveBeen;

    @Override
    protected int getLayoutId() {
        return R.layout.livechina_fragment;
    }

    @Override
    protected void init(View view) {
        liveBeen = new ArrayList<>();
        xrecyclerviewAdapter = new XrecyclerviewAdapter(getActivity(), liveBeen);
        pagefragmentXrlview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        RelativeLayout.LayoutParams params=
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        pagefragmentXrlview.setLayoutParams(params);
        pagefragmentXrlview.setAdapter(xrecyclerviewAdapter);
    }

    @Override
    protected void loadData() {
//        presenter.onload("http://www.ipanda.com/kehuduan/liebiao/zhangjiajie/index.shtml");
        presenter.onload(s);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(LiveChinaBean netBean) {
        List<LiveChinaBean.LiveBean> lives = netBean.getLive();
        liveBeen.addAll(lives);
        xrecyclerviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setResult2(SceneryBean netBean) {

    }


    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(LiveChinaContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
