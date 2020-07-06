package com.puyue.www.qiaoge.activity.mine.wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.adapter.mine.MyWalletAdapter;
import com.puyue.www.qiaoge.api.mine.GetWalletInfoAPI;
import com.puyue.www.qiaoge.api.mine.RechargeAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.XieYiDialog;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.event.WeChatPayEvent;
import com.puyue.www.qiaoge.fragment.cart.NumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.MapHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.RechargeModel;
import com.puyue.www.qiaoge.model.mine.wallet.GetWalletInfoModel;
import com.puyue.www.qiaoge.popupwindow.MyWalletPopuWindow;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${daff} on 2018/9/21
 * 我的钱包
 */
public class MyWalletActivity extends BaseSwipeActivity {

    private Toolbar toolbar;
    private TextView textDetail;
    private TextView myPrice;
    private TextView textViewInput;
    private RadioGroup radioGroup;
    private RadioButton radioButtonWeChat;
    private RadioButton radioButtonAliPay;
    private TextView commonButton;
    private RelativeLayout relativeLayoutInput;
    private RelativeLayout RelativeLayoutlRules;
    private MyWalletAdapter adapter;
    private RecyclerView recyclerView;
    private TextView textRules;
    private List<GetWalletInfoModel.DataBean.RechargeListBean> list = new ArrayList<>();
    private String RulesUrl;
    private MyWalletPopuWindow popuWindow;
    //  支付
    private String outTradeNo;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private String mEtAmount;
    CheckBox checkBox;
    private String num;

    private EditText et_amount;
    private LinearLayout ll_account;
    private TextView tv_cz;
    private TextView tv_account;

