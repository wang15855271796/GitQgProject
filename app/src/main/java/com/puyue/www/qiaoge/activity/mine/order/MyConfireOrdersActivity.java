package com.puyue.www.qiaoge.activity.mine.order;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.QiaoGeApplication;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.ConfirmOrderActivity;
import com.puyue.www.qiaoge.activity.cart.PayResultActivity;
import com.puyue.www.qiaoge.activity.mine.account.EditPasswordInputCodeActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletActivity;
import com.puyue.www.qiaoge.api.cart.CheckPayPwdAPI;
import com.puyue.www.qiaoge.api.cart.GetPayResultAPI;
import com.puyue.www.qiaoge.api.cart.OrderPayAPI;
import com.puyue.www.qiaoge.api.mine.AccountCenterAPI;
import com.puyue.www.qiaoge.api.mine.GetWalletAmountAPI;
import com.puyue.www.qiaoge.api.mine.order.GenerateOrderAPI;
import com.puyue.www.qiaoge.api.mine.order.MyOrderNumAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.WeChatPayEvent;
import com.puyue.www.qiaoge.event.WeChatUnPayEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CheckPayPwdModel;
import com.puyue.www.qiaoge.model.cart.GetPayResultModel;
import com.puyue.www.qiaoge.model.cart.OrderPayModel;
import com.puyue.www.qiaoge.model.mine.AccountCenterModel;
import com.puyue.www.qiaoge.model.mine.GetWalletAmountModel;
import com.puyue.www.qiaoge.model.mine.order.GenerateOrderModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;
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

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * @author daff
 * @date 2018/9/24.
 * 备注  订单支付页面
 */
public class MyConfireOrdersActivity extends BaseSwipeActivity {
    private ImageView imageViewBack;
    private ImageView okPay;

    private String remark;
    private double payAmount;

    private ImageView balancPay;
    private ImageView aliPay;
    private ImageView wechatPay;


    private String orderId;
    private String outTradeNo;
    private byte payChannel;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private TextView textViewAmount;
    private String num;
    private String mUserCell;
    private Handler handler = new Handler();

