package com.puyue.www.qiaoge.activity.mine.order;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.puyue.www.qiaoge.R;

import com.puyue.www.qiaoge.adapter.mine.AddressApi;
import com.puyue.www.qiaoge.api.mine.order.ConfirmGetGoodsAPI;
import com.puyue.www.qiaoge.api.mine.order.GetOrderMapMessageAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;


import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.mine.order.ConfirmGetGoodsModel;
import com.puyue.www.qiaoge.model.mine.order.GetOrderDriverModel;
import com.puyue.www.qiaoge.model.mine.wallet.AddressModel;
import com.puyue.www.qiaoge.slidenested.SlideNestedPanelLayout;
import com.puyue.www.qiaoge.slidenested.StateCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 用的帧布局，SlideNestedPanelLayout的滑动时间会和地图冲突，造成地图不能位移，缩小变大
 * Created by ${王文博} on 2019/5/28
 */
public class MapOrderMessageActivity extends BaseSwipeActivity {
    private MapView mMapView = null;
    private String orderId;
    //司机地址
    private String addressDetail;

    BaiduMap mBaiduMap;
    private ConfirmGetGoodsModel mModelConfirmGetGoods;

    private TextView mTvOk;

    private List<GetOrderDriverModel.DataBean.UserLocationVOListBean> listBeans = new ArrayList<>();

    private String payTime;
    private String receiveTime;
    private String sendTime;
    private String finishTime;

    private TextView tv_wait_order;
    private TextView tv_wait_send_date;
    private TextView tv_already_send_date;
    private TextView tv_send_date;
    private TextView tv_confirm_date;
    private TextView tv_wait_month;
    private TextView tv_wait_send_month;
    private TextView tv_already_send_month;
    private TextView tv_send_month;
    private TextView tv_confirm_month;
    private RelativeLayout rl_confirm_order;//确认收货
    private RelativeLayout rl_send_order;//发货完毕
    private TextView tv_driver_name;//司机名字
    private TextView tv_driver_phone;//司机电话
    private TextView tv_circle_one;
    private TextView tv_circle_four;
    private TextView tv_circle_five;
    private TextView tv_send_order;
    private ImageView iv_back;


    private LoadingDailog dialog;


    private LatLng cenpt;
    private MapStatusUpdate mMapStatusUpdate;


    private GeoCoder mCoder;
    double latitude1;
    double longitude1;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.map_order_detail);
    }

    @Override
    public void findViewById() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mTvOk = findViewById(R.id.tv_ok);
        tv_wait_order = findViewById(R.id.tv_wait_order);
        tv_wait_send_date = findViewById(R.id.tv_wait_send_date);
        tv_already_send_date = findViewById(R.id.tv_already_send_date);
        rl_confirm_order = findViewById(R.id.rl_confirm_order);
        rl_send_order = findViewById(R.id.rl_send_order);
        tv_send_date = findViewById(R.id.tv_send_date);
        tv_confirm_date = findViewById(R.id.tv_confirm_date);
        tv_wait_month = findViewById(R.id.tv_wait_month);
        tv_wait_send_month = findViewById(R.id.tv_wait_send_month);
        tv_already_send_month = findViewById(R.id.tv_already_send_month);
        tv_send_month = findViewById(R.id.tv_send_month);
        tv_confirm_month = findViewById(R.id.tv_confirm_month);
        tv_driver_name = findViewById(R.id.tv_driver_name);
        tv_driver_phone = findViewById(R.id.tv_driver_phone);
        tv_circle_five = findViewById(R.id.tv_circle_five);
        tv_circle_one = findViewById(R.id.tv_circle_one);
        tv_circle_four = findViewById(R.id.tv_circle_four);
        tv_send_order = findViewById(R.id.tv_send_order);
        iv_back = findViewById(R.id.iv_back);
    }

    @Override
    public void setViewData() {


        orderId = getIntent().getStringExtra("orderId");
        timer.schedule(task, 0, 60 * 1000);
        //获取物流信息
        getOrderMessage(orderId);


        mBaiduMap = mMapView.getMap();
//普通地图 ,mBaiduMap是地图控制器对象
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);


        //默认显示地图标注
        //  mBaiduMap.showMapPoi(false);

        //通过设置enable为true或false 选择是否显示比例尺
        mMapView.showScaleControl(false);

        //通过设置enable为true或false 选择是否显示缩放按钮
        mMapView.showZoomControls(false);
