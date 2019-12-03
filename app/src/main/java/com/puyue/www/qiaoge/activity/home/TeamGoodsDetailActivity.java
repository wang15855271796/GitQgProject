package com.puyue.www.qiaoge.activity.home;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CartActivity;
import com.puyue.www.qiaoge.activity.cart.ConfirmOrderActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.market.DataAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsDetailAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsEvaluationAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsRecommendAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.cart.GetCartNumAPI;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.ClickCollectionAPI;
import com.puyue.www.qiaoge.api.home.CommentOrderQueryAPI;
import com.puyue.www.qiaoge.api.home.GetCustomerPhoneAPI;
import com.puyue.www.qiaoge.api.home.SecKillOrTeamProductAPI;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryByIdAPI;
import com.puyue.www.qiaoge.api.mine.GetShareInfoAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.BigDecimalUtils;
import com.puyue.www.qiaoge.helper.CollapsingToolbarLayoutStateHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.ClickCollectionModel;
import com.puyue.www.qiaoge.model.home.CommentOrderQueryModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.HasCollectModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.home.SecKillOrTeamProductModel;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryByIdModel;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;
import com.puyue.www.qiaoge.model.mine.GetShareInfoModle;
import com.puyue.www.qiaoge.view.GlideModel;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/19.
 */

public class TeamGoodsDetailActivity extends BaseSwipeActivity {
    private LinearLayout mIvBack;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvInve;
    private TextView mTvVolume;

    private TextView mTvSpec;
    private TextView mTvPlace;
    private TextView mTvDesc;

    private LinearLayout mLlSingle;

    private TextView mTvGroupPrice;
    private ImageView mTvSub;
    private TextView mTvAmount;
    private ImageView mTvAdd;
    private TextView mTvTotalAmount;
    private TextView mTvTotalMoney;
    private LinearLayout mLlCustomer;
    //private LinearLayout mLlCollection;
    private TextView mTvCollection;
    private ImageView mIvCollection;
    private LinearLayout mLlCar;
    private ImageView mIvCar;
    private TextView mTvCarNum;
    private TextView mTvCarAmount;
    private TextView mTvFee;
    private TextView mTvAddCar;
    //banner图片集合
    private List<String> images = new ArrayList<>();
    private List<View> mListView = new ArrayList<>();
    //详情
    private RecyclerView mRvDetail;
    private GoodsDetailAdapter mAdapterDetail;
    private List<GoodsDetailModel> mListDetail = new ArrayList<>();
    //评价
    private RecyclerView mRvEvaluation;
    private GoodsEvaluationAdapter mAdapterEvaluation;
    private NestedScrollView mLlEmpty;
    private List<CommentOrderQueryModel.DataBean.ListBean> mListEvaluation = new ArrayList<>();
    //推荐
    private RecyclerView mRvRecommend;
    private GoodsRecommendAdapter mAdapterRecommend;
    private List<GetProductListModel.DataBean.ListBean> mListRecommend = new ArrayList<>();
    private int masterWorkerId;
    private byte businessType = 3;
    private boolean isCollection;
    private double price;
    private int amount = 0;
    private int pageSize = 10;
    private int pageNum = 1;
    private int inventory = 0;
    private String cell;
    //推荐
    private RecyclerView recyclerViewRecommend;

    // 商品详情
    private RecyclerView recyclerViewImage;
    private GoodsDetailAdapter mAdapterImage;
    private List<GoodsDetailModel> mListDetailImage = new ArrayList<>();
    private TextView textSpec;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private TextView textViewTitle;
    private CollapsingToolbarLayoutStateHelper state;
    // 分享
    private String mShareTitle;
    private String mShareDesc;
    private String mShareIcon;
    private String mShareUrl;
    private ImageView ImageViewShare;

    private ImageView buyImg;

