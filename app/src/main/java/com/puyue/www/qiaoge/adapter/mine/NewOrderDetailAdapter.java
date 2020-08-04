package com.puyue.www.qiaoge.adapter.mine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpikeGoodsDetailsActivity;
import com.puyue.www.qiaoge.adapter.home.SeckillGoodActivity;
import com.puyue.www.qiaoge.adapter.market.MarketGoodBrandAdapter;
import com.puyue.www.qiaoge.api.home.GetAllCommentListByPageAPI;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.cart.CartBalanceModel;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.home.GetAllCommentListByPageModel;
import com.puyue.www.qiaoge.model.home.JumpModel;
import com.puyue.www.qiaoge.model.mine.order.NewOrderDetailModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.GlideModel;
import com.puyue.www.qiaoge.view.LineBreakLayout;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${daff}
 * on 2018/10/20
 * 备注 改版后的 订单详情
 */
public class NewOrderDetailAdapter extends BaseQuickAdapter<GetOrderDetailModel.DataBean.ProductVOListBean, BaseViewHolder> {

    private static final String NODATAG = NewOrderDetailAdapter.class.getCanonicalName();
    private ImageView imageView;
    private ImageView imageIcon;
    private TextView oldPrice;
    private LineBreakLayout lineBreakLayout;
    private TextView textSpe;
    private TextView tv_return_status;
    TextView coupon;
    private LinearLayout ll_good;
    RecyclerView recyclerView;
    String orderId;

    public NewOrderDetailAdapter(int layoutResId, @Nullable List<GetOrderDetailModel.DataBean.ProductVOListBean> data, String orderId) {
        super(layoutResId, data);
        this.orderId = orderId;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrderDetailModel.DataBean.ProductVOListBean item) {
        coupon = helper.getView(R.id.coupon);
        recyclerView = helper.getView(R.id.recyclerView);
        imageView = helper.getView(R.id.imageView);
        imageIcon = helper.getView(R.id.imageIcon);
        textSpe = helper.getView(R.id.textSpe);
        oldPrice = helper.getView(R.id.oldPrice);
        lineBreakLayout = helper.getView(R.id.lineBreakLayout);
        tv_return_status = helper.getView(R.id.tv_return_status);
        ll_good = helper.getView(R.id.ll_good);
        lineBreakLayout.removeAllViews();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        OrderAdapter orderAdapter = new OrderAdapter(R.layout.item_order_desc, item.productDescVOList);
        recyclerView.setAdapter(orderAdapter);
        ll_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.businessType == 1) {
                    jumpDetail(orderId, item.productMainId, item.businessType,item);
                }else {
                    if(item.businessType==2) {
                        jumpDetails(orderId, item.productId, item.businessType,item);
                    }else {
                        jumpDetailss(orderId, item.productId, item.businessType,item);
                    }

                }
            }
        });


        if (item.returnNum != null && StringHelper.notEmptyAndNull(item.returnNum)) {
            tv_return_status.setVisibility(View.VISIBLE);
            tv_return_status.setText(item.returnNum);
        } else {
            tv_return_status.setVisibility(View.GONE);
        }

        if (item.businessType == 2 || item.businessType == 11) { // 有原价 有规格
            if (item.oldPrice != null && StringHelper.notEmptyAndNull(item.oldPrice)) {
                oldPrice.setVisibility(View.GONE);
                oldPrice.setText(item.oldPrice + "");
                oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                textSpe.setVisibility(View.VISIBLE);
            } else {
                oldPrice.setVisibility(View.GONE);
            }
        } else if (item.businessType == 1) {  // 没有原价 有规格
            oldPrice.setVisibility(View.GONE);
            textSpe.setVisibility(View.VISIBLE);

        } else if (item.businessType == 3) {// 没有原价 没有规格
            oldPrice.setVisibility(View.GONE);
            textSpe.setVisibility(View.INVISIBLE);
        }
        if (!TextUtils.isEmpty(item.name)) {
            helper.setText(R.id.textTitle, item.name);
        }

        if (StringHelper.notEmptyAndNull(item.picUrl)) {
            GlideModel.disPlayError(mContext, item.picUrl, imageView);
        }

        if (item.businessType != 1) {
            imageIcon.setVisibility(View.VISIBLE);
            GlideModel.disPlayError(mContext, item.prodTypeUrl, imageIcon);
        } else {
            imageIcon.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.spec)) {
            helper.setText(R.id.textSpe, item.spec);
        }
    }

    private void jumpDetails(String orderId, int productId, int businessType, GetOrderDetailModel.DataBean.ProductVOListBean item) {
        GetAllCommentListByPageAPI.jumpDetail(mContext, orderId, productId, businessType)
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
                                    Intent intent = new Intent(mContext, SeckillGoodActivity.class);
                                    intent.putExtra(AppConstant.ACTIVEID,productId);
                                    intent.putExtra("priceType",SharedPreferencesUtil.getString(mContext,"priceType"));
                                    intent.putExtra("num",jumpModel.getData());
                                    mContext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mContext, SeckillGoodActivity.class);
                                    intent.putExtra(AppConstant.ACTIVEID,productId);
                                    intent.putExtra("num",jumpModel.getData());
                                    intent.putExtra("priceType",SharedPreferencesUtil.getString(mContext,"priceType"));
                                    intent.putExtra("city",jumpModel.getMessage());
                                    mContext.startActivity(intent);
                                }
                            }
                        }else {
                            ToastUtil.showSuccessMsg(mContext,jumpModel.getMessage());
                        }
                    }
                });
    }
    private void jumpDetailss(String orderId, int productId, int businessType, GetOrderDetailModel.DataBean.ProductVOListBean item) {
        GetAllCommentListByPageAPI.jumpDetail(mContext, orderId, productId, businessType)
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
                                    Intent intent = new Intent(mContext, SpecialGoodDetailActivity.class);
                                    intent.putExtra(AppConstant.ACTIVEID,productId);
                                    intent.putExtra("priceType", SharedPreferencesUtil.getString(mContext,"priceType"));
                                    intent.putExtra("num",jumpModel.getData());
                                    mContext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mContext, SpecialGoodDetailActivity.class);
                                    intent.putExtra(AppConstant.ACTIVEID,productId);
                                    intent.putExtra("num",jumpModel.getData());
                                    intent.putExtra("priceType", SharedPreferencesUtil.getString(mContext,"priceType"));
                                    intent.putExtra("city",jumpModel.getMessage());
                                    mContext.startActivity(intent);
                                }
                            }
                        }else {
                            ToastUtil.showSuccessMsg(mContext,jumpModel.getMessage());
                        }
                    }
                });
    }
    /**
     * 跳转详情
     */
    private void jumpDetail(String orderId, int businessId, int businessType, GetOrderDetailModel.DataBean.ProductVOListBean item) {
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
                                    intent.putExtra("priceType", SharedPreferencesUtil.getString(mContext,"priceType"));
                                    mContext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                                    intent.putExtra(AppConstant.ACTIVEID,item.productMainId);
                                    intent.putExtra("num",jumpModel.getData());
                                    intent.putExtra("priceType", SharedPreferencesUtil.getString(mContext,"priceType"));
                                    intent.putExtra("city",jumpModel.getMessage());
                                    mContext.startActivity(intent);
                                }
                            }
                        }else {
                            ToastUtil.showSuccessMsg(mContext,jumpModel.getMessage());
                        }
                    }
                });
    }

}