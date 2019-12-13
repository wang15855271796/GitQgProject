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
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.cart.CartAdapter;
import com.puyue.www.qiaoge.adapter.cart.ItemChooseAdapter;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.ChoiceSpecModel;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.FlowLayout;

import org.greenrobot.eventbus.EventBus;
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
 * Created by ${王涛} on 2019/10/9
 */
public class ChooseDialog extends Dialog implements View.OnClickListener {

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
    private ChooseSpecAdapters chooseSpecAdapter;
    public ChooseDialog(Context context,int productId) {
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
                            Log.d("duoguigemingcheng...",productName);
                            tv_price.setText(model.getData().getMinMaxPrice());
                            salesVolume = model.getData().getSalesVolume();
                            tv_sale.setText(salesVolume);
                            Glide.with(context).load(model.getData().getDefaultPic()).into(iv_head);
                            tv_desc.setText(model.getData().getSpecialOffer());
                            prodSpecs = model.getData().getProdSpecs();
                            int productId1 = prodSpecs.get(0).getProductId();
                            exchangeList(productId1);


                            fl_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    chooseSpecAdapter.selectPosition(position);
                                    int productId2 = prodSpecs.get(position).getProductId();
//                                    exchangeList(productId2);
                                    GetProductDetailAPI.getExchangeList(context,productId2,1)
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
                                                    ItemChooseAdapter itemChooseAdapter = new ItemChooseAdapter(1, exchangeProductModel.getData().getProdSpecs().get(position).getProductId(),
                                                            R.layout.item_choose_content, exchangeProductModel.getData().getProdPrices());
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
                        ItemChooseAdapter itemChooseAdapter = new ItemChooseAdapter(1, productId, R.layout.item_choose_content, exchangeProductModel.getData().getProdPrices());
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
                EventBus.getDefault().post(new ReduceNumEvent());
                break;

            case R.id.tv_confirm:
                EventBus.getDefault().post(new ReduceNumEvent());
                dismiss();
                break;
                default:
                    break;

        }
    }

    public interface Onclick {
        void addDialog(int num);
    }
}
