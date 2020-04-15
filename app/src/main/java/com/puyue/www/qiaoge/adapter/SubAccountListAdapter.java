package com.puyue.www.qiaoge.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.MySubOrderActivity;
import com.puyue.www.qiaoge.event.SubAccountListModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/4/9
 */
public class SubAccountListAdapter extends BaseQuickAdapter<SubAccountListModel.DataBean.ListBean,BaseViewHolder> {

    public SubAccountListAdapter(int layoutResId, @Nullable List<SubAccountListModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubAccountListModel.DataBean.ListBean item) {
        helper.setText(R.id.tv_order_num,item.getOrderId());
        helper.setText(R.id.tv_amount,item.getOrderAmount());
        helper.setText(R.id.tv_date,item.getDateTime());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_phone,item.getPhone());
        helper.setText(R.id.tv_read,item.getState());

//        TextView tv_read = helper.getView(R.id.tv_read);
//        tv_read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
