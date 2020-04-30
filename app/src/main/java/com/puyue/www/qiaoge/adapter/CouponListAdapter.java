package com.puyue.www.qiaoge.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.event.CouponListModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/4/17
 */
public class CouponListAdapter extends BaseQuickAdapter<CouponListModel.DataBean.GiftsBean,BaseViewHolder> {

    public CouponListAdapter(int layoutResId, @Nullable List<CouponListModel.DataBean.GiftsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponListModel.DataBean.GiftsBean item) {
        helper.setText(R.id.tv_money,item.getAmountStr());
        helper.setText(R.id.tv_user,item.getLimitAmtStr());
        helper.setText(R.id.tv_title,item.getGiftName());
        helper.setText(R.id.tv_factor,item.getRole().get(0));
        helper.setText(R.id.tv_date,item.getDateTime());
    }
}
