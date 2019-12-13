package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.home.CommonAdapter;
import com.puyue.www.qiaoge.adapter.home.NewPriceAdapter;
import com.puyue.www.qiaoge.adapter.home.NewSpecAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchInnersAdapter;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.view.ExpandLayout;
import com.puyue.www.qiaoge.view.FlowLayout;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/15
 */
public class SelectionAdapter extends BaseQuickAdapter<MarketRightModel.DataBean.ProdClassifyBean.ListBean,BaseViewHolder> {

    private ImageView iv_head;
    private FlowLayout fl_container;
    private TextView tv_stock;
    private SelectionInnerAdapter selectionInnerAdapter;
    private LinearLayout ll_group;
    private ImageView iv_type;
    Onclick onclick;
    ClassifyDialog classifyDialog;
    public SelectionAdapter(int layoutResId, @Nullable List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> data,Onclick onclick) {
        super(layoutResId, data);
        this.onclick = onclick;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketRightModel.DataBean.ProdClassifyBean.ListBean item) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
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

        fl_container = helper.getView(R.id.fl_container);
        SelectionSpecAdapter searchSpecAdapter = new SelectionSpecAdapter(mContext,item.getProdSpecs());
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
        selectionInnerAdapter = new SelectionInnerAdapter(productId,R.layout.item_choose_content,item.getProdPrices());

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(selectionInnerAdapter);

        TextView tv_choose_spec = helper.getView(R.id.tv_choose_spec);
        tv_choose_spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null) {
                    onclick.addDialog();
                }
                classifyDialog = new ClassifyDialog(mContext,item);
                classifyDialog.show();
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
