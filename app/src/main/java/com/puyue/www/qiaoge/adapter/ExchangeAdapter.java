package com.puyue.www.qiaoge.adapter;

import android.media.Image;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.cart.ExChangeModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/7/8
 */
public class ExchangeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ExchangeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_amount,"优惠券"+item+"元");
        helper.setText(R.id.tv_num,"*1");
        helper.setText(R.id.tv_expend,item+"元");

    }
}