    private int orderDeliveryType;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_confrim_order);

    }

    @Override
    public void findViewById() {
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        okPay = (ImageView) findViewById(R.id.okPay);
        balancPay = FVHelper.fv(this, R.id.rb_activity_order_balance);
        aliPay = FVHelper.fv(this, R.id.rb_activity_order_alipay);
        wechatPay = FVHelper.fv(this, R.id.rb_activity_order_wechat);
        textViewAmount = FVHelper.fv(this, R.id.textViewAmount);
        getWalletAmount();
    }

    @Override
    public void setViewData() {
        EventBus.getDefault().register(this);
        orderId = getIntent().getStringExtra("orderId");
        payAmount = getIntent().getDoubleExtra("payAmount", 0.00);
        remark = getIntent().getStringExtra("remark");
        orderDeliveryType = getIntent().getIntExtra("orderDeliveryType", 0);

        UserInfoHelper.saveOrderId(mContext, orderId);
        UserInfoHelper.saveRemark(mContext, remark);
        UserInfoHelper.saveDeliverType(mContext,orderDeliveryType+"");
        UserInfoHelper.saveOrderAmount(mContext, String.valueOf(payAmount));
    }

    @Override
    public void setClickEvent() {
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderDeliveryType==0){
                    Intent intent = new Intent(mContext, NewOrderDetailActivity.class);
                    intent.putExtra("orderDeliveryType", orderDeliveryType);
                    intent.setClass(mContext, NewOrderDetailActivity.class);
                    intent.putExtra("orderId",orderId);
                    intent.putExtra(AppConstant.ORDERSTATE, "1");
                    startActivity(intent);
                }else if (orderDeliveryType==1){
                    Intent intent = new Intent(mContext, SelfSufficiencyOrderDetailActivity.class);
                    intent.putExtra("orderDeliveryType", orderDeliveryType);
                    intent.setClass(mContext, SelfSufficiencyOrderDetailActivity.class);
                    intent.putExtra("orderId",orderId);
                    intent.putExtra(AppConstant.ORDERSTATE, "1");
                    startActivity(intent);
                }


                // startActivity(MyOrdersActivity.getIntent(getContext(), MyOrdersActivity.class, AppConstant.ALL));
                overridePendingTransition(R.anim.slide_in_from_right,
                        R.anim.slide_out_from_left);
                finish();
            }
        });

        okPay.setOnClickListener(noDoubleClickListener);
        balancPay.setOnClickListener(noDoubleClickListener);
        aliPay.setOnClickListener(noDoubleClickListener);
        wechatPay.setOnClickListener(noDoubleClickListener);

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.rb_activity_order_balance: // 余额支付
                    payChannel = 1;
                    balancPay.setImageResource(R.mipmap.ic_pay_ok);
                    aliPay.setImageResource(R.mipmap.ic_pay_no);
                    wechatPay.setImageResource(R.mipmap.ic_pay_no);


                    break;
                case R.id.rb_activity_order_alipay: //支付宝支付
                    payChannel = 2;
                    aliPay.setImageResource(R.mipmap.ic_pay_ok);
                    balancPay.setImageResource(R.mipmap.ic_pay_no);
                    wechatPay.setImageResource(R.mipmap.ic_pay_no);
                    break;
                case R.id.rb_activity_order_wechat: //微信支付
                    payChannel = 3;
                    wechatPay.setImageResource(R.mipmap.ic_pay_ok);
                    aliPay.setImageResource(R.mipmap.ic_pay_no);
                    balancPay.setImageResource(R.mipmap.ic_pay_no);
                    break;
                case R.id.okPay:
                    if (payChannel == 0) {
                        AppHelper.showMsg(mContext, "请选择支付方式");
                        return;
                    } else {
                        if (payChannel == 1) {
                            String userWalletAccount = UserInfoHelper.getUserWalletAccount(mContext);

                            Log.i("account", "onNoDoubleClick: " + payAmount + "____" + Double.parseDouble(userWalletAccount));
//                            if (payAmount > Double.parseDouble(userWalletAccount)) {
//                                AlertDialog mDialog = new AlertDialog.Builder(mActivity).create();
//                                mDialog.show();
//                                Window window = mDialog.getWindow();
//                                window.setGravity(Gravity.CENTER);
//                                window.setContentView(R.layout.dialog_wallet_account);
//
//                                TextView tvCancel = window.findViewById(R.id.tv_sub_close);
//                                TextView tvConfirm = window.findViewById(R.id.tv_sub_confirm);
//                                //  ImageView iv_cancel = window.findViewById(R.id.iv_cancel);
//
//                             /*   iv_cancel.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        mDialog.dismiss();
//                                    }
//                                });*/
//                                tvCancel.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        mDialog.dismiss();
//                                    }
//                                });
//                                tvConfirm.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                        if ((payAmount - Double.parseDouble(userWalletAccount) < 1000) && (payAmount - Double.parseDouble(userWalletAccount) >= 0)) {
//                                            num = "3";
//                                        } else if ((payAmount - Double.parseDouble(userWalletAccount) < 3000) && (payAmount - Double.parseDouble(userWalletAccount) >= 1000)) {
//                                            num = "2";
//                                        } else if ((payAmount - Double.parseDouble(userWalletAccount) < 6000) && (payAmount - Double.parseDouble(userWalletAccount) >= 3000)) {
//                                            num = "1";
//                                        } else if ((payAmount - Double.parseDouble(userWalletAccount) > 6000)) {
//                                            num = "0";
//                                        }
//
//                                        UserInfoHelper.saveUserWalletNum(getContext(), num);
//
//                                        Intent intent = new Intent(mActivity, MyWalletActivity.class);
//
//                                        startActivity(intent);
//                                        mDialog.dismiss();
//                                        //startActivity(new Intent(mActivity, MyWalletActivity.class));
//                                    }
//                                });
//
//                            }
                        }
                    }
                    //调支付接口
                    //将这个按钮设置为false,失去焦点，只有在确切的事件之后，我们才会将这个状态重置回来。这样就写好了。
                    okPay.setEnabled(false);

                    orderPay(orderId, payChannel, payAmount, remark);

                    break;


            }
        }
    };

    // 支付
    private void orderPay(final String orderId, final byte payChannel, double payAmount, String remark) {
        OrderPayAPI.requestData(mContext, orderId, payChannel, payAmount, remark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderPayModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OrderPayModel orderPayModel) {
                        okPay.setEnabled(true);
                        if (orderPayModel.success) {
                            outTradeNo = orderPayModel.data.outTradeNo;

                            UserInfoHelper.saveWalletStatus(mContext, outTradeNo);

                            if (payChannel == 1) {
                                //余额支付
                                //ok
                                accountCenter();
                                okPay.setEnabled(true);
                         /*  Intent intent = new Intent(mContext, PayResultActivity.class);
                                intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
                                intent.putExtra(AppConstant.OUTTRADENO, orderPayModel.data.outTradeNo);
                                intent.putExtra(AppConstant.ORDERID, orderId);
                                startActivity(intent);
                                finish();*/
                            } else if (payChannel == 2) {
                                //支付宝支付 已经改好了
                                aliPay(orderPayModel.data.payToken);
                            } else if (payChannel == 3) {
                                //微信支付
                                weChatPay(orderPayModel.data.payToken);
                            }else if(payChannel == 14) {
                                //银联
                                payAliPay(orderPayModel.data.payToken);
                            }


                        } else {
                            //ok
                            okPay.setEnabled(true);
                            AppHelper.showMsg(MyConfireOrdersActivity.this, orderPayModel.message);
                        }
                    }
                });
    }

    /**
     * 支付宝
     * @param parms
     */
    private void payAliPay(String parms){
        UnifyPayRequest msg = new UnifyPayRequest();
        msg.payChannel = UnifyPayRequest.CHANNEL_ALIPAY;
        msg.payData = parms;
        UnifyPayPlugin.getInstance(this).sendPayRequest(msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWalletAmount();
    }

    private AlertDialog mDialog;


    /**
     * 获取用户支付密码的状态
     */
    private void accountCenter() {
        AccountCenterAPI.requestAccountCenter(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccountCenterModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AccountCenterModel accountCenterModel) {
                        logoutAndToHome(mContext, accountCenterModel.code);
                        if (accountCenterModel.success) {
                            mUserCell = accountCenterModel.data.phone;
                            if (accountCenterModel.data.hasSetPayPwd) {
                                showInputPwdDialog();
                            } else {
                                showGoSetDialog();
                            }
                        } else {
                            AppHelper.showMsg(mContext, accountCenterModel.message);
                        }
                    }
                });
    }

    /**
     * 显示设置支付密码弹窗
     */
    private void showGoSetDialog() {
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_not_set_paypwd);
        mDialog.getWindow().findViewById(R.id.tv_dialog_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
//                mLavLoading.setVisibility(View.GONE);
                //   mIvError.setVisibility(View.VISIBLE);
                //  mTvState.setText("取消支付");
            }
        });
        mDialog.getWindow().findViewById(R.id.tv_dialog_goset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoHelper.saveDeliverType(mContext,orderDeliveryType+"");
                UserInfoHelper.saveForgetPas(mContext, "wwwe");
                startActivity(EditPasswordInputCodeActivity.getIntent(mContext, EditPasswordInputCodeActivity.class, "0", mUserCell, "pay","forgetPsw",orderDeliveryType,payAmount));

                mDialog.dismiss();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //     mIvError.setVisibility(View.VISIBLE);
                        //    mTvState.setText("取消支付");
                    }
                }, 1000);
            }
        });
    }


    /**
     * 显示输入支付密码弹窗
     */
    private void showInputPwdDialog() {
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setView(new EditText(mContext));
        mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_input_pwd);
        final EditText mEtPwd = mDialog.getWindow().findViewById(R.id.et_dialog_paypwd);
        mDialog.getWindow().findViewById(R.id.tv_dialog_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditPasswordInputCodeActivity.class);


                startActivity(EditPasswordInputCodeActivity.getIntent(mContext, EditPasswordInputCodeActivity.class, "1", mUserCell, "pay","forgetPsw",orderDeliveryType,payAmount));
                UserInfoHelper.saveForgetPas(mContext, "wwwe");
                UserInfoHelper.saveDeliverType(mContext,orderDeliveryType+"");
                mDialog.dismiss();
