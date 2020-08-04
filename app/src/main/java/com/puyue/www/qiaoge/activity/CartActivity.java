package com.puyue.www.qiaoge.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.ConfirmNewOrderActivity;
import com.puyue.www.qiaoge.adapter.cart.CartUnableAdapter;
import com.puyue.www.qiaoge.api.cart.CartBalanceAPI;
import com.puyue.www.qiaoge.api.cart.CartListAPI;
import com.puyue.www.qiaoge.api.cart.DeleteCartAPI;
import com.puyue.www.qiaoge.api.home.CityChangeAPI;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.mine.order.CartGetReductDescAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.fragment.cart.CartFragment;
import com.puyue.www.qiaoge.fragment.cart.NumEvent;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.fragment.cart.UpdateEvent;
import com.puyue.www.qiaoge.fragment.home.CityEvent;
import com.puyue.www.qiaoge.fragment.home.MustAdapter;
import com.puyue.www.qiaoge.fragment.market.TestAdapter;
import com.puyue.www.qiaoge.helper.AlwaysMarqueeTextViewHelper;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.BigDecimalUtils;
import com.puyue.www.qiaoge.helper.CollapsingToolbarLayoutStateHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.IsShowModel;
import com.puyue.www.qiaoge.model.cart.CartActivityGoodsModel;
import com.puyue.www.qiaoge.model.cart.CartBalanceModel;
import com.puyue.www.qiaoge.model.cart.CartCommonGoodsModel;
import com.puyue.www.qiaoge.model.cart.CartsListModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.MustModel;
import com.puyue.www.qiaoge.model.mine.order.CartGetReductModel;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.Arith;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/9.
 */

public class CartActivity extends BaseSwipeActivity implements View.OnClickListener,TestAdapter.IProductSelectCallback{

    public Unbinder binder;
    @BindView(R.id.tv_delete)
    TextView tv_delete;
    @BindView(R.id.tv_clear)
    TextView tv_clear;
    @BindView(R.id.btn_sure)
    Button btn_sure;
    @BindView(R.id.cb_select_all)
    CheckBox cb_select_all;
    @BindView(R.id.rv_cart)
    RecyclerView mRv;
    @BindView(R.id.ll_select_all)
    LinearLayout ll_select_all;
    @BindView(R.id.linearLayoutButton)
    LinearLayout linearLayoutButton;
    @BindView(R.id.marqueeTextView)
    AlwaysMarqueeTextViewHelper marqueeTextView;
    @BindView(R.id.iv_clear)
    ImageView iv_clear;
    @BindView(R.id.tv_total_price)
    TextView tv_total_price;
    @BindView(R.id.ll_go_market)
    LinearLayout ll_go_market;
    @BindView(R.id.ll_service)
    LinearLayout ll_service;
    @BindView(R.id.tv_price_desc)
    TextView tv_price_desc;
    @BindView(R.id.rv_unable)
    RecyclerView rv_unable;
    boolean mSelect;
    @BindView(R.id.ll_NoData)
    LinearLayout ll_NoData;
    @BindView(R.id.rl_unable)
    RelativeLayout rl_unable;
    @BindView(R.id.imageGoBay)
    ImageView imageGoBay;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.rv_recommend)
    RecyclerView rv_recommend;
    @BindView(R.id.lav_activity_loading)
    AVLoadingIndicatorView lav_activity_loading;
    String cell;
    String enjoyProduct;
    //失效商品的cartId
    List<Integer> unCartsId = new ArrayList<>();
    //点击删除时的cartId存储集合
    List<Integer> cartsId = new ArrayList<>();
    private CartFragment.FragmentInteraction listterner;
    private CartFragment.GoToMarket mlisenter;
    private TestAdapter testAdapter;
    List<CartsListModel.DataBean.ValidListBean> data;
    private CollapsingToolbarLayoutStateHelper state;

    //可用列表
    private List<CartsListModel.DataBean.ValidListBean> mListCart = new ArrayList<>();
    private List<CartsListModel.DataBean.InValidListBean> unList = new ArrayList<>();
    //这里创建新的model,存储选中的item的数据来
    CartCommonGoodsModel mModelCartCommonGoods = new CartCommonGoodsModel();
    private CartActivityGoodsModel mModelCartActivityGoods = new CartActivityGoodsModel();
    private double sendAmount;
    private BigDecimal allprice;
    private BaseModel mModelDeleteCart;
    private String normalProductBalanceVOStr = "";
    private String activityBalanceVOStr = "";
    private String cartListStr;
    private double discribe;


    /**
     * 获取滚动数据
     *
     * @param amount
     */
    private void getScrollData(double amount) {
        CartGetReductDescAPI.requestCartGetReductDesc(mActivity, amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CartGetReductModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CartGetReductModel cartGetReductModel) {
                        if (cartGetReductModel.isSuccess()) {
                            if (!TextUtils.isEmpty(cartGetReductModel.getData())) {
                                marqueeTextView.setText(cartGetReductModel.getData());
                                fl.setVisibility(View.VISIBLE);
                            } else {
                                fl.setVisibility(View.GONE);

                            }

                        }
                    }
                });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageGoBay:
                startActivity(new Intent(mContext, HomeActivity.class));
                EventBus.getDefault().post(new GoToMarketEvent());
                break;
            case R.id.tv_clear:
                //清空所有失效的商品
