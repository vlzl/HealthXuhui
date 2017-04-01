package com.wondersgroup.healthxuhui.ui.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.healthxuhui.R;
import com.wondersgroup.healthxuhui.data.entity.SecondItemEntity;
import com.wondersgroup.healthxuhui.ui.base.BaseActivity;
import com.wondersgroup.healthxuhui.ui.browser.BrowserActivity;
import com.wondersgroup.healthxuhui.util.FrescoUtil;
import com.wondersgroup.healthxuhui.widget.DividerItemDecoration;
import com.wondersgroup.healthxuhui.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends BaseActivity implements SecondContract.View{
    /**
     * 标识该activity用于显示何种内容的key
     */
    public static final String SENCOND_TYPE = "sencond_type";

    private TextView tvTitle;
    private RecyclerView recyclerView;
    private SecondItemEnum itemType;

    private SecondContract.Presenter mPresenter;

    private List<SecondItemEntity> datas;
    private SecondRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrescoUtil.init(this);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle = (TextView) findViewById(R.id.tv_title);

        Intent intent = getIntent();
        itemType = (SecondItemEnum) intent.getSerializableExtra(SENCOND_TYPE);

        if (itemType == SecondItemEnum.QYWS){
            tvTitle.setText(getString(R.string.qyws));
        }else if (itemType == SecondItemEnum.YLFW){
            tvTitle.setText(getString(R.string.ylfw));
        }else if (itemType == SecondItemEnum.GGWS){
            tvTitle.setText(getString(R.string.ggws));
        }

        new SecondPresenter(this);

        initRecyclerView();

        mPresenter.loadItemList(itemType);
    }

    private void initRecyclerView() {
        datas = new ArrayList<SecondItemEntity>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerAdapter = new SecondRecyclerAdapter(this, datas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                SecondItemEntity entity = datas.get(position);
                mPresenter.openBrowser(entity.getUrl());
            }

            @Override
            public void onLongClick(int position) {

            }
        });
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(SecondActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void setPresenter(SecondContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showItemList(List<SecondItemEntity> list) {
        datas.clear();
        datas.addAll(list);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBrowser(String url) {
        Intent intent = new Intent(SecondActivity.this, BrowserActivity.class);
        intent.putExtra(BrowserActivity.URL, url);
        startActivity(intent);
    }
}