//                mLavLoading.setVisibility(View.GONE);

            }
        });
        mDialog.getWindow().findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().findViewById(R.id.tv_dialog_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtPwd.getText().toString())) {
                    AppHelper.showMsg(mContext, "请输入交易密码");
                } else {
                    mDialog.dismiss();
                    aliPay.setEnabled(false);
                    wechatPay.setEnabled(false);
                    okPay.setEnabled(false);
                    checkPayPwd(outTradeNo, mEtPwd.getText().toString());

                }
            }
        });
    }

    /**
     * 校验支付密码
     */
    private void checkPayPwd(final String outTradeNo, String passWord) {
        CheckPayPwdAPI.requestData(mContext, outTradeNo, passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CheckPayPwdModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CheckPayPwdModel checkPayPwdModel) {
                        if (checkPayPwdModel.success) {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {

                                    getPayResult(outTradeNo);

                                }
                            }, 500);
                        } else {
//                            mLavLoading.setVisibility(View.GONE);
//                            tryRecycleAnimationDrawable(animationDrawable);
                            AppHelper.showMsg(mContext, checkPayPwdModel.message);
                            balancPay.setEnabled(true);
                            wechatPay.setEnabled(true);
                            aliPay.setEnabled(true);
                            okPay.setEnabled(true);


                        }
                    }
                });
    }

    /**
     * 获取支付结果
     */
    private void getPayResult(String outTradeNo) {
        GetPayResultAPI.requestData(mContext, outTradeNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetPayResultModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetPayResultModel getPayResultModel) {
                        logoutAndToHome(mContext, getPayResultModel.getCode());
                        if (getPayResultModel.isSuccess()) {

                            Intent intent = new Intent(mContext, PayResultActivity.class);
                            intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
                            intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
                            intent.putExtra(AppConstant.ORDERID, orderId);
                            intent.putExtra(AppConstant.ORDERDELIVERYTYPE, orderDeliveryType+"");
                            startActivity(intent);
                            mDialog.dismiss();
                            finish();
                        } else {
                            AppHelper.showMsg(mContext, getPayResultModel.getMessage());

                        }
                    }
                });
    }

    /**
     * 获取账户余额
     */
    private void getWalletAmount() {
        GetWalletAmountAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWalletAmountModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetWalletAmountModel getWalletAmountModel) {
                        if (getWalletAmountModel.success) {
                            textViewAmount.setText("(" + getWalletAmountModel.data + ")");

                            UserInfoHelper.saveUserWallet(mContext, getWalletAmountModel.data);


                        } else {
                            AppHelper.showMsg(mContext, getWalletAmountModel.message);
                        }

                    }
                });
    }

    //------------------支付逻辑-------------------------------------------//

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
        //okpay
        okPay.setEnabled(true);
        Intent intent = new Intent(mContext, PayResultActivity.class);
        intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
        intent.putExtra(AppConstant.ORDERID, orderId);
        intent.putExtra(AppConstant.ORDERDELIVERYTYPE, orderDeliveryType+"");
        startActivity(intent);
        finish();
    }

    /**
     * 微信支付用户已取消支付的回调,使用的eventBus.......((‵□′))
     **/
    @Subscribe
    public void onEventMainThread(WeChatUnPayEvent event) {
        okPay.setEnabled(true);
    }

    /**
     * 支付宝支付
     */
    private void aliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MyConfireOrdersActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
                //设置支付调用
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
                    Log.e("TGA", result.get("resultStatus") + "");
                    if ("9000".equals(result.get("resultStatus"))) {
                        //okpay
                        okPay.setEnabled(true);
                        //支付成功
                        Intent intent = new Intent(mContext, PayResultActivity.class);
                        intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
                        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
                        intent.putExtra(AppConstant.ORDERDELIVERYTYPE, orderDeliveryType + "");

                        intent.putExtra(AppConstant.ORDERID, orderId);
                        startActivity(intent);
                        finish();
                    } else if ("6001".equals(result.get("resultStatus"))) {
                        //用户取消支付
                        AppHelper.showMsg(mContext, "您已取消支付");
                        okPay.setEnabled(true);
                    } else if ("6002".equals(result.get("resultStatus"))) {
                        //网络连接错误
                        AppHelper.showMsg(mContext, "网络连接错误");
                        okPay.setEnabled(true);
                    } else {
                        //okpay
                        okPay.setEnabled(true);
                        //支付失败
                        Intent intent = new Intent(mContext, PayResultActivity.class);
                        intent.putExtra(AppConstant.PAYCHANNAL, payChannel);
                        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
                        intent.putExtra(AppConstant.ORDERID, orderId);
                        intent.putExtra(AppConstant.ORDERDELIVERYTYPE, orderDeliveryType + "");


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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Intent intent = new Intent(mContext, NewOrderDetailActivity.class);
//        intent.putExtra(AppConstant.ORDERID, orderId);
//        intent.putExtra(AppConstant.ORDERSTATE, "");
//        intent.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
//        startActivity(intent);
        startActivity(MyOrdersActivity.getIntent(mContext, MyOrdersActivity.class, AppConstant.ALL));
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
        finish();
        return true;
    }

}