/*
        BaiduMapOptions options = new BaiduMapOptions();
        //设置地图模式为卫星地图
        options.mapType(BaiduMap.MAP_TYPE_SATELLITE);

        MapView mapView = new MapView(this, options);
        setContentView(mapView);*/

//设定中心点坐标
        NestedScrollView mScrollView = findViewById(R.id.nestedScrollView);
    /*    mScrollView.animate().alpha(1.0f).setDuration(500);

        SlideNestedPanelLayout mPanelLayout = findViewById(R.id.slideNestedPanelLayout);
        mPanelLayout.setStateCallback(new StateCallback() {
            @Override
            public void onExpandedState() {
                Log.i("-->","onExpandedState");
            }

            @Override
            public void onCollapsedState() {
                Log.i("-->","onCollapsedState");
            }
        });*/

/*
        points.add(new LatLng(30.283552, 120.189864));
        points.add(new LatLng(30.265587, 120.162843));
        points.add(new LatLng(30.292284, 120.117712));
        points.add(new LatLng(30.323712, 120.118574));
        points.add(new LatLng(30.333936, 120.223496));*/

        Log.i("dwqrqrqwr", "setViewData: " + returnDate("2019-05-22 10:17:28"));
        mCoder = GeoCoder.newInstance();

        mCoder.setOnGetGeoCodeResultListener(listener);

        //   getAddress(addressDetail);


    }

    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            if (null != geoCodeResult && null != geoCodeResult.getLocation()) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                    return;
                } else {
                    latitude1 = geoCodeResult.getLocation().latitude;
                    longitude1 = geoCodeResult.getLocation().longitude;
                    getAddressLocation();
                }
            }
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

        }

    };

    private void getAddressLocation() {
        LatLng cenpt = new LatLng(latitude1, longitude1);
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
                .fromResource(R.mipmap.ic_driver_location_end);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(cenpt)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    @Override
    public void setClickEvent() {
        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestConfirmGetGoods();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private final Timer timer = new Timer();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // 要做的事情


            super.handleMessage(msg);
            if (msg.what == 1) {
                //定时获取地址

            }
        }
    };


    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };


    private void getOrderMessage(String orderId) {


        GetOrderMapMessageAPI.requestDriverMe(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetOrderDriverModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetOrderDriverModel getOrderDriverModel) {

                        if (getOrderDriverModel.isSuccess()) {
                            //


                            payTime = getOrderDriverModel.getData().getPayTime();
                            receiveTime = getOrderDriverModel.getData().getReceiveTime();
                            sendTime = getOrderDriverModel.getData().getSendTime();
                            finishTime = getOrderDriverModel.getData().getFinishTime();

                            tv_wait_order.setText(returnDate(payTime));
                            tv_wait_send_date.setText(returnDate(receiveTime));
                            tv_already_send_date.setText(returnDate(sendTime));
                            tv_wait_month.setText(returnMonth(payTime));
                            tv_wait_send_month.setText(returnMonth(receiveTime));
                            tv_already_send_month.setText(returnMonth(sendTime));

                            tv_driver_name.setText("配送员：" + getOrderDriverModel.getData().getDriverName());
                            tv_driver_phone.setText(getOrderDriverModel.getData().getDriverPhone());
                            tv_send_month.setText(returnMonth(sendTime));
                            tv_send_date.setText(returnDate(sendTime));

                            if (finishTime != null) {
                                //  tv_send_date.setText(returnDate(finishTime));
                                tv_confirm_date.setText(returnDate(finishTime));
                                // tv_send_month.setText(returnMonth(finishTime));
                                tv_confirm_month.setText(returnMonth(finishTime));

                            }
                            if (getOrderDriverModel.getData().getOrderStatus() == 5) {
                                rl_confirm_order.setVisibility(View.VISIBLE);
                                tv_send_order.setText("派送完毕");
                                //  tv_driver_name.setText(getOrderDriverModel.getData().getDriverName());
                                //  tv_driver_phone.setText(getOrderDriverModel.getData().getDriverPhone());
                                tv_circle_five.setBackgroundResource(R.drawable.circle_bg);
                                //   tv_circle_one.setBackgroundResource(R.drawable.circle_bg_two);
                                tv_circle_four.setBackgroundResource(R.drawable.circle_bg_two);
                            } else {
                                rl_confirm_order.setVisibility(View.GONE);
                                tv_send_order.setText("正在派送中");
                                tv_circle_four.setBackgroundResource(R.drawable.circle_bg);
                            }

                            Log.i("wwbbb", "onNext: " + listBeans.size() + "/" + getOrderDriverModel.getData().getUserLocationVOList().size());
                            listBeans.clear();
                            addressDetail = getOrderDriverModel.getData().getAddressDetail();

                            listBeans.addAll(getOrderDriverModel.getData().getUserLocationVOList());

                            showPoint();
                            mCoder.geocode(new GeoCodeOption()
                                    .city("杭州")
                                    .address(addressDetail));
                            // getAddress(addressDetail);
                            if (dialog != null) {
                                dialog.dismiss();
                            }


                        }


                    }
                });
    }

    private String returnDate(String s) {


        String[] split = s.split(" ");
        String s1 = split[1];
        String s2;
        if (s1.length() > 3) {

            s2=s1.substring(0, s1.length()-3);
            return s2;
        }


        return s1;
    }

    private String returnMonth(String s) {

        String[] split = s.split(" ");
        String s1 = split[1];//这个得到的是18:20:20
        String s2 = split[0];//2018-01-02
        String[] split1 = s2.split("-");
        String s3 = split1[1];//01
        String s4 = split1[2];//02


        return s3 + "-" + s4;
    }


    private void showPoint() {


        //构建折线点坐标
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < listBeans.size(); i++) {
            points.add(new LatLng(Double.parseDouble(listBeans.get(i).getLatitude()), Double.parseDouble(listBeans.get(i).getLongitude())));
        }

        Log.i("wwbb", "setViewData: " + points.size());

        List<Integer> colors = new ArrayList<>();

        for (int i = 0; i < listBeans.size(); i++) {

            if (listBeans.get(i).getType().equals("1")) {
                colors.add(Integer.valueOf(Color.GREEN));
            } else if (listBeans.get(i).getType().equals("2")) {
                colors.add(Integer.valueOf(Color.RED));
            }
        }


       /* colors.add(Integer.valueOf(Color.BLUE));

        colors.add(Integer.valueOf(Color.YELLOW));*/


        OverlayOptions mOverlayOptions = new PolylineOptions()
                .width(10)
                .color(0xAAFF0000)
                .points(points)
                .colorsValues(colors);//设置每段折线的颜色

