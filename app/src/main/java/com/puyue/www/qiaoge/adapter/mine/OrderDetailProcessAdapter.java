package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.OrderDetailProcessModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class OrderDetailProcessAdapter extends BaseQuickAdapter<OrderDetailProcessModel, BaseViewHolder> {

    public OrderDetailProcessAdapter(int layoutResId, @Nullable List<OrderDetailProcessModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailProcessModel item) {
        helper.setText(R.id.tv_item_order_detail_process_name, item.processName);
        helper.setText(R.id.tv_item_order_detail_process_time, item.processTime);
    }
}
