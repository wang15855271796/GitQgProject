package com.puyue.www.qiaoge.fragment.market;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.MarketRightModel;

import java.util.List;

class RvDetailsAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean,BaseViewHolder> {

    public RvDetailsAdapter(int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean item) {
        helper.setText(R.id.tv_name,item.getProductName());
    }
}
