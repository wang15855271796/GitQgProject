package com.puyue.www.qiaoge.adapter.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.TeamGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/18.
 */

public class TeamActiveQueryAdapter extends BaseQuickAdapter<TeamActiveQueryModel.DataBean, BaseViewHolder> {

    private  ImageView addCar;
   private   OnClick onClick;
    public TeamActiveQueryAdapter(int layoutResId, List<TeamActiveQueryModel.DataBean> data, OnClick onClick) {
        super(layoutResId, data);
        this.onClick=onClick;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    protected void convert(final BaseViewHolder helper, TeamActiveQueryModel.DataBean model) {
        addCar=helper.getView(R.id.addCar);
        ImageView iv_type = helper.getView(R.id.iv_type);
//        if(model.available==true) {
//            iv_type.setVisibility(View.GONE);
//
//        }else {
//            Glide.with(mContext).load(model.saleDoneUrl).into(iv_type);
//            iv_type.setVisibility(View.VISIBLE);
//        }
//        helper.setText(R.id.tv_item_goods_name, model.activeTitle);
//        helper.setText(R.id.tv_item_goods_price, model.price);
//        GlideModel.disPlayError(mContext,model.defaultPic,helper.getView(R.id.iv_item_goods_img));
       /* Glide.with(mContext).load(model.defaultPic).crossFade().transform(new GlideRoundTransform(mContext, 3)).
                error(R.mipmap.icon_default_rec).into((ImageView) helper.getView(R.id.iv_item_goods_img));*/

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.addCarOnclick(helper.getLayoutPosition());
            }
        });

//        helper.getView(R.id.ll_team).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext,TeamGoodsDetailActivity.class);
//                intent.putExtra(AppConstant.ACTIVEID, model.activeId);
//                mContext.startActivity(intent);
//            }
//        });


    }
    public  interface  OnClick{
        void addCarOnclick(int position);
    }
}