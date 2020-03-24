package com.puyue.www.qiaoge.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.AppHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ${王涛} on 2019/12/30
 */
@SuppressLint("HandlerLeak")
public class Snap extends LinearLayout {

    private boolean isLight = false;

    private TextView tv_state;
    private TextView tv_hour_decade;
    private TextView tv_hour_unit;
    private TextView tv_min_decade;
    private TextView tv_min_unit;
    private TextView tv_sec_decade;
    private TextView tv_sec_unit;

    private LinearLayout ll_hour_bg;
    private LinearLayout ll_min_bg;
    private LinearLayout ll_sec_bg;
    private TextView tv_hour;
    private TextView tv_min;

    private Context context;

    private int hour_decade;
    private int hour_unit;
    private int min_decade;
    private int min_unit;
    private int sec_decade;
    private int sec_unit;

    private long nowTime;
    private long startTime;
    private long endTime;

    private Timer timer;
    private int type = 0;
    private TextView tv_statReturn;
    private TextView tvTimeTipe;

    public SnapUpCountDownTimerView.Timeout timeout;

    public SnapUpCountDownTimerView.Timeout getTimeout() {
        return timeout;
    }

    public void setTimeout(SnapUpCountDownTimerView.Timeout timeout) {
        this.timeout = timeout;
    }

