package com.puyue.www.qiaoge.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.CommonListDialog;
import com.puyue.www.qiaoge.dialog.HotDialog;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import java.util.List;

/**
 * Created by ${王涛} on 2020/6/2
 */
public class HotAdapter extends BaseQuickAdapter<ProductNormalModel.DataBean.ListBean,BaseViewHolder> {

    private ImageView iv_pic;
    List<ProductNormalModel.DataBean.ListBean> activesBean;
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

    public HotAdapter(String enjoyProduct,String flag, int layoutResId, @Nullable List<ProductNormalModel.DataBean.ListBean> activeList, Onclick onclick) {
        super(layoutResId, activeList);
        this.activesBean = activeList;
        this.onclick = onclick;
        this.flag = flag;
        this.enjoyProduct = enjoyProduct;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductNormalModel.DataBean.ListBean item) {
        tv_desc = helper.getView(R.id.tv_desc);
        tv_price = helper.getView(R.id.tv_price);
        iv_pic = helper.getView(R.id.iv_pic);
        iv_flag = helper.getView(R.id.iv_flag);
        iv_add = helper.getView(R.id.iv_add);
        rl_group = helper.getView(R.id.rl_group);
        tv_sale = helper.getView(R.id.tv_sale);
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_pic);
        helper.setText(R.id.tv_name,item.getProductName());
        if(flag.equals("hot")&&!item.getSalesVolume().equals("")) {
            tv_sale.setVisibility(View.VISIBLE);
            tv_sale.setText(item.getSalesVolume());
            tv_sale.setBackgroundResource(R.drawable.shape_orange);
        }else {
            tv_sale.setVisibility(View.GONE);

        }

        if(flag.equals("common")) {
            tv_sale.setVisibility(View.GONE);
            tv_sale.setText(item.getSalesVolume());
            iv_flag.setVisibility(View.VISIBLE);

            Glide.with(mContext).load(item.getTypeUrl()).into(iv_flag);
        }else {
            iv_flag.setVisibility(View.GONE);
        }

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

        if(flag.equals("reduce")&&item.getDeductAmount().equals("")) {
            tv_sale.setVisibility(View.GONE);

        }else if(flag.equals("reduce")&&!item.getDeductAmount().equals("")){
            tv_sale.setText(item.getDeductAmount()+"");
            tv_sale.setVisibility(View.VISIBLE);
            tv_sale.setBackgroundResource(R.drawable.shape_orange);
        }

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
                            hotDialog = new HotDialog(mContext,item.getProductId(),item);
                            hotDialog.show();
                        }
                    }else {
                        onclick.tipClick();
                    }

                }
            }
        });
    }

    public interface Onclick {
        void addDialog();
        void tipClick();
    }

}
