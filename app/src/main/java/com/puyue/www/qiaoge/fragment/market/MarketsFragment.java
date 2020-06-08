package com.puyue.www.qiaoge.fragment.market;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.slider.library.SliderLayout;
import com.example.xrecyclerview.XRecyclerView;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.CouponDetailActivity;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.activity.home.SearchStartActivity;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.activity.home.TeamDetailActivity;
import com.puyue.www.qiaoge.activity.home.TeamGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.mine.coupons.MyCouponsActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginEvent;
import com.puyue.www.qiaoge.activity.mine.login.RegisterActivity;
import com.puyue.www.qiaoge.activity.mine.login.RegisterMessageActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MinerIntegralActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletPointActivity;
import com.puyue.www.qiaoge.adapter.home.CommonProductActivity;
import com.puyue.www.qiaoge.adapter.home.HotProductActivity;
import com.puyue.www.qiaoge.adapter.home.ReductionProductActivity;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.adapter.home.SeckillGoodActivity;
import com.puyue.www.qiaoge.adapter.market.MarketAlreadyGoodAdapter;
import com.puyue.www.qiaoge.adapter.market.MarketGoodBrandAdapter;
import com.puyue.www.qiaoge.adapter.market.MarketGoodsAdapter;
import com.puyue.www.qiaoge.adapter.market.MarketSecondAdapter;
import com.puyue.www.qiaoge.api.cart.ProdRecommendModel;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.BannerModel;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.api.market.ClassIfyModel;
import com.puyue.www.qiaoge.api.market.MarketAlreadyGoodAPI;
import com.puyue.www.qiaoge.api.market.MarketGoodNameAPI;
import com.puyue.www.qiaoge.api.market.MarketGoodSelcetAPI;
import com.puyue.www.qiaoge.api.market.MarketGoodsClassifyAPI;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.GlideImageLoades;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.CouponDialog;
import com.puyue.www.qiaoge.dialog.LoadingDialog;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.event.LogoutEvent;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.event.UpDateNumEvent;
import com.puyue.www.qiaoge.fragment.home.CityEvent;
import com.puyue.www.qiaoge.fragment.home.HomeFragmentsss;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.TwoDeviceHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.market.MarketAlreadyGoodModel;
import com.puyue.www.qiaoge.model.market.MarketSelectGoodModel;
import com.puyue.www.qiaoge.utils.LoginUtil;
import com.puyue.www.qiaoge.view.FlowLayout;
import com.puyue.www.qiaoge.view.selectmenu.MenuBar;
import com.puyue.www.qiaoge.view.selectmenu.MyListView;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/10/30
 */
public class MarketsFragment extends BaseFragment {
    private RelativeLayout mLlSearch;
    CouponDialog couponDialog;
    //左侧列表
    private RecyclerView mRvSecond;
    //右侧列表
    private XRecyclerView mRvDetail;
    //
    private List<ClassIfyModel.DataBean> mList = new ArrayList<>();
    //左侧集合
    private List<ClassIfyModel.DataBean> mListSecondNow = new ArrayList<>();
    //右侧数据集合
    private List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> mListGoods = new ArrayList<>();
    //品牌数据集合
    private List<MarketRightModel.DataBean.BrandProdBean.ListBeanX> mListProd = new ArrayList<>();

    //品牌推荐数据集合
    private List<String> mListRecommendProd = new ArrayList<>();

    //左侧分类Adapter
    private MarketSecondAdapter mAdapterMarketSecond;
    //右侧adapter
    private MarketGoodsAdapter mAdapterMarketDetail;
    //左侧分类model
    private ClassIfyModel mModelMarketGoodsClassify;
    //右侧model
    private MarketRightModel mModelMarketGoods;
    private boolean select = false;
    private int mFirstCode;
    private int mSecondCode;
    private int pageNum = 1;//切换一级分类和二级分类的时候都要将这个pageNum置为1
//    private LoadingDailog dialog;
    private String cell; // 客服电话
    private ImageView mIvNoData;
    private LinearLayout llDialog;
    private RecyclerView tvRvSelect;
    private LinearLayout ll_up;
    private TextView tv_blank;
//    private SliderLayout mViewBanner;
    private List<BannerModel.DataBean> mListBanner = new ArrayList<>();
    private LinearLayout mllMarket;
    private RelativeLayout mRlSelectGood;
    private RecyclerView mRyGetGoodName;
    private RecyclerView mRyBuyName;
    private EditText mEtLowPrice;
    private EditText mEtHighPrice;
    private EditText mEtSearchGood;
    private TextView mTvReresh;
    AVLoadingIndicatorView lav_activity_loading;
    Banner banner;
    private TextView mTvOk;
    private ImageView ivSearch;
    private LoadingDailog dialog;
    private boolean isFirst = true;
    TextView tv_search;
    int isSelected;
    boolean isChecked = false;
    View v_shadow;
    private List<String> mListBrand = new ArrayList<>();
    //商品名
    private MarketGoodBrandAdapter mAdapterBrand;
    private String brandName = "";
    private String selectBrandName = "";
    private String minPrice;
    private String maxPrice;
    private List<MarketAlreadyGoodModel.DataBean> mListAlreadyGood = new ArrayList<>();
    private MarketAlreadyGoodAdapter mAlreadyAdapter;
    private PopupWindow popupWindow;
    private ArrayList<View> viewList = new ArrayList<>();
    private MenuBar mb_bar;
    private String saleVolume = "";
    private String priceUp = "";
    private String newProduct = "";
    private int Imposition = 0;
    private boolean isCheck = false;
    private boolean hasPage = true;
    EditText et_goods;
    LinearLayout ll_select;
    LinearLayout ll_prod;
    private ProdAdapter prodAdapter;
    XRecyclerView rv_prod_detail;
    Context context;
    private PopupWindow dialog1;
    private SearchProdAdapter searchProdAdapter;
    public int selectionPositon;
    private AlertDialog mTypedialog;
    int shopTypeId;
    boolean flag = false;

