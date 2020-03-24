package com.puyue.www.qiaoge.fragment.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.ChooseAddressActivity;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.CouponDetailActivity;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.activity.home.SearchStartActivity;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.activity.home.TeamDetailActivity;
import com.puyue.www.qiaoge.activity.home.TeamGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.mine.MessageCenterActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.LogoutsEvent;
import com.puyue.www.qiaoge.activity.mine.order.MyOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MinerIntegralActivity;
import com.puyue.www.qiaoge.activity.mine.wallet.MyWalletPointActivity;
import com.puyue.www.qiaoge.adapter.home.CommonAdapter;
import com.puyue.www.qiaoge.adapter.home.CommonProductActivity;
import com.puyue.www.qiaoge.adapter.home.HotProductActivity;
import com.puyue.www.qiaoge.adapter.home.ReductionProductActivity;
import com.puyue.www.qiaoge.adapter.home.SeckillGoodActivity;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.DriverInfo;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.home.IndexInfoModel;
import com.puyue.www.qiaoge.api.home.ProductListAPI;
import com.puyue.www.qiaoge.api.mine.UpdateAPI;
import com.puyue.www.qiaoge.api.mine.order.MyOrderNumAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.event.UpDateNumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.HomeNewRecommendModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.model.mine.UpdateModel;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;
import com.puyue.www.qiaoge.utils.DateUtils;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;
import com.puyue.www.qiaoge.view.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.taobao.library.VerticalBannerView;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/1/4
 */
