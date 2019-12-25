package com.puyue.www.qiaoge.activity.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CartActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.CommonProductAdapter;
import com.puyue.www.qiaoge.adapter.home.GetProductListAdapter;
import com.puyue.www.qiaoge.adapter.home.ItemConditionAdapter;
import com.puyue.www.qiaoge.adapter.home.NewArrivalAdapter;
import com.puyue.www.qiaoge.adapter.home.PriceReductionAdapter;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.adapter.home.SpecialGoodAdapter;
import com.puyue.www.qiaoge.adapter.home.SpikeActiveAdapter;
import com.puyue.www.qiaoge.adapter.home.SpikeActiveNewAdapter;
import com.puyue.www.qiaoge.adapter.home.SpikeActiveQueryAdapter;
import com.puyue.www.qiaoge.adapter.home.TeamActiveQueryAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.GetCommonProductAPI;
import com.puyue.www.qiaoge.api.home.GetMoreSpecialAPI;
import com.puyue.www.qiaoge.api.home.GetProductListAPI;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.ProductListAPI;
import com.puyue.www.qiaoge.api.home.SecKillMoreListAPI;
import com.puyue.www.qiaoge.api.home.SpikeNewActiveQueryAPI;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.calendar.listener.OnPagerChangeListener;
import com.puyue.www.qiaoge.calendar.utils.CalendarUtil;
import com.puyue.www.qiaoge.calendar.utils.SelectBean;
import com.puyue.www.qiaoge.calendar.weiget.CalendarView;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.TwoDeviceHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.GetCommonProductModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.ItemConditionModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.model.home.SeckillListModel;
import com.puyue.www.qiaoge.model.home.SpecialMoreGoodModel;
import com.puyue.www.qiaoge.model.home.SpikeActiveQueryModel;
import com.puyue.www.qiaoge.model.home.SpikeNewQueryModel;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.market.MarketClassifyModel;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;
import com.puyue.www.qiaoge.view.SuperTextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */
//列表
public class HomeGoodsListActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView mTvTitleScreen;
    private LinearLayout mLlSearchView;
    private LinearLayout mLlSearchClick;
    private TextView mTvSearchScreen;
    private RecyclerView mRvData;
    private RecyclerView mRvSpikeData;
    private PtrClassicFrameLayout mPtrRefresh;
    private CalendarView mCalendar;
    private TextView mTvMonth;
    private ImageView mIvLast;
    private ImageView mIvNext;
    private TextView mTvReset;
    private TextView mTvSure;
    private LinearLayout mLlScreen;
    private RecyclerView mRvLevelOne;
    private RecyclerView mRvLevelTwo;
    private LinearLayout mLlScreenPart;
    private LinearLayout mLlScreenDate;
    private RadioGroup mRgSort;
    private LinearLayout rootview;

    //秒杀预告，特惠
    private LinearLayout linearLayoutSpike;
    private TextView tvSpike;
    private SnapUpCountDownTimerView snapSipkeTime;


    ///热销商品/特别推荐,不改
    private GetProductListAdapter mAdapterNew;
    private List<GetProductListModel.DataBean.ListBean> mListNew = new ArrayList<>();

    //新品上市
    private NewArrivalAdapter adapterNewArrival;
    private List<GetProductListModel.DataBean.ListBean> mNewArrivaList = new ArrayList<>();
    //新秒杀活动
    private SpikeActiveNewAdapter mAdapterNewSpike;
    private List<SpikeNewQueryModel.DataBean> mListSpikeNew = new ArrayList<>();

    private SpikeActiveQueryAdapter mAdapterSpikeQuery;
    private List<SpikeNewQueryModel.DataBean> mListSpikeQuery = new ArrayList<>();

    //秒杀活动
    private SpikeActiveAdapter mAdapterSpike;
    private List<SpikeActiveQueryModel.DataBean.ListBean> mListSpike = new ArrayList<>();
    private List<SeckillListModel.DataBean.KillsBean> mListSeckill = new ArrayList<>();

    //常用清单
    private CommonProductAdapter mAdapterCommon;
    private List<GetCommonProductModel.DataBean.ListBean> mListCommon = new ArrayList<>();
    private String cell; // 客服电话

    private int addPosition;

    private int currentPosition;
    private String pageType;
    private int pageNum = 1;
    private int pageSize = 9;
    private int[] cDate = CalendarUtil.getCurrentDate();

    private ItemConditionAdapter mAdapterOneLevle;
    private List<ItemConditionModel> mListOnePart = new ArrayList<>();
    private ItemConditionAdapter mAdapterTwoLevel;
    private List<ItemConditionModel> mListTwoPart = new ArrayList<>();
    private MarketClassifyModel marketClassifyModel;

    private String screenOneId;
    private String screenTwoId;
    private String salesVolume;
    private String priceSorting;
    private byte sales = 0;//(0全部，1购物，2充值，3提现，4退款)
    private String searchType = String.valueOf(1);//销量降序1，价格升序2，价格降序3
    private String startDate;
    private String endDate;
    private LoadingDailog dialog;

    private SnapUpCountDownTimerView spikeTimeOut;
    private TextView mTvSpikeTitle;
    private boolean isFirst;


