package com.puyue.www.qiaoge.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;


import java.util.List;

/**
 * Created by ${王涛} on 2020/6/12
 */
public class TimeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public TimeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv,item);
    }
}
