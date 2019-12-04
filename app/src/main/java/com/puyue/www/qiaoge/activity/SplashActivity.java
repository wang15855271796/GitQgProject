package com.puyue.www.qiaoge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseActivity;
import com.puyue.www.qiaoge.helper.UserInfoHelper;

/**
 * Created by Administrator on 2018/5/21.
 */

public class SplashActivity extends BaseActivity {


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


        handleSplash();
    }

    @Override
    public void setClickEvent() {

    }

    /**
     * 闪屏处理
     */
    private void handleSplash() {
        // 闪屏的核心代码
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(mContext, HomeActivity.class);
                intent.putExtra("go_home", "goHome");
                startActivity(intent);
                finish();
            }
        }, 1000);
    }


}
