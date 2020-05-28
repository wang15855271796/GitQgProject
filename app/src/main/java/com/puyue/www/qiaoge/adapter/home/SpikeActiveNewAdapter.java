package com.puyue.www.qiaoge.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.home.SpikeNewQueryModel;
import com.puyue.www.qiaoge.utils.DateUtils;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.Snap;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by ${王文博} on 2019/4/12
 */
public class SpikeActiveNewAdapter extends RecyclerView.Adapter<SpikeActiveNewAdapter.MarketViewHolder> {
    private int selectPosition;      //记录当前选中的条目索引
    private OnItemClickListener onItemClickListener;


    private Context context;
    private List<SpikeNewQueryModel.DataBean> data;
    private int flag;
    private long startTime;
    private long endTime;
    private long l;
    private Date currents;
    private Date starts;
    private Date ends;
    private boolean exceed2;

    public SpikeActiveNewAdapter(Context context, List<SpikeNewQueryModel.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MarketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.spike_new_active, parent, false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarketViewHolder holder, final int position) {
        flag = data.get(position).getFlag();
        startTime = data.get(position).getStartTime();
        endTime = data.get(position).getEndTime();
        l = System.currentTimeMillis();

        String current = DateUtils.formatDate(l, "MM月dd日HH时mm分ss秒");
        String start = DateUtils.formatDate(startTime, "MM月dd日HH时mm分ss秒");
        String end = DateUtils.formatDate(endTime, "MM月dd日HH时mm分ss秒");
        try {
            currents = Utils.stringToDate(current, "MM月dd日HH时mm分ss秒");
            starts = Utils.stringToDate(start, "MM月dd日HH时mm分ss秒");
            ends = Utils.stringToDate(end, "MM月dd日HH时mm分ss秒");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(flag==0) {
            //未开始
            holder.snap.setVisibility(View.GONE);
            holder.tv.setVisibility(View.GONE);
            holder.tv.setText("距离开始");

            exceed2 = DateUtils.isExceed2(currents, starts);
            if(exceed2) {
                holder.tv_time.setVisibility(View.VISIBLE);
                holder.tv_time.setText(data.get(position).getTimeDesc());
                holder.tv_today.setVisibility(View.VISIBLE);
                holder.tv_today.setText(data.get(position).getDateDesc());
                holder.snap.setVisibility(View.GONE);
                holder.tv.setVisibility(View.GONE);

            }else {
                if(startTime !=0&& endTime !=0) {
                    holder.snap.setVisibility(View.VISIBLE);
                    holder.tv.setText("即将开始");
                    holder.tv.setVisibility(View.VISIBLE);
                    holder.snap.setTime(true, l, this.startTime, this.endTime);
                    holder.snap.changeBackGround(ContextCompat.getColor(context, R.color.white));
                    holder.snap.changeTypeColor(ContextCompat.getColor(context, R.color.color_F6551A));
                    holder.tv_time.setVisibility(View.GONE);
                    holder.snap.start();
                }else {
                    holder.tv.setVisibility(View.GONE);
                    holder.tv_time.setVisibility(View.GONE);
                    holder.snap.setVisibility(View.GONE);
                }
            }

        }else {
            //已开始
            holder.tv.setText("距离结束");
            holder.snap.setVisibility(View.VISIBLE);
            holder.tv.setVisibility(View.VISIBLE);

            holder.snap.setTime(true,l, this.startTime, this.endTime);
            holder.snap.start();
        }

        if (selectPosition == position) {
            holder.linearLayoutNewSpike.setBackgroundColor(Color.parseColor("#F56D23"));
            holder.mTvSanjiao.setBackgroundResource(R.drawable.bg_daosanjiao);
            holder.snap.changeBackGrounds(ContextCompat.getColor(context, R.color.wallet_bg));
        } else {
            holder.linearLayoutNewSpike.setBackgroundColor(Color.parseColor("#333333"));
            holder.snap.changeBackGrounds(ContextCompat.getColor(context, R.color.color333333));
            holder.mTvSanjiao.setBackgroundResource(R.drawable.bg_dao_san_jiao_two);
        }
        if (onItemClickListener != null) {
            holder.linearLayoutNewSpike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    class MarketViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_today;
        public TextView tv_time;
        public LinearLayout linearLayoutNewSpike;
        public TextView mTvSanjiao;
        Snap snap;
        TextView tv;

        public MarketViewHolder(View itemView) {
            super(itemView);
            snap = ((Snap) itemView.findViewById(R.id.snap));
            tv = ((TextView) itemView.findViewById(R.id.tv));
            tv_today = ((TextView) itemView.findViewById(R.id.tv_today));
            tv_time = ((TextView) itemView.findViewById(R.id.tv_time));
            linearLayoutNewSpike = ((LinearLayout) itemView.findViewById(R.id.linearLayout_spike_new));

            mTvSanjiao = itemView.findViewById(R.id.tv_dao_san_jiao);
        }
    }

    public void selectPosition(int position) {
        this.selectPosition = position;
    }
}