    public static MarketsFragment getInstance() {
        MarketsFragment fragment = new MarketsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int setLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    public void initViews(View view) {
//        initStatusBarWhiteColor();
    }

//    protected void initStatusBarWhiteColor() {
//        //设置状态栏颜色为白色，状态栏图标为黑色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getActivity().getWindow().setStatusBarColor(Color.WHITE);
//            StatusBarUtil.setStatusBarLightMode(getActivity());
//        }
//    }
    @Override
    public void findViewById(View view) {
        context = getActivity();

        EventBus.getDefault().register(this);
//        ptr = view.findViewById(R.id.ptr);
        banner = view.findViewById(R.id.banner);
        tv_search = view.findViewById(R.id.tv_search);
        et_goods = view.findViewById(R.id.et_goods);
        v_shadow = view.findViewById(R.id.v_shadow);
        rv_prod_detail = view.findViewById(R.id.rv_prod_detail);
        ll_select = view.findViewById(R.id.ll_select);
        ll_prod = view.findViewById(R.id.ll_prod);
        mLlSearch = view.findViewById(R.id.ll_market_search);//搜索
        mRvSecond = ((RecyclerView) view.findViewById(R.id.rv_market_second));
        mRvDetail = ((XRecyclerView) view.findViewById(R.id.rv_market_detail));
        mIvNoData = ((ImageView) view.findViewById(R.id.iv_market_no_data));
//        mViewBanner = view.findViewById(R.id.view_market_banner);
        lav_activity_loading = view.findViewById(R.id.lav_activity_loading);
        llDialog = ((LinearLayout) view.findViewById(R.id.dialog));
        tvRvSelect = view.findViewById(R.id.recyclerView_select);
        ll_up = view.findViewById(R.id.ll_up);
        tv_blank = view.findViewById(R.id.tv_blank);
        mRlSelectGood = view.findViewById(R.id.rl_select_good);
        mllMarket = view.findViewById(R.id.ll_market);
        mb_bar = view.findViewById(R.id.mb_bar);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBrandName = et_goods.getText().toString();
                pageNum = 1;
                if(mModelMarketGoods.getData().getBrandProd().isHasNextPage()) {
                    hasPage = true;
                    getDataThree();
                }else {
                    hasPage = false;
                    getDataThree();
                }
            }
        });

        v_shadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintKbTwo();
            }
        });

        et_goods.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    selectBrandName = et_goods.getText().toString();
                    pageNum = 1;
                    if(mModelMarketGoods.getData().getBrandProd().isHasNextPage()) {
                        hasPage = true;
                        getDataThree();
                    }else {
                        hasPage = false;
                        getDataThree();
                    }


                    return true;
                }
                return false;

            }
        });

        et_goods.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    ProdDialog();
                    v_shadow.setVisibility(View.VISIBLE);
                }
            }
        });

        tv_blank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select) {
                    llDialog.setVisibility(View.GONE);

                }
                select = false;
            }
        });
        tv_blank.setBackgroundColor(Color.BLACK);
        tv_blank.getBackground().setAlpha(100);

        mRlSelectGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 一个自定义的布局，作为显示的内容
                View contentView = LayoutInflater.from(mActivity).inflate(
                        R.layout.market_select_draw, null);
                mEtSearchGood = contentView.findViewById(R.id.et_activity_search_word);//输入商品名称
                mEtLowPrice = contentView.findViewById(R.id.et_low_price);//输入最低价
                mEtHighPrice = contentView.findViewById(R.id.rt_high_price);//输入最高价
                mRyBuyName = contentView.findViewById(R.id.ry_already_buy_good);//购买过的商品
                mRyGetGoodName = contentView.findViewById(R.id.recyclerView_search_good);//获取到的商品名
                mTvReresh = contentView.findViewById(R.id.tv_refresh_good);//重置
                mTvOk = contentView.findViewById(R.id.tv_ok);//确定
                ivSearch = contentView.findViewById(R.id.iv_search);
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int width1 = mActivity.getWindowManager().getDefaultDisplay().getWidth();


                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                popupWindow = new PopupWindow(contentView, width, LinearLayout.LayoutParams.MATCH_PARENT, true);
                popupWindow.setWidth(width1 * 3 / 4);
                popupWindow.setAnimationStyle(R.style.AnimationRightFade);
//全屏
                popupWindow.setClippingEnabled(false);
                backgroundAlpha(0.3f);
