package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.Nullable;
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

    Onclick onclick;
    private ImageView iv_pic;
    TextView tv_old_price;
    private TeamActiveQueryModel.DataBean.ActivesBean activesBean;
    private TextView tv_total;
    ProgressBar pb;
    private TextView tv_add;
    private RelativeLayout rl_root;
    private RelativeLayout rl_coupon;
    private int activeId;
    List<TeamActiveQueryModel.DataBean>  data;
    private TeamActiveQueryModel.DataBean dataBean;
    private TeamActiveQueryModel.DataBean.ActivesBean activesBean1;
    private List<TeamActiveQueryModel.DataBean.ActivesBean> actives;
    private int activeId1;


    public CouponsAdapter(int layoutResId, @Nullable List<TeamActiveQueryModel.DataBean> data,Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean item) {
        tv_old_price = helper.getView(R.id.tv_old_price);
        iv_pic = helper.getView(R.id.iv_pic);
        rl_root = helper.getView(R.id.rl_root);
        tv_add = helper.getView(R.id.tv_add);
        tv_total = helper.getView(R.id.tv_total);
        pb = helper.getView(R.id.pb);
        rl_coupon = helper.getView(R.id.rl_coupon);

        helper.setText(R.id.tv_time,item.getTitle());

        for (int i = 0; i <item.getActives().size() ; i++) {
            activesBean1 = item.getActives().get(i);
        }
        if(!activesBean1.getDiscount().equals("")) {
            helper.setText(R.id.tv_coupon,activesBean1.getDiscount());
            rl_coupon.setVisibility(View.VISIBLE);
        }else {
            rl_coupon.setVisibility(View.GONE);
        }

        rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                for (int i = 0; i <item.getActives().size() ; i++) {
                    activeId = item.getActives().get(i).getActiveId();
                }
                intent.putExtra(AppConstant.ACTIVEID,activeId);
                mContext.startActivity(intent);
            }
        });

        tv_total.setText(activesBean1.getRemainNum());
        Glide.with(mContext).load(activesBean1.getDefaultPic()).into(iv_pic);
        helper.setText(R.id.tv_name,activesBean1.getActiveName());
        helper.setText(R.id.tv_spec,activesBean1.getSpec());
        helper.setText(R.id.tv_price,activesBean1.getPrice());
        helper.setText(R.id.tv_old_price,activesBean1.getOldPrice());
        pb.setProgress(Integer.parseInt(activesBean1.getProgress()));
        tv_total.setText(activesBean1.getRemainNum());

        if(activesBean1.getSaleDone()==0) {
            //已售完
            tv_add.setText("已售罄");
            tv_add.setBackgroundResource(R.drawable.shape_detail_grey);
        }else {
            tv_add.setText("立即加购");
            tv_add.setBackgroundResource(R.drawable.shape_orange);
        }
        tv_old_price.getPaint().setAntiAlias(true);//抗锯齿
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }

    public interface Onclick {
        void addDialog();
    }

}
