package com.example.pandas.personal;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.personal.fragment.HighlightsFragment;
import com.example.pandas.personal.fragment.LiveBroadcastFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.phone)
    RadioButton phone;
    @Bind(R.id.mailbox)
    RadioButton mailbox;
    @Bind(R.id.fram)
    FrameLayout fram;
    private FragmentManager fragmentManager;
    private LiveBroadcastFragment liveBroadcastFragment;
    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initview() {
        liveBroadcastFragment = new LiveBroadcastFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fram, liveBroadcastFragment);
        transaction.commit();
    }

    @OnClick({R.id.fanhui,R.id.phone, R.id.mailbox})
    public void onViewClicked(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.fanhui:

                break;
            case R.id.phone:
                transaction.replace(R.id.fram,liveBroadcastFragment);
                transaction.commit();
                break;
            case R.id.mailbox:
                HighlightsFragment highlightsFragment = new HighlightsFragment();
                transaction.replace(R.id.fram,highlightsFragment);
                transaction.commit();
                break;
        }
    }
}
