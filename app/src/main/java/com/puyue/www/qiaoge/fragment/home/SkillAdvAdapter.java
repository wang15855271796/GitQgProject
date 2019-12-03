package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SpikeGoodsDetailsActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/11/25
 */
public class SkillAdvAdapter extends BaseQuickAdapter<HomeBaseModel.DataBean.SecKillListBean.KillsBean,BaseViewHolder> {
    private OnClick onClick;

    public SkillAdvAdapter(int layoutResId, @Nullable List<HomeBaseModel.DataBean.SecKillListBean.KillsBean> data) {
        super(layoutResId, data);
    }
    public void setOnclick(OnClick onClick) {
        this.onClick = onClick;
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeBaseModel.DataBean.SecKillListBean.KillsBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        ImageView iv_skill = helper.getView(R.id.iv_skill);
        Glide.with(mContext).load(item.getPic()).into(iv_skill);
        helper.setText(R.id.tv_price,item.getPrice());
        LinearLayout ll = helper.getView(R.id.ll);
        TextView tv_old_price = helper.getView(R.id.tv_old_price);
        if(item.getOldPrice()!=null) {
            helper.setText(R.id.tv_old_price,item.getOldPrice());
            tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ll.setVisibility(View.VISIBLE);
            tv_old_price.getPaint().setAntiAlias(true);//抗锯齿
        }else {
            ll.setVisibility(View.INVISIBLE);
        }
        helper.setText(R.id.tv_sale,item.getSales());
        helper.getView(R.id.iv_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick!=null) {
                    onClick.shoppingCartOnClick(helper.getAdapterPosition());
                }
            }
        });
        iv_skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SpikeGoodsDetailsActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.getActiveId());
                mContext.startActivity(intent);
            }
        });



    }

    public interface OnClick {
        void shoppingCartOnClick(int position);
    }

}
