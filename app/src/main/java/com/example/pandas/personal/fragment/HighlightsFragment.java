package com.example.pandas.personal.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.database.SqlUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by li on 2017/7/17.
 */

public class HighlightsFragment extends BaseFragment {

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

    @Override
    protected int getLayoutId() {
        return R.layout.highlightsfragment;
    }

    @Override
    protected void init(View view) {


        SqlUtils.getInstance().add(1,"http://img1.imgtn.bdimg.com/it/u=1494050267,2635264334&fm=26&gp=0.jpg", "00.30", "title", "2017/10/11", "");

//        list = new ArrayList<>();
////        list.add(new HistoryBean("http://img1.imgtn.bdimg.com/it/u=1494050267,2635264334&fm=26&gp=0.jpg", "00.30", "title", "2017/10/11", ""));
//        ArrayList<SqliteBean> query = SqlUtils.getInstance().query();
//        for (int x=0;x<query.size();x++){
//            SqliteBean sqliteBean = query.get(x);
//            list.add(sqliteBean);
//        }
//        if (list.size() == 0) {
//            personalHyNetLayout.setVisibility(View.VISIBLE);
//        } else {
//            personalHistoryDataLayout.setVisibility(View.VISIBLE);
//            adapter = new ListAdapter(this, list,lists);
//            personalHistoryListview.setAdapter(adapter);
//        }


    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.fanhui, R.id.quxiao, R.id.bianji, R.id.personal_hy_all, R.id.personal_hy_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                break;
            case R.id.quxiao:
                break;
            case R.id.bianji:
                break;
            case R.id.personal_hy_all:
                break;
            case R.id.personal_hy_delete:
                break;
        }
    }
}