//    private ImageView iv_home_search;
    private RelativeLayout rl_good_cart;
    private SuperTextView text_cart_num;


    private List<GetRegisterShopModel.DataBean> list = new ArrayList<>();


    int isSelected;
    int shopTypeId;
    boolean isChecked = false;
    RegisterShopAdapterTwo mRegisterAdapter;


    private RelativeLayout rl_spike;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            pageType = bundle.getString(AppConstant.PAGETYPE, null);
            isFirst = true;
            Log.i("aaaa", "handleExtra: " + pageType);
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
        setContentView(R.layout.activity_home_goods_list);
    }

    @Override
    public void findViewById() {
        rootview = FVHelper.fv(this, R.id.rootview);
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        mTvTitle = FVHelper.fv(this, R.id.tv_activity_goods_list_title);
        mTvTitleScreen = FVHelper.fv(this, R.id.tv_activity_goods_title_screen);
        mLlSearchView = FVHelper.fv(this, R.id.ll_activity_goods_list_search_view);
        mLlSearchClick = FVHelper.fv(this, R.id.ll_activity_goods_list_search_click);
        mTvSearchScreen = FVHelper.fv(this, R.id.tv_activity_goods_list_search_screen);
        mRvData = FVHelper.fv(this, R.id.rv_activity_goods_list);
        mPtrRefresh = FVHelper.fv(this, R.id.ptr_activity_goods_list);
        mCalendar = FVHelper.fv(this, R.id.cv_screen_calendar);
        mIvLast = FVHelper.fv(this, R.id.iv_screen_last);
        mIvNext = FVHelper.fv(this, R.id.iv_screen_next);
        mTvMonth = FVHelper.fv(this, R.id.tv_screen_month);
        mTvReset = FVHelper.fv(this, R.id.tv_activity_list_reset);
        mTvSure = FVHelper.fv(this, R.id.tv_activity_list_sure);
        mLlScreen = FVHelper.fv(this, R.id.ll_activity_screen);

        mRvLevelOne = FVHelper.fv(this, R.id.rv_view_screen_level_one);
        mRvLevelTwo = FVHelper.fv(this, R.id.rv_view_screen_level_two);
        mLlScreenPart = FVHelper.fv(this, R.id.ll_activity_screen_part);
        mLlScreenDate = FVHelper.fv(this, R.id.ll_activity_screen_date);
        mRgSort = FVHelper.fv(this, R.id.rg_activity_screen);

        //   private LinearLayout linearLayoutSpike;
        //    private TextView tvSpike;
        //    private SnapUpCountDownTimerView SnapSipkeTime;
        linearLayoutSpike = FVHelper.fv(this, R.id.linearLayout_spike);
        tvSpike = FVHelper.fv(this, R.id.tv_spike_content);
        snapSipkeTime = FVHelper.fv(this, R.id.snap_down_time);
        spikeTimeOut = FVHelper.fv(this, R.id.snap_down_time);
        mRvSpikeData = FVHelper.fv(this, R.id.recyclerview_spike_content);
        mTvSpikeTitle = FVHelper.fv(this, R.id.tv_spike_content);
//        iv_home_search = FVHelper.fv(this, R.id.iv_home_search);
        rl_good_cart = FVHelper.fv(this, R.id.rl_good_cart);
        text_cart_num = FVHelper.fv(this, R.id.text_cart_num);
        rl_spike = FVHelper.fv(this, R.id.rl_spike);
    }

    @Override
    public void setViewData() {
        setTranslucentStatus();
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            getCartNum();
        }

        if (AppConstant.SECONDTYPE.equals(pageType) || AppConstant.GROUPTYPE.equals(pageType)) {
//            iv_home_search.setVisibility(View.GONE);
        } else {
//            iv_home_search.setVisibility(View.VISIBLE);
        }

        mRvData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    mPtrRefresh.setEnabled(false);
                } else {
                    mPtrRefresh.setEnabled(true);
                }
            }
        });
        //新秒杀专区
        //shamgian
        mAdapterNewSpike = new SpikeActiveNewAdapter(mContext, mListSpikeNew);
        mAdapterSpikeQuery = new SpikeActiveQueryAdapter(R.layout.spike_new_active_product, mListSeckill);



        mAdapterNew = new GetProductListAdapter(R.layout.item_getproduct_adapter, mListNew, new GetProductListAdapter.onClick() {
            @Override
            public void shoppingCartOnclick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    setRecommendOnclick(position);
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }

                mAdapterNew.notifyDataSetChanged();

            }
        });


        mAdapterSpike = new SpikeActiveAdapter(R.layout.item_spike_active, mListSpike, new SpikeActiveAdapter.Onclick() {
            @Override
            public void addCarOnclick(int position) {
                //秒杀特惠
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    SpikeActiveQueryModel.DataBean.ListBean info = mListSpike.get(position);
                    addCar(info.activeId, "", 2, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                mAdapterSpike.notifyDataSetChanged();
            }
        });




        // 新品上市
        adapterNewArrival = new NewArrivalAdapter(R.layout.activity_item_new_arrival, mNewArrivaList, new NewArrivalAdapter.onClick() {
            @Override
            public void shoppingCartOnclick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    setNewOnclick(position);
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }

                adapterNewArrival.notifyDataSetChanged();
            }
        });


        mAdapterCommon = new CommonProductAdapter(R.layout.activity_home_list, mListCommon, new CommonProductAdapter.Onclick() {
            @Override
            public void shoppingCart(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {

                    setComProduct(position);
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }

                mAdapterCommon.notifyDataSetChanged();

            }
        });


        mCalendar.setInitDate(cDate[0] + "." + cDate[1]).init();
        mTvMonth.setText(cDate[0] + "年" + cDate[1] + "月");
        //设置一级分类
        mRvLevelOne.setNestedScrollingEnabled(false);
        mRvLevelOne.setLayoutManager(new GridLayoutManager(mContext, 4));
        mAdapterOneLevle = new ItemConditionAdapter(R.layout.item_screen_condition, mListOnePart);
        mAdapterOneLevle.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                screenOneId = null;
                screenTwoId = null;
                for (int i = 0; i < mListOnePart.size(); i++) {
                    mListOnePart.get(i).isSelected = false;
                }
                mListOnePart.get(position).isSelected = true;
                screenOneId = String.valueOf(mListOnePart.get(position).conditionId);
                mAdapterOneLevle.setNewData(mListOnePart);
                if (marketClassifyModel != null) {
                    mAdapterTwoLevel.getData().clear();
                    for (int i = 0; i < marketClassifyModel.data.get(position).secondClassifyList.size(); i++) {
                        mListTwoPart.add(new ItemConditionModel(marketClassifyModel.data.get(position).secondClassifyList.get(i).secondClassifyName, marketClassifyModel.data.get(position).secondClassifyList.get(i).secondClassifyId, false));
                    }
                    mAdapterTwoLevel.setNewData(mListTwoPart);
                }
            }
        });
        mRvLevelOne.setAdapter(mAdapterOneLevle);
        //设置二级分类
        mRvLevelTwo.setNestedScrollingEnabled(false);
        mRvLevelTwo.setLayoutManager(new GridLayoutManager(mContext, 4));
        mAdapterTwoLevel = new ItemConditionAdapter(R.layout.item_screen_condition, mListTwoPart);
        mAdapterTwoLevel.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mListTwoPart.size(); i++) {
                    mListTwoPart.get(i).isSelected = false;
                }
                mListTwoPart.get(position).isSelected = true;
                screenTwoId = String.valueOf(mListTwoPart.get(position).conditionId);
                mAdapterTwoLevel.setNewData(mListTwoPart);
            }
        });
        mRvLevelTwo.setAdapter(mAdapterTwoLevel);

        judgePageType();//进行差异性的设置。
        judgeRefreshData();
        getCustomerPhone();

    }

    //清单加入购物车
    private void setComProduct(int position) {
        if (mListCommon.size() > 0) {


            GetCommonProductModel.DataBean.ListBean listBean = mListCommon.get(position);

            Log.i("ddddda", "setComProduct: " + listBean.productName + listBean.price);

            JSONArray array = new JSONArray();
            try {
                JSONObject object = new JSONObject();
                object.put("productCombinationPriceId", mListCommon.get(position).productCombinationPriceId);
                object.put("totalNum", "1");
                array.put(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String productCombinationPriceVOList = array.toString();

            String totalNum = "";
            if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                // 特别推荐 先判断是零售用户还是批发用户  在判断是否是批发商品
                // 批发的商品是零售用户的话没有授权需要弹授权码的弹窗 成为批发用户直接添加购物车
                // 批发用户购买零售直接添加购物车
                if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_RETAIL)) {
                    //这个用户是零售用户
                    if ("批发".equals(listBean.type)) {
                        if (StringHelper.notEmptyAndNull(cell)) {
                            AppHelper.showAuthorizationDialog(mActivity, cell, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) ) {
                                        AppHelper.hideAuthorizationDialog();
                                        showDialog();
                                    } else {
                                        AppHelper.showMsg(mActivity, "请输入完整授权码");
                                    }
                                }
                            });
                        }
                    } else {
                        addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);

                    }
                } else if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_WHOLESALE)) {
                    //这个用户是批发用户

                    addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);

                }
            } else {
                AppHelper.showMsg(mActivity, "请先登录");
                startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
            }
        }

    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mLlSearchClick.setOnClickListener(noDoubleClickListener);
        mTvTitleScreen.setOnClickListener(noDoubleClickListener);
        mTvSearchScreen.setOnClickListener(noDoubleClickListener);
        mIvLast.setOnClickListener(noDoubleClickListener);
        mIvNext.setOnClickListener(noDoubleClickListener);
        mTvReset.setOnClickListener(noDoubleClickListener);
        mTvSure.setOnClickListener(noDoubleClickListener);
        mPtrRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                judgeRefreshData();
            }
        });
        mCalendar.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                mTvMonth.setText(date[0] + "年" + date[1] + "月");
            }
        });

