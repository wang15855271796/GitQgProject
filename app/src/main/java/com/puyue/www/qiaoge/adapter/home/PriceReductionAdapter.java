package com.puyue.www.qiaoge.adapter.home;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.PriceReductionModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by ${daff}
 * on 2018/10/17
 * 备注 降价商品
 */
public class PriceReductionAdapter extends BaseQuickAdapter <GetProductListModel.DataBean.ListBean,BaseViewHolder>{

    private RelativeLayout linearLayoutSold;// 是否售完
    private TextView priceReduction;
    private ImageView shoppingCart;
    private OnclickAdd onClickImage;
    private TextView tv_item_goods_spc;
    private  TextView tvOriginalPrice;


    public PriceReductionAdapter(int layoutResId, @Nullable List<GetProductListModel.DataBean.ListBean> data,OnclickAdd onClickImage) {
        super(layoutResId, data);
        this.onClickImage=onClickImage;
    }



    @Override
    protected void convert(final BaseViewHolder helper, GetProductListModel.DataBean.ListBean model) {
        tvOriginalPrice=helper.getView(R.id.tvOriginalPrice);
        linearLayoutSold=helper.getView(R.id.item_goods_type);
        priceReduction=helper.getView(R.id.priceReduction);
        tv_item_goods_spc=helper.getView(R.id.tv_item_goods_spc);
        shoppingCart=helper.getView(R.id.shoppingCart);
        helper.setText(R.id.tv_item_goods_name, model.productName);
        helper.setText(R.id.tv_item_goods_specification, model.spec);
        helper.setText(R.id.tv_item_goods_price,"￥"+ model.price);
        helper.setText(R.id.tv_item_goods_spc,"/"+model.num+model.productUnitName);
        helper.setText(R.id.tvBay, model.monthSalesVolume);
        GlideModel.disPlayError(mContext,model.imgUrl,helper.getView(R.id.iv_item_goods_img));
      /*  Glide.with(mContext).load(model.imgUrl).crossFade().transform(new GlideRoundTransform(mContext, 3)).
                error(R.mipmap.icon_default_rec).into((ImageView) helper.getView(R.id.iv_item_goods_img));*/
        priceReduction.setVisibility(View.GONE);

        tvOriginalPrice.setText("原价："+model.oldPrice);
       tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰

        shoppingCart.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                onClickImage.addCarOnclick(helper.getLayoutPosition());
            }
        });

        if (model.flag == 1) {
            //未售完
            linearLayoutSold.setVisibility(View.GONE);
           // shoppingCart.setVisibility(View.VISIBLE);
        } else {
            linearLayoutSold.getBackground().setAlpha(150);
            linearLayoutSold.setVisibility(View.VISIBLE);
            shoppingCart.setEnabled(false);
        }
    }

    public interface OnclickAdd {
        void addCarOnclick(int position);
    }

}
