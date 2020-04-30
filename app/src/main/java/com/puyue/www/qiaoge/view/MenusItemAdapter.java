package com.puyue.www.qiaoge.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.model.home.AddressBean;

import java.util.List;

/**
 * Created by ${王涛} on 2020/3/25
 */
public class MenusItemAdapter extends BaseQuickAdapter<AddressBean.DataBean,BaseViewHolder> {
    public MenusItemAdapter(Context context, int layoutResId, @Nullable List<AddressBean.DataBean> data, int choose_item_selected, int choose_eara_item_selector) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean.DataBean item) {
    }
}
