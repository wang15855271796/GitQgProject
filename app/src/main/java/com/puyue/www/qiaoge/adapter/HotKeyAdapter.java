package com.puyue.www.qiaoge.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.HotKeyModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/6/30
 */
public class HotKeyAdapter extends BaseQuickAdapter<HotKeyModel.DataBean, BaseViewHolder> {
    public HotKeyAdapter(int layoutResId, @Nullable List<HotKeyModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotKeyModel.DataBean item) {
        helper.setText(R.id.tv_content,item.getKey()+item.getKeyBegin()+item.getKeyEnd());

    }
}
