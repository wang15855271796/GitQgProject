package com.puyue.www.qiaoge.fragment.market;//package com.puyue.www.qiaoge.fragment.market;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.android.tu.loadingdialog.LoadingDailog;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.daimajia.slider.library.SliderLayout;
//import com.daimajia.slider.library.SliderTypes.BaseSliderView;
//import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
//import com.puyue.www.qiaoge.NewWebViewActivity;
//import com.puyue.www.qiaoge.R;
//import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
//import com.puyue.www.qiaoge.activity.home.SearchStartActivity;
//import com.puyue.www.qiaoge.activity.mine.MessageCenterActivity;
//import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
//import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletActivity;
//import com.puyue.www.qiaoge.adapter.home.CommonProductAdapter;
//import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
//import com.puyue.www.qiaoge.adapter.market.MarketGoodBrandAdapter;
//import com.puyue.www.qiaoge.adapter.market.MarketGoodsAdapter;
//import com.puyue.www.qiaoge.api.cart.AddCartAPI;
//import com.puyue.www.qiaoge.api.cart.MarketAddAPI;
//import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
//import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
//import com.puyue.www.qiaoge.api.market.ClassIfyModel;
//import com.puyue.www.qiaoge.api.market.MarketGoodBannerAPI;
//import com.puyue.www.qiaoge.api.market.MarketGoodNameAPI;
//import com.puyue.www.qiaoge.api.market.MarketGoodSelcetAPI;
//import com.puyue.www.qiaoge.api.market.MarketGoodsClassifyAPI;
//import com.puyue.www.qiaoge.api.market.MarketRightModel;
//import com.puyue.www.qiaoge.base.BaseFragment;
//import com.puyue.www.qiaoge.constant.AppConstant;
//import com.puyue.www.qiaoge.event.OnHttpCallBack;
//import com.puyue.www.qiaoge.helper.AppHelper;
//import com.puyue.www.qiaoge.helper.PublicRequestHelper;
//import com.puyue.www.qiaoge.helper.StringHelper;
//import com.puyue.www.qiaoge.helper.TwoDeviceHelper;
//import com.puyue.www.qiaoge.helper.UserInfoHelper;
//import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
//import com.puyue.www.qiaoge.listener.OnItemClickListener;
//import com.puyue.www.qiaoge.model.cart.AddCartModel;
//import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
//import com.puyue.www.qiaoge.model.cart.MarketAddCartModel;
//import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
//import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
//import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
//import com.puyue.www.qiaoge.model.market.MarketBannerModel;
//import com.puyue.www.qiaoge.model.market.MarketClassifyModel;
//import com.puyue.www.qiaoge.model.market.MarketGoodsModel;
//import com.puyue.www.qiaoge.model.market.MarketSelectGoodModel;
//import com.puyue.www.qiaoge.view.StatusBarUtil;
//import com.puyue.www.qiaoge.view.selectmenu.MenuBar;
//import com.puyue.www.qiaoge.view.selectmenu.MyListView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * Created by Administrator on 2018/3/28.
// */
////需要在这里加一个点击之后重新加载的动画,不能让用户明显地感觉出来列表数据变化
//// 还未加上刷新和加载
//public class MarketFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener{
//    private RelativeLayout mLlSearch;
//    private RecyclerView rv_right;
//    private List<MarketClassifyModel.DataBean> mList = new ArrayList<>();
//    private List<MarketGoodsModel.DataBean.ListBean> mListGoods = new ArrayList<>();
//    private MarketGoodsAdapter mAdapterMarketDetail;
//    private MarketClassifyModel mModelMarketGoodsClassify;
//    private MarketRightModel mModelMarketGoods;
//    private int mFirstClassifyNum;
//    private int mSecondClassifyNum;
//    private int mFirstCode = 0;
//    private int mSecondCode = 0;
//    private int pageNum = 1;//切换一级分类和二级分类的时候都要将这个pageNum置为1
//    private String cell; // 客服电话
//    private SliderLayout mViewBanner;
//    private List<MarketBannerModel.DataBean> mListBanner = new ArrayList<>();
//    private LinearLayout mllMarket;
//    private RelativeLayout mRlSelectGood;
//    private RecyclerView mRyGetGoodName;
//    private RecyclerView mRyBuyName;
//    private EditText mEtLowPrice;
//    private EditText mEtHighPrice;
//    private EditText mEtSearchGood;
//    private TextView mTvReresh;
//    private TextView mTvOk;
//    private ImageView ivSearch;
//    private List<String> mListBrand = new ArrayList<>();
//    //商品名
//    List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> list_right;
//    //品牌集合
//    List<MarketRightModel.DataBean.BrandProdBean> list_prod;
//    private MarketGoodBrandAdapter mAdapterBrand;
//    private String brandName = "";
//    private int isSelected;
//    private String selectBrandName = "";
//    private String minPrice;
//    private String maxPrice;
//    private PopupWindow popupWindow;
//    private ArrayList<View> viewList = new ArrayList<>();
//    private MenuBar mb_bar;
//    private String saleVolume = "";
//    private String priceUp = "";
//    private String newProduct = "";
//    private int Imposition = 0;
//    private boolean isCheck = false;
//    private boolean hasPage = true;
//    private List<GetRegisterShopModel.DataBean> list = new ArrayList<>();
//    public RecyclerView rv_left;
//    int typeId;
//    int shopTypeId;
//    boolean isChe = false;
//    RegisterShopAdapterTwo mRegisterAdapter;
//    Context context;
//    private SwipeRefreshLayout swipe_refresh;
//    private RvDetailAdapter rvDetailAdapter;
//    int page = 1;
//    public boolean isLoadMore = false;//用来区分下拉刷新和上拉加载的数据添加
//    private Handler handler = new Handler();
//    private int firstId;
//    private ProdAdapter prodAdapter;
//    RecyclerView rv_prod;
//    @Override
//    public int setLayoutId() {
//        return R.layout.fragment_market;
//    }
//
//    @Override
//    public void initViews(View view) {
//        initStatusBarWhiteColor();
//
//        swipe_refresh = view.findViewById(R.id.swipe_refresh);
//        rv_prod = (RecyclerView) view.findViewById(R.id.rv_prod);
//        rv_right = (RecyclerView) view.findViewById(R.id.rv_right);
//        swipe_refresh.setColorSchemeResources(R.color.colorAccent);//这个方法是设置SwipeRefreshLayout刷新圈颜色
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);//创建为listview形式的recyclerview
//        rv_right.setLayoutManager(layoutManager);
//        list_right = new ArrayList<>();
//        rvDetailAdapter = new RvDetailAdapter(R.layout.item_noresult_recommend, list_right);
//        rv_right.setAdapter(rvDetailAdapter);
//        rv_right.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipe_refresh.setRefreshing(true);
//                page=1;
//                getProductList("", "", "", "", "","");
//
//            }
//        },2000);
//
////        prodAdapter = new ProdAdapter(R.layout.item_prod,list_prod);
////        rv_prod.setLayoutManager(new GridLayoutManager(context,3));
////        rv_prod.setAdapter(prodAdapter);
////        list_prod = new ArrayList<>();
////        prodAdapter = new ProdAdapter(R.layout.item_prod,list_prod);
////        rv_prod.setLayoutManager(new GridLayoutManager(context,3));
////        rv_prod.setAdapter(prodAdapter);
//
//
//
//        rvDetailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                //这个是适配器自带的上拉加载功能 很方便一个实现方法搞定
//                isLoadMore = true;
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        page++;
//                        getProductList("", "", "", "", "","");
//                    }
//                }, 2000);
//
//            }
//        }, rv_right);
//
//
//        /**
//         * 手动刷新
//         */
//
//        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                isLoadMore = false;
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //模拟一下网络请求
//                        page=1;
//                        getProductList("", "", "", "", "","");
//                    }
//                }, 2000);
//            }
//        });
//
//
//
//
//    }
//
//
//
//
//
//    @Override
//    public void findViewById(View view) {
//       context =  getActivity();
//        mLlSearch = view.findViewById(R.id.ll_market_search);//搜索
//        rv_left = ((RecyclerView) view.findViewById(R.id.rv_left));//二级分类
//        mViewBanner = view.findViewById(R.id.view_market_banner);
//        mRlSelectGood = view.findViewById(R.id.rl_select_good);
//        mllMarket = view.findViewById(R.id.ll_market);
//        getClassifyList();
//        mb_bar = view.findViewById(R.id.mb_bar);
//
//        mRlSelectGood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 一个自定义的布局，作为显示的内容
//                View contentView = LayoutInflater.from(mActivity).inflate(R.layout.market_select_draw, null);
//                mEtSearchGood = contentView.findViewById(R.id.et_activity_search_word);//输入商品名称
//                mEtLowPrice = contentView.findViewById(R.id.et_low_price);//输入最低价
//                mEtHighPrice = contentView.findViewById(R.id.rt_high_price);//输入最高价
//                mRyBuyName = contentView.findViewById(R.id.ry_already_buy_good);//购买过的商品
//                mRyGetGoodName = contentView.findViewById(R.id.recyclerView_search_good);//获取到的商品名
//                mTvReresh = contentView.findViewById(R.id.tv_refresh_good);//重置
//                mTvOk = contentView.findViewById(R.id.tv_ok);//确定
//                ivSearch = contentView.findViewById(R.id.iv_search);
//                int width = LinearLayout.LayoutParams.MATCH_PARENT;
//                int width1 = mActivity.getWindowManager().getDefaultDisplay().getWidth();
//
//
//                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                popupWindow = new PopupWindow(contentView,
//                        width, LinearLayout.LayoutParams.MATCH_PARENT, true);
//                popupWindow.setWidth(width1 * 3 / 4);
//                popupWindow.setAnimationStyle(R.style.AnimationRightFade);
////全屏
//                popupWindow.setClippingEnabled(false);
//                backgroundAlpha(0.3f);
////关闭事件
//                popupWindow.setOnDismissListener(new popupDismissListener());
//
//                popupWindow.setOutsideTouchable(true);
//                popupWindow.setBackgroundDrawable(new BitmapDrawable());
//                popupWindow.setTouchable(true);
//                popupWindow.showAtLocation(mllMarket, Gravity.RIGHT, 0, 0);
//
//                //获取商品名字
//                brandName = "";
//                getGoodName();
//                //获取购买过的商品
//                ivSearch.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (StringHelper.notEmptyAndNull(mEtSearchGood.getText().toString())) {
//
//                            brandName = mEtSearchGood.getText().toString();
//                            getGoodName();
//                        } else {
//                            brandName = "";
//                            getGoodName();
//                        }
//                    }
//                });
//
//
//                mTvReresh.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mEtSearchGood.setText("");
//                        mEtLowPrice.setText("");
//                        mEtHighPrice.setText("");
//
//
//                    }
//                });
//                mTvOk.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mEtSearchGood.setText("");
//                        minPrice = mEtLowPrice.getText().toString();
//                        maxPrice = mEtHighPrice.getText().toString();
//                        getDataTwo();
//                        popupWindow.dismiss();
//
//                    }
//
//                });
//            }
//        });
//
//
//
//    }
//
//
//    /**
//     * 左边数据
//     */
//
//    private void getClassifyList() {
//        MarketGoodSelcetAPI.getClassify(getActivity())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ClassIfyModel>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ClassIfyModel classIfyModels){
//                        rv_left.setLayoutManager(new LinearLayoutManager(mActivity));
//                        ClassifyAdapter classifyAdapter = new ClassifyAdapter(R.layout.item_left_classify,classIfyModels.getData());
//                        rv_left.setAdapter(classifyAdapter);
////                        classifyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
////                            @Override
////                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                                classifyAdapter.selectPosition(position);
////                                firstId = classIfyModels.getData().get(position).getFirstId();
////                                    getProductList(page, firstId, 0,"", "", "", "", "","");
////                                }
////                                InnerAdapter innerAdapter = new InnerAdapter(R.layout.item_inner,classIfyModels.getData().get(position).getSecondClassify());
////                                if(classIfyModels.getData().get(position).getSecondClassify()!=null) {
////                                    classifyAdapter.setOnItemClickListener(new ClassifyAdapter.OnEventClickListener() {
////                                        @Override
////                                        public void onEventClick(int position) {
////                                            //请求右边接口
////
////                                        }
////                                    });
////                                }
////                            }
////                        });
//
//                        classifyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//                            @Override
//                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                                switch (view.getId()) {
//                                    case R.id.tv_name:
//                                        classifyAdapter.selectPosition(position);
//                                        firstId = classIfyModels.getData().get(position).getFirstId();
//                                        getProductList("","","","","","");
//                                        break;
//
//                                }
//                            }
//                        });
//                    }
//                });
//    }
//
//    /**
//     * 获取品牌列表
//     */
//    private void getProdList(int page,int firstId,int secondId,String saleVolume, String priceUp, String newProduct, String brandName, String minPrices, String maxPrices) {
//        MarketGoodSelcetAPI.getClassifyRight(context, page, 10, firstId, secondId, saleVolume, priceUp, newProduct, brandName, minPrices, maxPrices)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MarketRightModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MarketRightModel marketRightModel) {
//                        Log.d("qwqwertewwq..,,jn",marketRightModel.getData().getBrandProd()+"");
//                        List<MarketRightModel.DataBean.BrandProdBean> brandProd = marketRightModel.getData().getBrandProd();
//                        if (marketRightModel.isSuccess()) {
//                            if (swipe_refresh.isRefreshing()) {
//                                swipe_refresh.setRefreshing(false);
//                            }
//                            //右边详情列表
//                            if(isLoadMore) {
//                                list_prod.addAll(brandProd);
//                                prodAdapter.notifyDataSetChanged();
//                                prodAdapter.loadMoreComplete();
//                            }else {
//                                list_prod.clear();
//                                list_prod.addAll(brandProd);
//                                prodAdapter.notifyDataSetChanged();
//                                prodAdapter.loadMoreComplete();
//                            }
//
//                        } else {
//                            AppHelper.showMsg(mActivity, marketRightModel.getMessage());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 获取右边数据(除品牌外)
//     */
//    private void getProductList(String saleVolume, String priceUp, String newProduct, String brandName, String minPrices, String maxPrices) {
//        MarketGoodSelcetAPI.getClassifyRight(context, page, 10, firstId, 0, saleVolume, priceUp, newProduct, brandName, minPrices, maxPrices)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MarketRightModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MarketRightModel marketRightModel) {
//                        List<MarketRightModel.DataBean.ProdClassifyBean.ListBean> list = marketRightModel.getData().getProdClassify().getList();
//                        if (marketRightModel.isSuccess()) {
//                            if (swipe_refresh.isRefreshing()) {
//                                swipe_refresh.setRefreshing(false);
//                            }
//
//                            //右边详情列表
//                            if(isLoadMore) {
//                                list_right.addAll(list);
//                                rvDetailAdapter.notifyDataSetChanged();
//                                rvDetailAdapter.loadMoreComplete();
//                            }else {
//                                list_right.clear();
//                                list_right.addAll(list);
//                                rvDetailAdapter.notifyDataSetChanged();
//                                rvDetailAdapter.loadMoreComplete();
//                            }
//
//
//                        } else {
//                            AppHelper.showMsg(mActivity, marketRightModel.getMessage());
//                        }
//                    }
//                });
//    }
//
//    class popupDismissListener implements PopupWindow.OnDismissListener {
//
//        @Override
//        public void onDismiss() {
//            backgroundAlpha(1f);
//            hintKbTwo();
//        }
//
//    }
//
//
//    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        mActivity.getWindow().setAttributes(lp);
//    }
//
//    //筛选确定
//    private void sendSelectGoodTwo(String saleVolume, String priceUp, String newProduct, String brandName, String minPrices, String maxPrices) {
//        Log.i("lyy", "sendSelectGood: " + mFirstCode + "//" + mSecondCode);
//
//        MarketGoodSelcetAPI.getClassifyRight(mActivity, pageNum, 12, mFirstCode, -1, saleVolume, priceUp, newProduct, brandName, minPrices, maxPrices)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MarketRightModel>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MarketRightModel marketRightModel) {
//
//                        if (marketRightModel.isSuccess()) {
//                            hintKbTwo();
//                            mModelMarketGoods = marketRightModel;
////                            updateMarketGoods();
//                        } else {
//                            AppHelper.showMsg(mActivity, marketRightModel.getMessage());
//                        }
//
//
//                    }
//                });
//    }
//
//    //筛选确定
//    private void sendSelectGood(String saleVolume, String priceUp, String newProduct, String brandName, String minPrices, String maxPrices) {
//
//        MarketGoodSelcetAPI.getClassifyRight(mActivity, pageNum, 12, mFirstCode, mSecondCode, saleVolume, priceUp, newProduct, brandName, minPrices, maxPrices)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MarketRightModel>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MarketRightModel marketRightModel) {
//                        if (marketRightModel.isSuccess()) {
//                            rv_right.setLayoutManager(new LinearLayoutManager(context));
//                            //右边详情列表
//                            RvDetailAdapter rvDetailAdapter = new RvDetailAdapter(R.layout.item_noresult_recommend,marketRightModel.getData().getProdClassify().getList());
//                            rv_right.setAdapter(rvDetailAdapter);
//
////                            selectBrandName = "";
////                            minPrice = "";
////                            maxPrice = "";
////                            hintKbTwo();
////                            dialog.dismiss();
////                            mModelMarketGoods = marketRightModel;
////                            updateMarketGoods();
//                        } else {
//                            AppHelper.showMsg(mActivity, marketRightModel.getMessage());
//                        }
//                    }
//                });
//    }
//
//
//    //获取商品名
//    private void getGoodName() {
//
//        MarketGoodNameAPI.requestMarketName(mActivity, mFirstCode, mSecondCode, brandName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MarketSelectGoodModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MarketSelectGoodModel marketSelectGoodModel) {
//
//                        if (marketSelectGoodModel.isSuccess()) {
//                            mListBrand.clear();
//                            mListBrand.addAll(marketSelectGoodModel.getData());
//                            showGoodBrand();
//
//                        } else {
//                            AppHelper.showMsg(mActivity, marketSelectGoodModel.getMessage());
//                        }
//                    }
//                });
//
//    }
//
//    private void showGoodBrand() {
//        mAdapterBrand = new MarketGoodBrandAdapter(mActivity, mListBrand);
//        mRyGetGoodName.setLayoutManager(new GridLayoutManager(mActivity, 3));
//        mAdapterBrand.setOnItemClickListener(new MarketGoodBrandAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(List<Integer> list) {
//
//                selectBrandName = "";
//                if (list.size() > 0) {
//                    for (int i = 0; i < list.size(); i++) {
//                        selectBrandName += mListBrand.get(list.get(i)) + ",";
//                    }
//                }
//            }
//        });
//
//
//        mRyGetGoodName.setAdapter(mAdapterBrand);
//
//
//    }
//
//    @Override
//    public void setViewData() {
//        ArrayList<String> titles = new ArrayList<>();
//        titles.add("综合排序");
//        ArrayList<String> contentThree = new ArrayList<>();
//        contentThree.add("综合排序");
//        contentThree.add("价格排序");
//        contentThree.add("销量排序");
//        contentThree.add("新品");
//
//
//        MyListView myListView2 = new MyListView(mActivity, contentThree);
//        viewList.add(0, myListView2);
//        mb_bar.setView(titles, viewList);
//        myListView2.setOnSelectListener(new MyListView.OnSelectListener() {
//            @Override
//            public void getValue(String value, int position) {
//                mb_bar.setTitle(value);
//                pageNum = 1;
//                if (value.equals("价格排序")) {
//                    priceUp = "1";
//                    saleVolume = "";
//                    newProduct = "";
//                    selectBrandName = "";
//                    minPrice = "";
//                    maxPrice = "";
//                    Imposition = 1;
//
//                    getProductList(saleVolume, priceUp, newProduct, selectBrandName, minPrice, maxPrice);
//                } else if (value.equals("销量排序")) {
//                    saleVolume = "1";
//                    Imposition = 2;
//                    priceUp = "";
//                    newProduct = "";
//                    minPrice = "";
//                    maxPrice = "";
//                    selectBrandName = "";
//
//                    getProductList(saleVolume, priceUp, newProduct, selectBrandName, minPrice, maxPrice);
//                } else if (value.equals("新品")) {
//                    newProduct = "1";
//                    priceUp = "";
//                    saleVolume = "";
//                    selectBrandName = "";
//                    minPrice = "";
//                    Imposition = 3;
//                    maxPrice = "";
//
//                    getProductList(saleVolume, priceUp, newProduct, selectBrandName, minPrice, maxPrice);
//                } else {
//                    Imposition = 0;
//                    newProduct = "";
//                    priceUp = "";
//                    saleVolume = "";
//                    selectBrandName = "";
//                    minPrice = "";
//                    maxPrice = "";
//
//                    getProductList(saleVolume, priceUp, newProduct, selectBrandName, minPrice, maxPrice);
//                }
//
//
//            }
//        });
//
//
//        requestBanner();
//
//    }
//
//
//    private void getDataTwo() {
//        isCheck = true;
//        if (Imposition == 0) {
//            sendSelectGoodTwo("", "", "", selectBrandName, minPrice, maxPrice);
//        } else if (Imposition == 1) {
//            sendSelectGoodTwo("", "1", "", selectBrandName, minPrice, maxPrice);
//        } else if (Imposition == 2) {
//            sendSelectGoodTwo("1", "", "", selectBrandName, minPrice, maxPrice);
//        } else if (Imposition == 3) {
//            sendSelectGoodTwo("", "", "1", selectBrandName, minPrice, maxPrice);
//        } else {
//            sendSelectGoodTwo("", "", "", selectBrandName, minPrice, maxPrice);
//        }
//    }
//
//
//    @Override
//    public void setClickEvent() {
//        mLlSearch.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View view) {
//                Intent intent = new Intent(getActivity(), SearchStartActivity.class);
//                intent.putExtra(AppConstant.SEARCHTYPE, AppConstant.HOME_SEARCH);
//                intent.putExtra("flag", "first");
//                intent.putExtra("good_buy","");
//                getActivity().startActivity(intent);
//            }
//        });
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        String userMarketRefresh = UserInfoHelper.getUserMarketRefresh(getContext());
//        if (StringHelper.notEmptyAndNull(userMarketRefresh)) {
//
//        } else {
//            UserInfoHelper.saveUserMarketRefresh(getContext(), "market_has_refresh");
//        }
//        mViewBanner.startAutoCycle(3000, 8000, true);
//
//    }
//
//    /**
//     * 请求banner 接口
//     */
//    private void requestBanner() {
//        MarketGoodBannerAPI.requestMarketBanner(mActivity)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MarketBannerModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MarketBannerModel marketBannerModel) {
//
//                        if (marketBannerModel.isSuccess()) {
//                            mListBanner.clear();
//                            mListBanner.addAll(marketBannerModel.getData());
//                            initBanner();
//                        }
//
//                    }
//                });
//    }
//
//    /**
//     * 初始化banner
//     */
//    private void initBanner() {
//        mViewBanner.removeAllSliders();
//
//        if (mListBanner.size() > 0) {
//            mViewBanner.setVisibility(View.VISIBLE);
//            for (int i = 0; i < mListBanner.size(); i++) {
//                //图片轮播
//                DefaultSliderView defaultSliderView = new DefaultSliderView(getContext());
//                defaultSliderView.image(mListBanner.get(i).getPic());
//                defaultSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
//
//                defaultSliderView.setOnSliderClickListener(this);
//                defaultSliderView.bundle(new Bundle());
//                defaultSliderView.getBundle().putString("banner_url", mListBanner.get(i).getUrl());
//                defaultSliderView.getBundle().putString("toPage", mListBanner.get(i).getToPage());
//                mViewBanner.addSlider(defaultSliderView);
//
//            }
//        } else {
//            mViewBanner.setVisibility(View.GONE);
//        }
//
//        mViewBanner.setPresetTransformer(SliderLayout.Transformer.Default);
//        mViewBanner.setIndicatorVisibility(null);
//        //轮播的指示器点点
//     /*     mViewBanner.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//     mViewBanner.setCustomAnimation(new DescriptionAnimation());*/
//        mViewBanner.startAutoCycle(3000, 3000, true);
//
//
//    }
//
//    @Override
//    public void onSliderClick(BaseSliderView slider) {
//        String banner_url = slider.getBundle().getString("banner_url");
//        String toPage = slider.getBundle().getString("toPage");
//        if (StringHelper.notEmptyAndNull(toPage)) {
//            //这个地方也是跳转到之前的H5位置。
//
//            if (toPage.equals("vip")) {
//                //猜测一：将这个跳转修改为跳到NewWebViewActivity即可 不对
//                Intent intent = new Intent(getActivity(), NewWebViewActivity.class);
//                intent.putExtra("URL", banner_url);
//                intent.putExtra("TYPE", toPage);
//                intent.putExtra("name", "");
//                startActivity(intent);
//            } else if (toPage.equals("kill")) {
//
//                Intent intent = new Intent(mActivity, HomeGoodsListActivity.class);
//                intent.putExtra(AppConstant.PAGETYPE, AppConstant.SECONDTYPE);
//                startActivity(intent);
//
//
//            } else if (toPage.equals("team")) {
//                Intent intent = new Intent(mActivity, HomeGoodsListActivity.class);
//                intent.putExtra(AppConstant.PAGETYPE, AppConstant.GROUPTYPE);
//                startActivity(intent);
//
//            } else if (toPage.equals("wallet")) {
//                Intent intent = new Intent(mActivity, MyWalletActivity.class);
//                startActivity(intent);
//            } else if (toPage.equals("notice")) {
////跳转到信息中心
//                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(getActivity()))) {
////                    startActivity(MessageCenterActivity.getIntent(getContext(), MessageCenterActivity.class));
//                    //写一个携带返回结果的跳转
//                    Intent intent = new Intent(getActivity(), MessageCenterActivity.class);
//                    startActivityForResult(intent, 101);
////                    this.startActivityForResult()
//                } else {
//                    AppHelper.showMsg(getActivity(), "请先登录");
//                    startActivity(LoginActivity.getIntent(getActivity(), LoginActivity.class));
//                }
//            } else if (toPage.equals("disable")) {
//
//            }
//
//
////
//        }
//    }
//
//    @Override
//    public void onStop() {
//        mViewBanner.stopAutoCycle();
//        super.onStop();
//    }
//
//    protected void initStatusBarWhiteColor() {
//        //设置状态栏颜色为白色，状态栏图标为黑色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getActivity().getWindow().setStatusBarColor(Color.WHITE);
//            StatusBarUtil.setStatusBarLightMode(getActivity());
//        }
//    }
//
//    //此方法只是关闭软键盘
//    private void hintKbTwo() {
//        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm.isActive() && mActivity.getCurrentFocus() != null) {
//            if (mActivity.getCurrentFocus().getWindowToken() != null) {
//                imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }
//    }
//
//
//    public interface onClick {
//        void refreshCartNum(int pos);
//    }
//
//}