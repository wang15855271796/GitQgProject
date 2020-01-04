package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.puyue.www.qiaoge.adapter.cart.ItemChooseAdapter;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.model.home.ChoiceSpecModel;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.FlowLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/22
 */
public class ChooseDialogs extends Dialog implements View.OnClickListener {
    Context context;
    public View view;
    String productName;
    public Unbinder binder;
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
    public String salesVolume;
    int productId;
    public List<GetProductDetailModel.DataBean.ProdSpecsBean> prodSpecs;
    private List<GetProductDetailModel.DataBean.ProdPricesBean> prodPrices;
    private ChooseSpecAdapters chooseSpecAdapter;

    public ChooseDialogs(Context context,int productId) {
        super(context, R.style.dialog);
        this.context = context;
        this.productId = productId;
        init();
        getDetailSpec(productId);
    }

    /***
     * 获取多规格详情
     */
    private void getDetailSpec(int productId) {
        GetProductDetailAPI.requestData(context, productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetProductDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetProductDetailModel model) {
                        if (model.isSuccess()) {
                            productName = model.getData().getProductName();
                            tv_name.setText(productName);
                            tv_price.setText(model.getData().getMinMaxPrice());
                            salesVolume = model.getData().getSalesVolume();
                            tv_sale.setText(salesVolume);
                            Glide.with(context).load(model.getData().getDefaultPic()).into(iv_head);
                            tv_desc.setText(model.getData().getSpecialOffer());
                            prodPrices = model.getData().getProdPrices();
                            prodSpecs = model.getData().getProdSpecs();
                            int productId1 = prodSpecs.get(0).getProductId();
                            exchangeList(productId1);


                            fl_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    int productId2 = prodSpecs.get(position).getProductId();
                                    exchangeList(productId2);
                                    chooseSpecAdapter.selectPosition(position);

                                }
                            });

                            chooseSpecAdapter = new ChooseSpecAdapters(context,prodSpecs);
                            fl_container.setAdapter(chooseSpecAdapter);

                        } else {

                        }
                    }
                });
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

                    }
                });
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;

            case R.id.tv_confirm:

                break;
            default:
                break;

        }
    }
    public interface Onclick {
        void addDialog(int num);
    }
}
