package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.ConfirmOrderActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.GetEquipmentCommentListAdapter;
import com.puyue.www.qiaoge.adapter.market.DataAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsDetailAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsRecommendAdapter;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.ClickCollectionAPI;
import com.puyue.www.qiaoge.api.home.EquipmentReserveAPI;
import com.puyue.www.qiaoge.api.home.GetCustomerPhoneAPI;
import com.puyue.www.qiaoge.api.home.GetEquipmentByIdAndDateAPI;
import com.puyue.www.qiaoge.api.home.GetEquipmentCommentListByPageAPI;
import com.puyue.www.qiaoge.api.home.GetEquipmentDetailPicByEquipmentIdAPI;
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
import com.puyue.www.qiaoge.model.home.EquipmentReserveModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetEquipmentByIdAndDateModel;
import com.puyue.www.qiaoge.model.home.GetEquipmentCommentListByPageModel;
import com.puyue.www.qiaoge.model.home.GetEquipmentDetailPicByEquipmentIdModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.HasCollectModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;
import com.puyue.www.qiaoge.view.GlideModel;

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

public class EquipmentGoodsDetailActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVolume;
    private TextView mTvInven;
    private TextView mTvDesc;

    private CalendarView mCalendar;
    private TextView mTvClean;
    private TextView mTvMonth;
    private ImageView mIvLast;
    private ImageView mIVNext;
    private TextView mTvTotalTime;
    private TextView mTvTotalAmount;

    private ImageView mTvSub;
    private TextView mTvSubAmount;
    private ImageView mTvAdd;
    private LinearLayout mLlToBuy;

    private SlidingTabLayout mTab;
    private ViewPager mVpData;

    private LinearLayout mLlCustomer;
    private LinearLayout mLlCollection;
    private ImageView mIvCollection;
    private TextView mTvCollection;
    private TextView mTvOnce;

    private int equipmentId;
    private List<String> mListBanner = new ArrayList<>();
    private int[] cDate = CalendarUtil.getCurrentDate();
    private String[] titles = {"详情", "评价", "推荐"};
    private List<View> mListView = new ArrayList<>();
    private double price = 0;
    private int amount = 1;
    private int dayNum = 0;
    SearchResultsModel searchResultsModel;
    //详情
    private RecyclerView mRvDetail;
    private GoodsDetailAdapter mAdapterDetail;
    private List<GoodsDetailModel> mListDetail = new ArrayList<>();
    //评价
    private RecyclerView mRvEvaluation;
    private NestedScrollView mSvEmpty;
    private GetEquipmentCommentListAdapter mAdapterEvaluation;
    private List<GetEquipmentCommentListByPageModel.DataBean.ListBean> mListEvaluation = new ArrayList<>();
    //推荐
    private RecyclerView mRvRecommend;
    private GoodsRecommendAdapter mAdapterRecommend;
    private List<GetProductListModel.DataBean.ListBean> mListRecommend = new ArrayList<>();

    private DataAdapter mAdapterView;
    private int pageNum = 1;
    private int pageSize = 10;
    private byte businessType = 6;
    private boolean isCollection;
    private String depositPrice;//押金
    private List<SearchResultsModel.DataBean.SearchProdBean.ListBean> searchList = new ArrayList<>();
    private String cell;
    private String name;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            equipmentId = bundle.getInt(AppConstant.ACTIVEID);
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
        setContentView(R.layout.activity_equipment_details);
    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        mBanner = FVHelper.fv(this, R.id.banner_activity_equipment);
        mTvTitle = FVHelper.fv(this, R.id.tv_activity_equipment_title);
        mTvPrice = FVHelper.fv(this, R.id.tv_activity_equipment_price);
        mTvVolume = FVHelper.fv(this, R.id.tv_activity_equipment_volume);
        mTvInven = FVHelper.fv(this, R.id.tv_activity_equipment_inven);
        mTvDesc = FVHelper.fv(this, R.id.tv_activity_equipment_desc);

        mIvLast = FVHelper.fv(this, R.id.iv_activity_equipment_last);
        mTvMonth = FVHelper.fv(this, R.id.tv_activity_equipment_month);
        mIVNext = FVHelper.fv(this, R.id.iv_activity_equipment_next);
        mTvClean = FVHelper.fv(this, R.id.tv_activity_equipment_clean);
        mCalendar = FVHelper.fv(this, R.id.cv_activity_equipment);
        mTab = FVHelper.fv(this, R.id.tab_activity_equipment);
        mVpData = FVHelper.fv(this, R.id.vp_activity_equipment);


        mTvSub = FVHelper.fv(this, R.id.tv_activity_equipment_sub);
        mTvSubAmount = FVHelper.fv(this, R.id.tv_activity_subscribe_amount);
        mTvAdd = FVHelper.fv(this, R.id.tv_activity_equipment_add);
        mLlToBuy = FVHelper.fv(this, R.id.ll_activity_equipment_tobuy);
        mTvTotalTime = FVHelper.fv(this, R.id.tv_activity_equipment_total_day);
        mTvTotalAmount = FVHelper.fv(this, R.id.tv_activity_equipment_amount);

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
        mAdapterEvaluation = new GetEquipmentCommentListAdapter(R.layout.item_goods_evaluation, mListEvaluation);
        mRvEvaluation.setLayoutManager(new LinearLayoutManager(mContext));
        mRvEvaluation.setAdapter(mAdapterEvaluation);
        mAdapterEvaluation.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                getEquipmentList(pageNum, pageSize, equipmentId);
            }
        }, mRvEvaluation);

        //设置推荐数据
