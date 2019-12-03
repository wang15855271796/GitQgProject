package com.puyue.www.qiaoge.fragment.market;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.home.ChooseCartPriceAdapter;
import com.puyue.www.qiaoge.fragment.cart.UpdateEvent;
import com.puyue.www.qiaoge.model.cart.CartsListModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/22
 */
public class CartSpecAdapter extends BaseQuickAdapter<CartsListModel.DataBean.ValidListBean.SpecProductListBean,BaseViewHolder> {

    private RecyclerView recyclerView_price;
    IcartSpecListener icartSpecListener;
    List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> data;
    CartsListModel.DataBean.ValidListBean items;
    TestAdapter testAdapter;
    List<CartsListModel.DataBean.ValidListBean> validListBeans;
    int businessType;
    int businessId;
    TestAdapter.IProductSelectCallback iProductSelectCallback;
    public CartSpecAdapter(TestAdapter.IProductSelectCallback iProductSelectCallback, List<CartsListModel.DataBean.ValidListBean> validListBeans, int layoutResId, @Nullable List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> data, CartsListModel.DataBean.ValidListBean item,
                           TestAdapter testAdapter, int businessType, int businessId) {
        super(layoutResId, data);
        this.data = data;
        this.validListBeans = validListBeans;
        this.iProductSelectCallback = iProductSelectCallback;
        this.icartSpecListener = icartSpecListener;
        this.items = item;
        this.testAdapter = testAdapter;
        this.businessType = businessType;
        this.businessId = businessId;
    }

    @Override
    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.ValidListBean.SpecProductListBean item) {
        CheckBox cb_item_in = helper.getView(R.id.cb_item_in);
        cb_item_in.setOnCheckedChangeListener(null);
        //读取实体内存储的选中状态
//        cb_item_in.setChecked(isCheck.get(helper.getAdapterPosition()));
        cb_item_in.setChecked(item.isSelected());
        helper.setText(R.id.tv_spec,"规格："+item.getSpec());
        helper.setText(R.id.tv_stock,item.getInventory());
        recyclerView_price = helper.getView(R.id.recyclerView_price);
        ChooseCartPriceAdapter chooseCartPriceAdapter = new ChooseCartPriceAdapter(R.layout.item_choose_content,item.getProductDescVOList(),
                testAdapter,item.getBusinessId(),businessType);
        recyclerView_price.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView_price.setAdapter(chooseCartPriceAdapter);

        cb_item_in.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //更改item选中状态同时进行实体内的选中状态改变
                item.setSelected(isChecked);
                boolean noSelect = false;
                //内层item选中状态改变后要遍历判断是否全选，以改变外层item的选中状态
                for (CartsListModel.DataBean.ValidListBean.SpecProductListBean validListBean : data) {
                    if (!validListBean.isSelected()) {
                        noSelect = true;
                    }
                }
                if (!noSelect) {
                    items.setSelected(!noSelect);
                    testAdapter.notifyDataSetChanged();
                } else {
                    items.setSelected(!noSelect);
                    testAdapter.notifyDataSetChanged();
                }

                EventBus.getDefault().post(new UpdateEvent(testAdapter.getAllPrice()));
                iProductSelectCallback.update(validListBeans);
                Log.d("wuouwowdfuduf....",validListBeans+"");
            }
        });





    }

    public interface IProductSelectCallback {
        void update(List<CartsListModel.DataBean.ValidListBean> data);
    }


    public interface IcartSpecListener {
        void updateSpec(List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> data);
    }
}
