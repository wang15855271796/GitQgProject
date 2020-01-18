package com.puyue.www.qiaoge.fragment.mine;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Log;
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
    private ImageView iv_pics;
    public IntelliGencyAdapter(int layoutResId, @Nullable List<IntellGencyModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntellGencyModel.DataBean item) {
        helper.setText(R.id.tv_title,item.getCityName());
        iv_pic = helper.getView(R.id.iv_pic);
        iv_pics = helper.getView(R.id.iv_pics);
        if(!item.getLicenseNo().equals("")) {
            Glide.with(mContext).load(item.getLicenseNo()).into(iv_pic);
            iv_pic.setVisibility(View.VISIBLE);
        }else {
            iv_pic.setVisibility(View.GONE);
        }

        if(!item.getQualification().equals("")) {
            Glide.with(mContext).load(item.getQualification()).into(iv_pics);
            iv_pics.setVisibility(View.VISIBLE);
        }else {
            iv_pics.setVisibility(View.GONE);
        }
    }
}
