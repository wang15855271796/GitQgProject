package com.puyue.www.qiaoge.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.MySubOrderActivity;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.ReturnGoodDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.SelfSufficiencyOrderDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.SubAccountListModel;
import com.puyue.www.qiaoge.helper.UserInfoHelper;

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
        LinearLayout ll_root = helper.getView(R.id.ll_root);
        String deliverType = UserInfoHelper.getDeliverType(mContext);
        ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setText(R.id.tv_read,"已读");
                if (deliverType.equals("0")) {
                    if (item.getOrderState() == 11){
                        Intent intent1 =new Intent(mContext,ReturnGoodDetailActivity.class);
                        intent1.putExtra("orderType" ,0);
                        intent1.putExtra(AppConstant.RETURNPRODUCTMAINID, item.getId());
                        mContext.startActivity(intent1);

                    }else {
                        Intent intent2 = new Intent(mContext, NewOrderDetailActivity.class);
                        intent2.putExtra(AppConstant.ORDERID,item.getOrderId());
                        intent2.putExtra(AppConstant.ORDERSTATE, "3");
                        intent2.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                        mContext.startActivity(intent2);
                    }

                } else if (deliverType.equals("1")) {
                    if (item.getOrderState() == 11){
                        Intent intent3 =new Intent(mContext,ReturnGoodDetailActivity.class);
                        intent3.putExtra("orderType" ,1);
                        intent3.putExtra(AppConstant.RETURNPRODUCTMAINID, item.getId());
                        mContext.startActivity(intent3);

                    }else {
                        Intent intent4 = new Intent(mContext, SelfSufficiencyOrderDetailActivity.class);
                        intent4.putExtra(AppConstant.ORDERID, item.getOrderId());
                        intent4.putExtra(AppConstant.ORDERSTATE, "3");
                        intent4.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                        mContext.startActivity(intent4);
                    }
                }
            }
        });
//        TextView tv_read = helper.getView(R.id.tv_read);
//        tv_read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
