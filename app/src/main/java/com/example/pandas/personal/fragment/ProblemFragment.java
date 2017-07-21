package com.example.pandas.personal.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by li on 2017/7/15.
 */

public class ProblemFragment extends BaseFragment {

    @Bind(R.id.myquestion_cb1)
    CheckBox myquestionCb1;
    @Bind(R.id.myquestion_cb2)
    CheckBox myquestionCb2;
    @Bind(R.id.myquestion_cb3)
    CheckBox myquestionCb3;
    @Bind(R.id.myquestion_cb4)
    CheckBox myquestionCb4;
    @Bind(R.id.myquestion_cb5)
    CheckBox myquestionCb5;
    @Bind(R.id.myquestion_cb6)
    CheckBox myquestionCb6;
    @Bind(R.id.myquestion_content1)
    EditText myquestionContent1;
    @Bind(R.id.myquestion_contact2)
    EditText myquestionContact2;
    @Bind(R.id.myquestion_submit_btn)
    TextView myquestionSubmitBtn;
    private boolean matches;

    @Override
    protected int getLayoutId() {
        return R.layout.problem_fragment;
    }

    @Override
    protected void init(View view) {


    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.myquestion_submit_btn)
    public void onViewClicked() {
         Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher("wangxu198709@gmail.com");
      //  Mather m = p.matcher("wangxu198709@gmail.com.cn");这种也是可以的！
        matches = m.matches();

        if (myquestionCb1.isChecked() == true || myquestionCb2.isChecked() == true || myquestionCb3.isChecked() == true || myquestionCb4.isChecked() == true || myquestionCb5.isChecked() == true || myquestionCb6.isChecked() == true) {

            if(myquestionContent1.getText().toString().trim().equals("")) {
                Toast.makeText(getActivity(), "联系方式不能为空", Toast.LENGTH_SHORT).show();

        }else {
                if (matches==true) {
                    Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }
            }
        }else {
                Toast.makeText(getActivity(), "请选择问题类型", Toast.LENGTH_SHORT).show();

    }
    }
}
