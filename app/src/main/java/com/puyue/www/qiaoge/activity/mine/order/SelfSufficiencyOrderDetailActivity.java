package com.puyue.www.qiaoge.activity.mine.order;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CartActivity;
import com.puyue.www.qiaoge.activity.mine.account.AddressListActivity;
import com.puyue.www.qiaoge.adapter.mine.NewOrderDetailAdapter;
import com.puyue.www.qiaoge.api.cart.CancelOrderAPI;
import com.puyue.www.qiaoge.api.cart.DeleteOrderAPI;
import com.puyue.www.qiaoge.api.home.GetOrderDetailAPI;
import com.puyue.www.qiaoge.api.mine.order.ConfirmOrderSelfAPI;
import com.puyue.www.qiaoge.api.mine.order.CopyToCartAPI;
import com.puyue.www.qiaoge.api.mine.order.GetOrderDeliverTimeAPI;
import com.puyue.www.qiaoge.api.mine.order.PostModifyMesAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.MapHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CancelOrderModel;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.ConfirmGetGoodsModel;
import com.puyue.www.qiaoge.model.mine.order.CopyToCartModel;
import com.puyue.www.qiaoge.model.mine.order.GetTimeOrderModel;
import com.puyue.www.qiaoge.model.mine.order.OrderEvaluateListModel;
import com.puyue.www.qiaoge.view.GCJ02ToWGS84Util;
import com.puyue.www.qiaoge.view.GradientColorTextView;
import com.puyue.www.qiaoge.view.PickCityUtil;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王文博} on 2019/7/24
 */
public class SelfSufficiencyOrderDetailActivity extends BaseSwipeActivity {


    private ImageView imageViewBreak;
    private TextView textViewTitle;
    private RecyclerView recyclerView;
    // 非退货订单layout 头部
    private LinearLayout orderLinearLayout;
    private TextView tvOrderTitle; //订单类型
    private SnapUpCountDownTimerView orderTimerView; //倒计时
    private TextView tvOrderContent; //订单内容
    private LinearLayout threeButtonLayout; //三个按钮（退货按钮 评价按钮 在次购买按钮）
    private TextView buttonReturnGoods; // 退货按钮
    private TextView buttonEvaluate; //评价按钮
    private TextView buttonAgainBuy; // 在次购买按钮

    private LinearLayout twoButtonLayout; //二个按钮（取消订单  去支付）
    private TextView buttonCancelOrder; // 取消订单
    private TextView buttonGOPay; // 去支付

    //退货订单 layout 头部
    private LinearLayout ReturnGoodsLinearLayout;
    private TextView ReturnGoodsTitle; //退货类型
    private TextView ReturnGoodsContent; //退货内容
    private TextView tvNewOrderCommodityNum;//总件商品

    // 非退货订单信息layout 底部
    private LinearLayout newOrderInfoLayout;
    private TextView tvNewOrderCommodityAmount; //商品金额
    private TextView tvNewOrderDistributionFeePrice; // 配送费
    private TextView tvNewOrderVipSubtractionPrice; //会员满减
    private TextView tvNewOrderCoupons;//优惠卷
    private TextView tvNewOrderCommodity;  //商品数量
    private TextView tvNewOrderPayMoney; // 商品总价
    private TextView tvnormalReduct; // 满减活动

    ////退货订单信息layout 底部
    private LinearLayout returnGoodsInfoLayout;
    private TextView returnGoodsCommodityNum;//退货数量
    private TextView returnGoodsCommodityAmount; // 退货金额
    //private TextView returnGoodsCommodity; //退货总件商品
    private TextView returnGoodsCommodityPayMoney; // 退货商品总价
    private TextView returnGoodsReason; //退款原因


    //以下是每个订单详情都有的订单信息
    private TextView tvNewOrderAddresseeName;
    private TextView tvNewOrderAddress;
    private TextView tvNewOrderPhone; //下单号码
    private TextView tvNewOrderTime;//下单时间
    private ImageView tvNewOrderPay; //下单方式
    private TextView tvNewOrderRemarks; //备注
    //付款时间
    private LinearLayout tvNewOrderPayTimeLinearLayout;
    private TextView tvNewOrderPayTime;
    //申请退货时间
    private LinearLayout newOrderReturnTimeLinearLayout;
    private TextView tvNewOrderReturnTime;
    //审核时间
    private TextView tvExamineTime;
    private LinearLayout examineTimeLinearLayout;
    //下单时间
    private LinearLayout tvNewOrderTimeLinearLayout;
    private GradientColorTextView tvInfo;

    private LinearLayout linearLayoutPay;
    //删除订单
    private LinearLayout deleteButtonLayout;
    private TextView tv_delete;
    private TextView tv_buttonAgainBuy;


    private NewOrderDetailAdapter adapter;
    private List<GetOrderDetailModel.DataBean.ProductVOListBean> list = new ArrayList<>();

    private String returnProductMainId = "";
    private String orderId;
    private String orderState = "";
    private int orderStatusRequest;
    private Dialog mDialog;

    private List<OrderEvaluateListModel> mListEvaluate = new ArrayList<>();
    private List<GetOrderDetailModel.DataBean.ProductVOListBean> mListForReturn = new ArrayList<>();
    private GetOrderDetailModel.DataBean getOrderDetailModel;
    private ConfirmGetGoodsModel mModelConfirmGetGoods;
    private TextView tvNewOrderPayMoneyReturn;
    private RelativeLayout relativeLayoutCommodityReturn;
    private TextView tvReturnGoodsCommodity;

    private TextView mTvDeliverTime;
    private LinearLayout mLinearLayoutDeliver;
    private LinearLayout linearLayoutAddressArrow;
    private TextView sendAmountStr;
    private TextView tvDeductDsc;
    private TextView tvNomalReductDesc;

    private TextView tvCopy;

    private LinearLayout mLinearLayoutShipped;

    private LinearLayout mLinearLayoutEvalute;
    private LinearLayout mLinearLayoutReciverOrder;
    //复制订单
    private TextView tvCopyOrderOne;
    private TextView tvCopyOrderTwo;
    private TextView tvCopyOrderThree;

    private TextView mTvConfirmOrder;
    //物流信息
    private RelativeLayout mRelativeDriver;
    private TextView mTvDriverDeliverTime;
    private ImageView mFreshDriverStatus;
    private TextView mTvOrderStatus;
    private TextView mTvSeeDriverStatus;
    private TextView mTvDriverName;
    private TextView mTvOrderReturn;

    private RelativeLayout mRlReturn;
    private TextView tv_return_amount;
    private TextView tv_return_reason;

    private TextView tv_return_status;
    private TextView tv_driver_phone;
    private TextView tv_normal_vip_desc;
    private LoadingDailog dialog;
    private TextView buttonReturnGood_two;


    private TextView et_name;
    private TextView et_phone;

    BaiduMap mBaiduMap;
    private TextureMapView mMapView;
    private GeoCoder mCoder;
    double latitude1;//仓库位置
    double longitude1;

    double latitude2;//用户位置
    double longitude2;
    private TextView tv_address;


    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private UiSettings mUiSettings;


    private BottomSheetDialog mDialogMap;
    private String title;
    private String content;


    private LinearLayout iv_time_arrow;
    private TextView tv_year;
    private TextView tv_hour;


    private String mYear;
    private String mHour;
    private TextView et_time;
    private String deliverTimeStart = "";
    private String deliverTimeEnd = "";

    private String deliverTimeName = "";

    private TextView address;

    private LinearLayout ll_activity_order_address;
    private LinearLayout ll_one;
    private LinearLayout ll_two;


    private TextView tv_phone;

    private LinearLayout ll_deliver;


    //地球半径
    private static final double EARTH_RADIUS = 6378.137;
    private String goAccount;

