package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.ModifyActivity;
import com.puyue.www.qiaoge.activity.mine.SubAccountOrderActivity;
import com.puyue.www.qiaoge.activity.mine.order.MySubOrderActivity;
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
        holder.mTvName.setText(mListData.get(position).name);
        holder.mTvPhone.setText(mListData.get(position).phone);

        //点击到我的订单
        holder.tv_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MySubOrderActivity.class);
                intent.putExtra("subId",mListData.get(position).subId+"");
                context.startActivity(intent);
            }
        });
        //删除该子账号
        holder.mTvDelete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mOnEventClickListener.onEventClick(view, position, "delete");
            }
        });

        //编辑子账号
        holder.mTvModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ModifyActivity.class);
                intent.putExtra("subId",mListData.get(position).subId+"");
                context.startActivity(intent);
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
        private TextView mTvDelete;
        private TextView mTvModify;
        TextView tv_look;

        public SubAccountViewHolder(View itemView) {
            super(itemView);
            tv_look = ((TextView) itemView.findViewById(R.id.tv_look));
            mTvName = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_name));
            mTvPhone = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_phone));
            mTvDelete = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_delete));//删除
            mTvModify = ((TextView) itemView.findViewById(R.id.tv_item_sub_account_modify));//修改

        }
    }
}
