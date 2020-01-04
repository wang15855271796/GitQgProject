package com.puyue.www.qiaoge.fragment.mine;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.order.IntellGencyModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/31
 */
public class IntelliGencyAdapter extends BaseQuickAdapter<IntellGencyModel.DataBean,BaseViewHolder> {

    private ImageView iv_pic;

    public IntelliGencyAdapter(int layoutResId, @Nullable List<IntellGencyModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntellGencyModel.DataBean item) {
        helper.setText(R.id.tv_title,item.getCityName());
        iv_pic = helper.getView(R.id.iv_pic);
        Glide.with(mContext).load(item.getLicenseNo()).into(iv_pic);
    }
}
