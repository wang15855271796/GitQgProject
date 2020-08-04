package com.puyue.www.qiaoge.activity;

import android.content.Intent;
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
import com.chuanglan.shanyan_sdk.listener.GetPhoneInfoListener;
import com.chuanglan.shanyan_sdk.listener.InitListener;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.LogoutsEvent;
import com.puyue.www.qiaoge.api.PostLoadAmountAPI;
import com.puyue.www.qiaoge.api.SendJsPushAPI;
import com.puyue.www.qiaoge.api.home.CityChangeAPI;
import com.puyue.www.qiaoge.api.home.QueryHomePropupAPI;
import com.puyue.www.qiaoge.api.home.SendLocationAPI;
import com.puyue.www.qiaoge.base.BaseActivity;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.CouponDialog;
import com.puyue.www.qiaoge.event.GoToCartFragmentEvent;
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
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.IsShowModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.GetAddressModel;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;
import com.puyue.www.qiaoge.popupwindow.HomePopuWindow;
import com.puyue.www.qiaoge.utils.LoginUtil;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.view.StatusBarUtil;

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
    private MyLocationListener myListener = new MyLocationListener();
    private String token;
    private String locationMessage = "";
    private String guide;
    private boolean isSend = false;
    private String city;
    private boolean isGet = false;
    private String type;
    private String district;
    CouponDialog couponDialog;

    @Override
    public void onAttachFragment(Fragment fragment) {
        //重新让新的Fragment指向了原本未被销毁的fragment，它就是onAttach方法对应的Fragment对象
        if (mTabHome == null && fragment instanceof HomeFragmentsss)
            mTabHome = fragment;
        if (mTabMarket == null && fragment instanceof MarketsFragment)
            mTabMarket = fragment;
        if (mTabCart == null && fragment instanceof CartFragment)
            mTabCart = fragment;
        if (mTabMine == null && fragment instanceof MineFragment)
            mTabMine = fragment;
        super.onAttachFragment(fragment);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }



    @Override
    public void setContentView() {
        //showSystemParameter();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext


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
        setContentView(R.layout.activity_home);

        isShow();
    }

    /**
     * 授权展示
     */
    private void isShow() {
        CityChangeAPI.isShow(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IsShowModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IsShowModel isShowModel) {
                        if(isShowModel.isSuccess()) {
                            if(isShowModel.data!=null) {
                                SharedPreferencesUtil.saveString(mActivity,"priceType",isShowModel.getData().enjoyProduct);
                            }
                        }else {
                            AppHelper.showMsg(mActivity,isShowModel.getMessage());
                        }
                    }
                });
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
        StatusBarUtil.setStatusBarLightMode(mActivity);
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
//        QueryHomePropup();

//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        //  SharedPreferences preferences = getSharedPreferences("isFirstUse", MODE_WORLD_READABLE);
        //  UserInfoHelper.saveGuide(mActivity, "guide");
        guide = UserInfoHelper.getGuide(mActivity);
        UserInfoHelper.saveChangeFlag(mContext,0+"");

        JPushInterface.init(this);
        String registrationID = JPushInterface.getRegistrationID(this);

        UserInfoHelper.saveRegistionId(mContext, registrationID);


            mLocationClient.start();

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
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            } else if (view == mLlMarket) {
                switchTab(TAB_MARKET);

            } else if (view == mLlCart) {
                //从首页判断用户没有登录跳转到登录界面,登录成功回来的时候要重新请求数据,
                //由于是从首页和商城页点击进入的登录界面,回到原来界面的时候需要首页刷新或者商城界面刷新分类和细节数据
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    switchTab(TAB_CART);
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else {
//                    AppHelper.showMsg(mContext, "请先登录");
//                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                    initDialog();
                }
            } else if (view == mLlMine) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    switchTab(TAB_MINE);
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else {
                    initDialog();
                }
            }
        }
    };

    private void initDialog() {
        couponDialog = new CouponDialog(mActivity) {
            @Override
            public void Login() {
                startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                dismiss();
            }

            @Override
            public void Register() {
                LoginUtil.initRegister(getContext());
                dismiss();
            }
        };
        couponDialog.show();
    }

    private void switchTab(String tab) {

        mLocationClient.stop();
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
                    mTabHome = new HomeFragmentsss();
                    mFragmentTransaction.add(R.id.layout_home_container, mTabHome);
                    isGet = false;
                } else {
                    mFragmentTransaction.show(mTabHome);

                }

                mIvHome.setImageResource(R.mipmap.ic_tab_home_enable);
                mTvHome.setTextColor(getResources().getColor(R.color.app_tab_selected));
                getCartPoductNum();
                break;

            case TAB_MARKET:
                if (mTabMarket == null) {
                    mTabMarket = new MarketsFragment();
                    mFragmentTransaction.add(R.id.layout_home_container, mTabMarket);
                } else {
                    mFragmentTransaction.show(mTabMarket);
                }

                mIvMarket.setImageResource(R.mipmap.ic_tab_goods_enable);
                mTvMarket.setTextColor(getResources().getColor(R.color.app_tab_selected));
                getCartPoductNum();
                break;
            case TAB_CART:

                if (mTabCart == null) {
                    mTabCart = new CartFragment();
                    mFragmentTransaction.add(R.id.layout_home_container, mTabCart);
                } else {
                    mFragmentTransaction.show(mTabCart);

                }
                mIvCart.setImageResource(R.mipmap.ic_tab_cart_enable);
                mTvCart.setTextColor(getResources().getColor(R.color.app_tab_selected));
                getCartPoductNum();
                break;
            case TAB_MINE:
                if (mTabMine == null) {
                    mTabMine = new MineFragment();
                    mFragmentTransaction.add(R.id.layout_home_container, mTabMine);
                } else {
                    mFragmentTransaction.show(mTabMine);
                }

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
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(GoToMineEvent goToMineEvent) {
        switchTab(TAB_MINE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageEvent(GoToMarketEvent goToMarketEvent) {
        switchTab(TAB_MARKET);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goToCartFragment(GoToCartFragmentEvent goToCartFragmentEvent) {
        switchTab(TAB_CART);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LogoutsEvent mainEvent) {
        switchTab(TAB_HOME);
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


public class MyLocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取地址相关的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
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
        switchTab(TAB_HOME);
    }
}
}