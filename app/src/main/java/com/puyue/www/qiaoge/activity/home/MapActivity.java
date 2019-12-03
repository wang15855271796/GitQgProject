package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayOptions;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.AddressApi;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.MapHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.wallet.AddressModel;

import java.net.URISyntaxException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/28.
 */

public class MapActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView tv_address_one;
    private TextView tv_address_two;

    private ImageView mBtnGoHere;
    private BottomSheetDialog mDialogMap;

    private MapView mMapView = null;
    private double longitude;//经度
    private double latitlatitude;//纬度
    private String title;
    private String content;
    BaiduMap mBaiduMap;
    private String location;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            //  longitude = bundle.getDouble(AppConstant.LONGITUDE);
            // latitlatitude = bundle.getDouble(AppConstant.LATITLATITUDE);
            //   title = bundle.getString(AppConstant.TITLE);
            //   content = bundle.getString(AppConstant.CONTENT);

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_map);

    }

    @Override
    public void findViewById() {
        //  mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        // mTvTitle = FVHelper.fv(this, R.id.tv_activity_title);
        mBtnGoHere = FVHelper.fv(this, R.id.btn_activity_gohere);
        mMapView = FVHelper.fv(this, R.id.mp_activity_map);
        tv_address_one = FVHelper.fv(this, R.id.tv_address_one);
        tv_address_two = FVHelper.fv(this, R.id.tv_address_two);

    }

    @Override
    public void setViewData() {
//location="杭州拱墅万达广场";
     location = getIntent().getStringExtra("addressDriver");
        mBaiduMap = mMapView.getMap();
//普通地图 ,mBaiduMap是地图控制器对象
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);


        //默认显示地图标注
        //  mBaiduMap.showMapPoi(false);

        //通过设置enable为true或false 选择是否显示比例尺
        mMapView.showScaleControl(false);

        //通过设置enable为true或false 选择是否显示缩放按钮
        mMapView.showZoomControls(false);
        //mTvTitle.setText("地图");
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
    /*    mapView.onCreate(mBundle);
        //初始化地图控制器对象
        AMap aMap = mapView.getMap();
        LatLng latLng = new LatLng(latitlatitude, longitude);
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title(title).snippet(content);
        markerOption.draggable(true);//设置Marker可拖动
        markerOption.setFlat(true);//设置marker平贴地图效果
        aMap.addMarker(markerOption);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));*/
        getAddress(location);
        content = location;
        title = location;
        tv_address_one.setText(location);
        tv_address_two.setText(location);
    }


    @Override
    public void setClickEvent() {
        //  mIvBack.setOnClickListener(noDoubleClickListener);
        mBtnGoHere.setOnClickListener(noDoubleClickListener);

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
          /*  if (view == mIvBack) {
                finish();
            } else*/
            if (view == mBtnGoHere) {
                showMapDialog();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    public void showMapDialog() {
        mDialogMap = new BottomSheetDialog(mContext);
        mDialogMap.setContentView(R.layout.dialog_map);
        mDialogMap.show();
        mDialogMap.findViewById(R.id.ll_dialog_baidu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //百度
                goBaiduMap();
            }
        });
        mDialogMap.findViewById(R.id.ll_dialog_gaode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //高德
                goGaoDeMap();
            }
        });
        mDialogMap.findViewById(R.id.ll_dialog_tengxun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //腾讯
                goTengXunMap();
            }
        });
        mDialogMap.findViewById(R.id.tv_dialog_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消
                mDialogMap.dismiss();
            }
        });
    }

    /**
     * 跳转百度地图
     */
    private void goBaiduMap() {
        if (MapHelper.isAvilible(mContext, "com.baidu.BaiduMap")) {// 传入指定应用包名
            try {
                Intent intent = new Intent();
                intent.setData(Uri.parse("baidumap://map/marker?location=" + latitlatitude + "," + longitude + "&title=" + title + "&content=" + content + "&traffic=on"));
                startActivity(intent); // 启动调用
                mDialogMap.dismiss();
            } catch (Exception e) {
                Log.e("intent", e.getMessage());
            }
        } else {
            // 未安装
            String mapUri = "http://api.map.baidu.com/marker?location=" + latitlatitude + "," + longitude + "&title=" + title + "&content=" + content + "&output=html";
            Uri uri = Uri.parse(mapUri);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            mDialogMap.dismiss();
        }
    }

    /**
     * 跳转到高德地图
     */
    private void goGaoDeMap() {
        if (MapHelper.isAvilible(mContext, "com.autonavi.minimap")) {
            try {
                Intent intent = Intent.getIntent("androidamap://viewMap?sourceApplication=翘歌烧烤&poiname=" + title + "&lat="
                        + latitlatitude
                        + "&lon="
                        + longitude + "&dev=0");
                startActivity(intent);
                mDialogMap.dismiss();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            String mapUri = "http://uri.amap.com/marker?position=" + longitude + "," + latitlatitude + "&name=" + title + "&src=" + content + "&coordinate=gaode&callnative=0";
            Uri uri = Uri.parse(mapUri);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            mDialogMap.dismiss();
        }

    }

    /**
     * 打开腾讯地图网页版
     */
    private void goTengXunMap() {
        String mapUri = "http://apis.map.qq.com/uri/v1/marker?marker=coord:" + latitlatitude + "," + longitude + ";title:" + title + ";addr:" + content + "&referer=翘歌烧烤";
        Uri uri = Uri.parse(mapUri);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        mDialogMap.dismiss();
    }


    public void getAddress(String addressDetail) {
        AddressApi.requestAddress(mContext, addressDetail, "json", "KqnEcjvFD0DdacMhQxUtxtSYm2Fj9Lia", "C3:FD:C2:A1:A5:77:B8:27:81:BB:89:98:95:59:66:C0:E0:C1:2B:6B;com.barbecue.app")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddressModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("lyy", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(AddressModel addressModel) {
                        if (addressModel.status == 0) {
                            latitlatitude = addressModel.result.location.lat;//纬度
                            longitude = addressModel.result.location.lng;//经度
                            com.baidu.mapapi.model.LatLng cenpt = new com.baidu.mapapi.model.LatLng(latitlatitude, longitude);
                            //定义地图状态
                            MapStatus mMapStatus = new MapStatus.Builder()
                                    .target(cenpt)
                                    .zoom(18)
                                    .build();
                            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化


                            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                            //改变地图状态
                            //定义Maker坐标点

                            //构建Marker图标
                            BitmapDescriptor bitmap = BitmapDescriptorFactory
                                    .fromResource(R.mipmap.ic_map_status);
                            //构建MarkerOption，用于在地图上添加Marker
                            OverlayOptions option = new com.baidu.mapapi.map.MarkerOptions()
                                    .position(cenpt)
                                    .icon(bitmap);
                            //在地图上添加Marker，并显示
                            mBaiduMap.addOverlay(option);
                            mBaiduMap.setMapStatus(mMapStatusUpdate);
                        }
                    }
                });

    }
}
