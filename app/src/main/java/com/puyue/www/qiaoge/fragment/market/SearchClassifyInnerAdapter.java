package com.puyue.www.qiaoge.fragment.market;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.MarketRightModel;

import java.util.List;

public class SearchClassifyInnerAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean,BaseViewHolder> {

    private ImageView iv_cut;
    private ImageView iv_add;
    OnAddCarListener onAddCarListener;
    int productId;
    private TextView tv_price;
    public SearchClassifyInnerAdapter(int productId,int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean> data,OnAddCarListener onAddCarListener) {
        super(layoutResId, data);
        this.productId = productId;
        this.onAddCarListener = onAddCarListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean item) {
        tv_price = helper.getView(R.id.tv_price);
        tv_price.setText(item.getPrice());
        helper.setText(R.id.tv_unit,item.getProductUnit()+"");
        helper.setText(R.id.tv_old_price,item.getOldPrice());


        TextView tv_num = helper.getView(R.id.tv_num);
        iv_cut = helper.getView(R.id.iv_cut);
        iv_add = helper.getView(R.id.iv_add);

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(tv_num.getText().toString());
                num++;
                tv_num.setText(num+"");
                onAddCarListener.addCar(helper.getLayoutPosition(),num,item.getPriceId(),productId);

            }
        });

        iv_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(tv_num.getText().toString());
                if(num>0) {
                    num--;
                    tv_num.setText(num+"");
                }
            }
        });

    }

    public interface OnAddCarListener {
        void addCar(int pos, int num, int id, int businessId);

    }
}
