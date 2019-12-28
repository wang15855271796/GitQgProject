package com.puyue.www.qiaoge.adapter.home;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.SeckillListModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/12
 */
public class SpikeActiveQueryAdapter extends BaseQuickAdapter<SeckillListModel.DataBean.KillsBean, BaseViewHolder> {

    private ImageView ivSpike;
    private TextView tvPrice;
    private TextView tvOldPrice;
    private TextView tvTitle;
    private TextView ivAdd;
    private TextView ivSoldOut;
    private TextView ivAddGood;
    private TextView tvPsc;
    private TextView tvSale;
    private TextView tvSoldSale;
    private FrameLayout frameLayout;
    private ImageView ivSoldOutLeft;
    private TextView tv_add_remind;
    private long currentTime;
    private long startTime;
    Onclick onclick;
    private ProgressBar mProgressBar;
    SeckillListModel.DataBean dataBean;
    public SpikeActiveQueryAdapter(int layoutResId, @Nullable List<SeckillListModel.DataBean.KillsBean> data, SeckillListModel.DataBean dataBean,Onclick onclick) {
        super(layoutResId, data);
        this.dataBean = dataBean;
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, SeckillListModel.DataBean.KillsBean item) {
        tv_add_remind = helper.getView(R.id.tv_add_remind);
        ivSpike = helper.getView(R.id.iv_item_spike_img);
        tvTitle = helper.getView(R.id.tv_item_spike_title);
        tvPrice = helper.getView(R.id.tv_item_spike_price);
        tvOldPrice = helper.getView(R.id.tv_item_spike_old_price);
        ivAdd = helper.getView(R.id.addCar);
        ivSoldOut = helper.getView(R.id.tv_sold_out);
        ivAddGood = helper.getView(R.id.tv_add_remind);
        tvPsc = helper.getView(R.id.tv_item_spike_specification);
        frameLayout = helper.getView(R.id.iv_bg);
        ivSoldOutLeft = helper.getView(R.id.iv_sold);
        tvSale = helper.getView(R.id.tv_item_spike_sales);
        mProgressBar = helper.getView(R.id.pb_item_spike);
        tvSoldSale = helper.getView(R.id.tv_item_spike_sales);
        tvTitle.setText(item.title);
        tvPrice.setText(item.price);
        tvOldPrice.setText(item.oldPrice);
        tvPsc.setText(item.spec);
        tvSale.setText(item.sales);
        mProgressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.spike_progress));
        mProgressBar.setProgress(Integer.parseInt(item.progress));
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
        tvSoldSale.setText("已抢购" + item.progress + "%");
        String spikeFlag = UserInfoHelper.getSpikePosition(mContext);
        Log.i("vvvv", "convert: " + spikeFlag);
        GlideModel.disPlayError(mContext,item.pic,ivSpike);
        currentTime = dataBean.currentTime;
        startTime = dataBean.startTime;
        if(Integer.parseInt(spikeFlag)==0) {
            //未开始
            if(item.warnMe==0) {
                tv_add_remind.setText("取消提醒");
                SharedPreferencesUtil.saveInt(mContext,"warnMe",0);
            }else {
                tv_add_remind.setText("添加提醒");
                SharedPreferencesUtil.saveInt(mContext,"warnMe",1);
            }
            tv_add_remind.setVisibility(View.VISIBLE);
        }else {
            // 已开始
            ivAdd.setVisibility(View.GONE);
            ivSoldOut.setVisibility(View.GONE);

            if(item.soldOut==0) {
                tv_add_remind.setVisibility(View.GONE);
            }else {
                ivSoldOut.setVisibility(View.GONE);
                ivAdd.setVisibility(View.VISIBLE);
            }
        }




        if (Integer.parseInt(spikeFlag) == 0) {
            //秒杀预告
            ivAdd.setVisibility(View.GONE);
            ivAddGood.setVisibility(View.VISIBLE);
            ivSoldOut.setVisibility(View.GONE);
            ivSoldOutLeft.setVisibility(View.GONE);
            frameLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            ivSoldOut.setVisibility(View.GONE);
        } else if (Integer.parseInt(spikeFlag) == 1) {

            if (item.soldOut == 0) {
                ivAddGood.setVisibility(View.GONE);
                ivAdd.setVisibility(View.VISIBLE);
                ivSoldOut.setVisibility(View.GONE);
                ivSoldOutLeft.setVisibility(View.GONE);

            } else {
                ivAddGood.setVisibility(View.GONE);
                ivAdd.setVisibility(View.GONE);
                ivSoldOut.setVisibility(View.VISIBLE);

                GlideModel.disPlayError(mContext,item.flagUrl,ivSoldOutLeft);
                ivSoldOutLeft.setVisibility(View.VISIBLE);

            }
        }


        tv_add_remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.addRemind(v);
                }
                if(item.warnMe==0) {
                    tv_add_remind.setText("添加提醒");

                }else {
                    tv_add_remind.setText("取消提醒");
                }
            }
        });
    }

    public interface Onclick {
        void addRemind(View view);
    }

}
