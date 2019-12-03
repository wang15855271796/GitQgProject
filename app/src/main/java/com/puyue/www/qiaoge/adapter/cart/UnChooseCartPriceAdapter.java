package com.puyue.www.qiaoge.adapter.cart;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.cart.CartsListModel;

import java.util.List;

class UnChooseCartPriceAdapter extends BaseQuickAdapter<CartsListModel.DataBean.InValidListBean.SpecProductListBeanX.ProductDescVOListBeanX,BaseViewHolder> {

    public UnChooseCartPriceAdapter(int layoutResId, @Nullable List<CartsListModel.DataBean.InValidListBean.SpecProductListBeanX.ProductDescVOListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.InValidListBean.SpecProductListBeanX.ProductDescVOListBeanX item) {
        helper.setText(R.id.tv_unit,item.getUnitDesc());
        TextView tv_num = helper.getView(R.id.tv_num);
        helper.setText(R.id.tv_num, item.getProductNum() + "");
        helper.setText(R.id.tv_price, item.getPriceStr());
    }
}
