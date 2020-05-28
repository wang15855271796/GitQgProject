package com.puyue.www.qiaoge.adapter.mine;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.api.home.GetAllCommentListByPageAPI;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.home.JumpModel;
import com.puyue.www.qiaoge.model.mine.order.NewReturnOrderModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王文博} on 2019/8/28
 */
public class ReturnDetailOrderAdapter extends BaseQuickAdapter<NewReturnOrderModel.DataBean.ProductsBean, BaseViewHolder> {

    private TextView tv_return_amount;
    private TextView tv_return_num;
    private TextView tv_title;
    private TextView tv_return_one;
    private ImageView iv_food;
    private ImageView iv_flag;
    private RelativeLayout rl_good;
    private int returnState;
    String orderId;
    public ReturnDetailOrderAdapter(int layoutResId, @Nullable List<NewReturnOrderModel.DataBean.ProductsBean> data, int returnState, String orderId) {
        super(layoutResId, data);
        this.returnState = returnState;
        this.orderId = orderId;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewReturnOrderModel.DataBean.ProductsBean item) {
        tv_return_amount = helper.getView(R.id.tv_return_amount);
        tv_return_num = helper.getView(R.id.tv_return_num);
        tv_title = helper.getView(R.id.tv_title);
        iv_food = helper.getView(R.id.iv_food);
        iv_flag = helper.getView(R.id.iv_flag);
        rl_good = helper.getView(R.id.rl_good);
        tv_return_one = helper.getView(R.id.tv_return_one);


        tv_return_amount.setText(item.getReturnTotalAmount());
        tv_return_num.setText("退货数量:" + item.getReturnNum());
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_food);
        tv_title.setText(item.getProductName());

        if (item.getTypeImg() != null && StringHelper.notEmptyAndNull(item.getTypeImg())) {
            iv_flag.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getTypeImg()).into(iv_flag);
        } else {
            iv_flag.setVisibility(View.GONE);

        }

        if (returnState == 1) {
            tv_return_one.setVisibility(View.GONE);
            tv_return_amount.setVisibility(View.GONE);
        } else {
            tv_return_amount.setVisibility(View.VISIBLE);
            tv_return_one.setVisibility(View.VISIBLE);
        }

        rl_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getBusinessType() == 1) {
                    jumpDetail(orderId, item.productMainId, item.getBusinessType(),item);
                }else {
                    jumpDetail(orderId, item.getBusinessId(), item.getBusinessType(),item);
                }
            }
        });


    }

    /**
     * 跳转详情
     */
    private void jumpDetail(String orderId, int businessId, int businessType, NewReturnOrderModel.DataBean.ProductsBean item) {
        GetAllCommentListByPageAPI.jumpDetail(mContext, orderId, businessId, businessType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JumpModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JumpModel jumpModel) {
                        if(jumpModel.isSuccess()) {
                            if(jumpModel.getData()!=null) {
                                if(jumpModel.getData().equals("-1")) {
                                    Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                                    intent.putExtra(AppConstant.ACTIVEID,item.productMainId);
                                    intent.putExtra("num",jumpModel.getData());
                                    mContext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                                    intent.putExtra(AppConstant.ACTIVEID,item.productMainId);
                                    intent.putExtra("num",jumpModel.getData());
                                    intent.putExtra("city",jumpModel.getMessage());
                                    mContext.startActivity(intent);
                                }
                            }else {
                                AppHelper.showMsg(mContext,jumpModel.getMessage());
                            }
                        }
                    }
                });
    }
}
