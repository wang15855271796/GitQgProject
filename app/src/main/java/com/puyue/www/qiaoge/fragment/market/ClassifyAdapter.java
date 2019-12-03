package com.puyue.www.qiaoge.fragment.market;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.ClassIfyModel;
import com.puyue.www.qiaoge.view.ExpandLayout;

import java.util.List;

class ClassifyAdapter extends BaseQuickAdapter<ClassIfyModel.DataBean,BaseViewHolder> {

    private TextView tv_name;
    int selectPosition;

    public ClassifyAdapter(int layoutResId, @Nullable List<ClassIfyModel.DataBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ClassIfyModel.DataBean item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        RelativeLayout rl_bg = helper.getView(R.id.rl_bg);
        Glide.with(mContext).load(item.getImgUrl()).into(iv_icon);
        ExpandLayout expanded = helper.getView(R.id.expanded);
        tv_name = helper.getView(R.id.tv_name);
        expanded.initExpand(true);
        RecyclerView recyclerView = helper.getView(R.id.recyclerViews);
        helper.setText(R.id.tv_name,item.getName());

        if (selectPosition == helper.getAdapterPosition()) {
            tv_name.setTextColor(Color.parseColor("#333333"));
            rl_bg.setBackgroundColor(Color.parseColor("#ffffff"));

//            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//            InnerAdapter innerAdapter = new InnerAdapter(R.layout.item_inner, item.getSecondClassify());
//            recyclerView.setAdapter(innerAdapter);
//            innerAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
//                @Override
//                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                    switch (view.getId()) {
//                        case R.id.tv_name_second:
//                            Log.d("sdadddqwcsdbbb...","oooo");
//                            break;
//
//                    }
//                }
//            });
            } else {
            tv_name.setTextColor(Color.parseColor("#666666"));
            rl_bg.setBackgroundColor(Color.parseColor("#F5F5F7"));
            }

//
//        tv_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnEventClickListener.onEventClick(helper.getAdapterPosition());
//                expanded.toggleExpand();
//            }
//        });


        helper.setIsRecyclable(false);
    }

    public interface OnEventClickListener {
        void onEventClick(int position);

    }

    public void setOnItemClickListener(OnEventClickListener onEventClickListener) {
//        mOnEventClickListener = onEventClickListener;
    }
    public void selectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }


}
