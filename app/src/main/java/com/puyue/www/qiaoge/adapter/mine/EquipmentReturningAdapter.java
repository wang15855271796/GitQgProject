package com.puyue.www.qiaoge.adapter.mine;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.EquipmentGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.OrderDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.EquipmentModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class EquipmentReturningAdapter extends BaseQuickAdapter<MyOrdersModel.DataBean.ListBean, BaseViewHolder> {

    public EquipmentReturningAdapter(int layoutResId, @Nullable List<MyOrdersModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyOrdersModel.DataBean.ListBean item) {
        helper.setText(R.id.tv_item_equipment_returning_name, item.productVOList.get(0).name);
        helper.setText(R.id.tv_item_equipment_returning_time, item.gmtCreate);
        final String orderId = item.orderId;
        ((RelativeLayout) helper.getView(R.id.rl_equipment_item_jump)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //跳转设备订单详情页
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra(AppConstant.ORDERID, orderId);
                intent.putExtra(AppConstant.ORDERSTATE, "");
                intent.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                mContext.startActivity(intent);
            }
        });
    }
}
