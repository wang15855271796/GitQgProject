package com.puyue.www.qiaoge.adapter.mine;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.wallet.MyCountDetailActivity;
import com.puyue.www.qiaoge.model.mine.GetWallertRecordByPageModel;

import java.util.List;

/**
 * @author daff
 * @date 2018/9/22.
 * 备注  明细
 */
public class MyWalletDetailAdapter extends BaseQuickAdapter<GetWallertRecordByPageModel.DataBean.ListBean, BaseViewHolder> {

    private LinearLayout ll_detail;

    private ImageView iv_flag;
    private TextView textPries;
    public MyWalletDetailAdapter(int layoutResId, @Nullable List<GetWallertRecordByPageModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetWallertRecordByPageModel.DataBean.ListBean item) {
        ll_detail = helper.getView(R.id.ll_detail);
        iv_flag = helper.getView(R.id.iv_flag);
        textPries = helper.getView(R.id.textPries);


        if (!TextUtils.isEmpty(item.flowRecordTypeName)) {
            helper.setText(R.id.textTitle, item.flowRecordTypeName);
        }
        if (!TextUtils.isEmpty(item.amount)) {
            helper.setText(R.id.textPries, item.amount);
        }
        if (!TextUtils.isEmpty(item.createDate)) {
            helper.setText(R.id.textTime, item.createDate);
        }
        if (!TextUtils.isEmpty(item.walletRecordChannelType)) {
            helper.setText(R.id.textViewFrom, item.walletRecordChannelType);
        }


        if (Double.parseDouble(item.amount)>0){
            textPries.setTextColor(Color.parseColor("#fff6551a"));
        }else {
            textPries.setTextColor(Color.parseColor("#ff000000"));
        }
        Glide.with(mContext).load(item.iconUrl).into(iv_flag);




        ll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = item.id;
                int type = item.type;
                Intent intent = new Intent(mContext, MyCountDetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                mContext.startActivity(intent);
            }
        });


    }
}
