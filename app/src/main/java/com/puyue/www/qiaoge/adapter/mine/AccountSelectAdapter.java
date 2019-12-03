package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.SearchListModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/16
 */
public class AccountSelectAdapter extends BaseQuickAdapter<SearchListModel.DataBean.List3Bean, BaseViewHolder> {
    private TextView tv_content;

    public AccountSelectAdapter(int layoutResId, @Nullable List<SearchListModel.DataBean.List3Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchListModel.DataBean.List3Bean item) {
        tv_content=helper.getView(R.id.tv_content);

        tv_content.setText(item.getValue());

    }
}
