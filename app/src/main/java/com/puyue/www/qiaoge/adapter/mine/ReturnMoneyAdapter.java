package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/15
 */
public class ReturnMoneyAdapter extends BaseQuickAdapter<ReturnOrderDetailModel.DataBean.ReturnChannelBean, BaseViewHolder> {

private TextView mReasonContent;
    public ReturnMoneyAdapter(int layoutResId, @Nullable List<ReturnOrderDetailModel.DataBean.ReturnChannelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnOrderDetailModel.DataBean.ReturnChannelBean item) {
        mReasonContent = helper.getView(R.id.tv_reason_content);
        mReasonContent.setText(item.channelText);
    }
}
