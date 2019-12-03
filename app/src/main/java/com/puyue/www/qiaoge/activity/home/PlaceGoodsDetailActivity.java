package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.ConfirmOrderActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.PlaceCommentListByPageAdapter;
import com.puyue.www.qiaoge.adapter.market.DataAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsDetailAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsRecommendAdapter;
import com.puyue.www.qiaoge.api.home.ClickCollectionAPI;
import com.puyue.www.qiaoge.api.home.GetCustomerPhoneAPI;
import com.puyue.www.qiaoge.api.home.GetPlaceCommentListByPageAPI;
import com.puyue.www.qiaoge.api.home.GetSellPlaceDetailBySellPlaceIdAPI;
import com.puyue.www.qiaoge.api.home.GetSellPlaceDetailPicBySellPlaceIdAPI;
import com.puyue.www.qiaoge.api.home.SellPlaceReserveAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.calendar.bean.DateBean;
import com.puyue.www.qiaoge.calendar.listener.OnPagerChangeListener;
import com.puyue.www.qiaoge.calendar.listener.OnSingleChooseListener;
import com.puyue.www.qiaoge.calendar.utils.CalendarUtil;
import com.puyue.www.qiaoge.calendar.utils.SelectBean;
import com.puyue.www.qiaoge.calendar.weiget.CalendarView;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.BigDecimalUtils;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.ClickCollectionModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetPlaceCommentListByPageModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.GetSellPlaceDetailBySellPlaceIdModel;
import com.puyue.www.qiaoge.model.home.GetSellPlaceDetailPicBySellPlaceIdModel;
import com.puyue.www.qiaoge.model.home.HasCollectModel;
import com.puyue.www.qiaoge.model.home.SellPlaceReserveModel;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/19.
 */

public class PlaceGoodsDetailActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVolume;
    private TextView mTvDesc;

    private CalendarView mCalendar;
    private TextView mTvClean;
    private TextView mTvMonth;
    private ImageView mIvLast;
    private ImageView mIVNext;
    private TextView mTvTotalTime;
    private TextView mTvTotalAmount;

    private SlidingTabLayout mTab;
    private ViewPager mVpData;

    private LinearLayout mLlCustomer;
    private LinearLayout mLlCollection;
    private ImageView mIvCollection;
    private TextView mTvCollection;
    private TextView mTvOnce;

    private int sellPlaceId;
    private byte businessType = 4;
    private List<String> mListBanner = new ArrayList<>();
    private int[] cDate = CalendarUtil.getCurrentDate();
    private String[] titles = {"详情", "评价", "推荐"};
    private List<View> mListView = new ArrayList<>();
    private double price = 0;

    //详情
    private RecyclerView mRvDetail;
    private GoodsDetailAdapter mAdapterDetail;
    private List<GoodsDetailModel> mListDetail = new ArrayList<>();
    //评价
    private RecyclerView mRvEvaluation;
    private NestedScrollView mSvEmpty;
    private PlaceCommentListByPageAdapter mAdapterEvaluation;
    private List<GetPlaceCommentListByPageModel.DataBean.ListBean> mListEvaluation = new ArrayList<>();
    //推荐
    private RecyclerView mRvRecommend;
    private GoodsRecommendAdapter mAdapterRecommend;
    private List<GetProductListModel.DataBean.ListBean> mListRecommend = new ArrayList<>();

    private DataAdapter mAdapterView;
    private int pageNum = 1;
    private int pageSize = 10;
    private boolean isCollection;
    private String cell;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            sellPlaceId = bundle.getInt(AppConstant.ACTIVEID);
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
        setContentView(R.layout.activity_place_details);
    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        mBanner = FVHelper.fv(this, R.id.banner_activity_place);
        mTvTitle = FVHelper.fv(this, R.id.tv_activity_place_title);
        mTvPrice = FVHelper.fv(this, R.id.tv_activity_place_price);
        mTvVolume = FVHelper.fv(this, R.id.tv_activity_place_volume);
        mTvDesc = FVHelper.fv(this, R.id.tv_activity_place_desc);

        mIvLast = FVHelper.fv(this, R.id.iv_activity_place_last);
        mTvMonth = FVHelper.fv(this, R.id.tv_activity_place_month);
        mIVNext = FVHelper.fv(this, R.id.iv_activity_place_next);
        mTvClean = FVHelper.fv(this, R.id.tv_activity_place_clean);
        mCalendar = FVHelper.fv(this, R.id.cv_activity_place);
        mTab = FVHelper.fv(this, R.id.tab_activity_place);
        mVpData = FVHelper.fv(this, R.id.vp_activity_place);

        mTvTotalTime = FVHelper.fv(this, R.id.tv_activity_place_total_day);
        mTvTotalAmount = FVHelper.fv(this, R.id.tv_activity_place_amount);

        mLlCustomer = FVHelper.fv(this, R.id.ll_include_order_customer);
        mLlCollection = FVHelper.fv(this, R.id.ll_include_order_collection);
        mIvCollection = FVHelper.fv(this, R.id.iv_include_order_collection);
        mTvCollection = FVHelper.fv(this, R.id.tv_include_order_collection);
        mTvOnce = FVHelper.fv(this, R.id.tv_include_order_once);

        //详情
        View viewDetail = LayoutInflater.from(this).inflate(R.layout.item_viewpager, null);
        mRvDetail = FVHelper.fv(viewDetail, R.id.rv_item_viewpager);
        mListView.add(viewDetail);
        //评价
        View viewEvaluation = LayoutInflater.from(this).inflate(R.layout.item_viewpager, null);
        mRvEvaluation = FVHelper.fv(viewEvaluation, R.id.rv_item_viewpager);
        mSvEmpty = FVHelper.fv(viewEvaluation, R.id.nsv_item_empty);
        mListView.add(viewEvaluation);
        //推荐
        View viewRecommend = LayoutInflater.from(this).inflate(R.layout.item_viewpager, null);
        mRvRecommend = FVHelper.fv(viewRecommend, R.id.rv_item_viewpager);
        mListView.add(viewRecommend);

    }

    @Override
    public void setViewData() {
        mCalendar.setInitDate(cDate[0] + "." + cDate[1]).init();
        mTvMonth.setText(cDate[0] + "年" + cDate[1] + "月");
        //viewpager设置
        mAdapterView = new DataAdapter(mListView);
        mVpData.setAdapter(mAdapterView);
        //tablayout设置
        mTab.setViewPager(mVpData, titles);
        //设置详情数据
        mAdapterDetail = new GoodsDetailAdapter(mListDetail);
        mRvDetail.setLayoutManager(new LinearLayoutManager(mContext));
        mRvDetail.setAdapter(mAdapterDetail);
        //设置评论数据
        mAdapterEvaluation = new PlaceCommentListByPageAdapter(R.layout.item_goods_evaluation, mListEvaluation);
        mRvEvaluation.setLayoutManager(new LinearLayoutManager(mContext));
        mRvEvaluation.setAdapter(mAdapterEvaluation);
        mAdapterEvaluation.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                getPlaceCommentList(pageNum, pageSize, sellPlaceId);
            }
        }, mRvEvaluation);

        //设置推荐数据
