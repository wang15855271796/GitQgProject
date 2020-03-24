package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/3/16
 */
public class OrderAdapter extends BaseQuickAdapter<GetOrderDetailModel.DataBean.ProductVOListBean.ProductDescVOListBean,BaseViewHolder> {

    public OrderAdapter(int layoutResId, @Nullable List<GetOrderDetailModel.DataBean.ProductVOListBean.ProductDescVOListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrderDetailModel.DataBean.ProductVOListBean.ProductDescVOListBean item) {
        TextView tv_desc = helper.getView(R.id.tv_desc);
        TextView tv_coupon = helper.getView(R.id.tv_coupon);
        TextView tv_total_price = helper.getView(R.id.tv_total_price);
        tv_desc.setText(item.newDesc);
        if(!item.getAfterPrice().equals("")) {
            tv_coupon.setText(item.getAfterPrice());
            tv_coupon.setVisibility(View.VISIBLE);
        }else {
            tv_coupon.setVisibility(View.GONE);
        }

        tv_total_price.setText("￥"+item.getTotalPrice());
    }
}