    private TextView tv_evaluate;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_self_sufficiency_order_detail);
    }

    @Override
    public void findViewById() {
        imageViewBreak = (ImageView) findViewById(R.id.imageViewBreak);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        orderLinearLayout = (LinearLayout) findViewById(R.id.orderLinearLayout);
        tvOrderTitle = (TextView) findViewById(R.id.tvOrderTitle);
        orderTimerView = (SnapUpCountDownTimerView) findViewById(R.id.orderTimerView);
        tvOrderContent = (TextView) findViewById(R.id.tvOrderContent);
        threeButtonLayout = (LinearLayout) findViewById(R.id.threeButtonLayout);
        buttonReturnGoods = (TextView) findViewById(R.id.buttonReturnGoods);
        buttonEvaluate = (TextView) findViewById(R.id.buttonEvaluate);
        buttonAgainBuy = (TextView) findViewById(R.id.buttonAgainBuy);
        twoButtonLayout = (LinearLayout) findViewById(R.id.twoButtonLayout);
        buttonCancelOrder = (TextView) findViewById(R.id.buttonCancelOrder);
        buttonGOPay = (TextView) findViewById(R.id.buttonGOPay);
        ReturnGoodsLinearLayout = (LinearLayout) findViewById(R.id.ReturnGoodsLinearLayout);
        ReturnGoodsTitle = (TextView) findViewById(R.id.ReturnGoodsTitle);
        ReturnGoodsContent = (TextView) findViewById(R.id.ReturnGoodsContent);
        tvNewOrderCommodityNum = (TextView) findViewById(R.id.tvNewOrderCommodityNum);

        newOrderInfoLayout = (LinearLayout) findViewById(R.id.NewOrderInfoLayout);
        tvNewOrderCommodityAmount = (TextView) findViewById(R.id.tvNewOrderCommodityAmount);
        tvNewOrderDistributionFeePrice = (TextView) findViewById(R.id.tvNewOrderDistributionFeePrice);
        tvNewOrderVipSubtractionPrice = (TextView) findViewById(R.id.tvNewOrderVipSubtractionPrice);
        tvNewOrderCoupons = (TextView) findViewById(R.id.tvNewOrderCoupons);
        tvNewOrderCommodity = (TextView) findViewById(R.id.tvNewOrderCommodity);
        tvNewOrderPayMoney = (TextView) findViewById(R.id.tvNewOrderPayMoney);
        tvnormalReduct = (TextView) findViewById(R.id.tvnormalReduct);
        mTvOrderReturn = findViewById(R.id.tv_order_return);

        returnGoodsInfoLayout = (LinearLayout) findViewById(R.id.returnGoodsInfoLayout);
        returnGoodsCommodityNum = (TextView) findViewById(R.id.returnGoodsCommodityNum);
        returnGoodsCommodityAmount = (TextView) findViewById(R.id.returnGoodsCommodityAmount);
        returnGoodsCommodityPayMoney = (TextView) findViewById(R.id.tvNewOrderPayMoneyReturn);
        returnGoodsReason = (TextView) findViewById(R.id.returnGoodsReason);

        tvNewOrderPhone = (TextView) findViewById(R.id.tvNewOrderPhone);
        tvNewOrderTime = (TextView) findViewById(R.id.tvNewOrderTime);
        tvNewOrderPay = (ImageView) findViewById(R.id.tvNewOrderPay);
        tvNewOrderRemarks = (TextView) findViewById(R.id.tvNewOrderRemarks);

        tvNewOrderAddresseeName = (TextView) findViewById(R.id.tvNewOrderAddresseeName);
        tvNewOrderAddress = (TextView) findViewById(R.id.tvNewOrderAddress);
        tvNewOrderPayTimeLinearLayout = (LinearLayout) findViewById(R.id.tvNewOrderPayTimeLinearLayout);
        tvNewOrderPayTime = (TextView) findViewById(R.id.tvNewOrderPayTime);
        newOrderReturnTimeLinearLayout = (LinearLayout) findViewById(R.id.newOrderReturnTimeLinearLayout);
        tvNewOrderReturnTime = (TextView) findViewById(R.id.tvNewOrderReturnTime);
        tvExamineTime = (TextView) findViewById(R.id.tvExamineTime);
        examineTimeLinearLayout = (LinearLayout) findViewById(R.id.examineTimeLinearLayout);
        tvNewOrderTimeLinearLayout = (LinearLayout) findViewById(R.id.tvNewOrderTimeLinearLayout);
        tvInfo = (GradientColorTextView) findViewById(R.id.tvInfo);
        linearLayoutPay = (LinearLayout) findViewById(R.id.linearLayoutPay);
        tvNewOrderPayMoneyReturn = (TextView) findViewById(R.id.tvNewOrderPayMoneyReturn);
        relativeLayoutCommodityReturn = (RelativeLayout) findViewById(R.id.relativeLayoutCommodityReturn);
        tvReturnGoodsCommodity = (TextView) findViewById(R.id.tvReturnGoodsCommodity);
        //删除订单
        deleteButtonLayout = (LinearLayout) findViewById(R.id.deleteButtonLayout);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_buttonAgainBuy = (TextView) findViewById(R.id.tv_buttonAgainBuy);
        mTvDeliverTime = findViewById(R.id.tv_deliver_time);
        mLinearLayoutDeliver = findViewById(R.id.deliver_linearLayout);
        linearLayoutAddressArrow = findViewById(R.id.linearLayout_address_arrow);
        sendAmountStr = findViewById(R.id.tv_send_amount_str);
        tvDeductDsc = findViewById(R.id.tv_deduct_desc);
        tvNomalReductDesc = findViewById(R.id.tv_normal_reduct_desc);

        tvCopy = findViewById(R.id.tv_copy_order_num);
        mLinearLayoutShipped = findViewById(R.id.linearLayout_shipped);
        mLinearLayoutEvalute = findViewById(R.id.linearLayout_evalute);
        mLinearLayoutReciverOrder = findViewById(R.id.linearLayout_get_order);
        tvCopyOrderOne = findViewById(R.id.copy_order);
        tvCopyOrderTwo = findViewById(R.id.tv_get_order_copy);
        tvCopyOrderThree = findViewById(R.id.tv_copy_order);
        mTvConfirmOrder = findViewById(R.id.tv_confirm_order);
        mRelativeDriver = findViewById(R.id.relativeLayout_driver);
        mTvDriverDeliverTime = findViewById(R.id.tv_driver_content);
        mFreshDriverStatus = findViewById(R.id.iv_fresh_status);
        mTvOrderStatus = findViewById(R.id.tv_order_status);
        mTvSeeDriverStatus = findViewById(R.id.tv_order_driver_message);
        mTvDriverName = findViewById(R.id.tv_driver_name);

        mRlReturn = findViewById(R.id.rl_return);
        tv_return_amount = findViewById(R.id.tv_return_amount);
        tv_return_reason = findViewById(R.id.tv_return_reason);
        tv_driver_phone = findViewById(R.id.tv_driver_phone);
        tv_normal_vip_desc = findViewById(R.id.tv_normal_vip_desc);
        buttonReturnGood_two = findViewById(R.id.buttonReturnGood_two);
        et_name = (TextView) findViewById(R.id.et_name);
        et_phone = (TextView) findViewById(R.id.et_phone);
        tv_address = (TextView) findViewById(R.id.tv_address);
        //获取地图控件引用
        mMapView = (TextureMapView) findViewById(R.id.bmapView);
        iv_time_arrow = (LinearLayout) findViewById(R.id.iv_time_arrow);
        tv_year = (TextView) findViewById(R.id.tv_year);
        tv_hour = (TextView) findViewById(R.id.tv_hour);
        et_time = (TextView) findViewById(R.id.et_time);


        address = (TextView) findViewById(R.id.address);
        ll_activity_order_address = findViewById(R.id.ll_activity_order_address);
        ll_one = findViewById(R.id.ll_one);
        ll_two = findViewById(R.id.ll_two);
        tv_phone = findViewById(R.id.tv_phone);
        ll_deliver = findViewById(R.id.ll_deliver);
        tv_evaluate = findViewById(R.id.tv_evaluate);
    }


    @Override
    public void setViewData() {

        // setTranslucentStatus();
        EventBus.getDefault().register(this);

        mLinearLayoutDeliver.setVisibility(View.GONE);
        newOrderReturnTimeLinearLayout.setVisibility(View.GONE);
        examineTimeLinearLayout.setVisibility(View.GONE);
        tvNewOrderPayTimeLinearLayout.setVisibility(View.GONE);
        ll_deliver.setVisibility(View.GONE);


        orderId = getIntent().getStringExtra(AppConstant.ORDERID);
        orderState = getIntent().getStringExtra(AppConstant.ORDERSTATE);
        returnProductMainId = getIntent().getStringExtra(AppConstant.RETURNPRODUCTMAINID);

        goAccount = getIntent().getStringExtra("goAccount");

        et_time.setVisibility(View.GONE);
        tv_hour.setVisibility(View.VISIBLE);
        tv_year.setVisibility(View.VISIBLE);
        mBaiduMap = mMapView.getMap();
//普通地图 ,mBaiduMap是地图控制器对象
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //默认显示地图标注
        //  mBaiduMap.showMapPoi(false);

        //通过设置enable为true或false 选择是否显示比例尺
        mMapView.showScaleControl(false);

        //通过设置enable为true或false 选择是否显示缩放按钮
        mMapView.showZoomControls(false);

        mUiSettings = mBaiduMap.getUiSettings();
        //通过设置enable为true或false 选择是否禁用所有手势
        mUiSettings.setAllGesturesEnabled(false);


        iv_time_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showGetTime();
            }
        });

        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGetTime();
            }
        });

        //未支付，15分钟后跳转到取消订单
        orderTimerView.setTimeout(new SnapUpCountDownTimerView.Timeout() {
            @Override
            public void getStop() {
                if (orderStatusRequest == 1) {
                    cancelOrder(orderId);
                }


            }
        });

        adapter = new NewOrderDetailAdapter(R.layout.new_order_detail, list,orderId);
      /*  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }


        };
    recyclerView.setLayoutManager(linearLayoutManager);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setAdapter(adapter);
        getOrderDetail(orderId, orderState, returnProductMainId);
        // 文字渐变色
        LinearGradient mLinearGradient = new LinearGradient(0, 0, 0, tvInfo.getPaint().getTextSize(), Color.parseColor("#CEA6FF")
                , Color.parseColor("#6F81FF"), Shader.TileMode.CLAMP);
        tvInfo.getPaint().setShader(mLinearGradient);
    }

    @Override
    public void setClickEvent() {
        imageViewBreak.setOnClickListener(noDoubleClickListener);
        buttonCancelOrder.setOnClickListener(noDoubleClickListener);
        buttonGOPay.setOnClickListener(noDoubleClickListener);
        buttonReturnGoods.setOnClickListener(noDoubleClickListener);
        buttonEvaluate.setOnClickListener(noDoubleClickListener);
        buttonAgainBuy.setOnClickListener(noDoubleClickListener);
        tv_delete.setOnClickListener(noDoubleClickListener);
        tv_buttonAgainBuy.setOnClickListener(noDoubleClickListener);
        linearLayoutAddressArrow.setOnClickListener(noDoubleClickListener);
        tvCopy.setOnClickListener(noDoubleClickListener);

        tvCopyOrderOne.setOnClickListener(noDoubleClickListener);
        tvCopyOrderTwo.setOnClickListener(noDoubleClickListener);
        tvCopyOrderThree.setOnClickListener(noDoubleClickListener);
        mTvConfirmOrder.setOnClickListener(noDoubleClickListener);
        mFreshDriverStatus.setOnClickListener(noDoubleClickListener);
        mTvOrderReturn.setOnClickListener(noDoubleClickListener);
        mTvSeeDriverStatus.setOnClickListener(noDoubleClickListener);
        buttonReturnGood_two.setOnClickListener(noDoubleClickListener);

        et_phone.setOnClickListener(noDoubleClickListener);
        et_name.setOnClickListener(noDoubleClickListener);
        ll_one.setOnClickListener(noDoubleClickListener);
        ll_two.setOnClickListener(noDoubleClickListener);
        tv_evaluate.setOnClickListener(noDoubleClickListener);


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
    BaiduMap.OnMapClickListener listenerClick = new BaiduMap.OnMapClickListener() {
        /**
         * 地图单击事件回调函数
         *
         * @param point 点击的地理坐标
         */
        @Override
        public void onMapClick(LatLng point) {
            Log.i("dwqrqr", "onMapClick: " + "我点击了");
            showMapDialog();
        }

        /**
         * 地图内 Poi 单击事件回调函数
         *
         * @param mapPoi 点击的 poi 信息
         */
        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            return false;
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
                .fromResource(R.mipmap.ic_confirm_map);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(cenpt)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        LatLng cenpt1 = new LatLng(120.08947, 30.397751);

        OverlayOptions position = new MarkerOptions().position(cenpt1);

        LatLng cenpt2 = new LatLng(120.126731, 30.336927);

        //用来构造InfoWindow的Button
        TextView button = new TextView(mActivity.getApplicationContext());

        if (latitude2 > 0 || longitude2 > 0) {
            if (latitude2 != 4.9E-324 || longitude2 != 4.9E-324) {
                button.setVisibility(View.VISIBLE);
                button.setBackgroundResource(R.drawable.popup_map);
                button.setTextColor(Color.parseColor("#FF666666"));
                button.setGravity(Gravity.CENTER_HORIZONTAL);
                if (getDistance(longitude1, latitude1, longitude2, latitude2) > 1000) {
                    long round = Math.round(getDistance(longitude1, latitude1, longitude2, latitude2) / 1000);
                    button.setText("距您" + String.valueOf(round) + "公里");
                } else {
                    button.setText("距您不到1公里");
                }


                //构造InfoWindow
//point 描述的位置点
//-100 InfoWindow相对于point在y轴的偏移量

                InfoWindow mInfoWindow = new InfoWindow(button, ((MarkerOptions) option).getPosition(), -50);

//使InfoWindow生效
                mBaiduMap.showInfoWindow(mInfoWindow);
            } else {
                button.setVisibility(View.GONE);

            }
        } else {
            button.setVisibility(View.GONE);
        }




 /*       //用来构造InfoWindow
        BitmapDescriptor mBitmap = BitmapDescriptorFactory.fromResource(R.drawable.circle_bg);

//响应点击的OnInfoWindowClickListener
        InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick() {
                Toast.makeText(mActivity, "Click on InfoWindow", Toast.LENGTH_LONG).show();
            }
        };

//构造InfoWindow
//point 描述的位置点
//-100 InfoWindow相对于point在y轴的偏移量
      InfoWindow  mInfoWindow = new InfoWindow(mBitmap, cenpt1, -100, listener);

//使InfoWindow生效
        mBaiduMap.showInfoWindow(mInfoWindow);*/
    }


    /**
     * 根据经纬度查询距离
     *
     * @param lng1 经度
     * @param lat1 纬度
     * @param lng2 经度
     * @param lat2 纬度
     * @return
     */
    private static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS * 1000;
