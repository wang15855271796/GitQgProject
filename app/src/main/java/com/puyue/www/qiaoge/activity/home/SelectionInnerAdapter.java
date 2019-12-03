package com.puyue.www.qiaoge.activity.home;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.cart.AddMountChangeTwoAPI;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.model.cart.AddCartGoodModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/15
 */
public class SelectionInnerAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean,BaseViewHolder> {


    private TextView tv_price;
    private ImageView iv_cut;
    private ImageView iv_add;
    int productId;
    public SelectionInnerAdapter(int productId,int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean> data) {
        super(layoutResId, data);
        this.productId = productId;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdPricesBean item) {
        tv_price = helper.getView(R.id.tv_price);
        tv_price.setText(item.getPrice());
        helper.setText(R.id.tv_unit, item.getUnitDesc() + "");
        helper.setText(R.id.tv_old_price, item.getOldPrice());

        TextView tv_num = helper.getView(R.id.tv_num);
        tv_num.setText(item.getCartNum()+"");
        iv_cut = helper.getView(R.id.iv_cut);
        iv_add = helper.getView(R.id.iv_add);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = Integer.parseInt(tv_num.getText().toString());
                num++;
                addCart(num,item.getPriceId(),productId,1,tv_num);

            }
        });

        iv_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(tv_num.getText().toString());
                if (num > 0) {
                    num--;
                    tv_num.setText(num + "");
                }
            }
        });
    }

    /**
     * 添加购物车
     * @param num
     * @param tv_num
     */
    private void addCart(int num, int id, int businessId, int productType, TextView tv_num) {
        AddMountChangeTwoAPI.AddMountChangeService(mContext,productType,businessId,num,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartGoodModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCartGoodModel addMountReduceModel) {
                        if (addMountReduceModel.isSuccess()) {
                            tv_num.setText(num + "");
                            ToastUtils.showShortToast(mContext,"刷新购物车成功");

                        } else {

                            ToastUtils.showShortToast(mContext,addMountReduceModel.getMessage());
                        }
                    }
                });
    }
}