    private LinearLayout linearLayoutShare;
private String productName;
    private GoodsRecommendAdapter adapterRecommend;
    SearchResultsModel searchResultsModel;
    //搜索集合
    private List<SearchResultsModel.DataBean.SearchProdBean.ListBean> searchList = new ArrayList<>();

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = msg.getData().getParcelable("bitmap");
            buyImg.setImageBitmap(bitmap);
        }
    }


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            masterWorkerId = bundle.getInt(AppConstant.ACTIVEID);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        settranslucentStatus();
        setContentView(R.layout.activity_team_details);

    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        mBanner = FVHelper.fv(this, R.id.banner_activity_team);
        mTvTitle = FVHelper.fv(this, R.id.tv_activity_team_title);
        mTvPrice = FVHelper.fv(this, R.id.tv_activity_team_price);
        mTvInve = FVHelper.fv(this, R.id.tv_activity_team_inve);
        mTvVolume = FVHelper.fv(this, R.id.tv_activity_team_volume);

        mTvSpec = FVHelper.fv(this, R.id.tv_activity_team_spec);
        mTvPlace = FVHelper.fv(this, R.id.tv_activity_team_place);
        mTvDesc = FVHelper.fv(this, R.id.tv_activity_team_desc);
        mLlSingle = FVHelper.fv(this, R.id.ll_activity_team_single_buy);

        mTvGroupPrice = FVHelper.fv(this, R.id.tv_activity_team_group_price);
        mTvSub = FVHelper.fv(this, R.id.tv_activity_team_sub);
        mTvAmount = FVHelper.fv(this, R.id.tv_activity_team_amount);
        mTvAdd = FVHelper.fv(this, R.id.tv_activity_team_add);
        mTvTotalAmount = FVHelper.fv(this, R.id.tv_activity_team_group_amount);
        mTvTotalMoney = FVHelper.fv(this, R.id.tv_activity_team_total_money);

      linearLayoutShare=  FVHelper.fv(this, R.id.linearLayout_share);
        mLlCustomer = FVHelper.fv(this, R.id.ll_include_common_customer);
        // mLlCollection = FVHelper.fv(this, R.id.ll_include_common_collection);
        mTvCollection = FVHelper.fv(this, R.id.tv_include_common_collection);
        mIvCollection = FVHelper.fv(this, R.id.iv_include_common_collection);
        mLlCar = FVHelper.fv(this, R.id.ll_include_common_car);
        mIvCar = FVHelper.fv(this, R.id.iv_include_common_car);
        mTvCarNum = FVHelper.fv(this, R.id.tv_include_common_car_number);
        mTvCarAmount = FVHelper.fv(this, R.id.tv_include_common_amount);
        mTvFee = FVHelper.fv(this, R.id.tv_include_common_fee);
        mTvAddCar = FVHelper.fv(this, R.id.tv_add_car);

        //详情
        View viewDetail = LayoutInflater.from(this).inflate(R.layout.item_viewpager, null);
        mRvDetail = FVHelper.fv(viewDetail, R.id.rv_item_viewpager);
        mListView.add(viewDetail);
        //评价
        View viewEvaluation = LayoutInflater.from(this).inflate(R.layout.item_viewpager, null);
        mRvEvaluation = FVHelper.fv(viewEvaluation, R.id.rv_item_viewpager);
        mLlEmpty = FVHelper.fv(viewEvaluation, R.id.nsv_item_empty);
        mListView.add(viewEvaluation);
        //推荐
        View viewRecommend = LayoutInflater.from(this).inflate(R.layout.item_viewpager, null);
        mRvRecommend = FVHelper.fv(viewRecommend, R.id.rv_item_viewpager);
        mListView.add(viewRecommend);

        recyclerViewRecommend = (RecyclerView) findViewById(R.id.recyclerViewRecommend);
        recyclerViewImage = (RecyclerView) findViewById(R.id.recyclerViewImage);
        textSpec = (TextView) findViewById(R.id.textSpec);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textViewTitle = (TextView) findViewById(R.id.textViewTitleSpike);
        ImageViewShare = (ImageView) findViewById(R.id.ImageViewShare);

        adapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, searchList);
        LinearLayoutManager linearLayoutManagerCoupons = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommend.setLayoutManager(linearLayoutManagerCoupons);
        recyclerViewRecommend.setAdapter(adapterRecommend);
    }

    @Override
    public void setViewData() {

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //设置详情数据
        mAdapterDetail = new GoodsDetailAdapter(mListDetail);
        mRvDetail.setLayoutManager(new LinearLayoutManager(mContext));
        mRvDetail.setAdapter(mAdapterDetail);
        //设置评论数据
        mAdapterEvaluation = new GoodsEvaluationAdapter(R.layout.item_goods_evaluation, mListEvaluation);
        mRvEvaluation.setLayoutManager(new LinearLayoutManager(mContext));
        mRvEvaluation.setAdapter(mAdapterEvaluation);
        mAdapterEvaluation.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                commentOrderQuery(pageNum, pageSize);
            }
        }, mRvEvaluation);
        //设置推荐数据
