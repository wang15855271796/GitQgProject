package com.puyue.www.qiaoge.view;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by ${王文博} on 2019/4/15
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

 /*   @Override
    public void registerComponents(Context context, Glide glide) {

    }*/

    @Override
    public void registerComponents(Context context, Registry registry) {

    }
}
