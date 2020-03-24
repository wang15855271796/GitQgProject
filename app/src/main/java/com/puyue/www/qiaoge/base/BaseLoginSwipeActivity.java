package com.puyue.www.qiaoge.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Created by Administrator on 2018/5/11.
 */

public abstract class BaseLoginSwipeActivity extends SwipeBackActivity {
    private long mExitTime = 0;
    private boolean mIsExit = false;
    protected Context mContext;
    protected Activity mActivity;
    protected Resources mResources;
    protected Bundle mBundle;

    public static Intent getIntent(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView();
        mContext = this;
        mActivity = this;
        mResources = this.getResources();
        mBundle = savedInstanceState;
//        Sofia.with(mActivity).statusBarDarkFont()
//                .statusBarBackground(Color.parseColor("#ffffff"));
        findViewById();
        setViewData();
        setClickEvent();
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

    /**
     * //必须在super.OnCreate()之前调用，因为titlebar
     * 处理Extra数据，意味着需要同时实现onSaveInstanceState和onRestoreInstanceState
     * 技巧：紧接着就在它后面重写
     * onSaveInstanceState-存储：解决突发状况，比如电话进来
     * onRestoreInstanceState-获取
     *
     * @param savedInstanceState
     * @return
     */
    public abstract boolean handleExtra(Bundle savedInstanceState);

    /**
     * 设置ContentView
     */
    public abstract void setContentView();

    /**
     * 初始化Views
     */
    public abstract void findViewById();

    /**
     * 填充View数据
     */
    public abstract void setViewData();

    /**
     * 设置View点击事件监听器
     */
    public abstract void setClickEvent();

    /**
     * 是否允许退出App，默认false
     *
     * @param isExit
     */
    protected void keyBackExitApp(boolean isExit) {
        mIsExit = isExit;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mIsExit) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                   AppHelper.showMsg(this, "再按一次退出程序！");
                    mExitTime = System.currentTimeMillis();
                    return true;
                } else {
                    finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void logoutAndToHome(Context context) {
        //清空UserId
        UserInfoHelper.saveUserId(mContext, "");
//        UserInfoHelper.saveUserCell(mContext, "");
        UserInfoHelper.saveUserType(context, "");
        startActivity(new Intent(context, HomeActivity.class));

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
}
