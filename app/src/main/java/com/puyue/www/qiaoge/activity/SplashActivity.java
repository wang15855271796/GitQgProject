package com.puyue.www.qiaoge.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseActivity;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2018/5/21.
 */

public class SplashActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {
//        SharedPreferencesUtil.saveString(mContext,"once","0");
        //进行Android 6.0的动态权限申请
        requestAndroidSixPermissions();


    }

    @Override
    public void setClickEvent() {

    }
    private void requestAndroidSixPermissions() {
        String[] params = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, params)) {//检查是否获取该权限
            //全部允许
            handleSplash();
        } else {//第二次请求
            //存在不允许的权限  对话框为什么一会出来一会不出来
            EasyPermissions.requestPermissions(this, "需要加载必要的权限。", 1, params);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handleSplash();

    }


    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {


    }
    /**
     * 闪屏处理
     */
    private void handleSplash() {

        //对于银联支付没有回调的处理方式

        // 闪屏的核心代码
        SharedPreferencesUtil.getString(mContext,"once").equals("-1");
//        if(SharedPreferencesUtil.getString(mContext,"once").equals("0")) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    intent.putExtra("go_home", "goHome");
                    startActivity(intent);
                    finish();
                }
            });
//        }else {
//            Intent intent = new Intent(mContext, TestActivity.class);
//            intent.putExtra("two", "goHome");
//            startActivity(intent);
//            finish();
//        }

    }
}
