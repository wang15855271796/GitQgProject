package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.Nullable;
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

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ${王涛} on 2019/12/25
 */
public class CouponsAdapter extends BaseQuickAdapter<TeamActiveQueryModel.DataBean,BaseViewHolder> {

    RecyclerView recyclerView;
    Onclick onclick;
    public CouponsAdapter(int layoutResId, @Nullable List<TeamActiveQueryModel.DataBean> data,Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean item) {

        recyclerView = helper.getView(R.id.recyclerView);
        helper.setText(R.id.tv_time,item.getTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        CouponsInnerAdapter couponsInnerAdapter = new CouponsInnerAdapter(R.layout.coupon_inner,item.getActives(),onclick);
        recyclerView.setAdapter(couponsInnerAdapter);


    }

    public interface Onclick {
        void addDialog();
    }

}
