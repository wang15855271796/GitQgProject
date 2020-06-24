package com.puyue.www.qiaoge.fragment.market;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpikeGoodsDetailsActivity;
import com.puyue.www.qiaoge.activity.home.TeamGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.fragment.cart.CartFragment;
import com.puyue.www.qiaoge.fragment.cart.UpdateEvent;
import com.puyue.www.qiaoge.model.cart.CartsListModel;
import com.puyue.www.qiaoge.view.Arith;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.List;

public class TestAdapter extends BaseQuickAdapter<CartsListModel.DataBean.ValidListBean,BaseViewHolder> {
    private RecyclerView recyclerView;
    public List<CartsListModel.DataBean.ValidListBean> data;
    private IProductSelectCallback iProductSelectCallback;
    private OnResfreshListener mOnResfreshListener;
    private RecyclerView rv_given;

    public TestAdapter(int layoutResId, @Nullable List<CartsListModel.DataBean.ValidListBean> data,IProductSelectCallback iProductSelectCallback) {
        super(layoutResId, data);
        this.data = data;
        this.iProductSelectCallback = iProductSelectCallback;

    }

    @Override
    protected void convert(BaseViewHolder helper, CartsListModel.DataBean.ValidListBean item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        LinearLayout ll_cart = helper.getView(R.id.ll_cart);
        ll_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新品
                if(item.getBusinessType()==1) {
                    Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
                    mContext.startActivity(intent);
                }else if(item.getBusinessType()==2) {
                    //活动详情页,秒杀详情
                    Intent intent = new Intent(mContext, SpikeGoodsDetailsActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, item.getBusinessId());
                    mContext.startActivity(intent);
                }else if (item.getBusinessType() == 3) {
//                        //活动,团购详情
                    Intent intent = new Intent(mContext, SpecialGoodDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, item.getBusinessId());
                    mContext.startActivity(intent);

                }else if (item.getBusinessType() == 11) {
//                        //设备详情页
                        Intent intent = new Intent(mContext, SpecialGoodDetailActivity.class);
                        intent.putExtra(AppConstant.ACTIVEID, item.getBusinessId());
                        mContext.startActivity(intent);
                    }
            }
        });


        Glide.with(mContext).load(item.getFlagUrl()).into(iv_icon);
        recyclerView = helper.getView(R.id.recyclerView);
        rv_given = helper.getView(R.id.rv_given);
        rv_given.setLayoutManager(new LinearLayoutManager(mContext));
        GivenAdapter givenAdapter = new GivenAdapter(R.layout.item_given,item.getSpecProductList().get(0).getAdditionProductVOList());
        rv_given.setAdapter(givenAdapter);
        CheckBox cb_item_out = helper.getView(R.id.cb_item_out);
        helper.setText(R.id.tv_title,item.getProductName());
        ImageView iv_head = helper.getView(R.id.iv_head);
        Glide.with(mContext).load(item.getDefaultPic()).into(iv_head);

        CartSpecAdapter cartSpecAdapter = new CartSpecAdapter(iProductSelectCallback,data,R.layout.item_cart_spec, item.getSpecProductList(),item,
                this,item.getBusinessType(),item.getBusinessId());
        cb_item_out.setOnCheckedChangeListener(null);
        cb_item_out.setChecked(item.isSelected());
        if(mOnResfreshListener != null){
            boolean isSelect = false;
            for(int i = 0;i < data.size(); i++){
                if(!data.get(i).isSelected()){
                    isSelect = false;
                    break;
                }else{
                    isSelect = true;
                }
            }
            mOnResfreshListener.onResfresh(isSelect);
        }

        cb_item_out.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isPressed()) {
                    item.setSelected(isChecked);
                    for (CartsListModel.DataBean.ValidListBean.SpecProductListBean specProductList: item.getSpecProductList()) {
                        specProductList.setSelected(isChecked);
                        notifyDataSetChanged();

                    }
                }

                EventBus.getDefault().post(new UpdateEvent(getAllPrice()));
                iProductSelectCallback.update(data);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(cartSpecAdapter);


    }

    /**
     * 计算商品总价格
     * @return
     */
    public String getAllPrice() {
        BigDecimal allprice =new BigDecimal("0");
        if(data!=null){
            for (int i=0;i<data.size();i++){
                List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> specProductList = data.get(i).getSpecProductList();
                for (int y=0;y<specProductList.size();y++){
                    if(specProductList.get(y).isSelected()){
                        List<CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean> productDescVOList = specProductList.get(y).getProductDescVOList();
                        for (int j = 0; j <productDescVOList.size() ; j++) {
                            BigDecimal interestRate = new BigDecimal(productDescVOList.get(j).getProductNum()); //数量

                            double interest = Arith.mul(Double.parseDouble(productDescVOList.get(j).getPrice()), interestRate);
                            allprice=allprice.add(BigDecimal.valueOf(interest));

                        }
                    }
                }
            }
        }
        return allprice.toString();
    }


    //设置全选/全不选
    public void setAllselect(boolean b){
        for(int i=0;i<data.size();i++){
            data.get(i).setSelected(b);
            for (CartsListModel.DataBean.ValidListBean.SpecProductListBean specProductList : data.get(i).getSpecProductList()){
                specProductList.setSelected(b);
            }

        }
        notifyDataSetChanged();
        //发送 消息
        iProductSelectCallback.update(data);
        EventBus.getDefault().post(new UpdateEvent(getAllPrice()));
    }

    public interface IProductSelectCallback {
        void update(List<CartsListModel.DataBean.ValidListBean> data);
    }


    public interface OnResfreshListener{
        void onResfresh(boolean isSelect);
    }

    public void setResfreshListener(OnResfreshListener mOnResfreshListener){
        this.mOnResfreshListener = mOnResfreshListener;
    }

    public interface Onclick {

        void refreshCart();
    }

}
