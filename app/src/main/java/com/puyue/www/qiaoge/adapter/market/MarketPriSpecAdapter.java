package com.puyue.www.qiaoge.adapter.market;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.market.MarketGoodsModel;

import java.util.List;

/**
 * Created by ${王文博} on 2019/6/10
 */
public class MarketPriSpecAdapter extends BaseQuickAdapter<MarketGoodsModel.DataBean.ListBean.PriceList, BaseViewHolder> {

    private onClick onClick;

    public MarketPriSpecAdapter(int layoutResId, @Nullable List<MarketGoodsModel.DataBean.ListBean.PriceList> data,onClick onClick) {
        super(layoutResId, data);
        this.onClick =onClick;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketGoodsModel.DataBean.ListBean.PriceList item) {

        helper.setText(R.id.tv_item_market_price, "￥" + item.price);
        helper.setText(R.id.textSpec, "/" + item.unitName);

        helper.getView(R.id.addCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.addListCar(helper.getLayoutPosition());
            }
        });
    }



   public  interface  onClick{

        void addListCar(int pos);
   }

}
