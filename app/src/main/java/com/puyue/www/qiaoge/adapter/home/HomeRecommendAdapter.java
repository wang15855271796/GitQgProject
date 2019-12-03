package com.puyue.www.qiaoge.adapter.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.home.HomeNewRecommendModel;
import com.puyue.www.qiaoge.model.home.IndexHomeModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/10.
 */

public class HomeRecommendAdapter extends BaseQuickAdapter<HomeNewRecommendModel.DataBean.ListBean, BaseViewHolder> {

    public HomeRecommendAdapter(int layoutResId, @Nullable List<HomeNewRecommendModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, HomeNewRecommendModel.DataBean.ListBean item) {
        helper.setIsRecyclable(false);
        GlideModel.disPlayError(mContext, item.getImgUrl(), helper.getView(R.id.iv_home_seckill_img));
        helper.setText(R.id.tv_spec, item.getSpec());
        helper.setText(R.id.tvTitle, item.getProductName());
        helper.setText(R.id.tvMonthlySale, item.getMonthSalesVolume());
        helper.setText(R.id.tvHomePrice,item.getMinMaxPrice());
        //把下面部分处理好就可以了
        ((RelativeLayout) helper.getView(R.id.ll_item_group)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
                mContext.startActivity(intent);
            }
        });
    }
}
