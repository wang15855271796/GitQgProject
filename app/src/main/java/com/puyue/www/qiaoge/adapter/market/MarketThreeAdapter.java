package com.puyue.www.qiaoge.adapter.market;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PreferenceHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.market.MarketClassifyModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/4
 */
public class MarketThreeAdapter extends RecyclerView.Adapter<MarketThreeAdapter.MarketViewHolder> {
    private Context context;
    private List<MarketClassifyModel.DataBean> mListData;
    private int selectPosition;
    private OnItemClickListener onItemClickListener;

    public MarketThreeAdapter(Context context, List<MarketClassifyModel.DataBean> mListData) {
        this.context = context;
        this.mListData = mListData;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MarketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_market_three, parent, false);
        return new MarketViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MarketViewHolder holder, final int position) {
        holder.mTvFirst.setText(mListData.get(position).firstClassifyName);
        if (selectPosition == position) {
            holder.mTvFirst.setTextColor(Color.parseColor("#F56D23"));
        } else {
            holder.mTvFirst.setTextColor(Color.parseColor("#737373"));
        }
        if (onItemClickListener != null) {
            holder.mTvFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PreferenceHelper.saveData(context,"user_info","position",position+"");
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class MarketViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvFirst;

        public MarketViewHolder(View itemView) {
            super(itemView);
            mTvFirst = ((TextView) itemView.findViewById(R.id.tv_item_market_first_name));

        }
    }

    public void selectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }
}
