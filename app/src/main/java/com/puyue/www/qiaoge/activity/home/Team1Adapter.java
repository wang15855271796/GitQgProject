package com.puyue.www.qiaoge.activity.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.market.MarketGoodsAdapter;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/24
 */
public class Team1Adapter extends BaseQuickAdapter<TeamActiveQueryModel.DataBean,BaseViewHolder> {

    private ImageView iv_pic;
    private ImageView iv_icon;
    private ImageView iv_sale_done;
    private TeamActiveQueryModel.DataBean.ActivesBean activesBean;
    private TextView tv_price;

    Onclick onclick;
    public Team1Adapter(int layoutResId, List<TeamActiveQueryModel.DataBean> teamList,Onclick onclick) {
        super(layoutResId, teamList);
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamActiveQueryModel.DataBean item) {
        iv_pic = helper.getView(R.id.iv_pic);
        activesBean = item.getActives().get(0);
        Glide.with(mContext).load(activesBean.getDefaultPic()).into(iv_pic);

        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(item.getActives().get(0).getActiveName());
        tv_price = helper.getView(R.id.tv_price);
        ImageView iv_add = helper.getView(R.id.iv_add);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_price.setText(item.getActives().get(0).getPrice());
//        iv_icon = helper.getView(R.id.iv_icon);
//        Glide.with(mContext).load(activesBean.get).into(iv_pic);
//
//        iv_sale_done = helper.getView(R.id.iv_sale_done);
//
//        if(item.available) {
//            iv_sale_done.setVisibility(View.VISIBLE);
//            Glide.with(mContext).load(item.saleDoneUrl).into(iv_sale_done);
//        }else {
//            iv_sale_done.setVisibility(View.GONE);
//        }
    }

    public interface Onclick {
        void addDialog();
    }


}
