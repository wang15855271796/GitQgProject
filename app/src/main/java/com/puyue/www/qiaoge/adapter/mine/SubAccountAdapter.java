package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.SubAccountModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class SubAccountAdapter extends RecyclerView.Adapter<SubAccountAdapter.SubAccountViewHolder> {
    private Context context;
    private List<SubAccountModel.DataBean> mListData;
    private OnEventClickListener mOnEventClickListener;

    public SubAccountAdapter(Context context, List<SubAccountModel.DataBean> mListData) {
        this.context = context;
        this.mListData = mListData;
    }

    public interface OnEventClickListener {
        void onEventClick(View view, int position, String flag);

        void onEventLongClick(View view, int position, String flag);
    }

    public void setOnItemClickListener(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }

    @Override
    public SubAccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sub_account, parent, false);
        return new SubAccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubAccountViewHolder holder, final int position) {
        holder.mTvName.setText(mListData.get(position).loginUserName);
        holder.mTvPhone.setText(mListData.get(position).loginPhone);
        if (mListData.get(position).enabled == 0) {
            //这个账号禁用中,可以恢复
            holder.mTvDisable.setVisibility(View.GONE);
            holder.mTvEnable.setVisibility(View.VISIBLE);
        } else if (mListData.get(position).enabled == 1) {
            //这个账号可用中,可以禁用
            holder.mTvDisable.setVisibility(View.VISIBLE);
            holder.mTvEnable.setVisibility(View.GONE);
        }
        //禁用该子账号
        holder.mTvDisable.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, position, "disable");
            }
        });
        //恢复该子账号
        holder.mTvEnable.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, position, "enable");
            }
        });
        //删除该子账号
        holder.mTvDelete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, position, "delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class SubAccountViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvName;
        private TextView mTvPhone;
        private TextView mTvDisable;
        private TextView mTvDelete;
        private TextView mTvModify;
        private TextView mTvEnable;

        public SubAccountViewHolder(View itemView) {
            super(itemView);
            mTvName = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_name));
            mTvPhone = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_phone));
            mTvDisable = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_disable));//禁用
            mTvDelete = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_delete));//删除
            mTvModify = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_modify));//修改
            mTvEnable = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_enable));//恢复
        }
    }
}
