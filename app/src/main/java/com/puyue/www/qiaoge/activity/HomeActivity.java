package com.puyue.www.qiaoge.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.InitListener;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.api.PostLoadAmountAPI;
import com.puyue.www.qiaoge.api.SendJsPushAPI;
import com.puyue.www.qiaoge.api.home.QueryHomePropupAPI;
import com.puyue.www.qiaoge.api.home.SendLocationAPI;
import com.puyue.www.qiaoge.base.BaseActivity;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.GoToMineEvent;
import com.puyue.www.qiaoge.event.LogoutEvent;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.event.WeChatUnPayEvent;
import com.puyue.www.qiaoge.fragment.cart.CartFragment;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.fragment.home.HomeFragment;
import com.puyue.www.qiaoge.fragment.home.HomeFragments;
import com.puyue.www.qiaoge.fragment.market.MarketsFragment;
import com.puyue.www.qiaoge.fragment.mine.MineFragment;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.GetAddressModel;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;
import com.puyue.www.qiaoge.popupwindow.HomePopuWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.jpush.android.api.JPushInterface;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends BaseActivity implements CartFragment.FragmentInteraction, CartFragment.GoToMarket {
    private static final String TAB_HOME = "tab_home";
    private static final String TAB_MARKET = "tab_market";
    private static final String TAB_CART = "tab_cart";
    private static final String TAB_MINE = "tab_mine";
    private Fragment mTabHome;
    private Fragment mTabMarket;
    private Fragment mTabCart;
    private Fragment mTabMine;
    private FragmentTransaction mFragmentTransaction;
    private LinearLayout mLlHome;
    private ImageView mIvHome;
    private TextView mTvHome;
    private LinearLayout mLlMarket;
    private ImageView mIvMarket;
    private TextView mTvMarket;
    private LinearLayout mLlCart;
    private ImageView mIvCart;
    private TextView mTvCart;
    private LinearLayout mLlMine;
    private ImageView mIvMine;
    private TextView mTvMine;
    private long mExitTime = 0;
    private TextView mTvCarNum;
    // 弹窗
    private HomePopuWindow popuWindow;
    private String popuWindowImage;
    private String popuWindowUrlIntent;
    private int popuWindowId;
    private LinearLayout rootview;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener;
    private String token;
    private String locationMessage = "";
    private String guide;
    private boolean isSend = false;
    private String city;
    private boolean isGet = false;
    private String type;

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
                Log.d("sswswswdffffff.....",code+"");
            }
        });
        setContentView(R.layout.activity_home);
        //在首页中写运行时权限设置tv_home_car_number
