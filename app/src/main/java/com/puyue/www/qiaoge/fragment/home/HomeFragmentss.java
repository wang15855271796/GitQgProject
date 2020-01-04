package com.puyue.www.qiaoge.fragment.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.ChangeCityActivity;
import com.puyue.www.qiaoge.activity.home.ChooseAddressActivity;
import com.puyue.www.qiaoge.activity.home.CouponDetailActivity;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.activity.home.NoticeListActivity;
import com.puyue.www.qiaoge.activity.home.SearchStartActivity;
import com.puyue.www.qiaoge.activity.home.SelectionGoodActivity;
import com.puyue.www.qiaoge.activity.home.TeamDetailActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginEvent;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.BannerModel;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.home.IndexInfoModel;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.api.mine.UpdateAPI;
import com.puyue.www.qiaoge.api.mine.order.MyOrderNumAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.HomeNewRecommendModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.mine.UpdateModel;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;
import com.puyue.www.qiaoge.view.MarqueeView;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;
import com.puyue.www.qiaoge.view.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
public class HomeFragmentss extends BaseFragment implements View.OnClickListener, BaseSliderView.OnSliderClickListener{
    Unbinder binder;
    @BindView(R.id.rv_icon)
    RecyclerView rv_icon;
    @BindView(R.id.rv_skill)
    RecyclerView rv_skill;
    @BindView(R.id.rv_coupon)
    RecyclerView rv_coupon;
    @BindView(R.id.rv_team)
    RecyclerView rv_team;
    @BindView(R.id.rv_new)
    RecyclerView rv_new;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv6)
    ImageView iv6;
    @BindView(R.id.ll_classify)
    LinearLayout ll_classify;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    @BindView(R.id.notice)
    MarqueeView notice;
    @BindView(R.id.ll_notice)
    LinearLayout ll_notice;
    @BindView(R.id.tv_skill)
    TextView tv_skill;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.ll_skill)
    LinearLayout ll_skill;
    @BindView(R.id.tv_coupon)
    TextView tv_coupon;
    @BindView(R.id.tv_coupon_desc)
    TextView tv_coupon_desc;
    @BindView(R.id.ll_coupon)
    LinearLayout ll_coupon;
    @BindView(R.id.tv_team)
    TextView tv_team;
    @BindView(R.id.tv_team_desc)
    TextView tv_team_desc;
    @BindView(R.id.ll_team)
    LinearLayout ll_team;
    @BindView(R.id.ll_new)
    LinearLayout ll_new;
    @BindView(R.id.time_start)
    SnapUpCountDownTimerView time_start;
    @BindView(R.id.time_end)
    SnapUpCountDownTimerView time_end;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_new)
    TextView tv_new;
    @BindView(R.id.tv_adv)
    TextView tv_adv;
    @BindView(R.id.rv_skill_adv)
    RecyclerView rv_skill_adv;
    @BindView(R.id.rl_message)
    RelativeLayout rl_message;
    @BindView(R.id.homeMessage)
    ImageView homeMessage;
    @BindView(R.id.tv_inner_classify)
    TextView tv_inner_classify;
    @BindView(R.id.tv_inner_new)
    TextView tv_inner_new;
    @BindView(R.id.ll_skill_progress)
    LinearLayout ll_skill_progress;
    @BindView(R.id.ll_skill_adv)
    LinearLayout ll_skill_adv;
    @BindView(R.id.rv_type)
    RecyclerView rv_type;
    @BindView(R.id.rv_distribute)
    RecyclerView rv_distribute;
    //    @BindView(R.id.smart)
