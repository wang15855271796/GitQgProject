package com.puyue.www.qiaoge.adapter.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.GetCommonProductModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/25.
 */

public class CommonProductAdapter extends BaseQuickAdapter<GetCommonProductModel.DataBean.ListBean, BaseViewHolder> {

    private ImageView ivSoldOut;// 是否售完
    private TextView priceReduction;
    private ImageView shoppingCart;

    private Onclick onclick;

    public CommonProductAdapter(int layoutResId, List<GetCommonProductModel.DataBean.ListBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(final BaseViewHolder helper, GetCommonProductModel.DataBean.ListBean model) {
        GlideModel.disPlayError(mContext,model.imgUrl,helper.getView(R.id.iv_item_goods_img));
        //Glide.with(mContext).load(model.imgUrl).crossFade().into((ImageView) helper.getView(R.id.iv_item_goods_img));
        helper.setText(R.id.tv_item_goods_name, model.productName);
        helper.setText(R.id.tv_item_goods_specification, model.spec);
        helper.setText(R.id.tv_item_goods_price, model.price);
        shoppingCart = helper.getView(R.id.shoppingCart);
        ivSoldOut = helper.getView(R.id.iv_sold_out);
        if (model.flag == 1) {
            //未售完
            ivSoldOut.setVisibility(View.GONE);


        } else {
            ivSoldOut.getBackground().setAlpha(150);
            ivSoldOut.setVisibility(View.VISIBLE);
            shoppingCart.setEnabled(false);

        }

        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.shoppingCart(helper.getLayoutPosition());
            }
        });
    }

    public interface Onclick {
        void shoppingCart(int pos);
    }

}