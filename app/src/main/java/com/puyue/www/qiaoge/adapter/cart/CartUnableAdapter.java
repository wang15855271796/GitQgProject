package com.puyue.www.qiaoge.adapter.cart;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.cart.CartsListModel;

import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CartUnableAdapter extends BaseQuickAdapter<CartsListModel.DataBean.InValidListBean, BaseViewHolder> {

    public CartUnableAdapter(int layoutResId, @Nullable List<CartsListModel.DataBean.InValidListBean> data) {
        super(layoutResId, data);


    }

    @Override
    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.InValidListBean item) {
        CheckBox cb_item_out = helper.getView(R.id.cb_item_out);
        cb_item_out.setVisibility(View.GONE);
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        helper.setText(R.id.tv_title,item.getProductName());
        ImageView iv_head = helper.getView(R.id.iv_head);
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_head);


        UnCartSpecAdapter cartSpecAdapter = new UnCartSpecAdapter(R.layout.item_cart_spec, item.getSpecProductList());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(cartSpecAdapter);

    }
}