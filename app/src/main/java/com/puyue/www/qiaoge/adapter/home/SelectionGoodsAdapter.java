package com.puyue.www.qiaoge.adapter.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.market.MarketGoodsModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by ${daff}
 * on 2018/10/24
 * 备注  精选分类
 */
public class SelectionGoodsAdapter extends BaseQuickAdapter<MarketGoodsModel.DataBean.ListBean, BaseViewHolder> {


    private ImageView ivSoldOut;// 是否售完
    private TextView priceReduction;
    private ImageView shoppingCart;
    private onClick onClickImage;
    private ImageView tvSale;

    public SelectionGoodsAdapter(int layoutResId, @Nullable List<MarketGoodsModel.DataBean.ListBean> data, onClick onClickImage) {
        super(layoutResId, data);
        this.onClickImage = onClickImage;

    }

    @Override
    protected void convert(final BaseViewHolder helper, MarketGoodsModel.DataBean.ListBean item) {

        tvSale = helper.getView(R.id.tvBay);
        ivSoldOut = helper.getView(R.id.iv_sold_out);
        priceReduction = helper.getView(R.id.priceReduction);
        shoppingCart = helper.getView(R.id.shoppingCart);
        helper.setText(R.id.tv_item_goods_name, item.productName);
        helper.setText(R.id.tv_item_goods_specification, "规格：" + item.spec);
        helper.setText(R.id.tv_item_goods_price, item.price);
        Glide.with(mContext).load(item.prodTypeUrl).into(tvSale);
        GlideModel.displayTransForms(mContext,item.imgUrl,helper.getView(R.id.iv_item_goods_img));
      /*  Glide.with(mContext).load(item.imgUrl).crossFade().transform(new GlideRoundTransform(mContext, 3)).
                error(R.mipmap.icon_default_rec).into((ImageView) helper.getView(R.id.iv_item_goods_img));*/

        priceReduction.setVisibility(View.GONE);
        if (item.flag == 1) {
            //未售完
            ivSoldOut.setVisibility(View.GONE);
            shoppingCart.setVisibility(View.VISIBLE);
        } else {
            ivSoldOut.getBackground().setAlpha(150);
            ivSoldOut.setVisibility(View.VISIBLE);
            shoppingCart.setEnabled(false);
        }
        shoppingCart.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                onClickImage.shoppingCartOnclick(helper.getLayoutPosition());
            }
        });
        final int productId = item.productId;
        ((RelativeLayout) helper.getView(R.id.ll_item_group)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, productId);
                mContext.startActivity(intent);
            }
        });
    }

    public interface onClick {
        void shoppingCartOnclick(int position);
    }
}
