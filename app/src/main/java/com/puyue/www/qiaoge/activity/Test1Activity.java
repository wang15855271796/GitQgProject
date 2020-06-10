package com.puyue.www.qiaoge.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.GetPhoneInfoListener;
import com.chuanglan.shanyan_sdk.listener.InitListener;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.puyue.www.qiaoge.CustomViewPager;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.TabEntity;
import com.puyue.www.qiaoge.activity.mine.login.LogoutsEvent;
import com.puyue.www.qiaoge.adapter.mine.ViewPagerAdapters;
import com.puyue.www.qiaoge.api.PostLoadAmountAPI;
import com.puyue.www.qiaoge.api.SendJsPushAPI;
import com.puyue.www.qiaoge.api.home.QueryHomePropupAPI;
import com.puyue.www.qiaoge.api.home.SendLocationAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.GoToMineEvent;
import com.puyue.www.qiaoge.event.LogoutEvent;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.fragment.cart.CartFragment;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.fragment.home.HomeFragmentsss;
import com.puyue.www.qiaoge.fragment.market.MarketsFragment;
import com.puyue.www.qiaoge.fragment.mine.MineFragment;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.GetAddressModel;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;
import com.puyue.www.qiaoge.popupwindow.HomePopuWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/5/20
 */
public class Test1Activity extends BaseSwipeActivity implements CartFragment.FragmentInteraction, CartFragment.GoToMarket{
    int mCurrIndex = 0;
    private long mExitTime = 0;
    //    private TextView mTvCarNum;
//    private TextView tv_change;
    // 弹窗
    private HomePopuWindow popuWindow;
    private String popuWindowImage;
    private String popuWindowUrlIntent;
    private int popuWindowId;
    private LinearLayout rootview;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private String token;
    private String locationMessage = "";
    private String guide;
    private boolean isSend = false;
    private String city;
    private boolean isGet = false;
    private String type;
    private String district;
    // 顶部滑动的标签栏
    private String[] mTitles = {"首页", "商品", "购物车","我的"};
    // 未被选中的图标
    private int[] mIconUnSelectIds = {R.mipmap.ic_tab_home_unable, R.mipmap.ic_tab_goods_unable, R.mipmap.ic_tab_cart_unable,R.mipmap.ic_tab_mine_unable};
    // 被选中的图标
    private int[] mIconSelectIds = {R.mipmap.ic_tab_home_enable, R.mipmap.ic_tab_goods_enable, R.mipmap.ic_tab_cart_enable,R.mipmap.ic_tab_mine_enable};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private CommonTabLayout tabLayout;
    private CustomViewPager viewPager;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        //showSystemParameter();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext

        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        OneKeyLoginManager.getInstance().init(getApplicationContext(), "cuRwbnsv", new InitListener() {
            @Override
            public void getInitStatus(int code, String result) {
                //初始化回调

            }
        });

        //闪验SDK预取号（可缩短拉起授权页时间）
        OneKeyLoginManager.getInstance().getPhoneInfo(new GetPhoneInfoListener() {
            @Override
            public void getPhoneInfoStatus(int code, String result) {
                //预取号回调
                Log.e("VVV", "预取号： code==" + code + "   result==" + result);

            }
        });
        setContentView(R.layout.test);

    }




    @Override
    public void findViewById() {
//        tv_change = (TextView) findViewById(R.id.tv_change);

//        mTvCarNum = (TextView) findViewById(R.id.tv_home_car_number);
//        tabLayout = findViewById(R.id.tab_layout);
//        viewPager = findViewById(R.id.main_viewpager);
//        rootview = findViewById(R.id.rootview);

//        tv_change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext,ChangeCityActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    public void setViewData() {

        EventBus.getDefault().register(this);
        if (getIntent() != null) {
            type = getIntent().getStringExtra("go_home");
        }

        token = AppConstant.TOKEN;
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);


        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true
        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(true);
        mLocationClient.setLocOption(option);


        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //传当前地址信息
        QueryHomePropup();

//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        //  SharedPreferences preferences = getSharedPreferences("isFirstUse", MODE_WORLD_READABLE);
        //  UserInfoHelper.saveGuide(mActivity, "guide");
        guide = UserInfoHelper.getGuide(mActivity);
        UserInfoHelper.saveChangeFlag(mContext,0+"");


