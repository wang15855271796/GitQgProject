package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.wallet.OrderReturnSelectReasonModel;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/15
 */
public class ReturnOrderAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private TextView mReasonContent;
    public ReturnOrderAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        mReasonContent = helper.getView(R.id.tv_reason_content);
        mReasonContent.setText(item);
    }
}
