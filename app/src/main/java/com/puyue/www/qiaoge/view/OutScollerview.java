package com.puyue.www.qiaoge.view;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 描述：
 */
public class OutScollerview extends ScrollView {

    private int mStartY = 0;
    private static final String TAG = "OutScrollView ";
  private   PointF pointF;
    public OutScollerview(Context context) {
        super(context);
    }

    public OutScollerview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutScollerview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //获取屏幕上点击的坐标
                Log.e(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION_UP");

            default:
                break;
        }

        return super.onTouchEvent(event);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartY = (int) ev.getY();

              pointF = new PointF();
              pointF.x =ev.getX();
                intercepted = false;
                super.onInterceptTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int endY = (int) ev.getY();
                if (isRelMove(pointF,ev)) {
                    if (getScrollY() != 0 || endY - mStartY < 0) {
                        intercepted = true;
                        Log.e("我处理", "自己处理");
                    } else {
                        Log.e("子类处理", "交给子布局");
                        intercepted = false;
                    }
                }
    /*        if (getScrollY()!=0){
                intercepted=true;
            }else {
                intercepted=false;
            }
           if(endY==mStartY){
               intercepted=false;
           }else if (endY -mStartY<0){
               intercepted=true;
           }else {
               intercepted=false;
           }*/

                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }
        return intercepted;
    }


    /**
     * 判断是否是真实的滑动手势（解决在华为手机上两次点击坐标一致的情况下系统也返回滑动事件标识的问题）
     *
     * @param downPointF 触发 ACTION_DOWN 时保存的坐标
     * @param moveEvent  触发 ACTION_MOVE 时的MotionEvent
     */
    private boolean isRelMove(PointF downPointF, MotionEvent moveEvent) {
        return moveEvent.getAction() == MotionEvent.ACTION_MOVE && Math.abs(moveEvent.getX() - downPointF.x) > 0;
    }

}

