package com.puyue.www.qiaoge.activity.mine.login;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.LocationClientOption;
import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.OneKeyLoginListener;
import com.chuanglan.shanyan_sdk.listener.OpenLoginAuthListener;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.ConfigUtils;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.activity.WebDriverActivity;
import com.puyue.www.qiaoge.activity.mine.account.EditAccountInputPhoneActivity;
import com.puyue.www.qiaoge.api.home.GetCustomerPhoneAPI;
import com.puyue.www.qiaoge.api.home.OneRegisterModel;
import com.puyue.www.qiaoge.api.mine.login.LoginAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.login.LoginModel;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/3.
 */

public class LoginActivity extends BaseSwipeActivity {

    private EditText mEditAccount;
    private RelativeLayout mRlAccountDelete;
    private EditText mEditPassword;
    private RelativeLayout mRlPasswordDisplay;
    private ImageView mIvPasswordDisplay;
    private ImageView mBtnLogin;
    private TextView mTvForgetPassword;
    private TextView mTvRegister;
    private RelativeLayout mRelative;
    private boolean showPassword = true;
    private ImageView mIvBack;

    private LoginModel mModelLogin;

    private LinearLayout linIphone;
    private LinearLayout linPsd;

    private ImageView iv_change_type;
    private LinearLayout ll_change_type;
//    public LocationClient mLocationClient = null;
//    private MyLocationListener myListener = new MyLocationListener();

    private String city = "";

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void findViewById() {
        mEditAccount = (EditText) findViewById(R.id.edit_login_account);
        mRlAccountDelete = (RelativeLayout) findViewById(R.id.rl_login_account_delete);
        mEditPassword = (EditText) findViewById(R.id.edit_login_password);
        mRlPasswordDisplay = (RelativeLayout) findViewById(R.id.rl_login_password_display);
        mIvPasswordDisplay = (ImageView) findViewById(R.id.iv_login_password_display);
        mBtnLogin = (ImageView) findViewById(R.id.btn_login_next);
        mTvForgetPassword = (TextView) findViewById(R.id.tv_login_forget_password);
        mTvRegister = (TextView) findViewById(R.id.tv_login_register);
        mIvBack = (ImageView) findViewById(R.id.iv_login_back);
        linIphone = (LinearLayout) findViewById(R.id.linIphone);
        linPsd = (LinearLayout) findViewById(R.id.linPsd);
        mRelative = (RelativeLayout) findViewById(R.id.relativeLayout);
        iv_change_type = (ImageView) findViewById(R.id.iv_change_type);
        ll_change_type = (LinearLayout) findViewById(R.id.ll_change_type);
    }

