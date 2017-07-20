package com.example.pandas.personal.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by li on 2017/7/17.
 */

public class HighlightsFragment extends BaseFragment {
    @Bind(R.id.personal_history_listview)
    ListView personalHistoryListview;
    @Bind(R.id.personal_hy_all)
    TextView personalHyAll;
    @Bind(R.id.personal_hy_delete)
    TextView personalHyDelete;

    @Override
    protected int getLayoutId() {
        return R.layout.highlightsfragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.personal_hy_all, R.id.personal_hy_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_hy_all:
                break;
            case R.id.personal_hy_delete:
                break;
        }
    }
}
