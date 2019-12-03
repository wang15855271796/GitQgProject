package com.puyue.www.qiaoge.activity.mine.order;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.PayResultActivity;
import com.puyue.www.qiaoge.api.cart.GetPayResultAPI;
import com.puyue.www.qiaoge.api.mine.order.VipPayResultAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetPayResultModel;
import com.puyue.www.qiaoge.model.mine.order.VipPayResultModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注 会员积分支付成功
 */
public class VipPayResultActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
//    private LottieAnimationView mLavLoading;
    private ImageView mIvSuccess;
    private ImageView mIvError;
    private TextView mTvState;
    private TextView mTvOrderDetail;

    private int payChannal;
    private String outTradeNo;
    private String mUserCell;
    private String orderId;
    private AlertDialog mDialog;
    private Handler handler = new Handler();
    private  TextView textViewSuccess; //支付成功文案
    private  ImageView imageViewRecommend;
    private String imageUrl;
    private TextView otherMessage;


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_vippayresult);
    }

    @Override
    public void findViewById() {

        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
//        mLavLoading = FVHelper.fv(this, R.id.lav_activity_loading);
        mIvSuccess = FVHelper.fv(this, R.id.iv_activity_order_success);
        mIvError = FVHelper.fv(this, R.id.iv_activity_order_error);
        mTvState = FVHelper.fv(this, R.id.tv_activity_result_state);
        mTvOrderDetail = FVHelper.fv(this, R.id.tv_activity_order_look);
        textViewSuccess=FVHelper.fv(this, R.id.textViewSuccess);
        imageViewRecommend=FVHelper.fv(this,R.id.imageViewRecommend);
        otherMessage =FVHelper.fv(this,R.id.otherMessage);
    }

    @Override
    public void setViewData() {
//        mLavLoading.setVisibility(View.VISIBLE);
        payChannal = getIntent().getIntExtra("payChannal",0);
        outTradeNo = getIntent().getStringExtra("outTradeNo");
        if (payChannal == 2) {
            //支付宝支付
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
        imageViewRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewWebViewActivity.class);
                intent.putExtra("URL", imageUrl);
                intent.putExtra("name","");
                startActivity(intent);
            }
        });
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        mTvOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void getPayResult(String outTradeNo) {
        VipPayResultAPI.requestOrderComment(mContext, outTradeNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VipPayResultModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(VipPayResultModel vipPayResultModel) {
//                        mLavLoading.setVisibility(View.GONE);
                        logoutAndToHome(mContext, vipPayResultModel.getCode());
                        if (vipPayResultModel.isSuccess()) {
                            //支付成功
                            if (vipPayResultModel.getData()!=null) {
                                //支付成功
                                otherMessage.setVisibility(View.VISIBLE);
                                mIvSuccess.setVisibility(View.VISIBLE);
                                mIvError.setVisibility(View.GONE);
                               mTvState.setText("");
                                Glide.with(mActivity).load(vipPayResultModel.getData().getVo().getBannerUrl()).into(imageViewRecommend);
                                imageUrl = vipPayResultModel.getData().getVo().getBannerDetailUrl();

                                textViewSuccess.setText(vipPayResultModel.getData().getPayMsg());
                                otherMessage.setText(vipPayResultModel.getData().getErrorMsg());
                                textViewSuccess.setVisibility(View.VISIBLE);
                            } else {
                                mIvSuccess.setVisibility(View.GONE);
                                mIvError.setVisibility(View.VISIBLE);
                                mTvState.setText("");
                                textViewSuccess.setVisibility(View.GONE);
                            }
                        } else {
                            AppHelper.showMsg(VipPayResultActivity.this, vipPayResultModel.getData().getPayMsg());
                            mIvError.setVisibility(View.VISIBLE);
                            mTvState.setText("");
                        }
                    }
                });
    }


}