//	   s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mMapView.onResume();


        mLocationClient = new LocationClient(mActivity.getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true
//设置地图单击事件监听
        mBaiduMap.setOnMapClickListener(listenerClick);

        mLocationClient.setLocOption(option);
        mLocationClient.start();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);

        mCoder = GeoCoder.newInstance();
        list.clear();

        getOrderDetail(orderId, orderState, returnProductMainId);
        mCoder.setOnGetGeoCodeResultListener(listener);


    }

//    private void initOptionPicker() {//条件选择器初始化
//
//        /**
//         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
//         */
//
//        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
////                String tx = options1Items.get(options1).getPickerViewText()
////                        + options2Items.get(options1).get(options2)
////                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
////                btn_Options.setText(tx);
//            }
//        })
//                .setTitleText("请选择自提时间段")
//                .setTitleColor(Color.parseColor("#F56D23"))
//
//                .setCancelColor(Color.parseColor("#F56D23"))
//                .setSubmitColor(Color.parseColor("#F56D23"))
//                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
//                    @Override
//                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
//                    }
//                })
//                .build();
//
////        pvOptions.setSelectOptions(1,1);
//        /*pvOptions.setPicker(options1Items);//一级选择器*/
////        pvOptions.setPicker(options1Items, options2Items);//二级选择器
////        pvOptions.show();
//        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
//    }

    private GetTimeOrderModel dataBean;

    private void showGetTime() {


        GetOrderDeliverTimeAPI.requestOrderSelfTime(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetTimeOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetTimeOrderModel getTimeOrderModel) {
                        List<String> listYear = new ArrayList<>();

                        List<List<String>> listTimer = new ArrayList<>();
                        if (getTimeOrderModel.isSuccess()) {
                            dataBean = getTimeOrderModel;

                            for (int i = 0; i < getTimeOrderModel.getData().size(); i++) {
                                listYear.add(getTimeOrderModel.getData().get(i).getDateTime());
                            }
                            Log.i("aaaadqee", "onNext: " + listYear.size());


                            for (int i = 0; i < getTimeOrderModel.getData().size(); i++) {
                                List<String> listTime = new ArrayList<>();
                                for (int j = 0; j < getTimeOrderModel.getData().get(i).getDetailTime().size(); j++) {
                                    listTime.add(getTimeOrderModel.getData().get(i).getDetailTime().get(j).getName() + "(" + getTimeOrderModel.getData().get(i).getDetailTime().get(j).getStartTime() + "-" + getTimeOrderModel.getData().get(i).getDetailTime().get(j).getEndTime() + ")");

                                }
                                listTimer.add(listTime);
                            }

                            PickCityUtil.showDoublePickView(mActivity, listYear, listTimer, "请选择自提时间段", new PickCityUtil.ChooseDPositionListener() {
                                @Override
                                public void choosePosition(int position1, int position2, String s) {
                                    et_time.setVisibility(View.GONE);
                                    tv_hour.setVisibility(View.VISIBLE);
                                    tv_year.setVisibility(View.VISIBLE);
                                    mYear = listYear.get(position1);


                                    // tv_year.setText(mYear);
                                    //  deliverTimeStart = dataBean.getData().get(position1).getDetailTime().get(position2).getStartTime();
                                    //  deliverTimeEnd = dataBean.getData().get(position1).getDetailTime().get(position2).getEndTime();
                                    //   deliverTimeName = dataBean.getData().get(position1).getDetailTime().get(position2).getName();
                                    //    tv_hour.setText(dataBean.getData().get(position1).getDetailTime().get(position2).getStartTime() + "-" + dataBean.getData().get(position1).getDetailTime().get(position2).getEndTime());

                                    PostModifyMesAPI.requestModifyMsg(mContext, orderId, et_name.getText().toString(), et_phone.getText().toString(), listYear.get(position1), dataBean.getData().get(position1).getDetailTime().get(position2).getStartTime(), dataBean.getData().get(position1).getDetailTime().get(position2).getEndTime(), dataBean.getData().get(position1).getDetailTime().get(position2).getName())
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Subscriber<BaseModel>() {
                                                @Override
                                                public void onCompleted() {

                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }

                                                @Override
                                                public void onNext(BaseModel baseModel) {
                                                    if (baseModel.success) {

                                                        tv_year.setText(mYear);
                                                        deliverTimeStart = dataBean.getData().get(position1).getDetailTime().get(position2).getStartTime();
                                                        deliverTimeEnd = dataBean.getData().get(position1).getDetailTime().get(position2).getEndTime();
                                                        deliverTimeName = dataBean.getData().get(position1).getDetailTime().get(position2).getName();
                                                        tv_hour.setText(dataBean.getData().get(position1).getDetailTime().get(position2).getStartTime() + "-" + dataBean.getData().get(position1).getDetailTime().get(position2).getEndTime());

                                                    } else {
                                                        AppHelper.showMsg(mContext, baseModel.message);
                                                    }

                                                }
                                            });
                                }
                            });
                        }
                    }
                });


        List<String> list1 = new ArrayList<>();
        list1.clear();
        list1.add("AAA");
        list1.add("BBB");
        list1.add("CCC");
        list1.add("DDD");

        List<String> list2 = new ArrayList<>();
        list2.clear();
        list2.add("aaa");
        list2.add("bbb");
        list2.add("ccc");
        list2.add("ddd");

        List<List<String>> list = new ArrayList<>();
        list.clear();
        for (int i = 0; i < list1.size(); i++) {
            list.add(list2);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backEvent();
            EventBus.getDefault().post(new BackEvent());
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Subscribe
    public void onEventMainThread(AddressEvent event) {

        list.clear();
        getOrderDetail(orderId, orderState, returnProductMainId);

    }

    private void getOrderDetailDialog(String orderId, String orderState, String returnProductMainId) {
        GetOrderDetailAPI.requestData(mContext, orderId, orderState, returnProductMainId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetOrderDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetOrderDetailModel orderDetailModel) {

                        if (orderDetailModel.success) {
                            dialog.dismiss();
                            if (orderDetailModel != null) {
                                setText(orderDetailModel);
                                if (orderDetailModel.data.productVOList.size() > 0) {
                                    // Log.e(TAG, "onNext: " + orderDetailModel.data.productVOList.get(0).productDescVOList.get(0).newDesc);
                                    list.clear();
                                    list.addAll(orderDetailModel.data.productVOList);
                                }
                            }
                            adapter.notifyDataSetChanged();

                        } else {
                            AppHelper.showMsg(mContext, orderDetailModel.message);
                        }
                    }
                });
    }

    private GetOrderDetailModel mDetailModel;

    //获取订单详情
    private void getOrderDetail(String orderId, String orderState, String returnProductMainId) {
        GetOrderDetailAPI.requestData(mContext, orderId, orderState, returnProductMainId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetOrderDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetOrderDetailModel orderDetailModel) {

                        if (orderDetailModel.success) {
                            mDetailModel = orderDetailModel;
                            title = orderDetailModel.data.wareAddress;
                            content = orderDetailModel.data.wareAddress;
                            if (orderDetailModel != null) {
                                setText(orderDetailModel);
                                mCoder.geocode(new GeoCodeOption()
                                        .city("杭州")
                                        .address(orderDetailModel.data.wareAddress));

                                tv_year.setText(orderDetailModel.data.sendStartTime);
                                tv_hour.setText(orderDetailModel.data.deliverTimeStart + "-" + orderDetailModel.data.deliverTimeEnd);
                                et_name.setText(orderDetailModel.data.pickUserName);
                                et_phone.setText(orderDetailModel.data.pickPhone);
                                address.setText(orderDetailModel.data.wareName);
                                tv_address.setText(orderDetailModel.data.wareAddress);

                                if (orderDetailModel.data.productVOList.size() > 0) {
                                    //  Log.e(TAG, "onNext: " + orderDetailModel.data.productVOList.get(0).productDescVOList.get(0).newDesc);
                                    list.clear();
                                    list.addAll(orderDetailModel.data.productVOList);
                                }
                            }
                            adapter.notifyDataSetChanged();

                        } else {
                            AppHelper.showMsg(mContext, orderDetailModel.message);
                        }
                    }
                });
    }

    //设置文字
    private void setText(GetOrderDetailModel info) {

        getOrderDetailModel = info.data;
        orderStatusRequest = getOrderDetailModel.orderStatus;
        setViewShow();
        tvOrderTitle.setText(getOrderDetailModel.orderStatusName);
        ReturnGoodsTitle.setText(getOrderDetailModel.orderStatusName);

        tvNewOrderCommodityNum.setText("共" + getOrderDetailModel.totalNum + "件商品");
        tvNewOrderCommodity.setText("实付金额");

        tvNewOrderCommodityAmount.setText("￥" + getOrderDetailModel.prodAmount);

        tvNewOrderVipSubtractionPrice.setText("￥" + getOrderDetailModel.vipReductDesc);
        tvNewOrderPayMoney.setText("￥" + getOrderDetailModel.totalAmount);
        tvNewOrderDistributionFeePrice.setText("￥" + getOrderDetailModel.deliveryFee);
        tvnormalReduct.setText("￥" + getOrderDetailModel.normalReduct);

        tvNewOrderCoupons.setText("￥" + getOrderDetailModel.offerAmount + "");
        returnGoodsCommodityNum.setText(getOrderDetailModel.applyReturnDate);
        returnGoodsReason.setText(getOrderDetailModel.returnReson);

        tv_phone.setText(info.data.customerPhone);
        //  returnGoodsCommodityPayMoney.setText();
        tv_return_amount.setText("￥" + getOrderDetailModel.actuallyReturn);
        returnGoodsCommodityAmount.setText(getOrderDetailModel.checkDate);
        tv_return_reason.setText(getOrderDetailModel.returnReson);
        //   tvNewOrderPayMoneyReturn.setText("￥" + getOrderDetailModel.actuallyReturn);
        // tvReturnGoodsCommodity.setText("共" + getOrderDetailModel.returnProductNum + "件商品");
        sendAmountStr.setText(getOrderDetailModel.sendAmountStr);
        tvNewOrderPhone.setText(getOrderDetailModel.orderId);
        tvNewOrderTime.setText(getOrderDetailModel.gmtCreate);
        tv_normal_vip_desc.setText(getOrderDetailModel.vipReductDesc);

        tvDeductDsc.setText(getOrderDetailModel.deductDesc);

        tvNomalReductDesc.setText(getOrderDetailModel.normalReductDesc);
    /*    if (getOrderDetailModel.deliverTimeStart == null) {
            mLinearLayoutDeliver.setVisibility(View.GONE);
        } else {
            mLinearLayoutDeliver.setVisibility(View.VISIBLE);
            mTvDeliverTime.setText(getOrderDetailModel.deliverTimeName + "  " + getOrderDetailModel.deliverTimeStart + " - " + getOrderDetailModel.deliverTimeEnd);
        }*/

        mTvDriverName.setText(getOrderDetailModel.driverName);
        tv_driver_phone.setText(getOrderDetailModel.driverPhone);
        tvNewOrderAddresseeName.setText(getOrderDetailModel.addressVO.userName + "    "
                + getOrderDetailModel.addressVO.contactPhone);
        tvNewOrderAddress.setText(getOrderDetailModel.addressVO.provinceName + getOrderDetailModel.addressVO.cityName
                + getOrderDetailModel.addressVO.areaName + getOrderDetailModel.addressVO.detailAddress);
        tvNewOrderVipSubtractionPrice.setText(" ￥ " + getOrderDetailModel.vipReduct);
        tvNewOrderRemarks.setText(getOrderDetailModel.remark);
        //  examineTimeLinearLayout.setVisibility(orderStatusRequest == 11 ? View.VISIBLE : View.GONE);
        if (getOrderDetailModel.payChannelType == 1) { //余额支付"=1 , 支付宝=2 微信支付=3
            tvNewOrderPay.setImageResource(R.mipmap.ic_balance_pay);
        } else if (getOrderDetailModel.payChannelType == 2) {
            tvNewOrderPay.setImageResource(R.mipmap.ic_pay_alipay);
        } else if (getOrderDetailModel.payChannelType == 3) {
            tvNewOrderPay.setImageResource(R.mipmap.ic_we_chat_icon);
        }


        if (orderStatusRequest == 2) {
            mTvDriverDeliverTime.setText(getOrderDetailModel.payDate);
        } else if (orderStatusRequest == 14) {
            mTvDriverDeliverTime.setText(getOrderDetailModel.waitSendReceiveTime);
        } else if (orderStatusRequest == 3) {
            mTvDriverDeliverTime.setText(getOrderDetailModel.confirmDate);
        }

        if (orderStatusRequest == 2) {
            mTvOrderStatus.setText("等待接收订单");
        } else if (orderStatusRequest == 14) {
            mTvOrderStatus.setText("订单已接收");
        } else if (orderStatusRequest == 3) {
            mTvOrderStatus.setText("装车完成-已发货");
        }


        //倒计时设置
        orderTimerView.SnapUpCountDownTimerViewType(mContext, 1);
        orderTimerView.setBackTheme(true);
        orderTimerView.setTime(true, getOrderDetailModel.sysCurrentTime, getOrderDetailModel.orderOverTime, 0);
        orderTimerView.changeTypeColor(Color.WHITE);
        orderTimerView.start();
     /*   if (!TextUtils.isEmpty(getOrderDetailModel.payDate)) {// 付款时间
            tvNewOrderPayTime.setText(getOrderDetailModel.payDate);
            tvNewOrderPayTimeLinearLayout.setVisibility(View.VISIBLE);
        } else {
            tvNewOrderPayTimeLinearLayout.setVisibility(View.GONE);
        }*/
   /*     if (!TextUtils.isEmpty(getOrderDetailModel.confirmDate)) {
            tvNewOrderReturnTime.setText(getOrderDetailModel.confirmDate);
            newOrderReturnTimeLinearLayout.setVisibility(View.VISIBLE);
        } else {
            newOrderReturnTimeLinearLayout.setVisibility(View.GONE);
        }*/
  /*      if (!TextUtils.isEmpty(getOrderDetailModel.finishDate)) {
            tvExamineTime.setText(getOrderDetailModel.finishDate);
            examineTimeLinearLayout.setVisibility(View.VISIBLE);
        } else {
            examineTimeLinearLayout.setVisibility(View.GONE);

        }*/

    }

    //返回上个页面
    private void backEvent() {


        if (goAccount != null && StringHelper.notEmptyAndNull(goAccount)) {
            finish();
        } else {
            if (orderStatusRequest == 1) {
                //待付款
                Intent intent = new Intent(mContext, MyOrdersActivity.class);
                intent.putExtra("type", AppConstant.PAYMENT);
                intent.putExtra("orderDeliveryType", 1);
                startActivity(intent);
                //   UserInfoHelper.saveDeliverType(mContext,"1");
                //   startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.PAYMENT));
                overridePendingTransition(R.anim.slide_in_from_right,
                        R.anim.slide_out_from_left);
            } else if (orderStatusRequest == 2) {
                //待发货
                Intent intent = new Intent(mContext, MyOrdersActivity.class);
                intent.putExtra("type", AppConstant.DELIVERY);
                intent.putExtra("orderDeliveryType", 1);
                startActivity(intent);
                //  UserInfoHelper.saveDeliverType(mContext,"1");
                //  startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.DELIVERY));
                overridePendingTransition(R.anim.slide_in_from_right,
                        R.anim.slide_out_from_left);
            } /*else if (orderStatusRequest == 3) {
            //待收货
            startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.RECEIVED));
            overridePendingTransition(R.anim.slide_in_from_right,
                    R.anim.slide_out_from_left);
        }*/ else if (orderStatusRequest == 4) {
                //已完成
                Intent intent = new Intent(mContext, MyOrdersActivity.class);
                intent.putExtra("type", AppConstant.ALL);
                intent.putExtra("orderDeliveryType", 1);
                startActivity(intent);
                // UserInfoHelper.saveDeliverType(mContext,"1");
                //  startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
                overridePendingTransition(R.anim.slide_in_from_right,
                        R.anim.slide_out_from_left);
            } else if (orderStatusRequest == 5) {
                //待评价
                Intent intent = new Intent(mContext, MyOrdersActivity.class);
                intent.putExtra("type", AppConstant.EVALUATED);
                intent.putExtra("orderDeliveryType", 1);
                startActivity(intent);
                //  UserInfoHelper.saveDeliverType(mContext,"1");
                //  startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.EVALUATED));
                overridePendingTransition(R.anim.slide_in_from_right,
                        R.anim.slide_out_from_left);
            } else if (orderStatusRequest == 6) {
                //已评价
                Intent intent = new Intent(mContext, MyOrdersActivity.class);
                intent.putExtra("type", AppConstant.ALL);
                intent.putExtra("orderDeliveryType", 1);
                startActivity(intent);
                //   UserInfoHelper.saveDeliverType(mContext,"1");
                //  startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
                overridePendingTransition(R.anim.slide_in_from_right,
                        R.anim.slide_out_from_left);
            } else if (orderStatusRequest == 7) {
                //已取消(订单关闭)
                Intent intent = new Intent(mContext, MyOrdersActivity.class);
                intent.putExtra("type", AppConstant.ALL);
                intent.putExtra("orderDeliveryType", 1);
                startActivity(intent);
                // UserInfoHelper.saveDeliverType(mContext,"1");
                //  startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
                overridePendingTransition(R.anim.slide_in_from_right,
                        R.anim.slide_out_from_left);
            } else if (orderStatusRequest == 11) {
                //退货
                Intent intent = new Intent(mContext, MyOrdersActivity.class);
                intent.putExtra("type", AppConstant.RETURN);
                intent.putExtra("orderDeliveryType", 1);
                startActivity(intent);
                //  UserInfoHelper.saveDeliverType(mContext,"1");
                // startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.RETURN));
                overridePendingTransition(R.anim.slide_in_from_right,
                        R.anim.slide_out_from_left);
            }
            finish();
        }
    }

    // 不同的状态显示不同的layout

    /**
     * 订单类型：("待付款", 1), ("待发货-待接收", 2), ("待发货-已接收", 14), ("待收货", 3), ("已完成", 4), ("待评价", 5), ("已评价", 6), ("已取消", 7), ("退货", 11),
     */
    private void setViewShow() {
//评价
        mLinearLayoutEvalute.setVisibility(orderStatusRequest == 5 || orderStatusRequest == 6 ? View.VISIBLE : View.GONE);
        // 判是否出现退货的  orderStatusRequest=11头部 layout
     //   textViewTitle.setText(orderStatusRequest == 11 ? "退货订单" : " 订单详情");
        //查看评价
        tv_evaluate.setVisibility(orderStatusRequest == 6 ? View.VISIBLE : View.GONE);
        buttonEvaluate.setVisibility(orderStatusRequest==5?View.VISIBLE:View.GONE);
        ReturnGoodsLinearLayout.setVisibility(orderStatusRequest == 11 ? View.VISIBLE : View.GONE);
        orderLinearLayout.setVisibility(orderStatusRequest == 11 ? View.GONE : View.VISIBLE);
        // 判是否出现退货的 底部 layout
     //   returnGoodsInfoLayout.setVisibility(orderStatusRequest == 11 ? View.VISIBLE : View.GONE);
        // newOrderInfoLayout.setVisibility(orderStatusRequest == 11 ? View.GONE : View.VISIBLE);

        //待发货待接受
        //  mLinearLayoutShipped.setVisibility(orderStatusRequest == 2 ? View.VISIBLE : View.GONE);
        //待发货 已接收
        // mLinearLayoutReciverOrder.setVisibility(orderStatusRequest == 14 ? View.VISIBLE : View.GONE);
        //待提货
        threeButtonLayout.setVisibility(orderStatusRequest == 2 ? View.VISIBLE : View.GONE);
//提货地图
        ll_activity_order_address.setVisibility(orderStatusRequest == 1 || orderStatusRequest == 2 ? View.VISIBLE : View.GONE);
        ll_one.setVisibility(orderStatusRequest == 1 || orderStatusRequest == 2 ? View.VISIBLE : View.GONE);
        ll_two.setVisibility(orderStatusRequest == 1 || orderStatusRequest == 2 ? View.VISIBLE : View.GONE);
        iv_time_arrow.setVisibility(orderStatusRequest == 1 || orderStatusRequest == 2 ? View.VISIBLE : View.GONE);

        et_name.setEnabled(orderStatusRequest == 1 || orderStatusRequest == 2 ? true : false);
        et_phone.setEnabled(orderStatusRequest == 1 || orderStatusRequest == 2 ? true : false);

//司机物流信息
        //  mRelativeDriver.setVisibility(orderStatusRequest == 2 || orderStatusRequest == 14 || orderStatusRequest == 3 ? View.VISIBLE : View.GONE);
        // mTvSeeDriverStatus.setVisibility(orderStatusRequest == 3 ? View.VISIBLE : View.GONE);
        //  mTvDriverName.setVisibility(orderStatusRequest == 3 ? View.VISIBLE : View.GONE);
        //   tv_driver_phone.setVisibility(orderStatusRequest == 3 ? View.VISIBLE : View.GONE);

        //状态是待付款 或者是待支付 显示二个个按钮(取消订单 去支付) 其他状态需要不显示
        twoButtonLayout.setVisibility(orderStatusRequest == 1 ? View.VISIBLE : View.GONE);

        tvInfo.setVisibility(orderStatusRequest == 11 ? View.VISIBLE : View.GONE);
        //  状态是待付款 或者是待支付 显示倒计时 其他状态不需要显示


        if (orderStatusRequest == 1) {
            orderTimerView.setVisibility(View.VISIBLE);
            linearLayoutAddressArrow.setVisibility(View.VISIBLE);
        } else {
            orderTimerView.setVisibility(View.GONE);
            linearLayoutAddressArrow.setVisibility(View.GONE);
        }

        //  orderTimerView.setVisibility(orderStatusRequest == 1 ? View.VISIBLE : View.GONE);
        //状态是待付款 或者是待支付 不显示状态文案 其他状态需要显示
        tvOrderContent.setVisibility(orderStatusRequest == 1 ? View.GONE : View.VISIBLE);
        //状态是待付款 或者是待支付 orderStatusRequest=1 不显示三个按钮(退货 评价 再次购买) 其他状态需要显示


        //状态是待发货 已评价 不显示评价 其他状态
        // buttonEvaluate.setVisibility(orderStatusRequest == 2 || orderStatusRequest == 7 || orderStatusRequest == 6 ? View.GONE : View.VISIBLE);

        // buttonReturnGoods.setVisibility(orderStatusRequest == 7 ? View.GONE : View.VISIBLE);
        // buttonEvaluate.setText(orderStatusRequest == 5 ? "确认收货" : "评价");
        //状态是已取消  删除订单，再次购买显示，其它不显示
        //
       // mRlReturn.setVisibility(orderStatusRequest == 11 ? View.VISIBLE : View.GONE);


        setOrderContent(getOrderDetailModel.checkStatus);

    }

    private void setOrderContent(int type) {
        switch (orderStatusRequest) {
            case 2:
                tvOrderContent.setText("有问题请及时联系客服，客服电话" + getOrderDetailModel.customerPhone);
                break;
           /* case 3:
                tvOrderContent.setText("有问题请及时联系客服，客服电话" + getOrderDetailModel.customerPhone);
                break;*/
            case 4:
                tvOrderContent.setText("收到商品后，可以来评价试试");
                break;
            case 5:
                tvOrderContent.setText("收到商品后，可以来评价试试");
                break;  // 0审核 1 成功 2 失败
            case 7:
                tvOrderContent.setText("您的订单已取消");

                deleteButtonLayout.setVisibility(View.VISIBLE);
                threeButtonLayout.setVisibility(View.GONE);
                buttonAgainBuy.setVisibility(View.GONE);
                break;
            case 11:
                if (type == 0) {
                    ReturnGoodsContent.setText("不要着急，工作人员正在审核中");
                    relativeLayoutCommodityReturn.setVisibility(View.GONE);
                } else if (type == 1) {
                    ReturnGoodsContent.setText("欢迎再次使用翘歌烧烤");
                    relativeLayoutCommodityReturn.setVisibility(View.VISIBLE);
                } else {
                    ReturnGoodsContent.setText(getOrderDetailModel.feedbackReson);
                    relativeLayoutCommodityReturn.setVisibility(View.GONE);
                }
                break;
            case 14:
                tvOrderContent.setText("我们将尽快发货，感谢您对翘歌的信任");
                break;
            case 6:
                tvOrderContent.setText("感谢您的评价！欢迎您再次使用翘歌！");
                break;
        }
    }

    //显示取消订单提示
    private void showCancleOrder() {
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_are_you_sure);
        TextView mTvTip = mDialog.getWindow().findViewById(R.id.tv_dialog_tip);
        mTvTip.setText("确认取消订单?");
        mDialog.getWindow().findViewById(R.id.tv_dialog_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().findViewById(R.id.tv_dialog_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                cancelOrder(orderId);
            }
        });
    }

    //取消订单
    private void cancelOrder(final String orderId) {
        CancelOrderAPI.requestData(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CancelOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CancelOrderModel cancelOrderModel) {
                        if (cancelOrderModel.success) {
                            //取消成功
                            AppHelper.showMsg(mContext, "取消订单成功");

                            getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                            AppHelper.showMsg(mContext, cancelOrderModel.message);
                        }
                    }
                });
    }

    //不能退货弹窗
    private void showCannotReturnDialog() {
        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext, R.style.CommonDialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_can_not_return_reason);
        TextView mTvReason = (TextView) window.findViewById(R.id.tv_dialog_cannot_return_reason);
        Button mBtnConfirm = (Button) window.findViewById(R.id.btn_dialog_cannot_return_confirm);
        mTvReason.setText(getOrderDetailModel.cannotReturnGoodsMsg);
        mBtnConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    //立即评价
    private void requestEvaluate() {
        //去评价需要将订单里面的商品列表中的商品的商品名,商品ID组成list,传到评价的界面
        mListEvaluate.clear();
        if (getOrderDetailModel.productVOList != null && getOrderDetailModel.productVOList.size() > 0) {
            for (int i = 0; i < getOrderDetailModel.productVOList.size(); i++) {
                mListEvaluate.add(new OrderEvaluateListModel(getOrderDetailModel.productVOList.get(i).productId,
                        getOrderDetailModel.productVOList.get(i).businessType,
                        getOrderDetailModel.productVOList.get(i).name, getOrderDetailModel.productVOList.get(i).picUrl, 5 + "", ""));
            }
        } else {
            AppHelper.showMsg(mContext, "订单商品数据错误!");
        }
        Intent intentPut = new Intent(mContext, OrderEvaluateActivity.class);
        intentPut.putExtra("evaluateList", (Serializable) mListEvaluate);
        intentPut.putExtra("orderId", orderId);
        intentPut.putExtra("orderDeliveryType", 1);
        startActivityForResult(intentPut, 12);
    }

    // 再次购买
    private void requestCopyToCart() {
        CopyToCartAPI.requestCopyToCart(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CopyToCartModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CopyToCartModel copyToCartModel) {

                        if (copyToCartModel.success) {
                            //将订单内的商品加入购物车
                            AppHelper.showMsg(mContext, copyToCartModel.message);
                            startActivity(CartActivity.getIntent(mContext, CartActivity.class));

                        } else {
                            AppHelper.showMsg(mContext, copyToCartModel.message);
                        }
                    }
                });
    }

    // 确认自提订单收货
    private void requestConfirmGetGoods() {
        ConfirmOrderSelfAPI.requestDriverMe(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.success) {
                            //确认收货成功
                            AppHelper.showMsg(mContext, "确认提货成功");
                            //刷新订单状态
                            getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });


    /*    ConfirmGetGoodsAPI.reuqestConfirmGetGoods(mContext, orderId)
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
                            //刷新订单状态
                            getOrderDetail(orderId, orderState, returnProductMainId);
                        } else {
                            AppHelper.showMsg(mContext, mModelConfirmGetGoods.message);
                        }
                    }
                });*/
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.imageViewBreak: //返回按钮
                    backEvent();
                    break;
                case R.id.buttonCancelOrder:// 取消订单
                    showCancleOrder();
                    break;
                case R.id.buttonGOPay://去支付


                    Intent intent = new Intent(mContext, MyConfireOrdersActivity.class);
                    intent.putExtra("remark", getOrderDetailModel.remark);
                    // Log.i("wwb", "onNoDoubleClick: "+getOrderDetailModel.remark);
                    intent.putExtra("payAmount", Double.parseDouble(getOrderDetailModel.totalAmount));
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("flag", true);
                    intent.putExtra("orderDeliveryType", 1);
                    finish();
                    startActivity(intent);
                    break;

                case R.id.buttonReturnGood_two:
                    if (getOrderDetailModel.canReturnGoods) {
                      /*  mListForReturn.clear();
                        mListForReturn.addAll(getOrderDetailModel.productVOList);
                        Intent intentReturnGoods = new Intent(mContext, ReturnGoodsListActivity.class);
                        intentReturnGoods.putExtra("goodsList", (Serializable) mListForReturn);
                        intentReturnGoods.putExtra("orderId", orderId);
                        intentReturnGoods.putExtra("orderType", getOrderDetailModel.orderType);
                        intentReturnGoods.putExtra("returnProductMainId", returnProductMainId);
                        startActivity(intentReturnGoods);*/
                        mListForReturn.clear();
                        mListForReturn.addAll(getOrderDetailModel.productVOList);
                        Intent intent1 = new Intent(mContext, ReturnGoodActivity.class);
                        intent1.putExtra("orderStatus", orderStatusRequest + "");
                        intent1.putExtra("orderId", orderId);
                        intent1.putExtra("orderDeliveryType", 1);
                        startActivity(intent1);

                    } else {
                        //不能退货,弹框提示消息
                        showCannotReturnDialog();
                    }

                    break;
                case R.id.buttonReturnGoods: //退货

                    if (getOrderDetailModel.canReturnGoods) {
                      /*  mListForReturn.clear();
                        mListForReturn.addAll(getOrderDetailModel.productVOList);
                        Intent intentReturnGoods = new Intent(mContext, ReturnGoodsListActivity.class);
                        intentReturnGoods.putExtra("goodsList", (Serializable) mListForReturn);
                        intentReturnGoods.putExtra("orderId", orderId);
                        intentReturnGoods.putExtra("orderType", getOrderDetailModel.orderType);
                        intentReturnGoods.putExtra("returnProductMainId", returnProductMainId);
                        startActivity(intentReturnGoods);*/
                        mListForReturn.clear();
                        mListForReturn.addAll(getOrderDetailModel.productVOList);
                        Intent intent1 = new Intent(mContext, ReturnGoodActivity.class);
                        intent1.putExtra("orderStatus", orderStatusRequest+"");
                        intent1.putExtra("orderId", orderId);
                        intent1.putExtra("orderDeliveryType", 1);
                        startActivity(intent1);

                    } else {
                        //不能退货,弹框提示消息
                        showCannotReturnDialog();
                    }

                    break;

                case R.id.tv_order_return:
                    if (getOrderDetailModel.canReturnGoods) {
                      /*  mListForReturn.clear();
                        mListForReturn.addAll(getOrderDetailModel.productVOList);
                        Intent intentReturnGoods = new Intent(mContext, ReturnGoodsListActivity.class);
                        intentReturnGoods.putExtra("goodsList", (Serializable) mListForReturn);
                        intentReturnGoods.putExtra("orderId", orderId);
                        intentReturnGoods.putExtra("orderType", getOrderDetailModel.orderType);
                        intentReturnGoods.putExtra("returnProductMainId", returnProductMainId);
                        startActivity(intentReturnGoods);*/
                        mListForReturn.clear();
                        mListForReturn.addAll(getOrderDetailModel.productVOList);
                        Intent intent1 = new Intent(mContext, ReturnGoodActivity.class);
                        intent1.putExtra("orderStatus", orderStatusRequest+"");
                        intent1.putExtra("orderId", orderId);
                        intent1.putExtra("orderDeliveryType", 1);
                        startActivity(intent1);

                    } else {
                        //不能退货,弹框提示消息
                        showCannotReturnDialog();
                    }

                    break;

                case R.id.tv_confirm_order:
                    showConfirmOrderDialog();


                    break;
                case R.id.buttonEvaluate:
                    /*if (orderStatusRequest == 3) { // 确实收货
                        requestConfirmGetGoods();
                    } else {   // 立即评价
                        requestEvaluate();
                    }*/

                    if (orderStatusRequest == 5) {
                        requestEvaluate();
                    }
                    break;
                case R.id.buttonAgainBuy: // 再次购买
                    requestCopyToCart();
                    break;

                case R.id.tv_buttonAgainBuy:

                    requestCopyToCart();
                    break;
                case R.id.tv_delete:
                    deleteOrder();
                    break;
                case R.id.linearLayout_address_arrow:
                    Intent intent_ = new Intent(mContext, AddressListActivity.class);

                    intent_.putExtra("orderId", orderId);
                    intent_.putExtra("type", 1);
                    startActivity(intent_);
                    break;
                case R.id.tv_copy_order_num:
