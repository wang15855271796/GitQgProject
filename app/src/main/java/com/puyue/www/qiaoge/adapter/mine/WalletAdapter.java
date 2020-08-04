package com.puyue.www.qiaoge.adapter.mine;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.GetWallertRecordByPageModel;
import com.puyue.www.qiaoge.model.mine.MineWalletModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/11.
 */

public class WalletAdapter extends BaseQuickAdapter<GetWallertRecordByPageModel.DataBean.RecordsBean, BaseViewHolder> {

    public WalletAdapter(int layoutResId, List<GetWallertRecordByPageModel.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetWallertRecordByPageModel.DataBean.RecordsBean model) {
//        helper.setText(R.id.tv_item_wallet_type, model.flowRecordTypeName);
//        helper.setText(R.id.tv_item_wallet_time, model.createDate);
//        helper.setText(R.id.tv_item_wallet_bank, model.walletRecordChannelType);
//        helper.setText(R.id.tv_item_wallet_amount, model.amount);
//        if (model.recordType == 1) {
//            //支出
////            helper.setText(R.id.tv_item_wallet_amount, "-" + model.amount);
//            helper.setTextColor(R.id.tv_item_wallet_amount, Color.parseColor("#232131"));
//        } else {
//            //收入
////            helper.setText(R.id.tv_item_wallet_amount, "+" + model.amount);
//            helper.setTextColor(R.id.tv_item_wallet_amount, Color.parseColor("#F54022"));
//        }
    }


}