package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AddressAdapter extends BaseQuickAdapter<AddressModel.DataBean, BaseViewHolder> {
    private OnEventClickListener mOnEventClickListener;

    public AddressAdapter(int layoutResId, @Nullable List<AddressModel.DataBean> data) {
        super(layoutResId, data);
    }

    public interface OnEventClickListener {
        void onEventClick(View view, int position, String flag);

        void onEventLongClick(View view, int position, String flag);
    }

    public void setOnItemChangeClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, AddressModel.DataBean item) {
        helper.setText(R.id.tv_item_address_name, item.userName);
        helper.setText(R.id.tv_item_address_phone, item.contactPhone);
        helper.setText(R.id.tv_item_address_address, item.provinceName+item.cityName+item.areaName+item.detailAddress);
        /*if (StringHelper.notEmptyAndNull(item.shopName)) {
            ((LinearLayout) helper.getView(R.id.ll_item_address_store)).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_item_address_store, item.shopName);
        } else {
            ((LinearLayout) helper.getView(R.id.ll_item_address_store)).setVisibility(View.GONE);
        }*/
        if (item.isDefault == 0) {
            //不是默认地址
            helper.setChecked(R.id.cb_item_address_default, false);
        } else if (item.isDefault == 1) {
            //是默认地址
            helper.setChecked(R.id.cb_item_address_default, true);
        }
        helper.getView(R.id.fl_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnEventClickListener.onEventLongClick(v,helper.getAdapterPosition(),"");
            }
        });

        //切换默认地址
        ((LinearLayout) helper.getView(R.id.ll_item_address_default)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "default");
            }
        });
        //编辑地址
        ((TextView) helper.getView(R.id.tv_item_address_edit)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "edit");
            }
        });
        //删除地址
        ((TextView) helper.getView(R.id.tv_item_address_delete)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "delete");
            }
        });
        //切换订单地址
        ((LinearLayout) helper.getView(R.id.ll_item_address)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "order_address");
            }
        });
    }
}