//在地图上绘制折线
//mPloyline 折线对象
        Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);


        if (listBeans.size() > 0) {
            initMapTwo(Double.parseDouble(listBeans.get(0).getLatitude()), Double.parseDouble(listBeans.get(0).getLongitude()));
        }

        if (listBeans.size() == 1) {
            initMap(Double.parseDouble(listBeans.get(0).getLatitude()), Double.parseDouble(listBeans.get(0).getLongitude()));
        } else if (listBeans.size() > 1) {
            initMap(Double.parseDouble(listBeans.get(listBeans.size() - 1).getLatitude()), Double.parseDouble(listBeans.get(listBeans.size() - 1).getLongitude()));
        }

    }

    private void initMapTwo(Double lat, Double lng) {
        LatLng cenpt = new LatLng(lat, lng);
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
                .fromResource(R.mipmap.ic_driver_location_start);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(cenpt)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

    }

    public void getAddress(String addressDetail) {
        AddressApi.requestAddress(mContext, "杭州市万达广场", "json", "KqnEcjvFD0DdacMhQxUtxtSYm2Fj9Lia", "C3:FD:C2:A1:A5:77:B8:27:81:BB:89:98:95:59:66:C0:E0:C1:2B:6B;com.barbecue.app")
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


                        Log.i("wwb", "onNext: " + addressModel.status);
                        if (addressModel.status == 0) {
                            double lat = addressModel.result.location.lat;//纬度
                            double lng = addressModel.result.location.lng;//经度
                            LatLng cenpt = new LatLng(lat, lng);
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
                                    .fromResource(R.mipmap.ic_driver_location_end);
                            //构建MarkerOption，用于在地图上添加Marker
                            OverlayOptions option = new MarkerOptions()
                                    .position(cenpt)
                                    .icon(bitmap);
                            //在地图上添加Marker，并显示
                            mBaiduMap.addOverlay(option);
                            mBaiduMap.setMapStatus(mMapStatusUpdate);
                        } else {

                        }
                    }
                });

    }


    private void initMap(Double lat, Double lng) {
        cenpt = new LatLng(lat, lng);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化


        mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        //定义Maker坐标点

        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_map_status);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(cenpt)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        timer.cancel();
        mCoder.destroy();

    }


    // 确认收货
    private void requestConfirmGetGoods() {
        ConfirmGetGoodsAPI.reuqestConfirmGetGoods(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ConfirmGetGoodsModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ConfirmGetGoodsModel confirmGetGoodsModel) {
                        mModelConfirmGetGoods = confirmGetGoodsModel;
                        if (mModelConfirmGetGoods.success) {

                            //确认收货成功
                            AppHelper.showMsg(mContext, "确认收货成功");
                            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                                    .setMessage("确认收货成功")
                                    .setCancelable(false)

                                    .setCancelOutside(true);
                            dialog = loadBuilder.create();
                            dialog.show();
                            mTvOk.setClickable(false);
                            mTvOk.setText("收货成功");
                            getOrderMessageTwo(orderId);
                        } else {
                            AppHelper.showMsg(mContext, mModelConfirmGetGoods.message);
                        }
                    }
                });
    }

    private void getOrderMessageTwo(String orderId) {

        GetOrderMapMessageAPI.requestDriverMe(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetOrderDriverModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetOrderDriverModel getOrderDriverModel) {

                        if (getOrderDriverModel.isSuccess()) {
                            //

                            dialog.dismiss();
                            payTime = getOrderDriverModel.getData().getPayTime();
                            receiveTime = getOrderDriverModel.getData().getReceiveTime();
                            sendTime = getOrderDriverModel.getData().getSendTime();
                            finishTime = getOrderDriverModel.getData().getFinishTime();


                            tv_wait_order.setText(returnDate(payTime));
                            tv_wait_send_date.setText(returnDate(receiveTime));
                            tv_already_send_date.setText(returnDate(sendTime));
                            tv_wait_month.setText(returnMonth(payTime));
                            tv_wait_send_month.setText(returnMonth(receiveTime));
                            tv_already_send_month.setText(returnMonth(sendTime));

                            tv_send_date.setText(returnDate(sendTime));
                            tv_send_month.setText(returnMonth(sendTime));
                            tv_driver_name.setText("配送员：" + getOrderDriverModel.getData().getDriverName());
                            tv_driver_phone.setText(getOrderDriverModel.getData().getDriverPhone());
                            if (finishTime != null) {
                                //    tv_send_date.setText(returnDate(finishTime));
                                tv_confirm_date.setText(returnDate(finishTime));
                                //   tv_send_month.setText(returnMonth(finishTime));
                                tv_confirm_month.setText(returnMonth(finishTime));

                            }
                            if (getOrderDriverModel.getData().getOrderStatus() == 5) {
                                rl_confirm_order.setVisibility(View.VISIBLE);

                                // tv_driver_name.setText(getOrderDriverModel.getData().getDriverName());
                                // tv_driver_phone.setText(getOrderDriverModel.getData().getDriverPhone());
                                tv_circle_five.setBackgroundResource(R.drawable.circle_bg);
                                tv_circle_four.setBackgroundResource(R.drawable.circle_bg_two);

                            } else {
                                rl_confirm_order.setVisibility(View.GONE);

                                tv_circle_four.setBackgroundResource(R.drawable.circle_bg);
                            }

                            Log.i("wwbbb", "onNext: " + listBeans.size() + "/" + getOrderDriverModel.getData().getUserLocationVOList().size());
                            listBeans.clear();
                            //    addressDetail = getOrderDriverModel.getData().getAddressDetail();
                            // getAddress(addressDetail);
                            listBeans.addAll(getOrderDriverModel.getData().getUserLocationVOList());

                            showPoint();


                        }


                    }
                });
    }
}
