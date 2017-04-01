package com.wondersgroup.healthxuhui.ui.map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.wondersgroup.healthxuhui.R;
import com.wondersgroup.healthxuhui.data.source.remote.RemoteDataSource;
import com.wondersgroup.healthxuhui.data.entity.HealthServiceCenterEntity;
import com.wondersgroup.healthxuhui.ui.base.BaseActivity;
import com.wondersgroup.healthxuhui.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends BaseActivity  implements MapContract.View{
    private static final String COMMUNITY = "community";
    private static final String MARKER_POSITION = "position";

    /**
     * 初始缩放级别
     */
    private static final float ZOOM_LEVEL = 14.5f;
    private TextView tvTitle;
    private MapView mapView;
    private ViewPager viewPager;

    private List<Marker> markers;

    private List<HealthServiceCenterEntity> communities;

    private List<PagerFragment> pagerFragments;

    private SDKReceiver mReceiver;

    private InfoWindow mInfoWindow;

    BitmapDescriptor bd;
    private BaiduMap baiduMap;

    private MapContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText(getText(R.string.community));

        mapView = (MapView) findViewById(R.id.mv_map);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setPageMargin(20);

        bd = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);

        // 注册 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);


        baiduMap = mapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(ZOOM_LEVEL);
        baiduMap.setMapStatus(msu);

        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener(){

            @Override
            public boolean onMarkerClick(final Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                int position = bundle.getInt(MARKER_POSITION);
                showInfoWindow(marker);
                viewPager.setCurrentItem(position);
                return true;
            }
        });

        new MapPresenter(RemoteDataSource.getInstance(), this);
        mPresenter.getDataList();
    }

    /**
     * 显示覆盖物的文字
     * @param marker
     */
    private void showInfoWindow(Marker marker) {
        Bundle bundle = marker.getExtraInfo();
        HealthServiceCenterEntity entity = bundle.getParcelable(COMMUNITY);
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.drawable.map_popup_bg);
        button.setText(entity.getName());
        button.setTextColor(Color.BLACK);
        LatLng ll = marker.getPosition();
        mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, -47, null);
        baiduMap.showInfoWindow(mInfoWindow);
    }

    @Override
    protected void onPause() {
        // MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
        mapView.onPause();
        mPresenter.unsubscribe();
        super.onPause();
    }

    @Override
    protected void onResume() {
        // MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
        mapView.onResume();
        mPresenter.subscribe();
        super.onResume();

        LatLng ll = new LatLng(31.158511, 121.447195);
        showTargetMaker(ll);
    }

    /**
     * 调整地图，把相应坐标移到视图中间
     * @param ll
     */
    private void showTargetMaker(LatLng ll) {
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(ZOOM_LEVEL);
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @Override
    protected void onDestroy() {
        // MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
        mapView.onDestroy();
        super.onDestroy();
        // 回收 bitmap 资源
        bd.recycle();

        // 取消监听 SDK 广播
        unregisterReceiver(mReceiver);
    }

    @Override
    public void showDataList(List<HealthServiceCenterEntity> list) {
        communities = list;
        pagerFragments = new ArrayList<PagerFragment>();
        markers = new ArrayList<Marker>();
        for (int i = 0; i < communities.size(); i++){
            HealthServiceCenterEntity entity = communities.get(i);
            PagerFragment pagerFragment = PagerFragment.newInstance(entity);
            pagerFragments.add(pagerFragment);
            LatLng ll = new LatLng(entity.getWeidu(), entity.getJingdu());
            Bundle bundle = new Bundle();
            bundle.putInt(MARKER_POSITION, i);
            bundle.putParcelable(COMMUNITY, entity);//将实体类与maker 绑定
            MarkerOptions ooA = new MarkerOptions().position(ll).icon(bd).zIndex(i).extraInfo(bundle);
            // 掉下动画
            ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
            Marker marker = (Marker) (baiduMap.addOverlay(ooA));
            markers.add(marker);
        }

        MapPagerAdapter pagerAdapter = new MapPagerAdapter(getSupportFragmentManager(), pagerFragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.i("onPageSelected:"+position);
                Marker marker = markers.get(position);
                LatLng latLng = marker.getPosition();
                showInfoWindow(marker);
                showTargetMaker(latLng);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showInfoWindow() {

    }

    @Override
    public void turnViewPager(int position) {

    }

    @Override
    public void setPresenter(MapContract.Presenter presenter) {
        mPresenter = presenter;
    }


    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                LogUtil.i("key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
                LogUtil.i("key 验证成功! 功能可以正常使用");
            }else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                LogUtil.i("网络出错");
            }
        }
    }
}
