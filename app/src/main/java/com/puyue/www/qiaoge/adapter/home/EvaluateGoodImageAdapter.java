package com.puyue.www.qiaoge.adapter.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/26
 */
public class EvaluateGoodImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public EvaluateGoodImageAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView;
        imageView= helper.getView(R.id.iv_good);
        Glide.with(mContext).load(item).into(imageView);
    }
}
