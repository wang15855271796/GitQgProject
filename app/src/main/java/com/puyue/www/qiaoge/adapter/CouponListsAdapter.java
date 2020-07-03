package com.puyue.www.qiaoge.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by ${王涛} on 2020/7/3
 */
public class CouponListsAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    List<String> list;
    public CouponListsAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
    public void addData(int position) {
        //   在list中添加数据，并通知条目加入一条
         list.add(position,"ss");
         notifyItemInserted(position);
    }

}