    @Override
    public void setViewData() {
        UserInfoHelper.saveMac(mContext, getMacAddress(mContext));
        UserInfoHelper.savePhoneIp(mContext, getLocalIpAddress(mContext));
//        mLocationClient = new LocationClient(getApplicationContext());
//        //声明LocationClient类
//        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true

//        mLocationClient.setLocOption(option);
//        mLocationClient.start();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);


        //在登录这里不需要去判断用户输入的密码是否合规,只要请求接口确认是和后台的密码一致的即可
        // mEditAccount.setInputType(InputType.TYPE_CLASS_NUMBER);
        //用了荣耀8青春版就黑屏
        mEditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //   mEditPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mBtnLogin.setEnabled(false);
        // mBtnLogin.setTextColor(getResources().getColor(R.color.app_btn_unable));
        //根据用户有没有登录过,显示之前那一次登录的账号
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserCell(mContext))) {
            mEditAccount.setText(UserInfoHelper.getUserCell(mContext));
        }
        //  applyBlur();
    }

    @Override
    public void setClickEvent() {
        mEditAccount.addTextChangedListener(textWatcher);
        mEditPassword.addTextChangedListener(textWatcher);
        mRlAccountDelete.setOnClickListener(noDoubleClickListener);
        mRlPasswordDisplay.setOnClickListener(noDoubleClickListener);
        mBtnLogin.setOnClickListener(noDoubleClickListener);
        mTvForgetPassword.setOnClickListener(noDoubleClickListener);
        mTvRegister.setOnClickListener(noDoubleClickListener);
        mIvBack.setOnClickListener(noDoubleClickListener);
        iv_change_type.setOnClickListener(noDoubleClickListener);
        ll_change_type.setOnClickListener(noDoubleClickListener);
        mEditAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

           /*     if (b) {
                    linIphone.setBackgroundColor(Color.parseColor("#BDBDBD"));
                } else {
                    linIphone.setBackgroundColor(Color.parseColor("#EFEFEF"));
                }*/
            }
        });
        mEditPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {


/*
                if (b) {
                    linPsd.setBackgroundColor(Color.parseColor("#BDBDBD"));
                } else {
                    linPsd.setBackgroundColor(Color.parseColor("#EFEFEF"));
                }*/
            }
        });


    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mRlAccountDelete) {
                mEditAccount.setText("");
            } else if (view == mRlPasswordDisplay) {
                if (showPassword) {// 显示密码

                    mIvPasswordDisplay.setImageResource(R.mipmap.ic_login_display);
                    mEditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mEditPassword.setSelection(mEditPassword.getText().toString().length());
                    showPassword = !showPassword;
                } else {// 隐藏密码

                    mIvPasswordDisplay.setImageResource(R.mipmap.ic_login_hide);
                    mEditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mEditPassword.setSelection(mEditPassword.getText().toString().length());
                    showPassword = !showPassword;
                }
            } else if (view == mBtnLogin) {
                //走登录流程
                requestLogin();
            } else if (view == mTvForgetPassword) {
                //走忘记密码的流程,忘记密码需要先输入一次手机号码
                startActivity(EditAccountInputPhoneActivity.getIntent(mContext, EditAccountInputPhoneActivity.class, "1", "forget"));
            }
            else if (view == mTvRegister) {
                //走注册流程
//                startActivity(RegisterActivity.getIntent(mContext, RegisterActivity.class));
                OneKeyLoginManager.getInstance().setAuthThemeConfig(ConfigUtils.getCJSConfig(getApplicationContext()));
                openLoginActivity();
            }
            else if (view == mIvBack) {
                Intent intent = new Intent(mContext, HomeActivity.class);
                intent.putExtra("go_home", "");
                startActivity(intent);

                finish();
            } else if (view == iv_change_type || view == ll_change_type) {

                AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams p = window.getAttributes();
                p.width = WindowManager.LayoutParams.MATCH_PARENT;
                p.height = WindowManager.LayoutParams.MATCH_PARENT;
                p.dimAmount = 0.5f;
                window.setAttributes(p);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                window.setContentView(R.layout.login_change_type);
                LinearLayout ll_business_manage = window.findViewById(R.id.ll_business_manage);
                ll_business_manage.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginActivity.this,DcloudActvity.class);
                        startActivity(intent);
                    }
                });
                LinearLayout linearLayout = window.findViewById(R.id.linearlayout_driver);
                LinearLayout ll_driver = window.findViewById(R.id.ll_driver);//司机端登录
                LinearLayout ll_purchase = window.findViewById(R.id.ll_purchase);//司机端登录


                linearLayout.getBackground().setAlpha(80);

                LinearLayout linearLayout1 = window.findViewById(R.id.tv_tool);
                linearLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                LinearLayout linearLayout2 = window.findViewById(R.id.ll_two);
                linearLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                ll_driver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