//        mAdapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, mListRecommend);
        mRvRecommend.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        mRvRecommend.setAdapter(mAdapterRecommend);
        //获取数据
        teamActiveQueryById(masterWorkerId);

        commentOrderQuery(pageNum, pageSize);
        getCustomerPhone();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            //有userId
            hasCollectState(masterWorkerId, businessType);
            getCartNum();
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        // mIvCollection.setOnClickListener(noDoubleClickListener);
        mTvAddCar.setOnClickListener(noDoubleClickListener);
        mLlCar.setOnClickListener(noDoubleClickListener);
        mLlCustomer.setOnClickListener(noDoubleClickListener);
        mTvSub.setOnClickListener(noDoubleClickListener);
        mTvAdd.setOnClickListener(noDoubleClickListener);
        linearLayoutShare.setOnClickListener(noDoubleClickListener);

        state = CollapsingToolbarLayoutStateHelper.EXPANDED;
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.app_color_white), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
                toolbar.getBackground().setAlpha((int) (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange() * 255));

                // 展开折叠改变状态
                if (state != CollapsingToolbarLayoutStateHelper.EXPANDED) { //展开的状态
                    state = CollapsingToolbarLayoutStateHelper.EXPANDED;//修改状态标记为展开
                  //  textViewTitle.setVisibility(View.GONE);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutStateHelper.INTERNEDIATE) {
                    //    textViewTitle.setVisibility(View.VISIBLE);
                        state = CollapsingToolbarLayoutStateHelper.INTERNEDIATE;
                    }

                }
            }
        });

    }

    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mIvCollection) {
                //判断是否登录
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (isCollection) {
                        //取消收藏
                        clickCollection(masterWorkerId, businessType, (byte) 0);
                    } else {
                        clickCollection(masterWorkerId, businessType, (byte) 1);
                    }
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mTvSub) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (amount > 1) {
                        amount--;
                        mTvAmount.setText(String.valueOf(amount));
                        mTvTotalAmount.setText("共" + amount + "箱");
                        mTvTotalMoney.setText("￥" + BigDecimalUtils.mul(price, amount, 2));
                    }
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mTvAdd) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (inventory != 0) {
                        amount++;
                        mTvAmount.setText(String.valueOf(amount));
                        mTvTotalAmount.setText("共" + amount + "箱");
                        mTvTotalMoney.setText("￥" + BigDecimalUtils.mul(price, amount, 2));
                    }
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }

            } else if (view == mTvAddCar) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    addCar();
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mLlCustomer) {
                if (StringHelper.notEmptyAndNull(cell)) {
                    AppHelper.showPhoneDialog(mContext, cell);
                } else {
                    AppHelper.showMsg(mContext, "获取客服号码失败");
                }
            } else if (view == mLlCar) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    startActivity(new Intent(mContext, CartActivity.class));
                } else {
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == linearLayoutShare) {
                requestGoodsList();
            }
        }
    };

    /**
     * 推荐
     **/
    private void getProductList() {
        RecommendApI.requestData(mContext,productName,pageNum,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResultsModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchResultsModel recommendModel) {
                        if (recommendModel.isSuccess()) {
                            searchResultsModel = recommendModel;
                            if(recommendModel.getData().getSearchProd()!=null) {
                                searchList.addAll(recommendModel.getData().getSearchProd().getList());
                                adapterRecommend.notifyDataSetChanged();
                                Log.d("weorishssss....",searchList.size()+"");
                            }

                        } else {
                            AppHelper.showMsg(mContext, recommendModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取评价
     **/
    private void commentOrderQuery(final int pageNum, int pageSize) {
        CommentOrderQueryAPI.requestData(mContext, pageNum, pageSize, masterWorkerId, (byte) 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommentOrderQueryModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CommentOrderQueryModel commentOrderQueryModel) {
                        if (commentOrderQueryModel.success) {
                            if (commentOrderQueryModel.data.list != null && commentOrderQueryModel.data.list.size() > 0) {
                                mRvEvaluation.setVisibility(View.VISIBLE);
                                mLlEmpty.setVisibility(View.GONE);
                                if (pageNum == 1) {
                                    mListEvaluation.clear();
                                    mListEvaluation.addAll(commentOrderQueryModel.data.list);
                                    mAdapterEvaluation.notifyDataSetChanged();
                                } else {
                                    mListEvaluation.addAll(commentOrderQueryModel.data.list);
                                    mAdapterEvaluation.notifyDataSetChanged();
                                }
                            } else {
                                mRvEvaluation.setVisibility(View.GONE);
                                mLlEmpty.setVisibility(View.VISIBLE);
                            }
                            if (commentOrderQueryModel.data.hasNextPage) {
                                mAdapterEvaluation.loadMoreComplete();
                            } else {
                                mAdapterEvaluation.loadMoreEnd(false);
                            }
                        } else {
                            AppHelper.showMsg(mContext, commentOrderQueryModel.message);
                        }

                    }
                });
    }

    /*
     * 团购详情
     * **/
    private void teamActiveQueryById(final int activeId) {
        TeamActiveQueryByIdAPI.requestData(mContext, activeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TeamActiveQueryByIdModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TeamActiveQueryByIdModel model) {
                        logoutAndToHome(mContext, model.code);
                        if (model.success) {
                            productName=model.data.activeTitle;
                            //填充banner
                            if (model.data.picCarousel != null) {
                                //设置banner样式
                                mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                                //设置图片加载器
                                mBanner.setImageLoader(new GlideImageLoader());
                                //设置图片集合
                                images.addAll(model.data.picCarousel);
                                mBanner.setImages(images);
                                //设置banner动画效果
                                mBanner.setBannerAnimation(Transformer.DepthPage);
                                //设置自动轮播，默认为true
                                mBanner.isAutoPlay(true);
                                //设置轮播时间
                                mBanner.setDelayTime(3000);
                                //设置指示器位置（当banner模式中有指示器时）
                                mBanner.setIndicatorGravity(BannerConfig.RIGHT);
                                //banner设置方法全部调用完毕时最后调用
                                mBanner.start();
                            }
                            //banner设置点击监听
                            mBanner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    AppHelper.showPhotoDetailDialog(mContext, images, position);
                                }
                            });
                            getProductList();
                            //填充详情
                            mListDetail.clear();
                            if (model.data.picDetail != null) {
                                for (int i = 0; i < model.data.picDetail.size(); i++) {
                                    GoodsDetailModel goodsDetailModel = new GoodsDetailModel(GoodsDetailModel.typeIv);
                                    goodsDetailModel.content = model.data.picDetail.get(i);
                                    mListDetailImage.add(goodsDetailModel);
                                }


                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                mAdapterImage = new GoodsDetailAdapter(mListDetailImage);
                                recyclerViewImage.setLayoutManager(linearLayoutManager);
                                recyclerViewImage.setAdapter(mAdapterImage);

                            }
                            setText(model);

                            mAdapterDetail.notifyDataSetChanged();

                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }

                    }
                });
    }

    private void setText(TeamActiveQueryByIdModel model) {
        mTvTitle.setText(model.data.activeTitle);
        mTvPrice.setText("￥" + model.data.price);
        mTvInve.setText("余量：" + model.data.inventory);
        mTvVolume.setText("销量：" + model.data.monthSalesVolume);
        mTvSpec.setText(model.data.specification);
        mTvPlace.setText(model.data.origin);
        mTvDesc.setText(model.data.instructions);
        textSpec.setText(model.data.unitName);

        if (model.data.prodList != null) {
            setSingleBuyDate(model.data.prodList);
        }
        price = Double.parseDouble(model.data.price);
        amount = 1;
        mTvGroupPrice.setText(model.data.combinationPrice);
        mTvAmount.setText(String.valueOf(amount));
        mTvTotalAmount.setText("共" + amount + "箱");
        mTvTotalMoney.setText("￥" + BigDecimalUtils.mul(price, amount, 2));
        inventory = Integer.parseInt(model.data.inventory);
        if (inventory <= 0) {
            mTvAddCar.setEnabled(false);
            mTvAddCar.setBackgroundResource(R.drawable.app_car);
        } else {
            mTvAddCar.setEnabled(true);
            mTvAddCar.setBackgroundResource(R.drawable.selector_once_buy);

        }
    }

    /*
     * 动态设置单件购买的视图
     * **/
    private void setSingleBuyDate(List<TeamActiveQueryByIdModel.DataBean.ProdListBean> mList) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);       //LayoutInflater inflater1=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int i = 0; i < mList.size() - 1; i++) {
            View view = inflater.inflate(R.layout.item_team_single_buy, null);
            view.setLayoutParams(lp);
            mLlSingle.addView(view);
            setViewDataClick(view, mList.get(i));
            //下划线
            View viewLine = inflater.inflate(R.layout.view_line, null);
            viewLine.setLayoutParams(lp);
            mLlSingle.addView(viewLine);
        }
        View view = inflater.inflate(R.layout.item_team_single_buy, null);
        view.setLayoutParams(lp);
        mLlSingle.addView(view);
        setViewDataClick(view, mList.get(mList.size() - 1));
    }

    /*
     * 设置数据和监听
     * **/
    private void setViewDataClick(View view, final TeamActiveQueryByIdModel.DataBean.ProdListBean bean) {
        ImageView mIvImg = FVHelper.fv(view, R.id.iv_item_team);
        GlideModel.disPlayErrorPlace(mContext,bean.imgUrl,mIvImg);
      //  Glide.with(mContext).load(bean.imgUrl).crossFade().placeholder(R.mipmap.icon_default_rec).error(R.mipmap.icon_default_rec).into(mIvImg);
        TextView mTvItem = FVHelper.fv(view, R.id.tv_item_team_title);
        mTvItem.setText(bean.productName);
        mTvItem = FVHelper.fv(view, R.id.tv_item_team_spec);
        mTvItem.setText(bean.spec);
        mTvItem = FVHelper.fv(view, R.id.tv_item_team_price);
        mTvItem.setText(bean.price);
        mTvItem = FVHelper.fv(view, R.id.tv_item_team_volume);
        mTvItem.setText(bean.monthSalesVolume);
        mTvItem = FVHelper.fv(view, R.id.tv_item_team_inven);
        mTvItem.setText(bean.inventory);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, bean.productId);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取收藏状态
     */
    private void hasCollectState(int businessId, byte businessType) {
        PublicRequestHelper.hasCollectState(mContext, businessId, businessType, new OnHttpCallBack<HasCollectModel>() {
            @Override
            public void onSuccessful(HasCollectModel hasCollectModel) {
                if (hasCollectModel.success) {
                    isCollection = hasCollectModel.data;
                    if (isCollection) {
                        //已收藏
                        mIvCollection.setImageResource(R.mipmap.icon_collection_fill);
                        mTvCollection.setText("已收藏");
                    } else {
                        mIvCollection.setImageResource(R.mipmap.icon_collection_null);
                        mTvCollection.setText("收藏");
                    }
                } else {
                    AppHelper.showMsg(mContext, hasCollectModel.message);
                }
            }

            @Override
            public void onFaild(String errorMsg) {

            }
        });
    }

    /**
     * 点击收藏
     */
    private void clickCollection(int businessId, byte businessType, byte type) {
        ClickCollectionAPI.requestData(mContext, businessId, businessType, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClickCollectionModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        AppHelper.showMsg(TeamGoodsDetailActivity.this, "点击收藏报错");
                    }

                    @Override
                    public void onNext(ClickCollectionModel clickCollectionModel) {
                        if (clickCollectionModel.success) {
                            if (!isCollection) {
                                isCollection = true;
                                mIvCollection.setImageResource(R.mipmap.icon_collection_fill);
                                mTvCollection.setText("已收藏");
                            } else {
                                isCollection = false;
                                mIvCollection.setImageResource(R.mipmap.icon_collection_null);
                                mTvCollection.setText("收藏");
                            }
                        } else {
                            AppHelper.showMsg(mContext, clickCollectionModel.message);
                        }

                    }
                });
    }


    /**
     * 加入购物车
     */
    private void addCar() {
        AddCartAPI.requestData(mContext, masterWorkerId, null, businessType, String.valueOf(amount))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCartModel addCartModel) {
                        if (addCartModel.success) {

                            setAnim(mTvAddCar);
                            getCartNum();
                            AppHelper.showMsg(mContext, "成功加入购物车");

                        } else {
                            AppHelper.showMsg(mContext, addCartModel.message);
                        }

                    }
                });
    }

    public void returnBitMap(String src) {
        MyHandler myHandler = new MyHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap origin = BitmapFactory.decodeStream(input);
                    int width = origin.getWidth();
                    int height = origin.getHeight();
                    Matrix matrix = new Matrix();
                    matrix.preScale(0.07f, 0.07f);
                    Bitmap bitmap = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bitmap", bitmap);
                    message.setData(bundle);
                    myHandler.sendMessage(message);
                    origin.recycle();
                } catch (IOException e) {

                }
            }
        }).start();
    }


    public void setAnim(View view) {
        // TODO Auto-generated method stub
        int[] start_location = new int[2];// 一个整型数组用来存储按钮在屏幕的X,Y坐标
        view.getLocationInWindow(start_location);// 购买按钮在屏幕中的坐标
        buyImg = new ImageView(this);// 动画的小圆圈
        //  buyImg.setImageResource(R.mipmap.ic_launcher);
        returnBitMap(images.get(0));
        setAnim(buyImg, start_location);

    }

    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();// 获得Window界面的最顶层
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        //animLayout.setId();
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup vp, final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    private ViewGroup anim_mask_layout;

    public void setAnim(final View v, int[] start_location) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);

        // 当前位置
        float[] currentPosition = new float[2];
        int[] controlPosition = new int[2];
        View view = addViewToAnimLayout(anim_mask_layout, v, start_location);
        int[] end_location = new int[2];// 存储动画结束位置的X,Y坐标
        mIvCar.getLocationInWindow(end_location);// 将购物车的位置存储起来


        // 计算位移
        int endX = end_location[0] - start_location[0];// 动画位移的X坐标
        int endY = end_location[1] - start_location[1];// 动画位移的y坐标

        controlPosition[0] = (start_location[0] + end_location[0]) / 2;
        controlPosition[1] = ((start_location[1] + 100) / 2);

        Path path = new Path();
        path.moveTo(start_location[0], start_location[1]);
        path.quadTo(controlPosition[0], controlPosition[1], end_location[0], end_location[1]);
        PathMeasure pathMeasure = new PathMeasure();
        // false表示path路径不闭合
        pathMeasure.setPath(path, false);
        // ofFloat是一个生成器
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        // 匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(400);

        valueAnimator.setRepeatCount(0);
        valueAnimator.addUpdateListener(animation -> {
            float value = (Float) animation.getAnimatedValue();
            pathMeasure.getPosTan(value, currentPosition, null);
            buyImg.setX(currentPosition[0]);
            buyImg.setY(currentPosition[1]);
        });
        valueAnimator.start();


        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                v.setVisibility(View.VISIBLE);


            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.GONE);

                valueAnimator.cancel();
                animation.cancel();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(view, "scale", 1.0F, 1.5F, 1.0f)//
                .setDuration(500);//
        anim.setStartDelay(1000);

        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
             /*   mTvAddCar.setScaleX(cVal);
                mTvAddCar.setScaleY(cVal);*/
                mIvCar.setScaleX(cVal);
                mIvCar.setScaleY(cVal);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    /**
     * 获取角标数据
     */
    private void getCartNum() {
        GetCartNumAPI.requestData(mContext)
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
                                mIvCar.setImageResource(R.mipmap.ic_buy_car_fill);
                                mTvCarNum.setVisibility(View.VISIBLE);
                                mTvCarNum.setText(getCartNumModel.getData().getNum());
                                mTvCarAmount.setText("￥" + getCartNumModel.getData().getTotalPrice() + "元");
                                mTvCarAmount.setTextColor(Color.parseColor("#000000"));
                                mTvFee.setVisibility(View.VISIBLE);
                                mTvFee.setText("满" + getCartNumModel.getData().getSendAmount() + "元免配送费");
                            } else {
                                mIvCar.setImageResource(R.mipmap.ic_buy_car);
                                mTvCarNum.setVisibility(View.GONE);
                                mTvCarAmount.setText("未选购商品");
                                mTvCarAmount.setTextColor(Color.parseColor("#A7A7A7"));
                                mTvFee.setVisibility(View.GONE);
                            }
                        } else {
                            AppHelper.showMsg(mContext, getCartNumModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取客服电话
     */
    private void getCustomerPhone() {
        GetCustomerPhoneAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCustomerPhoneModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetCustomerPhoneModel getCustomerPhoneModel) {
                        if (getCustomerPhoneModel.isSuccess()) {
                            cell = getCustomerPhoneModel.getData();
                        } else {
                            AppHelper.showMsg(mContext, getCustomerPhoneModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 分享
     */

    protected void settranslucentStatus() {
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

    // 分享
    private void showInviteDialog() {
        final Dialog dialog = new Dialog(mContext, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_invite, null);
        //获得dialog的window窗口
        Window window = dialog.getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.dialogStyle);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        android.view.WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        dialog.setContentView(dialogView);
        LinearLayout mLlInviteQQ = dialogView.findViewById(R.id.ll_invite_dialog_qq);
        LinearLayout mLlInviteWxCircle = dialogView.findViewById(R.id.ll_invite_dialog_wxcircle);
        LinearLayout mLlInviteWeChat = dialogView.findViewById(R.id.ll_invite_dialog_wechat);
        TextView mTvInviteText = dialogView.findViewById(R.id.tv_invite_dialog_text);
        TextView mTvInviteCancel = dialogView.findViewById(R.id.tv_invite_dialog_cancel);
        dialog.show();
        mLlInviteQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMWeb umWeb = new UMWeb(mShareUrl);
                umWeb.setDescription(mShareDesc);
                umWeb.setThumb(new UMImage(TeamGoodsDetailActivity.this, mShareIcon));
                umWeb.setTitle(mShareTitle);

                new ShareAction(TeamGoodsDetailActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(umWeb)//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
            }
        });


        mLlInviteWeChat.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (StringHelper.notEmptyAndNull(mShareTitle)
                        && StringHelper.notEmptyAndNull(mShareDesc)
                        && StringHelper.notEmptyAndNull(mShareIcon)
                        && StringHelper.notEmptyAndNull(mShareUrl)) {

                    UMWeb umWeb = new UMWeb(mShareUrl);
                    umWeb.setDescription(mShareDesc);
                    umWeb.setThumb(new UMImage(TeamGoodsDetailActivity.this, mShareIcon));
                    umWeb.setTitle(mShareTitle);
                    new ShareAction(TeamGoodsDetailActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    Toast.makeText(TeamGoodsDetailActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        mLlInviteWxCircle.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (StringHelper.notEmptyAndNull(mShareTitle)
                        && StringHelper.notEmptyAndNull(mShareDesc)
                        && StringHelper.notEmptyAndNull(mShareIcon)
                        && StringHelper.notEmptyAndNull(mShareUrl)) {
                    UMWeb umWeb = new UMWeb(mShareUrl);
                    umWeb.setDescription(mShareDesc);
                    umWeb.setThumb(new UMImage(TeamGoodsDetailActivity.this, mShareIcon));
                    umWeb.setTitle(mShareTitle);
                    new ShareAction(TeamGoodsDetailActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    Toast.makeText(TeamGoodsDetailActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        mTvInviteCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                dialog.dismiss();
            }
        });
    }


    // 获取分享内容
    private void requestGoodsList() {
        GetShareInfoAPI.requestGetShareInfoService(mContext, masterWorkerId, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetShareInfoModle>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetShareInfoModle getShareInfoModle) {
                        if (getShareInfoModle.isSuccess()) {
                            mShareTitle = getShareInfoModle.getData().getTitle();
                            mShareDesc = getShareInfoModle.getData().getDesc();
                            mShareIcon = getShareInfoModle.getData().getIcon();
                            mShareUrl = getShareInfoModle.getData().getPageUrl();
                            showInviteDialog();

                        }


                    }
                });
    }

    /**
     * 分享回调
     */
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(TeamGoodsDetailActivity.this, " 收藏成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TeamGoodsDetailActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(MyInviteActivity.this, " 分享失败啦", Toast.LENGTH_SHORT).show();
//            if (t != null) {
//            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(MyInviteActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