//        iv_home_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, SearchStartActivity.class);
//                intent.putExtra(AppConstant.SEARCHTYPE, AppConstant.HOME_SEARCH);
//                intent.putExtra("flag", "first");
//                if (AppConstant.COMMONTYPE.equals(pageType)) {
//                    intent.putExtra("good_buy", "common");
//                } else {
//                    intent.putExtra("good_buy", "");
//                }
//                startActivity(intent);
//            }
//        });


        rl_good_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    startActivity(new Intent(mContext, CartActivity.class));
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }


            }
        });

    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                backEvent();
            } else if (view == mLlSearchClick) {
                Intent intent = new Intent(mContext, SearchStartActivity.class);
                intent.putExtra(AppConstant.SEARCHTYPE, AppConstant.HOME_SEARCH);
                intent.putExtra("flag", "first");

                if (AppConstant.COMMONTYPE.equals(pageType)) {
                    intent.putExtra("good_buy", "common");
                } else {
                    intent.putExtra("good_buy", "");
                }

                startActivity(intent);
            } else if (view == mTvTitleScreen || view == mTvSearchScreen) {
                if ("isShow".equals(mLlScreen.getTag())) {
                    mLlScreen.setVisibility(View.GONE);
                    mLlScreen.setTag("isHide");
                } else {
                    mLlScreen.setVisibility(View.VISIBLE);
                    mLlScreen.setTag("isShow");
                }
            } else if (view == mIvLast) {
                mCalendar.lastMonth();
            } else if (view == mIvNext) {
                mCalendar.nextMonth();
            } else if (view == mTvReset) {
                mRgSort.clearCheck();
                if (mLlScreenDate.getVisibility() == View.VISIBLE) {
                    SelectBean.cleanDate();
                    mCalendar.today();
                    startDate = null;
                    endDate = null;
                } else {
                    mListOnePart.clear();
                    for (int i = 0; i < marketClassifyModel.data.size(); i++) {
                        mListOnePart.add(new ItemConditionModel(marketClassifyModel.data.get(i).firstClassifyName, marketClassifyModel.data.get(i).firstClassifyId, false));
                    }
                    mAdapterOneLevle.setNewData(mListOnePart);
                    mListTwoPart.clear();
                    mAdapterTwoLevel.setNewData(mListTwoPart);
                    screenOneId = null;
                    screenTwoId = null;
                }
                searchType = String.valueOf(1);
            } else if (view == mTvSure) {
                if (mRgSort.getCheckedRadioButtonId() == R.id.rb_activity_screen_sales) {
                    //销量
                    sales = 1;
                    salesVolume = "salesVolume";
                    priceSorting = null;
                    searchType = String.valueOf(1);
                } else if (mRgSort.getCheckedRadioButtonId() == R.id.rb_activity_screen_asc) {
                    //价格升序
                    sales = 2;
                    salesVolume = null;
                    priceSorting = "asc";
                    searchType = String.valueOf(2);
                } else if (mRgSort.getCheckedRadioButtonId() == R.id.rb_activity_screen_desc) {
                    //价格降序
                    sales = 3;
                    salesVolume = null;
                    priceSorting = "desc";
                    searchType = String.valueOf(3);
                } else {
                    sales = 0;
                    salesVolume = null;
                    priceSorting = null;
                    searchType = String.valueOf(1);
                }
                if (mLlScreenDate.getVisibility() == View.VISIBLE) {
                    if (SelectBean.startDay != 0 && SelectBean.endDay != 0) {
                        Date sDate = new Date(SelectBean.startDay);
                        Date eDate = new Date(SelectBean.endDay);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        startDate = format.format(sDate);
                        endDate = format.format(eDate);
                        mLlScreen.setVisibility(View.GONE);
                        mLlScreen.setTag("isHide");
                    } else if (SelectBean.startDay != 0 && SelectBean.endDay == 0) {
                        AppHelper.showMsg(mContext, "请选择结束时间");
                    } else {
                        startDate = null;
                        endDate = null;
                        mLlScreen.setVisibility(View.GONE);
                        mLlScreen.setTag("isHide");
                    }
                } else {
                    mLlScreen.setVisibility(View.GONE);
                    mLlScreen.setTag("isHide");
                }
                judgeRefreshData();
            }
        }
    };

    /**
     * 判断是哪种类型的页面
     **/
    private void judgePageType() {
        if (AppConstant.SPECIAL.equals(pageType)) {
            mRvData.setBackgroundColor(Color.parseColor("#F5F5F5"));
            mRvData.setLayoutManager(new LinearLayoutManager(mContext));
            mTvTitle.setText("折扣专区");
            rootview.setBackgroundColor(Color.WHITE);
            mTvTitleScreen.setVisibility(View.GONE);
            linearLayoutSpike.setVisibility(View.GONE);
        }

        mLlScreenDate.setVisibility(View.GONE);
        if (AppConstant.NEWTYPE.equals(pageType)) {
            mRvData.setBackgroundColor(Color.parseColor("#F5F5F5"));
            mRvData.setLayoutManager(new GridLayoutManager(mContext, 3));
            mTvTitle.setText("新品上市");
            rootview.setBackgroundColor(Color.WHITE);
            mTvTitleScreen.setVisibility(View.GONE);
            linearLayoutSpike.setVisibility(View.GONE);

            //添加分隔线
            DividerItemDecoration dividerItemDecorationMRvRecommed = new DividerItemDecoration(mActivity,
                    DividerItemDecoration.HORIZONTAL_LIST);
            dividerItemDecorationMRvRecommed.setDivider(R.drawable.app_divider);
            mRvData.addItemDecoration(dividerItemDecorationMRvRecommed);


            mRvData.setAdapter(adapterNewArrival);
            adapterNewArrival.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    judgeLoadMoreData();
                }
            }, mRvData);
            adapterNewArrival.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, adapterNewArrival.getData().get(position).productId);
                    startActivity(intent);
                }
            });
            adapterNewArrival.setEmptyView(AppHelper.getLoadingView(mContext));
            mLlScreenDate.setVisibility(View.GONE);
        } else if (AppConstant.SECONDTYPE.equals(pageType)) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRvSpikeData.setLayoutManager(linearLayoutManager);
            mTvTitle.setText("秒杀专区");
            mTvTitleScreen.setVisibility(View.GONE);

            linearLayoutSpike.setVisibility(View.VISIBLE);

            mRvSpikeData.setAdapter(mAdapterNewSpike);
            mRvData.setLayoutManager(new LinearLayoutManager(mContext));
            mRvData.setBackgroundColor(Color.parseColor("#F5F5F5"));
            mRvData.setAdapter(mAdapterSpikeQuery);
         /*   if (mListSpikeNew.size() > 0) {
                // mRvSpikeData.getChildAt(0).performClick();
                mPtrRefresh.autoRefresh();
            }*/


            //mAdapterNewSpike,上面的。  mAdapterSpikeQuery 下面的
            mAdapterNewSpike.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    mAdapterNewSpike.selectPosition(position);

                    if (mListSpikeNew.get(position).getFlag() == 1) {
                        mTvSpikeTitle.setText("秒杀抢购中");
                        snapSipkeTime.setVisibility(View.VISIBLE);
                    } else if (mListSpikeNew.get(position).getFlag() == 0) {
                        mTvSpikeTitle.setText("秒杀即将开始");
                        snapSipkeTime.setVisibility(View.GONE);

                    }

                    spikeActiveQuery(mListSpikeNew.get(position).getActiveId());

                    currentPosition = position;








                 /*   // Log.i("wwwwww", "onItemClick: "+ mAdapterSpike.getData().get(position).activeId);
                    Intent intent = new Intent(mContext, SpikeGoodsDetailsActivity.class);

                    intent.putExtra(AppConstant.ACTIVEID, mAdapterSpike.getData().get(position).activeId);
                    startActivity(intent);*/


                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });


            //mRvData  下面的recyclerview这个是和其他模块公用的
            mAdapterSpikeQuery.setOnClick(new SpikeActiveQueryAdapter.Onclick() {
                @Override
                public void addShop(int position) {
                    addPosition = position;
                    if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {


                        addCar(mListSeckill.get(position).activeId, "", 2, "1");

                    } else {
                        AppHelper.showMsg(mActivity, "请先登录");
                        startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                    }
                    mAdapterSpikeQuery.notifyDataSetChanged();
                }
            });
            mAdapterSpikeQuery.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(mContext, SpikeGoodsDetailsActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, mListSeckill.get(position).activeId);
                    startActivity(intent);
                }
            });


            //mAdapterNewSpike.setEmptyView(AppHelper.getLoadingView(mContext));
            mLlScreenDate.setVisibility(View.GONE);
        } else if (AppConstant.HOTTYPE.equals(pageType)) {
            mTvTitle.setText("热销商品");
            linearLayoutSpike.setVisibility(View.GONE);
            mRvData.setBackgroundColor(Color.parseColor("#F5F5F5"));
            mRvData.setLayoutManager(new GridLayoutManager(mContext, 3));
            mTvTitleScreen.setVisibility(View.GONE);
            mLlSearchView.setVisibility(View.GONE);
            mRvData.setAdapter(mAdapterNew);

            //添加分隔线
            DividerItemDecoration dividerItemDecorationMRvRecommed = new DividerItemDecoration(mActivity,
                    DividerItemDecoration.HORIZONTAL_LIST);
            dividerItemDecorationMRvRecommed.setDivider(R.drawable.app_divider);
            mRvData.addItemDecoration(dividerItemDecorationMRvRecommed);
            mAdapterNew.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    judgeLoadMoreData();
                }
            }, mRvData);
            mAdapterNew.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, mAdapterNew.getData().get(position).productId);
                    startActivity(intent);
                }
            });
            mAdapterNew.setEmptyView(AppHelper.getLoadingView(mContext));
            mLlScreenDate.setVisibility(View.GONE);

        }   else if (AppConstant.COMMONTYPE.equals(pageType)) {
            linearLayoutSpike.setVisibility(View.GONE);
            mRvData.setBackgroundColor(Color.parseColor("#F5F5F5"));
            mRvData.setLayoutManager(new GridLayoutManager(mContext, 3));
            mTvTitle.setText("常用清单");
            mTvTitleScreen.setVisibility(View.VISIBLE);
            mLlSearchView.setVisibility(View.GONE);
            mTvTitleScreen.setVisibility(View.GONE);
            mRvData.setAdapter(mAdapterCommon);

            //添加分隔线
            DividerItemDecoration dividerItemDecorationMRvRecommed = new DividerItemDecoration(mActivity,
                    DividerItemDecoration.HORIZONTAL_LIST);
            dividerItemDecorationMRvRecommed.setDivider(R.drawable.app_divider);
            mRvData.addItemDecoration(dividerItemDecorationMRvRecommed);

            mAdapterCommon.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    judgeLoadMoreData();
                }
            }, mRvData);
            mAdapterCommon.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(mContext, CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, mAdapterCommon.getData().get(position).productId);
                    startActivity(intent);

                }
            });
            mAdapterCommon.setEmptyView(AppHelper.getLoadingView(mContext));
            mLlScreenDate.setVisibility(View.GONE);
        }
    }

    /**
     * 判断获取哪个页面的刷新数据
     */
    private void judgeRefreshData() {
        pageNum = 1;
        if (AppConstant.SPECIAL.equals(pageType)) {

        } else if (AppConstant.SECONDTYPE.equals(pageType)) {
            //秒杀活动
            getNewSpikeTool();
        }
    }


    /**
     * 判断加载更多数据接口
     */
    private void judgeLoadMoreData() {
        pageNum++;
        if (AppConstant.NEWTYPE.equals(pageType)) {

        } else if (AppConstant.SECONDTYPE.equals(pageType)) {
            //秒杀活动
            getNewSpikeTool();

        }
    }


    private void getNewSpikeTool() {
        SpikeNewActiveQueryAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SpikeNewQueryModel>() {
                    @Override
                    public void onCompleted() {
                        mPtrRefresh.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPtrRefresh.refreshComplete();
                    }

                    @Override
                    public void onNext(SpikeNewQueryModel spikeNewQueryModel) {
                        if (spikeNewQueryModel.isSuccess()) {
                            mListSpikeNew.clear();

                            if (spikeNewQueryModel.getData().size() <= 0) {
                                rl_spike.setVisibility(View.GONE);
                            } else {

                                rl_spike.setVisibility(View.VISIBLE);

                            }
                            if (spikeNewQueryModel.getData() != null) {

                                mListSpikeNew.addAll(spikeNewQueryModel.getData());
                                if (isFirst) {
                                    mAdapterNewSpike.selectPosition(0);
                                    spikeActiveQuery(spikeNewQueryModel.getData().get(0).getActiveId());
                                } else {
                                    mAdapterNewSpike.selectPosition(currentPosition);
                                    spikeActiveQuery(spikeNewQueryModel.getData().get(currentPosition).getActiveId());
                                }

                                isFirst = false;

                                mAdapterNewSpike.notifyDataSetChanged();


                            }


                        } else {
                            AppHelper.showMsg(mContext, spikeNewQueryModel.getMessage());
                        }

                    }
                });
    }


    /**
     * 秒杀活动接口
     */
    private void spikeActiveQuery(int activeId) {
        SecKillMoreListAPI.requestMoreListData(mContext, activeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SeckillListModel>() {
                    @Override
                    public void onCompleted() {
                        mPtrRefresh.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPtrRefresh.refreshComplete();
                    }

                    @Override
                    public void onNext(SeckillListModel seckillListModel) {
                        if (seckillListModel.success) {

                            int flag = seckillListModel.data.flag;
                            UserInfoHelper.saveSpikePosition(mContext, String.valueOf(flag));
                            if (flag == 1) {
                                mTvSpikeTitle.setText("秒杀抢购中");
                                snapSipkeTime.setVisibility(View.VISIBLE);
                            } else if (flag == 0) {
                                mTvSpikeTitle.setText("秒杀即将开始");
                                snapSipkeTime.setVisibility(View.GONE);

                            }

                            spikeTimeOut.setTime(true, seckillListModel.data.currentTime, seckillListModel.data.startTime, seckillListModel.data.endTime);
                            //spikeTimeOut.changeTextColor(ContextCompat.getColor(mContext, R.color.app_color_white));
                            spikeTimeOut.changeBackGround(ContextCompat.getColor(mContext, R.color.color_time_bg));
                            spikeTimeOut.start();
                            mListSeckill.clear();
                            if (seckillListModel.data.kills != null) {
                                mListSeckill.addAll(seckillListModel.data.kills);
                                //写显示倒计时的时间

                            }

                        } else {

                            AppHelper.showMsg(mActivity, seckillListModel.message);

                        }
                        mAdapterSpikeQuery.notifyDataSetChanged();
                        // judgeShowNodata(mAdapterSpikeQuery);
                    }
                });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        SelectBean.cleanDate();
    }

    private void backEvent() {
        if ("isShow".equals(mLlScreen.getTag())) {
            //显示状态,关闭显示
            mLlScreen.setTag("isHide");
            mLlScreen.setVisibility(View.GONE);
            isFirst = false;
        } else {
            finish();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isFirst = false;
            backEvent();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 加入购物车
     */
    private void addCar(int businessId, String productCombinationPriceVOList, int businessType, String totalNum) {
        AddCartAPI.requestData(mActivity, businessId, productCombinationPriceVOList, businessType, String.valueOf(totalNum))
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
                            AppHelper.showMsg(mActivity, "成功加入购物车");
                            getCartNum();
                        } else {
                            AppHelper.showMsg(mActivity, addCartModel.message);
                            Log.e("Crash", "onNext: " + addCartModel.message);
                        }

                    }
                });
    }

    /**
     * 获取购物车角标数据
     */
    private void getCartNum() {
        PublicRequestHelper.getCartNum(mContext, new OnHttpCallBack<GetCartNumModel>() {
            @Override
            public void onSuccessful(GetCartNumModel getCartNumModel) {
                if (getCartNumModel.isSuccess()) {
                    if (Integer.valueOf(getCartNumModel.getData().getNum()) > 0) {
                        text_cart_num.setVisibility(View.VISIBLE);
                        text_cart_num.setText(getCartNumModel.getData().getNum());
                    } else {
                        text_cart_num.setVisibility(View.GONE);
                    }
                } else {
                    AppHelper.showMsg(mContext, getCartNumModel.getMessage());
                }
            }

            @Override
            public void onFaild(String errorMsg) {

            }
        });
    }

    /**
     * 特别推荐购物车的点击事件
     */
    private void setRecommendOnclick(int position) {
        GetProductListModel.DataBean.ListBean listBean = mListNew.get(position);
        JSONArray array = new JSONArray();
        try {
            JSONObject object = new JSONObject();
            object.put("productCombinationPriceId", mListNew.get(position).productCombinationPriceId);
            object.put("totalNum", "1");
            array.put(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String productCombinationPriceVOList = array.toString();

        String totalNum = "";
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
            // 特别推荐 先判断是零售用户还是批发用户  在判断是否是批发商品
            // 批发的商品是零售用户的话没有授权需要弹授权码的弹窗 成为批发用户直接添加购物车
            // 批发用户购买零售直接添加购物车
            if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_RETAIL)) {
                //这个用户是零售用户
                if ("批发".equals(listBean.type)) {
                    if (StringHelper.notEmptyAndNull(cell)) {
                        AppHelper.showAuthorizationDialog(mActivity, cell, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) ) {
                                    AppHelper.hideAuthorizationDialog();
                                    showDialog();
                                } else {
                                    AppHelper.showMsg(mActivity, "请输入完整授权码");
                                }
                            }
                        });
                    }
                } else {
                    addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);

                }
            } else if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_WHOLESALE)) {
                //这个用户是批发用户

                addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);

            }
        } else {
            AppHelper.showMsg(mActivity, "请先登录");
            startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
        }

    }

    /**
     * 新品上市购物车的点击事件
     */
    private void setNewOnclick(int position) {
        GetProductListModel.DataBean.ListBean listBean = mNewArrivaList.get(position);
        JSONArray array = new JSONArray();
        try {
            JSONObject object = new JSONObject();
            object.put("productCombinationPriceId", mNewArrivaList.get(position).productCombinationPriceId);
            object.put("totalNum", "1");
            array.put(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String productCombinationPriceVOList = array.toString();

        String totalNum = "";
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
            // 特别推荐 先判断是零售用户还是批发用户  在判断是否是批发商品
            // 批发的商品是零售用户的话没有授权需要弹授权码的弹窗 成为批发用户直接添加购物车
            // 批发用户购买零售直接添加购物车
            if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_RETAIL)) {
                //这个用户是零售用户
                if ("批发".equals(listBean.type)) {
                    if (StringHelper.notEmptyAndNull(cell)) {
                        AppHelper.showAuthorizationDialog(mActivity, cell, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode())) {
                                    AppHelper.hideAuthorizationDialog();
                                    showDialog();
                                } else {
                                    AppHelper.showMsg(mActivity, "请输入完整授权码");
                                }
                            }
                        });
                    }
                } else {
                    addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);

                }
            } else if (UserInfoHelper.getUserType(mActivity).equals(AppConstant.USER_TYPE_WHOLESALE)) {
                //这个用户是批发用户

                addCar(listBean.productId, productCombinationPriceVOList, 1, totalNum);

            }
        } else {
            AppHelper.showMsg(mActivity, "请先登录");
            startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
        }

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

    private void showDialog() {

        GetRegisterShopAPI.requestData(mActivity, AppHelper.getAuthorizationCode())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetRegisterShopModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ccca", "onError: " + "网络错误");
                    }

                    @Override
                    public void onNext(GetRegisterShopModel getRegisterShopModel) {

                        if (getRegisterShopModel.isSuccess()) {
                            list.clear();
                            list.addAll(getRegisterShopModel.getData());
                            //    mRegisterAdapter.notifyDataSetChanged();
                            AlertDialog alertDialog = new AlertDialog.Builder(mActivity).create();


                            alertDialog.show();

                            Window window = alertDialog.getWindow();
                            window.setContentView(R.layout.dialog_auth_shop_type);

                            //    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                            //   window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                            window.setGravity(Gravity.CENTER);
                            RecyclerView rl_shop_type = window.findViewById(R.id.rl_shop_type);
                            TextView tv_dialog_cancel = window.findViewById(R.id.tv_dialog_cancel);
                            TextView tv_dialog_sure = window.findViewById(R.id.tv_dialog_sure);
                            LinearLayoutManager linearLayoutManager = new GridLayoutManager(mActivity, 3);


                            rl_shop_type.setLayoutManager(linearLayoutManager);


                            mRegisterAdapter = new RegisterShopAdapterTwo(mActivity, list);
                            rl_shop_type.setAdapter(mRegisterAdapter);


                            mRegisterAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    isSelected = position;
                                    Log.i("ddda", "onItemClick: " + isSelected);
                                    mRegisterAdapter.selectPosition(position);

                                    shopTypeId = list.get(isSelected).getId();
                                    isChecked = true;
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });
                            tv_dialog_sure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (isChecked) {
                                        alertDialog.dismiss();
                                        updateUserInvitation(AppHelper.getAuthorizationCode(), shopTypeId);
                                    } else {
                                        AppHelper.showMsg(mActivity, "请选择店铺类型");
                                    }
                                }
                            });

                            tv_dialog_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                    AppHelper.setAuthorizationCode("");
                                }
                            });
                        } else {
                            AppHelper.setAuthorizationCode("");
                            AppHelper.showMsg(mActivity, getRegisterShopModel.getMessage());
                        }
                    }
                });

    }

    /**
     * 提交验证码
     */
    private void updateUserInvitation(String code, int typeId) {
        UpdateUserInvitationAPI.requestData(mActivity, code, typeId)
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
                            UserInfoHelper.saveUserType(mActivity, AppConstant.USER_TYPE_RETAIL);

                            //  dialog.show();
                            Log.d("----->", "------");
                            judgeRefreshData();
                        } else {
                            AppHelper.showMsg(mActivity, updateUserInvitationModel.getMessage());
                        }
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
//        judgeRefreshData();
        isFirst = false;
        //  setViewData();

    }


    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = HomeGoodsListActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            HomeGoodsListActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}