    public interface Timeout {
        void getStop();
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            countDown();
        }
    };

    public void SnapType(Context context, int type) { // ==1 待付款进入
        this.type = type;
        this.context = context;

    }

    public Snap(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_countdowntimer, this);
        tv_state = view.findViewById(R.id.tv_state);//秒杀
        tv_hour_decade = (TextView) view.findViewById(R.id.tv_hour_decade);
        tv_hour_unit = (TextView) view.findViewById(R.id.tv_hour_unit);
        tv_min_decade = (TextView) view.findViewById(R.id.tv_min_decade);
        tv_min_unit = (TextView) view.findViewById(R.id.tv_min_unit);
        tv_sec_decade = (TextView) view.findViewById(R.id.tv_sec_decade);
        tv_sec_unit = (TextView) view.findViewById(R.id.tv_sec_unit);

        ll_hour_bg = (LinearLayout) view.findViewById(R.id.ll_hour_bg);
        ll_min_bg = (LinearLayout) view.findViewById(R.id.ll_min_bg);
        ll_sec_bg = (LinearLayout) view.findViewById(R.id.ll_sec_bg);

        tv_hour = (TextView) view.findViewById(R.id.colon_hour);
        tv_min = (TextView) view.findViewById(R.id.colon_minute);
        tv_statReturn = view.findViewById(R.id.tv_statReturn);
        tvTimeTipe = view.findViewById(R.id.tvTimeTipe);

        SnapType(context, type);
    }


    private void countDown() {

        if (isCarry4Unit(tv_sec_unit)) {
            if (isCarry4Decade(tv_sec_decade)) {
                if (isCarry4Unit(tv_min_unit)) {
                    if (isCarry4Decade(tv_min_decade)) {
                        if (isCarry4Unit(tv_hour_unit)) {
                            if (isCarry4Decade(tv_hour_decade)) {
                                timeOutEvent();
                            }
                        }
                    }
                }
            }
        }
    }

    private void timeOutEvent() {
        if (nowTime == 0 || startTime == 0 || endTime == 0) {


            if (timeout != null) {
                timeout.getStop();
            }


            stop();
            setTime(0, 0, 0);//重置为0
        } else {
            if (type == 1) {
                if (nowTime < startTime) {
                    tv_state.setText("");
                    changeTimeType(endTime - startTime);
                    start();
                } else if (nowTime > endTime) {
                    //实时大于结束时间,活动结束
                    tv_state.setText("");
                    stop();
                    setTime(0, 0, 0);
                } else {
                    tv_state.setText("");
                    stop();
                    setTime(0, 0, 0);
                }
            } else {
                if (nowTime < startTime) {
                    //活动未开始,倒计时结束,活动开始
                    changeTimeType(endTime - startTime);
                    start();
                } else if (nowTime > endTime) {
                    stop();
                    setTime(0, 0, 0);
                } else {
                    //秒杀结束
                    stop();
                    setTime(0, 0, 0);
                }
            }

        }

    }

    public void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, 0, 1000);
        }
    }


    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    public void setTime(int hour, int min, int sec) {
        if (hour > 999) {
            hour = 999;

        }
        if (hour >= 999 || min >= 60 || sec >= 60 || hour < 0 || min < 0
                || sec < 0) {
//            AppHelper.showMsg(context, "时间格式错误");
        }

        hour_decade = hour / 10;
        hour_unit = hour - hour_decade * 10;


        min_decade = min / 10;
        min_unit = min - min_decade * 10;

        sec_decade = sec / 10;
        sec_unit = sec - sec_decade * 10;

        tv_hour_decade.setText(hour_decade + "");
        tv_hour_unit.setText(hour_unit + "");
        tv_min_decade.setText(min_decade + "");
        tv_min_unit.setText(min_unit + "");
        tv_sec_decade.setText(sec_decade + "");
        tv_sec_unit.setText(sec_unit + "");

    }

    public void setTime(boolean isShow, long nowTime, long startTime, long endTime) {
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
        long timePoor = 0;
        if (isShow) {
            tv_state.setVisibility(GONE);
        }


        if (nowTime < startTime) {
            //实时小于开始时间,活动未开始
            if (type == 1) {
                tvTimeTipe.setVisibility(VISIBLE);
                tv_statReturn.setVisibility(VISIBLE);
                tv_state.setVisibility(GONE);
            } else {
                tv_state.setVisibility(GONE);
            }

            timePoor = startTime - nowTime;
            changeTimeType(timePoor);
        } else if (nowTime > endTime) {
            if (type == 1) {
                tv_state.setText("");

            } else {
            }
            setTime(0, 0, 0);

        } else {
            if (type == 1) {
                tv_state.setText("");
            } else {
            }
            timePoor = endTime - nowTime;
            changeTimeType(timePoor);
        }


    }

    private boolean isCarry4Decade(TextView tv) {

        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 5;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }
    }


    private boolean isCarry4Unit(TextView tv) {

        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 9;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }
    }

    public void setBackTheme(boolean isLight) {
        //是否为亮色
        this.isLight = isLight;
        changeBgColor();
    }

    private void changeBgColor() {
        if (isLight) {
            ll_hour_bg.setBackgroundResource(0);
            ll_sec_bg.setBackgroundResource(0);
            ll_min_bg.setBackgroundResource(0);
            tv_hour.setTextColor(Color.WHITE);
            tv_min.setTextColor(Color.WHITE);
            tv_hour_decade.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            tv_hour_unit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            tv_min_decade.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            tv_min_unit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            tv_sec_decade.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            tv_sec_unit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        }
    }

    public void changeTextColor(int color) {

        tv_hour_decade.setTextColor(color);
        tv_hour_unit.setTextColor(color);
        tv_min_decade.setTextColor(color);
        tv_min_unit.setTextColor(color);
        tv_sec_decade.setTextColor(color);
        tv_sec_unit.setTextColor(color);
        tv_hour.setTextColor(color);
        tv_min.setTextColor(color);
    }

    public void changeColon(int color) {
        tv_hour.setTextColor(color);
        tv_min.setTextColor(color);
    }

    public void changeBackGround(int color) {
        ll_hour_bg.setBackgroundColor(color);
        ll_min_bg.setBackgroundColor(color);
        ll_sec_bg.setBackgroundColor(color);

        tv_hour_decade.setTextColor(Color.parseColor("#F56D23"));
        tv_hour_unit.setTextColor(Color.parseColor("#F56D23"));
        tv_min_decade.setTextColor(Color.parseColor("#F56D23"));
        tv_min_unit.setTextColor(Color.parseColor("#F56D23"));
        tv_sec_decade.setTextColor(Color.parseColor("#F56D23"));
        tv_sec_unit.setTextColor(Color.parseColor("#F56D23"));
        tv_hour.setTextColor(Color.parseColor("#F56D23"));
        tv_min.setTextColor(Color.parseColor("#F56D23"));

    }

    public void changeBackGrounds(int color) {
        ll_hour_bg.setBackgroundColor(color);
        ll_min_bg.setBackgroundColor(color);
        ll_sec_bg.setBackgroundColor(color);

        tv_hour_decade.setTextColor(Color.parseColor("#ffffff"));
        tv_hour_unit.setTextColor(Color.parseColor("#ffffff"));
        tv_min_decade.setTextColor(Color.parseColor("#ffffff"));
        tv_min_unit.setTextColor(Color.parseColor("#ffffff"));
        tv_sec_decade.setTextColor(Color.parseColor("#ffffff"));
        tv_sec_unit.setTextColor(Color.parseColor("#ffffff"));
        tv_hour.setTextColor(Color.parseColor("#ffffff"));
        tv_min.setTextColor(Color.parseColor("#ffffff"));

    }

    public void changeBackGroundss(int color) {
        ll_hour_bg.setBackgroundColor(color);
        ll_min_bg.setBackgroundColor(color);
        ll_sec_bg.setBackgroundColor(color);

        tv_hour_decade.setTextColor(Color.parseColor("#333333"));
        tv_hour_unit.setTextColor(Color.parseColor("#333333"));
        tv_min_decade.setTextColor(Color.parseColor("#333333"));
        tv_min_unit.setTextColor(Color.parseColor("#333333"));
        tv_sec_decade.setTextColor(Color.parseColor("#333333"));
        tv_sec_unit.setTextColor(Color.parseColor("#333333"));
        tv_hour.setTextColor(Color.parseColor("#333333"));
        tv_min.setTextColor(Color.parseColor("#333333"));

    }

    public void changeTypeColor(int color) {
        tv_state.setTextColor(color);
    }

    /**
     * 将毫秒差转化为时分秒
     */
    private void changeTimeType(long timePoor) {
        if (timePoor <= 60000) {
            //时间差小于60秒
            setTime(0, 0, (int) timePoor / 1000);
        } else if (timePoor > 60000 && timePoor <= 3600000) {
            //时间差大于60秒,小于60分
            int min = (int) timePoor / 60000;
            int sec = (int) (timePoor - min * 60000) / 1000;
            setTime(0, min, sec);
        } else if (timePoor > 3600000) {
            //时间差大于60分
            int hour = (int) timePoor / 3600000;
            int min = (int) ((timePoor - hour * 3600000) / 60000);
            int sec = (int) ((timePoor - hour * 3600000 - min * 60000) / 1000);
            setTime(hour, min, sec);
        }
    }


}