public class HomeFragmentsss extends BaseFragment implements View.OnClickListener, BaseSliderView.OnSliderClickListener{
    Unbinder binder;
    @BindView(R.id.rv_icon)
    RecyclerView rv_icon;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_driver)
    LinearLayout ll_driver;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_message)
    RelativeLayout rl_message;
    @BindView(R.id.homeMessage)
    ImageView homeMessage;
    @BindView(R.id.rv_type)
    RecyclerView rv_type;
    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.rg_group)
    RadioGroup rg_group;
    @BindView(R.id.rb_1)
    RadioButton rb_1;
    @BindView(R.id.rb_2)
    RadioButton rb_2;
    @BindView(R.id.rb_3)
    RadioButton rb_3;
    @BindView(R.id.content)
    FrameLayout frameLayout;
    @BindView(R.id.rg_new)
    RadioGroup rg_new;
    @BindView(R.id.rb_new)
    RadioButton rb_new;
    @BindView(R.id.rb_must_common)
    RadioButton rb_must_common;
    @BindView(R.id.rb_info)
    RadioButton rb_info;
    @BindView(R.id.rb_common)
    RadioButton rb_common;
    @BindView(R.id.ll_line)
    LinearLayout ll_line;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.v3)
    View v3;
    @BindView(R.id.v4)
    View v4;
    @BindView(R.id.tv_title1)
    TextView tv_title1;
    @BindView(R.id.tv_title2)
    TextView tv_title2;
    @BindView(R.id.tv_title3)
    TextView tv_title3;
    @BindView(R.id.tv_title4)
    TextView tv_title4;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.ll_small_title)
    LinearLayout ll_small_title;
    @BindView(R.id.ll_active)
    LinearLayout ll_active;
    @BindView(R.id.recyclerViewTest)
    RecyclerView recyclerViewTest;
    @BindView(R.id.tv_more)
    TextView tv_more;
    @BindView(R.id.snap)
    SnapUpCountDownTimerView snap;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_desc2)
    TextView tv_desc2;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.tv_desc3)
    TextView tv_desc3;
    @BindView(R.id.rl_more)
    RelativeLayout rl_more;
    @BindView(R.id.rl_more2)
    RelativeLayout rl_more2;
    @BindView(R.id.rl_more3)
    RelativeLayout rl_more3;
    @BindView(R.id.verticalBanner)
    VerticalBannerView verticalBanner;
    @BindView(R.id.lav_activity_loading)
    AVLoadingIndicatorView lav_activity_loading;
    private String cell; // 客服电话
    //司机信息
    List<DriverInfo.DataBean> driverList = new ArrayList<>();
    //八个icon集合
    List<IndexInfoModel.DataBean.IconsBean> iconList = new ArrayList<>();
    //秒杀集合
    List<HomeBaseModel.DataBean.SecKillListBean.KillsBean> skillList = new ArrayList<>();
    //秒杀预告集合
    List<HomeBaseModel.DataBean.SecKillListBean.KillsBean> skillAdvList = new ArrayList<>();
    //特惠集合
    List<HomeBaseModel.DataBean.OfferListBean> couponList = new ArrayList<>();
    //组合集合
    List<HomeBaseModel.DataBean.TeamListBean> teamList = new ArrayList<>();
    //新品集合
    List<HomeNewRecommendModel.DataBean.ListBean> newList = new ArrayList<>();
    //banner集合
    private List<String> bannerList = new ArrayList<>();
    private RvIconAdapter rvIconAdapter;
    Context context;
    int PageNum = 1;
    private MyOrderNumModel mModelMyOrderNum;
    private String token;
    private UpdateModel mModelUpdate;
    private boolean update;
    private boolean forceUpdate;
    private String content;
    private String url;//更新所用的url
    private boolean isShowed = false;//店铺类型是否展示
    private AlertDialog mTypedialog;
    private String invitationCode;
    private boolean isFirst = true;
    int isSelected;
    boolean isChecked = false;
    int shopTypeId;
    boolean flag;
    //banner集合
    List<String> list = new ArrayList<>();
    private IndexInfoModel.DataBean data;
    //分类列表
    private List<IndexInfoModel.DataBean.ClassifyListBean> classifyList = new ArrayList<>();
    private TypesAdapter typeAdapter;
    private DriverAdapter driverAdapter;
    SpikeFragment spikeFragment;
    CouponsFragment couponsFragment;
    TeamsFragment teamsFragment;
    boolean isFirsts = false;
    NewFragment newFragment;
    MustFragment mustFragment;
    InfoFragment infoFragment;
    CommonFragment commonFragment;
    private String questUrl;
    private CouponModel.DataBean data1;
    private CouponModel.DataBean data2;
    private CouponModel.DataBean data3;
    private int showType;
    private CommonAdapter commonAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<CouponModel.DataBean.ActivesBean> actives = new ArrayList<>();
    private View view;
    private int spikeNum;
    private int teamNum;
    private int specialNum;
    private long currentTime;
    private long startTime;
    private long endTime;

    private Date currents;
    private Date starts;
    private CouponModel.DataBean data4;
    private VerticalBannerAdapter verticalBannerAdapter;
    private State state;
    public enum State {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    @Override
    public int setLayoutId() {
        return R.layout.home_fragmentss;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestOrderNumTwo();
    }

    private void requestOrderNumTwo() {
        MyOrderNumAPI.requestOrderNum(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrderNumModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyOrderNumModel myOrderNumModel) {
                        mModelMyOrderNum = myOrderNumModel;
                        if (mModelMyOrderNum.success) {
                            updateOrderNum();
                        } else {
                            AppHelper.showMsg(mActivity, mModelMyOrderNum.message);
                        }
                    }
                });
    }

    private void updateOrderNum() {
        //消息中心
        if (mModelMyOrderNum.getData().getNotice() > 0) {
            tv_num.setVisibility(View.VISIBLE);
            tv_num.setText("  " + mModelMyOrderNum.getData().getNotice() + "  ");
        } else {
            tv_num.setVisibility(View.GONE);
        }
    }

    private void getSpikeList(int type) {
        IndexHomeAPI.getCouponList(mActivity,type+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CouponModel>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CouponModel couponModel) {
                        if(couponModel.isSuccess()) {
                            actives.clear();
                            data4 = couponModel.getData();

                            if(type==2) {
                                data1 = couponModel.getData();
                                if(data1!=null) {
                                    rl_more.setVisibility(View.VISIBLE);
                                    rl_more2.setVisibility(View.GONE);
                                    rl_more3.setVisibility(View.GONE);
                                    actives.addAll(data1.getActives());
                                    rb_1.setVisibility(View.VISIBLE);
                                    tv_desc.setText(data1.getDesc());
                                    currentTime = couponModel.getData().getCurrentTime();
                                    startTime = couponModel.getData().getStartTime();
                                    commonAdapter = new CommonAdapter(2+"",R.layout.item_commons_list, actives);
                                    recyclerViewTest.setAdapter(commonAdapter);
                                    commonAdapter.setOnclick(new CommonAdapter.OnClick() {
                                        @Override
                                        public void shoppingCartOnClick(int position) {
                                            if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                                                int activeId = actives.get(position).getActiveId();
                                                addCar(activeId, "", 2, "1");
                                            } else {
                                                AppHelper.showMsg(mActivity, "请先登录");
                                                startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                                            }
                                        }
                                    });

                                    endTime = couponModel.getData().getEndTime();
                                    String current = DateUtils.formatDate(currentTime, "MM月dd日HH时mm分ss秒");
                                    String start = DateUtils.formatDate(startTime, "MM月dd日HH时mm分ss秒");
                                    try {
                                        currents = Utils.stringToDate(current, "MM月dd日HH时mm分ss秒");
                                        starts = Utils.stringToDate(start, "MM月dd日HH时mm分ss秒");
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }


                                    if(currentTime>startTime) {
                                        //秒杀开始
                                        if(startTime !=0&& endTime !=0) {
                                            snap.setVisibility(View.VISIBLE);
                                            snap.setTime(true, currentTime, startTime, endTime);
                                            snap.changeBackGround(ContextCompat.getColor(mActivity, R.color.white));
                                            snap.changeTypeColor(ContextCompat.getColor(mActivity, R.color.color_F6551A));
                                            tv_time.setVisibility(View.GONE);
                                            snap.start();
                                        }else {
                                            tv_time.setVisibility(View.GONE);
                                            snap.setVisibility(View.GONE);
                                        }
                                    }else {
                                        //未开始
                                        boolean exceed2 = DateUtils.isExceed2(currents, starts);
                                        if(exceed2) {
                                            //大于2
                                            tv_time.setText(start+"开抢");
                                            tv_time.setVisibility(View.VISIBLE);
                                            snap.setVisibility(View.GONE);
                                        }else {
                                            //小于2
                                            if(startTime !=0&& endTime !=0) {
                                                snap.setVisibility(View.VISIBLE);
                                                snap.setTime(true, currentTime, startTime, endTime);
                                                snap.changeBackGround(ContextCompat.getColor(mActivity, R.color.white));
                                                snap.changeTypeColor(ContextCompat.getColor(mActivity, R.color.color_F6551A));
                                                tv_time.setVisibility(View.GONE);
                                                snap.start();
                                            }else {
                                                tv_time.setVisibility(View.GONE);
                                                snap.setVisibility(View.GONE);
                                            }
                                        }
                                    }

                                }else {
                                    rb_1.setVisibility(View.GONE);
                                    rl_more.setVisibility(View.GONE);
                                }

                            } else if(type==11) {
                                data1 = couponModel.getData();
                                if(data1!=null) {

                                    rb_2.setVisibility(View.VISIBLE);
                                    rl_more3.setVisibility(View.GONE);
                                    rl_more.setVisibility(View.GONE);
                                    actives.addAll(data1.getActives());
                                    commonAdapter = new CommonAdapter(11+"",R.layout.item_commons_list, actives);
                                    recyclerViewTest.setAdapter(commonAdapter);

                                    commonAdapter.setOnclick(new CommonAdapter.OnClick() {
                                        @Override
                                        public void shoppingCartOnClick(int position) {
                                            if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                                                int activeId = actives.get(position).getActiveId();
                                                addCar(activeId, "", 11, "1");
                                            } else {
                                                AppHelper.showMsg(mActivity, "请先登录");
                                                startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                                            }
                                        }
                                    });

                                    rl_more2.setVisibility(View.VISIBLE);
                                    tv_desc2.setText(data1.getDesc());
                                }else {
                                    rb_2.setVisibility(View.GONE);

                                    rl_more2.setVisibility(View.GONE);
                                }
                            } else if(type==3) {
                                data1 = couponModel.getData();
                                if(data1!=null) {

                                    rb_3.setVisibility(View.VISIBLE);
                                    actives.addAll(data1.getActives());
                                    commonAdapter = new CommonAdapter(3+"",R.layout.item_commons_list, actives);
                                    recyclerViewTest.setAdapter(commonAdapter);

                                    commonAdapter.setOnclick(new CommonAdapter.OnClick() {
                                        @Override
                                        public void shoppingCartOnClick(int position) {
                                            if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                                                int activeId = actives.get(position).getActiveId();
                                                Log.d("fsffffffff...",activeId+"");
                                                addCar(activeId, "", 3, "1");
                                            } else {
                                                AppHelper.showMsg(mActivity, "请先登录");
                                                startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                                            }
                                        }
                                    });

                                    rl_more.setVisibility(View.GONE);
                                    rl_more2.setVisibility(View.GONE);
                                    tv_desc3.setText(data1.getDesc());
                                    rl_more3.setVisibility(View.VISIBLE);
                                }else {
                                    rb_3.setVisibility(View.GONE);
                                }
                            }
                            commonAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

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
                        }

                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        verticalBanner.stop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initViews(View view) {
        binder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        context = getActivity();
        initStatusBarWhiteColor();
        token = UserInfoHelper.getUserId(mActivity);
//        rb_new.setChecked(true);
//        switchRb4();
        getProductsList(1,10,"commonBuy");
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                int totalScrollRange = appBarLayout.getTotalScrollRange();
                if(totalScrollRange ==Math.abs(verticalOffset)) {
                    flag = true;
                }else {
                    flag = false;
                }

                if(flag) {
                    ll_small_title.setVisibility(View.GONE);
                    ll_line.setVisibility(View.VISIBLE);
                    Log.d("qqqqqqqqqq....","ppppppppp");
                }else {
                    Log.d("qqqqqqqqqq....","wwwwwwwwww");
                    ll_small_title.setVisibility(View.VISIBLE);
                    ll_line.setVisibility(View.GONE);

                }

            }
        });

        ll_small_title.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(flag) {
                    ll_small_title.setVisibility(View.GONE);
                    ll_line.setVisibility(View.VISIBLE);
                    if(rb_new.isChecked()) {
                        v1.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);
                        v4.setVisibility(View.INVISIBLE);

                    }else if(rb_must_common.isChecked()){
                        v1.setVisibility(View.INVISIBLE);
                        v2.setVisibility(View.VISIBLE);
                        v3.setVisibility(View.INVISIBLE);
                        v4.setVisibility(View.INVISIBLE);

                    }else if(rb_info.isChecked()){
                        v1.setVisibility(View.INVISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.VISIBLE);
                        v4.setVisibility(View.INVISIBLE);

                    }else if(rb_common.isChecked()){
                        v1.setVisibility(View.INVISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);
                        v4.setVisibility(View.VISIBLE);
                    }
                }else {
                    ll_small_title.setVisibility(View.VISIBLE);
                    ll_line.setVisibility(View.GONE);

                }
            }
        });

        linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTest.setLayoutManager(linearLayoutManager);



        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        rb_1.setTextColor(Color.parseColor("#ffffff"));
                        rb_1.setBackgroundResource(R.drawable.shape_orange);

                        rb_2.setTextColor(Color.parseColor("#333333"));
                        rb_2.setBackgroundResource(R.drawable.shape_white);

                        rb_3.setTextColor(Color.parseColor("#333333"));
                        rb_3.setBackgroundResource(R.drawable.shape_white);
                        getSpikeList(2);
                        Log.d("swsssssssssssss.....","sswwss");
                        break;

                    case R.id.rb_2:
                        rb_2.setTextColor(Color.parseColor("#ffffff"));
                        rb_2.setBackgroundResource(R.drawable.shape_orange);

                        rb_1.setTextColor(Color.parseColor("#333333"));
                        rb_1.setBackgroundResource(R.drawable.shape_white);

                        rb_3.setTextColor(Color.parseColor("#333333"));
                        rb_3.setBackgroundResource(R.drawable.shape_white);
                        getSpikeList(11);
                        break;

                    case R.id.rb_3:
                        rb_1.setTextColor(Color.parseColor("#333333"));
                        rb_1.setBackgroundResource(R.drawable.shape_white);

                        rb_2.setTextColor(Color.parseColor("#333333"));
                        rb_2.setBackgroundResource(R.drawable.shape_white);

                        rb_3.setTextColor(Color.parseColor("#ffffff"));
                        rb_3.setBackgroundResource(R.drawable.shape_orange);
                        getSpikeList(3);
                        break;
                }
            }
        });




        rg_new.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

