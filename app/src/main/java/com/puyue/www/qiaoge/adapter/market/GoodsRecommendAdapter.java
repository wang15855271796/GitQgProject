package com.puyue.www.qiaoge.adapter.market;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.GuessModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.market.GoodsRecommendModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/13.
 */

public class GoodsRecommendAdapter extends BaseQuickAdapter<GuessModel.DataBean, BaseViewHolder> {

    public GoodsRecommendAdapter(int layoutResId, List<GuessModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GuessModel.DataBean model) {
        if (StringHelper.notEmptyAndNull(model.getImgUrl())) {
            GlideModel.disPlayError(mContext,model.getImgUrl(),helper.getView(R.id.iv_item_goods_recommend));
        }
        helper.setText(R.id.tv_item_goods_recommend_name, model.getProductName());
//        helper.setText(R.id.tv_item_goods_recommend_price, model.getMinMaxPrice());
//        helper.setText(R.id.tv_item_goods_recommend_pin, model.getSalesVolume());

        ((LinearLayout) helper.getView(R.id.ll_item_goods_detail_recommend)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, model.getProductMainId());
                intent.putExtra("priceType",SharedPreferencesUtil.getString(mContext,"priceType"));
                mContext.startActivity(intent);
            }
        });
    }
}