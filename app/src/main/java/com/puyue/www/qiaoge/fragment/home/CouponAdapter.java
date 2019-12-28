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
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;

import java.util.List;

public class CouponAdapter extends BaseQuickAdapter<HomeBaseModel.DataBean.OfferListBean,BaseViewHolder> {

    private OnClick onClick;

    public CouponAdapter(int layoutResId, @Nullable List<HomeBaseModel.DataBean.OfferListBean> data) {
        super(layoutResId, data);
    }

    public void setOnclick(OnClick onClick) {
        this.onClick = onClick;
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeBaseModel.DataBean.OfferListBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        ImageView iv_sold = helper.getView(R.id.iv_sold);
        if(item.getSoldOut()==1) {
            iv_sold.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getFlagUrl()).into(iv_sold);
        }else {
            iv_sold.setVisibility(View.GONE);
        }

        ImageView iv_skill = helper.getView(R.id.iv_skill);
        Glide.with(mContext).load(item.getPic()).into(iv_skill);
        helper.setText(R.id.tv_price,item.getPrice());
        helper.setText(R.id.tv_sale,item.getSalesVolume());
        LinearLayout ll = helper.getView(R.id.ll);
        TextView tv_old_price = helper.getView(R.id.tv_old_price);
        if(item.getOldPrice().equals("")) {
            ll.setVisibility(View.INVISIBLE);
        }else {
            helper.setText(R.id.tv_old_price,item.getOldPrice());
            tv_old_price.getPaint().setAntiAlias(true);//抗锯齿
            tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ll.setVisibility(View.VISIBLE);
        }
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
                Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.getActiveId());
                mContext.startActivity(intent);
            }
        });
    }

    public interface OnClick {
        void shoppingCartOnClick(int position);
    }

}
