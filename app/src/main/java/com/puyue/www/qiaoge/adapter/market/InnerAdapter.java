package com.puyue.www.qiaoge.adapter.market;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.ClassIfyModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/30
 */
class InnerAdapter extends BaseQuickAdapter<ClassIfyModel.DataBean.SecondClassifyBean,BaseViewHolder> {

    int selectPosition;
    List<ClassIfyModel.DataBean.SecondClassifyBean> data;

    public InnerAdapter(int layoutResId, @Nullable List<ClassIfyModel.DataBean.SecondClassifyBean> data) {
        super(layoutResId, data);
        this.data = data;

    }

    @Override
    protected void convert(BaseViewHolder helper, ClassIfyModel.DataBean.SecondClassifyBean item) {
        TextView tv_name = helper.getView(R.id.tv_name_second);
        helper.addOnClickListener(R.id.ll_bg);
        tv_name.setText(item.getName());
        View iv_point = helper.getView(R.id.iv_point);
        if(selectPosition==helper.getAdapterPosition()) {
            tv_name.setTextColor(Color.parseColor("#F56D23"));
            iv_point.setBackgroundColor(Color.parseColor("#FF680A"));

        }else {
            tv_name.setTextColor(Color.parseColor("#666666"));
            iv_point.setBackgroundColor(Color.parseColor("#ffffff"));


        }
    }

    public void selectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }
}
