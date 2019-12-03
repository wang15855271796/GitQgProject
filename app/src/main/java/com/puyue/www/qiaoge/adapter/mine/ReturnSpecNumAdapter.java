package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/23
 */
public class ReturnSpecNumAdapter extends BaseQuickAdapter<ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean.ReturnUnitsBean, BaseViewHolder> {

    private TextView mTvSpecNum;


    public ReturnSpecNumAdapter( int layoutResId, @Nullable List<ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean.ReturnUnitsBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnOrderDetailModel.DataBean.ProductsBean.DetailsBean.ReturnUnitsBean item) {

        mTvSpecNum = helper.getView(R.id.tv_spec_num);
        mTvSpecNum.setText(item.getUnitName());


    }
}
