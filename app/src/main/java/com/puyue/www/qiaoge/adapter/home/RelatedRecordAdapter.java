package com.puyue.www.qiaoge.adapter.home;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;

import java.util.List;

/**
 * Created by ${王文博} on 2019/9/4
 */
public class RelatedRecordAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    private TextView tv_reason_content;


    public RelatedRecordAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        tv_reason_content=helper.getView(R.id.tv_reason_content);
        tv_reason_content.setText("退货订单"+item+"");
    }
}
