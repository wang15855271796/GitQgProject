package com.puyue.www.qiaoge.activity.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/24
 */
public class Team2Adapter extends BaseQuickAdapter<TeamActiveQueryModel.DataBean,BaseViewHolder> {

    private ImageView iv_pic;
    private ImageView iv_icon;
    private ImageView iv_sale_done;
    private TeamActiveQueryModel.DataBean.ActivesBean activesBean;
    private TextView tv_price;

    public Team2Adapter(int layoutResId, @Nullable List<TeamActiveQueryModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean item) {
        iv_pic = helper.getView(R.id.iv_pic);
        activesBean = item.getActives().get(1);
        Glide.with(mContext).load(activesBean.getDefaultPic()).into(iv_pic);

        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(item.getActives().get(1).getActiveName());

        tv_price = helper.getView(R.id.tv_price);
        tv_price.setText(item.getActives().get(1).getPrice());

        for (int i = 0; i < item.getActives().size(); i++) {

        }
    }
}
