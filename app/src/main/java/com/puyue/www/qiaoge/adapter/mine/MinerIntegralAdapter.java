package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.model.mine.wallet.MinerIntegralModel;

import java.util.List;

/**
 * Created by ${daff}
 * on 2018/10/19
 * 备注 我的积分
 */
public class MinerIntegralAdapter  extends BaseQuickAdapter<MinerIntegralModel.DataBean.AppPointVOListBean,BaseViewHolder>{

    public MinerIntegralAdapter(int layoutResId, @Nullable List<MinerIntegralModel.DataBean.AppPointVOListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MinerIntegralModel.DataBean.AppPointVOListBean item) {
        helper.setText(R.id.textViewIntegralTitle,item.getTypeDesc());
        helper.setText(R.id.textViewIntegralPrice,item.getPointChangeDesc());
        helper.setText(R.id.textViewIntegralTime,item.getTime());
    }
}
