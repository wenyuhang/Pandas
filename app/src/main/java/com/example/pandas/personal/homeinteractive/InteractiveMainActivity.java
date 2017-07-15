package com.example.pandas.personal.homeinteractive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.pandas.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InteractiveMainActivity extends AppCompatActivity {

    @Bind(R.id.interactive_xrecyclerview)
    XRecyclerView interactiveXrecyclerview;
    @Bind(R.id.interactive_finish)
    ImageView interactiveFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_main);
        ButterKnife.bind(this);
    }
}
