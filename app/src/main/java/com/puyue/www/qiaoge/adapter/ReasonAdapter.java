package com.puyue.www.qiaoge.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.CancleReasonModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/6/5
 */
public class ReasonAdapter extends BaseQuickAdapter<CancleReasonModel.DataBean,BaseViewHolder> {
    int selectionPosition = -1;
    public ReasonAdapter(int layoutResId, @Nullable List<CancleReasonModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CancleReasonModel.DataBean item) {
        helper.setText(R.id.tv_reason,item.getCode());

        if(selectionPosition==helper.getAdapterPosition()) {
            helper.setVisible(R.id.iv_icon,true);
        }else {
            helper.setVisible(R.id.iv_icon,false);
        }
    }

    public void selectionPosition(int position){
        this.selectionPosition  = position;
    }
}
