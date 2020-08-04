package com.puyue.www.qiaoge.adapter.cart;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.cart.CartsListModel;

import java.util.List;

/**
 * 失效的购物车列表
 */
public class UnCartSpecAdapter extends BaseQuickAdapter<CartsListModel.DataBean.InValidListBean.SpecProductListBeanX,BaseViewHolder> {

    RecyclerView recyclerView_price;
    String productName ;
    public UnCartSpecAdapter(int layoutResId, @Nullable List<CartsListModel.DataBean.InValidListBean.SpecProductListBeanX> data, String productName) {
        super(layoutResId, data);
        this.productName = productName;
    }

    @Override
    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.InValidListBean.SpecProductListBeanX item) {
        helper.setText(R.id.tv_spec,"规格："+item.getSpec());
        helper.setText(R.id.tv_stock,item.getInventory());
        CheckBox cb_item_in = helper.getView(R.id.cb_item_in);
        cb_item_in.setVisibility(View.GONE);

        recyclerView_price = helper.getView(R.id.recyclerView_price);
        UnChooseCartPriceAdapter unChooseCartPriceAdapter = new UnChooseCartPriceAdapter(R.layout.item_choose_un,item.getProductDescVOList(),productName);
        recyclerView_price.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView_price.setAdapter(unChooseCartPriceAdapter);


    }
}
