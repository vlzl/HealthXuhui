package com.wondersgroup.healthxuhui.ui.map;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.healthxuhui.R;
import com.wondersgroup.healthxuhui.data.entity.HealthServiceCenterEntity;

/**
 *
 */
public class PagerFragment extends Fragment {
    private static final String PARAM = "param1";

    private HealthServiceCenterEntity healthServiceCenterEntity;


    public PagerFragment() {
    }

    public static PagerFragment newInstance(HealthServiceCenterEntity entity) {
        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARAM, entity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            healthServiceCenterEntity = getArguments().getParcelable(PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        ((TextView) view.findViewById(R.id.tv_name)).setText(healthServiceCenterEntity.getName());
        ((TextView) view.findViewById(R.id.tv_address)).setText(healthServiceCenterEntity.getAddress());
        ((TextView) view.findViewById(R.id.tv_phone)).setText("电话：" + healthServiceCenterEntity.getTelephone());
        ((TextView) view.findViewById(R.id.tv_postcode)).setText("邮编：" + healthServiceCenterEntity.getCode());
        int show = healthServiceCenterEntity.getShow();
        if (show == 1){
            View showView = view.findViewById(R.id.ll_show);
            showView.setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.tv_qxrk)).setText("服务人口：" + healthServiceCenterEntity.getQxrk());
            ((TextView) view.findViewById(R.id.tv_yjyqy)).setText("1+1+1签约：" + healthServiceCenterEntity.getYjyqy());
        }
        return view;
    }

}
