package com.example.pandas.personal;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initview() {
        ArrayList<String> list = new ArrayList<>();
        list.add("sss");
        if (list.size() == 0) {
            personalHyNetLayout.setVisibility(View.VISIBLE);
        }else {
            personalHistoryDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.quxiao, R.id.bianji, R.id.personal_hy_all, R.id.personal_hy_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quxiao:
                bianji.setVisibility(View.VISIBLE);
                quxiao.setVisibility(View.GONE);
                personalHyAll.setVisibility(View.GONE);
                personalHyDelete.setVisibility(View.GONE);
                break;
            case R.id.bianji:
                bianji.setVisibility(View.GONE);
                quxiao.setVisibility(View.VISIBLE);
                personalHyAll.setVisibility(View.VISIBLE);
                personalHyDelete.setVisibility(View.VISIBLE);
                break;
            case R.id.personal_hy_all:
                break;
            case R.id.personal_hy_delete:
                break;
        }
    }
}
