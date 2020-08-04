package com.puyue.www.qiaoge.activity.cart;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.account.EditPasswordInputCodeActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.OrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.SelfSufficiencyOrderDetailActivity;
import com.puyue.www.qiaoge.api.cart.CheckPayPwdAPI;
import com.puyue.www.qiaoge.api.cart.GetPayResultAPI;
import com.puyue.www.qiaoge.api.mine.AccountCenterAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CheckPayPwdModel;
import com.puyue.www.qiaoge.model.cart.GetPayResultModel;
import com.puyue.www.qiaoge.model.mine.AccountCenterModel;

import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/26.
 */

public class PayResultActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    //    private LottieAnimationView mLavLoading;
    private ImageView mIvSuccess;
    private ImageView mIvError;
    private TextView mTvState;
    private TextView mTvOrderDetail;

    private byte payChannal;
    private String outTradeNo;
    private String mUserCell;
    private String orderId;
    private AlertDialog mDialog;
    private Handler handler = new Handler();
    private TextView textViewSuccess;
    private ImageView imageViewRecommend;
    private TextView otherMessage;
    private String imageUrl;

    private String status;

    private int orderDeliveryType;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            payChannal = bundle.getByte(AppConstant.PAYCHANNAL);
            outTradeNo = bundle.getString(AppConstant.OUTTRADENO, null);
            orderId = bundle.getString(AppConstant.ORDERID, null);
            if (bundle.getString(AppConstant.ORDERDELIVERYTYPE,null)!=null){
                orderDeliveryType=Integer.parseInt(bundle.getString(AppConstant.ORDERDELIVERYTYPE,null));
            }


            Log.i("aarqwrq", "findViewById: "+orderDeliveryType);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_pay_result);
    }

    @Override
    public void findViewById() {

        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
//        mLavLoading = FVHelper.fv(this, R.id.lav_activity_loading);
        mIvSuccess = FVHelper.fv(this, R.id.iv_activity_order_success);
        mIvError = FVHelper.fv(this, R.id.iv_activity_order_error);
        mTvState = FVHelper.fv(this, R.id.tv_activity_result_state);
        mTvOrderDetail = FVHelper.fv(this, R.id.tv_activity_order_look);
        textViewSuccess = FVHelper.fv(this, R.id.textViewSuccess);
        imageViewRecommend = FVHelper.fv(this, R.id.imageViewRecommend);
        otherMessage = FVHelper.fv(this, R.id.otherMessage);

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                backEvent();
            } else if (view == mTvOrderDetail) {

                if (orderDeliveryType==0){
                    Intent intent = new Intent(mContext, NewOrderDetailActivity.class);
                    intent.putExtra(AppConstant.ORDERID, orderId);
                    intent.putExtra(AppConstant.ORDERSTATE, "");
                    intent.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                    startActivity(intent);
                    Log.d("sddddd...........","000000");
                    finish();
                }else if (orderDeliveryType==1){
                    Intent intent = new Intent(mContext, SelfSufficiencyOrderDetailActivity.class);
                    intent.putExtra(AppConstant.ORDERID, orderId);
                    intent.putExtra(AppConstant.ORDERSTATE, "");
                    intent.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                    startActivity(intent);
                    Log.d("sddddd...........","111111");
                    finish();
                }

            } else if (view == imageViewRecommend) {
                Intent intent = new Intent(mContext, NewWebViewActivity.class);
                intent.putExtra("URL", imageUrl);
                intent.putExtra("name","");
                startActivity(intent);
//                Log.d("sssssssssssswwwwwwwwww","sdsdsdsdsd1111111");
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = MyOrdersActivity.getIntent(mActivity, MyOrdersActivity.class, AppConstant.ALL);
            intent.putExtra("orderDeliveryType",0);
            startActivity(intent);

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backEvent() {
        finish();
    }

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
                mDialog.dismiss();
//                mLavLoading.setVisibility(View.GONE);
                mIvError.setVisibility(View.VISIBLE);
            //    mTvState.setText("取消支付");
            }
        });
        mDialog.getWindow().findViewById(R.id.tv_dialog_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtPwd.getText().toString())) {
                    AppHelper.showMsg(mContext, "请输入交易密码");
                } else {
                    mDialog.dismiss();
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
                            }, 2500);
                        } else {
//                            mLavLoading.setVisibility(View.GONE);
//                            tryRecycleAnimationDrawable(animationDrawable);
                            mIvError.setVisibility(View.VISIBLE);
                        //    mTvState.setText(checkPayPwdModel.message);
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
                mIvError.setVisibility(View.VISIBLE);
            //    mTvState.setText("取消支付");
            }
        });
        mDialog.getWindow().findViewById(R.id.tv_dialog_goset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(EditPasswordInputCodeActivity.getIntent(mContext, EditPasswordInputCodeActivity.class, "0", mUserCell, "pay","forgetPsw",0,0));
                mDialog.dismiss();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIvError.setVisibility(View.VISIBLE);
                    //    mTvState.setText("取消支付");
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void setViewData() {

        IntentFilter filter = new IntentFilter(AppConstant.PAY_PASSWORD_ACTION);
        registerReceiver(broadcastReceiver, filter);
        if (payChannal == 1) {
            //accountCenter();

            getPayResultAlready(outTradeNo);
        } else if (payChannal == 2) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    getPayResult(outTradeNo);
                }
            }, 3000);
        } else if (payChannal == 3) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    getPayResult(outTradeNo);
                }
            }, 3000);
        }

    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mTvOrderDetail.setOnClickListener(noDoubleClickListener);
        imageViewRecommend.setOnClickListener(noDoubleClickListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
//        mLavLoading.cancelAnimation();
    }

    private void getPayResultAlready(String outTradeNo) {
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
                            if (getPayResultModel.getData() != null) {
                                mIvSuccess.setVisibility(View.VISIBLE);
                                mIvError.setVisibility(View.GONE);
                         //       mTvState.setVisibility(View.GONE);
                                imageUrl = getPayResultModel.getData().getVo().getBannerDetailUrl();
                                Glide.with(mActivity).load(getPayResultModel.getData().getVo().getBannerUrl()).into(imageViewRecommend);
                                textViewSuccess.setText(getPayResultModel.getData().getMessage());


                                if (!TextUtils.isEmpty(getPayResultModel.getData().getOtherMessage())) {
                                    otherMessage.setText(getPayResultModel.getData().getOtherMessage());
                                    otherMessage.setVisibility(View.VISIBLE);
                             //       mTvState.setVisibility(View.GONE);
                                } else {
                                    otherMessage.setVisibility(View.GONE);
                           //         mTvState.setVisibility(View.VISIBLE);
                                }

                                textViewSuccess.setVisibility(View.VISIBLE);
                            } else {
                                mIvSuccess.setVisibility(View.GONE);
                                mIvError.setVisibility(View.VISIBLE);
                         //       mTvState.setText("支付失败");
                                textViewSuccess.setVisibility(View.GONE);
                            }

                        } else {
                            AppHelper.showMsg(PayResultActivity.this, getPayResultModel.getMessage());
                            mIvError.setVisibility(View.VISIBLE);
                         ///   mTvState.setText("支付失败");
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
                            if (getPayResultModel.getData() != null) {
                                mIvSuccess.setVisibility(View.VISIBLE);
                                mIvError.setVisibility(View.GONE);
                           //     mTvState.setText("支付成功");
                                imageUrl = getPayResultModel.getData().getVo().getBannerDetailUrl();
                                Glide.with(mActivity).load(getPayResultModel.getData().getVo().getBannerUrl()).into(imageViewRecommend);
                                textViewSuccess.setText(getPayResultModel.getData().getMessage());


                                if (!TextUtils.isEmpty(getPayResultModel.getData().getOtherMessage())) {
                                    otherMessage.setText(getPayResultModel.getData().getOtherMessage());
                                    otherMessage.setVisibility(View.VISIBLE);
                                //    mTvState.setVisibility(View.GONE);
                                } else {
                                    otherMessage.setVisibility(View.GONE);
                                 //   mTvState.setVisibility(View.VISIBLE);
                                }

                                textViewSuccess.setVisibility(View.VISIBLE);
                            } else {
                                mIvSuccess.setVisibility(View.GONE);
                                mIvError.setVisibility(View.VISIBLE);
                              //  mTvState.setText("支付失败");
                                textViewSuccess.setVisibility(View.GONE);
                            }
                        } else {
                            AppHelper.showMsg(PayResultActivity.this, getPayResultModel.getMessage());
                            mIvError.setVisibility(View.VISIBLE);
                         //   mTvState.setText("支付失败");
                        }
                    }
                });
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mType = intent.getExtras().getString("type");
            if (StringHelper.notEmptyAndNull(mType)) {
                if (mType.equals("balance")) {
                  //  mTvState.setText("");
                    mIvError.setVisibility(View.GONE);
                    mIvSuccess.setVisibility(View.GONE);
                    accountCenter();
                }
            }
        }
    };


}