//        mAdapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, searchList);
        mRvRecommend.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        mRvRecommend.setAdapter(mAdapterRecommend);
        //获取数据
        getEquipmentById(equipmentId);
        getEquipmentDetailPic(equipmentId);
        getEquipmentList(pageNum, pageSize, equipmentId);
        getProductList();
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            hasCollectState(equipmentId, businessType);
        }
        getCustomerPhone();
    }

    /**
     * 获取评价
     **/
    private void getEquipmentList(final int pageNum, int pageSize, int equipmentId) {
        GetEquipmentCommentListByPageAPI.requestData(mContext, pageNum, pageSize, equipmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetEquipmentCommentListByPageModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetEquipmentCommentListByPageModel model) {
                        if (model.success) {
                            if (model.data.list != null) {
                                if (pageNum == 1) {
                                    mAdapterEvaluation.setNewData(model.data.list);
                                } else {
                                    mAdapterEvaluation.addData(model.data.list);
                                }
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
                                mAdapterEvaluation.loadMoreEnd();
                            }
                        } else {
                            AppHelper.showMsg(EquipmentGoodsDetailActivity.this, model.message );
                        }
                    }
                });
    }

    /**
    * 获取详情图片
    * **/
    private void getEquipmentDetailPic(int equipmentId) {
        GetEquipmentDetailPicByEquipmentIdAPI.requestData(mContext, equipmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetEquipmentDetailPicByEquipmentIdModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetEquipmentDetailPicByEquipmentIdModel model) {
                        if (model.success) {
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
                             AppHelper.showMsg(mContext, model.message );
                        }
                    }
                });
    }

    /**
    * 获取详情
    * **/
    private void getEquipmentById(int equipmentId) {
        GetEquipmentByIdAndDateAPI.requestData(mContext, equipmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetEquipmentByIdAndDateModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetEquipmentByIdAndDateModel model) {
                        logoutAndToHome(mContext, model.code);

                        if (model.success) {
                            name = model.data.name;
                            mTvTitle.setText(model.data.name);
                            mTvPrice.setText("¥ " + model.data.price + "/天");
                            price = Double.parseDouble(model.data.price);
                            mTvVolume.setText("库存：" + model.data.number);
                            mTvInven.setText("月销：" + model.data.monthSalesVolume);
                            mTvDesc.setText(model.data.desc);
                            depositPrice = model.data.deposit;
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
                            addViewToBuy(model.data);
                        } else {
                             AppHelper.showMsg(mContext, model.message );
                        }

                    }
                });
    }

    /**
     * 直接购买的视图
     */
    private void addViewToBuy(final GetEquipmentByIdAndDateModel.DataBean bean) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);       //LayoutInflater inflater1=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_team_single_buy, null);
        view.setLayoutParams(lp);
        mLlToBuy.addView(view);
        ImageView mIvImg = FVHelper.fv(view, R.id.iv_item_team);
        GlideModel.disPlayPlaceHolder(mContext,bean.mainPic,mIvImg);
       // Glide.with(mContext).load(bean.mainPic).crossFade().placeholder(R.mipmap.icon_default_rec).error(R.mipmap.icon_default_rec).into(mIvImg);
        TextView mTvItem = FVHelper.fv(view, R.id.tv_item_team_title);
        mTvItem.setText(bean.name);
        mTvItem = FVHelper.fv(view, R.id.tv_item_team_spec);
        mTvItem.setText("规格：" + bean.spec);
        mTvItem = FVHelper.fv(view, R.id.tv_item_team_price);
        mTvItem.setText("¥ " + bean.sellPrice + "/件");
        mTvItem = FVHelper.fv(view, R.id.tv_item_team_volume);
        mTvItem.setText("月销:" + bean.monthSalesVolume);
        mTvItem = FVHelper.fv(view, R.id.tv_item_team_inven);
        mTvItem.setText("库存:" + bean.totalSale);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                intent.putExtra(AppConstant.ACTIVEID, bean.id);
                intent.putExtra("equipment", "equipment");
                startActivity(intent);
            }
        });
    }

    /**
     * 推荐
     **/
    private void getProductList() {
        RecommendApI.requestData(mContext,name,pageNum,pageSize)
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
                                mAdapterRecommend.notifyDataSetChanged();

                            }

                        } else {
                            AppHelper.showMsg(mContext, recommendModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 立即预约
     */
    private void equipmentReserve(int equipmentId, String startDate, String endDate, int num) {
        EquipmentReserveAPI.requestData(mContext, equipmentId, startDate, endDate, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EquipmentReserveModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(EquipmentReserveModel equipmentReserveModel) {
                        if (equipmentReserveModel.success) {
                            Intent intent = new Intent(mContext, ConfirmOrderActivity.class);
                            intent.putExtra(AppConstant.ORDERID, equipmentReserveModel.data);
                            startActivity(intent);
                        } else {
                             AppHelper.showMsg(EquipmentGoodsDetailActivity.this, equipmentReserveModel.message );
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
                     AppHelper.showMsg(mContext, hasCollectModel.message );
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
//                         AppHelper.showMsg(mContext, "点击收藏报错" );

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
                             AppHelper.showMsg(mContext, clickCollectionModel.message );
                        }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SelectBean.cleanDate();
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mIvLast.setOnClickListener(noDoubleClickListener);
        mIVNext.setOnClickListener(noDoubleClickListener);
        mTvClean.setOnClickListener(noDoubleClickListener);
        mTvSub.setOnClickListener(noDoubleClickListener);
        mTvAdd.setOnClickListener(noDoubleClickListener);
        mCalendar.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                mTvMonth.setText(date[0] + "年" + date[1] + "月");
            }
        });
        mCalendar.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                dayNum = SelectBean.getDayPoor();
                mTvTotalTime.setText("共" + dayNum + "天");
                mTvTotalAmount.setText("￥" + BigDecimalUtils.mul(BigDecimalUtils.mul(price, dayNum, 2), amount, 2) + "+" + BigDecimalUtils.mul(String.valueOf(depositPrice), String.valueOf(amount), 2) + "押金");
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
            } else if (view == mTvSub) {
                if (amount > 1) {
                    amount--;
                    mTvTotalAmount.setText("￥" + BigDecimalUtils.mul(BigDecimalUtils.mul(price, dayNum, 2), amount, 2) + "+" + BigDecimalUtils.mul(String.valueOf(depositPrice), String.valueOf(amount), 2) + "押金");
                    mTvSubAmount.setText(String.valueOf(amount));
                }
            } else if (view == mTvAdd) {
                amount++;
                mTvTotalAmount.setText("￥" + BigDecimalUtils.mul(BigDecimalUtils.mul(price, dayNum, 2), amount, 2) + "+" + BigDecimalUtils.mul(String.valueOf(depositPrice), String.valueOf(amount), 2) + "押金");
                mTvSubAmount.setText(String.valueOf(amount));
            } else if (view == mLlCollection) {
                //判断是否登录
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (isCollection) {
                        //取消收藏
                        clickCollection(equipmentId, businessType, (byte) 0);
                    } else {
                        clickCollection(equipmentId, businessType, (byte) 1);
                    }
                } else {
                     AppHelper.showMsg(mContext, "请先登录" );
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mTvOnce) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (SelectBean.startDay != 0 && SelectBean.endDay != 0) {
                        Date starDate = new Date(SelectBean.startDay);
                        Date endDate = new Date(SelectBean.endDay);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        equipmentReserve(equipmentId, format.format(starDate), format.format(endDate), amount);
                    } else {
                         AppHelper.showMsg(mContext, "请选择完整时间" );
                    }
                } else {
                     AppHelper.showMsg(mContext, "请先登录" );
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mLlCustomer) {
                if (StringHelper.notEmptyAndNull(cell)) {
                    AppHelper.showPhoneDialog(mContext, cell);
                } else {
                     AppHelper.showMsg(mContext, "获取客服号码失败" );
                }
            }
        }
    };

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
                            AppHelper.showMsg(mContext, getCustomerPhoneModel.getMessage() );
                        }
                    }
                });
    }
}
