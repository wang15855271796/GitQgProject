package com.puyue.www.qiaoge;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.InitListener;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2018/3/21.
 */

public class QiaoGeApplication extends MultiDexApplication {
    public IWXAPI api;


    @Override
    public void onCreate() {
        super.onCreate();



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

    }


}