//关闭事件
                popupWindow.setOnDismissListener(new popupDismissListener());

                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(mllMarket, Gravity.RIGHT, 0, 0);


                //  mEtLowPrice.setInputType(InputType.TYPE_CLASS_NUMBER);
                //  mEtHighPrice.setInputType(InputType.TYPE_CLASS_NUMBER);
                //获取商品名字
                brandName = "";
                getGoodName();
                //获取购买过的商品
                getAlreadyGood();
                ivSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StringHelper.notEmptyAndNull(mEtSearchGood.getText().toString())) {

                            brandName = mEtSearchGood.getText().toString();
                            getGoodName();
                        } else {
                            brandName = "";
                            getGoodName();
                        }
                    }
                });


                mTvReresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEtSearchGood.setText("");
                        mEtLowPrice.setText("");
                        mEtHighPrice.setText("");


                    }
                });
                mTvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEtSearchGood.setText("");
                        // if (isChecked && StringHelper.notEmptyAndNull(mEtLowPrice.getText().toString()) && StringHelper.notEmptyAndNull(mEtHighPrice.getText().toString())) {
                        minPrice = mEtLowPrice.getText().toString();
                        maxPrice = mEtHighPrice.getText().toString();

                        getDataTwo();

                        popupWindow.dismiss();

                    }

                });
            }
        });
    }

    /**
     * 推荐商品弹框列表
     */
    private void ProdDialog() {
        dialog1 = new PopupWindow(getActivity());
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        View searchView = LayoutInflater.from(getActivity()).inflate(R.layout.prod, null);
        FlowLayout fl_container = searchView.findViewById(R.id.fl_container);
        dialog1.setContentView(searchView);
        dialog1.setBackgroundDrawable(new BitmapDrawable());
        dialog1.setAnimationStyle(R.style.AnimationRightFade);
//全屏
        dialog1.setClippingEnabled(false);
//关闭事件
        dialog1.setOnDismissListener(new popupDismissListener());
        dialog1.getBackground().setAlpha(100);
        dialog1.setOutsideTouchable(true);
        dialog1.setTouchable(true);
        dialog1.showAsDropDown(et_goods,0,0);

        fl_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String prodName = mListRecommendProd.get(position);
                selectBrandName = prodName;
                getDataThree();
                et_goods.setText(selectBrandName);
                dialog1.dismiss();
            }
        });
        searchProdAdapter = new SearchProdAdapter(context,mListRecommendProd);
        fl_container.setAdapter(searchProdAdapter);

    }

    //此方法只是关闭软键盘
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && mActivity.getCurrentFocus() != null) {
            if (mActivity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
//            et_goods.clearFocus();
            v_shadow.setVisibility(View.GONE);
        }

    }



    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }

    //筛选确定
    private void sendSelectGoodTwo(String saleVolume, String priceUp, String newProduct, String brandName, String minPrices, String maxPrices) {
        MarketGoodSelcetAPI.getClassifyRight(mActivity, pageNum, 12, mFirstCode, mSecondCode, saleVolume, priceUp, newProduct, brandName, minPrices, maxPrices)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MarketRightModel>() {
                    @Override
                    public void onCompleted() {
                        mRvDetail.refreshComplete();

                    }

                    @Override
                    public void onError(Throwable e) {
//                        ptr.refreshComplete();
                        lav_activity_loading.hide();

                    }

                    @Override
                    public void onNext(MarketRightModel marketGoodSelectModel) {

                        if (marketGoodSelectModel.isSuccess()) {
                            flag = true;
                            mModelMarketGoods = marketGoodSelectModel;
                            dialog.dismiss();
                            updateMarketGoods();
                            lav_activity_loading.hide();
//                            Log.d("swsssssssssss.......","1");

                        } else {
                            lav_activity_loading.hide();
                            AppHelper.showMsg(mActivity, marketGoodSelectModel.getMessage());
                        }

                    }
                });
    }

    //筛选确定
    private void sendSelectGood(String saleVolume, String priceUp, String newProduct, String brandName, String minPrices, String maxPrices) {


        MarketGoodSelcetAPI.getClassifyRight(mActivity, pageNum, 12, mFirstCode, mSecondCode, saleVolume, priceUp, newProduct, brandName, minPrices, maxPrices)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MarketRightModel>() {
                    @Override
                    public void onCompleted() {
//                        ptr.refreshComplete();
                        mRvDetail.refreshComplete();

                    }

                    @Override
                    public void onError(Throwable e) {
//                        ptr.refreshComplete();
                        lav_activity_loading.hide();
                    }

                    @Override
                    public void onNext(MarketRightModel marketGoodSelectModel) {
                        if (marketGoodSelectModel.isSuccess()) {
                            Log.d("swsssssssssss.......","2");
                            selectBrandName = "";
                            minPrice = "";
                            maxPrice = "";
                            mModelMarketGoods = marketGoodSelectModel;
                            dialog.dismiss();
                            updateMarketGoods();
                            lav_activity_loading.hide();
                            flag = true;
                        } else {
                            AppHelper.showMsg(mActivity, marketGoodSelectModel.getMessage());
                            lav_activity_loading.hide();

                        }
                    }
                });
    }


    //获取购买过商品
    private void getAlreadyGood() {
        MarketAlreadyGoodAPI.requestMarketAlready(mActivity, mFirstCode, mSecondCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MarketAlreadyGoodModel>() {
                    @Override
                    public void onCompleted() {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onNext(MarketAlreadyGoodModel marketAlreadyGoodModel) {

                        if (marketAlreadyGoodModel.isSuccess()) {
                            mListAlreadyGood.clear();
                            mListAlreadyGood.addAll(marketAlreadyGoodModel.getData());
                            showAlreadyGood(mListAlreadyGood);
                        } else {
                            AppHelper.showMsg(mActivity, marketAlreadyGoodModel.getMessage());
                        }

                    }
                });

    }

    private void showAlreadyGood(List<MarketAlreadyGoodModel.DataBean> listBeans) {

        mAlreadyAdapter = new MarketAlreadyGoodAdapter(R.layout.already_buy_good, listBeans);
        //1、RecyclerView 有自己默认的动画，去除默认动画
        mRyBuyName.setLayoutManager(new GridLayoutManager(mActivity, 2));
        //并且设置对应的adapter，设置
        ((SimpleItemAnimator) mRyBuyName.getItemAnimator()).setSupportsChangeAnimations(false);

        mAlreadyAdapter.setHasStableIds(true);
        mAlreadyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, CommonGoodsDetailActivity.class);
                int productId = listBeans.get(position).getProductId();
                intent.putExtra(AppConstant.ACTIVEID, productId);
                startActivity(intent);
            }
        });


        mRyBuyName.setAdapter(mAlreadyAdapter);

    }

    //获取商品名
    private void getGoodName() {
        MarketGoodNameAPI.requestMarketName(mActivity, mFirstCode, mSecondCode, brandName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MarketSelectGoodModel>() {
                    @Override
                    public void onCompleted() {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onNext(MarketSelectGoodModel marketSelectGoodModel) {

                        if (marketSelectGoodModel.isSuccess()) {

                            mListBrand.clear();
                            mListBrand.addAll(marketSelectGoodModel.getData());

                            showGoodBrand();

                        } else {
                            AppHelper.showMsg(mActivity, marketSelectGoodModel.getMessage());
                        }
                    }
                });

    }

    private void showGoodBrand() {
        mAdapterBrand = new MarketGoodBrandAdapter(mActivity, mListBrand);
        mRyGetGoodName.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mAdapterBrand.setOnItemClickListener(new MarketGoodBrandAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List<Integer> list) {

                selectBrandName = "";
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        selectBrandName += mListBrand.get(list.get(i)) + ",";
                    }
                }
            }
        });

        mRyGetGoodName.setAdapter(mAdapterBrand);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cityEvent(CityEvent event) {
        //刷新UI
        lav_activity_loading.show();
//        lav_activity_loading.smoothToShow();
        requestGoodsList();
        requestBanner();
        getSearchProd();
        getDataThree();
        getDataTwo();
//        getData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky=true)
    public void update(AddressEvent event) {
        //刷新数据
        lav_activity_loading.show();
//        lav_activity_loading.smoothToShow();
        requestGoodsList();
        requestBanner();
        getSearchProd();
        getDataThree();
        getDataTwo();

    }

    @Override
    public void setViewData() {
        lav_activity_loading.show();
        ArrayList<String> titles = new ArrayList<>();
        titles.add("综合排序");
        ArrayList<String> contentThree = new ArrayList<>();
        contentThree.add("综合排序");
        contentThree.add("价格排序");
        contentThree.add("销量排序");
        contentThree.add("新品");
        MyListView myListView2 = new MyListView(mActivity, contentThree);
        viewList.add(0, myListView2);
        mb_bar.setView(titles, viewList);
        myListView2.setOnSelectListener(new MyListView.OnSelectListener() {
            @Override
            public void getValue(String value, int position) {
                mb_bar.setTitle(value);
                pageNum = 1;
                Log.i("dda", "getValue: " + mRvSecond.getWidth() + mActivity.getWindowManager().getDefaultDisplay().getWidth());
                if (value.equals("价格排序")) {
                    priceUp = "1";
                    saleVolume = "";
                    newProduct = "";
                    selectBrandName = "";
                    minPrice = "";
                    maxPrice = "";
                    Imposition = 1;

                    sendSelectGood(saleVolume, priceUp, newProduct, selectBrandName, minPrice, maxPrice);
                } else if (value.equals("销量排序")) {
                    saleVolume = "1";
                    Imposition = 2;
                    priceUp = "";
                    newProduct = "";
                    minPrice = "";
                    maxPrice = "";
                    selectBrandName = "";

                    sendSelectGood(saleVolume, priceUp, newProduct, selectBrandName, minPrice, maxPrice);
                } else if (value.equals("新品")) {
                    newProduct = "1";
                    priceUp = "";
                    saleVolume = "";
                    selectBrandName = "";
                    minPrice = "";
                    Imposition = 3;
                    maxPrice = "";

                    sendSelectGood(saleVolume, priceUp, newProduct, selectBrandName, minPrice, maxPrice);
                } else {
                    Imposition = 0;
                    newProduct = "";
                    priceUp = "";
                    saleVolume = "";
                    selectBrandName = "";
                    minPrice = "";
                    maxPrice = "";

                    sendSelectGood("", "", "", "", "", "");
                }


            }
        });

        //获取banner
        requestBanner();
        getSearchProd();

        //切换左边导航时的加载数据弹窗
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getContext())
                .setMessage("获取数据中")
                .setCancelable(false)
                .setCancelOutside(true);
        dialog = loadBuilder.create();


        mTypedialog = new AlertDialog.Builder(mActivity, R.style.DialogStyle).create();
        mTypedialog.setCancelable(false);

        mRvSecond.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvDetail.setLayoutManager(new LinearLayoutManager(getContext()));

        mRvDetail.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                if (isCheck) {
                    pageNum = 1;
                    hasPage  = true;
                    getDataTwo();
                } else {
                    pageNum = 1;
                    hasPage  = true;
                    getData();

                }
            }

            @Override
            public void onLoadMore() {
                if (isCheck) {
//筛选
                    if (hasPage) {
                        pageNum++;
                        getDataTwo();

                    } else {
                        hasPage = false;
                        mRvDetail.noMoreLoading();

                    }
                } else {

                    Log.d("putongshujushaixin,,","5555////"+hasPage);
                    if (hasPage) {
                        pageNum++;
                        getData();
                    } else {
                        hasPage = false;
                        mRvDetail.noMoreLoading();

                        Log.d("qianqianqianqian...",hasPage+"");
                        Log.d("qianqianqianqian000...",pageNum+"");
                    }
                }
            }
        });


        ll_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select) {
                    llDialog.setVisibility(View.GONE);

                }
                select = false;
            }
        });

        mAdapterMarketSecond = new MarketSecondAdapter(R.layout.item_left_classify, mListSecondNow);

            mAdapterMarketSecond.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //在点击二级列表的时候,需要将样式修改过来,然后刷新三级详情列表数据