/**
          *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
          */
        if (guide.equals("")) {
            showDialogGuide();
        }

        JPushInterface.init(this);
        String registrationID = JPushInterface.getRegistrationID(this);

        UserInfoHelper.saveRegistionId(mContext, registrationID);


        mLocationClient.start();

    }


    /**
     * 引导页
     */
    private void showDialogGuide() {

        UserInfoHelper.saveGuide(mActivity, "guide");
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.home_guide);
        window.setGravity(Gravity.TOP | Gravity.RIGHT);
        ImageView ivGuideOne = window.findViewById(R.id.iv_guide_one);
        ivGuideOne.setVisibility(View.VISIBLE);
        ivGuideOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivGuideOne.setVisibility(View.GONE);
                ImageView ivGuideTwo = window.findViewById(R.id.iv_guide_two);
                ivGuideTwo.setVisibility(View.VISIBLE);
                ivGuideTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivGuideOne.setVisibility(View.GONE);
                        ivGuideTwo.setVisibility(View.GONE);
                        alertDialog.dismiss();
                    }
                });


            }
        });


    }

    private void sendLocation() {
        SendLocationAPI.requestData(mContext, locationMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAddressModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetAddressModel getAddressModel) {

                        if (getAddressModel.isSuccess()) {

                        }
                    }
                });

    }

    @Override
    public void setClickEvent() {
    }


    //在这个方法里面拿到回传回来的数据修改UI即可
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(LogoutEvent logoutEvent) {
        getCartPoductNum();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(GoToMineEvent goToMineEvent) {
        getCartPoductNum();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(GoToMarketEvent goToMarketEvent) {
        getCartPoductNum();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                AppHelper.showMsg(this, "再按一次退出程序！");
                mExitTime = System.currentTimeMillis();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getCartPoductNum() {
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            getCartNum();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            sendRegistionId();
            if (!isSend) {
                if (UserInfoHelper.getLoadAmount(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getLoadAmount(mContext))) {
                    sendLoadNum();
                }
            }

        } else {
//            mTvCarNum.setVisibility(View.GONE);
            tabLayout.hideMsg(2);
        }
        if (token != null) {

            sendLocation();

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBuss(ReduceNumEvent event) {
        //刷新UI
        getCartPoductNum();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LogoutsEvent mainEvent) {

        if (city != null) {
            UserInfoHelper.saveCity(mContext, city);
            UserInfoHelper.saveAreaName(mContext,district);
        } else {
            UserInfoHelper.saveCity(mContext, "");
        }

    }


    private void sendLoadNum() {
        PostLoadAmountAPI.requestData(mContext)
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
                            isSend = true;
                        }
                    }
                });
    }

    private void sendRegistionId() {


        SendJsPushAPI.requestData(mContext, UserInfoHelper.getRegistionid(mContext))
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

                        }
                    }
                });
    }


    /**
     * 获取购物车角标数据
     */
    private void getCartNum() {

        PublicRequestHelper.getCartNum(mContext, new OnHttpCallBack<GetCartNumModel>() {
            @Override
            public void onSuccessful(GetCartNumModel getCartNumModel) {
                if (getCartNumModel.isSuccess()) {
                    if (Integer.valueOf(getCartNumModel.getData().getNum()) > 0) {
                        tabLayout.showMsg(2, Integer.parseInt(getCartNumModel.getData().getNum()));
//                        mTvCarNum.setVisibility(View.VISIBLE);
//                        mTvCarNum.setText(getCartNumModel.getData().getNum());
                    } else {
                        tabLayout.hideMsg(2);
//                        mTvCarNum.setVisibility(View.GONE);
                    }
                } else {
                    AppHelper.showMsg(mContext, getCartNumModel.getMessage());
                }
            }

            @Override
            public void onFaild(String errorMsg) {

            }
        });
    }

    private String toPage;


    // 首页弹窗
    private void QueryHomePropup() {
        QueryHomePropupAPI.requestQueryHomePropup(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QueryHomePropupModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QueryHomePropupModel queryHomePropupModel) {
                        if (queryHomePropupModel.isSuccess()) {

                            if (queryHomePropupModel.getData().isPropup()) {
                                popuWindowImage = queryHomePropupModel.getData().getHomePropup().getShowUrl();
                                popuWindowUrlIntent = queryHomePropupModel.getData().getHomePropup().getPageUrl();
                                popuWindowId = queryHomePropupModel.getData().getHomePropup().getId();
                                toPage = queryHomePropupModel.getData().getHomePropup().getToPage();
                                setPopuWindow();
                            }
                        } else {
                            AppHelper.showMsg(mActivity, queryHomePropupModel.getMessage());
                        }
                    }
                });
    }

    private void setPopuWindow() {
        popuWindow = new HomePopuWindow(mActivity, popuWindowId, popuWindowImage, popuWindowUrlIntent, toPage);
        popuWindow.showAtLocation(rootview, Gravity.NO_GRAVITY, 0, 0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            String marketGood = intent.getStringExtra("collect");

            if (marketGood != null) {

            }
        }




    }

    @Override
    public void refreshCarNum() {
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            getCartNum();
        } else {
//            mTvCarNum.setVisibility(View.GONE);
            tabLayout.hideMsg(2);
        }
    }

    @Override
    public void jumpMarket() {

    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
//         String city = location.getCity();    //获取城市
            String street = location.getStreet();    //获取街道信息
            String streetNumber = location.getStreetNumber();
            //获取区县
            district = location.getDistrict();
            city = location.getCity();
            UserInfoHelper.saveAreaName(mContext, district);
            UserInfoHelper.saveLocation(mContext,location.getAddrStr());
            isGet = true;

            if (type.equals("goHome")) {
                if (city != null) {
                    UserInfoHelper.saveCity(mContext, city);

                } else {
                    UserInfoHelper.saveCity(mContext, "");
                }
            }
            type = "";
            locationMessage = location.getAddrStr();    //获取详细地址信息
            initTab();
        }
    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        ViewPagerAdapters adapter = new ViewPagerAdapters(getSupportFragmentManager());
        adapter.addFragment(HomeFragmentsss.getInstance());
        adapter.addFragment(MarketsFragment.getInstance());
        adapter.addFragment(CartFragment.getInstance());
        adapter.addFragment(MineFragment.getInstance());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        //为Tab赋值数据
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position, false);
                mCurrIndex = position;
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.setCurrentItem(0);
        tabLayout.setCurrentTab(mCurrIndex);
    }

}
