package com.puyue.www.qiaoge.fragment.market;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.cart.CartsListModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/11/7
 */
public class GivenAdapter extends BaseQuickAdapter<CartsListModel.DataBean.ValidListBean.SpecProductListBean.AdditionProductVOList,BaseViewHolder> {

    public GivenAdapter(int layoutResId, @Nullable List<CartsListModel.DataBean.ValidListBean.SpecProductListBean.AdditionProductVOList> data) {
        super(layoutResId, data);

    }


    @Override
    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.ValidListBean.SpecProductListBean.AdditionProductVOList item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.getPicUrl()).into(iv_icon);
        ImageView iv_flag = helper.getView(R.id.iv_flag);
        Glide.with(mContext).load(item.getFlagUrl()).into(iv_flag);
        helper.setText(R.id.tv_title,item.getName());
        helper.setText(R.id.tv_spec,item.getSpec());
        helper.setText(R.id.tv_stock,item.getInventory());
        helper.setText(R.id.tv_given_num,item.getAdditionNum());

    }
}
