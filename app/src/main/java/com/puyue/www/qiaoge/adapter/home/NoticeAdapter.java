package com.puyue.www.qiaoge.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.NoticeListModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeListModel.DataBean.ListBean, BaseViewHolder> {

    public NoticeAdapter(int layoutResId, @Nullable List<NoticeListModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeListModel.DataBean.ListBean item) {
        helper.setText(R.id.tv_item_notice_title, item.title);
        helper.setText(R.id.tv_item_notice_date, item.gmtCreate);
    }
}
