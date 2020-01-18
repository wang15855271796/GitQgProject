package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/20
 */
public class ReturnSpecAdapter extends BaseQuickAdapter<ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean, BaseViewHolder> {


    private TextView mTvSpec;
    private TextView mTvPrice;
    private OnItemClick click;
    TextView tv_old_price;
    public OnItemClick getClick() {
        return click;
    }

    public void setClick(OnItemClick click) {
        this.click = click;
    }

    public ReturnSpecAdapter(int layoutResId, @Nullable List<ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean item) {
        helper.setIsRecyclable(false);
        //tv_coupon_price
        tv_old_price = helper.getView(R.id.tv_old_price);
        tv_old_price.getPaint().setAntiAlias(true);//抗锯齿
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mTvPrice = helper.getView(R.id.tv_return_price);
        mTvSpec = helper.getView(R.id.tv_return_spec);
        mTvPrice.setText(item.getAfterPrice());
        tv_old_price.setText(item.getTotalPrice());
        mTvSpec.setText(item.getDesc());
        helper.getView(R.id.tv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.delete();
            }
        });
    }

    public interface OnItemClick {
        void delete();
    }
}

