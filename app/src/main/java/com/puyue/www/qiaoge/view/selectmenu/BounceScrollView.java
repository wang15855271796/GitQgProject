package com.puyue.www.qiaoge.view.selectmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

/**
 * Created by ${王文博} on 2019/6/26
 */
public class BounceScrollView extends ScrollView {
    // 这个值控制可以把ScrollView包裹的控件拉出偏离顶部或底部的距离。
    private static final int MAX_OVER_SCROLL_Y = 100;
    private int newMaxOverScrollY;

    public BounceScrollView(Context context) {
        super(context);
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BounceScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float density = metrics.density;
        newMaxOverScrollY = (int) (density * MAX_OVER_SCROLL_Y);
        //false:隐藏ScrollView的滚动条。
        this.setVerticalScrollBarEnabled(false);
        //去掉拉到底部或者顶部的半透明波纹阴影效果。
        this.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //增加阻尼效果,使滑动有吃力感
        double ratio = 1d;
        if (deltaY > 0 && scrollY + deltaY > scrollRangeY) { //滑动到底部并且向下滑动，使用自己的customOverScrollBy
            ratio = 1.05d + (scrollRangeY - scrollY) / (newMaxOverScrollY * 1.2);
            return customOverScrollBy(deltaX, (int) (deltaY * ratio), scrollX, scrollY,
                    scrollRangeX, scrollRangeY, maxOverScrollX, newMaxOverScrollY, isTouchEvent);
        }
        //其他情况还是直接走super的流程
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        // Not consumed means it wasn't handled because ScrollView
        // doesn't take over scrolling bounds into scroll range,
        // so we fling it ourselves to get it bounce back
        if (!consumed) {
            fling((int) velocityY);
            return true;
        } else {
            return super.dispatchNestedFling(velocityX, velocityY, consumed);
        }
    }

    /**
     * 这里在ScrollView中就不用关心横向滑动问题，可以把原来横向逻辑的代码统统删掉，并且设置默认是可以竖向滑动的
     */
    @SuppressWarnings({"UnusedParameters"})
    protected boolean customOverScrollBy(int deltaX, int deltaY,
                                         int scrollX, int scrollY,
                                         int scrollRangeX, int scrollRangeY,
                                         int maxOverScrollX, int maxOverScrollY,
                                         boolean isTouchEvent) {
        int newScrollX = scrollX + deltaX;
        int newScrollY = scrollY + deltaY;
        // Clamp values if at the limits and record
        final int left = -maxOverScrollX;
        final int right = maxOverScrollX + scrollRangeX;
        final int top = -maxOverScrollY;
        final int bottom = maxOverScrollY + scrollRangeY;
        boolean clampedX = false;
        if (newScrollX > right) {
            newScrollX = right;
            clampedX = true;
        } else if (newScrollX < left) {
            newScrollX = left;
            clampedX = true;
        }
        boolean clampedY = false;
        if (newScrollY > bottom) {
            newScrollY = bottom;
            clampedY = true;
        } else if (newScrollY < top) {
            newScrollY = top;
            clampedY = true;
        }
        onOverScrolled(newScrollX, newScrollY, clampedX, clampedY);
        return clampedX || clampedY;
    }
}

