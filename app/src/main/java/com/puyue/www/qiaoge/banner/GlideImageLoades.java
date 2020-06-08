package com.puyue.www.qiaoge.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.puyue.www.qiaoge.banner.loader.ImageLoaderUse;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;

/**
 * Created by ${王涛} on 2020/6/4
 */
public class GlideImageLoades extends ImageLoaderUse {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
//        Glide.with(context.getApplicationContext())
//                .load(path)
//                .into(imageView);

        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        RequestOptions myOptions;
        myOptions = new RequestOptions()
                .centerCrop()
                .transform(new GlideRoundTransform(context, 0));
        //Glide 加载图片简单用法
        Glide.with(context)
                .load(path)
                .apply(myOptions)
                .into(imageView);


    }

}
