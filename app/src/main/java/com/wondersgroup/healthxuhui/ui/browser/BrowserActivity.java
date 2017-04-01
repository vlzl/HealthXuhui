package com.wondersgroup.healthxuhui.ui.browser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wondersgroup.healthxuhui.R;
import com.wondersgroup.healthxuhui.ui.base.BaseActivity;
import com.wondersgroup.healthxuhui.util.LogUtil;

public class BrowserActivity extends BaseActivity {

    public static final String URL = "url";
    public static final String TITLE = "title";

    private TextView tvTitle;
    private String url;
    private ProgressBar progressBar;
    private String title;
    private BrowserFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(fragment != null && fragment.canAndGOback())){
                    finish();
                }
            }
        });

        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            //界面向上，逐步隐藏appbar
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                if (Math.abs(verticalOffset) >= totalScrollRange - 15) {
                    appBarLayout.setAlpha(0);
                    return;
                }
                float alpha = verticalOffset * 0.5f / totalScrollRange + 1;
                appBarLayout.setAlpha(alpha);
            }
        });


        tvTitle = (TextView) findViewById(R.id.tv_title);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Intent intent = getIntent();
        url = intent.getStringExtra(URL);
        title = intent.getStringExtra(TITLE);
        if (TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        LogUtil.i(url);
        showWebPage(url);
    }

    public void showWebPage(String url){
        fragment = BrowserFragment.newInstance(url);
        fragment.setBrowserCallback(new BrowserCallback() {
            @Override
            public void inProgress(int progress) {
                super.inProgress(progress);
                if (progressBar.getVisibility() != View.VISIBLE){
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setProgress(progress);
                if (progress == 100){
                    progressBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }, 500);
                }
            }

            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onReceivedTitle(String title) {
                super.onReceivedTitle(title);
                tvTitle.setText(title);
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contentFrame, fragment);
        ft.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && fragment != null && fragment.canAndGOback()){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
