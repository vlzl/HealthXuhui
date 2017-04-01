package com.wondersgroup.healthxuhui.ui.map;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yangjinxi on 2016/6/12.
 */
public class MapPagerAdapter extends FragmentPagerAdapter {

    private List<PagerFragment> fragments;

    public MapPagerAdapter(FragmentManager fm, List<PagerFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
