package com.puyue.www.qiaoge.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.puyue.www.qiaoge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: LuckPan
 * @Package: com.itfitness.luckpan.widget
 * @ClassName: LuckPan
 * @Description: java类作用描述 ：
 * @Author: 作者名：lml
 * @CreateDate: 2019/3/12 15:50
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/3/12 15:50
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class LuckPan extends View {
    private Paint mPaintArc;//转盘扇形画笔
    private Paint mPaintItemStr;//转盘文字画笔
    private int mRadius;//圆盘的半径
    private RectF rectFPan;//构建转盘的矩形
    private RectF rectFStr;//构建文字圆盘的矩形
    private List<String> mItemStrs;
    private ArrayList<Path> mArcPaths;
    private float mItemAnge;
    private int mRepeatCount = 4;//转几圈
    private int mLuckNum = 2;//最终停止的位置
    private float mStartAngle = 0;//存储圆盘开始的位置
    private float mOffsetAngle = 0;//圆盘偏移角度（当Item数量为4的倍数的时候）
    private float mTextSize = 20;//文字大小
    private ObjectAnimator objectAnimator;
    private LuckPanAnimEndCallBack luckPanAnimEndCallBack;

    public LuckPanAnimEndCallBack getLuckPanAnimEndCallBack() {
        return luckPanAnimEndCallBack;
    }

    public void setLuckPanAnimEndCallBack(LuckPanAnimEndCallBack luckPanAnimEndCallBack) {
        this.luckPanAnimEndCallBack = luckPanAnimEndCallBack;
    }

    public LuckPan(Context context) {
        this(context,null);
    }

    public LuckPan(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LuckPan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintArc = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintArc.setStyle(Paint.Style.FILL);

        mPaintItemStr = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintItemStr.setColor(Color.parseColor("#ED2F2F"));
        mPaintItemStr.setStrokeWidth(3);
        mPaintItemStr.setTextAlign(Paint.Align.CENTER);

        mArcPaths = new ArrayList<>();

//        for ( int i = 0; i < 5; i++ ) {
//            mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_add));
//        }
    }

    /**
     * 设置转盘数据
     * @param items
     */
    public void setItems(List<String> items){
        mItemStrs = items;
        mOffsetAngle=0;
        mStartAngle=0;
        mOffsetAngle = 360/items.size()/2;
        invalidate();
    }
    /**
     * 设置转盘数据
     */
    public void setLuckNumber(int luckNumber){
        mLuckNum = luckNumber;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadius = (int) (Math.min(w,h)/2*0.9);
        //这里是将（0，0）点作为圆心
        rectFPan = new RectF(-mRadius,-mRadius,mRadius,mRadius);
        rectFStr = new RectF(-mRadius/7*5,-mRadius/7*5,mRadius/7*5,mRadius/7*5);
        //每一个Item的角度
        mItemAnge = 360 / mItemStrs.size();
        mTextSize = mRadius/9;
        mPaintItemStr.setTextSize(mTextSize);
        //数据初始化
        mOffsetAngle=0;
        mStartAngle=0;
        mOffsetAngle = mItemAnge/2;
    }
    public void startAnim(){
        if(objectAnimator!=null){
            objectAnimator.cancel();
        }
        float v = mItemAnge*mLuckNum+mStartAngle%360;//如果转过一次了那下次旋转的角度就需要减去上一次多出的，否则结束的位置会不断增加的
        objectAnimator = ObjectAnimator.ofFloat(this, "rotation", mStartAngle, mStartAngle-mRepeatCount*360-v);
        objectAnimator.setDuration(3000);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(luckPanAnimEndCallBack!=null){
                    luckPanAnimEndCallBack.onAnimEnd(mItemStrs.get(3));
                }
            }
        });

        objectAnimator.start();
        mStartAngle -= mRepeatCount*360+v;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);//画布中心点设置为（0，0）
        canvas.rotate(-90-mOffsetAngle);
        drawPanItem(canvas);
        drawText(canvas);
//        drawBg(canvas);
    }

    private List<Bitmap> mListBitmap = new ArrayList<>();

    private void drawBg(Canvas canvas) {
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        float startAngle = -mItemAnge / 2 - 90;
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        for (int i = 0; i <5 ; i++) {
            float angle = ( float ) Math.toRadians(startAngle + mItemAnge / 2);
            //确定图片在圆弧中 中心点的位置
            float x = ( float ) (width / 2 + (mRadius / 2 + mRadius / 12) * Math.cos(angle));
            float y = ( float ) (height / 2 + (mRadius / 2 + mRadius / 12) * Math.sin(angle));
            int imgWidth = mRadius / 3;
            int w = ( int ) (Math.abs(Math.cos(Math.toRadians(Math.abs(180 - mItemAnge * i)))) *
                    imgWidth + imgWidth * Math.abs(Math.sin(Math.toRadians(Math.abs(180 - mItemAnge * i)))));
            int h = ( int ) (Math.abs(Math.sin(Math.toRadians(Math.abs(180 - mItemAnge * i)))) *
                    imgWidth + imgWidth * Math.abs(Math.cos(Math.toRadians(Math.abs(180 - mItemAnge * i)))));

            RectF rect1 = new RectF(x - w *2, y - h *2, x + w *2, y + h *2);
            canvas.drawBitmap(mListBitmap.get(i), null, rect1, null);
        }
    }

    //画文字
    private void drawText(Canvas canvas) {
        for(int x = 0;x<mItemStrs.size();x++){
            Path path = mArcPaths.get(x);
            canvas.drawTextOnPath(mItemStrs.get(x),path,0,0,mPaintItemStr);
        }
    }

    private void drawPanItem(Canvas canvas) {
        float startAng = 0;//扇形开始的角度
        for (int x = 1;x<= mItemStrs.size();x++){
            if(x%2 == 1){
                //是奇数
                mPaintArc.setColor(Color.WHITE);
            }else {
                //偶数
                mPaintArc.setColor(Color.parseColor("#FEB446"));
            }
            Path path = new Path();
            path.addArc(rectFStr,startAng,mItemAnge);//文字的路径圆形比盘的小
            mArcPaths.add(path);
            canvas.drawArc(rectFPan,startAng,mItemAnge,true,mPaintArc);
            startAng+=mItemAnge;
        }
    }
}
