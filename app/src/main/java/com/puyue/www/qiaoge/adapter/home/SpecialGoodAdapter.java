package com.puyue.www.qiaoge.adapter.home;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.SpecialMoreGoodModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/11
 */
public class SpecialGoodAdapter extends BaseQuickAdapter<SpecialMoreGoodModel.DataBean, BaseViewHolder> {

    private TextView tvSpecialPrice;
    private TextView tvSpecialOldPrice;
    private TextView tvSpecialAmount;
    private TextView tvSpecialSpc;
    private TextView tvSpecialTitle;
    private ImageView ivSpecial;
    private ImageView ivAdd;
    private ImageView ivSoldOut;
    private Onclick onclick;
    private ImageView ivSoldoutLeft;
    private TextView ivBg;

    public SpecialGoodAdapter(int layoutResId, @Nullable List<SpecialMoreGoodModel.DataBean> data, Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialMoreGoodModel.DataBean item) {
        helper.setText(R.id.tv_item_special_title, item.getTitle());
        helper.setText(R.id.tv_item_special_amount, item.getSalesVolume());
        helper.setText(R.id.tv_special_price, item.getPrice());
        helper.setText(R.id.tv_item_special_specification, "规格：" + item.getSpec());
        tvSpecialOldPrice = helper.getView(R.id.tv_old_price);
        tvSpecialOldPrice.setText(item.getOldPrice());
        ivSpecial = helper.getView(R.id.iv_item_special_img);
        GlideModel.disPlayError(mContext,item.getPic(),ivSpecial);
        ivSoldOut = helper.getView(R.id.sold_out);
        ivAdd = helper.getView(R.id.addCar);
        ivBg = helper.getView(R.id.tv_bg);
        ivSoldoutLeft = helper.getView(R.id.iv_sold);
        tvSpecialOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线

        if (item.getSoldOut() == 1) {
            ivSoldOut.setVisibility(View.VISIBLE);
            ivAdd.setVisibility(View.GONE);
            ivSoldoutLeft.setVisibility(View.VISIBLE);
            GlideModel.displayTransForms(mContext,item.getFlagUrl(),ivSoldoutLeft);
            ivBg.setVisibility(View.GONE);
            ivBg.getBackground().setAlpha(75);
        } else if (item.getSoldOut() == 0) {
            ivSoldOut.setVisibility(View.GONE);
            ivAdd.setVisibility(View.VISIBLE);
            ivSoldoutLeft.setVisibility(View.GONE);
            ivBg.setVisibility(View.GONE);
        }

        helper.getView(R.id.ll_coupon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.getActiveId());
                mContext.startActivity(intent);
            }
        });


        ivAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                onclick.addCarOnclick(helper.getLayoutPosition());
            }
        });


    }

    public interface Onclick {
        void addCarOnclick(int position);
    }
}
