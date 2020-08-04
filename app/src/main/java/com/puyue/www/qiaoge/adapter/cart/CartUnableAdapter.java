package com.puyue.www.qiaoge.adapter.cart;

import android.content.Intent;
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
import com.puyue.www.qiaoge.activity.home.SearchReasultActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.cart.CartsListModel;

import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CartUnableAdapter extends BaseQuickAdapter<CartsListModel.DataBean.InValidListBean, BaseViewHolder> {

    private TextView tv_price;
    private TextView tv_search;
    public CartUnableAdapter(int layoutResId, @Nullable List<CartsListModel.DataBean.InValidListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.InValidListBean item) {
        CheckBox cb_item_out = helper.getView(R.id.cb_item_out);
        cb_item_out.setVisibility(View.GONE);
        tv_price = helper.getView(R.id.tv_price);
        helper.setText(R.id.tv_title,item.getProductName());
        ImageView iv_head = helper.getView(R.id.iv_head);
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_head);
        if(!item.getPriceStr().equals("")) {
            tv_price.setText(item.getPriceStr());
        }
        ImageView iv_flag = helper.getView(R.id.iv_flag);
        Glide.with(mContext).load(item.getFlagUrl()).into(iv_flag);
        tv_search = helper.getView(R.id.tv_search);

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SearchReasultActivity.class);
                intent.putExtra(AppConstant.SEARCHWORD,item.getProductName());
                mContext.startActivity(intent);
            }
        });
    }
}