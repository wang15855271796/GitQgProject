package com.puyue.www.qiaoge.calendar.weiget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.calendar.bean.AttrsBean;
import com.puyue.www.qiaoge.calendar.bean.DateBean;
import com.puyue.www.qiaoge.calendar.listener.CalendarViewAdapter;
import com.puyue.www.qiaoge.calendar.listener.OnSingleChooseListener;
import com.puyue.www.qiaoge.calendar.utils.CalendarUtil;
import com.puyue.www.qiaoge.calendar.utils.SelectBean;
import com.puyue.www.qiaoge.helper.AppHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MonthView extends ViewGroup {

    private static final int ROW = 6;
    private static final int COLUMN = 7;

    private Context mContext;

    private List<SelectBean.ObjBean> mListView = new ArrayList<>();//记录
    private int currentMonthDays;//记录当月天数
    private int lastMonthDays;//记录当月显示的上个月天数
    private int nextMonthDays;//记录当月显示的下个月天数
    private TextView mTvDesc;
    private TextView mTvDay;

    private int item_layout;
    private CalendarViewAdapter calendarViewAdapter;
    private Set<Integer> chooseDays = new HashSet<>();//记录多选时当前页选中的日期
    private AttrsBean mAttrsBean;
    private int[] cDate = CalendarUtil.getCurrentDate();

    public MonthView(Context context) {
        this(context, null);
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

        mContext = context;
        setBackgroundColor(Color.WHITE);
    }

    /**
     * @param dates            需要展示的日期数据
     * @param currentMonthDays 当月天数
     */
    public void setDateList(List<DateBean> dates, int currentMonthDays) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        lastMonthDays = 0;
        nextMonthDays = 0;
        boolean findSingleDate = false;//是否找到单选时默认选中的日期
        chooseDays.clear();

        this.currentMonthDays = currentMonthDays;
        for (int i = 0; i < dates.size(); i++) {
            final DateBean date = dates.get(i);

            if (date.getType() == 0) {
                lastMonthDays++;
                if (!mAttrsBean.isShowLastNext()) {
                    addView(new View(mContext), i);
                    continue;
                }
            }

            if (date.getType() == 2) {
                nextMonthDays++;
                if (!mAttrsBean.isShowLastNext()) {
                    addView(new View(mContext), i);
                    continue;
                }
            }

            View view;
            TextView solarDay;//阳历TextView
            TextView lunarDay;//阴历TextView(节假日、节气同样使用阴历TextView来显示)
            if (item_layout != 0 && calendarViewAdapter != null) {
                view = LayoutInflater.from(mContext).inflate(item_layout, null);
                TextView[] views = calendarViewAdapter.convertView(view, date);
                solarDay = views[0];
            } else {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_month_layout, null);
                solarDay = (TextView) view.findViewById(R.id.solar_day);
            }
            //设置当天的颜色其他颜色
            solarDay.setTextColor(mAttrsBean.getColorSolar());
            solarDay.setTextSize(mAttrsBean.getSizeSolar());
            if (cDate[0] == date.getSolar()[0] && cDate[1] == date.getSolar()[1] && cDate[2] == date.getSolar()[2]) {
                solarDay.setTag("today");
                solarDay.setTextColor(mAttrsBean.colorToday);
            }
            //设置上个月和下个月的阳历颜色
            if (date.getType() == 0 || date.getType() == 2) {
                solarDay.setTextColor(mAttrsBean.colorGray);
            }
            solarDay.setText(String.valueOf(date.getSolar()[2]));

            //当月的日历要记录
            SelectBean.ObjBean objBean = new SelectBean.ObjBean();
            objBean.addView = view;
            objBean.addTime = SelectBean.changeTime(date.getSolar()[0] + "-" + date.getSolar()[1] + "-" + date.getSolar()[2]);
            objBean.dateType = date.getType();
            mListView.add(objBean);


            //找到单选时默认选中的日期，并选中（如果有）
            if (mAttrsBean.getSingleDate() != null
                    && !findSingleDate
                    && date.getType() == 1
                    && mAttrsBean.getSingleDate()[0] == date.getSolar()[0]
                    && mAttrsBean.getSingleDate()[1] == date.getSolar()[1]
                    && mAttrsBean.getSingleDate()[2] == date.getSolar()[2]) {
                changeBg();
//                setDayColor(view, COLOR_SET);
                findSingleDate = true;
            }

            //设置禁用日期
            if (date.getType() == 1) {
                view.setTag(date.getSolar()[2]);
                if (mAttrsBean.getDisableStartDate() != null
                        && (CalendarUtil.dateToMillis(mAttrsBean.getDisableStartDate()) > CalendarUtil.dateToMillis(date.getSolar()))) {
                    view.setTag(-1);
                    addView(view, i);
                    continue;
                }

                if (mAttrsBean.getDisableEndDate() != null
                        && (CalendarUtil.dateToMillis(mAttrsBean.getDisableEndDate()) < CalendarUtil.dateToMillis(date.getSolar()))) {
                    view.setTag(-1);
                    addView(view, i);
                    continue;
                }
            }

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int day = date.getSolar()[2];
                    CalendarView calendarView = (CalendarView) getParent();
                    OnSingleChooseListener clickListener = calendarView.getSingleChooseListener();
                    if (date.getType() == 1) {//点击当月
                        long selectDay = SelectBean.changeTime(date.getSolar()[0] + "-" + date.getSolar()[1] + "-" + date.getSolar()[2]);
                        if (SelectBean.startDay == 0) {
                            //第一次选择
                            SelectBean.startDay = selectDay;
                        } else {
                            //第二次选择
                            if (selectDay < SelectBean.startDay) {
                                //选中时间小于开始时间,提示错误
                              AppHelper.showMsg(mContext, "选择错误");
                            } else {
                                SelectBean.endDay = selectDay;
                            }
                        }
                        calendarView.setLastClickDay(day);
                        changeBg();
                        if (clickListener != null) {
                            clickListener.onSingleChoose(v, date);
                        }
                    } else if (date.getType() == 0) {//点击上月
                        if (mAttrsBean.isSwitchChoose()) {
                            calendarView.setLastClickDay(day);
                        }
                        calendarView.lastMonth();
                        if (clickListener != null) {
                            clickListener.onSingleChoose(v, date);
                        }
                    } else if (date.getType() == 2) {//点击下月
                        if (mAttrsBean.isSwitchChoose()) {
                            calendarView.setLastClickDay(day);
                        }
                        calendarView.nextMonth();
                        if (clickListener != null) {
                            clickListener.onSingleChoose(v, date);
                        }
                    }
                }
            });
            addView(view, i);
        }
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int itemWidth = widthSpecSize / COLUMN;

        //计算日历的最大高度
        if (heightSpecSize > itemWidth * ROW) {
            heightSpecSize = itemWidth * ROW + 25;
        }

        setMeasuredDimension(widthSpecSize, heightSpecSize);

        int itemHeight = heightSpecSize / ROW;

        int itemSize = Math.min(itemWidth, itemHeight);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.measure(MeasureSpec.makeMeasureSpec(itemSize, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(itemSize, MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() == 0) {
            return;
        }

        View childView = getChildAt(0);
        int itemWidth = getMeasuredWidth() / COLUMN;
        int itemHeight = getMeasuredWidth() / COLUMN;
        //计算列间距
        int dx = (getMeasuredWidth() - itemWidth * COLUMN) / (COLUMN * 2);

        //当显示五行时扩大行间距
        int dy = 5;
        if (getChildCount() == 35) {
            dy = itemHeight / 5;
        }

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);

            int left = i % COLUMN * itemWidth + ((2 * (i % COLUMN) + 1)) * 0;
            int top = i / COLUMN * (itemHeight + dy);
            int right = left + itemWidth;
            int bottom = top + itemHeight;
            view.layout(left, top, right, bottom);
        }
    }

    public void refresh(int day, boolean flag) {
        changeBg();
        if (!flag) {
            return;
        }
        View destView = findDestView(day);
        if (destView == null) {
            return;
        }
        invalidate();
    }

    private void changeBg() {
        //0:上月，1:当月，2:下月
        if (mListView != null && SelectBean.startDay != 0 && SelectBean.endDay != 0 && SelectBean.startDay != SelectBean.endDay) {
            //开始时间和结束时间都不为0,并且两个时间不一样
            for (int i = 0; i < mListView.size(); i++) {
                mTvDay = mListView.get(i).addView.findViewById(R.id.solar_day);
                mTvDesc = mListView.get(i).addView.findViewById(R.id.day_desc);
                if (mListView.get(i).addTime < SelectBean.startDay || mListView.get(i).addTime > SelectBean.endDay) {
                    //区间之外
                    mListView.get(i).addView.setBackgroundColor(Color.WHITE);
                    mListView.get(i).addView.findViewById(R.id.day_desc).setVisibility(GONE);
                    setSolarDayColor(mTvDay, mListView.get(i).dateType);
                } else if (mListView.get(i).addTime == SelectBean.startDay) {
                    //开始时间
                    mTvDay.setTextColor(Color.WHITE);
                    mTvDesc.setVisibility(VISIBLE);
                    mTvDesc.setText("起");
                    if (SelectBean.getWeek(mListView.get(i).addTime) == 7) {
                        //开始时间是周六
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.selectedBg);
                    } else if (SelectBean.getWeek(mListView.get(i).addTime) == 1) {
                        //开始时间是周日
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.startBg);
                    } else {
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.startBg);
                    }
                } else if (mListView.get(i).addTime == SelectBean.endDay) {
                    //结束时间
                    mTvDesc = mListView.get(i).addView.findViewById(R.id.day_desc);
                    mTvDay.setTextColor(Color.WHITE);
                    mTvDesc.setVisibility(VISIBLE);
                    mTvDesc.setText("止");
                    if (SelectBean.getWeek(mListView.get(i).addTime) == 7) {
                        //结束时间是周六
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.endBg);
                    } else if (SelectBean.getWeek(mListView.get(i).addTime) == 1) {
                        //结束时间是周日
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.selectedBg);
                    } else {
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.endBg);
                    }
                } else {
                    if (SelectBean.getWeek(mListView.get(i).addTime) == 1) {
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.leftBg);
                    } else if (SelectBean.getWeek(mListView.get(i).addTime) == 7) {
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.rightBg);
                    } else {
                        mListView.get(i).addView.setBackgroundResource(mAttrsBean.commonBg);
                    }
                    mListView.get(i).addView.findViewById(R.id.day_desc).setVisibility(GONE);
                    setSolarDayColor(mTvDay, mListView.get(i).dateType);
                }
            }
        } else if (mListView != null && SelectBean.startDay != 0 && SelectBean.endDay != 0 && SelectBean.startDay == SelectBean.endDay) {
            //开始时间和结束时间一致
            for (int i = 0; i < mListView.size(); i++) {
                mTvDay = mListView.get(i).addView.findViewById(R.id.solar_day);
                mTvDesc = mListView.get(i).addView.findViewById(R.id.day_desc);
                if (mListView.get(i).addTime < SelectBean.startDay || mListView.get(i).addTime > SelectBean.endDay) {
                    //区间之外
                    mListView.get(i).addView.setBackgroundColor(Color.WHITE);
                    setSolarDayColor(mTvDay, mListView.get(i).dateType);
                    mListView.get(i).addView.findViewById(R.id.day_desc).setVisibility(GONE);
                } else {
                    mListView.get(i).addView.setBackgroundResource(mAttrsBean.selectedBg);
                    mTvDay.setTextColor(Color.WHITE);
                    mTvDesc.setVisibility(VISIBLE);
                    mTvDesc.setText("起/止");
                }
            }
        } else if (mListView != null && SelectBean.startDay != 0 && SelectBean.endDay == 0) {
            //第一次选择
            for (int i = 0; i < mListView.size(); i++) {
                mTvDay = mListView.get(i).addView.findViewById(R.id.solar_day);
                mTvDesc = mListView.get(i).addView.findViewById(R.id.day_desc);
                if (mListView.get(i).addTime == SelectBean.startDay) {
                    //选中时间
                    mListView.get(i).addView.setBackgroundResource(mAttrsBean.selectedBg);
                    mTvDay.setTextColor(Color.WHITE);
                    mTvDesc.setVisibility(VISIBLE);
                    mTvDesc.setText("起");
                } else {
                    mListView.get(i).addView.setBackgroundColor(Color.WHITE);
                    mTvDesc.setVisibility(GONE);
                    setSolarDayColor(mTvDay, mListView.get(i).dateType);
                }
            }
        } else if (mListView != null && SelectBean.startDay == 0 && SelectBean.endDay == 0) {
            //还没开始选择或者清空记录
            for (int i = 0; i < mListView.size(); i++) {
                mTvDay = mListView.get(i).addView.findViewById(R.id.solar_day);
                mListView.get(i).addView.setBackgroundColor(Color.WHITE);
                mListView.get(i).addView.findViewById(R.id.day_desc).setVisibility(GONE);
                setSolarDayColor(mTvDay, mListView.get(i).dateType);
            }
        }
    }

    /**
     * 查找要跳转到的页面需要展示的日期View
     *
     * @param day
     * @return
     */
    private View findDestView(int day) {
        View view = null;
        for (int i = lastMonthDays; i < getChildCount() - nextMonthDays; i++) {
            if ((Integer) getChildAt(i).getTag() == day) {
                view = getChildAt(i);
                break;
            }
        }

        if (view == null) {
            view = getChildAt(currentMonthDays + lastMonthDays - 1);
        }

        if ((Integer) view.getTag() == -1) {
            view = null;
        }
        return view;
    }

    public void setAttrsBean(AttrsBean attrsBean) {
        mAttrsBean = attrsBean;
    }

    public void setOnCalendarViewAdapter(int item_layout, CalendarViewAdapter calendarViewAdapter) {
        this.item_layout = item_layout;
        this.calendarViewAdapter = calendarViewAdapter;
    }

    /*
    * 设置颜色
    *  //0:上月，1:当月，2:下月
    * **/
    private void setSolarDayColor(TextView mTvDay, int type) {
        if (type == 0 || type == 2) {
            mTvDay.setTextColor(mAttrsBean.colorGray);
        } else {
            if ("today".equals(mTvDay.getTag())) {
                mTvDay.setTextColor(mAttrsBean.colorToday);
            } else {
                mTvDay.setTextColor(mAttrsBean.getColorSolar());
            }
        }
    }
}
