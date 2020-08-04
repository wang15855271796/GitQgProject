package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.home.QueryHomePropupAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.event.CouponListModel;
import com.puyue.www.qiaoge.fragment.cart.NumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static cn.com.chinatelecom.account.api.CtAuth.mContext;

/**
 * Created by ${王涛} on 2020/4/15
 * 隐私弹窗
 */
public class PrivacyDialog extends Dialog {

    Context mContext;
    public TextView tv_sure;
    WebView webView;
    String content;
    TextView tv_account;
    TextView tv_second;
    CountDownTimer countDownTimer;
    CheckBox checkbox;
    LinearLayout ll_sure;
    private List<CouponListModel.DataBean.GiftsBean> lists;

    public PrivacyDialog(@NonNull Context context, String content) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_privacy);
        mContext = context;
        this.content = content;
        initView();
        initAction();
        handleCountDown();
    }

    private void handleCountDown() {
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                checkbox.setEnabled(true);
                ll_sure.setBackgroundResource(R.drawable.shape_orange);
                ll_sure.setEnabled(true);
                tv_second.setText("("+millisUntilFinished / 1000 +"s"+")");
                tv_second.setTextColor(Color.parseColor("#F56D23"));

            }

            @Override
            public void onFinish() {
                checkbox.setEnabled(true);
                tv_second.setVisibility(View.GONE);
                ll_sure.setEnabled(true);
                ll_sure.setBackgroundResource(R.drawable.shape_orange);
                tv_second.setTextColor(Color.parseColor("#F56D23"));


            }
        }.start();
    }

    private void initView() {
        checkbox = findViewById(R.id.checkbox);
        ll_sure = findViewById(R.id.ll_sure);
        webView= findViewById(R.id.webView);
        tv_second = findViewById(R.id.tv_second);
        webView.loadUrl(content);
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int scale = dm.densityDpi;
        if (scale == 240) { //设置自动适配
            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (scale == 160) {
            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else {
            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }

        tv_account = findViewById(R.id.tv_account);
        tv_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(CommonH5Activity.getIntent(mContext, CommonH5Activity.class, content));
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        settings.setSupportMultipleWindows(false);//这里一定得是false,不然打开的网页中，不能在点击打开了


        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = mContext.getCacheDir().getAbsolutePath();
        settings.setAppCachePath(appCachePath);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        settings.setDefaultFixedFontSize(14);
        tv_sure = (TextView) findViewById(R.id.tv_sure);
    }


    private void initAction() {

        ll_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkbox.isChecked()) {
                    ToastUtil.showErroMsg(mContext,"请勾选");

                }else {
                    readPrivacy();
                }
            }
        });
    }

    /**
     * 隐私弹窗
     */
    private void readPrivacy() {
        IndexHomeAPI.readPrivacy(mContext)
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
                        if(baseModel.success) {
                            dismiss();
                            SharedPreferencesUtil.saveString(mContext,"once","0");
                            getCouponList();
                        }else {
                            AppHelper.showMsg(mContext,baseModel.message);
                        }
                    }
                });
    }

    CouponListDialog couponListDialog;
    private void getCouponList() {
        IndexHomeAPI.getCouponLists(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CouponListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CouponListModel couponListModel) {
                        if(couponListModel.isSuccess()) {
                            lists = couponListModel.getData().getGifts();
                            if(lists.size()>0) {
                                couponListDialog = new CouponListDialog(mContext,couponListModel, lists);
                                couponListDialog.show();
                            }else {
                                QueryHomePropup();
                            }

                        }else {
                            AppHelper.showMsg(mContext,couponListModel.getMessage());
                        }
                    }
                });
    }

    /**
     *
     * 首页活动弹窗
     */
    HomeActivityDialog homeActivityDialog;
    private void QueryHomePropup() {
        QueryHomePropupAPI.requestQueryHomePropup(mContext)
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
                            if(queryHomePropupModel.getData().getHomePropup()!=null) {
                                QueryHomePropupModel.DataBean.HomePropupBean homePropup = queryHomePropupModel.getData().getHomePropup();
                                homeActivityDialog = new HomeActivityDialog(mContext,homePropup);
                                if (queryHomePropupModel.getData().isPropup()) {
                                    homeActivityDialog.show();
                                }else {
                                    homeActivityDialog.dismiss();
                                }
                            }

                        } else {
                            AppHelper.showMsg(mContext, queryHomePropupModel.getMessage());
                        }
                    }
                });
    }

}
