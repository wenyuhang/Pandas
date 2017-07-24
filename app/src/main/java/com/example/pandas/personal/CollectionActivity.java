package com.example.pandas.personal;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.personal.fragment.HighlightsFragment;
import com.example.pandas.personal.fragment.LiveBroadcastFragment;

import butterknife.Bind;
import butterknife.OnClick;

import static com.example.pandas.R.id.collect_all;

public class CollectionActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.phone)
    RadioButton phone;
    @Bind(R.id.mailbox)
    RadioButton mailbox;
    @Bind(R.id.fram)
    FrameLayout fram;
    @Bind(R.id.collection_edit)
    Button collectionEdit;
    @Bind(collect_all)
    Button collectAll;
    @Bind(R.id.collect_delect)
    Button collectDelect;
    @Bind(R.id.collect_linearlayout)
    LinearLayout collectLinearlayout;
    @Bind(R.id.activity_collection)
    RelativeLayout activityCollection;
    private FragmentManager fragmentManager;
    private LiveBroadcastFragment liveBroadcastFragment;
    private Intent intent;

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

    @OnClick({R.id.fanhui, R.id.phone, R.id.mailbox,R.id.collection_edit, collect_all, R.id.collect_delect})
    public void onViewClicked(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.phone:
                transaction.replace(R.id.fram, liveBroadcastFragment);
                transaction.commit();
                break;
            case R.id.mailbox:
                HighlightsFragment highlightsFragment = new HighlightsFragment();
                transaction.replace(R.id.fram, highlightsFragment);
                transaction.commit();
                break;

            case R.id.collection_edit:
                if (collectionEdit.getText().toString().equals("编辑")) {
                    collectLinearlayout.setVisibility(View.VISIBLE);
                    collectionEdit.setText("取消");

                } else {
                    collectLinearlayout.setVisibility(View.GONE);
                    collectionEdit.setText("编辑");
                }
                break;
            case R.id.collect_all:
                if (collectAll.getText().toString().equals("全选")) {
                    collectAll.setText("取消全选");
                    intent = new Intent("checks");
                    intent.putExtra("yes","全选");
                    sendBroadcast(intent);
                } else {
                    collectAll.setText("全选");
                    intent.putExtra("yes","取消全选");
                    sendBroadcast(intent);
                }
                break;
            case R.id.collect_delect:
                intent = new Intent("checks");
                intent.putExtra("yes","删除");
                sendBroadcast(intent);
                break;
        }
    }

}
