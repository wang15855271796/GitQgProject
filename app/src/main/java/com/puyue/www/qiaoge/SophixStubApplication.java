package com.puyue.www.qiaoge;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Keep;
import android.support.multidex.MultiDex;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.bugly.crashreport.CrashReport;

public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";

    //    此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(QiaoGeApplication.class)
    static class RealApplicationStub {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SophixManager.getInstance().queryAndLoadNewPatch();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        如果需要使用MultiDex，需要在此处调用。
        MultiDex.install(this);

        initSophix();
    }

    private void initSophix() {
        String appVersion = "1.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setSecretMetaData(null, null, null)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {

                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            //创建一个SharePreferences接口的实例对象，将生成一个XML名称为DATA_RELOAD，模式为MODE_PRIVATE
                            SharedPreferences sp= getSharedPreferences("DATA_RELOAD",MODE_PRIVATE);
                            //通过edit()方法创建一个SharePreferences.Editor类的实例对象
                            SharedPreferences.Editor editor =sp.edit();
                            //通过putString()方法，将数据存入文件中
                            editor.putBoolean("isReload",true);
                            //用commit()方法予以正式提交
                            editor.commit();
                        }
                    }
                }).initialize();
    }


}
