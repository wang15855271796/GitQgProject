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
public class ExchangeAdapter extends BaseQuickAdapter<ExChangeModel.DetailListBean,BaseViewHolder> {

    public ExchangeAdapter(int layoutResId, @Nullable List<ExChangeModel.DetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExChangeModel.DetailListBean item) {
        helper.setText(R.id.tv_amount,item.list.get(helper.getAdapterPosition())+"");
        helper.setText(R.id.tv_num,item.num+"");
        helper.setText(R.id.tv_expend,item.expend.get(helper.getAdapterPosition())+"");

    }
}
