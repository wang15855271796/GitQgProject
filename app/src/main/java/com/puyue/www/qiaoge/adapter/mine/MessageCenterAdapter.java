package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.MessageListModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class MessageCenterAdapter extends BaseQuickAdapter<MessageListModel.DataBean.ListBean, BaseViewHolder> {

    public MessageCenterAdapter(int layoutResId, @Nullable List<MessageListModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageListModel.DataBean.ListBean item) {
        if (item.looked_status == 0) {
            //消息未读
            helper.setTextColor(R.id.tv_item_message_center_title, Color.parseColor("#232131"));
            helper.setTextColor(R.id.tv_item_message_center_date, Color.parseColor("#a7a7a7"));
        } else if (item.looked_status == 1) {
            //消息已读
            helper.setTextColor(R.id.tv_item_message_center_title, Color.parseColor("#b7b7b7"));
            helper.setTextColor(R.id.tv_item_message_center_date, Color.parseColor("#b7b7b7"));
        }
        helper.setText(R.id.tv_item_message_center_title, item.title);
        helper.setText(R.id.tv_item_message_center_date, item.gmtCreateTime);
    }
}
