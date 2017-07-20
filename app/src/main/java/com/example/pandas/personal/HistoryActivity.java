package com.example.pandas.personal;

import android.content.Intent;
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
import com.example.pandas.personal.adapters.ListAdapter;
import com.example.pandas.personal.beans.HistoryBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity {

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
    private ArrayList<HistoryBean> list;
    private ArrayList<Integer> lists = new ArrayList<>();
    private ListAdapter adapter;
    private Integer integer;
    private int i;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initview() {
        list = new ArrayList<>();
        list.add(new HistoryBean("http://img1.imgtn.bdimg.com/it/u=1494050267,2635264334&fm=26&gp=0.jpg", "00.30", "title", "2017/10/11", ""));
        if (list.size() == 0) {
            personalHyNetLayout.setVisibility(View.VISIBLE);
        } else {
            personalHistoryDataLayout.setVisibility(View.VISIBLE);
            adapter = new ListAdapter(this, list,lists);
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
                Intent intent1 = new Intent("quxiao");
                sendBroadcast(intent1);
                break;
            case R.id.bianji:
                bianji.setVisibility(View.GONE);
                quxiao.setVisibility(View.VISIBLE);
                personalHistoryItemDetailBottom.setVisibility(View.VISIBLE);
                Intent intent = new Intent("bianji");
                sendBroadcast(intent);
                break;
            case R.id.personal_hy_all:
                break;
            case R.id.personal_hy_delete:
                for (int x=0;x<lists.size();x++){
                    integer = lists.get(x);
                    i = integer.intValue();
                    Log.e("TAG",i+"");
                }
                list.remove(i);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
