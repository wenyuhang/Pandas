package com.example.pandas.personal;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

import static com.example.pandas.R.id.personal_set_fankui_layout;

public class SetActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.pe_set_push_view1)
    ImageView peSetPushView1;
    @Bind(R.id.pe_set_push_view2)
    ImageView peSetPushView2;
    @Bind(R.id.pe_set_play_view1)
    ImageView peSetPlayView1;
    @Bind(R.id.pe_set_play_view2)
    ImageView peSetPlayView2;
    @Bind(R.id.set_cache_size_tv)
    TextView setCacheSizeTv;
    @Bind(R.id.personal_set_delete_cache_layout)
    RelativeLayout personalSetDeleteCacheLayout;
    @Bind(personal_set_fankui_layout)
    RelativeLayout personalSetFankuiLayout;
    @Bind(R.id.personal_set_udpate_layout)
    RelativeLayout personalSetUdpateLayout;
    @Bind(R.id.personal_set_ping_layout)
    RelativeLayout personalSetPingLayout;
    @Bind(R.id.personal_set_about_layout)
    RelativeLayout personalSetAboutLayout;
    @Bind(R.id.activity_set)
    LinearLayout activitySet;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initview() {

    }

    @OnClick({R.id.fanhui, R.id.pe_set_push_view1, R.id.pe_set_push_view2, R.id.pe_set_play_view1, R.id.pe_set_play_view2, R.id.personal_set_delete_cache_layout, personal_set_fankui_layout, R.id.personal_set_udpate_layout, R.id.personal_set_ping_layout, R.id.personal_set_about_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.pe_set_push_view1:
                peSetPushView1.setVisibility(View.GONE);
                peSetPushView2.setVisibility(View.VISIBLE);
                break;
            case R.id.pe_set_push_view2:
                peSetPushView2.setVisibility(View.GONE);
                peSetPushView1.setVisibility(View.VISIBLE);
                break;
            case R.id.pe_set_play_view1:
                peSetPlayView1.setVisibility(View.GONE);
                peSetPlayView2.setVisibility(View.VISIBLE);
                break;
            case R.id.pe_set_play_view2:
                peSetPlayView2.setVisibility(View.GONE);
                peSetPlayView1.setVisibility(View.VISIBLE);
                break;
            case R.id.personal_set_delete_cache_layout:

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog);
                dialog.show();
                dialog.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.findViewById(R.id.queding).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SetActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                break;
            case personal_set_fankui_layout:

                startActivity(new Intent(SetActivity.this,UserFeedbackActivity.class));

                break;
            case R.id.personal_set_udpate_layout:
                Toast.makeText(SetActivity.this, "更新什么啊，是不是流量多了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personal_set_ping_layout:
                break;
            case R.id.personal_set_about_layout:
                    startActivity(new Intent(SetActivity.this,AboutActivity.class));
                break;
        }
    }

//    @OnClick({R.id.fanhui, pe_set_push_view1, R.id.pe_set_push_view2, R.id.pe_set_play_view1, R.id.pe_set_play_view2, R.id.set_cache_size_tv, R.id.personal_set_delete_cache_layout, R.id.personal_set_fankui_layout, R.id.personal_set_udpate_layout, R.id.personal_set_ping_layout, R.id.personal_set_about_layout})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.fanhui:
//                finish();
//                break;
//            case pe_set_push_view1:
//                Toast.makeText(SetActivity.this, "66", Toast.LENGTH_SHORT).show();
//                peSetPushView1.setVisibility(View.VISIBLE);
//                peSetPushView2.setVisibility(View.GONE);
//                break;
//            case R.id.pe_set_push_view2:
//                peSetPushView1.setVisibility(View.GONE);
//                peSetPushView2.setVisibility(View.VISIBLE);
//                break;
//            case R.id.pe_set_play_view1:
//                peSetPushView1.setVisibility(View.VISIBLE);
//                peSetPushView2.setVisibility(View.GONE);
//                break;
//            case R.id.pe_set_play_view2:
//                peSetPushView1.setVisibility(View.GONE);
//                peSetPushView2.setVisibility(View.VISIBLE);
//                break;
//            case R.id.set_cache_size_tv:
//                break;
//            case R.id.personal_set_delete_cache_layout:
//                break;
//            case R.id.personal_set_fankui_layout:
//                break;
//            case R.id.personal_set_udpate_layout:
//                break;
//            case R.id.personal_set_ping_layout:
//                break;
//            case R.id.personal_set_about_layout:
//                break;
//        }
//    }
}
