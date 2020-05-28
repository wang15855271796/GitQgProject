package com.puyue.www.qiaoge.adapter.cart;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/10
 */
public class ImageViewAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private ImageView imageView;
    Context mContext;
    public ImageViewAdapter(Context mContext, int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        imageView = helper.getView(R.id.iv_image);
        Glide.with(mContext).load(item).into(imageView);
    }
}
