package com.puyue.www.qiaoge.adapter.market;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
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
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.MarketGialog;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.view.FlowLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 * 分类右侧Adpater
 */

public class MarketGoodsAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean, BaseViewHolder> {

    private ImageView iv_head;
    private FlowLayout fl_container;
    private LinearLayout ll_group;
    private ImageView iv_type;
    Onclick onclick;
    MarketGialog marketGialog;
    private TextView tv_price;

    public MarketGoodsAdapter( int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;

    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean item) {
        int businessType = item.getBusinessType();
        RelativeLayout rl_price = helper.getView(R.id.rl_price);
        TextView tv_desc = helper.getView(R.id.tv_desc);
        ImageView iv_no_data = helper.getView(R.id.iv_no_data);
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
                if(businessType==11) {
                    Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, item.getActiveId());
                    intent.putExtra("priceType",SharedPreferencesUtil.getString(mContext,"priceType"));
                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
                    intent.putExtra("priceType",SharedPreferencesUtil.getString(mContext,"priceType"));
                    mContext.startActivity(intent);
                }
            }
        });

        fl_container = helper.getView(R.id.fl_container);
        helper.setText(R.id.tv_name,item.getProductName());
        tv_price = helper.getView(R.id.tv_price);
        helper.setText(R.id.tv_sale,item.getSalesVolume());
        helper.setText(R.id.tv_price,item.getMinMaxPrice());
        helper.setText(R.id.tv_stock_total,item.getInventory());

        if(SharedPreferencesUtil.getString(mContext,"priceType").equals("1")) {
            tv_price.setVisibility(View.VISIBLE);
            tv_desc.setVisibility(View.GONE);
            rl_price.setVisibility(View.GONE);
            rl_spec.setVisibility(View.VISIBLE);
        }else {
            rl_price.setVisibility(View.VISIBLE);
            rl_spec.setVisibility(View.GONE);
            tv_price.setVisibility(View.GONE);
            tv_desc.setVisibility(View.VISIBLE);
        }
        TextView tv_choose_spec = helper.getView(R.id.tv_choose_spec);
        rl_spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onclick!=null) {
                    onclick.addDialog();
                }

                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    marketGialog = new MarketGialog(mContext, item);
                    marketGialog.show();

                }
            }
        });

        rl_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.getPrice();
                }
            }
        });
        tv_choose_spec.setText("选规格");
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
