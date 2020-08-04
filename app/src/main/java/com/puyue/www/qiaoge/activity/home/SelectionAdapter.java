package com.puyue.www.qiaoge.activity.home;

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
import com.puyue.www.qiaoge.adapter.HotAdapter;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.HotDialog;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import java.util.List;

/**
 * Created by ${王涛} on 2019/11/15
 */
public class SelectionAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean,BaseViewHolder> {

//    private ImageView iv_head;
//    private TextView tv_stock;
//    private LinearLayout ll_group;
//    private ImageView iv_type;
//    Onclick onclick;
//    String enjoyProduct;

    ClassifyDialog classifyDialog;
    private ImageView iv_pic;
    List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> activesBean;
    private ImageView iv_add;
    Onclick onclick;
    private HotDialog hotDialog;
    private RelativeLayout rl_group;
    String flag;
    private TextView tv_sale;
    ImageView iv_flag;
    String enjoyProduct;
    private TextView tv_desc;
    TextView tv_price;

    public SelectionAdapter(String enjoyProduct,int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> data,Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
        this.activesBean = data;
        this.enjoyProduct = enjoyProduct;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean item) {

        tv_desc = helper.getView(R.id.tv_desc);
        tv_price = helper.getView(R.id.tv_price);
        iv_pic = helper.getView(R.id.iv_pic);
        iv_flag = helper.getView(R.id.iv_flag);
        iv_add = helper.getView(R.id.iv_add);
        rl_group = helper.getView(R.id.rl_group);
        tv_sale = helper.getView(R.id.tv_sale);
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_pic);
        helper.setText(R.id.tv_name,item.getProductName());

        if(enjoyProduct!=null) {
            if(enjoyProduct.equals("1")) {
                tv_price.setVisibility(View.VISIBLE);
                tv_desc.setVisibility(View.GONE);
                tv_price.setText(item.getMinMaxPrice());
            }else {
                tv_price.setVisibility(View.GONE);
                tv_desc.setVisibility(View.VISIBLE);
            }
        }

        tv_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.tipClick();
                }
            }
        });

        rl_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID,item.getProductMainId());
                intent.putExtra("priceType",enjoyProduct);
                mContext.startActivity(intent);
            }
        });

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    if(SharedPreferencesUtil.getString(mContext,"priceType").equals("1")) {
                        onclick.addDialog();
                        if(StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                            classifyDialog = new ClassifyDialog(mContext,item);
                            classifyDialog.show();
                        }
                    }else {
                        onclick.tipClick();
                    }

                }
            }
        });


//        ImageView iv_no_data = helper.getView(R.id.iv_no_data);
//        iv_type = helper.getView(R.id.iv_type);
//        if(item.getFlag()==0) {
//            Glide.with(mContext).load(item.getTypeUrl()).into(iv_no_data);
//            iv_no_data.setVisibility(View.VISIBLE);
//            iv_type.setVisibility(View.GONE);
//        }else {
//            Glide.with(mContext).load(item.getTypeUrl()).into(iv_type);
//            iv_type.setVisibility(View.VISIBLE);
//            iv_no_data.setVisibility(View.GONE);
//        }
//
//        helper.setText(R.id.tv_spec,"规格："+item.getSpec());
//        ll_group = helper.getView(R.id.ll_group);
//        ll_group.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
//                intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
//                mContext.startActivity(intent);
//            }
//        });
//
//        helper.setText(R.id.tv_name,item.getProductName());
//        helper.setText(R.id.tv_stock_total,item.getInventory());
//        helper.setText(R.id.tv_sale,item.getSalesVolume());
//        helper.setText(R.id.tv_price,item.getMinMaxPrice());
//        helper.setText(R.id.tv_desc,item.getSpecialOffer());
//        tv_stock = helper.getView(R.id.tv_stock);
//        tv_stock.setText(item.getInventory());
//
//        TextView tv_choose_spec = helper.getView(R.id.tv_choose_spec);
//        tv_choose_spec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onclick!=null) {
//                    onclick.addDialog();
//                }
//                if(StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//                    classifyDialog = new ClassifyDialog(mContext,item);
//                    classifyDialog.show();
//                }
//
//
//            }
//        });
//
//        iv_head = helper.getView(R.id.iv_head);
//        Glide.with(mContext)
//                .load(item.getDefaultPic())
//                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
//                .apply(new RequestOptions().placeholder(iv_head.getDrawable()).skipMemoryCache(false).dontAnimate())
//                .into(iv_head);
//
    }

    public interface Onclick {
        void addDialog();
        void tipClick();
    }
}
