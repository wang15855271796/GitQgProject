package com.puyue.www.qiaoge.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.banner.loader.ImageLoaderUse;


/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/12.
 */

public class GlideImageLoader extends ImageLoaderUse {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }

//    @Override
//    public ImageView createImageView(Context context) {
//        //圆角
//        return new RoundAngleImageView(context);
//    }
}
