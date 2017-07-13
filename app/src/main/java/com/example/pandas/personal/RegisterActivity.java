package com.example.pandas.personal;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.personal.fragment.MailboxFragment;
import com.example.pandas.personal.fragment.PhoneFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.phone)
    RadioButton phone;
    @Bind(R.id.mailbox)
    RadioButton mailbox;
    @Bind(R.id.fram)
    FrameLayout fram;
    private PhoneFragment phoneFragment;
    private MailboxFragment mailboxFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initview() {
        phoneFragment = new PhoneFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fram, phoneFragment);
        transaction.commit();
    }

    @OnClick({R.id.fanhui, R.id.phone, R.id.mailbox})
    public void onViewClicked(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.phone:
                transaction.replace(R.id.fram,phoneFragment);
                transaction.commit();
                break;
            case R.id.mailbox:
                mailboxFragment = new MailboxFragment();
                transaction.replace(R.id.fram,mailboxFragment);
                transaction.commit();
                break;
        }
    }
}