//    com.scwang.smartrefresh.layout.SmartRefreshLayout SmartRefreshLayout;
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
    //公告
    private List<String> noticeList = new ArrayList<>();
    private RvIconAdapter rvIconAdapter;
    private SkillAdapter skillAdapter;
    private CouponAdapter couponAdapter;
    private TeamAdapter teamAdapter;
    private NewAdapter newAdapter;
    Context context;
    int PageNum = 1;
    int PageSize = 12;
    HomeNewRecommendModel homeNewRecommendModels;
    private MyOrderNumModel mModelMyOrderNum;
    HomeBaseModel homeBaseModels;
    private String token;
    private int id0;
    private String title0;
    private int id1;
    private String title1;
    private int id2;
    private String title2;
    private int id3;
    private String title3;
    private int id4;
    private String title4;
    private int id5;
    private String title5;
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
    SkillAdvAdapter skillAdvAdapter;
    //banner集合
    List<String> list = new ArrayList<>();
    private IndexInfoModel.DataBean data;
    //分类列表
    private List<IndexInfoModel.DataBean.ClassifyListBean> classifyList = new ArrayList<>();
    private TypesAdapter typeAdapter;

    @Override
    public int setLayoutId() {
//        setTranslucentStatus();
        return R.layout.home_fragmentss;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("wojiafsgkg;fgg...","ssdwdsd");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initViews(View view) {
        initStatusBarWhiteColor();
        binder = ButterKnife.bind(this, view);

        EventBus.getDefault().register(this);
        context = getActivity();
        token = UserInfoHelper.getUserId(mActivity);



        //八个icon Adapter
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,true);
        rvIconAdapter = new RvIconAdapter(R.layout.item_home_icon,iconList);
        rv_icon.setLayoutManager(new GridLayoutManager(context,4));
        rv_icon.setAdapter(rvIconAdapter);

        //六个品种点击
        typeAdapter = new TypesAdapter(R.layout.item_type,classifyList);
        rv_type.setLayoutManager(new GridLayoutManager(context,2));
        rv_type.setAdapter(typeAdapter);

        //秒杀 Adapter
        skillAdapter = new SkillAdapter(R.layout.item_skill,skillList);
        rv_skill.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        rv_skill.setAdapter(skillAdapter);

        skillAdapter.notifyDataSetChanged();
        skillAdapter.setOnclick(new SkillAdapter.OnClick() {
            @Override
            public void shoppingCartOnClick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    int activeId = skillList.get(position).getActiveId();
                    addCar(activeId, "", 2, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                skillAdapter.notifyDataSetChanged();
            }
        });

        //秒杀预告
        skillAdvAdapter = new SkillAdvAdapter(R.layout.item_skill,skillAdvList);
        rv_skill_adv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        rv_skill_adv.setAdapter(skillAdvAdapter);
        skillAdvAdapter.notifyDataSetChanged();
        skillAdvAdapter.setOnclick(new SkillAdvAdapter.OnClick() {
            @Override
            public void shoppingCartOnClick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    int activeId = skillAdvList.get(position).getActiveId();
                    addCar(activeId, "", 2, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                skillAdvAdapter.notifyDataSetChanged();
            }
        });
        //特惠
        couponAdapter = new CouponAdapter(R.layout.item_skill,couponList);
        rv_coupon.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        rv_coupon.setAdapter(couponAdapter);

        couponAdapter.setOnclick(new CouponAdapter.OnClick() {
            @Override
            public void shoppingCartOnClick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    int activeId = couponList.get(position).getActiveId();
                    addCar(activeId, "", 11, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                couponAdapter.notifyDataSetChanged();
            }
        });


        //组合
        teamAdapter = new TeamAdapter(R.layout.item_skill,teamList);
        rv_team.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        rv_team.setAdapter(teamAdapter);
        teamAdapter.setOnclick(new TeamAdapter.OnClick() {
            @Override
            public void shoppingCartOnClick(int position) {   //超值组合(团购)
                int activeId = teamList.get(position).getActiveId();

                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    addCar(activeId, "", 3, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                teamAdapter.notifyDataSetChanged();
            }
        });


        //新品
        newAdapter = new NewAdapter(R.layout.item_skill,newList);
        rv_new.setLayoutManager(new GridLayoutManager(context,3));
        rv_new.setAdapter(newAdapter);

        ll_skill.setOnClickListener(this);
        ll_coupon.setOnClickListener(this);
        ll_team.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        rl_message.setOnClickListener(this);
        tv_city.setOnClickListener(this);

        notice.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                //跳转公告列表
                Log.d("sdwdwddddd..","swwcfff");
                startActivity(NoticeListActivity.getIntent(getContext(), NoticeListActivity.class));
            }
        });
    }

    protected void initStatusBarWhiteColor() {
        //设置状态栏颜色为白色，状态栏图标为黑色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getActivity().getWindow().setStatusBarColor(Color.WHITE);
            StatusBarUtil.setStatusBarLightMode(getActivity());
        }
    }
    /**
     * 添加购物车
     * @param
     * @param
     * @param
     * @param
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
                        }

                    }
                });
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

        newList.clear();
        skillList.clear();
        skillAdvList.clear();
        getNewProductList(1+"", PageSize+"");
        getBaseList("version","1");
        getBanner();
        getBaseLists();
        mTypedialog = new AlertDialog.Builder(mActivity, R.style.DialogStyle).create();
        mTypedialog.setCancelable(false);

        if (token != null && StringHelper.notEmptyAndNull(token)) {
            requestOrderNum();
        }

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                PageNum = 1;
                newList.clear();
                skillList.clear();
                skillAdvList.clear();
                getNewProductList(1+"", PageSize+"");
                getBaseList("version","1");
                refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (homeNewRecommendModels.getData()!=null) {
                    Log.d("swdwdsvgf.,","sss");
                    if(homeNewRecommendModels.getData().isHasNextPage()) {
                        PageNum++;
                        getNewProductList(PageNum+"", PageSize+"");
                        refreshLayout.finishLoadMore();      //加载完成

                    }else {
                        refreshLayout.finishLoadMoreWithNoMoreData();
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
                        Log.d("sssssssss,,,",e.getMessage());
                    }

                    @Override
                    public void onNext(IndexInfoModel indexInfoModel) {
                        data = indexInfoModel.getData();
                        classifyList.addAll(data.getClassifyList());
                        if(indexInfoModel.isSuccess()) {
                            Glide.with(mActivity).load(data.getOtherInfo()).into(iv_pic);
                        }
                        iconList.addAll(data.getIcons());

                        data.
                    }
                });
    }

    /**
     * 分类banner获取
     */

    private void getBanner() {
        IndexHomeAPI.getBanner(getContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerModel bannerModel) {
                        if (mModelUpdate.success) {
                            for (int i = 0; i < bannerModel.getData().size(); i++) {
                                list.add(bannerModel.getData().get(i).getDefaultPic());
                            }
                            if (bannerModel.getData().size() > 0) {
                                banner.setVisibility(View.VISIBLE);
                                bannerList.clear();
                                banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                                banner.setImageLoader(new GlideImageLoader());
                                bannerList.addAll(list);
                                banner.setImages(bannerList);
                                banner.setBannerAnimation(Transformer.DepthPage);
                                banner.isAutoPlay(true);
                                banner.setDelayTime(3000);
                                banner.setIndicatorGravity(BannerConfig.RIGHT);
                                banner.start();
                                Log.d("ddfdfffffffffff.....",bannerList.size()+"");
                            } else {
                                banner.setVisibility(View.GONE);
                            }

                        } else {
                            AppHelper.showMsg(mActivity, bannerModel.getMessage());
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

    /**
     * 首页新品
     * @param
     * @param
     */
    private void getNewProductList(String PageNum, String PageSize) {
        Log.d("swxdsdwwdswssasas...",PageNum+"");
        IndexHomeAPI.getRecommendData(mActivity, PageNum+"",PageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeNewRecommendModel>() {
                    @Override
                    public void onCompleted() {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        ptr.refreshComplete();
                    }

                    @Override
                    public void onNext(HomeNewRecommendModel homeNewRecommendModel) {

                        homeNewRecommendModels = homeNewRecommendModel;

                        if(homeNewRecommendModel.getData().getList()!=null&&homeNewRecommendModel.getData().getList().size()>0) {
                            ll_new.setVisibility(View.VISIBLE);
                            newList.addAll(homeNewRecommendModel.getData().getList());
                            newAdapter.notifyDataSetChanged();
                        }else {
                            ll_new.setVisibility(View.GONE);
                        }
                    }
                });

//        ptr.setVisibility(View.VISIBLE);
    }


    /**
     * 获取首页基础信息（新品除外）
     * @param version
     * @param clientType
     */
    private void getBaseList(String version, String clientType) {
        IndexHomeAPI.getBaseList(getActivity(), version, clientType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeBaseModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("dfdfweeffeyghfb...",e.getMessage());
                    }

                    @Override
                    public void onNext(HomeBaseModel homeBaseModel) {
                        if (homeBaseModel.getData().getCityName() != null && StringHelper.notEmptyAndNull(homeBaseModel.getData().getCityName())) {
                            tv_city.setText(homeBaseModel.getData().getCityName());
                        }else {
                            if (UserInfoHelper.getCity(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getCity(mActivity))) {
                                tv_city.setText(UserInfoHelper.getCity(mActivity));

                            }
                        }

                        homeBaseModels = homeBaseModel;
                        invitationCode = homeBaseModel.getData().getInvitationCode();
                        if (homeBaseModel.isSuccess()) {

                            //秒杀
                            if(homeBaseModel.getData().getSecKillList()!=null&&homeBaseModel.getData().getSecKillList().size()>0) {
                                long currentTime = 0;
                                long startTime = 0;
                                long endTime = 0;
                                long currentTimePre = 0;
                                long startTimePre = 0;
                                long endTimePre = 0;

                                //秒杀
                                for (int i = 0; i <homeBaseModels.getData().getSecKillList().size() ; i++) {
                                    ll_skill.setVisibility(View.VISIBLE);
                                    //秒杀预告
                                    if(homeBaseModels.getData().getSecKillList().get(i).getFlag()==0) {
                                        if(skillList.size()==0) {
                                            ll_skill_progress.setVisibility(View.GONE);
                                        }else {
                                            ll_skill_progress.setVisibility(View.VISIBLE);
                                        }
                                        skillAdvList.addAll(homeBaseModel.getData().getSecKillList().get(i).getKills());
                                        Log.d("miaoshayugao.....",skillAdvList.size()+"");
                                        skillAdvAdapter.notifyDataSetChanged();
                                        tv_adv.setText(homeBaseModel.getData().getKillTrailDesc());
                                        ll_skill_adv.setVisibility(View.VISIBLE);
//                                        ll_skill_progress.setVisibility(View.GONE);
                                        currentTimePre = homeBaseModel.getData().getSecKillList().get(i).getCurrentTime();
                                        startTimePre = homeBaseModel.getData().getSecKillList().get(i).getStartTime();
                                        endTimePre = homeBaseModel.getData().getSecKillList().get(i).getEndTime();
                                        time_end.setBackTheme(false);
                                        time_end.setTime(true, currentTimePre, startTimePre, endTimePre);
                                        time_end.changeTextColor(ContextCompat.getColor(getContext(), R.color.app_color_white));
                                        time_end.changeColon(ContextCompat.getColor(getContext(), R.color.app_bg_colon));
                                        time_end.start();
                                    }else {
                                        if(skillAdvList.size()==0) {
                                            ll_skill_adv.setVisibility(View.GONE);
                                        }else {
                                            ll_skill_adv.setVisibility(View.VISIBLE);
                                        }
                                        ll_skill_progress.setVisibility(View.VISIBLE);
                                        skillList.addAll(homeBaseModels.getData().getSecKillList().get(i).getKills());
                                        Log.d("miaoshayugao..........",skillList.size()+"");
                                        skillAdapter.notifyDataSetChanged();
                                        tv_desc.setText(homeBaseModels.getData().getKillDesc());
                                        tv_skill.setText(homeBaseModels.getData().getKillTitle());
                                        currentTime = homeBaseModels.getData().getSecKillList().get(i).getCurrentTime();
                                        startTime = homeBaseModels.getData().getSecKillList().get(i).getStartTime();
                                        endTime = homeBaseModels.getData().getSecKillList().get(i).getEndTime();

                                        time_start.setBackTheme(false);
                                        time_start.setTime(true, currentTime, startTime, endTime);
                                        time_start.changeTextColor(ContextCompat.getColor(getContext(), R.color.app_color_white));
                                        time_start.changeColon(ContextCompat.getColor(getContext(), R.color.app_bg_colon));
                                        time_start.start();

                                    }
                                }

                            }else {
                                ll_skill.setVisibility(View.GONE);
                            }
//                            skillAdapter.notifyDataSetChanged();
//                            skillAdvAdapter.notifyDataSetChanged();

                            //判断是否弹店铺类型的弹窗
                            if (homeBaseModel.getData().getShopFlag() == 1 && !isShowed) {
                                isShowed = true;
                                showSelectType();
                            }
//                            String city = UserInfoHelper.getCity(mActivity);

//                            tv_city.setText(city);

                            EventBus.getDefault().post(new CityEvent());

                            if(homeBaseModel.getData().getClassicDesc()!=null) {
                                tv_inner_classify.setText(homeBaseModel.getData().getClassicDesc());
                            }
                            if(homeBaseModel.getData().getRecommendDesc()!=null) {
                                tv_inner_new.setText(homeBaseModel.getData().getRecommendDesc());
                                tv_new.setText(homeBaseModel.getData().getRecommendTitle());
                            }


                            //公告
                            if (homeBaseModel.getData().getIndexNoticeList().size() > 0) {
                                ll_notice.setVisibility(View.VISIBLE);
                                for (int i = 0; i < homeBaseModel.getData().getIndexNoticeList().size(); i++) {
                                    noticeList.add(homeBaseModel.getData().getIndexNoticeList().get(i).getNoticeTitle());
                                }
                                notice.startWithList(noticeList);
                            } else {
                                ll_notice.setVisibility(View.GONE);
                            }

                            //八个icon
//                            iconList.clear();
//                            iconList.addAll(homeBaseModel.getData().getIconList());
                            rvIconAdapter.notifyDataSetChanged();

                            //特惠
                            if(homeBaseModel.getData().getOfferList()!=null&&homeBaseModel.getData().getOfferList().size()>0) {
                                couponList.clear();
                                ll_coupon.setVisibility(View.VISIBLE);
                                couponList.addAll(homeBaseModel.getData().getOfferList());
                                couponAdapter.notifyDataSetChanged();
                                tv_coupon.setText(homeBaseModel.getData().getOfferTitle());
                                tv_coupon_desc.setText(homeBaseModel.getData().getOfferDesc());
                            }else {
                                ll_coupon.setVisibility(View.GONE);
                            }

                            //组合
                            if(homeBaseModel.getData().getTeamList()!=null&&homeBaseModel.getData().getTeamList().size()>0) {
                                teamList.clear();
                                ll_team.setVisibility(View.VISIBLE);
                                teamList.addAll(homeBaseModel.getData().getTeamList());
                                Log.d("swddwddddsdweeee...",teamList.size()+"");
                                teamAdapter.notifyDataSetChanged();
                                tv_team.setText(homeBaseModel.getData().getTeamTitle());
                                tv_team_desc.setText(homeBaseModel.getData().getTeamDesc());
                            }else {
                                ll_team.setVisibility(View.GONE);
                            }

                            //精选
                            if (homeBaseModel.getData().getClassicList() != null && homeBaseModel.getData().getClassicList().size() > 0) {
                                List<HomeBaseModel.DataBean.ClassicListBean> classicList = homeBaseModel.getData().getClassicList();
                                ll_classify.setVisibility(View.VISIBLE);
                                if (classicList.size() == 1) {
                                    iv1.setVisibility(View.VISIBLE);
                                    iv2.setVisibility(View.GONE);
                                    iv3.setVisibility(View.GONE);
                                    iv4.setVisibility(View.GONE);
                                    iv5.setVisibility(View.GONE);
                                    iv6.setVisibility(View.GONE);
                                    Glide.with(context).load(classicList.get(0).getImg()).into(iv1);
                                    id0 = classicList.get(0).getId();
                                    title0 = classicList.get(0).getTitle();

                                }

                                if (classicList.size() == 2) {
                                    iv1.setVisibility(View.VISIBLE);
                                    iv2.setVisibility(View.VISIBLE);
                                    iv3.setVisibility(View.GONE);
                                    iv4.setVisibility(View.GONE);
                                    iv5.setVisibility(View.GONE);
                                    iv6.setVisibility(View.GONE);
                                    Glide.with(context).load(classicList.get(0).getImg()).into(iv1);
                                    Glide.with(context).load(classicList.get(1).getImg()).into(iv2);

                                    id0 = classicList.get(0).getId();
                                    title0 = classicList.get(0).getTitle();
                                    id1 = classicList.get(1).getId();
                                    title1 = classicList.get(1).getTitle();
                                }

                                if (classicList.size() == 3) {
                                    iv1.setVisibility(View.VISIBLE);
                                    iv2.setVisibility(View.VISIBLE);
                                    iv3.setVisibility(View.VISIBLE);
                                    iv4.setVisibility(View.GONE);
                                    iv5.setVisibility(View.GONE);
                                    iv6.setVisibility(View.GONE);
                                    Glide.with(context).load(classicList.get(0).getImg()).into(iv1);
                                    Glide.with(context).load(classicList.get(1).getImg()).into(iv2);
                                    Glide.with(context).load(classicList.get(2).getImg()).into(iv3);

                                    id0 = classicList.get(0).getId();
                                    title0 = classicList.get(0).getTitle();
                                    id1 = classicList.get(1).getId();
                                    title1 = classicList.get(1).getTitle();
                                    id2 = classicList.get(2).getId();
                                    title2 = classicList.get(2).getTitle();
                                }

                                if (classicList.size() == 4) {
                                    iv1.setVisibility(View.VISIBLE);
                                    iv2.setVisibility(View.VISIBLE);
                                    iv3.setVisibility(View.VISIBLE);
                                    iv4.setVisibility(View.VISIBLE);
                                    iv5.setVisibility(View.GONE);
                                    iv6.setVisibility(View.GONE);
                                    Glide.with(context).load(classicList.get(0).getImg()).into(iv1);
                                    Glide.with(context).load(classicList.get(1).getImg()).into(iv2);
                                    Glide.with(context).load(classicList.get(2).getImg()).into(iv3);
                                    Glide.with(context).load(classicList.get(3).getImg()).into(iv4);

                                    id0 = classicList.get(0).getId();
                                    title0 = classicList.get(0).getTitle();
                                    id1 = classicList.get(1).getId();
                                    title1 = classicList.get(1).getTitle();
                                    id2 = classicList.get(2).getId();
                                    title2 = classicList.get(2).getTitle();
                                    id3 = classicList.get(3).getId();
                                    title3 = classicList.get(3).getTitle();


                                }

                                if (classicList.size() == 5) {
                                    iv1.setVisibility(View.VISIBLE);
                                    iv2.setVisibility(View.VISIBLE);
                                    iv3.setVisibility(View.VISIBLE);
                                    iv4.setVisibility(View.VISIBLE);
                                    iv5.setVisibility(View.VISIBLE);
                                    iv6.setVisibility(View.GONE);
                                    Glide.with(context).load(classicList.get(0).getImg()).into(iv1);
                                    Glide.with(context).load(classicList.get(1).getImg()).into(iv2);
                                    Glide.with(context).load(classicList.get(2).getImg()).into(iv3);
                                    Glide.with(context).load(classicList.get(3).getImg()).into(iv4);
                                    Glide.with(context).load(classicList.get(4).getImg()).into(iv5);

                                    id0 = classicList.get(0).getId();
                                    title0 = classicList.get(0).getTitle();
                                    id1 = classicList.get(1).getId();
                                    title1 = classicList.get(1).getTitle();
                                    id2 = classicList.get(2).getId();
                                    title2 = classicList.get(2).getTitle();
                                    id3 = classicList.get(3).getId();
                                    title3 = classicList.get(3).getTitle();
                                    id4 = classicList.get(4).getId();
                                    title4 = classicList.get(4).getTitle();
                                }

                                if (classicList.size() == 6) {
                                    iv1.setVisibility(View.VISIBLE);
                                    iv2.setVisibility(View.VISIBLE);
                                    iv3.setVisibility(View.VISIBLE);
                                    iv4.setVisibility(View.VISIBLE);
                                    iv5.setVisibility(View.VISIBLE);
                                    iv6.setVisibility(View.VISIBLE);
                                    Glide.with(context).load(classicList.get(0).getImg()).into(iv1);
                                    Glide.with(context).load(classicList.get(1).getImg()).into(iv2);
                                    Glide.with(context).load(classicList.get(2).getImg()).into(iv3);
                                    Glide.with(context).load(classicList.get(3).getImg()).into(iv4);
                                    Glide.with(context).load(classicList.get(4).getImg()).into(iv5);
                                    Glide.with(context).load(classicList.get(5).getImg()).into(iv6);

                                    id0 = classicList.get(0).getId();
                                    title0 = classicList.get(0).getTitle();
                                    id1 = classicList.get(1).getId();
                                    title1 = classicList.get(1).getTitle();
                                    id2 = classicList.get(2).getId();
                                    title2 = classicList.get(2).getTitle();
                                    id3 = classicList.get(3).getId();
                                    title3 = classicList.get(3).getTitle();
                                    id4 = classicList.get(4).getId();
                                    title4 = classicList.get(4).getTitle();
                                    id5 = classicList.get(5).getId();
                                    title5 = classicList.get(5).getTitle();
                                }

                            }else {
                                ll_classify.setVisibility(View.GONE);
                            }
                        }
                    }
                });
    }

    /**
     * 选择店铺类型
     */
    private void showSelectType() {
        GetRegisterShopAPI.requestData(mActivity, invitationCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetRegisterShopModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ccca", e.getMessage());
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
                                        updateUserInvitation(invitationCode, shopTypeId);
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
     * 提交授权码
     * @param
     * @param
     */
    private void updateUserInvitation(String invitationCode, int shopTypeId) {
        UpdateUserInvitationAPI.requestData(mActivity, invitationCode, shopTypeId)
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
                            PageNum = 1;
                            requestOrderNum();
                            getBaseList("version","1");
                            getNewProductList(1+"",12+"");
                            UserInfoHelper.saveUserHomeRefresh(getContext(), "home_has_refresh");
                        } else {
                            AppHelper.showMsg(mActivity, updateUserInvitationModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 初始化banner
     */
    private void initBanner() {

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

            case R.id.ll_coupon:
                Intent couponIntent = new Intent(getActivity(), CouponDetailActivity.class);
                getActivity().startActivity(couponIntent);
                break;

            case R.id.ll_team:
                Intent teamIntent = new Intent(getActivity(), TeamDetailActivity.class);
                getActivity().startActivity(teamIntent);
                break;

            case R.id.ll_skill:
                Intent skillIntent = new Intent(getActivity(), HomeGoodsListActivity.class);
                skillIntent.putExtra(AppConstant.PAGETYPE, AppConstant.SECONDTYPE);
                getActivity().startActivity(skillIntent);
                break;

            case R.id.iv1:
                IntentClass(id0, title0);
                break;

            case R.id.iv2:
                IntentClass(id1, title1);
                break;

            case R.id.iv3:
                IntentClass(id2, title2);
                break;

            case R.id.iv4:
                IntentClass(id3, title3);
                break;

            case R.id.iv5:
                IntentClass(id4, title4);
                break;

            case R.id.iv6:
                IntentClass(id5, title5);
                break;

            case R.id.rl_message:
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(getActivity()))) {

//                    Intent messageIntent = new Intent(getActivity(), MessageCenterActivity.class);
                    Intent messageIntents = new Intent(getActivity(), ChooseAddressActivity.class);
                    startActivity(messageIntents);
//                    startActivityForResult(messageIntents, 101);

                } else {
                    AppHelper.showMsg(getActivity(), "请先登录");
                    startActivity(LoginActivity.getIntent(getActivity(), LoginActivity.class));
                }
                break;

            case R.id.tv_city:
                //选择城市
                if (homeBaseModels.getData().getCityName() != null && StringHelper.notEmptyAndNull(homeBaseModels.getData().getCityName())) {

                } else {
//                    startActivity(new Intent(mActivity, ChangeCityActivity.class));

                    Intent messageIntent = new Intent(getActivity(), ChangeCityActivity.class);
                    startActivityForResult(messageIntent, 104);
                }
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
            getNewProductList(1+"", PageSize+"");
            getBaseList("version","1");
        }
    }

    // 跳转精选分类传参
    private void IntentClass(int productId, String title) {
        Intent intent = new Intent(getActivity(), SelectionGoodActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("title", title);
        startActivity(intent);
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
    public void loginEvent(LoginEvent event) {
        //刷新UI
        newList.clear();
        skillList.clear();
        skillAdvList.clear();
        getNewProductList(1+"", PageSize+"");
        getBaseList("version","1");
        Log.d("woshiwangtao....","sddwd");
    }
}
