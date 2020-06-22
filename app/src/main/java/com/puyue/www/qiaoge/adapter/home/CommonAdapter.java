package com.puyue.www.qiaoge.adapter.home;

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
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.home.CouponModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/11/5
 */
public class CommonAdapter extends BaseQuickAdapter<CouponModel.DataBean.ActivesBean,BaseViewHolder> {

    private ImageView iv_pic;
    private ImageView iv_add;
    private RelativeLayout rl_group;
    String flag;
    ImageView iv_flag;
    private TextView tv_old_price;
    private TextView tv_coupon;
    RelativeLayout rl_coupon;
    String style;
    public OnClick onClick;
    ImageView iv_sale_done;
    public CommonAdapter(String style,int layoutResId, @Nullable List<CouponModel.DataBean.ActivesBean> data) {
        super(layoutResId, data);
        this.style = style;
    }



    public void setOnclick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CouponModel.DataBean.ActivesBean item) {
        iv_pic = helper.getView(R.id.iv_pic);
        iv_sale_done = helper.getView(R.id.iv_sale_done);
        iv_flag = helper.getView(R.id.iv_flag);
        iv_add = helper.getView(R.id.iv_add);
        tv_coupon = helper.getView(R.id.tv_coupon);
        rl_coupon = helper.getView(R.id.rl_coupon);
        rl_group = helper.getView(R.id.rl_group);
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_pic);
        helper.setText(R.id.tv_name,item.getActiveName());
        helper.setText(R.id.tv_price,item.getPrice());
        tv_old_price = helper.getView(R.id.tv_old_price);
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.tv_old_price,item.getOldPrice());
        tv_old_price.getPaint().setAntiAlias(true);//抗锯齿
        if(item.getDiscount()!=null) {
            tv_coupon.setText(item.getDiscount());
            rl_coupon.setVisibility(View.VISIBLE);
        }else {
            rl_coupon.setVisibility(View.GONE);
        }

        if(item.getFlag()==1) {
            iv_sale_done.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getSoldOutPic()).into(iv_sale_done);
        }else {
            iv_sale_done.setVisibility(View.GONE);
        }


        rl_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(style.equals("2")) {
                    Intent intent = new Intent(mContext,SeckillGoodActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID,item.getActiveId());
                    intent.putExtra("num","-1");
                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID,item.getActiveId());
                    mContext.startActivity(intent);
                }
            }
        });

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick!=null) {
                    onClick.shoppingCartOnClick(helper.getAdapterPosition());
                }

//                if(style.equals("2")) {
//
//                }
            }
        });
    }

    public interface OnClick {
        void shoppingCartOnClick(int position);
    }

}