//        setNewPosition();

    }

    /**
     * 将角标数据重新给HomeFragment
     */
    private void setNewPosition(int newPosition) {
        //HA 传给HF

        Bundle newPositionbundle = new Bundle();
        newPositionbundle.putInt("newPosition", newPosition);

        mTabHome.setArguments(newPositionbundle);

        Log.e("MessageCenterActivity", "setNewPosition: " + newPosition);


    }


    @Override
    public void findViewById() {
        mLlHome = (LinearLayout) findViewById(R.id.layout_tab_bar_home);
        mIvHome = (ImageView) findViewById(R.id.iv_tab_bar_home_icon);
        mTvHome = (TextView) findViewById(R.id.tv_tab_bar_home_title);

        mLlMarket = (LinearLayout) findViewById(R.id.layout_tab_bar_market);
        mIvMarket = (ImageView) findViewById(R.id.iv_tab_bar_market_icon);
        mTvMarket = (TextView) findViewById(R.id.tv_tab_bar_market_title);

        mLlCart = (LinearLayout) findViewById(R.id.layout_tab_bar_cart);
        mIvCart = (ImageView) findViewById(R.id.iv_tab_bar_cart_icon);
        mTvCart = (TextView) findViewById(R.id.tv_tab_bar_cart_title);

        mLlMine = (LinearLayout) findViewById(R.id.layout_tab_bar_mine);
        mIvMine = (ImageView) findViewById(R.id.iv_tab_bar_mine_icon);
        mTvMine = (TextView) findViewById(R.id.tv_tab_bar_mine_title);
        mTvCarNum = (TextView) findViewById(R.id.tv_home_car_number);
        rootview = findViewById(R.id.rootview);

    }


    @Override
    public void setViewData() {

        EventBus.getDefault().register(this);

        if (getIntent() != null) {
            type = getIntent().getStringExtra("go_home");
        }

        token = AppConstant.TOKEN;
        myListener = new MyLocationListener();
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
/**
          *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
          */
        if (guide.equals("")) {
            showDialogGuide();
            //startActivity(new Intent(HandlerActivity.this, MainActivity.class));
        } else {
            //startActivity(new Intent(HandlerActivity.this, LoginActivity.class));
        }
//finish();
        //   JPushInterface.setDebugMode(true);//测试版为true
        JPushInterface.init(this);
        String registrationID = JPushInterface.getRegistrationID(this);

        UserInfoHelper.saveRegistionId(mContext, registrationID);


      /*  if (UserInfoHelper.getCity(mContext) != null&&StringHelper.notEmptyAndNull(UserInfoHelper.getCity(mContext))) {

        }else {
            mLocationClient.start();
        }*/

            mLocationClient.start();



    /*  if (city!=null&&StringHelper.notEmptyAndNull(city)){

      }else {
          new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  switchTab(TAB_HOME);
              }
          }, 5000);
      }*/
    switchTab(TAB_HOME);

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


        ivGuideOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivGuideOne.setVisibility(View.GONE);
                /*alertDialog.dismiss();

                final AlertDialog alertDialog1 = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
                alertDialog1.setCanceledOnTouchOutside(false);
                alertDialog1.show();
                Window window = alertDialog1.getWindow();
                window.setContentView(R.layout.home_guide);*/

                ImageView ivGuideTwo = window.findViewById(R.id.iv_guide_two);
                ivGuideTwo.setVisibility(View.VISIBLE);
                ivGuideTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivGuideOne.setVisibility(View.GONE);
                        ivGuideTwo.setVisibility(View.GONE);
                     /*   //alertDialog1.dismiss();
                        final AlertDialog alertDialog2 = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
                        alertDialog2.setCanceledOnTouchOutside(false);
                        alertDialog2.show();
                        Window window = alertDialog2.getWindow();
                        window.setContentView(R.layout.home_guide);*/
                        ImageView ivGuideThree = window.findViewById(R.id.iv_guide_three);
                        ivGuideThree.setVisibility(View.VISIBLE);
                        ivGuideThree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();


                                UserInfoHelper.saveLoadAmount(mContext, "已下载");

                            }
                        });
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
        mLlHome.setOnClickListener(noDoubleClickListener);
        mLlMarket.setOnClickListener(noDoubleClickListener);
        mLlCart.setOnClickListener(noDoubleClickListener);
        mLlMine.setOnClickListener(noDoubleClickListener);
    }


    //在这个方法里面拿到回传回来的数据修改UI即可
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mLlHome) {
                switchTab(TAB_HOME);
                setTranslucentStatus();
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            } else if (view == mLlMarket) {
                switchTab(TAB_MARKET);
                //  StatusBarCompat.setStatusBarColor(mActivity, Color.parseColor("#ffffff"), true);


            } else if (view == mLlCart) {
                //从首页判断用户没有登录跳转到登录界面,登录成功回来的时候要重新请求数据,
                //由于是从首页和商城页点击进入的登录界面,回到原来界面的时候需要首页刷新或者商城界面刷新分类和细节数据
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    switchTab(TAB_CART);
//                     StatusBarCompat.setStatusBarColor(mActivity, R.mipmap.ic_car_title,true);
                    setTranslucentStatus();
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mLlMine) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    switchTab(TAB_MINE);
                    setTranslucentStatus();
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            }
        }
    };

    private void switchTab(String tab) {

        //开始事务
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        //隐藏所有的Fragment
        if (mTabHome != null) {
            mFragmentTransaction.hide(mTabHome);
        }
        if (mTabMarket != null) {
            mFragmentTransaction.hide(mTabMarket);
        }
        if (mTabCart != null) {
            mFragmentTransaction.hide(mTabCart);
        }
        if (mTabMine != null) {
            mFragmentTransaction.hide(mTabMine);
        }
        //重置所有的tabStyle
        mIvHome.setImageResource(R.mipmap.ic_tab_home_unable);
        mTvHome.setTextColor(getResources().getColor(R.color.app_color_bottom_gray));
        mIvMarket.setImageResource(R.mipmap.ic_tab_goods_unable);
        mTvMarket.setTextColor(getResources().getColor(R.color.app_color_bottom_gray));
        mIvCart.setImageResource(R.mipmap.ic_tab_cart_unable);
        mTvCart.setTextColor(getResources().getColor(R.color.app_color_bottom_gray));
        mIvMine.setImageResource(R.mipmap.ic_tab_mine_unable);
        mTvMine.setTextColor(getResources().getColor(R.color.app_color_bottom_gray));
        //切换被选中的tab
        switch (tab) {
            case TAB_HOME:
                if (mTabHome == null || isGet) {
                    mTabHome = new HomeFragments();
                    mFragmentTransaction.add(R.id.layout_home_container, mTabHome);
                    isGet = false;
                } else {
                    mFragmentTransaction.show(mTabHome);
                }

//                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//
//                    QueryHomePropup();
//                }

                mIvHome.setImageResource(R.mipmap.ic_tab_home_enable);
                mTvHome.setTextColor(getResources().getColor(R.color.app_tab_selected));
                getCartPoductNum();
                break;
            case TAB_MARKET:
            /*    if (mTabMarket == null) {
                    mTabMarket = new MarketFragment();
                    mFragmentTransaction.add(R.id.layout_home_container, mTabMarket);
                } else {
                    mFragmentTransaction.show(mTabMarket);
                }*/
                //   mTabMarket = new MarketFragment();
                ///   mFragmentTransaction.add(R.id.layout_home_container, mTabMarket);

                mTabMarket = new MarketsFragment();
                mFragmentTransaction.add(R.id.layout_home_container, mTabMarket);

                mIvMarket.setImageResource(R.mipmap.ic_tab_goods_enable);
                mTvMarket.setTextColor(getResources().getColor(R.color.app_tab_selected));

                getCartPoductNum();
                break;
            case TAB_CART:

             /*   if (mTabCart == null) {
                    mTabCart = new CartFragment();

                    mFragmentTransaction.add(R.id.layout_home_container, mTabCart);
                } else {
                    mFragmentTransaction.show(mTabCart);

                }*/
                mTabCart = new CartFragment();

                mFragmentTransaction.add(R.id.layout_home_container, mTabCart);
                mIvCart.setImageResource(R.mipmap.ic_tab_cart_enable);
                mTvCart.setTextColor(getResources().getColor(R.color.app_tab_selected));
                getCartPoductNum();
                break;
            case TAB_MINE:
             /*   if (mTabMine == null) {
                    mTabMine = new MineFragment();
                    mFragmentTransaction.add(R.id.layout_home_container, mTabMine);
                } else {
                    mFragmentTransaction.show(mTabMine);
                }*/
                mTabMine = new MineFragment();
                mFragmentTransaction.add(R.id.layout_home_container, mTabMine);
                mIvMine.setImageResource(R.mipmap.ic_tab_mine_enable);
                mTvMine.setTextColor(getResources().getColor(R.color.app_tab_selected));
                getCartPoductNum();
                break;
        }
        //提交事务
        mFragmentTransaction.commitAllowingStateLoss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(LogoutEvent logoutEvent) {
//        switchTab(TAB_HOME);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(GoToMineEvent goToMineEvent) {
        switchTab(TAB_MINE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(GoToMarketEvent goToMarketEvent) {
        switchTab(TAB_MARKET);
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
     /*   if (mTabHome.isVisible()) {

            QueryHomePropup();


        }*/



        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            getCartNum();

            sendRegistionId();
            if (!isSend) {
                if (UserInfoHelper.getLoadAmount(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getLoadAmount(mContext))) {
                    sendLoadNum();
                }
            }

        } else {
            mTvCarNum.setVisibility(View.GONE);
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
                        mTvCarNum.setVisibility(View.VISIBLE);
                        mTvCarNum.setText(getCartNumModel.getData().getNum());

                    } else {
                        mTvCarNum.setVisibility(View.GONE);
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
                            Log.d("hahhaaahahhaha....",queryHomePropupModel.getData().isPropup()+"");
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
      /*  String spikeFlag = UserInfoHelper.getSpikeFlag(mContext);
        Log.i("cccc", "onNewIntent: " + spikeFlag);
        if (spikeFlag.equals("popu")) {

        } else {
            QueryHomePropup();
        }*/
        if (intent != null) {
            String marketGood = intent.getStringExtra("collect");

            if (marketGood != null) {
                switchTab(TAB_MARKET);
            }
        }




    }

    @Override
    public void refreshCarNum() {
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            getCartNum();
        } else {
            mTvCarNum.setVisibility(View.GONE);
        }
    }

    @Override
    public void jumpMarket() {
        switchTab(TAB_MARKET);
    }

//    @Override
//    public void jumpMarket() {
//        switchTab(TAB_MARKET);
//    }


public class MyLocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取地址相关的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        String country = location.getCountry();    //获取国家
        String province = location.getProvince();    //获取省份
        // String city = location.getCity();    //获取城市
        String district = location.getDistrict();    //获取区县
        String street = location.getStreet();    //获取街道信息
        String streetNumber = location.getStreetNumber();
        city = location.getCity();

        isGet = true;

        if (type.equals("goHome")) {
            if (city != null && StringHelper.notEmptyAndNull(city)) {
                UserInfoHelper.saveCity(mContext, city);
//                switchTab(TAB_HOME);

            } else {
                UserInfoHelper.saveCity(mContext, "杭州市");
//                switchTab(TAB_HOME);
            }
        }

        type = "";
        locationMessage = location.getAddrStr();    //获取详细地址信息

        Log.i("wweabv", "onReceiveLocation: " + city);
    }
}
}