////https://shaokao.qoger.com/apph5/html/OrderList.html
                        //http://120.55.55.99:8082/apph5/html/OrderList.html
                        String url = "https://shaokao.qoger.com/apph5/html/OrderList.html";

                        Intent intent = new Intent(mContext, WebDriverActivity.class);
                        intent.putExtra("URL", url);
                        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
                            AppHelper.showMsg(mContext, "网络不给力!");
                        } else {
                            startActivity(intent);
                        }

                        alertDialog.dismiss();
                    }
                });

        /*        ll_purchase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url ="http://120.55.55.99:8082/apph5/BusinessApp/index.html";

                        Intent intent = new Intent(mContext, NewWebViewActivity.class);
                        intent.putExtra("URL", url);
                        intent.putExtra("name", "");
                        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
                            AppHelper.showMsg(mContext, "网络不给力!");
                        }else {
                            startActivity(intent);
                        }

                        alertDialog.dismiss();
                    }
                });*/
            }
        }
    };
    private String token1;

    private void openLoginActivity() {
        //开始拉取授权页
        OneKeyLoginManager.getInstance().openLoginAuth(false, new OpenLoginAuthListener() {
            @Override
            public void getOpenLoginAuthStatus(int code, String result) {

                if (1000 == code) {
                    Log.e("VVV", "拉起授权页成功： code==" + code + "   result==" + result);
                } else {
                    Log.e("VVV", "拉起授权页失败： code==" + code + "   result==" + result);
                    Intent intent = new Intent(LoginActivity.this,RegisterMessageActivity.class);
                    startActivity(intent);
                }
            }
        }, new OneKeyLoginListener() {
            @Override
            public void getOneKeyLoginStatus(int code, String result) {

                if (1011 == code) {
                    Log.e("VVV", "用户点击授权页返回： code==" + code + "   result==" + result);
                    return;
                } else if (1000 == code) {
                    Log.e("VVV", "用户点击登录获取token成功： code==" + code + "   result==" + result);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result);
                        token1 = jsonObject.getString("token");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    checks(token1);
                } else {
                    Log.e("VVV", "用户点击登录获取token失败： code==" + code + "   result==" + result);
                }
            }
        });
    }

    private void checks(String result) {
        GetCustomerPhoneAPI.getData(mContext,result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OneRegisterModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(OneRegisterModel baseModel) {
                        if (baseModel.success) {
                            Intent intent = new Intent(LoginActivity.this,RegisterStep1Activity.class);
                            intent.putExtra("phone",baseModel.data);
                            intent.putExtra("token1",result);
                            startActivity(intent);
                            OneKeyLoginManager.getInstance().finishAuthActivity();
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                            OneKeyLoginManager.getInstance().finishAuthActivity();
                        }
                    }
                });
    }


    private void applyBlur() {

        // 获取壁纸管理器
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
        // 获取当前壁纸
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();
        // 将Drawable,转成Bitmap
        Bitmap bmp = ((BitmapDrawable) wallpaperDrawable).getBitmap();

        blur(bmp);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blur(Bitmap bkg) {
        long startMs = System.currentTimeMillis();
        float radius = 20;

        bkg = small(bkg);
        Bitmap bitmap = bkg.copy(bkg.getConfig(), true);

        final RenderScript rs = RenderScript.create(mContext);
        final Allocation input = Allocation.createFromBitmap(rs, bkg, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(radius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);

        bitmap = big(bitmap);
        mRelative.setBackground(new BitmapDrawable(getResources(), bitmap));
        rs.destroy();
        Log.d("zhangle", "blur take away:" + (System.currentTimeMillis() - startMs) + "ms");
    }

    private static Bitmap big(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(4f, 4f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    private static Bitmap small(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(0.25f, 0.25f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }


    private void requestLogin() {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            AppHelper.showMsg(mContext, "网络不给力!");
        } else {
            LoginAPI.requestLogin(mContext, mEditAccount.getText().toString(), mEditPassword.getText().toString(), "", 2)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<LoginModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(LoginModel loginModel) {
                            mModelLogin = loginModel;
                            if (mModelLogin.success) {
                                updateLogin();
                            } else {
                                AppHelper.showMsg(mContext, mModelLogin.message);
                            }
                        }
                    });
        }
    }//cityName

    private void updateLogin() {
        AppHelper.showMsg(mContext, "登录成功");

        UserInfoHelper.saveUserId(mContext, mModelLogin.data.token);
        UserInfoHelper.saveUserCell(mContext, mModelLogin.data.userBaseInfoVO.phone);
        UserInfoHelper.saveUserType(mContext, String.valueOf(mModelLogin.data.userBaseInfoVO.type));
        UserInfoHelper.saveCity(mContext, city);


        //登录成功,登录状态有变化,需要让
        UserInfoHelper.saveUserHomeRefresh(mContext, "");
        UserInfoHelper.saveUserMarketRefresh(mContext, "");
        Intent intent = new Intent(mContext,HomeActivity.class);
        startActivity(intent);
        EventBus.getDefault().post(new LogoutsEvent());
        finish();
//        UserInfoHelper.saveUserHomeRefresh(mContext, "");
//        UserInfoHelper.saveUserMarketRefresh(mContext, "");
//        startActivity(new Intent(mContext, HomeActivity.class));
//        EventBus.getDefault().post(new LogoutsEvent());
//
//        finish();
    }

//    public class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
//            //以下只列举部分获取地址相关的结果信息
//            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
//
//            UserInfoHelper.saveLocation(mContext,location.getAddrStr());
//
//            String country = location.getCountry();    //获取国家
//            String province = location.getProvince();    //获取省份
//            city = location.getCity();    //获取城市
//            String district = location.getDistrict();    //获取区县
//            String street = location.getStreet();    //获取街道信息
//            String streetNumber = location.getStreetNumber();
//
//
//
//            Log.i("city..............",location.getLocType()+"");
//
//
//        }
//    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //禁止空格输入
            if (s.toString().contains(" ")) {
                String[] str = s.toString().split(" ");
                String str1 = "";
                for (int i = 0; i < str.length; i++) {
                    str1 += str[i];
                }
                mEditPassword.setText(str1);
                mEditPassword.setSelection(start);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (StringHelper.notEmptyAndNull(mEditAccount.getText().toString())
                    && StringHelper.notEmptyAndNull(mEditPassword.getText().toString())) {
                //两个都输入了
                mBtnLogin.setEnabled(true);
                //  mBtnLogin.setTextColor(getResources().getColor(R.color.app_color_white));
            } else {
                //数据输入不完全
                mBtnLogin.setEnabled(false);
                //  mBtnLogin.setTextColor(getResources().getColor(R.color.app_btn_unable));
            }

            if (s.length() > 0) {
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (c >= 0x4e00 && c <= 0X9fff) { // 根据字节码判断
                        // 如果是中文，则清除输入的字符，否则保留
                        s.delete(i, i + 1);
                    }
                }

            }
        }
    };


    //此方法只是关闭软键盘
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hintKbTwo();
    }

    @Override
    public void onPause() {
        super.onPause();
        hintKbTwo();
    }


    // /获取本机IP地址

    public static String getLocalIpAddress(Context ctx) {
        WifiManager wifiManager = (WifiManager) ctx
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        try {
            return InetAddress.getByName(
                    String.format("%d.%d.%d.%d", (ipAddress & 0xff),
                            (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff),
                            (ipAddress >> 24 & 0xff))).toString();
        } catch (UnknownHostException e) {
            return null;
        }

    }


    /**
     * Android  6.0 之前（不包括6.0）
     * 必须的权限  <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     *
     * @param context
     * @return
     */
    private static String getMacDefault(Context context) {
        String mac = "02:00:00:00:00:00";
        if (context == null) {
            return mac;
        }

        WifiManager wifi = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if (wifi == null) {
            return mac;
        }
        WifiInfo info = null;
        try {
            info = wifi.getConnectionInfo();
        } catch (Exception e) {
        }
        if (info == null) {
            return null;
        }
        mac = info.getMacAddress();
        if (!TextUtils.isEmpty(mac)) {
            mac = mac.toUpperCase(Locale.ENGLISH);
        }
        return mac;
    }

    /**
     * Android 6.0（包括） - Android 7.0（不包括）
     *
     * @return
     */
    private static String getMacAddress() {
        String WifiAddress = "02:00:00:00:00:00";
        try {
            WifiAddress = new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WifiAddress;
    }

    /**
     * 遍历循环所有的网络接口，找到接口是 wlan0
     * 必须的权限 <uses-permission android:name="android.permission.INTERNET" />
     *
     * @return
     */
    private static String getMacFromHardware() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }


    /**
     * 获取MAC地址
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        String mac = "02:00:00:00:00:00";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mac = getMacDefault(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mac = getMacAddress();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mac = getMacFromHardware();
        }
        Log.i("wwb", "getMacAddress: " + mac);
        return mac;
    }
}
