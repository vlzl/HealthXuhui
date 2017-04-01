package com.wondersgroup.healthxuhui.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wondersgroup.healthxuhui.util.LogUtil;

/**
 * Created by yang on 16/7/3.
 */
public class BaseFragment extends Fragment{

    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG + "------" + "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG + "------" + "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i(TAG + "------" + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG + "------" + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG + "------" + "onPause");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.i(TAG + "------" + "onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i(TAG + "------" + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG + "------" + "onDestroy");
    }
}