//                hideFragment();
                switch (checkedId) {
                    case R.id.rb_new:
                        Log.d("wssssssssssss....",checkedId+"ss");
                        rb_info.setTextColor(Color.parseColor("#333333"));
                        rb_common.setTextColor(Color.parseColor("#333333"));
                        rb_must_common.setTextColor(Color.parseColor("#333333"));
                        rb_new.setTextColor(Color.parseColor("#FF5000"));
                        tv_title1.setTextColor(Color.parseColor("#ffffff"));
                        tv_title1.setBackgroundResource(R.drawable.shape_orange);

                        tv_title2.setTextColor(Color.parseColor("#999999"));
                        tv_title2.setBackgroundResource(R.drawable.shape_white);

                        tv_title3.setTextColor(Color.parseColor("#999999"));
                        tv_title3.setBackgroundResource(R.drawable.shape_white);

                        tv_title4.setTextColor(Color.parseColor("#999999"));
                        tv_title4.setBackgroundResource(R.drawable.shape_white);
//                        if (newFragment == null){
//                            newFragment=new NewFragment();
//
//                            fragmentTransaction.add(R.id.content,newFragment);
//                        }
//                        fragmentTransaction.show(newFragment);
                        switchRb4();
                        break;

                    case R.id.rb_must_common:

                        rb_info.setTextColor(Color.parseColor("#333333"));
                        rb_common.setTextColor(Color.parseColor("#333333"));
                        rb_must_common.setTextColor(Color.parseColor("#333333"));
                        rb_new.setTextColor(Color.parseColor("#333333"));

                        rb_must_common.setTextColor(Color.parseColor("#FF5000"));
                        tv_title2.setTextColor(Color.parseColor("#ffffff"));
                        tv_title2.setBackgroundResource(R.drawable.shape_orange);

                        tv_title1.setTextColor(Color.parseColor("#999999"));
                        tv_title1.setBackgroundResource(R.drawable.shape_white);

                        tv_title3.setTextColor(Color.parseColor("#999999"));
                        tv_title3.setBackgroundResource(R.drawable.shape_white);

                        tv_title4.setTextColor(Color.parseColor("#999999"));
                        tv_title4.setBackgroundResource(R.drawable.shape_white);

                        switchRb5();
                        break;

                    case R.id.rb_info:
                        rb_info.setTextColor(Color.parseColor("#FF5000"));
                        rb_common.setTextColor(Color.parseColor("#333333"));
                        rb_must_common.setTextColor(Color.parseColor("#333333"));
                        rb_new.setTextColor(Color.parseColor("#333333"));
                        tv_title2.setTextColor(Color.parseColor("#999999"));
                        tv_title2.setBackgroundResource(R.drawable.shape_white);

                        tv_title1.setTextColor(Color.parseColor("#999999"));
                        tv_title1.setBackgroundResource(R.drawable.shape_white);

                        tv_title3.setTextColor(Color.parseColor("#ffffff"));
                        tv_title3.setBackgroundResource(R.drawable.shape_orange);

                        tv_title4.setTextColor(Color.parseColor("#999999"));
                        tv_title4.setBackgroundResource(R.drawable.shape_white);

                        switchRb6();
                        break;

                    case R.id.rb_common:
                        rb_info.setTextColor(Color.parseColor("#333333"));
                        rb_common.setTextColor(Color.parseColor("#FF5000"));
                        rb_must_common.setTextColor(Color.parseColor("#333333"));
                        rb_new.setTextColor(Color.parseColor("#333333"));
                        rb_info.setTextColor(Color.parseColor("#333333"));

                        tv_title2.setTextColor(Color.parseColor("#999999"));
                        tv_title2.setBackgroundResource(R.drawable.shape_white);

                        tv_title1.setTextColor(Color.parseColor("#999999"));
                        tv_title1.setBackgroundResource(R.drawable.shape_white);

                        tv_title3.setTextColor(Color.parseColor("#999999"));
                        tv_title3.setBackgroundResource(R.drawable.shape_white);

                        tv_title4.setTextColor(Color.parseColor("#ffffff"));
                        tv_title4.setBackgroundResource(R.drawable.shape_orange);

                        switchRb7();
                        break;
                }
            }
        });

        //司机信息Adapter
        driverAdapter = new DriverAdapter(R.layout.item_driver,driverList);

        //八个icon Adapter
        rvIconAdapter = new RvIconAdapter(R.layout.item_home_icon,iconList);
        rv_icon.setLayoutManager(new GridLayoutManager(context,4));
        rv_icon.setAdapter(rvIconAdapter);

        //六个品种点击
        typeAdapter = new TypesAdapter(R.layout.item_type,classifyList);
        rv_type.setLayoutManager(new GridLayoutManager(context,2));
        rv_type.setAdapter(typeAdapter);


        tv_search.setOnClickListener(this);
        rl_message.setOnClickListener(this);
        tv_city.setOnClickListener(this);
        rl_more.setOnClickListener(this);
        rl_more2.setOnClickListener(this);
        rl_more3.setOnClickListener(this);

    }

    private void getProductsList(int pageNums, int pageSize, String type) {
        ProductListAPI.requestData(mActivity, pageNums, pageSize,type,null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductNormalModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProductNormalModel getCommonProductModel) {
                        if (getCommonProductModel.isSuccess()) {
                            if(getCommonProductModel.getData().getList().size()>0) {
                                switchRb7();
                                rb_common.setChecked(true);
                            }else {
                                switchRb4();
                                rb_new.setChecked(true);
                            }
                        }else {
                            AppHelper.showMsg(mActivity,getCommonProductModel.getMessage());
                        }
                    }
                });
    }
    private void hideFragment() {
        if (newFragment!=null){
            //隐藏
            fragmentTransaction.hide(newFragment);
        }
        if (mustFragment!=null){
            //隐藏
            fragmentTransaction.hide(mustFragment);
        }
        if (infoFragment!=null){
            //隐藏
            fragmentTransaction.hide(infoFragment);
        }
        if (commonFragment!=null){
            //隐藏
            fragmentTransaction.hide(commonFragment);
        }
    }


    /**
     * 常用清单
     */
    private void switchRb7() {

        fragmentTransaction = supportFragmentManager.beginTransaction();
        if (commonFragment == null) {
            commonFragment = new CommonFragment();
            fragmentTransaction.add(R.id.content, commonFragment, CommonFragment.class.getCanonicalName());
        }

        fragmentTransaction.show(commonFragment);

        if (infoFragment != null) {
            fragmentTransaction.hide(infoFragment);
        }

        if (mustFragment != null) {
            fragmentTransaction.hide(mustFragment);
        }

        if (newFragment != null) {
            fragmentTransaction.hide(newFragment);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 咨讯
     */
    private void switchRb6() {
        fragmentTransaction = supportFragmentManager.beginTransaction();
        if (infoFragment == null) {
            infoFragment = new InfoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url",questUrl);
            bundle.putInt("TYPE",2);
            bundle.putString("name","");
            infoFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.content, infoFragment, InfoFragment.class.getCanonicalName());
        }

        fragmentTransaction.show(infoFragment);

        if (mustFragment != null) {
            fragmentTransaction.hide(mustFragment);
        }

        if (newFragment != null) {
            fragmentTransaction.hide(newFragment);
        }

        if (commonFragment != null) {
            fragmentTransaction.hide(commonFragment);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 必买
     */
    private void switchRb5() {
        fragmentTransaction = supportFragmentManager.beginTransaction();
        if (mustFragment == null) {
            mustFragment = new MustFragment();
            fragmentTransaction.add(R.id.content, mustFragment, MustFragment.class.getCanonicalName());
        }

        fragmentTransaction.show(mustFragment);

        if (infoFragment != null) {
            fragmentTransaction.hide(infoFragment);
        }

        if (newFragment != null) {
            fragmentTransaction.hide(newFragment);
        }

        if (commonFragment != null) {
            fragmentTransaction.hide(commonFragment);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 新品
     */
    private void switchRb4() {
        fragmentTransaction = supportFragmentManager.beginTransaction();
        if (newFragment == null) {
            newFragment = new NewFragment();
            Log.d("wssssssssssss....","000000000");
            fragmentTransaction.add(R.id.content, newFragment, NewFragment.class.getCanonicalName());
        }
        fragmentTransaction.show(newFragment);

        if (infoFragment != null) {
            fragmentTransaction.hide(infoFragment);
        }

        if (mustFragment != null) {
            fragmentTransaction.hide(mustFragment);
        }

        if (commonFragment != null) {
            fragmentTransaction.hide(commonFragment);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }


    /**
     * 更新购物车角标
     */
    private void getCartNum() {
        PublicRequestHelper.getCartNum(mActivity, new OnHttpCallBack<GetCartNumModel>() {
            @Override
            public void onSuccessful(GetCartNumModel getCartNumModel) {
                if (getCartNumModel.isSuccess()) {
                    if (Integer.valueOf(getCartNumModel.getData().getNum()) > 0) {
                        ((TextView) getActivity().findViewById(R.id.tv_home_car_number)).setText(getCartNumModel.getData().getNum());
                        getActivity().findViewById(R.id.tv_home_car_number).setVisibility(View.VISIBLE);

                    } else {
                        getActivity().findViewById(R.id.tv_home_car_number).setVisibility(View.GONE);
                    }
                } else {
                    AppHelper.showMsg(mActivity, getCartNumModel.getMessage());
                }
            }

            @Override
            public void onFaild(String errorMsg) {

            }
        });
    }


    @Override
    public void findViewById(View view) {

    }

    @Override
    public void setViewData() {
        requestUpdate();
        refreshLayout.autoRefresh();
        lav_activity_loading.show();
        getCustomerPhone();
        mTypedialog = new AlertDialog.Builder(mActivity, R.style.DialogStyle).create();
        mTypedialog.setCancelable(false);

        if (token != null && StringHelper.notEmptyAndNull(token)) {
//            requestOrderNum();
        }

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                PageNum = 1;
                newList.clear();
                skillList.clear();
                skillAdvList.clear();
                driverList.clear();
                getBaseLists();
                getDriveInfo();
                EventBus.getDefault().post(new BackEvent());
                refreshLayout.finishRefresh();
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
     * 获取司机信息
     */
    private void getDriveInfo() {
        IndexHomeAPI.getDriverInfo(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DriverInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DriverInfo driverInfo) {
                        if(driverInfo.isSuccess()) {

                            if(driverInfo.getData().size()!=0) {
                                driverList.clear();
                                driverList.addAll(driverInfo.getData());
                                if(!cell.equals("")) {
                                    ll_driver.setVisibility(View.VISIBLE);
                                    verticalBannerAdapter = new VerticalBannerAdapter(cell,driverList,getContext());
                                    verticalBanner.setAdapter(verticalBannerAdapter);
                                    verticalBanner.start();

                                }else {
                                    ll_driver.setVisibility(View.GONE);
                                }

                            }else {
                                ll_driver.setVisibility(View.GONE);
                            }
                        }

                    }
                });
    }

    /**
     * 获取首页信息
     */
    private void getBaseLists() {
        IndexHomeAPI.getIndexInfo(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IndexInfoModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("asfsdfffgfggggg.....",e.getMessage());
                        lav_activity_loading.hide();
                    }

                    @Override
                    public void onNext(IndexInfoModel indexInfoModel) {
                        if(indexInfoModel.isSuccess()) {
                            data = indexInfoModel.getData();
                            classifyList.clear();
                            classifyList.addAll(data.getClassifyList());
                            if(classifyList.size()>0) {
                                rv_type.setVisibility(View.VISIBLE);
                            }else {
                                rv_type.setVisibility(View.GONE);

                            }
                            typeAdapter.notifyDataSetChanged();

                            iconList.clear();
                            iconList.addAll(data.getIcons());
                            if(iconList.size()>0) {
                                rv_icon.setVisibility(View.VISIBLE);
                            }else {
                                rv_icon.setVisibility(View.GONE);
                            }

                            spikeNum = indexInfoModel.getData().getSpikeNum();
                            teamNum = indexInfoModel.getData().getTeamNum();
                            specialNum = indexInfoModel.getData().getSpecialNum();
//                        rb_1.setText(data);
                            if(spikeNum!=0) {
                                rb_1.setVisibility(View.VISIBLE);
                                rb_1.setChecked(true);
                                getSpikeList(2);
                            }else {
                                rb_1.setChecked(false);
                                rb_1.setVisibility(View.GONE);

                            }

                            if(spikeNum==0) {
                                if(specialNum!=0) {
                                    rb_2.setChecked(true);
                                    getSpikeList(11);
                                    Log.d("ewgrgegrdggrgege.....","000000");
                                    rb_2.setVisibility(View.VISIBLE);
                                }else {
                                    rb_2.setChecked(false);
                                    rb_2.setVisibility(View.GONE);
                                }

                                if(specialNum==0) {
                                    if(teamNum!=0) {
                                        getSpikeList(3);
                                        Log.d("ewgrgegrdggrgege.....","111111");
                                        rb_3.setVisibility(View.VISIBLE);
                                        rb_3.setChecked(true);
                                    }else {
                                        rb_3.setChecked(false);
                                        rb_3.setVisibility(View.GONE);
                                    }
                                }


                            }

                            if(teamNum==0) {
                                rb_3.setVisibility(View.GONE);

                            }else {
                                rb_3.setVisibility(View.VISIBLE);
                            }

                            if(spikeNum==0) {
                                rb_1.setVisibility(View.GONE);

                            }else {
                                rb_1.setVisibility(View.VISIBLE);
                                Log.d("dseeeeeeeeeeee.....","000000");
                            }

                            if(specialNum==0) {
                                rb_2.setVisibility(View.GONE);
                            }else {
                                rb_2.setVisibility(View.VISIBLE);
                            }


                            if(teamNum==0&&specialNum==0&&spikeNum==0) {
                                ll_active.setVisibility(View.GONE);
                            }else {
                                ll_active.setVisibility(View.VISIBLE);
                            }

                            rvIconAdapter.notifyDataSetChanged();
                            questUrl = indexInfoModel.getData().getQuestUrl();
                            //----------------------------
                            tv_city.setText(data.getAddress());
                            Glide.with(mActivity).load(data.getOtherInfo()).into(iv_pic);
                            list.clear();
                            for (int i = 0; i < indexInfoModel.getData().getBanners().size(); i++) {
                                list.add(data.getBanners().get(i).getDefaultPic());
                            }

                            if (data.getBanners().size() > 0) {
                                banner.setVisibility(View.VISIBLE);
                                banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                                banner.setImageLoader(new GlideImageLoader());
                                bannerList.clear();
                                bannerList.addAll(list);
                                banner.setImages(bannerList);
                                banner.setBannerAnimation(Transformer.DepthPage);
                                banner.isAutoPlay(true);
                                banner.setDelayTime(3000);
                                banner.setIndicatorGravity(BannerConfig.RIGHT);

                                ClickBanner(data.getBanners());


                                banner.start();
                            } else {
                                banner.setVisibility(View.GONE);
                            }
                            lav_activity_loading.hide();
                            Log.d("wsswswwsss.....","swsssws");
                        }else {
                            AppHelper.showMsg(mActivity, indexInfoModel.getMessage());
                            lav_activity_loading.hide();
                        }
                    }
                });
    }

    private void ClickBanner(List<IndexInfoModel.DataBean.BannersBean> banners) {
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                showType = banners.get(position).getShowType();
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
                        Intent intent = new Intent(getActivity(), MyOrdersActivity.class);
                        startActivity(intent);
                    }
                }else if(showType ==4 ) {
                    //商品
                    int businessId = Integer.parseInt(banners.get(position).getBusinessId());
                    Intent intent = new Intent(getActivity(), CommonGoodsDetailActivity.class);
                    intent.putExtra(AppConstant.ACTIVEID, businessId);
                    startActivity(intent);
                }else if(showType ==5 ) {
                    //活动
                    String businessType = banners.get(position).getBusinessType();
                    int businessId = Integer.parseInt(banners.get(position).getBusinessId());
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


    /**
     * 获取更新
     */
    private void requestUpdate() {
        UpdateAPI.requestUpdate(getContext(), AppHelper.getVersion(getContext()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateModel updateModel) {
                        mModelUpdate = updateModel;
                        if (mModelUpdate.success) {
                            Log.d("weffffffff...",mModelUpdate.data.version);
                            updateUpdate();
                        } else {
                            AppHelper.showMsg(mActivity, mModelUpdate.message);
                        }
                    }
                });

    }

    private void updateUpdate() {
        url = mModelUpdate.data.url;
        update = mModelUpdate.data.update;
        forceUpdate = mModelUpdate.data.forceUpdate;
        content = mModelUpdate.data.msg;
        if (update) {
            //因为服务器上面的是2.0.6，所以才会出现新版本和提示框的字样，只要上架之后重新上传一个2.0.7就可以了。
            //有更新
            UserInfoHelper.saveGuide(mActivity, "");
            showUpdateDialog();
        }
    }

    /**
     * 更新弹窗
     */
    private void showUpdateDialog() {
        final AlertDialog mDialog = new AlertDialog.Builder(getContext()).create();
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.update_dialog);
        Button mBtnForceUpdate = (Button) mDialog.getWindow().findViewById(R.id.btnForceUpdate);
        Button mBtnCancel = (Button) mDialog.getWindow().findViewById(R.id.btnCancel);
        Button mBtnOK = (Button) mDialog.getWindow().findViewById(R.id.btnOK);
        LinearLayout mLlButton = (LinearLayout) mDialog.getWindow().findViewById(R.id.llButton);
        TextView mTvContent = (TextView) mDialog.getWindow().findViewById(R.id.tvContent);

        mTvContent.setText(content);
        if (forceUpdate) {
            mDialog.setCancelable(false);
            mLlButton.setVisibility(View.GONE);
            mBtnForceUpdate.setVisibility(View.VISIBLE);
        } else {
            mDialog.setCancelable(true);
            mLlButton.setVisibility(View.VISIBLE);
            mBtnForceUpdate.setVisibility(View.GONE);
        }
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   ((BaseSwipeActivity) mContext).finish();
                mDialog.dismiss();
            }
        });

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 下载
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    startActivity(intent);
                } catch (Exception e) {

                }
                mDialog.dismiss();
            }
        });
        mBtnForceUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 下载
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url.contains("http://") ? ("http://" + url) : url);
                    intent.setData(content_url);
                    startActivity(intent);
                } catch (Exception e) {

                }
                mDialog.dismiss();
            }
        });
    }

    /**
     * 获取消息
     */
    private void requestOrderNum() {
        MyOrderNumAPI.requestOrderNum(getContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrderNumModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyOrderNumModel myOrderNumModel) {
                        mModelMyOrderNum = myOrderNumModel;
                        if (mModelMyOrderNum.success) {
                            if (mModelMyOrderNum.getData().getNotice() > 0) {
                                tv_num.setVisibility(View.VISIBLE);

                                tv_num.setText("  " + mModelMyOrderNum.getData().getNotice() + "  ");
                            } else {
                                tv_num.setVisibility(View.GONE);
                            }
                        } else {
                            AppHelper.showMsg(mActivity, mModelMyOrderNum.message);
                        }
                    }
                });
    }


    @Override
    public void setClickEvent() {

    }
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Intent intent = new Intent(context,SearchStartActivity.class);
                intent.putExtra(AppConstant.SEARCHTYPE, AppConstant.HOME_SEARCH);
                intent.putExtra("flag", "first");
                intent.putExtra("good_buy", "");
                startActivity(intent);
                break;


            case R.id.rl_message:
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(getActivity()))) {
                    Intent intents = new Intent(getActivity(), MessageCenterActivity.class);
                    startActivityForResult(intents, 101);

                } else {
                    AppHelper.showMsg(getActivity(), "请先登录");
                    startActivity(LoginActivity.getIntent(getActivity(), LoginActivity.class));
                }
                break;

            case R.id.tv_city:
                //选择城市
                if(data!=null) {
                    Intent messageIntent = new Intent(getActivity(), ChooseAddressActivity.class);
                    messageIntent.putExtra("cityName",data.getCityName());
                    messageIntent.putExtra("areaName",data.getAreaName());
                    startActivityForResult(messageIntent, 104);
                }

                break;

            case R.id.rl_more:
                //秒杀专区
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(getActivity()))) {
                    Intent secIntent = new Intent(getActivity(), HomeGoodsListActivity.class);
                    startActivity(secIntent);
                } else {
                    AppHelper.showMsg(getActivity(), "请先登录");
                    startActivity(LoginActivity.getIntent(getActivity(), LoginActivity.class));
                }


                break;

            case R.id.rl_more2:
                //精选折扣
                Intent specialIntent = new Intent(getActivity(), CouponDetailActivity.class);
                startActivity(specialIntent);
                break;

            case R.id.rl_more3:
                //超值组合
                Intent teamIntent = new Intent(getActivity(), TeamDetailActivity.class);
                startActivity(teamIntent);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == 102) {
                int newPosition = data.getIntExtra("NewPosition", 5);//NewPosition
                if (newPosition > 0) {
                    tv_num.setVisibility(View.VISIBLE);
                    tv_num.setText("  " + newPosition + "  ");
                } else {
                    tv_num.setVisibility(View.GONE);
                }
            }
        }

        if (requestCode == 104) {
            newList.clear();
            skillList.clear();
            skillAdvList.clear();
            getBaseLists();
            EventBus.getDefault().post(new BackEvent());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        String banner_url = slider.getBundle().getString("banner_url");
        if (StringHelper.notEmptyAndNull(banner_url)) {
            Intent intent = new Intent(getActivity(), NewWebViewActivity.class);
            intent.putExtra("URL", banner_url);
            intent.putExtra("TYPE", 2);
            intent.putExtra("name", "");
            startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginEvent(LogoutsEvent event) {
        //刷新UI
//        newList.clear();
//        skillList.clear();
//        skillAdvList.clear();
        Log.d("sdfhdshjdlsk.......","sdfsdfsd");
        refreshLayout.autoRefresh();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginsEvent(AddressEvent event) {
        //刷新UI
        refreshLayout.autoRefresh();
        Log.d("sdfhdshjdlsk.......","sdfsdfsd");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cartNum(UpDateNumEvent event) {
        getCartNum();
    }
    protected void initStatusBarWhiteColor() {
        //设置状态栏颜色为白色，状态栏图标为黑色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getActivity().getWindow().setStatusBarColor(Color.WHITE);
            StatusBarUtil.setStatusBarLightMode(getActivity());
        }
    }

}
