package com.example.pandas.personal;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class HistoryActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.bianji)
    TextView bianji;
    @Bind(R.id.personal_hy_all)
    TextView personalHyAll;
    @Bind(R.id.personal_hy_delete)
    TextView personalHyDelete;
    @Bind(R.id.personal_history_item_detail_bottom)
    LinearLayout personalHistoryItemDetailBottom;
    @Bind(R.id.personal_history_view)
    RecyclerView personalHistoryView;
    @Bind(R.id.personal_history_data_layout)
    RelativeLayout personalHistoryDataLayout;
    @Bind(R.id.personal_hy_net_layout)
    ImageView personalHyNetLayout;
    @Bind(R.id.activity_history)
    LinearLayout activityHistory;

    private ArrayList<HistoryBean> list;
    private ListAdapter adapter;
    private int count=0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initview() {
        list=new ArrayList<>();
        ArrayList<SqliteBean> query = SqlUtils.getInstance().query();
        for(int i=0;i<query.size();i++){

            HistoryBean historyBean=new HistoryBean();
            historyBean.setTitle(query.get(i).getMoviename());
            historyBean.setImage(query.get(i).getImageurl());
            historyBean.setData(query.get(i).getMoviedate());
            historyBean.setTimer(query.get(i).getMovietime());
            historyBean.setUrl(query.get(i).getMovieurl());
            historyBean.setFlg(false);
            list.add(0,historyBean);
        }


        if (list.size() == 0) {
            personalHyNetLayout.setVisibility(View.VISIBLE);
            personalHistoryDataLayout.setVisibility(View.GONE);
            personalHistoryItemDetailBottom.setVisibility(View.GONE);
        } else {
            personalHyNetLayout.setVisibility(View.GONE);
            personalHistoryDataLayout.setVisibility(View.VISIBLE);
            personalHistoryItemDetailBottom.setVisibility(View.GONE);
            personalHistoryView.setHasFixedSize(true);
            personalHistoryView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            adapter = new ListAdapter(this, list);
            personalHistoryView.setAdapter(adapter);
            adapter.setOnClick(new ListAdapter.OnClick() {
                @Override
                public void choseClick(int pos) {
                    if(bianji.getText().equals("取消")){
                        if(list.get(pos).isFlgCheck()==false){
                            list.get(pos).setFlgCheck(true);
                            count++;
                            personalHyDelete.setText("删除"+count);
                        }else{
                            count--;
                            personalHyDelete.setText("删除"+count);
                            list.get(pos).setFlgCheck(false);
                        }
                        if(count==0){
                            personalHyDelete.setText("删除");
                        }
                    }
                    handler.sendEmptyMessage(300);
                }
            });
        }
    }

    @OnClick({R.id.fanhui, R.id.bianji, R.id.personal_hy_all, R.id.personal_hy_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.bianji:
                if(bianji.getText().equals("编辑")){
                    bianji.setText("取消");
                    personalHistoryDataLayout.setVisibility(View.VISIBLE);
                    personalHistoryItemDetailBottom.setVisibility(View.VISIBLE);
                    for(int i=0;i<list.size();i++){
                        list.get(i).setFlg(true);
                    }
                    handler.sendEmptyMessage(300);
                }else if(bianji.getText().equals("取消")){
                    bianji.setText("编辑");
                    personalHistoryDataLayout.setVisibility(View.VISIBLE);
                    personalHistoryItemDetailBottom.setVisibility(View.GONE);
                    for(int i=0;i<list.size();i++){
                        list.get(i).setFlg(false);
                    }
                    handler.sendEmptyMessage(300);
                }
                break;
            case R.id.personal_hy_all:
                if(personalHyAll.getText().equals("全选")){
                    personalHyAll.setText("取消全选");
                    if(bianji.getText().equals("取消")){
                        for(int i=0;i<list.size();i++){
                            list.get(i).setFlgCheck(true);
                        }
                        count=list.size();
                        personalHyDelete.setText("删除"+count);
                        handler.sendEmptyMessage(300);
                    }
                }else{
                    for(int i=0;i<list.size();i++){
                        list.get(i).setFlgCheck(false);
                    }
                    personalHyDelete.setText("删除");
                    personalHyAll.setText("全选");
                    handler.sendEmptyMessage(300);
                }
                break;
            case R.id.personal_hy_delete:
                if(bianji.getText().equals("取消")){
                    for(int i=list.size()-1;i>=0;i--){
                        if(list.get(i).isFlgCheck()){
                            SqlUtils.getInstance().delete(list.get(i).getTitle());
                            list.remove(i);
                        }
                    }
                    count=0;
                    personalHyDelete.setText("删除");
                    handler.sendEmptyMessage(300);

                    if(list.size()==0){
                        personalHistoryDataLayout.setVisibility(View.GONE);
                        personalHistoryItemDetailBottom.setVisibility(View.GONE);
                        bianji.setText("编辑");
                        bianji.setVisibility(View.GONE);
                        personalHyNetLayout.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }
}
