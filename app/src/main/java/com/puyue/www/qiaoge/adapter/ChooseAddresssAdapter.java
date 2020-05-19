package com.puyue.www.qiaoge.adapter;

import android.media.Image;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.account.AddressAdapters;
import com.puyue.www.qiaoge.activity.mine.account.EditAddressActivity;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/4/28 (可配送)
 */
public class ChooseAddresssAdapter extends BaseQuickAdapter<AddressModel.DataBean,BaseViewHolder> {

    String orderId;
    Onclick onclick;
    private OnEventClickListener mOnEventClickListener;
    public ChooseAddresssAdapter(int layoutResId, @Nullable List<AddressModel.DataBean> data, String orderId, Onclick onclick) {
        super(layoutResId, data);
        this.orderId = orderId;
        this.onclick = onclick;
    }


    public interface OnEventClickListener {
        void onEventClick(View view, int position, String flag);

        void onEventLongClick(View view, int position, String flag);
    }

    public void setOnItemChangeClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }


    @Override
    protected void convert(BaseViewHolder helper, AddressModel.DataBean item) {
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_num = helper.getView(R.id.tv_num);
        ImageView iv_edit = helper.getView(R.id.iv_edit);
        CheckBox iv_check = helper.getView(R.id.iv_check);
        if(item.sendType==1) {
            tv_title.setText(item.provinceName+item.cityName+item.areaName+item.detailAddress);
            tv_name.setText(item.userName);
            tv_num.setText(item.contactPhone);
            iv_check.setVisibility(View.VISIBLE);
        }else {
            iv_check.setVisibility(View.GONE);
        }
        if (item.isDefault == 0) {
            //不是默认地址
            helper.setChecked(R.id.iv_check, false);
        } else if (item.isDefault == 1) {
            //是默认地址
            helper.setChecked(R.id.iv_check, true);

        }

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.jump(helper.getAdapterPosition());
                }
            }
        });



        //切换默认地址
         helper.getView(R.id.rl_item_address).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, helper.getAdapterPosition(), "default");

            }
        });


    }

    public interface Onclick {
        void jump(int position);
    }

}
