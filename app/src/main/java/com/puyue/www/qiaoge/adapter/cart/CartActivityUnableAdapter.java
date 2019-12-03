package com.puyue.www.qiaoge.adapter.cart;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CartActivity;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.cart.CartListModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * 创建 on 2019/3/20.
 * 描述：
 */
public class CartActivityUnableAdapter extends BaseQuickAdapter<CartListModel.DataBean.ListBean.ValidListBean, BaseViewHolder> {
//    private CartActivity.CartStandardAdapter mAdapterCartStandard;
    private int code;

    public CartActivityUnableAdapter(int layoutResId, @Nullable List<CartListModel.DataBean.ListBean.ValidListBean> data) {
        super(layoutResId, data);
        this.code = code;
    }

    @Override
    protected void convert(BaseViewHolder helper, CartListModel.DataBean.ListBean.ValidListBean item) {
        Glide.with(mContext).load(item.flagUrl).into((ImageView) helper.getView(R.id.iv_flag));
        //根据这个购物车item是否已经失效来判断是否显示失效布局
        if (!item.valid) {
            //失效
            ((LinearLayout) helper.getView(R.id.ll_item_cart_invalid)).setVisibility(View.VISIBLE);
            ((CheckBox) helper.getView(R.id.cb_item_cart)).setVisibility(View.GONE);
            ((TextView) helper.getView(R.id.tv_item_cart_invalid)).setVisibility(View.INVISIBLE);
        } else {
            //有效
            ((LinearLayout) helper.getView(R.id.ll_item_cart_invalid)).setVisibility(View.GONE);
            ((CheckBox) helper.getView(R.id.cb_item_cart)).setVisibility(View.VISIBLE);
            ((TextView) helper.getView(R.id.tv_item_cart_invalid)).setVisibility(View.INVISIBLE);
        }
        if (StringHelper.notEmptyAndNull(item.picUrl)) {
            GlideModel.displayTransForms(mContext,item.picUrl,helper.getView(R.id.iv_item_cart_img));
           // Glide.with(mContext).load(item.picUrl).transform(new GlideRoundTransform(mContext, 3)).into((ImageView) helper.getView(R.id.iv_item_cart_img));
        }
        helper.setText(R.id.tv_item_cart_title, item.name);
        //这个规格,要根据不同的产品类型来判断是否要显示
//        helper.setText(R.id.tv_item_cart_standard, item.standard);
//        helper.setText(R.id.tv_item_cart_price, item.price);
//        helper.setText(R.id.tv_item_cart_volume, item.volume);
//        helper.setText(R.id.tv_item_cart_stock, item.stock);
        helper.setText(R.id.tv_item_cart_standard, "规格：" + item.spec);
        ((RecyclerView) helper.getView(R.id.rv_item_cart)).setLayoutManager(new LinearLayoutManager(mContext));
//        mAdapterCartStandard = new CartActivity.CartStandardAdapter(R.layout.item_cart_standard, item.productDescVOList, item.businessType, code);
//        ((RecyclerView) helper.getView(R.id.rv_item_cart)).setAdapter(mAdapterCartStandard);
    }
}

