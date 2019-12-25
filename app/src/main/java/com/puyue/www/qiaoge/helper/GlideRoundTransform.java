package com.puyue.www.qiaoge.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2018/3/29.
 */

public class GlideRoundTransform extends BitmapTransformation {

    private static float radius = 0f;
    private Paint mBorderPaint;
    private float borderWidth;
    private int borderColor;

    public GlideRoundTransform(Context context,int dp) {
        this(context, dp,1f);
    }

    public GlideRoundTransform(Context context, int dp,float borderWidth) {
        super();
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        mBorderPaint = new Paint();
        mBorderPaint.setColor(borderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStrokeWidth(borderWidth);
        mBorderPaint.setDither(true);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
        return roundCrop(pool, bitmap);
    }

    private  Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(borderWidth, borderWidth, source.getWidth()-borderWidth, source.getHeight()-borderWidth);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        /************************描边*********************/
        //注意：避免出现描边被屏幕边缘裁掉
        // 画边框
        RectF rectF2 = new RectF(borderWidth/2, 0+borderWidth/2, source.getWidth()-borderWidth/2, source.getHeight()-borderWidth/2);
        canvas.drawRoundRect(rectF2, radius, radius, mBorderPaint);
        return result;
    }

    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }


//    private static float radius = 0f;
//
//    public GlideRoundTransform(Context context) {
//        this(context, 4);
//    }
//
//    public GlideRoundTransform(Context context, int dp) {
//        super(context);
//        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
//    }
//
//    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
//        return roundCrop(pool, toTransform);
//    }
//
//    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
//        if (source == null) return null;
//
//        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//        if (result == null) {
//            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//        }
//
//        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
//        paint.setAntiAlias(true);
//        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
//        canvas.drawRoundRect(rectF, radius, radius, paint);
//        return result;
//    }
//
// /*   @Override public String getId() {
//        return getClass().getName() + Math.round(radius);
//    }*/
//
//    @Override
//    public void updateDiskCacheKey(MessageDigest messageDigest) {
//
//    }
}
