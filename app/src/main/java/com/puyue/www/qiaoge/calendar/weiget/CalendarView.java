package com.puyue.www.qiaoge.calendar.weiget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;


import com.puyue.www.qiaoge.calendar.bean.AttrsBean;
import com.puyue.www.qiaoge.calendar.bean.DateBean;
import com.puyue.www.qiaoge.calendar.listener.CalendarViewAdapter;
import com.puyue.www.qiaoge.calendar.listener.OnPagerChangeListener;
import com.puyue.www.qiaoge.calendar.listener.OnSingleChooseListener;
import com.puyue.www.qiaoge.calendar.utils.CalendarUtil;
import com.puyue.www.qiaoge.calendar.utils.SolarUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalendarView extends ViewPager {
    //记录当前PagerAdapter的position
    private int currentPosition;

    private OnPagerChangeListener pagerChangeListener;
    private OnSingleChooseListener singleChooseListener;
    private CalendarViewAdapter calendarViewAdapter;
    private int item_layout;

    private int[] initDate;//日历初始显示的年月
    private int[] startDate;//日历的开始年、月
    private int[] endDate;//日历的结束年、月

    private int count;//ViewPager的页数
    private int[] lastClickDate = new int[2];//记录单选的ViewPager position以及选中的日期
    private SparseArray<HashSet<Integer>> chooseDate;//记录多选时全部选中的日期
    private Set<Integer> positions;//多选时记录选中日期对应的ViewPager position

    private CalendarPagerAdapter calendarPagerAdapter;

    private AttrsBean mAttrsBean;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAttrsBean = new AttrsBean();
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        Calendar c = Calendar.getInstance();
        startDate = new int[]{c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1};
        mAttrsBean.setStartDate(startDate);
        c.add(Calendar.MONTH, 2);
        endDate = new int[]{c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1};
        mAttrsBean.setEndDate(endDate);
    }

    public void init() {
        //根据设定的日期范围计算日历的页数
        count = (endDate[0] - startDate[0]) * 12 + endDate[1] - startDate[1] + 1;
        calendarPagerAdapter = new CalendarPagerAdapter(count);
        calendarPagerAdapter.setAttrsBean(mAttrsBean);
        calendarPagerAdapter.setOnCalendarViewAdapter(item_layout, calendarViewAdapter);
        setAdapter(calendarPagerAdapter);

        currentPosition = CalendarUtil.dateToPosition(initDate[0], initDate[1], startDate[0], startDate[1]);

        //单选
        int[] singleDate = mAttrsBean.getSingleDate();
        if (singleDate != null) {
            lastClickDate[0] = CalendarUtil.dateToPosition(singleDate[0], singleDate[1], startDate[0], startDate[1]);
            lastClickDate[1] = singleDate[2];
        }

        setCurrentItem(currentPosition, false);

        addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                refreshMonthView(position);
                currentPosition = position;
                if (pagerChangeListener != null) {
                    int[] date = CalendarUtil.positionToDate(position, startDate[0], startDate[1]);
                    pagerChangeListener.onPagerChanged(new int[]{date[0], date[1], lastClickDate[1]});
                }
            }
        });
    }

    /**
     * 计算 ViewPager 高度
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int calendarHeight;
        if (getAdapter() != null) {
            MonthView view = (MonthView) getChildAt(0);
            if (view != null) {
                calendarHeight = view.getMeasuredHeight();
                setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(calendarHeight, MeasureSpec.EXACTLY));
            }
        }
    }

    /**
     * 刷新MonthView
     *
     * @param position
     */
    private void refreshMonthView(int position) {
        MonthView monthView = calendarPagerAdapter.getViews().get(position);

        //单选时，如果设置切换月份不选中上次选中的日期但如果切换回有选中日期的页则需要刷新选中，或者切换选中开启则需要刷新选中
        boolean flag = (!mAttrsBean.isSwitchChoose() && lastClickDate[0] == position)
                || mAttrsBean.isSwitchChoose();
        monthView.refresh(lastClickDate[1], flag);
    }

    /**
     * 设置单选时选中的日期
     *
     * @param day
     */
    public void setLastClickDay(int day) {
        lastClickDate[0] = currentPosition;
        lastClickDate[1] = day;
    }
    /**
     * 设置日期单选回调
     *
     * @param singleChooseListener
     */
    public void setOnSingleChooseListener(OnSingleChooseListener singleChooseListener) {
        this.singleChooseListener = singleChooseListener;
    }


    public OnSingleChooseListener getSingleChooseListener() {
        return singleChooseListener;
    }

    /**
     * 设置月份切换回调
     *
     * @param pagerChangeListener
     */
    public void setOnPagerChangeListener(OnPagerChangeListener pagerChangeListener) {
        this.pagerChangeListener = pagerChangeListener;
    }


    /**
     * 单选时跳转到今天
     */
    public void today() {
        int destPosition = CalendarUtil.dateToPosition(CalendarUtil.getCurrentDate()[0], CalendarUtil.getCurrentDate()[1], startDate[0], startDate[1]);
        lastClickDate[0] = destPosition;
        lastClickDate[1] = CalendarUtil.getCurrentDate()[2];
        if (destPosition == currentPosition) {
            refreshMonthView(destPosition);
        } else {
            setCurrentItem(destPosition, false);
        }
    }

    /**
     * 跳转到下个月
     */
    public void nextMonth() {
        if (currentPosition < count - 1)
            setCurrentItem(++currentPosition, false);
    }

    /**
     * 跳转到上个月
     */
    public void lastMonth() {
        if (currentPosition > 0)
            setCurrentItem(--currentPosition, false);
    }

    /**
     * 设置日历初始显示的年月
     *
     * @param date
     * @return
     */
    public CalendarView setInitDate(String date) {
        initDate = CalendarUtil.strToArray(date);
        return this;
    }
}
