package com.puyue.www.qiaoge.adapter.market;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.market.MarketAlreadyGoodModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/21
 */
public class MarketAlreadyGoodAdapter extends BaseQuickAdapter<MarketAlreadyGoodModel.DataBean, BaseViewHolder> {

    private TextView mTvName;
    private ImageView mIvProduct;

    public MarketAlreadyGoodAdapter(int layoutResId, @Nullable List<MarketAlreadyGoodModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketAlreadyGoodModel.DataBean item) {

        mTvName = helper.getView(R.id.tv_good_name);
        mIvProduct = helper.getView(R.id.iv_good);

        mTvName.setText(item.getProductName());

        GlideModel.disPlayError(mContext,item.getImgUrl(),mIvProduct);


    }
}
