package com.example.pandas.personal;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.config.database.SqlUtils;
import com.example.pandas.config.database.SqliteBean;
import com.example.pandas.personal.adapters.ListAdapter;
import com.example.pandas.personal.beans.HistoryBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity implements ListAdapter.OnClick{

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.quxiao)
    TextView quxiao;
    @Bind(R.id.bianji)
    TextView bianji;
    @Bind(R.id.personal_history_listview)
    ListView personalHistoryListview;
    @Bind(R.id.personal_hy_all)
    TextView personalHyAll;
    @Bind(R.id.personal_hy_delete)
    TextView personalHyDelete;
    @Bind(R.id.personal_hy_net_layout)
    ImageView personalHyNetLayout;
    @Bind(R.id.personal_history_data_layout)
    RelativeLayout personalHistoryDataLayout;
    @Bind(R.id.personal_history_item_detail_bottom)
    LinearLayout personalHistoryItemDetailBottom;
    private ArrayList<SqliteBean> list;
    private ArrayList<HistoryBean> history;
    private ListAdapter adapter;
    private Integer integer;
    private ArrayList<Integer> positionBean = new ArrayList<>();
    private boolean sureTrue = false;


    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initview() {
        history = new ArrayList<>();
        list = new ArrayList<>();
//        list.add(new HistoryBean("http://img1.imgtn.bdimg.com/it/u=1494050267,2635264334&fm=26&gp=0.jpg", "00.30", "title", "2017/10/11", ""));
        ArrayList<SqliteBean> query = SqlUtils.getInstance().query();
        for (int x=0;x<query.size();x++){
            SqliteBean sqliteBean = query.get(x);
            HistoryBean bean = new HistoryBean();
            bean.setData(sqliteBean.getMoviedate());
            bean.setImage(sqliteBean.getImageurl());
            bean.setTimer(sqliteBean.getMovietime());
            bean.setTitle(sqliteBean.getMoviename());
            bean.setUrl(sqliteBean.getMovieurl());
            bean.setFlg(false);

            history.add(bean);
            list.add(sqliteBean);
        }
        if (list.size() == 0) {
            personalHyNetLayout.setVisibility(View.VISIBLE);
        } else {
            personalHistoryDataLayout.setVisibility(View.VISIBLE);
            adapter = new ListAdapter(this, history ,personalHyDelete);
            adapter.setOnClick(this);
            positionBean.addAll(adapter.getList());
            personalHistoryListview.setAdapter(adapter);
        }
    }





    @OnClick({R.id.quxiao, R.id.bianji, R.id.personal_hy_all, R.id.personal_hy_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quxiao:
                bianji.setVisibility(View.VISIBLE);
                quxiao.setVisibility(View.GONE);
                personalHistoryItemDetailBottom.setVisibility(View.GONE);
                adapter.setFlg(false);
                adapter.notifyDataSetChanged();
                break;
            case R.id.bianji:
                this.bianji.setVisibility(View.GONE);
                adapter.setFlg(true);
                quxiao.setVisibility(View.VISIBLE);
                personalHistoryItemDetailBottom.setVisibility(View.VISIBLE);

                adapter.notifyDataSetChanged();
                break;
            case R.id.personal_hy_all:
                positionBean.clear();
                if(personalHyAll.equals("全选")) {
                    for (int i=0;i<history.size();i++){
                        history.get(i).setFlg(true);
                        positionBean.add(i);
                    }
                    personalHyAll.setText("取消全选");
                }else {
                    positionBean.clear();
                    for (int i=0;i<history.size();i++){
                        history.get(i).setFlg(false);
                    }
                    personalHyAll.setText("全选");
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.personal_hy_delete:
                Log.e("TAG",list.toString()+"have");
                for (int x=0;x<positionBean.size();x++){
                    this.list.remove(x);
                }
                adapter.notifyDataSetChanged();

                break;
        }
    }


    @Override
    public void choseClick(int pos) {
//        i = pos;
    }
}
