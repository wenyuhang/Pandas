package com.example.pandas.personal;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by li on 2017/7/13.
 */

public class UpdateActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.edit_phonenumber)
    EditText editPhonenumber;
    @Bind(R.id.hint_phonenumber)
    TextView hintPhonenumber;
    @Bind(R.id.edit_checkimage)
    EditText editCheckimage;
    @Bind(R.id.personal_reg_imgcheck)
    TextView personalRegImgcheck;
    @Bind(R.id.hint_checkimage)
    TextView hintCheckimage;
    @Bind(R.id.edit_checkphone)
    EditText editCheckphone;
    @Bind(R.id.personal_reg_phonecheck)
    Button personalRegPhonecheck;
    @Bind(R.id.hint_checkphone)
    TextView hintCheckphone;
    @Bind(R.id.edit_newpssword)
    EditText editNewpssword;
    @Bind(R.id.hint_newpssword)
    TextView hintNewpssword;
    @Bind(R.id.tvfoundpswd)
    TextView tvfoundpswd;
    @Bind(R.id.activity_forget)
    LinearLayout activityForget;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initview() {

    }


    @OnClick({R.id.fanhui, R.id.personal_reg_imgcheck, R.id.personal_reg_phonecheck, R.id.tvfoundpswd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.personal_reg_imgcheck:
                break;
            case R.id.personal_reg_phonecheck:
                break;
            case R.id.tvfoundpswd:
                break;
        }
    }


}
