package com.wondersgroup.healthxuhui.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wondersgroup.healthxuhui.util.LogUtil;

/**
 * Created by yangjinxi on 2016/6/14.
 */
public class BaseActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG + "------" + "onCreate");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(TAG + "------" + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG + "------" + "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG + "------" + "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG + "------" + "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(TAG + "------" + "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i(TAG + "------" + "onRestart");
    }
}
