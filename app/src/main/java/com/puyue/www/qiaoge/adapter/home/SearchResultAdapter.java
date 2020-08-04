package com.puyue.www.qiaoge.adapter.home;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.view.FlowLayout;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/16.
 * 搜索推荐Adapter
 */

public class SearchResultAdapter extends BaseQuickAdapter<SearchResultsModel.DataBean.RecommendProdBean,BaseViewHolder>{

    private TextView tv_price_desc;
    private ImageView iv_head;
    private TextView tv_stock;
    private LinearLayout ll_group;
    private ImageView iv_type;
    Onclick onclick;
    RecommendDialog recommendDialog;

    public SearchResultAdapter(int layoutResId, @Nullable List<SearchResultsModel.DataBean.RecommendProdBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;

    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResultsModel.DataBean.RecommendProdBean item) {
        ImageView iv_no_data = helper.getView(R.id.iv_no_data);
        tv_price_desc = helper.getView(R.id.tv_price_desc);
        iv_type = helper.getView(R.id.iv_type);
        if(item.getFlag()==0) {
            Glide.with(mContext).load(item.getTypeUrl()).into(iv_no_data);
            iv_no_data.setVisibility(View.VISIBLE);
            iv_type.setVisibility(View.GONE);
        }else {
            Glide.with(mContext).load(item.getTypeUrl()).into(iv_type);
            iv_type.setVisibility(View.VISIBLE);
            iv_no_data.setVisibility(View.GONE);
        }
        RelativeLayout rl_spec = helper.getView(R.id.rl_spec);
        helper.setText(R.id.tv_spec,"规格："+item.getSpec());
        ll_group = helper.getView(R.id.ll_group);
        ll_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
                intent.putExtra("priceType", SharedPreferencesUtil.getString(mContext,"priceType"));
                mContext.startActivity(intent);
            }
        });

        RelativeLayout rl_price = helper.getView(R.id.rl_price);
        TextView tv_price = helper.getView(R.id.tv_price);
        helper.setText(R.id.tv_name,item.getProductName());
        helper.setText(R.id.tv_stock_total,item.getInventory());
        helper.setText(R.id.tv_sale,item.getSalesVolume());
        helper.setText(R.id.tv_price,item.getMinMaxPrice());
        helper.setText(R.id.tv_desc,item.getSpecialOffer());
        tv_stock = helper.getView(R.id.tv_stock);
        tv_stock.setText(item.getInventory());

        if(SharedPreferencesUtil.getString(mContext,"priceType").equals("1")) {
            rl_price.setVisibility(View.GONE);
            rl_spec.setVisibility(View.VISIBLE);
            tv_price.setText(item.getMinMaxPrice());
            tv_price.setVisibility(View.VISIBLE);
            tv_price_desc.setVisibility(View.GONE);
        }else {
            rl_spec.setVisibility(View.GONE);
            rl_price.setVisibility(View.VISIBLE);
            tv_price.setText(item.getMinMaxPrice());
            tv_price.setVisibility(View.GONE);
            tv_price_desc.setVisibility(View.VISIBLE);
        }

        rl_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.getPrice();
                }
            }
        });
        rl_spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.addDialog();
                }

                if(StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    recommendDialog = new RecommendDialog(mContext,item);
                    recommendDialog.show();
                }
            }
        });

        iv_head = helper.getView(R.id.iv_head);
        Glide.with(mContext)
                .load(item.getDefaultPic())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .apply(new RequestOptions().placeholder(iv_head.getDrawable()).skipMemoryCache(false).dontAnimate())
                .into(iv_head);
    }

    public interface Onclick {
        void addDialog();
        void getPrice();
    }

}