//        mAdapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, mListRecommend);
        mRvRecommend.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        mRvRecommend.setAdapter(mAdapterRecommend);
        //获取数据
        getSellPlaceDetail(sellPlaceId);
        getSellPlacePic(sellPlaceId);
        getProductList();
        getPlaceCommentList(pageNum, pageSize, sellPlaceId);
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            hasCollectState(sellPlaceId, businessType);
        }
        getCustomerPhone();
    }

    /*
    * 获取评价
    * **/
    private void getPlaceCommentList(int pageNum, int pageSize, int sellPlaceId) {
        GetPlaceCommentListByPageAPI.requestData(mContext, pageNum, pageSize, sellPlaceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetPlaceCommentListByPageModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetPlaceCommentListByPageModel model) {
                        if (model.success) {
                            if (model.data.list != null) {
                                mAdapterEvaluation.addData(model.data.list);
                                if (mAdapterEvaluation.getData().size() == 0) {
                                    mRvEvaluation.setVisibility(View.GONE);
                                    mSvEmpty.setVisibility(View.VISIBLE);
                                }
                            } else {
                                mRvEvaluation.setVisibility(View.GONE);
                                mSvEmpty.setVisibility(View.VISIBLE);
                            }
                            if (model.data.hasNextPage) {
                                mAdapterEvaluation.loadMoreComplete();
                            } else {
                                mAdapterEvaluation.loadMoreEnd(false);
                            }

                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }

                    }
                });
    }

    /**
     * 推荐
     **/
    private void getProductList() {
        PublicRequestHelper.getProductList(mContext, 1, 9, "recommend", null, null, null, null, null, new OnHttpCallBack<GetProductListModel>() {
            @Override
            public void onSuccessful(GetProductListModel getProductListModel) {
                if (getProductListModel.success) {
                    if (getProductListModel.data.list != null) {
//                        mAdapterRecommend.addData(getProductListModel.data.list);
                    }
                } else {
                    AppHelper.showMsg(mContext, getProductListModel.message);
                }
            }

            @Override
            public void onFaild(String errorMsg) {

            }
        });
    }

    /**
     * 获取详情图片
     **/
    private void getSellPlacePic(int sellPlaceId) {
        GetSellPlaceDetailPicBySellPlaceIdAPI.requestData(mContext, sellPlaceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetSellPlaceDetailPicBySellPlaceIdModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetSellPlaceDetailPicBySellPlaceIdModel model) {
                        if (model.success) {
                            //填充详情
                            if (model.data != null) {
                                mListDetail.clear();
                                for (int i = 0; i < model.data.size(); i++) {
                                    GoodsDetailModel goodsDetailModel = new GoodsDetailModel(GoodsDetailModel.typeIv);
                                    goodsDetailModel.content = model.data.get(i);
                                    mListDetail.add(goodsDetailModel);
                                }
                            }
                            mAdapterDetail.notifyDataSetChanged();
//                            mAdapterDetail.addData(mListDetail);
                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }
                    }
                });
    }

    /*
    * 获取详情
    * ***/
    private void getSellPlaceDetail(int sellPlaceId) {
        GetSellPlaceDetailBySellPlaceIdAPI.requestData(mContext, sellPlaceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetSellPlaceDetailBySellPlaceIdModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetSellPlaceDetailBySellPlaceIdModel model) {
                        logoutAndToHome(mContext, model.code);
                        if (model.success) {
                            mTvTitle.setText(model.data.name);
                            mTvPrice.setText("¥ " + model.data.price + "/天");
                            price = Double.parseDouble(model.data.price);
                            mTvVolume.setText("预订量：" + model.data.totalReservation);
                            if (model.data.picUrlList != null) {
                                //填充banner
                                //设置banner样式
                                mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                                //设置图片加载器
                                mBanner.setImageLoader(new GlideImageLoader());
                                //设置图片集合
                                mListBanner.addAll(model.data.picUrlList);
                                mBanner.setImages(mListBanner);
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
                                    AppHelper.showPhotoDetailDialog(mContext, mListBanner, position);
                                }
                            });
                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }

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
//                        AppHelper.showMsg(mContext, "点击收藏报错");

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
     * 提交预约
     */
    private void sellPlaceReserve(int sellPlaceId, String startDate, String endDate) {
        SellPlaceReserveAPI.requestData(mContext, sellPlaceId, startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SellPlaceReserveModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SellPlaceReserveModel sellPlaceReserveModel) {
                        if (sellPlaceReserveModel.success) {
                            Intent intent = new Intent(mContext, ConfirmOrderActivity.class);
                            intent.putExtra(AppConstant.ORDERID, sellPlaceReserveModel.data);
                            startActivity(intent);
                        } else {
                            AppHelper.showMsg(PlaceGoodsDetailActivity.this, sellPlaceReserveModel.message);
                        }

                    }
                });
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mIvLast.setOnClickListener(noDoubleClickListener);
        mIVNext.setOnClickListener(noDoubleClickListener);
        mTvClean.setOnClickListener(noDoubleClickListener);
        mCalendar.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                mTvMonth.setText(date[0] + "年" + date[1] + "月");
            }
        });
        mCalendar.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                int dayNum = SelectBean.getDayPoor();
                mTvTotalTime.setText("共" + dayNum + "天");
                mTvTotalAmount.setText("￥" + BigDecimalUtils.mul(price, dayNum, 2));
            }
        });
        mLlCollection.setOnClickListener(noDoubleClickListener);
        mTvOnce.setOnClickListener(noDoubleClickListener);
        mLlCustomer.setOnClickListener(noDoubleClickListener);

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mIvLast) {
                mCalendar.lastMonth();
            } else if (view == mIVNext) {
                mCalendar.nextMonth();
            } else if (view == mTvClean) {
                SelectBean.cleanDate();
                mTvTotalTime.setText("共0天");
                mTvTotalAmount.setText("￥0.00");
                mCalendar.today();
            } else if (view == mLlCollection) {
                //判断是否登录
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (isCollection) {
                        //取消收藏
                        clickCollection(sellPlaceId, businessType, (byte) 0);
                    } else {
                        clickCollection(sellPlaceId, businessType, (byte) 1);
                    }
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mTvOnce) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (SelectBean.startDay != 0 && SelectBean.endDay != 0) {
                        Date starDate = new Date(SelectBean.startDay);
                        Date endDate = new Date(SelectBean.endDay);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        sellPlaceReserve(sellPlaceId, format.format(starDate), format.format(endDate));
                    } else {
                        AppHelper.showMsg(mContext, "请选择完整时间");
                    }
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
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SelectBean.cleanDate();
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
}
