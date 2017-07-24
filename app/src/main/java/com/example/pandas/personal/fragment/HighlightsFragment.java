package com.example.pandas.personal.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.ACacheUtils;
import com.example.pandas.config.CollectionDate;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by li on 2017/7/17.
 */

public class HighlightsFragment extends BaseFragment {


    @Bind(R.id.history_listview)
    ListView historyListview;

    @Override
    protected int getLayoutId() {
        return R.layout.highlightsfragment;
    }

    @Override
    protected void init(View view) {
        ArrayList<CollectionDate> storage = ACacheUtils.getUtils().Storage();
        if(storage!=null){
            String moviename = storage.get(0).getMoviename();
            Log.d("HighlightsFragment", moviename);
        }

    }

    @Override
    protected void loadData() {

    }

}