//                for (int i = 0; i <unList.size() ; i++) {
//                    List<CartsListModel.DataBean.InValidListBean.SpecProductListBeanX> specProductList = unList.get(i).getSpecProductList();
//                    for (int j = 0; j <specProductList.size() ; j++) {
//                        int cartId = specProductList.get(j).getCartId();
//                        unCartsId.add(cartId);
//                    }
//                }

                showClearDialog();

                break;

            case R.id.tv_delete:
                cartsId.clear();
                if(data==null) {
                    for (int i = 0; i <mListCart.size() ; i++) {
                        List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> specProductList = mListCart.get(i).getSpecProductList();
                        for (int j = 0; j <specProductList.size() ; j++) {
                            if(specProductList.get(j).isSelected()) {
                                int cartId = specProductList.get(j).getCartId();
                                cartsId.add(cartId);
                            }
                        }
                    }
                    if(cartsId.size()==0) {
                        ToastUtil.showSuccessMsg(mContext,"请选择要删除的商品");
                    }else {
                        showDeleteCartDialog(0,cartsId);
                    }


                }else {
                    for (int i = 0; i <data.size() ; i++) {
                        List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> specProductList = data.get(i).getSpecProductList();
                        for (int j = 0; j <specProductList.size() ; j++) {
                            if(specProductList.get(j).isSelected()) {
                                int cartId = specProductList.get(j).getCartId();
//                                cartsId.clear();
                                cartsId.add(cartId);
                            }
                        }
                    }
                    if(cartsId.size()==0) {
                        ToastUtil.showSuccessMsg(mContext,"请选择要删除的商品");
                    }else {
                        showDeleteCartDialog(0,cartsId);
                    }
                }
                break;

            case R.id.btn_sure:
                btn_sure.setEnabled(false);
                lav_activity_loading.show();
                lav_activity_loading.setVisibility(View.VISIBLE);
                mModelCartCommonGoods.amount.clear();
                mModelCartCommonGoods.productIdList.clear();
                mModelCartCommonGoods.detailList.clear();
                mModelCartActivityGoods.totalNumList.clear();
                mModelCartActivityGoods.amount.clear();
                mModelCartActivityGoods.activityIdList.clear();
                double priceCommonGoods = 0.00;
                List<Integer> cartIds = new ArrayList<>();
                if(data==null) {
                    for (int i = 0; i <mListCart.size() ; i++) {
                        int businessType = mListCart.get(i).getBusinessType();

                        List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> specProductList = mListCart.get(i).getSpecProductList();
                        if (businessType == 1) {
                            for (int j = 0; j < specProductList.size(); j++) {
                                List<CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean> productDescVOList = specProductList.get(j).getProductDescVOList();
                                List<CartCommonGoodsModel.DetailListBean> commonGoodsDetailList = new ArrayList<>();
                                int cartId = specProductList.get(j).getCartId();
                                cartIds.add(cartId);
                                cartListStr = cartIds.toString();
//                                Log.d("wodemingzishiss000.....",cartListStr);
                                for (int k = 0; k < productDescVOList.size(); k++) {
//                                    CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean productDescVOListBean = productDescVOList.get(k);
//
//                                    priceCommonGoods = Double.parseDouble(BigDecimalUtils.add(Double.toString(priceCommonGoods), BigDecimalUtils.mul(productDescVOListBean.getPrice(),
//                                            String.valueOf(productDescVOListBean.getProductNum()), 2), 2));
//
//                                    commonGoodsDetailList.add(new CartCommonGoodsModel.DetailListBean(productDescVOListBean.getProductCombinationPriceId(), productDescVOListBean.getProductNum()));
//                                    mModelCartCommonGoods.amount.add(priceCommonGoods);
                                    CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean productDescVOListBean = productDescVOList.get(k);
                                    priceCommonGoods = Double.parseDouble(BigDecimalUtils.add(Double.toString(priceCommonGoods), BigDecimalUtils.mul(productDescVOListBean.getPrice(),
                                            String.valueOf(productDescVOListBean.getProductNum()), 2), 2));

                                    commonGoodsDetailList.add(new CartCommonGoodsModel.DetailListBean(productDescVOListBean.getProductCombinationPriceId(), productDescVOListBean.getProductNum()));
                                    mModelCartCommonGoods.amount.add((Double.parseDouble(BigDecimalUtils.mul(productDescVOList.get(k).getPrice(), String.valueOf(productDescVOList.get(k).getProductNum()), 2))));

                                }
                                mModelCartCommonGoods.detailList.add(commonGoodsDetailList);
                                mModelCartCommonGoods.productIdList.add(specProductList.get(j).getBusinessId());
                            }
//                            mModelCartCommonGoods.amount.add(priceCommonGoods);



                        } else if (businessType == 2 || businessType == 3 || businessType == 11) {
                            for (int j = 0; j < specProductList.size(); j++) {
                                List<CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean> productDescVOList = specProductList.get(j).getProductDescVOList();
                                List<CartCommonGoodsModel.DetailListBean> commonGoodsDetailList = new ArrayList<>();
                                if (specProductList.get(j).isSelected()) {
                                    int cartId = specProductList.get(j).getCartId();
//                                    cartIds.clear();
                                    cartIds.add(cartId);
                                    cartListStr = cartIds.toString();
                                    for (int k = 0; k < productDescVOList.size(); k++) {
                                        CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean productDescVOListBean = productDescVOList.get(k);
                                        priceCommonGoods = Double.parseDouble(BigDecimalUtils.add(Double.toString(priceCommonGoods), BigDecimalUtils.mul(productDescVOListBean.getPrice(),
                                                String.valueOf(productDescVOListBean.getProductNum()), 2), 2));

                                        commonGoodsDetailList.add(new CartCommonGoodsModel.DetailListBean(productDescVOListBean.getProductCombinationPriceId(), productDescVOListBean.getProductNum()));
                                        mModelCartActivityGoods.totalNumList.add(productDescVOList.get(k).getProductNum());
                                        mModelCartActivityGoods.amount.add((Double.parseDouble(BigDecimalUtils.mul(productDescVOList.get(k).getPrice(), String.valueOf(productDescVOList.get(k).getProductNum()), 2))));

                                    }
                                    mModelCartActivityGoods.activityIdList.add(specProductList.get(j).getBusinessId());
                                }
                            }
//                            mModelCartActivityGoods.amount.add(priceCommonGoods);
                        }
                    }

                }
                else {
                    for (int i = 0; i <data.size() ; i++) {
                        int businessType = data.get(i).getBusinessType();
                        List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> specProductList = data.get(i).getSpecProductList();
                        if(businessType == 1) {
                            for (int j = 0; j <specProductList.size() ; j++) {
                                List<CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean> productDescVOList = specProductList.get(j).getProductDescVOList();
                                List<CartCommonGoodsModel.DetailListBean> commonGoodsDetailList = new ArrayList<>();
                                if(specProductList.get(j).isSelected()) {
                                    int cartId = specProductList.get(j).getCartId();
//                                    cartIds.clear();
                                    cartIds.add(cartId);
                                    cartListStr = cartIds.toString();
                                    for (int k = 0; k <productDescVOList.size() ; k++) {
                                        CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean productDescVOListBean = productDescVOList.get(k);
                                        priceCommonGoods = Double.parseDouble(BigDecimalUtils.add(Double.toString(priceCommonGoods), BigDecimalUtils.mul(productDescVOListBean.getPrice(),
                                                String.valueOf(productDescVOListBean.getProductNum()), 2), 2));

                                        commonGoodsDetailList.add(new CartCommonGoodsModel.DetailListBean(productDescVOListBean.getProductCombinationPriceId(),productDescVOListBean.getProductNum()));
                                        mModelCartCommonGoods.amount.add(priceCommonGoods);
                                    }

                                    mModelCartCommonGoods.detailList.add(commonGoodsDetailList);
                                    mModelCartCommonGoods.productIdList.add(specProductList.get(j).getBusinessId());
                                }
                            }
//                            mModelCartCommonGoods.amount.add(priceCommonGoods);
                            //活动商品
                        }
                        else if(businessType == 2 || businessType == 3 || businessType == 11) {
                            for (int j = 0; j <specProductList.size() ; j++) {
                                List<CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean> productDescVOList = specProductList.get(j).getProductDescVOList();
                                List<CartCommonGoodsModel.DetailListBean> commonGoodsDetailList = new ArrayList<>();
                                if(specProductList.get(j).isSelected()) {
                                    int cartId = specProductList.get(j).getCartId();
//                                    cartIds.clear();
                                    cartIds.add(cartId);
                                    cartListStr = cartIds.toString();
                                    for (int k = 0; k <productDescVOList.size() ; k++) {
                                        CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean productDescVOListBean = productDescVOList.get(k);
                                        priceCommonGoods = Double.parseDouble(BigDecimalUtils.add(Double.toString(priceCommonGoods),BigDecimalUtils.mul(productDescVOListBean.getPrice(),
                                                String.valueOf(productDescVOListBean.getProductNum()),2),2));

                                        commonGoodsDetailList.add(new CartCommonGoodsModel.DetailListBean(productDescVOListBean.getProductCombinationPriceId(),productDescVOListBean.getProductNum()));
                                        mModelCartActivityGoods.totalNumList.add(productDescVOList.get(k).getProductNum());
                                        mModelCartActivityGoods.amount.add((Double.parseDouble(BigDecimalUtils.mul(productDescVOList.get(k).getPrice(), String.valueOf(productDescVOList.get(k).getProductNum()), 2))));
                                        mModelCartActivityGoods.amount.add(priceCommonGoods);

                                    }
                                    mModelCartActivityGoods.activityIdList.add(specProductList.get(j).getBusinessId());
                                }
                            }
//                            mModelCartActivityGoods.amount.add(priceCommonGoods);
                        }
                    }
                }

                normalProductBalanceVOStr = "";
                if (mModelCartCommonGoods.amount != null && mModelCartCommonGoods.amount.size() > 0) {
                    //统计完成,有普通商品需要结算
                    normalProductBalanceVOStr = mModelCartCommonGoods.toString();
                }



                activityBalanceVOStr = "";
                if (mModelCartActivityGoods.amount != null && mModelCartActivityGoods.amount.size() > 0) {
                    //统计完成,有活动商品需要结算
                    activityBalanceVOStr = mModelCartActivityGoods.toString();
                }

                requestCartBalance();

                break;

            case R.id.iv_clear:
                fl.setVisibility(View.GONE);
                ll_service.setVisibility(View.GONE);
                iv_clear.setVisibility(View.GONE);
                break;

            case R.id.ll_go_market:
                startActivity(new Intent(mContext, HomeActivity.class));
                EventBus.getDefault().post(new GoToMarketEvent());
                break;
            case R.id.cb_select_all:
                if (mSelect) {
                    cb_select_all.setChecked(false);
                    testAdapter.setAllselect(false);
                } else {
                    cb_select_all.setChecked(true);
                    testAdapter.setAllselect(true);
                }

                break;
        }
    }

    /**
     * 清除失效商品弹窗
     */
    private void showClearDialog() {
        //确认要删除选中的商品吗
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(mContext, R.style.DialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_delete_cart);
        TextView mTvTitle = (TextView) window.findViewById(R.id.tv_dialog_delete_cart_title);
        TextView mTvCancel = (TextView) window.findViewById(R.id.tv_dialog_delete_cart_cancel);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_dialog_delete_cart_confirm);
        mTvTitle.setText("确定清空失效的商品吗?");

        mTvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        unCartsId.clear();
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {

                for (int i = 0; i <unList.size() ; i++) {
                    List<CartsListModel.DataBean.InValidListBean.SpecProductListBeanX> specProductList = unList.get(i).getSpecProductList();
                    for (int j = 0; j <specProductList.size() ; j++) {
                        int cartId = specProductList.get(j).getCartId();
                        unCartsId.add(cartId);
                    }
                }

                requestDeleteCart(unCartsId.toString());
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 删除弹窗
     * @param
     * @param cartsId
     */
    private void showDeleteCartDialog(int type, List<Integer> cartsId) {
        //确认要删除选中的商品吗
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(mContext, R.style.DialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_delete_cart);
        TextView mTvTitle = (TextView) window.findViewById(R.id.tv_dialog_delete_cart_title);
        TextView mTvCancel = (TextView) window.findViewById(R.id.tv_dialog_delete_cart_cancel);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_dialog_delete_cart_confirm);
        if (type == 0) {
            mTvTitle.setText("确定删除选中的商品吗?");
        }
        mTvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (type == 0) {
                    requestDeleteCart(cartsId.toString());
                }
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 删除购物车
     * @param
     */
    private void requestDeleteCart(String cardIds) {
        DeleteCartAPI.requestDeleteCart(mContext, cardIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        mModelDeleteCart = baseModel;
                        if (mModelDeleteCart.success) {
                            //删除成功,重新请求列表数据
                            ToastUtil.showSuccessMsg(mActivity, "删除商品成功");
                            state = CollapsingToolbarLayoutStateHelper.EXPANDED;

                            requestCartList();
                        } else {
                            ToastUtil.showSuccessMsg(mActivity,baseModel.message);
                        }
                    }
                });
    }


    /**
     * 结算
     */
    private void requestCartBalance() {
        CartBalanceAPI.requestCartBalance(mActivity, normalProductBalanceVOStr, activityBalanceVOStr, cartListStr, "", 0,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CartBalanceModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CartBalanceModel cartBalanceModel) {
                        if (cartBalanceModel.success) {
                            Intent intent = new Intent(mContext, ConfirmNewOrderActivity.class);
                            intent.putExtra("normalProductBalanceVOStr", normalProductBalanceVOStr);
                            intent.putExtra("activityBalanceVOStr", activityBalanceVOStr);
                            intent.putExtra("cartListStr", cartListStr);
                            startActivity(intent);
                            lav_activity_loading.hide();
                            lav_activity_loading.setVisibility(View.GONE);
                            btn_sure.setEnabled(true);
                        }else {
                            lav_activity_loading.hide();
                            lav_activity_loading.setVisibility(View.GONE);
                            btn_sure.setEnabled(true);
                            ToastUtil.showSuccessMsg(mActivity, cartBalanceModel.message);
                        }
                    }
                });

    }


    //数据更新
    @Override
    public void update(List<CartsListModel.DataBean.ValidListBean> data) {
        this.data = data;
    }

    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void refreshCarNum();
    }

    public interface GoToMarket {
        void jumpMarket();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            EventBus.getDefault().post(new NumEvent());
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBuss(ReduceNumEvent event) {
        //刷新UI
        requestCartList();

    }

    //这里用了eventBus来进行实时价格的UI更改。
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(UpdateEvent event) {
        //刷新UI
//        requestCartList();
        discribe = Double.parseDouble(event.getDiscribe());

        tv_total_price.setText("￥"+discribe);

        getScrollData(discribe);

        if(sendAmount> discribe) {
            double diff = sendAmount - discribe;
            ll_service.setVisibility(View.VISIBLE);
            double result = Double.parseDouble(String.format("%.2f", diff));
            tv_price_desc.setText(""+result);
        }else {
            ll_service.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.fragment_cart);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setTranslucentStatus();
    }

    @Override
    public void findViewById() {
        binder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tv_delete.setOnClickListener(this);
        cb_select_all.setOnClickListener(this);
        iv_clear.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        ll_go_market.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
        imageGoBay.setOnClickListener(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setViewData() {
        requestCartList();
        getCustomerPhone();
        getProductsList();
        lav_activity_loading.show();
        lav_activity_loading.setVisibility(View.VISIBLE);
    }

    private void getAllPrice(List<CartsListModel.DataBean.ValidListBean> validList) {
        allprice = new BigDecimal("0");
        if(validList!=null){
            for (int i=0;i<validList.size();i++){
                List<CartsListModel.DataBean.ValidListBean.SpecProductListBean> specProductList = validList.get(i).getSpecProductList();
                for (int y=0;y<specProductList.size();y++){
                    if(specProductList.get(y).isSelected()){

                        List<CartsListModel.DataBean.ValidListBean.SpecProductListBean.ProductDescVOListBean> productDescVOList = specProductList.get(y).getProductDescVOList();
                        for (int j = 0; j <productDescVOList.size() ; j++) {
                            BigDecimal interestRate = new BigDecimal(productDescVOList.get(j).getProductNum()); //数量
                            double interest = Arith.mul(Double.parseDouble(productDescVOList.get(j).getPrice()), interestRate);
                            allprice = allprice.add(BigDecimal.valueOf(interest));

                        }
                    }
                }
            }
        }
        double allprices = allprice.doubleValue();
        getScrollData(allprices);
        tv_total_price.setText("￥"+allprice);

        if(sendAmount> allprices) {
            double diff = sendAmount - allprices;
            double result = Double.parseDouble(String.format("%.2f", diff));
            tv_price_desc.setText(""+result);
            ll_service.setVisibility(View.VISIBLE);
        }else {
            ll_service.setVisibility(View.GONE);
        }

    }

    @Override
    public void setClickEvent() {

    }


    /**
     * 购物车列表
     */
    private void requestCartList() {
        CartListAPI.requestCartLists(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CartsListModel>() {
                    @Override
                    public void onCompleted() {

                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CartsListModel cartListModel) {
                        if(cartListModel.isSuccess()) {
                            mListCart.clear();
                            unList.clear();
                            sendAmount = Double.parseDouble(cartListModel.getData().getSendAmount());
                            mRv.setLayoutManager(new LinearLayoutManager(mContext));
                            //可用列表
                            List<CartsListModel.DataBean.ValidListBean> validList = cartListModel.getData().getValidList();
                            mListCart.addAll(validList);
                            btn_sure.setText("结算"+"("+cartListModel.getData().getValidList().size()+")");
                            testAdapter = new TestAdapter(R.layout.item_carts, mListCart,CartActivity.this);
                            mRv.setAdapter(testAdapter);
                            //过期列表
                            List<CartsListModel.DataBean.InValidListBean> inValidList = cartListModel.getData().getInValidList();
                            unList.addAll(inValidList);
                            Log.d("dwdssssssss.....",unList.size()+"");
                            if(unList.size()==0) {
                                rl_unable.setVisibility(View.GONE);
                                rv_unable.setVisibility(View.GONE);
                            }else {
                                rl_unable.setVisibility(View.VISIBLE);
                                rv_unable.setVisibility(View.VISIBLE);
                            }

                            if(mListCart.size()==0&&unList.size()==0) {
                                ll_NoData.setVisibility(View.VISIBLE);
                                ll.setVisibility(View.GONE);
                                tv_delete.setVisibility(View.GONE);
                                ll_service.setVisibility(View.GONE);
                                getScrollData(0);
                            }else {
                                tv_delete.setVisibility(View.VISIBLE);
                                ll_NoData.setVisibility(View.GONE);
                                ll.setVisibility(View.VISIBLE);
                                getAllPrice(validList);


                            }

                            if(mListCart.size()==0 && unList.size()!=0) {
                                ll_service.setVisibility(View.GONE);
                                ll.setVisibility(View.GONE);
                            }else {
//                            ll_service.setVisibility(View.VISIBLE);
//                            ll.setVisibility(View.VISIBLE);
                            }


                            if(mListCart.size()==0) {
//                            ll_service.setVisibility(View.GONE);
//                            getScrollData(0);
                            }else {
//                            getAllPrice(validList);
                            }

//                        if(unList.size()!=0&&mListCart.size()!=0) {
//                            ll.setVisibility(View.VISIBLE);
//                        }else {
//                            ll.setVisibility(View.GONE);
//                        }

                            CartUnableAdapter unAbleAdapter = new CartUnableAdapter(R.layout.item_uncarts,unList);
//
                            rv_unable.setLayoutManager(new LinearLayoutManager(mContext));
                            rv_unable.setAdapter(unAbleAdapter);

                            testAdapter.notifyDataSetChanged();

                            testAdapter.setResfreshListener(new TestAdapter.OnResfreshListener() {
                                @Override
                                public void onResfresh( boolean isSelect) {
                                    mSelect = isSelect;
                                    if(isSelect){
                                        cb_select_all.setChecked(true);
                                    }else {
                                        cb_select_all.setChecked(false);
                                    }
                                }
                            });
                            lav_activity_loading.hide();
                            lav_activity_loading.setVisibility(View.GONE);

                        }else {
                            lav_activity_loading.hide();
                            lav_activity_loading.setVisibility(View.GONE);
                        }


                    }
                });
    }

    /**
     * 必买列表(王涛)
     * @param
     */
    MustAdapter mustAdapter;
    private void getProductsList() {
        IndexHomeAPI.getMust(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MustModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MustModel getCommonProductModel) {
                        if (getCommonProductModel.isSuccess()) {
                            //为您推荐列表
                            mustAdapter = new MustAdapter(R.layout.item_team_list, getCommonProductModel.getData(), new MustAdapter.Onclick() {
                                @Override
                                public void addDialog() {
                                    requestCartList();
                                    getProductsList();
                                }

                                @Override
                                public void tipClick() {
                                    showPhoneDialog(cell);
                                }
                            });

                            rv_recommend.setLayoutManager(new GridLayoutManager(mActivity,2));
                            rv_recommend.setAdapter(mustAdapter);


                        } else {
                            AppHelper.showMsg(mActivity, getCommonProductModel.getMessage());
                        }
                    }
                });
    }

    private void getCustomerPhone() {
        PublicRequestHelper.getCustomerPhone(mActivity, new OnHttpCallBack<GetCustomerPhoneModel>() {
            @Override
            public void onSuccessful(GetCustomerPhoneModel getCustomerPhoneModel) {
                if (getCustomerPhoneModel.isSuccess()) {
                    cell = getCustomerPhoneModel.getData();
                } else {
                    AppHelper.showMsg(mActivity, getCustomerPhoneModel.getMessage());
                }
            }

            @Override
            public void onFaild(String errorMsg) {
            }
        });
    }


    /**
     * 弹出电话号码
     */
    private AlertDialog mDialog;
    TextView tv_phone;
    public void showPhoneDialog(final String cell) {
        mDialog = new AlertDialog.Builder(mActivity).create();
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_shouye_tip);
        tv_phone = mDialog.getWindow().findViewById(R.id.tv_phone);
        tv_phone.setText(cell);
        mDialog.getWindow().findViewById(R.id.tv_dialog_call_phone_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cityEvent(CityEvent event) {
        requestCartList();
        isShow();
        getCustomerPhone();
    }

    private void isShow() {
        CityChangeAPI.isShow(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IsShowModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IsShowModel isShowModel) {
                        if(isShowModel.isSuccess()) {
                            if(isShowModel.data!=null) {
                                enjoyProduct = isShowModel.getData().enjoyProduct;
                                getProductsList();
                            }
                        }else {
                            AppHelper.showMsg(mActivity,isShowModel.getMessage());
                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upPrice(AddressEvent event) {
        //刷新UI
        requestCartList();
        getProductsList();
        getCustomerPhone();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBusss(BackEvent event) {
        //刷新UI
        requestCartList();
        getCustomerPhone();
    }

    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}