//获取剪贴板管理器：
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", tvNewOrderPhone.getText().toString());
// 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
                    AppHelper.showMsg(mContext, "复制成功");
                    break;


                case R.id.copy_order:
                    requestCopyToCart();
                    break;
                case R.id.tv_get_order_copy:
                    requestCopyToCart();

                    break;
                case R.id.tv_copy_order:
                    requestCopyToCart();
                    break;
                case R.id.iv_fresh_status:
                    LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                            .setMessage("获取数据中")
                            .setCancelable(false)

                            .setCancelOutside(true);
                    dialog = loadBuilder.create();
                    dialog.show();
                    getOrderDetailDialog(orderId, orderState, returnProductMainId);
                    break;

                //查看物流信息
                case R.id.tv_order_driver_message:

                    Intent intent1 = new Intent(mContext, MapOrderMessageActivity.class);
                    intent1.putExtra("orderId", orderId);
                    startActivity(intent1);
                    break;
                case R.id.et_name:
                    showModifyMsg(1);
                    break;
                case R.id.et_phone:
                    showModifyMsg(2);
                    break;
                case R.id.ll_one:
                    showModifyMsg(1);
                    break;
                case R.id.ll_two:
                    showModifyMsg(2);
                    break;
                case R.id.tv_evaluate:
                    Intent intent2 = new Intent(mContext, UserEvaluateActivity.class);
                    intent2.putExtra("orderId", orderId);
                    intent2.putExtra("orderDeliveryType", 1);
                    startActivity(intent2);


                    break;
            }
        }
    };

    /**
     * 确认收货弹窗
     */

    private void showConfirmOrderDialog() {
        AlertDialog dialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(R.layout.confirm_sufficiency_order_dialog);
        TextView tv_ok = window.findViewById(R.id.tv_ok);
        TextView tv_cancel = window.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                requestConfirmGetGoods();
            }
        });
    }

    /**
     * 修改提货人姓名和手机号
     */
    private void showModifyMsg(int type) {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        window.setContentView(R.layout.dialog_self_order);

        EditText et_name_one = window.findViewById(R.id.et_num);
        TextView tv_ok = window.findViewById(R.id.tv_ok);
        TextView tv_cancel = window.findViewById(R.id.tv_cancel);
        TextView tv_content = window.findViewById(R.id.tv_content);

        window.setGravity(Gravity.CENTER);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (type == 1) {
            tv_content.setText("请输入提货人姓名:");
            et_name_one.setInputType(InputType.TYPE_CLASS_TEXT);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_name_one.getText().toString() != null && StringHelper.notEmptyAndNull(et_name_one.getText().toString())) {

                        PostModifyMesAPI.requestModifyMsg(mContext, orderId, et_name_one.getText().toString(), et_phone.getText().toString(), tv_year.getText().toString(), mDetailModel.data.sendStartTime, mDetailModel.data.deliverTimeEnd, mDetailModel.data.deliverTimeName)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<BaseModel>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(BaseModel baseModel) {
                                        if (baseModel.success) {
                                            alertDialog.dismiss();
                                            et_name.setText(et_name_one.getText());
                                        } else {
                                            AppHelper.showMsg(mContext, baseModel.message);
                                        }

                                    }
                                });

                    } else {
                        AppHelper.showMsg(mContext, "请输入提货人姓名");
                    }

                }
            });
        } else if (type == 2) {
            tv_content.setText("请输入提货人手机号");
            et_name_one.setInputType(InputType.TYPE_CLASS_NUMBER);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_name_one.getText().toString() != null && StringHelper.notEmptyAndNull(et_name_one.getText().toString())) {
                        PostModifyMesAPI.requestModifyMsg(mContext, orderId, et_name.getText().toString(), et_name_one.getText().toString(), tv_year.getText().toString(), mDetailModel.data.sendStartTime, mDetailModel.data.deliverTimeEnd, mDetailModel.data.deliverTimeName)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<BaseModel>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(BaseModel baseModel) {
                                        if (baseModel.success) {
                                            alertDialog.dismiss();
                                            et_phone.setText(et_name_one.getText());
                                        } else {
                                            AppHelper.showMsg(mContext, baseModel.message);
                                        }

                                    }
                                });

                    } else {
                        AppHelper.showMsg(mContext, "请输入提货人手机号");
                    }

                }
            });
        }


    }

    public void deleteOrder() {
        DeleteOrderAPI.requestData(mContext, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CancelOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CancelOrderModel cancelOrderModel) {
                        if (cancelOrderModel.success) {
                            AppHelper.showMsg(mContext, "删除订单成功");
                            startActivity(new Intent(mContext, MyOrdersActivity.class));
                        } else {
                            AppHelper.showMsg(mContext, cancelOrderModel.message);
                        }
                    }
                });
    }

