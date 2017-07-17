package com.example.pandas.personal.fragment;

import android.view.View;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by li on 2017/7/15.
 */

public class CommonProblemFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.common_problem_fragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

