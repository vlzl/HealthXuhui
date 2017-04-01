package com.wondersgroup.healthxuhui.ui.browser;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wondersgroup.healthxuhui.R;
import com.wondersgroup.healthxuhui.ui.base.BaseFragment;
import com.wondersgroup.healthxuhui.util.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 加载网页的浏览器fragment
 */
public class BrowserFragment extends BaseFragment {
    /**
     * 加载的网页URL 的 key
     */
    public static final String URL_KEY = "url";

    /**
     * 加载的网页URL
     */
    private String url;

    private BrowserCallback callback;
    private WebView webView;
    private HashMap<String, String> resMap;
    private AssetManager assetManager;

    private List<String> titles = new ArrayList<String>();

    public static BrowserFragment newInstance(@NonNull String url) {

        Bundle args = new Bundle();
        if (TextUtils.isEmpty(url)) {
            throw new RuntimeException("加载的网页URL 不能为空!");
        }
        args.putString(URL_KEY, url);
        BrowserFragment fragment = new BrowserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public BrowserFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle agrs = getArguments();
        url = agrs.getString(URL_KEY, "");
        assetManager = getActivity().getAssets();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_browser, container, false);
        webView = (WebView) root.findViewById(R.id.webview);
        initWebView();
        return root;
    }


    /**
     * 初始化webview
     */
    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLoadsImagesAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//优先使用缓存

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (null != callback) {
                    callback.inProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titles.add(title);
                if (null != callback) {
                    callback.onReceivedTitle(title);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {//点击页面链接，在当前browser打开


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
//                showBrowser(url);
                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.loadUrl("file:///android_asset/error/error.html");
//                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
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
    public void onStart() {
        super.onStart();
        webView.loadUrl(url);
    }



    @Override
    public void onDestroy() {
        webView.stopLoading();
        webView.clearHistory();
        ((ViewGroup) webView.getParent()).removeView(webView);
        webView.destroy();
        webView = null;
        assetManager = null;
        resMap = null;
        super.onDestroy();
    }

    public boolean canAndGOback(){
        if (isVisible() && webView != null && webView.canGoBack()){
            webView.goBack();
            int size = titles.size();
            titles.remove(size - 1);
            callback.onReceivedTitle(titles.get(size - 2));
            return true;
        }
        return false;
    }

    public void setBrowserCallback(BrowserCallback callback) {
        this.callback = callback;
    }
}
