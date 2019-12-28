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

    public CouponsAdapter(int layoutResId, @Nullable List<TeamActiveQueryModel.DataBean> data,Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean item) {
        tv_old_price = helper.getView(R.id.tv_old_price);
        iv_pic = helper.getView(R.id.iv_pic);
        rl_root = helper.getView(R.id.rl_root);
        tv_add = helper.getView(R.id.tv_add);
        tv_total = helper.getView(R.id.tv_total);
        pb = helper.getView(R.id.pb);
        tv_total.setText(item.getActives().get(helper.getAdapterPosition()).getRemainNum());
        helper.setText(R.id.tv_time,item.getTitle());
        for (int i = 0; i <item.getActives().size() ; i++) {
            activesBean = item.getActives().get(i);

        }

        Glide.with(mContext).load(activesBean.getDefaultPic()).into(iv_pic);
        helper.setText(R.id.tv_name,activesBean.getActiveName());
        helper.setText(R.id.tv_spec,activesBean.getSpec());
        helper.setText(R.id.tv_price,activesBean.getPrice());
        helper.setText(R.id.tv_old_price,activesBean.getOldPrice());
        pb.setProgress(Integer.parseInt(activesBean.getProgress()));
        tv_total.setText(activesBean.getRemainNum());


        if(activesBean.getSaleDone()==0) {
            tv_add.setText("立即加购");
            tv_add.setBackgroundResource(R.drawable.shape_orange);
        }else {
            tv_add.setText("已售罄");
            tv_add.setBackgroundResource(R.drawable.shape_detail_grey);
        }
        tv_old_price.getPaint().setAntiAlias(true);//抗锯齿
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID,activesBean.getActiveId());
                mContext.startActivity(intent);
            }
        });
    }

    public interface Onclick {
        void addDialog();
    }

}
