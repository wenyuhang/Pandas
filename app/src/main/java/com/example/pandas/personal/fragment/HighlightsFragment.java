package com.example.pandas.personal.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.ACacheUtils;
import com.example.pandas.config.CollectionDate;
import com.example.pandas.personal.adapters.CollectionAdapter;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by li on 2017/7/17.
 */

public class HighlightsFragment extends BaseFragment {


    @Bind(R.id.history_recyclerview)
    RecyclerView history_recyclerview;

    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("yes").equals("全选")){
                Toast.makeText(context, "全选", Toast.LENGTH_SHORT).show();

            }else if(intent.getStringExtra("yes").equals("取消全选")){
                Toast.makeText(context, "取消全选", Toast.LENGTH_SHORT).show();
            }else if(intent.getStringExtra("yes").equals("删除")){
                Toast.makeText(context, "删除", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private CollectionAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.highlightsfragment;
    }

    @Override
    protected void init(View view) {
        IntentFilter filter=new IntentFilter("checks");
        getActivity().registerReceiver(receiver,filter);
        ArrayList<CollectionDate> storage = ACacheUtils.getUtils().Storage();
        if(storage!=null){
            history_recyclerview.setHasFixedSize(true);
            history_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            adapter = new CollectionAdapter(getActivity(),storage);
            history_recyclerview.setAdapter(adapter);
        }

    }

    @Override
    protected void loadData() {

    }

}
