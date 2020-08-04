package com.puyue.www.qiaoge.activity.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alipay.sdk.app.PayTask;
import com.chinaums.pppay.unify.UnifyPayPlugin;
import com.chinaums.pppay.unify.UnifyPayRequest;
import com.puyue.www.qiaoge.QiaoGeApplication;
import com.puyue.www.qiaoge.R;

import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.order.VipPayResultActivity;
import com.puyue.www.qiaoge.api.mine.order.CopyToCartAPI;
import com.puyue.www.qiaoge.api.mine.order.VipPayAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.WeChatPayEvent;
import com.puyue.www.qiaoge.event.WeChatUnPayEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.order.CopyToCartModel;
import com.puyue.www.qiaoge.model.mine.order.VipPayModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注  我的积分支付页面
 */
public class IntegralPayActivity extends BaseSwipeActivity {

    private ImageView imageViewBack;
    private ImageView alipay;
    private ImageView wechat;
    private ImageView okPay;
    private String vipPackageId;
    private int payChannel;  // 支付渠道   2=支付宝   3=微信
    private String outTradeNo;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    AVLoadingIndicatorView lav_activity_loading;
    private Handler handler = new Handler();
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_integral_pay);

    }

    @Override
    public void findViewById() {
        lav_activity_loading = (AVLoadingIndicatorView) findViewById(R.id.lav_activity_loading);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        alipay = (ImageView) findViewById(R.id.rb_activity_order_alipay);
        wechat = (ImageView) findViewById(R.id.rb_activity_order_wechat);
        okPay = (ImageView) findViewById(R.id.okPay);
    }

    @Override
    public void setViewData() {
        EventBus.getDefault().register(this);
        vipPackageId = getIntent().getStringExtra("vipPackageId");
    }

    @Override
    public void setClickEvent() {
        imageViewBack.setOnClickListener(noDoubleClickListener);
        alipay.setOnClickListener(noDoubleClickListener);
        wechat.setOnClickListener(noDoubleClickListener);
        okPay.setOnClickListener(noDoubleClickListener);
    }

    public NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {

            switch (view.getId()) {
                case R.id.imageViewBack:
                    finish();
                    break;
                case R.id.rb_activity_order_alipay:
                    payChannel = 2;
                    alipay.setImageResource(R.mipmap.ic_pay_ok);
                    wechat.setImageResource(R.mipmap.ic_pay_no);
                    break;
                case R.id.rb_activity_order_wechat:
                    payChannel = 3;
                    alipay.setImageResource(R.mipmap.ic_pay_no);
                    wechat.setImageResource(R.mipmap.ic_pay_ok);

                    break;
                case R.id.okPay:
                    if (payChannel==0){
                        AppHelper.showMsg(mContext, "请选择支付方式");
                        return;
                    }
                    lav_activity_loading.setVisibility(View.VISIBLE);
                    lav_activity_loading.show();
                    requestVipPay();
                    break;
            }
        }
    };


    private void  requestVipPay() {
        VipPayAPI.requestVipPayData(mContext, vipPackageId,payChannel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VipPayModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(VipPayModel vipPayModel) {
                        if (vipPayModel.isSuccess()) {
                            outTradeNo = vipPayModel.getData().getOutTradeNo();
                            if (payChannel == 2&&vipPayModel.getData().getPayType()==2) {
                                //支付宝支付
                                aliPay(vipPayModel.getData().getPayToken());
                            } else if (payChannel == 3) {
                                //微信支付
                                weChatPay(vipPayModel.getData().getPayToken());
                            }else if(vipPayModel.getData().getPayType()==14&&payChannel == 2) {
                                //银联
                                payAliPay(vipPayModel.getData().getPayToken());
                            }
                        } else {
                            AppHelper.showMsg(mContext, vipPayModel.getMessage());
                        }
                    }
                });
    }

    private void payAliPay(String parms) {
        UnifyPayRequest msg = new UnifyPayRequest();
        msg.payChannel = UnifyPayRequest.CHANNEL_ALIPAY;
        msg.payData = parms;
        UnifyPayPlugin.getInstance(this).sendPayRequest(msg);
        lav_activity_loading.setVisibility(View.GONE);
        lav_activity_loading.hide();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = MyOrdersActivity.getIntent(getContext(), HomeActivity.class, AppConstant.ALL);
                intent.putExtra("orderDeliveryType",0);
                startActivity(intent);
                finish();
            }
        },20000);
    }


    //---------------------------支付逻辑------------------------------------------------//
    /**
     * 微信支付
     */

    private void weChatPay(String json) {
        try {
            IWXAPI api = WXAPIFactory.createWXAPI(this, "wxbc18d7b8fee86977");
            JSONObject obj = new JSONObject(json);
            PayReq request = new PayReq();
            request.appId = obj.optString("appId");
            request.partnerId = obj.optString("mchID");
            request.prepayId = obj.optString("prepayId");
            request.packageValue = obj.optString("pkg");
            request.nonceStr = obj.optString("nonceStr");
            request.timeStamp = obj.optString("timeStamp");
            request.sign = obj.optString("paySign");
           api.sendReq(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lav_activity_loading.setVisibility(View.GONE);
        lav_activity_loading.hide();

    }

    /**
     * 微信支付的回调,使用的eventBus.......((‵□′))
     **/
    @Subscribe
    public void onEventMainThread(WeChatPayEvent event) {
        Intent intent = new Intent(mContext, VipPayResultActivity.class);
        intent.putExtra("payChannal", payChannel);
        intent.putExtra("outTradeNo", outTradeNo);
        startActivity(intent);
        finish();
    }

    /**
     * 微信支付用户已取消支付的回调,使用的eventBus.......((‵□′))
     **/
    @Subscribe
    public void onEventMainThread(WeChatUnPayEvent event) {
    }


    /**
     * 支付宝支付
     */
    private void aliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(IntegralPayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        lav_activity_loading.setVisibility(View.GONE);
        lav_activity_loading.hide();
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝支付结果
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    if ("9000".equals(result.get("resultStatus"))) {
                        //支付成功
                        Intent intent = new Intent(mContext, VipPayResultActivity.class);
                        intent.putExtra("payChannal", payChannel);
                        intent.putExtra("outTradeNo", outTradeNo);
                        startActivity(intent);
                        finish();
                    } else if ("6001".equals(result.get("resultStatus"))) {
                        //用户取消支付
                        AppHelper.showMsg(mContext, "您已取消支付");
                    } else if ("6002".equals(result.get("resultStatus"))) {
                        //网络连接错误
                        AppHelper.showMsg(mContext, "网络连接错误");
                    } else {
                        //支付失败
                        Intent intent = new Intent(mContext, VipPayResultActivity.class);
                        intent.putExtra("payChannal", payChannel);
                        intent.putExtra("outTradeNo", outTradeNo);
                        startActivity(intent);
                        finish();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
