package com.puyue.www.qiaoge.adapter.mine;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class MyOrderListAdapter extends BaseQuickAdapter<MyOrdersModel.DataBean.ListBean.ProductVOListBean, BaseViewHolder> {

    public MyOrderListAdapter(int layoutResId, @Nullable List<MyOrdersModel.DataBean.ListBean.ProductVOListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyOrdersModel.DataBean.ListBean.ProductVOListBean item) {
        if (StringHelper.notEmptyAndNull(item.picUrl)) {
            GlideModel.displayTransForms(mContext,item.picUrl,helper.getView(R.id.iv_item_my_order_img));
           // Glide.with(mContext).load(item.picUrl).transform(new GlideRoundTransform(mContext, 3)).into((ImageView) helper.getView(R.id.iv_item_my_order_img));
        }
        helper.setText(R.id.tv_item_my_order_title, item.name);
//        这个规格,要根据不同的产品类型来判断是否要显示
        helper.setText(R.id.tv_item_my_order_standard, item.spec);
//        helper.setText(R.id.tv_item_my_order_price, item.price);
//        helper.setText(R.id.tv_item_my_order_volume, item.volume);
//        helper.setText(R.id.tv_item_my_order_stock, item.stock);
    }
}
