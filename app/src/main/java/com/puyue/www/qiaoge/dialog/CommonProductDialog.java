package com.puyue.www.qiaoge.dialog;

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
import com.puyue.www.qiaoge.adapter.CommonProductItemAdapter;
import com.puyue.www.qiaoge.adapter.cart.ItemChooseAdapter;
import com.puyue.www.qiaoge.api.cart.GetCartNumAPI;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.event.GoToCartFragmentEvent;
import com.puyue.www.qiaoge.event.UpDateNumEvent;
import com.puyue.www.qiaoge.event.UpDateNumEvent4;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.utils.ToastUtil;
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
 * Created by ${王涛} on 2020/6/2
 */
public class CommonProductDialog extends Dialog implements View.OnClickListener{
    Context context;
    public View view;
    String productName;
    public Unbinder binder;
    @BindView(R.id.iv_cart)
    ImageView iv_cart;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_sale)
    TextView tv_sale;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.fl_container)
    FlowLayout fl_container;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.tv_stock)
    TextView tv_stock;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_free_desc)
    TextView tv_free_desc;
    public String salesVolume;
    int productId;
    @BindView(R.id.tv_price_total)
    TextView tv_price_total;
    public List<GetProductDetailModel.DataBean.ProdSpecsBean> prodSpecs;
    private ChooseSpecAdapters chooseSpecAdapter;
    ProductNormalModel.DataBean.ListBean item;
    int pos = 0;
    private ExchangeProductModel exchangeProductModel1s;

    public CommonProductDialog(Context context,int productId,ProductNormalModel.DataBean.ListBean item) {
        super(context, R.style.dialog);
        this.context = context;
        this.productId = productId;
        this.item = item;
        init();
        exchangeList(productId);
        getCartNum();
    }


    @Override
    public void show() {
        super.show();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }


    @Override
    public void cancel() {
        super.cancel();
        EventBus.getDefault().unregister(this);
    }

    public void init() {
        view = View.inflate(context, R.layout.dialog_choice, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        binder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = Utils.getScreenWidth(context);
        getWindow().setAttributes(attributes);
        iv_close.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
        iv_cart.setOnClickListener(this);


        fl_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                chooseSpecAdapter.selectPosition(position);
                int productId = exchangeProductModel1s.getData().getProdSpecs().get(position).getProductId();
                exchangeList(productId);
            }
        });
        chooseSpecAdapter = new ChooseSpecAdapters(context,item.getProdSpecs());
        fl_container.setAdapter(chooseSpecAdapter);

    }

    /**
     * 切换商品规格列表
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
                        if(exchangeProductModel.isSuccess()) {
                            if(exchangeProductModel.getData()!=null) {
                                exchangeProductModel1s = exchangeProductModel;
                                tv_name.setText(exchangeProductModel.getData().getProductName());
                                tv_sale.setText(exchangeProductModel.getData().getSalesVolume());
                                tv_price.setText(exchangeProductModel.getData().getMinMaxPrice()+"");
                                tv_desc.setText(exchangeProductModel.getData().getSpecialOffer());
                                tv_stock.setText(exchangeProductModel.getData().getInventory());
                                Glide.with(context).load(exchangeProductModel.getData().getDefaultPic()).into(iv_head);
                                CommonProductItemAdapter commonProductItemAdapter = new CommonProductItemAdapter(1, productId, R.layout.item_choose_content, exchangeProductModel.getData().getProdPrices());
                                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                                recyclerView.setAdapter(commonProductItemAdapter);
                            }
                        }else {
                            ToastUtil.showSuccessMsg(context,exchangeProductModel.getMessage());
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                EventBus.getDefault().post(new ReduceNumEvent());
                EventBus.getDefault().unregister(this);
                break;

            case R.id.tv_confirm:
                EventBus.getDefault().post(new ReduceNumEvent());
                dismiss();
                break;
            case R.id.iv_cart:
                context.startActivity(new Intent(context, HomeActivity.class));
                EventBus.getDefault().post(new GoToCartFragmentEvent());
                dismiss();
                EventBus.getDefault().unregister(this);
                break;
            default:
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTotal(UpDateNumEvent4 upDateNumEvent) {
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

    public interface Onclick {
        void addDialog(int num);
    }
}