/*

    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
*/

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        mMapView.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明


            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            String streetNumber = location.getStreetNumber();
            latitude2 = location.getLatitude();
            longitude2 = location.getLongitude();

        }
    }


    public void showMapDialog() {
        mDialogMap = new BottomSheetDialog(mActivity);
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
        if (MapHelper.isAvilible(mActivity, "com.baidu.BaiduMap")) {// 传入指定应用包名
            try {
                Intent intent = new Intent();
                intent.setData(Uri.parse("baidumap://map/marker?location=" + latitude1 + "," + longitude1 + "&title=" + title + "&content=" + content + "&traffic=on"));
                startActivity(intent); // 启动调用
                mDialogMap.dismiss();
            } catch (Exception e) {
                Log.e("intent", e.getMessage());
            }
        } else {
            // 未安装
            String mapUri = "http://api.map.baidu.com/marker?location=" + latitude1 + "," + longitude1 + "&title=" + title + "&content=" + content + "&output=html";
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

        Map<String, Double> stringDoubleMap = GCJ02ToWGS84Util.bd09togcj02(longitude1, latitude1);
        Double lon = stringDoubleMap.get("lon");
        Double lat = stringDoubleMap.get("lat");


        if (MapHelper.isAvilible(mActivity, "com.autonavi.minimap")) {
            try {
                Intent intent = Intent.getIntent("androidamap://viewMap?sourceApplication=翘歌烧烤&poiname=" + title + "&lat="
                        + lat
                        + "&lon="
                        + lon + "&dev=0");
                startActivity(intent);
                mDialogMap.dismiss();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            String mapUri = "http://uri.amap.com/marker?position=" + lon + "," + lat + "&name=" + title + "&src=" + content + "&coordinate=gaode&callnative=0";
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
        Map<String, Double> stringDoubleMap = GCJ02ToWGS84Util.bd09togcj02(longitude1, latitude1);
        Double lon = stringDoubleMap.get("lon");
        Double lat = stringDoubleMap.get("lat");

        String mapUri = "http://apis.map.qq.com/uri/v1/marker?marker=coord:" + lat + "," + lon + ";title:" + title + ";addr:" + content + "&referer=翘歌烧烤";
        Uri uri = Uri.parse(mapUri);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        mDialogMap.dismiss();
    }

}
