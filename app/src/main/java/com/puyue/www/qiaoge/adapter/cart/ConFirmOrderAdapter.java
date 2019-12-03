package com.puyue.www.qiaoge.adapter.cart;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.cart.OrderNumModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/25.
 */

public class ConFirmOrderAdapter extends BaseMultiItemQuickAdapter<OrderNumModel, BaseViewHolder> {

    public ConFirmOrderAdapter(List data) {
        super(data);
        addItemType(OrderNumModel.lineType, R.layout.view_line);
        addItemType(OrderNumModel.textType, R.layout.item_confirm_order_num);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderNumModel item) {
        switch (helper.getItemViewType()) {
            case OrderNumModel.lineType:
                break;
            case OrderNumModel.textType:
                helper.setText(R.id.tv_item_order_spec, item.detailDesc);
                helper.setText(R.id.tv_item_order_num, item.totalNum);
                break;
        }
    }
}