package com.puyue.www.qiaoge.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.calendar.utils.CalendarUtil;
import com.puyue.www.qiaoge.helper.FVHelper;

/**
 * Created by win7 on 2018/7/26.
 */

public class WeekView extends LinearLayout {
    private TextView mTvSunday;
    private ImageView mIvSunday;

    private TextView mTvMonday;
    private ImageView mIvMonday;

    private TextView mTvTuesday;
    private ImageView mIvTuesday;

    private TextView mTvWednesday;
    private ImageView mIvWednesday;

    private TextView mTvThursday;
    private ImageView mIvThursday;

    private TextView mTvFriday;
    private ImageView mIvFriday;

    private TextView mTvSaturday;
    private ImageView mIvSaturday;

    private Context context;
    private int[] cDate;


    public WeekView(Context context) {
        super(context);
    }

    public WeekView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_week, this);
        mTvSunday = FVHelper.fv(view, R.id.tv_view_sunday);
        mIvSunday = FVHelper.fv(view, R.id.iv_view_sunday);

        mTvMonday = FVHelper.fv(view, R.id.tv_view_monday);
        mIvMonday = FVHelper.fv(view, R.id.iv_view_monday);

        mTvTuesday = FVHelper.fv(view, R.id.tv_view_tuesday);
        mIvTuesday = FVHelper.fv(view, R.id.iv_view_tuesday);

        mTvWednesday = FVHelper.fv(view, R.id.tv_view_wednesday);
        mIvWednesday = FVHelper.fv(view, R.id.iv_view_wednesday);

        mTvThursday = FVHelper.fv(view, R.id.tv_view_thursday);
        mIvThursday = FVHelper.fv(view, R.id.iv_view_thursday);

        mTvFriday = FVHelper.fv(view, R.id.tv_view_friday);
        mIvFriday = FVHelper.fv(view, R.id.iv_view_friday);

        mTvSaturday = FVHelper.fv(view, R.id.tv_view_saturday);
        mIvSaturday = FVHelper.fv(view, R.id.iv_view_satusday);

        cDate = CalendarUtil.getCurrentDate();
        setWeekColor(cDate[3]);
    }

    /**
     * 添加数据
     */
    /*
    * 设定星期
    * **/
    private void setWeekColor(int week) {
        switch (week) {
            case 1: {
                //星期日
                mTvSunday.setTextColor(ContextCompat.getColor(context, R.color.wallet_unchecked));
                mIvSunday.setVisibility(View.VISIBLE);
            }
            break;
            case 2: {
                mTvMonday.setTextColor(ContextCompat.getColor(context, R.color.wallet_unchecked));
                mIvMonday.setVisibility(View.VISIBLE);
            }
            break;
            case 3: {
                mTvTuesday.setTextColor(ContextCompat.getColor(context, R.color.wallet_unchecked));
                mIvTuesday.setVisibility(View.VISIBLE);
            }
            break;
            case 4: {
                mTvWednesday.setTextColor(ContextCompat.getColor(context, R.color.wallet_unchecked));
                mIvWednesday.setVisibility(View.VISIBLE);
            }
            break;
            case 5: {
                mTvThursday.setTextColor(ContextCompat.getColor(context, R.color.wallet_unchecked));
                mIvThursday.setVisibility(View.VISIBLE);
            }
            break;
            case 6: {
                mTvFriday.setTextColor(ContextCompat.getColor(context, R.color.wallet_unchecked));
                mIvFriday.setVisibility(View.VISIBLE);
            }
            break;
            case 7: {
                //星期六
                mTvSaturday.setTextColor(ContextCompat.getColor(context, R.color.wallet_unchecked));
                mIvSaturday.setVisibility(View.VISIBLE);
            }
            break;
            default:
                break;

        }

    }

}
