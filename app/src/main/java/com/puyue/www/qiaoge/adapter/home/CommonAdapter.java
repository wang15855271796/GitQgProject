package com.puyue.www.qiaoge.adapter.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.cart.SearchInnerAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.view.ExpandLayout;
import com.puyue.www.qiaoge.view.FlowLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/5
 */
public class CommonAdapter extends BaseQuickAdapter<ProductNormalModel.DataBean.ListBean,BaseViewHolder> {

    private ImageView iv_head;
    private FlowLayout fl_container;
    private TextView tv_stock;
    private NewPriceAdapter searchInnerAdapter;
    private LinearLayout ll_group;
    private ImageView iv_type;
    Onclick onclick;
    public CommonAdapter(int layoutResId, @Nullable List<ProductNormalModel.DataBean.ListBean> data,Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(final BaseViewHolder helper, ProductNormalModel.DataBean.ListBean item) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        ImageView iv_no_data = helper.getView(R.id.iv_no_data);
        iv_type = helper.getView(R.id.iv_type);
        LinearLayout ll = helper.getView(R.id.ll);
        RelativeLayout rl_spec = helper.getView(R.id.rl_spec);
        if(item.getFlag()==0) {
            Glide.with(mContext).load(item.getTypeUrl()).into(iv_no_data);
            iv_no_data.setVisibility(View.VISIBLE);
            iv_type.setVisibility(View.GONE);
        }else {
            Glide.with(mContext).load(item.getTypeUrl()).into(iv_type);
            iv_type.setVisibility(View.VISIBLE);
            iv_no_data.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_spec,"规格："+item.getSpec());
        ll_group = helper.getView(R.id.ll_group);
        ll_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
                mContext.startActivity(intent);
            }
        });
        ll.setVisibility(View.GONE);
        fl_container = helper.getView(R.id.fl_container);
        NewSpecAdapter searchSpecAdapter = new NewSpecAdapter(mContext,item.getProdSpecs());
        fl_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchSpecAdapter.selectPosition(position);
                int productIds = item.getProdSpecs().get(position).getProductId();

                GetProductDetailAPI.getExchangeList(mContext,productIds,1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ExchangeProductModel>() {

                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ExchangeProductModel exchangeProductModel) {
                                helper.setText(R.id.tv_stock,exchangeProductModel.getData().getInventory());
                                helper.setText(R.id.tv_desc,exchangeProductModel.getData().getSpecialOffer());
                                SearchInnersAdapter itemChooseAdapter = new SearchInnersAdapter(1,exchangeProductModel.getData().getProdSpecs().get(position).getProductId(),
                                        R.layout.item_choose_content,
                                        exchangeProductModel.getData().getProdPrices());
                                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                                recyclerView.setAdapter(itemChooseAdapter);
                            }
                        });
            }
        });

        fl_container.setAdapter(searchSpecAdapter);
        helper.setText(R.id.tv_name,item.getProductName());
        helper.setText(R.id.tv_stock_total,item.getInventory());
        helper.setText(R.id.tv_sale,item.getSalesVolume());
        helper.setText(R.id.tv_price,item.getMinMaxPrice());
        helper.setText(R.id.tv_desc,item.getSpecialOffer());
        tv_stock = helper.getView(R.id.tv_stock);
        tv_stock.setText(item.getInventory());
        int productId = item.getProdSpecs().get(0).getProductId();
        searchInnerAdapter = new NewPriceAdapter(productId,R.layout.item_choose_content,item.getProdPrices());

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(searchInnerAdapter);

//        ExpandLayout expandLayout = helper.getView(R.id.expanded);
//        expandLayout.initExpand(false);
        TextView tv_choose_spec = helper.getView(R.id.tv_choose_spec);
        rl_spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.addDialog();
                }

                if(ll.getVisibility() == View.VISIBLE) {
                    ll.setVisibility(View.GONE);
                    tv_choose_spec.setText("选规格");

                }else {
                    ll.setVisibility(View.VISIBLE);
                    tv_choose_spec.setText("收起");
                }
            }
        });

        iv_head = helper.getView(R.id.iv_head);
        Glide.with(mContext)
                .load(item.getDefaultPic())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .apply(new RequestOptions().placeholder(iv_head.getDrawable()).skipMemoryCache(false).dontAnimate())
                .into(iv_head);

    }
    public interface Onclick {
        void addDialog();
    }
}
