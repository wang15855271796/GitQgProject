package com.puyue.www.qiaoge.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/6
 */
public class RegisterShopAdapter extends RecyclerView.Adapter<RegisterShopAdapter.MyShopAdapter> {


    private Context context;
    private List<GetRegisterShopModel.DataBean> list;
    private int selectPosition = -1;

    private OnItemClickListener onItemClickListener;

    public RegisterShopAdapter(Context context, List<GetRegisterShopModel.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyShopAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.register_shop_select, parent, false);
        return new MyShopAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyShopAdapter holder, int position) {

        holder.mTv.setText(list.get(position).getName());
        if (selectPosition == position) {
            holder.mTv.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mTv.setBackgroundResource(R.drawable.register_select_bg);

        } else {
            holder.mTv.setTextColor(Color.parseColor("#636363"));
            holder.mTv.setBackgroundResource(R.drawable.register_shop_bg);
        }
        if (onItemClickListener != null) {
            holder.mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    // private List<GetRegisterShopModel.DataBean> list;

    class MyShopAdapter extends RecyclerView.ViewHolder {
        public TextView mTv;

        public MyShopAdapter(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_register_shop_select);
        }
    }

    public void selectPosition(int position) {
        this.selectPosition = position;

        notifyDataSetChanged();
    }

}
