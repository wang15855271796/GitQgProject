package com.puyue.www.qiaoge.adapter.market;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.cart.SearchInnerAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchInnersAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchResultAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchSpecxAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.view.ExpandLayout;
import com.puyue.www.qiaoge.view.FlowLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/19.
 * 分类右侧Adpater
 */

public class MarketGoodsAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean, BaseViewHolder> {

    private ImageView iv_head;
    private FlowLayout fl_container;
    private LinearLayout ll_group;
    private ImageView iv_type;
    int FirstId;
    Onclick onclick;
    private SearchInnersAdapter itemChooseAdapter;

    public MarketGoodsAdapter(int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> data, int FirstId, Onclick onclick) {
        super(layoutResId, data);
        this.FirstId = FirstId;
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean item) {
        int businessType = item.getBusinessType();
        LinearLayout ll = helper.getView(R.id.ll);
        ImageView iv_no_data = helper.getView(R.id.iv_no_data);
        iv_type = helper.getView(R.id.iv_type);
        if(item.getFlag()==0) {
            Glide.with(mContext).load(item.getTypeUrl()).into(iv_no_data);
            iv_no_data.setVisibility(View.VISIBLE);
            iv_type.setVisibility(View.GONE);
        }else {
            Glide.with(mContext).load(item.getTypeUrl()).into(iv_type);
            iv_type.setVisibility(View.VISIBLE);
            iv_no_data.setVisibility(View.GONE);
        }
        RelativeLayout rl_spec = helper.getView(R.id.rl_spec);
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        helper.setText(R.id.tv_spec,"规格："+item.getSpec());
        ll_group = helper.getView(R.id.ll_group);
        ll_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(businessType==11) {
                    Intent intent = new Intent(mContext,SpecialGoodDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, item.getActiveId());
                    Log.d("woshishuju.....",item.getActiveId()+"");
                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext,CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, item.getProductMainId());
                    mContext.startActivity(intent);
                }
            }
        });

        ll.setVisibility(View.GONE);
        fl_container = helper.getView(R.id.fl_container);
        MarketSpecAdapter marketSpecAdapter = new MarketSpecAdapter(mContext,item.getProdSpecs());
        fl_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                marketSpecAdapter.selectPosition(position);
                int productIds = item.getProdSpecs().get(position).getProductId();

                GetProductDetailAPI.getExchangeList(mContext,productIds,businessType)
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
                                if(businessType==11) {
                                    itemChooseAdapter = new SearchInnersAdapter(item.getBusinessType(),exchangeProductModel.getData().getActiveId(),
                                            R.layout.item_choose_contents,exchangeProductModel.getData().getProdPrices());
                                }else {
                                    itemChooseAdapter = new SearchInnersAdapter(item.getBusinessType(),item.getProdSpecs().get(position).getProductId(),
                                            R.layout.item_choose_contents,exchangeProductModel.getData().getProdPrices());
                                }

                                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                                recyclerView.setAdapter(itemChooseAdapter);

                            }
                        });
            }
        });

        fl_container.setAdapter(marketSpecAdapter);
        helper.setText(R.id.tv_name,item.getProductName());

        helper.setText(R.id.tv_sale,item.getSalesVolume());
        helper.setText(R.id.tv_price,item.getMinMaxPrice());
        helper.setText(R.id.tv_desc,item.getSpecialOffer());
        helper.setText(R.id.tv_stock,item.getInventory());
        helper.setText(R.id.tv_stock_total,item.getInventory());
        int productId = item.getProdSpecs().get(0).getProductId();
        int activeId = item.getActiveId();

        MarketInnerAdapter marketInnerAdapter = new MarketInnerAdapter(activeId,item.getBusinessType(),productId,R.layout.item_choose_contents,item.getProdPrices());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(marketInnerAdapter);
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
        tv_choose_spec.setText("选规格");
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
