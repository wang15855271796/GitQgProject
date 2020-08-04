package com.puyue.www.qiaoge;

import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2018/3/21.
 */

public class QiaoGeApplication extends MultiDexApplication {
    public IWXAPI api;
//    public LocationClient mLocationClient = null;
//    private MyLocationListener myListener = new MyLocationListener();

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtil.saveString(this,"pays","-1");
        Log.d("weeeeeeeeeee.....","000");
        CrashReport.initCrashReport(getApplicationContext(), "385e5aaa75", false);
        SDKInitializer.initialize(getApplicationContext());
//        mLocationClient = new LocationClient(getApplicationContext());
//        //声明LocationClient类
//        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true
//
//        mLocationClient.setLocOption(option);
//        mLocationClient.start();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);



        UserInfoHelper.saveDate(this, 0+"");
        api = WXAPIFactory.createWXAPI(this, "wxbc18d7b8fee86977");
        api.registerApp("wxbc18d7b8fee86977");
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);

        {

            PlatformConfig.setWeixin("wxbc18d7b8fee86977", "710d1b08a6fd655ca8b3e4404fd937cd");
            PlatformConfig.setQQZone("1106452431", "vgywMsj2j66nW35l");
        }
        UMConfigure.init(this, "5bcef11ab465f52b9d000094"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        disableAPIDialog();
    }

    private void disableAPIDialog(){
        if (Build.VERSION.SDK_INT < 28)return;
        try {
            Class clazz = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = clazz.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            Object activityThread = currentActivityThread.invoke(null);
            Field mHiddenApiWarningShown = clazz.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
//            //以下只列举部分获取地址相关的结果信息
//            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
//
//            UserInfoHelper.saveLocation(getApplicationContext(),location.getAddrStr());
//
//            String country = location.getCountry();    //获取国家
//            String province = location.getProvince();    //获取省份
////            city = location.getCity();    //获取城市
//            String district = location.getDistrict();    //获取区县
//            String street = location.getStreet();    //获取街道信息
//            String streetNumber = location.getStreetNumber();
//
//
//
//            Log.i("citysss..............",location.getLocType()+"");
//
//
//        }
//    }

}
