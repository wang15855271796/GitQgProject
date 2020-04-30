package com.puyue.www.qiaoge.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/4/27
 */
public class ChooseAddressHeadAdapter extends BaseQuickAdapter<AddressModel.DataBean,BaseViewHolder> {

    public ChooseAddressHeadAdapter(int layoutResId, @Nullable List<AddressModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressModel.DataBean item) {
        RelativeLayout rl_root = helper.getView(R.id.rl_root);
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_num = helper.getView(R.id.tv_num);

        tv_title.setText(item.provinceName+item.cityName+item.areaName+item.detailAddress);
        tv_name.setText(item.userName);
        tv_num.setText(item.contactPhone);
//        View view = View.inflate(mContext,R.layout.test,null);
//        rl_root.addView(view);



        /**
         * 判断是否是可送范围（1可送）
         */
        if(item.sendType==1) {
            tv_title.setTextColor(Color.parseColor("#333333"));
            tv_num.setTextColor(Color.parseColor("#999999"));
            tv_name.setTextColor(Color.parseColor("#999999"));
        }else {
            tv_title.setTextColor(Color.parseColor("#999999"));
            tv_num.setTextColor(Color.parseColor("#999999"));
            tv_name.setTextColor(Color.parseColor("#999999"));
        }

        if (item.isDefault == 0) {
            //不是默认地址
            helper.setVisible(R.id.iv_check, false);
        } else if (item.isDefault == 1) {
            //是默认地址
            helper.setVisible(R.id.iv_check, true);
        }

    }
}
