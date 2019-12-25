package com.puyue.www.qiaoge.activity.home;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/25
 */
public class Coupon1Adapter extends BaseQuickAdapter<TeamActiveQueryModel.DataBean,BaseViewHolder> {

    Onclick onclick;
    private ImageView iv_pic;
    private TeamActiveQueryModel.DataBean.ActivesBean activesBean;


    public Coupon1Adapter(int layoutResId, @Nullable List<TeamActiveQueryModel.DataBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean item) {
        iv_pic = helper.getView(R.id.iv_pic);
        activesBean = item.getActives().get(helper.getAdapterPosition());
        Glide.with(mContext).load(activesBean.getDefaultPic()).into(iv_pic);

        helper.setText(R.id.tv_name,activesBean.getActiveName());
        helper.setText(R.id.tv_spec,activesBean.getSpec());
        helper.setText(R.id.tv_price,activesBean.getPrice());
        helper.setText(R.id.tv_old_price,activesBean.getOldPrice());

    }

    public interface Onclick {
        void addDialog();
    }

}
