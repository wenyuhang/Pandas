package com.example.pandas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Timer timer = new Timer();timer.schedule(new TimerTask() {
            @Override
            public void run() {
              startActivity(new Intent(AnimationActivity.this,HomeActivity.class));
                finish();
            }
        },2000);
    }
}
