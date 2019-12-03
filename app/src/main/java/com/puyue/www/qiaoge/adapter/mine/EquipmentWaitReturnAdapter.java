package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/11.
 */

public class EquipmentWaitReturnAdapter extends BaseQuickAdapter<MyOrdersModel.DataBean.ListBean, BaseViewHolder> {
    public Map<Integer, Boolean> isCheck;
    private OnEventClickListener mOnEventClickListener;

    public EquipmentWaitReturnAdapter(int layoutResId, @Nullable List<MyOrdersModel.DataBean.ListBean> data, Map<Integer, Boolean> isCheck) {
        super(layoutResId, data);
        this.isCheck = isCheck;
    }

    public interface OnEventClickListener {
        void onEventClick(View view, int position, String type);

        void onEventLongClick(View view, int position, String type);
    }

    public void setOnItemClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }


    @Override
    protected void convert(final BaseViewHolder helper, MyOrdersModel.DataBean.ListBean item) {
        helper.setText(R.id.tv_item_equipment_wait_return_name, item.productVOList.get(0).name);
        helper.setText(R.id.tv_item_equipment_wait_return_time, item.gmtCreate);
        helper.setChecked(R.id.cb_item_equipment_wait_return, isCheck.get(helper.getAdapterPosition()));
        ((FrameLayout) helper.getView(R.id.fl_item_equipment_wait_return_check)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "check");
            }
        });
        ((RelativeLayout) helper.getView(R.id.rl_item_equipment_wait_return_jump)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "jump");
            }
        });
    }
}
