package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;
import com.puyue.www.qiaoge.utils.DateUtils;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;
import com.rey.material.widget.ProgressView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by ${王涛} on 2019/12/25
 */
public class Coupon1Adapter extends BaseQuickAdapter<TeamActiveQueryModel.DataBean,BaseViewHolder> {

    Onclick onclick;
    private TeamActiveQueryModel.DataBean.ActivesBean activesBean;
    private long startTime;
    private long currentTime;
    private long endTime;
    private Date currents;
    private Date starts;
    private Date ends;
    private TextView tv_time;
    private SnapUpCountDownTimerView tv_cut_down;

    RecyclerView recyclerView;
    public Coupon1Adapter(int layoutResId, @Nullable List<TeamActiveQueryModel.DataBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean item) {

        recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        Coupon1InnerAdapter couponsInnerAdapter = new Coupon1InnerAdapter(R.layout.coupon_inner,item.getActives());
        recyclerView.setAdapter(couponsInnerAdapter);

        tv_cut_down = helper.getView(R.id.tv_cut_down);
        startTime = item.getStartTime();
        currentTime = item.getCurrentTime();
        endTime = item.getEndTime();

        tv_time = helper.getView(R.id.tv_time);
        String current = DateUtils.formatDate(currentTime, "MM月dd日HH时mm分ss秒");
        String start = DateUtils.formatDate(startTime, "MM月dd日HH时mm分ss秒");
        String end = DateUtils.formatDate(endTime, "MM月dd日HH时mm分ss秒");
        try {
            currents = Utils.stringToDate(current, "MM月dd日HH时mm分ss秒");
            starts = Utils.stringToDate(start, "MM月dd日HH时mm分ss秒");
            ends = Utils.stringToDate(end, "MM月dd日HH时mm分ss秒");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        boolean exceed24 = DateUtils.isExceed24(currents, starts);
        if(exceed24) {
            //大于24
            tv_time.setText(item.getTitle());

        }else {
            //小于24
            if(startTime!=0) {
                tv_cut_down.setTime(true,currentTime,startTime,endTime);
                tv_cut_down.changeBackGroundss(ContextCompat.getColor(mContext, R.color.bgColor_alertview_alert));
                tv_cut_down.changeTypeColor(R.color.color_333);
                tv_time.setVisibility(View.INVISIBLE);
                tv_cut_down.start();
                tv_cut_down.setVisibility(View.VISIBLE);

            }else {
                tv_time.setVisibility(View.INVISIBLE);
                tv_cut_down.setVisibility(View.INVISIBLE);
            }
        }

    }

    public interface Onclick {
        void addDialog();
    }

}
