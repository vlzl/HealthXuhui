package com.wondersgroup.healthxuhui.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wondersgroup.healthxuhui.R;
import com.wondersgroup.healthxuhui.ui.main.MainActivity;
import com.wondersgroup.healthxuhui.util.AppUrlUtil;

public class WelcomeActivity extends AppCompatActivity {

    private static final int SHOW_MAIN = 0x123;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_MAIN){
                showMain();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ActionBar actionBar  = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        View view = findViewById(R.id.rl_content);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessageDelayed(SHOW_MAIN, 2000);
        AppUrlUtil.getHomeUrl(this);
    }

    public void showMain() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
