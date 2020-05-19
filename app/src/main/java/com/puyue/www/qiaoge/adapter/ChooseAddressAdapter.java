package com.puyue.www.qiaoge.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.transition.Visibility;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${王涛} on 2020/4/23
 */
public class ChooseAddressAdapter extends BaseQuickAdapter<AddressModel.DataBean, BaseViewHolder> {
    public ChooseAddressAdapter(int layoutResId, @Nullable List<AddressModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressModel.DataBean item) {

    }
//    public static final int CONTENT = 2;
//    public static final int HEAD = 1;
//    List<AddressModel.DataBean> data;
//    private RecyclerView recyclerView;
//    private ChooseAddressHeadAdapter chooseAddressHeadAdapter;
//    List<AddressModel.DataBean> data0 = new ArrayList<>();
//    List<AddressModel.DataBean> data1 = new ArrayList<>();
//    List<AddressModel.DataBean> data2 = new ArrayList<>();
//    public ChooseAddressAdapter(List<AddressModel.DataBean> data) {
//        super(data);
//        this.data = data;
//        for (int i = 0; i <data.size() ; i++) {
//            if(data.get(i).sendType==1) {
//                //可配送
//                data0.add(data.get(i));
//            }else {
//                //不可配送
//                data1.add(data.get(i));
//            }
//        }
//        //可配送布局
//        addItemType(ChooseAddressAdapter.CONTENT, R.layout.item_dialog_address);
//        //不可配送布局
//        addItemType(ChooseAddressAdapter.HEAD, R.layout.item_dialog_address_head);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, AddressModel.DataBean item) {
//        TextView tv_title = helper.getView(R.id.tv_title);
//        TextView tv_name = helper.getView(R.id.tv_name);
//        TextView tv_num = helper.getView(R.id.tv_num);
//
//        switch (helper.getItemViewType()) {
//            case ChooseAddressAdapter.CONTENT:
//                int pos = helper.getAdapterPosition();
//                tv_title.setText(data0.get(pos).provinceName+data0.get(pos).cityName+data0.get(pos).areaName+data0.get(pos).detailAddress);
//                tv_name.setText(data0.get(pos).userName);
//                tv_num.setText(data0.get(pos).contactPhone);
//                break;
//
//            case ChooseAddressAdapter.HEAD:
//                recyclerView = helper.getView(R.id.recyclerView);
//                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//                chooseAddressHeadAdapter = new ChooseAddressHeadAdapter(R.layout.item_dialog_address,data1);
//                recyclerView.setAdapter(chooseAddressHeadAdapter);
////                tv_title.setText(item.provinceName + item.cityName + item.areaName + item.detailAddress);
////                tv_name.setText(item.userName);
////                tv_num.setText(item.contactPhone);
//                break;
//
//        }
//            /**
//             * 判断是否是可送范围（1可送）
//             */
//            if (item.sendType == 1) {
//                tv_title.setTextColor(Color.parseColor("#333333"));
//                tv_num.setTextColor(Color.parseColor("#999999"));
//                tv_name.setTextColor(Color.parseColor("#999999"));
//            } else {
//                tv_title.setTextColor(Color.parseColor("#999999"));
//                tv_num.setTextColor(Color.parseColor("#999999"));
//                tv_name.setTextColor(Color.parseColor("#999999"));
//            }
//
//            if (item.isDefault == 0) {
//                //不是默认地址
//                helper.setVisible(R.id.iv_check, false);
//            } else if (item.isDefault == 1) {
//                //是默认地址
//                helper.setVisible(R.id.iv_check, true);
//            }
//        }

}
