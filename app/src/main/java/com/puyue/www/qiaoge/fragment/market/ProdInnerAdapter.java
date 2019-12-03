package com.puyue.www.qiaoge.fragment.market;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.constant.AppConstant;

import java.util.List;

/**
 * Created by ${王涛} on 2019/11/1
 */
class ProdInnerAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.BrandProdBean.ListBeanX.ProdClassifyBean.ListBean,BaseViewHolder> {

    public ProdInnerAdapter(int layoutResId, @Nullable List<MarketRightModel.DataBean.BrandProdBean.ListBeanX.ProdClassifyBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.BrandProdBean.ListBeanX.ProdClassifyBean.ListBean item) {
        helper.setText(R.id.tv_name,item.getProductName());
        helper.setText(R.id.tv_sale,item.getSalesVolume());
        helper.setText(R.id.tv_price,item.getMinMaxPrice());
        ImageView iv_pic = helper.getView(R.id.iv_pic);
        iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
                mContext.startActivity(intent);
            }
        });
        Glide.with(mContext)
                .load(item.getDefaultPic())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .apply(new RequestOptions().placeholder(iv_pic.getDrawable()).skipMemoryCache(false).dontAnimate())
                .into(iv_pic);

    }
}
