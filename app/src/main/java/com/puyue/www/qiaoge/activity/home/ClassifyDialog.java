package com.puyue.www.qiaoge.activity.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.adapter.cart.ItemChooseAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchInnersAdapter;
import com.puyue.www.qiaoge.adapter.market.SpecAdapter;
import com.puyue.www.qiaoge.api.cart.GetCartNumAPI;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.event.GoToCartFragmentEvent;
import com.puyue.www.qiaoge.event.UpDateNumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.FlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/12/11
 * 首页分类精品弹窗
 */
public class ClassifyDialog extends Dialog implements View.OnClickListener {
    Context context;
    public View view;
    public Unbinder binder;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sale)
    TextView tv_sale;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.tv_stock)
    TextView tv_stock;
    @BindView(R.id.fl_container)
    FlowLayout fl_container;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.iv_cart)
    ImageView iv_cart;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_free_desc)
    TextView tv_free_desc;
    @BindView(R.id.tv_price_total)
    TextView tv_price_total;
    private SpecAdapter specAdapter;
    MarketRightModel.DataBean.ProdClassifyBean.ListBean listBean;
    int pos = 0;
    private ItemChooseAdapter itemChooseAdapter;
    private SelectionInnerAdapter selectionInnerAdapter;
    private SelectionSpecAdapter searchSpecAdapter;

    public ClassifyDialog(Context mContext, MarketRightModel.DataBean.ProdClassifyBean.ListBean item) {
        super(mContext, R.style.dialog);
        this.context = mContext;
        this.listBean = item;
        exchangeList(listBean.getProductId());
        init();
    }

    @Override
    public void show() {
        super.show();
        EventBus.getDefault().register(this);
    }

    @Override
    public void cancel() {
        super.cancel();
        EventBus.getDefault().unregister(this);
    }

    //初始化布局
    private void init() {
        if(view == null) {
            view = View.inflate(context, R.layout.dialog_choice, null);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            binder = ButterKnife.bind(this, view);
            setContentView(view);

            getWindow().setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = Utils.getScreenWidth(context);
            getWindow().setAttributes(attributes);
        }
        iv_close.setOnClickListener(this);
        iv_cart.setOnClickListener(this);


        List<MarketRightModel.DataBean.ProdClassifyBean.ListBean.ProdSpecsBean> prodSpecs = listBean.getProdSpecs();

        //切换规格
        fl_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                searchSpecAdapter.selectPosition(position);
                int productId = prodSpecs.get(position).getProductId();
                exchangeList(productId);
            }
        });

        searchSpecAdapter = new SelectionSpecAdapter(context,listBean.getProdSpecs());
        fl_container.setAdapter(searchSpecAdapter);
    }

    /**
     * 切换规格
     * @param productId
     */
    private void exchangeList(int productId) {
        GetProductDetailAPI.getExchangeList(context,productId,1)
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

                        SearchInnersAdapter itemChooseAdapter = new SearchInnersAdapter(1,exchangeProductModel.getData().getProdSpecs().get(pos).getProductId(),
                                R.layout.item_choose_content,
                                exchangeProductModel.getData().getProdPrices());

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(itemChooseAdapter);

                        tv_sale.setText(exchangeProductModel.getData().getSalesVolume());
                        tv_price.setText(exchangeProductModel.getData().getMinMaxPrice()+"");
                        tv_desc.setText(exchangeProductModel.getData().getSpecialOffer());
                        tv_stock.setText(exchangeProductModel.getData().getInventory());
                        Glide.with(context).load(exchangeProductModel.getData().getDefaultPic()).into(iv_head);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;

            case R.id.iv_cart:

                context.startActivity(new Intent(context, HomeActivity.class));
                EventBus.getDefault().post(new GoToCartFragmentEvent());
                dismiss();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTotal(UpDateNumEvent upDateNumEvent) {
        getCartNum();
    }
    /**
     * 获取角标数据
     */
    private void getCartNum() {
        GetCartNumAPI.requestData(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCartNumModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetCartNumModel getCartNumModel) {
                        if (getCartNumModel.isSuccess()) {
                            if (Integer.valueOf(getCartNumModel.getData().getNum()) > 0) {
                                tv_num.setVisibility(View.VISIBLE);
                                tv_num.setText(getCartNumModel.getData().getNum());
                                tv_price_total.setText(getCartNumModel.getData().getTotalPrice());
                                tv_free_desc.setText("满"+getCartNumModel.getData().getSendAmount()+"元免配送费");
                            } else {
                                tv_free_desc.setText("未选购商品");
                                tv_num.setVisibility(View.GONE);
                                tv_price_total.setText(getCartNumModel.getData().getTotalPrice());
                            }
                        } else {
                            AppHelper.showMsg(context, getCartNumModel.getMessage());
                        }
                    }
                });
    }


}
