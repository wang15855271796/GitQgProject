package com.puyue.www.qiaoge.adapter.market;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.ClassIfyModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */
//
public class MarketSecondAdapter extends BaseQuickAdapter<ClassIfyModel.DataBean,BaseViewHolder> {
    private int selectPosition;
    OnEventClickListener mOnEventClickListener;
    com.puyue.www.qiaoge.listener.OnItemClickListener onItemClickListener;
    private ImageView iv_icon;
    boolean open = false;
    boolean opens = false;
    int pos = 0;
    public MarketSecondAdapter(int layoutResId, @Nullable List<ClassIfyModel.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ClassIfyModel.DataBean item) {
        iv_icon = helper.getView(R.id.iv_icon);
        if(helper.getAdapterPosition()==1) {
            iv_icon.setImageResource(R.mipmap.icon_hot);
            iv_icon.setVisibility(View.VISIBLE);
        }else if(helper.getAdapterPosition()==2){
            iv_icon.setImageResource(R.mipmap.icon_tip);
            iv_icon.setVisibility(View.VISIBLE);
        }else if(helper.getAdapterPosition()==3){
            iv_icon.setImageResource(R.mipmap.icon_new);
            iv_icon.setVisibility(View.VISIBLE);
        }else if(helper.getAdapterPosition()==4){
            iv_icon.setImageResource(R.mipmap.icon_coupon);
            iv_icon.setVisibility(View.VISIBLE);
        }else {
            iv_icon.setVisibility(View.GONE);
        }

        TextView tv_name = helper.getView(R.id.tv_name);
        LinearLayout rl_bg = helper.getView(R.id.rl_bg);
        tv_name.setText(item.getName());
        RelativeLayout rl = helper.getView(R.id.rl);
        RecyclerView recyclerView = helper.getView(R.id.recyclerViews);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        InnerAdapter innerAdapter = new InnerAdapter(R.layout.item_inner,item.getSecondClassify());


        if(selectPosition==helper.getLayoutPosition()) {
            tv_name.setTextColor(Color.parseColor("#333333"));
            tv_name.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv_name.setTextSize(14);
            if(pos != helper.getLayoutPosition()) {
                pos = helper.getLayoutPosition();

                if(item.getSecondClassify()==null) {
                    recyclerView.setVisibility(View.GONE);
                }else {
                    if(opens) {
                        if(recyclerView.getVisibility()==View.VISIBLE) {
                            recyclerView.setVisibility(View.GONE);

                        }else {
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                        opens = false;
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                        open = true;

                    }
                }
            }else {
                pos = helper.getLayoutPosition();
                if(item.getSecondClassify()==null) {
                    recyclerView.setVisibility(View.GONE);
                }else {
                    if(open) {
                        recyclerView.setVisibility(View.GONE);
                        open = false;
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                        open = true;
                    }
                }
            }


            rl_bg.setBackgroundResource(R.drawable.hh);
            rl.setVisibility(View.GONE);
            rl_bg.setPadding(0,0,0,0);

        }else {
            recyclerView.setVisibility(View.GONE);
            rl.setVisibility(View.VISIBLE);
            rl_bg.setBackgroundColor(Color.parseColor("#ffffff"));
            tv_name.setTextColor(Color.parseColor("#676767"));
            tv_name.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_name.setTextSize(12);
            rl.setBackgroundColor(Color.parseColor("#F8F8F8"));

        }

        recyclerView.setAdapter(innerAdapter);
        innerAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_bg:
                        innerAdapter.selectPosition(position);
                        if(mOnEventClickListener!=null) {
                            mOnEventClickListener.onEventClick(position,item.getSecondClassify().get(position).getSecondId());
                        }
                        break;
                }

            }
        });

        if (onItemClickListener != null) {
            rl_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,helper.getAdapterPosition());

                }
            });
        }
    }


    public interface OnEventClickListener {
        void onEventClick(int position, int secondId);

    }

    public void setOnItemClickListeners(OnEventClickListener onEventClickListener) {
        mOnEventClickListener = onEventClickListener;
    }


    public void setOnItemClickListener(com.puyue.www.qiaoge.listener.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    public void selectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }
}
