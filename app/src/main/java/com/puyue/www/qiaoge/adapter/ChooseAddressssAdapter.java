package com.puyue.www.qiaoge.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/4/28(不可配送)
 */
public class ChooseAddressssAdapter extends BaseQuickAdapter<AddressModel.DataBean,BaseViewHolder> {


    public ChooseAddressssAdapter(int layoutResId, @Nullable List<AddressModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressModel.DataBean item) {
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_num = helper.getView(R.id.tv_num);
        CheckBox iv_check = helper.getView(R.id.iv_check);
        if(item.sendType==0) {
            tv_title.setText(item.provinceName+item.cityName+item.areaName+item.detailAddress);
            tv_name.setText(item.userName);
            tv_num.setText(item.contactPhone);
            iv_check.setVisibility(View.GONE);
        }else {
            iv_check.setVisibility(View.VISIBLE);
        }

        tv_title.setTextColor(Color.parseColor("#999999"));
        tv_name.setTextColor(Color.parseColor("#999999"));
        tv_num.setTextColor(Color.parseColor("#999999"));

//        if (item.isDefault == 0) {
//            //不是默认地址
//            helper.setVisible(R.id.iv_check, false);
//        } else if (item.isDefault == 1) {
//            //是默认地址
//            helper.setVisible(R.id.iv_check, true);
//        }

    }
}
