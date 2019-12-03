package com.puyue.www.qiaoge.adapter.home;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.ItemConditionModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/16.
 */

public class ItemConditionAdapter extends BaseQuickAdapter<ItemConditionModel, BaseViewHolder> {
    private TextView mTvCondition;

    public ItemConditionAdapter(int layoutResId, List<ItemConditionModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemConditionModel model) {
        mTvCondition = (TextView) helper.getView(R.id.tv_item_screen_condition);
        mTvCondition.setText(model.condition);
        if (model.isSelected){
            //被选中
            mTvCondition.setTextColor(ContextCompat.getColor(mContext,R.color.wallet_checked));
            mTvCondition.setBackgroundResource(0);
        }else {
            //未选中
            mTvCondition.setTextColor(ContextCompat.getColor(mContext,R.color.app_cancle_gray));
            mTvCondition.setBackgroundResource(R.drawable.shape_gray_box);
        }
    }
}