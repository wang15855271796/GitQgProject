package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/1/17
 */
public class Team1InnerAdapter extends BaseQuickAdapter<TeamActiveQueryModel.DataBean.ActivesBean,BaseViewHolder> {

    CouponsAdapter.Onclick onclick;
    private ImageView iv_pic;
    TextView tv_old_price;
    private TeamActiveQueryModel.DataBean.ActivesBean activesBean;
    private TextView tv_add;
    private RelativeLayout rl_root;
    private RelativeLayout rl_coupon;
    private int activeId;
    List<TeamActiveQueryModel.DataBean>  data;
    private TeamActiveQueryModel.DataBean dataBean;
    private List<TeamActiveQueryModel.DataBean.ActivesBean> actives;
    private int activeId1;
    private TextView tv_coupon;

    public Team1InnerAdapter(int layoutResId, @Nullable List<TeamActiveQueryModel.DataBean.ActivesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean.ActivesBean item) {
        tv_old_price = helper.getView(R.id.tv_old_price);
        tv_coupon = helper.getView(R.id.tv_coupon);
        iv_pic = helper.getView(R.id.iv_pic);
        rl_root = helper.getView(R.id.rl_root);
        tv_add = helper.getView(R.id.tv_add);
        rl_coupon = helper.getView(R.id.rl_coupon);

        Glide.with(mContext).load(item.getDefaultPic()).into(iv_pic);
        helper.setText(R.id.tv_name,item.getActiveName());
        helper.setText(R.id.tv_spec,item.getSpec());
        helper.setText(R.id.tv_price,item.getPrice());
        helper.setText(R.id.tv_old_price,item.getOldPrice());

        rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID,item.getActiveId());
                mContext.startActivity(intent);
            }
        });
        rl_coupon.setVisibility(View.INVISIBLE);

            //已售完
        tv_add.setText("  未开始  ");
        tv_add.setBackgroundResource(R.drawable.shape_detail_grey);
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_old_price.getPaint().setAntiAlias(true);//抗锯齿


    }
}
