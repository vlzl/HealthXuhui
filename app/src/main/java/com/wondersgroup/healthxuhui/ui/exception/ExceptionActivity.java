package com.wondersgroup.healthxuhui.ui.exception;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.healthxuhui.R;

public class ExceptionActivity extends AppCompatActivity {
    public static final String EXCEPTION = "exception";

    private TextView tvException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvException = (TextView) findViewById(R.id.tv_exception);
        String exception = getIntent().getStringExtra(EXCEPTION);
        if (!TextUtils.isEmpty(exception)){
            tvException.setText(exception);
        }
    }

}