    private String url;
    private XieYiDialog promptDialog;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_wallet);
    }

    @Override
    public void findViewById() {
        textRules = (TextView) findViewById(R.id.textRules);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textDetail = (TextView) findViewById(R.id.textDetail);
        myPrice = (TextView) findViewById(R.id.myPrice);
        textViewInput = (TextView) findViewById(R.id.textViewInput);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButtonWeChat = (RadioButton) findViewById(R.id.radioButtonWeChat);
        radioButtonAliPay = (RadioButton) findViewById(R.id.radioButtonAliPay);
        commonButton = findViewById(R.id.commonButton);
        relativeLayoutInput = (RelativeLayout) findViewById(R.id.relativeLayoutInput);
        RelativeLayoutlRules = (RelativeLayout) findViewById(R.id.RelativeLayoutlRules);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        et_amount = (EditText) findViewById(R.id.et_amount);
        ll_account = (LinearLayout) findViewById(R.id.ll_account);
        tv_cz = (TextView) findViewById(R.id.tv_cz);
        tv_account = (TextView) findViewById(R.id.tv_account);
    }


    @Override
    public void setViewData() {
        num = UserInfoHelper.getUserWalletNum(mContext);

        url = "https://shaokao.qoger.com/apph5/html/czxy.html";
        EventBus.getDefault().register(this);
        requestGetWallet();
        setRecyclerView();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBuss(NumEvent event) {
        //刷新UI
        checkBox.setChecked(true);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            EventBus.getDefault().post(new BackEvent());
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void setClickEvent() {
        toolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                EventBus.getDefault().post(new BackEvent());
                finish();
            }
        });
        textDetail.setOnClickListener(noDoubleClickListener);
        commonButton.setOnClickListener(noDoubleClickListener);
        RelativeLayoutlRules.setOnClickListener(noDoubleClickListener);
        relativeLayoutInput.setOnClickListener(noDoubleClickListener);
        tv_cz.setOnClickListener(noDoubleClickListener);
        tv_account.setOnClickListener(noDoubleClickListener);


        et_amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setFlag(false);
                    }
                    adapter.notifyDataSetChanged();
                    mEtAmount=et_amount.getText().toString();

                }
            }
        });

        et_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mEtAmount = s.toString();
                Log.i("werqa", "afterTextChanged: "+mEtAmount);
            }
        });

    }

    private void setRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MyWalletAdapter(R.layout.item_my_wallet, list, new MyWalletAdapter.Onclick() {
            @Override
            public void ItemOnclick(int position) {
                //

                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setFlag(i == position);
                }
                // relativeLayoutInput.setBackgroundResource(R.drawable.app_my_wallet_bg);
//relativeLayoutInput.setBackgroundColor(Color.parseColor("#ffff8003"));
                mEtAmount = list.get(position).getAmount() + "";
                adapter.notifyDataSetChanged();
                et_amount.setFocusable(false);
                et_amount.setFocusableInTouchMode(true);


                et_amount.setHint("请输入自定义金额");
            }

        });
        recyclerView.setAdapter(adapter);
        // 防止 NestedScrollview嵌套 recyclerView 出现卡顿

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);


    }


    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.textDetail: // 明细
                    startActivity(MyWalletDetailActivity.getIntent(mContext, MyWalletDetailActivity.class));
                    break;
                case R.id.RelativeLayoutlRules:
                    startActivity(CommonH5Activity.getIntent(mContext, CommonH5Activity.class, RulesUrl));
                    break;
                case R.id.tv_cz:
                    startActivity(CommonH5Activity.getIntent(mContext, CommonH5Activity.class, RulesUrl));
                    break;

                case R.id.tv_account:
                    startActivity(CommonH5Activity.getIntent(mContext, CommonH5Activity.class, url));
                    break;
                case R.id.relativeLayoutInput:
                    if (null == popuWindow) {
                        setPopuWindow();
                    }
                    popuWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
                    break;
                case R.id.commonButton:
                    if(MapHelper.isAvilible(mActivity, "com.tencent.mm")) {
                        if(!TextUtils.isEmpty(mEtAmount)) {
                            if(radioButtonAliPay.isChecked()) {
                                if (Double.parseDouble(mEtAmount)>0){

                                    if(!checkBox.isChecked()) {
                                        promptDialog = new XieYiDialog(mActivity) {
                                            @Override
                                            public void Confirm() {
                                                dismiss();
                                            }

                                            @Override
                                            public void Cancle() {
                                                dismiss();
                                            }
                                        };
                                        promptDialog.show();
                                    }else {
                                        recharge(Double.parseDouble(mEtAmount), (byte) 2);
                                    }

                                }else {
                                    AppHelper.showMsg(mContext,"充值金额不能为0");
                                }
                            } else if (radioButtonWeChat.isChecked()) {
                                if (Double.parseDouble(mEtAmount)>0){
                                    if(!checkBox.isChecked()) {
                                        promptDialog = new XieYiDialog(mActivity) {
                                            @Override
                                            public void Confirm() {
                                                dismiss();
                                            }

                                            @Override
                                            public void Cancle() {
                                                dismiss();
                                            }
                                        };
                                        promptDialog.show();
                                    }else {
                                        recharge(Double.parseDouble(mEtAmount), (byte) 3);
                                    }

                                }else {
                                    AppHelper.showMsg(mContext,"充值金额不能为0");
                                }
                            }
                        }else {
                            AppHelper.showMsg(mContext, "请选择价格");
                        }
                    }else {
                        AppHelper.showMsg(mContext, "请下载微信");
                    }


                    break;
            }
        }
    };


    private void setPopuWindow() {
        popuWindow = new MyWalletPopuWindow(mContext, new MyWalletPopuWindow.ViewOnclick() {
            @Override
            public void buttonOnclick(String edit) {
                popuWindow.dismiss();
                mEtAmount = edit;
                textViewInput.setText(edit);
                relativeLayoutInput.setBackgroundColor(Color.parseColor("#ffff8003"));
                // relativeLayoutInput.setBackgroundResource(R.drawable.app_my_wallet_selected);

                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setFlag(false);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void requestGetWallet() {
        GetWalletInfoAPI.requestGetWalletInfo(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWalletInfoModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetWalletInfoModel info) {
                        if (info.success) {
                            setText(info);
                            if (info != null && info.getData().getRechargeList().size() > 0)
                                list.addAll(info.getData().getRechargeList());
                            // 默认选中第一条
                            adapter.notifyDataSetChanged();

                            if (num != null) {
                                info.getData().getRechargeList().get(Integer.parseInt(num)).setFlag(true);
                                mEtAmount = list.get(Integer.parseInt(num)).getAmount() + "";
                            } else {
                                info.getData().getRechargeList().get(0).setFlag(true);

                                mEtAmount = list.get(0).getAmount() + "";
                            }


                            //  mEtAmount = info.getData().getAmount();



                        }


                    }
                });
    }

    private void setText(GetWalletInfoModel info) {
        if (!TextUtils.isEmpty(info.getData().getAmount())) {
            myPrice.setText(info.getData().getAmount());

        }
        if (!TextUtils.isEmpty(info.getData().getRechargeRole().getBannerDetailUrl())) {
            RulesUrl = info.getData().getRechargeRole().getBannerDetailUrl();
            textRules.setText(info.getData().getRechargeRole().getBannerUrl());
            RelativeLayoutlRules.setVisibility(View.VISIBLE);
        } else {
            RelativeLayoutlRules.setVisibility(View.GONE);
        }

    }


    //----------以下是支付流程---------------------//

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
     * 支付宝支付
     */
    private void aliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MyWalletActivity.this);
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
     * 微信支付
     */
    private void weChatPay(String json) {
        try {
            IWXAPI api = WXAPIFactory.createWXAPI(this, "wxbc18d7b8fee86977");
            if (api.isWXAppInstalled()) {
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
            }


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
                      //  commonButton.setEnabled(true);
                        //支付成功
                        Intent intent = new Intent(mContext, WalletResultActivity.class);
                        intent.putExtra(AppConstant.PAYCHANNAL, 2);
                        intent.putExtra(AppConstant.OUTTRADENO, outTradeNo);
                        intent.putExtra(AppConstant.PAGETYPE, "recharge");
                        startActivity(intent);
                        finish();
                    } else if ("6001".equals(result.get("resultStatus"))) {
                      //  commonButton.setEnabled(true);
                        //用户取消支付
                        AppHelper.showMsg(mContext, "您已取消充值");
                    } else if ("6002".equals(result.get("resultStatus"))) {
                       // commonButton.setEnabled(true);
                        //网络连接错误
                        AppHelper.showMsg(mContext, "网络连接错误");
                    } else {
                        //commonButton.setEnabled(true);
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


    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


}
