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
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.cart.CartBalanceModel;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.NewOrderDetailModel;
import com.puyue.www.qiaoge.view.GlideModel;
import com.puyue.www.qiaoge.view.LineBreakLayout;

import java.util.List;

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
    public NewOrderDetailAdapter(int layoutResId, @Nullable List<GetOrderDetailModel.DataBean.ProductVOListBean> data) {
        super(layoutResId, data);

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
        OrderAdapter orderAdapter = new OrderAdapter(R.layout.item_order_desc,item.productDescVOList);
        recyclerView.setAdapter(orderAdapter);
        // 添加 什么规格购买了多少
//        for (int i = 0; i < item.productDescVOList.size(); i++) {
//            Log.e(NODATAG, "convert: " + item.productDescVOList.size());
//            TextView tv = new TextView(mContext);
//            tv.setTextColor(Color.parseColor("#939393"));
//            tv.setTextSize(11);

//            if(!item.productDescVOList.get(i).getAfterPrice().equals("")) {
//                coupon.setText(item.productDescVOList.get(i).getAfterPrice());
//                coupon.setVisibility(View.VISIBLE);
//            }else {
//                coupon.setVisibility(View.GONE);
//            }

//            Log.d("weewewewewew....","wwssss");
//            if (!TextUtils.isEmpty(item.productDescVOList.get(i).detailDesc)) {
//                tv.setText(item.productDescVOList.get(i).newDesc + "  ");
//                Log.e(TAG, "convert: " + item.productDescVOList.get(i).newDesc);
//            } else {
//                tv.setText("");
//            }
//
//            lineBreakLayout.addView(tv);

//        }
        ll_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (item.businessType == 1) {
                    if (item.onShelves == 1) {
//                        Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
//                        intent.putExtra(AppConstant.ACTIVEID, item.productMainId);
//                        mContext.startActivity(intent);
                    }else if (item.onShelves == 0){
//                        AppHelper.showMsg(mContext,"商品已下架");
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
          /*  Glide.with(mContext).load(item.picUrl).crossFade().transform(new GlideRoundTransform(mContext, 3)).
                    error(R.mipmap.icon_default_rec).into(imageView);*/
        }


        if (item.businessType != 1) {
            imageIcon.setVisibility(View.VISIBLE);
            GlideModel.disPlayError(mContext, item.prodTypeUrl, imageIcon);
        } else {
            imageIcon.setVisibility(View.GONE);
        }

      /*  if (item.businessType == 2) { ///businessType这个字段判断是秒杀还是团购  ，2秒杀，3团购
            imageIcon.setImageResource(R.mipmap.ic_order);
            imageIcon.setVisibility(View.VISIBLE);
        } else if (item.businessType == 3) {
            imageIcon.setImageResource(R.mipmap.ic_order_two);
            imageIcon.setVisibility(View.VISIBLE);
        } else {
            imageIcon.setVisibility(View.GONE);
        }*/


        if (!TextUtils.isEmpty(item.spec)) {
            helper.setText(R.id.textSpe, item.spec);
        }
//        if (!TextUtils.isEmpty(item.amount)) {
//            helper.setText(R.id.Price, "¥" + item.amount);
//        }
    }
}
