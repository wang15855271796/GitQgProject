package com.puyue.www.qiaoge.adapter.home;

import android.content.Context;
import android.graphics.Color;
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

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/12
 */
public class SpikeActiveNewAdapter extends RecyclerView.Adapter<SpikeActiveNewAdapter.MarketViewHolder> {
    private int selectPosition;      //记录当前选中的条目索引
    private OnItemClickListener onItemClickListener;


    private Context context;
    private List<SpikeNewQueryModel.DataBean> data;

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
    public void onBindViewHolder(SpikeActiveNewAdapter.MarketViewHolder holder, final int position) {

        holder.mTvTime.setText(data.get(position).getDateDesc());
        holder.mTvTitle.setText(data.get(position).getTimeDesc());
        if (selectPosition == position) {
            holder.linearLayoutNewSpike.setBackgroundColor(Color.parseColor("#F56D23"));
            holder.mTvTitle.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mTvTime.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mTvSanjiao.setBackgroundResource(R.drawable.bg_daosanjiao);
        } else {
            holder.linearLayoutNewSpike.setBackgroundColor(Color.parseColor("#333333"));
            holder.mTvTitle.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mTvTime.setTextColor(Color.parseColor("#FFFFFF"));
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

        public TextView mTvTime;
        public TextView mTvTitle;
        public LinearLayout linearLayoutNewSpike;
        public TextView mTvSanjiao;

        public MarketViewHolder(View itemView) {
            super(itemView);
            mTvTime = ((TextView) itemView.findViewById(R.id.tv_spike_time));
            mTvTitle = ((TextView) itemView.findViewById(R.id.tv_spike_title));
            linearLayoutNewSpike = ((LinearLayout) itemView.findViewById(R.id.linearLayout_spike_new));

            mTvSanjiao = itemView.findViewById(R.id.tv_dao_san_jiao);
        }
    }

    public void selectPosition(int position) {
        this.selectPosition = position;

        notifyDataSetChanged();
    }
}
