package com.puyue.www.qiaoge.activity.mine.wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alipay.sdk.app.PayTask;
import com.puyue.www.qiaoge.QiaoGeApplication;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.RechargeAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.WeChatPayEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.RechargeModel;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/9.
 */

public class RechargeActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private EditText mEtAmount;
    private RadioGroup mRgType;
    private RadioButton mRbAlipay;
    private RadioButton mRbWechat;
    private Button mBtnSure;

    private String outTradeNo;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_recharge);
        EventBus.getDefault().register(this);
    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_recharge_back);
        mEtAmount = FVHelper.fv(this, R.id.et_activity_amount);
        mRgType = FVHelper.fv(this, R.id.rg_activity_type);
        mRbAlipay = FVHelper.fv(this, R.id.rb_activity_alipay);
        mRbWechat = FVHelper.fv(this, R.id.rb_activity_wechat);
        mBtnSure = FVHelper.fv(this, R.id.btn_activity_sure);
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mBtnSure.setOnClickListener(noDoubleClickListener);

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mBtnSure) {
                if (!StringHelper.notEmptyAndNull(mEtAmount.getText().toString())) {
                    AppHelper.showMsg(mContext, "请输入金额");
                } else if (mRbAlipay.isChecked()) {
                    recharge(Double.parseDouble(mEtAmount.getText().toString()), (byte) 2);
                } else if (mRbWechat.isChecked()) {
                    recharge(Double.parseDouble(mEtAmount.getText().toString()), (byte) 3);
                } else {
                    AppHelper.showMsg(mContext, "请选择渠道");
                }

            }
        }
    };

    /**
     * 充值接口
     * rechargeType 2 支付宝，3微信，1 网银|byte
     */
    private void recharge(double amount, final byte rechargeType) {
        RechargeAPI.requestData(mContext, amount, rechargeType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RechargeModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RechargeModel rechargeModel) {
                        logoutAndToHome(mContext, rechargeModel.code);
                        if (rechargeModel.success) {
                            if (rechargeType == 2) {
                                //支付宝支付
                                outTradeNo = rechargeModel.data.outTradeNo;
                                aliPay(rechargeModel.data.payToken);
                            } else if (rechargeType == 3) {
                                outTradeNo = rechargeModel.data.outTradeNo;
                                weChatPay(rechargeModel.data.payToken);
                            }
                        } else {
                            AppHelper.showMsg(mContext, rechargeModel.message);
                        }

                    }
                });
    }

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

    }

    /**
     * 微信支付的回调,使用的eventBus.......((‵□′))
     **/
    @Subscribe
    public void onEventMainThread(WeChatPayEvent event) {
        Intent intent = new Intent(mContext, WalletResultActivity.class);
        intent.putExtra(AppConstant.PAYCHANNAL, 3);
        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
        intent.putExtra(AppConstant.PAGETYPE, "recharge");
        startActivity(intent);
        finish();
    }

    /**
     * 支付宝支付
     */
    private void aliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
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
                        Intent intent = new Intent(mContext, WalletResultActivity.class);
                        intent.putExtra(AppConstant.PAYCHANNAL, 2);
                        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
                        intent.putExtra(AppConstant.PAGETYPE, "recharge");
                        startActivity(intent);
                        finish();
                    } else if ("6001".equals(result.get("resultStatus"))) {
                        //用户取消支付
                        AppHelper.showMsg(mContext, "您已取消充值");
                    } else if ("6002".equals(result.get("resultStatus"))) {
                        //网络连接错误
                        AppHelper.showMsg(mContext, "网络连接错误");
                    } else {
                        //支付失败
                        Intent intent = new Intent(mContext, WalletResultActivity.class);
                        intent.putExtra(AppConstant.PAYCHANNAL, 2);
                        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
                        intent.putExtra(AppConstant.PAGETYPE, "recharge");
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
