package com.puyue.www.qiaoge.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/5/28.
 */

public class PayResultImageView extends ImageView {
    public PayResultImageView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception e) {
            System.out.println("MyImageView  -> onDraw() Canvas: trying to use a recycled bitmap");
        }
    }
}
