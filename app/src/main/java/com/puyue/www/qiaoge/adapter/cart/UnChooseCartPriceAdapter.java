package com.puyue.www.qiaoge.adapter.cart;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SearchReasultActivity;
import com.puyue.www.qiaoge.adapter.home.SearchResultAdapter;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.cart.CartsListModel;

import java.util.List;

class UnChooseCartPriceAdapter extends BaseQuickAdapter<CartsListModel.DataBean.InValidListBean.SpecProductListBeanX.ProductDescVOListBeanX,BaseViewHolder> {

    private TextView tv_search;
    String productName;
    public UnChooseCartPriceAdapter(int layoutResId, @Nullable List<CartsListModel.DataBean.InValidListBean.SpecProductListBeanX.ProductDescVOListBeanX> data, String productName) {
        super(layoutResId, data);
        this.productName = productName;
    }

    @Override
    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.InValidListBean.SpecProductListBeanX.ProductDescVOListBeanX item) {
        helper.setText(R.id.tv_unit,item.getUnitDesc());
        helper.setText(R.id.tv_price, item.getPriceStr());
        tv_search = helper.getView(R.id.tv_search);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SearchReasultActivity.class);
                intent.putExtra(AppConstant.SEARCHWORD,productName);
                mContext.startActivity(intent);
            }
        });
    }
}
