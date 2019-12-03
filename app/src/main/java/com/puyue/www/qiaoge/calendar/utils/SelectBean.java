package com.puyue.www.qiaoge.calendar.utils;

import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/10.
 */

public class SelectBean {
    public static long startDay = 0;
    public static long endDay = 0;
    public static long dayMs = 86400000;

    public static long changeTime(String date) {
        //输入日期，转化为毫秒数，用DATE方法()
        /**
         * 先用SimpleDateFormat.parse() 方法将日期字符串转化为Date格式
         * 通过Date.getTime()方法，将其转化为毫秒数
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//24小时制
        long time = 0;
        try {
            time = simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static int getWeek(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    public ObjBean obj;

    public static class ObjBean {
        /**
         * reInvestFlag : true
         * repaymentNum : 1
         * reInvestNum : 0
         * addRatePercent : 0.5%
         * addRate : 0.5
         * repaymentAmount : 500.27
         * activityUrl : http://www.4399.com
         * show : true
         */

        public View addView;
        public long addTime;
        public int dateType;
    }

    public static void cleanDate() {
        startDay = 0;
        endDay = 0;
    }

    public static int getDayPoor() {
        if (startDay == 0) {
            //说明未选择时间或者时间充值
            return 0;
        } else if (startDay != 0 && endDay == 0) {
            //说明只选了开始时间
            return 1;
        } else {
            if (startDay == endDay) {
                //开始时间和结束时间为同一时间
                return 1;
            } else {
                return (int) ((endDay - startDay) / dayMs) + 1;
            }
        }
    }
}
