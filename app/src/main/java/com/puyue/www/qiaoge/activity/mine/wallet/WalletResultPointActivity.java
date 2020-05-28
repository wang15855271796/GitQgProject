package com.puyue.www.qiaoge.activity.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CartActivity;
import com.puyue.www.qiaoge.api.cart.GetPayResultAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetPayResultModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 温馨提示充值结果
 * Created by ${王文博} on 2019/4/23
 */
public class WalletResultPointActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
    private TextView mTvTitle;
    //    private LottieAnimationView mLavLoading;
    private ImageView mIvSuccess;
    private ImageView mIvError;
    private TextView mTvState;
    private TextView mTvOrderDetail;

    private String outTradeNo;
    private int channelType;
    private String pageType;

    private TextView textViewSuccess;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            outTradeNo = bundle.getString(AppConstant.OUTTRADENO, null);
            channelType = bundle.getInt(AppConstant.PAYCHANNAL, 0);
            pageType = bundle.getString(AppConstant.PAGETYPE, null);
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
        mTvTitle = FVHelper.fv(this, R.id.tv_activity_title);
//        mLavLoading = FVHelper.fv(this, R.id.lav_activity_loading);
        mIvSuccess = FVHelper.fv(this, R.id.iv_activity_order_success);
        mIvError = FVHelper.fv(this, R.id.iv_activity_order_error);
        mTvState = FVHelper.fv(this, R.id.tv_activity_result_state);
        mTvOrderDetail = FVHelper.fv(this, R.id.tv_activity_order_look);
        textViewSuccess = FVHelper.fv(this, R.id.textViewSuccess);
    }

    @Override
    public void setViewData() {

        mTvOrderDetail.setText("完成");
        if ("recharge".equals(pageType)) {
//            mLavLoading.setVisibility(View.VISIBLE);
            mIvSuccess.setVisibility(View.GONE);
            mTvTitle.setText("充值结果");
            if (channelType == 2) {
                //支付宝
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getPayResult(outTradeNo);
                    }
                }, 3000);
            } else if (channelType == 3) {
                //微信支付
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getPayResult(outTradeNo);
                    }
                }, 3000);
            }
        } else {
            mTvTitle.setText("提现申请结果");
//            mLavLoading.setVisibility(View.GONE);
            mIvSuccess.setVisibility(View.VISIBLE);
            mTvState.setText("提交成功");
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mTvOrderDetail.setOnClickListener(noDoubleClickListener);
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                if (mTvState.getText().toString().equals("充值成功")) {
                    Intent intent = new Intent(mContext, CartActivity.class);
                    startActivity(intent);

                } else {
                    finish();
//                    Log.d("sssssssssssswwwwwwwwww","sdsdsdsdsd000000");
                }


            } else if (view == mTvOrderDetail) {
                Intent intent = new Intent(mContext, CartActivity.class);
                startActivity(intent);

                finish();
            }
        }
    };

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
//                        mLavLoading.setVisibility(View.GONE);
                        if (getPayResultModel.isSuccess()) {
                            //支付成功
                            if (getPayResultModel.getData() != null) {
                                //支付成功
                                mIvSuccess.setVisibility(View.VISIBLE);
                                mTvState.setText("充值成功");


                                textViewSuccess.setVisibility(View.VISIBLE);
                                textViewSuccess.setText(getPayResultModel.getData().getMessage());

                            } else {
                                mIvError.setVisibility(View.VISIBLE);
                                mTvState.setText("充值失败");
                            }
                        } else {
                            AppHelper.showMsg(mContext, getPayResultModel.getMessage());
                            mIvError.setVisibility(View.VISIBLE);
                            mTvState.setText(getPayResultModel.getMessage());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mLavLoading.cancelAnimation();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mTvState.getText().toString().equals("充值成功")) {
            Intent intent = new Intent(mContext, CartActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
