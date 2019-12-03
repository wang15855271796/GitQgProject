package com.puyue.www.qiaoge.activity;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.AppSafeHelper;
import com.puyue.www.qiaoge.helper.DeviceHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

/**
 * H5 混合开发交互页面 邀请页面
 * 之后都用NewWebViewActivity
 * Created by Administrator on 2018/4/18.
 */

public class CommonH5Activity extends BaseSwipeActivity {
    public static final String URL = "url";

    public static final String TAG = CommonH5Activity.class.getSimpleName();

    private TextView mTvTitle;
    private ProgressBar mProgress;
    private WebView mWv;

    private String mUrl;
    private FrameLayout mFram;
    private ImageView mIvBack;

    public static Intent getIntent(Context context, Class<?> cls, String url) {
        Intent intent = new Intent();
        intent.putExtra(URL, url);
        intent.setClass(context, cls);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        mUrl = getIntent().getStringExtra(URL);
        if (savedInstanceState != null) {
            mUrl = savedInstanceState.getString(URL);
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(URL, mUrl);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_new_h5);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_h5_back);
        mTvTitle = (TextView) findViewById(R.id.tv_h5_title);
        //这个是h5的页面
        mFram = (FrameLayout) findViewById(R.id.frm_h5);
        mProgress = (ProgressBar) findViewById(R.id.progress_h5);
    }

    /**
     * WebView 的初始化设置
     */
    @Override
    public void setViewData() {
        mWv = new WebView(mContext.getApplicationContext());
        mFram.addView(mWv, 0);
        //返回键
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (mWv.canGoBack()) {
                    mWv.goBack();
                } else {
                    finish();
                }
            }
        });
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            //没网的情况下,让webview消失
            AppHelper.showMsg(mContext, "网络链接不可用!");
            mWv.setVisibility(View.GONE);
        } else {
            mWv.setVisibility(View.VISIBLE);
            //before WebView setting
//            setCookie();
            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            mWv.getSettings().setJavaScriptEnabled(true);
            //
            mWv.getSettings().setDomStorageEnabled(true);
            mWv.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
            String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
            mWv.getSettings().setAppCachePath(appCachePath);
            mWv.getSettings().setAllowFileAccess(true);
            mWv.getSettings().setAppCacheEnabled(true);
//取消登录之后再次点击邀请好友出现弹窗（请先登录）


            mWv.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    mTvTitle.setText(title);
                }

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress != 100) {
                        mProgress.setProgress(newProgress);
                        mProgress.setVisibility(View.VISIBLE);
                    } else {
                        mProgress.setVisibility(View.GONE);
                    }
                    super.onProgressChanged(view, newProgress);
                }
            });
            mWv.setWebViewClient(new WebViewClient() {
                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    //WebView被允许访问SSL证书网页
                    handler.proceed();
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    view.loadUrl(url);
//                    return true;
                    return false;
                }

                @Override
                public void onLoadResource(WebView view, String url) {
                    super.onLoadResource(view, url);
                }
            });
            mWv.loadUrl(mUrl);
//            Log.e(TAG, "setViewData: "+mUrl );
        }
    }


    @Override
    public void setClickEvent() {

    }

    /**
     * 退出处理
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWv.canGoBack()) {
                mWv.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 这个地方是对Cookie进行管理。
     */
    @Override
    protected void onDestroy() {
        CookieSyncManager.createInstance(mContext);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeSessionCookie();
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
        mWv.onPause();
        mWv.destroy();
        mWv = null;
        mFram.removeAllViews();
        System.gc();
        super.onDestroy();
    }


    /**
     * 通过cookie添加公共入参（js去获取cookie）
     */
    private void setCookie() {

//        //后期修改
//        UserModel userModel = (UserModel) CommonMethod.getObject(CommonH5Activity.this, UserModel.class, Const.USERMODEL);
//        String userId = null;
//        if (userModel != null) {
//            userId = userModel.getUserId();
//        }
        String stime = AppSafeHelper.getSTime();
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(mWv, true);
        }
        cookieManager.setCookie(mUrl, "userId=" + UserInfoHelper.getUserId(CommonH5Activity.this));
        cookieManager.setCookie(mUrl, "stime=" + stime);
        cookieManager.setCookie(mUrl, "sign=" + AppSafeHelper.sign(stime));
        cookieManager.setCookie(mUrl, "version=" + AppHelper.getVersion(CommonH5Activity.this));
        cookieManager.setCookie(mUrl, "token=" + DeviceHelper.getDeviceId(CommonH5Activity.this));
        cookieManager.setCookie(mUrl, "appType=" + AppConstant.APP_TYPE);
        cookieManager.setCookie(mUrl, "platformCode=" + "YOUMI");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWv.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }


}
