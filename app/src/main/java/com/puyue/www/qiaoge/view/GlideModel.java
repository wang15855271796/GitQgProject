package com.puyue.www.qiaoge.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;

/**
 * Created by ${王文博} on 2019/5/20
 */
public class GlideModel {

    public static void displayTransForms(Context context, String url, ImageView view) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.icon_default_rec)
                .transform(new GlideRoundTransform(context, 3))
                .error(R.mipmap.icon_default_rec)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
    }


    public static void disPlayError(Context context, String url, ImageView view) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.mipmap.icon_default_rec)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
    }


    public static void disPlayPlaceHolder(Context context, String url, ImageView view) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.icon_default_rec)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
    }

    public static void disPlayErrorPlace(Context context ,String url ,ImageView view){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.mipmap.icon_default_rec)
                .placeholder(R.mipmap.icon_default_rec)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
    }
}
