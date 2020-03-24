package com.puyue.www.qiaoge.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by ${王文博} on 2019/6/28
 */
public class FlipScrollView extends ScrollView {
    private View mInnerView;
    private Rect rect = new Rect();
    private float y;
    private float dampNumber=2.5f;

    float Intery;
    int total;
    float prey;

    public FlipScrollView(Context context) {
        super(context);
    }

    public FlipScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlipScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>0){
            mInnerView = getChildAt(0);
        }
    }


    /**
     * 键盘弹起监听
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        if (onSoftKeyboardListener != null) {
            final int newSpec = MeasureSpec.getSize(heightMeasureSpec);
            final int oldSpec = getMeasuredHeight();
            // If layout became smaller, that means something forced it to resize. Probably soft keyboard :)
            if (oldSpec > newSpec){
                onSoftKeyboardListener.onShown();
            } else {
                onSoftKeyboardListener.onHidden();
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private OnSoftKeyboardListener onSoftKeyboardListener;

    public final void setOnSoftKeyboardListener(final OnSoftKeyboardListener listener) {
        this.onSoftKeyboardListener = listener;
    }

    // Simplest possible listener :)
    public interface OnSoftKeyboardListener {
        public void onShown();
        public void onHidden();
    }

    /**
     *回弹效果
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Intery = ev.getY();
                prey=Intery;
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                int v = (int) Math.abs(prey - y);
                total=total+v;
                prey = y;
                if(total>=20){
                    total=0;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mInnerView == null) {
            return super.onTouchEvent(ev);
        } else {
            setFlipMotionEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    private void setFlipMotionEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                super.onInterceptTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                final float preY = y;
                float nowy=ev.getY();
                int distancey = (int) ((preY - nowy) / dampNumber);
                y=nowy;

                if(isNeedMove()){
                    if(rect.isEmpty()){
                        rect.set(mInnerView.getLeft(),mInnerView.getTop(),mInnerView.getRight(),mInnerView.getBottom());
                        return;
                    }
                    int yyT = (int) (mInnerView.getTop()-distancey);
                    int yyB = (int) (mInnerView.getBottom()-distancey);
                    mInnerView.layout(mInnerView.getLeft(),yyT,mInnerView.getRight(),yyB);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isNeedAnimation()){
                    animation();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return true;
    }

    private boolean isNeedMove() {
        int offset = mInnerView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY >= offset) {
            return true;
        }
        return false;
    }

    private void animation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, mInnerView.getTop()-rect.top,0);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setDuration(250);
        mInnerView.startAnimation(translateAnimation);
        mInnerView.layout(rect.left, rect.top,rect.right,rect.bottom);
        rect.setEmpty();
    }

    private boolean isNeedAnimation() {
        return !rect.isEmpty();
    }
}

