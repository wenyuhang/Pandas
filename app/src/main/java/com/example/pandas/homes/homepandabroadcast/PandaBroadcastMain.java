package com.example.pandas.homes.homepandabroadcast;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.ACache;
import com.example.pandas.config.GsonUtiles;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;
import com.example.pandas.personal.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/7/10.
 */

public class PandaBroadcastMain extends BaseFragment implements PandaBroadcastContract.View {
    PandaBroadcastContract.Presenter presenter;
    @Bind(R.id.pandaBroadcast_login)
    ImageView pandaBroadcastLogin;
    @Bind(R.id.pdb_PullToRefreshRecyclerView)
    PullToRefreshRecyclerView pdbPullToRefreshRecyclerView;
    private int x = 0;
    int a = 4;
    private String path = "iphoneInterface/general/getArticleAndVideoListInfo.json";
    private String primary_id2 = "PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE145147362893414";
    private String primary_id = "PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE1451473628934144";
    private String serviceId = "panda";
    private TextView textView;
    private List<PdBBean.ListBean> list;
    private ArrayList<PdBBean.ListBean> listBeen;
    private PandaBroadcastAdapter pandaBroadcastAdapter;
    private ImageView pdb_up_image;
    private ACache aCache;


    @Override
    protected int getLayoutId() {
        return R.layout.main_pandabroadcast;
    }

    @Override
    protected void init(View view) {
        listBeen = new ArrayList<>();
        View inflate = View.inflate(getContext(), R.layout.pandabroadcast_up_item, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        inflate.setLayoutParams(params);
        textView = (TextView) inflate.findViewById(R.id.pdb_up_image_title);
        pdb_up_image = (ImageView) inflate.findViewById(R.id.pdb_up_image);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "image", Toast.LENGTH_SHORT).show();
            }
        });
        pdbPullToRefreshRecyclerView.addHeaderView(inflate);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        pdbPullToRefreshRecyclerView.setLayoutManager(manager);
        pandaBroadcastAdapter = new PandaBroadcastAdapter(getContext(), listBeen);
        pdbPullToRefreshRecyclerView.setAdapter(pandaBroadcastAdapter);
        presenter.lodeMore(path, primary_id, serviceId);
        pdbPullToRefreshRecyclerView.setPullRefreshEnabled(true);
        pdbPullToRefreshRecyclerView.setLoadingMoreEnabled(true);
        pdbPullToRefreshRecyclerView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pdbPullToRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.lodeMore(path, primary_id, serviceId);
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
                        pdbPullToRefreshRecyclerView.setRefreshComplete();
                        StringBuffer sb = new StringBuffer(primary_id2);
                        a++;
                        String s = sb.append(a).toString();
                        presenter.lodeMore(path, s, serviceId);
                        pandaBroadcastAdapter.notifyDataSetChanged();

                    }
                }, 2000);

            }
        });
    }

    //
    @Override
    protected void loadData() {
        ACache aCache1 = ACache.get(getActivity());
//        ACache aCache2 = ACache.get(getActivity());
//        String titleBeanJson = aCache2.getAsString("name1");
        String pdBBeanJson = aCache1.getAsString("name");
        if(pdBBeanJson!=null){
            Log.d("MainActivity1", pdBBeanJson);
//            Gson gson = GsonUtiles.getInstance().getGson();
            Gson gson = new Gson();
            Type type = new TypeToken<List<PdBBean.ListBean>>(){}.getType();
            List<PdBBean.ListBean> pdBBean = gson.fromJson(pdBBeanJson, type);
            listBeen.addAll(pdBBean);
            textView.setText(listBeen.get(0).getTitle());
            Glide.with(getContext()).load(listBeen.get(0).getPicurl()).into(pdb_up_image);

//            Type type1 = new TypeToken<TitleBean.DataBean.BigImgBean>() {
//            }.getType();
            //List<TitleBean.DataBean.BigImgBean> o = gson.fromJson(titleBeanJson, type1);
            //textView.setText(o.get(0).getTitle());
            //Glide.with(getContext()).load(o.get(0).getImage()).into(pdb_up_image);
            pandaBroadcastAdapter.notifyDataSetChanged();
        }else {
            presenter.strat();
        }




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
        textView.setText(listBeen.get(5).getTitle());
        Glide.with(getContext()).load(listBeen.get(5).getPicurl()).into(pdb_up_image);
        pandaBroadcastAdapter.notifyDataSetChanged();
        aCache = ACache.get(getActivity());
        Gson gson = GsonUtiles.getInstance().getGson();
        String s1 = gson.toJson(list);
        aCache.put("name1", s1);


//        ACache aCache1 = ACache.get(getActivity());
//        JSONObject sss1 = aCache1.getAsJSONObject("sss");
//        PdBBean sss = (PdBBean) aCache1.getAsObject("sss");
//        Log.d("PandaBroadcastMain1", "s:" + sss.toString()+sss1.toString());

    }

    @Override
    public void setResult1(TitleBean titleBean) {
        List<TitleBean.DataBean.BigImgBean> bigImg = titleBean.getData().getBigImg();
        textView.setText(bigImg.get(5).getTitle());
        Glide.with(getContext()).load(bigImg.get(5).getImage()).into(pdb_up_image);
//        ACache aCachetitle = ACache.get(getActivity());
//        Gson gson = GsonUtiles.getInstance().getGson();
//        String ssss = gson.toJson(bigImg);
//        Log.e("ssssss",ssss);
//        aCachetitle.put("name", ssss);
    }

    @Override
    public void showMessage(String msg) {

    }


    @Override
    public void setPresenter(PandaBroadcastContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.pandaBroadcast_login)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