//
                        if (flag) {
                            flag = false;
//                            if(pos!=position) {
//                            pos = position;
                            hintKbTwo();
                            dialog.show();
                            selectionPositon = position;
                            mAdapterMarketSecond.selectPosition(position);
                            mFirstCode = mList.get(position).getFirstId();
                            mAdapterMarketSecond.notifyDataSetChanged();
                            if (mList.get(position).getSecondClassify() == null) {
                                mSecondCode = 0;
                            } else {
                                mSecondCode = mList.get(position).getSecondClassify().get(0).getSecondId();
                            }


                            if (mFirstCode == -5) {
                                ll_select.setVisibility(View.GONE);
                                ll_prod.setVisibility(View.VISIBLE);
                                getDataThree();

                            } else {
                                ll_select.setVisibility(View.VISIBLE);
                                ll_prod.setVisibility(View.GONE);
                                if (isCheck) {
                                    pageNum = 1;
                                    hasPage = true;
                                    getDataTwo();
                                } else {
                                    pageNum = 1;
                                    hasPage = true;
                                    getData();
                                    Log.d("wwdewfgdd..", hasPage + "");

                                }
                            }
//                            }else {
//                                selectionPositon = position;
//                                mAdapterMarketSecond.selectPosition(position);
//                                mAdapterMarketSecond.notifyDataSetChanged();
//                            }
                        }


                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });


        mAdapterMarketSecond.setOnItemClickListeners(new MarketSecondAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(int position, int secondId) {
                dialog.show();
                pageNum = 1;
                if (isCheck) {
                    getDataTwo();

                } else {
                    mSecondCode = secondId;
                    getData();
                }
            }
        });

        mAdapterMarketDetail = new MarketGoodsAdapter(R.layout.item_noresult_recommends, mListGoods, new MarketGoodsAdapter.Onclick() {
            @Override
            public void addDialog() {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(context))) {
                    if(UserInfoHelper.getUserType(getActivity()).equals(AppConstant.USER_TYPE_RETAIL)) {
                        if (StringHelper.notEmptyAndNull(cell)) {
                            AppHelper.showAuthorizationDialog(getActivity(), cell, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
                                        AppHelper.hideAuthorizationDialog();
                                        showSelectType(AppHelper.getAuthorizationCode());

                                    } else {
                                        AppHelper.showMsg(getActivity(), "请输入完整授权码");
                                    }
                                }
                            });
                        }
                    }
                }else {
                    initDialog();
                }
            }
        });

        prodAdapter = new ProdAdapter(R.layout.item_prod,mListProd);
        rv_prod_detail.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_prod_detail.setAdapter(prodAdapter);
        rv_prod_detail.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                if (isCheck) {
                    pageNum = 1;
                    getDataThree();
                    hasPage  = true;
                } else {
                    pageNum = 1;
                    getDataThree();
                    hasPage  = true;

                }
            }

            @Override
            public void onLoadMore() {
                if (isCheck) {

                    if (hasPage) {

                        pageNum++;
                        getDataThree();
                    } else {
                        hasPage = false;
                        rv_prod_detail.noMoreLoading();

                    }
                } else {

                    if (hasPage) {
                        Log.d("wwwwwwwwwwwww...",hasPage+"11");
                        pageNum++;

                        getDataThree();
                    } else {
                        hasPage = false;
                        rv_prod_detail.noMoreLoading();
                    }
                }
            }
        });
        mRvSecond.setAdapter(mAdapterMarketSecond);
        mRvDetail.setAdapter(mAdapterMarketDetail);
        requestGoodsList();
        getCustomerPhone();


    }

    /**
     * 填写授权码
     * @param authorizationCode
     * @param shopTypeId
     */
    private void updateUserInvitation(String authorizationCode, int shopTypeId) {
        UpdateUserInvitationAPI.requestData(mActivity, authorizationCode,shopTypeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateUserInvitationModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateUserInvitationModel updateUserInvitationModel) {
                        if (updateUserInvitationModel.isSuccess()) {
                            UserInfoHelper.saveUserType(mActivity, AppConstant.USER_TYPE_WHOLESALE);
                            UserInfoHelper.saveUserId(mActivity, updateUserInvitationModel.getData());
                            pageNum = 1;
                            requestGoodsList();
                            UserInfoHelper.saveUserHomeRefresh(getContext(), "home_has_refresh");
                        } else {
                            AppHelper.showMsg(mActivity, updateUserInvitationModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 选择店铺类型
     * @param authorizationCode
     */
    private void showSelectType(String authorizationCode) {
        GetRegisterShopAPI.requestData(mActivity, authorizationCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetRegisterShopModel>() {
                    @Override
                    public void onCompleted() {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onNext(GetRegisterShopModel getRegisterShopModel) {
                        UserInfoHelper.saveIsRegister(mActivity, "is_register_type");
                        if (getRegisterShopModel.isSuccess()) {
                            isFirst = true;
                            List<GetRegisterShopModel.DataBean> mList = new ArrayList<>();
                            mList.addAll(getRegisterShopModel.getData());
                            mTypedialog.show();
                            Window window = mTypedialog.getWindow();
                            window.setContentView(R.layout.select_type);
                            WindowManager.LayoutParams attributes = window.getAttributes();
                            attributes.width = LinearLayout.LayoutParams.MATCH_PARENT;
                            attributes.height = LinearLayout.LayoutParams.MATCH_PARENT;
                            window.setAttributes(attributes);
                            RecyclerView rl_type = window.findViewById(R.id.rl_type);
                            TextView tv_ok = window.findViewById(R.id.tv_ok);
                            rl_type.setLayoutManager(new GridLayoutManager(mActivity, 3));
                            RegisterShopAdapterTwo mRegisterAdapterType = new RegisterShopAdapterTwo(mActivity, mList);
                            rl_type.setAdapter(mRegisterAdapterType);
                            mRegisterAdapterType.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    isSelected = position;
                                    mRegisterAdapterType.selectPosition(position);

                                    shopTypeId = mList.get(isSelected).getId();
                                    isChecked = true;
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });

                            tv_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (isChecked) {
                                        mTypedialog.dismiss();
                                        updateUserInvitation(authorizationCode, shopTypeId);
                                    } else {
                                        AppHelper.showMsg(mActivity, "请选择店铺类型");
                                    }
                                }
                            });
                        }
                    }
                });
    }

    /**
     * 获取品牌名称
     */
    private void getSearchProd() {
        RecommendApI.getSearchProd(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProdRecommendModel>() {
                    @Override
                    public void onCompleted() {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onNext(ProdRecommendModel recommendModel) {
                        if (recommendModel.isSuccess()) {
                            mListRecommendProd.clear();
                            mListRecommendProd.addAll(recommendModel.getData());
                            searchProdAdapter.notifyDataSetChanged();

                        } else {
                            AppHelper.showMsg(context, recommendModel.getMessage());
                        }
                    }
                });
    }

    private void getDataThree() {
        Log.d("wodeswhuedhddhjjd.....","222");
        if (Imposition == 0) {
            sendSelectGoodThree("", "", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 1) {
            sendSelectGoodThree("", "1", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 2) {
            sendSelectGoodThree("1", "", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 3) {
            sendSelectGoodThree("", "", "1", selectBrandName, minPrice, maxPrice);
        } else {
            sendSelectGoodThree("", "", "", selectBrandName, minPrice, maxPrice);
        }
    }

    /**
     * 品牌
     * @param saleVolume
     * @param priceUp
     * @param newProduct
     * @param brandName
     * @param minPrices
     * @param maxPrices
     */
    private void sendSelectGoodThree(String saleVolume, String priceUp, String newProduct, String brandName, String minPrices, String maxPrices) {
        Log.d("dsdsdwdwdd....",pageNum+"");
        MarketGoodSelcetAPI.getClassifyRight(mActivity, pageNum, 4, mFirstCode, mSecondCode,
                saleVolume, priceUp, newProduct, brandName, minPrices, maxPrices)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MarketRightModel>() {
                    @Override
                    public void onCompleted() {
                        rv_prod_detail.refreshComplete();
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        lav_activity_loading.hide();
                    }

                    @Override
                    public void onNext(MarketRightModel marketGoodSelectModel) {
                        mModelMarketGoods = marketGoodSelectModel;
                        if (marketGoodSelectModel.isSuccess()) {
//                            Log.d("swsssssssssss.......","0");
                            flag = true;
                            dialog.dismiss();
                            upProdDate(marketGoodSelectModel);
                            lav_activity_loading.hide();
                        } else {
                            AppHelper.showMsg(mActivity, marketGoodSelectModel.getMessage());
                            lav_activity_loading.hide();
                        }
                    }
                });
    }

    /**
     * 更新品牌
     * @param marketGoodSelectModel
     */
    private void upProdDate(MarketRightModel marketGoodSelectModel) {
        if (pageNum == 1) {
            if (marketGoodSelectModel.getData().getBrandProd() != null && marketGoodSelectModel.getData().getBrandProd().getList().size()>0) {
                rv_prod_detail.setVisibility(View.VISIBLE);
                mIvNoData.setVisibility(View.GONE);
                mListProd.clear();
                mListProd.addAll(marketGoodSelectModel.getData().getBrandProd().getList());
                prodAdapter.notifyDataSetChanged();
            } else {
                rv_prod_detail.setVisibility(View.GONE);
                mIvNoData.setVisibility(View.VISIBLE);

            }
        } else {

            mListProd.addAll(marketGoodSelectModel.getData().getBrandProd().getList());
            prodAdapter.notifyDataSetChanged();

        }


        if (marketGoodSelectModel.getData().getBrandProd().isHasNextPage()) {
            hasPage = true;
        } else {
            hasPage = false;
        }


    }



    private void getDataTwo() {
        isCheck = true;
        if (Imposition == 0) {
            sendSelectGoodTwo("", "", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 1) {
            sendSelectGoodTwo("", "1", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 2) {
            sendSelectGoodTwo("1", "", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 3) {
            sendSelectGoodTwo("", "", "1", selectBrandName, minPrice, maxPrice);
        } else {
            sendSelectGoodTwo("", "", "", selectBrandName, minPrice, maxPrice);
        }
    }

    private void getData() {
        isCheck = false;
        if (Imposition == 0) {
            sendSelectGood("", "", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 1) {
            sendSelectGood("", "1", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 2) {
            sendSelectGood("1", "", "", selectBrandName, minPrice, maxPrice);
        } else if (Imposition == 3) {
            sendSelectGood("", "", "1", selectBrandName, minPrice, maxPrice);
        } else {
            sendSelectGood("", "", "", selectBrandName, minPrice, maxPrice);
        }
    }


    /**
     * 请求左侧数据集合
     */
    private void requestGoodsList() {
        MarketGoodsClassifyAPI.getClassify(getContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassIfyModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClassIfyModel marketGoodsModel) {
                        AppHelper.UserLogout(getContext(), marketGoodsModel.getCode(), 0);
                        mModelMarketGoodsClassify = marketGoodsModel;
                        if (mModelMarketGoodsClassify.isSuccess()) {
                            updateGoodsList();
                        } else {
                            if (marketGoodsModel.getCode() == AppConstant.ANOTHER_PLACE_LOGIN) {
                                TwoDeviceHelper.logoutAndToHome(getActivity());
                            } else {
                                AppHelper.showMsg(mActivity, mModelMarketGoodsClassify.getMessage());
                            }
                        }
                    }
                });
    }

    /**
     * 初始化列表数据
     */
    private void updateGoodsList() {
        mList.clear();
        mList.addAll(mModelMarketGoodsClassify.getData());
        mListSecondNow.clear();
        mListSecondNow.addAll(mModelMarketGoodsClassify.getData());
        mAdapterMarketSecond.notifyDataSetChanged();
        mAdapterMarketSecond.selectPosition(0);

        mFirstCode = -1;
        mSecondCode  = 0;
        getData();

    }


    /**
     * 更新数据
     */
    private void updateMarketGoods() {
        if (pageNum == 1) {
            if (mModelMarketGoods.getData().getProdClassify() != null && mModelMarketGoods.getData().getProdClassify().getList().size() > 0) {
                mRvDetail.setVisibility(View.VISIBLE);
                mIvNoData.setVisibility(View.GONE);
                mListGoods.clear();
                mListGoods.addAll(mModelMarketGoods.getData().getProdClassify().getList());
                mAdapterMarketDetail.notifyDataSetChanged();

            } else {
                mRvDetail.setVisibility(View.GONE);
                mIvNoData.setVisibility(View.VISIBLE);

            }
        } else {

            mListGoods.addAll(mModelMarketGoods.getData().getProdClassify().getList());
            mAdapterMarketDetail.notifyDataSetChanged();
            // mAdapterMarketDetail.loadMoreComplete();
        }

        if (mModelMarketGoods.getData().getProdClassify().isHasNextPage()) {

            hasPage = true;
        } else {

            hasPage = false;

        }
    }

    @Override
    public void setClickEvent() {

        mLlSearch.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), SearchStartActivity.class);
                intent.putExtra(AppConstant.SEARCHTYPE, AppConstant.HOME_SEARCH);
                intent.putExtra("flag", "first");
                intent.putExtra("good_buy","");
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        String userMarketRefresh = UserInfoHelper.getUserMarketRefresh(getContext());
//        dialog.show();
        if (StringHelper.notEmptyAndNull(userMarketRefresh)) {

        } else {
            pageNum = 1;
//            getData();
//            requestGoodsList();
            UserInfoHelper.saveUserMarketRefresh(getContext(), "market_has_refresh");
        }
     /*   pageNum = 1;
        requestGoodsList();*/

//        mViewBanner.startAutoCycle(3000, 8000, true);

    }

    /**
     * 获取客服电话
     */
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
     * 请求banner接口 common/product/getTopBanner
     */
    private void requestBanner() {
        IndexHomeAPI.getBanner(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerModel marketBannerModel) {
                        if (marketBannerModel.isSuccess()) {
                            mListBanner.clear();
                            mListBanner.addAll(marketBannerModel.getData());
                            list.clear();
                            for (int i = 0; i < marketBannerModel.getData().size(); i++) {
                                list.add(marketBannerModel.getData().get(i).getDefaultPic());
                            }

                            if (marketBannerModel.getData().size() > 0) {
                                banner.setVisibility(View.VISIBLE);
                                banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                                banner.setImageLoader(new GlideImageLoades());
                                bannerList.clear();
                                bannerList.addAll(list);
                                banner.setImages(bannerList);
                                banner.setBannerAnimation(Transformer.DepthPage);
                                banner.isAutoPlay(true);
                                banner.setDelayTime(3000);
                                banner.setIndicatorGravity(BannerConfig.RIGHT);
                                ClickBanner(marketBannerModel.getData());
                                banner.start();
                            } else {
                                banner.setVisibility(View.GONE);

                            }
                        }

                    }
                });
    }
    private int showType;
    private void ClickBanner(List<BannerModel.DataBean> banners) {
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                showType = banners.get(position).getShowType();
                Log.d("wswswsswsss",banners.get(position).getProdPage()+"");
                if(showType==1|| banners.get(position).getLinkSrc()!=null) {
                    //链接 banners.get(position).getLinkSrc()
                    Intent intent = new Intent(getActivity(), NewWebViewActivity.class);
                    intent.putExtra("URL", banners.get(position).getLinkSrc());
                    intent.putExtra("TYPE", 2);
                    intent.putExtra("name", "");
                    startActivity(intent);
                }

                else if(showType == 2|| banners.get(position).getDetailPic()!=null) {
                    //图片
                    AppHelper.showPhotoDetailDialog(mActivity, bannerList, position);
                }else if(showType == 3|| banners.get(position).getProdPage()!=null) {
                    //H5页面

                    if(AppConstant.KILL_PROD.equals(banners.get(position).getProdPage())) {
                        Intent intent = new Intent(getActivity(), HomeGoodsListActivity.class);
                        startActivity(intent);
                    }else if(AppConstant.HOT_PROD.equals(banners.get(position).getProdPage())){
                        Intent intent = new Intent(getActivity(), HotProductActivity.class);
                        startActivity(intent);
                    }else if(AppConstant.COMMON_PROD.equals(banners.get(position).getProdPage())){
                        Intent intent = new Intent(getActivity(), CommonProductActivity.class);
                        startActivity(intent);
                    }else if(AppConstant.DEDUCT_PROD.equals(banners.get(position).getProdPage())){
                        Intent intent = new Intent(getActivity(), ReductionProductActivity.class);
                        startActivity(intent);
                    }else if(AppConstant.SPECIAL_PROD.equals(banners.get(position).getProdPage())){
                        Intent intent = new Intent(getActivity(), CouponDetailActivity.class);
                        startActivity(intent);
                    }else if(AppConstant.TEAM_PROD.equals(banners.get(position).getProdPage())){
                        Intent intent = new Intent(getActivity(), TeamDetailActivity.class);
                        startActivity(intent);
                    }else if(AppConstant.BALANCE.equals(banners.get(position).getProdPage())){
                        Intent intent = new Intent(getActivity(), MyWalletPointActivity.class);
                        startActivity(intent);
                    }else if(AppConstant.POINT.equals(banners.get(position).getProdPage())){
                        Intent intent = new Intent(getActivity(), MinerIntegralActivity.class);
                        startActivity(intent);
                    }else if(AppConstant.GIFT.equals(banners.get(position).getProdPage())){
                        Intent intent = new Intent(getActivity(), MyCouponsActivity.class);
                        startActivity(intent);
                    }
                }else if(showType ==4 ) {
                    //商品
                    int businessId = banners.get(position).getBusinessId();
                    Intent intent = new Intent(getActivity(), CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, businessId);
                    startActivity(intent);
                }else if(showType ==5 ) {
                    //活动
                    String businessType = String.valueOf(banners.get(position).getBusinessType());
                    int businessId = banners.get(position).getBusinessId();
                    if(businessType.equals("2")) {
                        Intent intent = new Intent(getActivity(), SeckillGoodActivity.class);
                        intent.putExtra(AppConstant.ACTIVEID,businessId );
                        startActivity(intent);
                    }else if(businessType.equals("3")) {
                        Intent intent = new Intent(getActivity(), TeamGoodsDetailActivity.class);
                        intent.putExtra(AppConstant.ACTIVEID, businessId);
                        startActivity(intent);
                    }else if(businessType.equals("11")) {
                        Intent intent = new Intent(getActivity(), SpecialGoodDetailActivity.class);
                        intent.putExtra(AppConstant.ACTIVEID,businessId);
                        startActivity(intent);
                    }

                }

            }
        });
    }

    List<String> list = new ArrayList<>();
    private List<String> bannerList = new ArrayList<>();

    public interface onClick {
        void refreshCartNum(int pos);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginEvent(LoginEvent event) {
        //刷新UI
        requestGoodsList();
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginEvent(LogoutEvent event) {
        //刷新UI
        requestGoodsList();
        getData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void change(CityEvent cityEvent) {
        requestGoodsList();
        getData();

    }

    /**
     * 提示用户去登录还是注册的弹窗
     */
    private void initDialog() {
        couponDialog = new CouponDialog(mActivity) {
            @Override
            public void Login() {
                startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                dismiss();
            }

            @Override
            public void Register() {
//                startActivity(RegisterActivity.getIntent(mActivity, RegisterMessageActivity.class));
                LoginUtil.initRegister(mActivity);
                dismiss();
            }
        };
        couponDialog.show();
    }
}
