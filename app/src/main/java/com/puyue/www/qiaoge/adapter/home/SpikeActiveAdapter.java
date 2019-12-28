package com.puyue.www.qiaoge.adapter.home;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.SpikeActiveQueryModel;
import com.puyue.www.qiaoge.view.GlideModel;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class SpikeActiveAdapter extends BaseQuickAdapter<SpikeActiveQueryModel.DataBean.ListBean, BaseViewHolder> {
    private RelativeLayout mRlOnce;
    private ProgressBar mPbSale;
    private TextView mTvSales;
    private ImageView mIvSold;
    private TextView mIvSoldOut;
    private TextView mTvPrice;

    private ImageView imageOver;
    private TextView addCar;
    private Onclick onclick;

    public SpikeActiveAdapter(int layoutResId, List<SpikeActiveQueryModel.DataBean.ListBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }


    @Override
    protected void convert(final BaseViewHolder helper, SpikeActiveQueryModel.DataBean.ListBean model) {
        GlideModel.displayTransForms(mContext,model.defaultPic,helper.getView(R.id.iv_item_spike_img));

        helper.setText(R.id.tv_item_spike_title, model.activeTitle);
        addCar = helper.getView(R.id.addCar);
        mIvSoldOut = helper.getView(R.id.tv_sold_out);
        imageOver = helper.getView(R.id.imageOver);
        mTvSales = helper.getView(R.id.tv_item_spike_sales);
        mTvSales.setText("已售" + model.progress + "%");
        mRlOnce = helper.getView(R.id.rl_item_spike_once);
        mIvSold = helper.getView(R.id.iv_item_spike_type);
        mPbSale = helper.getView(R.id.pb_item_spike);
        if (Integer.parseInt(model.inventory) > 0) {
            //还有库存,就根据后台状态来
            if ("NOT_START".equals(model.type)) {
                // NOT_START("未开始"
                mIvSold.setVisibility(View.GONE);
                mRlOnce.setVisibility(View.VISIBLE);
                mTvSales.setVisibility(View.VISIBLE);
                imageOver.setVisibility(View.GONE);
                addCar.setVisibility(View.VISIBLE);
                mIvSoldOut.setVisibility(View.GONE);
                mPbSale.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.layerlist_spike_progress));
                helper.setTextColor(R.id.tv_item_spike_price, Color.parseColor("#FF703C"));

            } else if ("STARTED".equals(model.type)) {
                // STARTED("进行中"
                mIvSold.setVisibility(View.GONE);
                mRlOnce.setVisibility(View.VISIBLE);
                mTvSales.setVisibility(View.VISIBLE);
                imageOver.setVisibility(View.GONE);
                addCar.setVisibility(View.VISIBLE);
                mIvSoldOut.setVisibility(View.GONE);
                mPbSale.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.layerlist_spike_progress));
                helper.setTextColor(R.id.tv_item_spike_price, Color.parseColor("#FF703C"));
            } else if ("OVER".equals(model.type)) {
                //  OVER("已结束"
                mIvSold.setVisibility(View.VISIBLE);
                mRlOnce.setVisibility(View.GONE);
                mTvSales.setVisibility(View.VISIBLE);
                imageOver.setVisibility(View.VISIBLE);
                addCar.setVisibility(View.GONE);
                mIvSoldOut.setVisibility(View.VISIBLE);
                mPbSale.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.bg_snap_up));
                mPbSale.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.app_item_pro_bg));

            }
        } else {
            //没有库存了,直接显示已售完
            mIvSold.setVisibility(View.VISIBLE);

            mRlOnce.setVisibility(View.GONE);
            mTvSales.setVisibility(View.GONE);
            addCar.setVisibility(View.GONE);
            mIvSoldOut.setVisibility(View.VISIBLE);
            helper.setTextColor(R.id.tv_item_spike_price, Color.parseColor("#FF703C"));
            mPbSale.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.app_item_pro_bg));

        }
        mPbSale.setProgress(Integer.parseInt(model.progress));
        mTvPrice = helper.getView(R.id.tv_item_spike_old_price);
        if (model.oldPrice.equals("")) {
            mTvPrice.setText("");
        } else {
            mTvPrice.setText(" " + model.oldPrice + " ");
            mTvPrice.getPaint().setAntiAlias(true);//抗锯齿
            mTvPrice.getPaint().setColor(ContextCompat.getColor(mContext, R.color.app_cancle_gray));
            mTvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
        }


        helper.setText(R.id.tv_item_spike_price, model.price);
        helper.setText(R.id.tv_item_spike_specification, model.specification);

        addCar.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                onclick.addCarOnclick(helper.getLayoutPosition());
            }
        });
    }

    public interface Onclick {
        void addCarOnclick(int position);
    }
}