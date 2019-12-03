package com.puyue.www.qiaoge.fragment.home;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.home.HomeNewRecommendModel;

import java.util.List;

public class NewAdapter extends BaseQuickAdapter<HomeNewRecommendModel.DataBean.ListBean,BaseViewHolder> {

    public NewAdapter(int layoutResId, @Nullable List<HomeNewRecommendModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeNewRecommendModel.DataBean.ListBean item) {
        helper.setText(R.id.tv_title,item.getProductName());
        ImageView iv_skill = helper.getView(R.id.iv_skill);
        ImageView iv_cart = helper.getView(R.id.iv_cart);
        iv_cart.setVisibility(View.GONE);
        TextView tv = helper.getView(R.id.tv);
        tv.setVisibility(View.GONE);
        Glide.with(mContext).load(item.getImgUrl()).into(iv_skill);
        helper.setText(R.id.tv_price,item.getMinMaxPrice());
        helper.getView(R.id.tv_old_price).setVisibility(View.GONE);
        helper.setText(R.id.tv_sale,item.getMonthSalesVolume());
        iv_skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
                mContext.startActivity(intent);
            }
        });
    }
}
