package com.puyue.www.qiaoge.adapter.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.adapter.market.ChoosesDialog;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/27
 * 常用清单adapter
 */
public class CommonsAdapter extends BaseQuickAdapter<ProductNormalModel.DataBean.ListBean,BaseViewHolder> {

    private ImageView iv_pic;
    List<ProductNormalModel.DataBean.ListBean> activesBean;
    private TextView iv_add;
    Onclick onclick;
    private CommonDialog commonDialog;
    private LinearLayout ll_group;

    public CommonsAdapter(int layoutResId, @Nullable List<ProductNormalModel.DataBean.ListBean> activeList, Onclick onclick) {
        super(layoutResId, activeList);
        this.activesBean = activeList;
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductNormalModel.DataBean.ListBean item) {
        iv_pic = helper.getView(R.id.iv_pic);
        iv_add = helper.getView(R.id.iv_add);
        ll_group = helper.getView(R.id.ll_group);
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_pic);
        helper.setText(R.id.tv_name,item.getProductName());
        helper.setText(R.id.tv_price,item.getMinMaxPrice());

        ll_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID,item.getProductId());
                mContext.startActivity(intent);
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.addDialog();
                }

                commonDialog = new CommonDialog(mContext,item);
                commonDialog.show();

            }
        });
    }

    public interface Onclick {
        void addDialog();
    }

}
