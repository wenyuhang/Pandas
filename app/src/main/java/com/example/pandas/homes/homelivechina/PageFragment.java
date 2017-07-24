package com.example.pandas.homes.homelivechina;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.ACache;
import com.example.pandas.model.datebean.livechina.LiveChinaBean;
import com.example.pandas.model.datebean.livechina.SceneryBean;
import com.example.pandas.wxapi.App;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private ACache aCache;

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
        pagefragmentXrlview.setLoadingMoreEnabled(false);
        pagefragmentXrlview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Timer().schedule(timerTask,1500);
            }
            @Override
            public void onLoadMore() {

            }
        });

    }

    @Override
    protected void loadData() {
//        presenter.onload("http://www.ipanda.com/kehuduan/liebiao/zhangjiajie/index.shtml");
        aCache = ACache.get(App.context);
        String tab = aCache.getAsString("tab");
        if(tab!=null){
            LiveChinaBean liveChinaBean = new Gson().fromJson(tab, LiveChinaBean.class);
            setDate(liveChinaBean);
        }else {
            presenter.onload(s);
        }

    }


    @Override
    public void setResult(LiveChinaBean netBean) {
        setDate(netBean);
        Gson gson = new Gson();
        String s = gson.toJson(netBean);
        aCache.put("tab",s);
    }

    private void setDate(LiveChinaBean netBean) {
        List<LiveChinaBean.LiveBean> lives = netBean.getLive();
        liveBeen.addAll(lives);
        xrecyclerviewAdapter.notifyDataSetChanged();
    }


    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LiveChinaContract.Presenter presenter) {
        this.presenter = presenter;
    }
    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pagefragmentXrlview.refreshComplete();
                }
            });
        }
    };
    @Override
    public void setResult2(SceneryBean netBean) {

    }
}
