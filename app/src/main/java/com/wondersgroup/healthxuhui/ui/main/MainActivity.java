package com.wondersgroup.healthxuhui.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.healthxuhui.R;
import com.wondersgroup.healthxuhui.data.entity.VersonUpdateEntity;
import com.wondersgroup.healthxuhui.data.source.remote.RemoteDataSource;
import com.wondersgroup.healthxuhui.ui.base.BaseActivity;
import com.wondersgroup.healthxuhui.ui.browser.BrowserActivity;
import com.wondersgroup.healthxuhui.ui.map.MapActivity;
import com.wondersgroup.healthxuhui.ui.second.SecondActivity;
import com.wondersgroup.healthxuhui.ui.second.SecondItemEnum;
import com.wondersgroup.healthxuhui.util.AppUrlUtil;
import com.wondersgroup.healthxuhui.util.SPUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;


public class MainActivity extends BaseActivity implements MainContract.View, View.OnClickListener {

    /**
     * 社区
     */
    private MenuItem communityMI;
    /**
     * 记录AppBarLayout的状态
     */
    private CollapsingToolbarLayoutState state;

    /**
     * 标题
     */
    private TextView tvTitle;

    private RelativeLayout rlQygk;//区域概况
    private RelativeLayout rlQyws;//区域卫生
    private RelativeLayout rlYlfw;//医疗服务
    private RelativeLayout rlGgws;//公共卫生

    private MainContract.Presenter mPresenter;

    private WebView webView;
    private HashMap<String, String> resMap;
    private AssetManager assetManager;
    private RelativeLayout rlLoading;


    private enum CollapsingToolbarLayoutState {
        //展开状态
        EXPANDED,
        //折叠状态
        COLLAPSED,
        //中间状态
        INTERNEDIATE
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initWebView();
    }

    /**
     * 初始化
     */
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tvTitle = (TextView) findViewById(R.id.app_bar_title);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            //当appbar滚动时，verticalOffset的变化范围：
            // 展开时为0，
            // 向上滚时为-1、-5、……，最后固定，例如-328
            //在向上滚时，-328、……，最后为0
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {//展开时
                    state = CollapsingToolbarLayoutState.EXPANDED;
                    if (communityMI != null) {
                        communityMI.setVisible(false);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {//折叠时
                    state = CollapsingToolbarLayoutState.COLLAPSED;
                    communityMI.setVisible(true);
                    tvTitle.setVisibility(View.VISIBLE);

                } else {//折叠或者展开过程中
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {//刚进入中间状态
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {//由折叠进入中间状态
                            communityMI.setVisible(false);
                            tvTitle.setVisibility(View.INVISIBLE);
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;
                    }
                }
            }
        });

        rlQygk = (RelativeLayout) findViewById(R.id.rl_qygk);
        rlQyws = (RelativeLayout) findViewById(R.id.rl_qyws);
        rlYlfw = (RelativeLayout) findViewById(R.id.rl_ylfw);
        rlGgws = (RelativeLayout) findViewById(R.id.rl_ggws);
        rlQygk.setOnClickListener(this);
        rlQyws.setOnClickListener(this);
        rlYlfw.setOnClickListener(this);
        rlGgws.setOnClickListener(this);

        rlLoading = (RelativeLayout) findViewById(R.id.rl_loading);

        new MainPresenter(RemoteDataSource.getInstance(), this);


        long lastPromptTime = SPUtils.getSP(this).getLong(SPUtils.LAST_PROMPT_TIME, 0);
        if (new Date().getTime() - lastPromptTime > 24*60*60*1000){//大于1天的时间
            mPresenter.updateVerion();
        }
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLoadsImagesAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//优先使用缓存

        webView.setWebViewClient(new WebViewClient() {//点击页面链接，在当前browser打开


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                rlLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                WebResourceResponse response = getWebResourceResponse(url);
                if (null != response){
                    return response;
                }
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                showBrowser(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.loadUrl("file:///android_asset/error/error.html");
//                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

        mPresenter.loadHomeWebPage();
    }

    /**
     * 截获网页的资源请求,从本地加载网页的资源文件
     * @param url
     * @return
     */
    private WebResourceResponse getWebResourceResponse(String url){
        if(resMap == null){
            resMap = new HashMap<String, String>();
            //本地资源文件的目录
            resMap.put("text/css", "xhws_webapp/css/");
            resMap.put("image/png", "xhws_webapp/images/");
            resMap.put("text/javascript", "xhws_webapp/js/");
        }
        if (assetManager == null){
            assetManager = getAssets();
        }
        try {
            for (String key : resMap.keySet()){
                String value = resMap.get(key);
                int index = url.indexOf(value);
                if (index > 0){
                    String substring = url.substring(index);
                    return new WebResourceResponse(key, substring, assetManager.open(substring));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void loadUrl(String url) {
        webView.loadUrl(url);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_qygk:
                mPresenter.openQygk();
                break;
            case R.id.rl_qyws:
                mPresenter.openQyws();
                break;
            case R.id.rl_ylfw:
                mPresenter.openYlfw();
                break;
            case R.id.rl_ggws:
                mPresenter.openGGws();
                break;
        }
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        communityMI = menu.getItem(0);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mi_community) {
            showMap();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showMap() {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSecond(SecondItemEnum sencondType) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(SecondActivity.SENCOND_TYPE, sencondType);
        startActivity(intent);
    }

    @Override
    public void showBrowser(String url) {
        Intent intent = new Intent(MainActivity.this, BrowserActivity.class);
        intent.putExtra(BrowserActivity.URL, url);
        startActivity(intent);
    }

    @Override
    public void showUpdateDialog(VersonUpdateEntity entity) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("发现新版本：" + entity.getVersionNumber())
                .setMessage("更新日志：\n" + entity.getVersionChange())
                .setPositiveButton("下载更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("https://www.pgyer.com/jkxh_android");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = SPUtils.getSP(MainActivity.this).edit();
                        editor.putLong(SPUtils.LAST_PROMPT_TIME, new Date().getTime());
                        editor.commit();
                        dialog.dismiss();
                    }
                });
        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}