package com.puyue.www.qiaoge.fragment.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.ChangeCityActivity;
import com.puyue.www.qiaoge.activity.home.HomeGoodsListActivity;
import com.puyue.www.qiaoge.activity.home.NoticeListActivity;
import com.puyue.www.qiaoge.activity.home.SearchStartActivity;
import com.puyue.www.qiaoge.activity.home.SelectionGoodsActivity;
import com.puyue.www.qiaoge.activity.mine.MessageCenterActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.HomeGroupAdapter;
import com.puyue.www.qiaoge.adapter.home.HomeRecommendAdapter;
import com.puyue.www.qiaoge.adapter.home.HomeSpecialOfferAdapter;
import com.puyue.www.qiaoge.adapter.home.HomeTipAdapter;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.home.QueryHomePropupAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.api.mine.UpdateAPI;
import com.puyue.www.qiaoge.api.mine.order.MyOrderNumAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DividerItemDecoration;
import com.puyue.www.qiaoge.helper.DividerItemDecorationTwo;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.TwoDeviceHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.HomeNewRecommendModel;
import com.puyue.www.qiaoge.model.home.HomeNewSecKillAdapter;
import com.puyue.www.qiaoge.model.home.HomeSecKillPreAdapter;
import com.puyue.www.qiaoge.model.home.IndexHomeModel;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.mine.UpdateModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;
import com.puyue.www.qiaoge.popupwindow.HomePopuWindow;
import com.puyue.www.qiaoge.view.MarqueeView;
import com.puyue.www.qiaoge.view.ScrollBottomScrollView;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by Administrator on 2018/3/28.
 */
//StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))
public class HomeFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    //banner
    private ArrayList<IndexHomeModel.DataBean.BannerListBean> mListBanner = new ArrayList<>();
    //8大icon
    private List<IndexHomeModel.DataBean.IconListBean> mListTip = new ArrayList<>();
    private HomeTipAdapter mAdapterHomeTip;
    //超值组合
    private List<IndexHomeModel.DataBean.TeamListBean> mListGroup = new ArrayList<>();
    private HomeGroupAdapter mAdapterHomeGroup;
    //新品上市
    private List<IndexHomeModel.DataBean.ProdPageBean.ListBean> mListRecommend = new ArrayList<>();
    private HomeRecommendAdapter mAdapterHomeRecommendNew;
    //精选秒杀
    private List<IndexHomeModel.DataBean.NewSecKillBean> mNewSecKill = new ArrayList<>();
    private List<IndexHomeModel.DataBean.NewSecKillBean.SecKill> secKill1 = new ArrayList<>();
    private List<IndexHomeModel.DataBean.NewSecKillBean.SecKill> secKill2 = new ArrayList<>();

    private HomeNewSecKillAdapter mAdatpterHomeNewSecKill;
    private HomeSecKillPreAdapter mAdapterHomeSecKillPre;
    //特惠专区
    private List<IndexHomeModel.DataBean.OfferListBean> mOfferList = new ArrayList<>();
    private HomeSpecialOfferAdapter mAdapterHomeSpecial;
    private PtrClassicFrameLayout mPtr;
    private SliderLayout mViewBanner;
    private RecyclerView mRvTips;
    private TextView mTvSecKillMore;
    private TextView mTvGroupMore;
    private RecyclerView mRvSecKill;
    private RecyclerView mRvGroup;
    private RecyclerView mRvRecommed;
    private RecyclerView mRvSpecial;
    private RecyclerView mRvSecKillPre;
    private GridLayoutManager mLayoutManagerRecommend;  //特别精选
    private LinearLayoutManager mLayoutManagerNewSecKill; // 秒杀
    private LinearLayoutManager mLayoutManagerNewSecKillPre;//秒杀预告
    private LinearLayoutManager mLayoutManagerGroup;//团购
    private LinearLayoutManager mLayoutManagerSpecial;//特惠
    private GridLayoutManager mLayoutManagerTips;
    private String cell; // 客服电话
    private TextView mTvSearch;
    private LinearLayout mLlSpecial;
    private LinearLayout mLlSecKill;
    private LinearLayout mLlGroup;
    private RelativeLayout mLlRecommend;
    private RelativeLayout mRlPreKill;

    private MarqueeView mViewNotice;
    private List<String> mListNotice = new ArrayList<>();
    private int pageNum = 1;
    private String pageSize = "9";
    private IndexHomeModel mModelIndexHome;
    private ScrollBottomScrollView mViewScroll;
    //新品Model
    HomeNewRecommendModel homeNewRecommendModel1;
    private LoadingDailog dialog;
    private ImageView homeMessage;
    private MyOrderNumModel mModelMyOrderNum;
    private TextView mViewMessageNum;

    private TextView killTitle;
    private TextView killDesc;
    private TextView teamTitle;
    private TextView teamDesc;
    private TextView recommendTitle;
    private TextView recommendDesc;
    private TextView specialTitle;
    private TextView specialContent;
    // 精选分类
    private TextView tvSelectionTitle;
    private TextView tvSelectionContent;
    private ImageView imSelectionOne;
    private ImageView imSelectionTwo;
    private ImageView imSelectionThree;
    private ImageView imSelectionFour;
    private ImageView imSelectionFive;
    private ImageView imSelectionSix;
    private int productIdOne;
    private int productIdTwo;
    private int productIdThree;
    private int productIdFour;
    private int productIdFive;
    private int productIdSix;

    private String token;
    private String version;
    private String clientType = "1";

    private String titleOne;
    private String titleTwo;
    private String titleThree;
    private String titleFour;
    private String titleFive;
    private String titleSix;

    private UpdateModel mModelUpdate;
    private boolean update;
    private boolean forceUpdate;
    private String content;
    private String url;//更新所用的url

    private SnapUpCountDownTimerView spikeTimeKill;
    private SnapUpCountDownTimerView spikeTimePreKill;
    private TextView tvSpecialMore;
    private TextView mTvPreContent;
    private View mTvSpikeLine;
    private View mTvSpikeLineTwo;

    private RelativeLayout rootview;
    private boolean isFirst = true;

    //位置更换
    private TextView tv_home_top_location;
    private LinearLayout ll_classify;

    private List<GetRegisterShopModel.DataBean> list = new ArrayList<>();
    int isSelected;
    int shopTypeId;
    boolean isChecked = false;
    private AlertDialog mTypedialog;

    private LinearLayout ll_home_notice;

    private String invitationCode;

    private boolean isShowed = false;

    @Override
    public int setLayoutId() {
        setTranslucentStatus();
        return R.layout.fragment_home;
    }

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

    @Override
    public void findViewById(View view) {
        tvSelectionTitle = (view.findViewById(R.id.tvSelectionTitle));
        tvSelectionContent = (view.findViewById(R.id.tvSelectionContent));
        killTitle = (view.findViewById(R.id.killTitle));
        killDesc = (view.findViewById(R.id.killDesc));
        teamTitle = (view.findViewById(R.id.teamTitle));
        teamDesc = (view.findViewById(R.id.teamDesc));
        recommendTitle = (view.findViewById(R.id.recommendTitle));
        recommendDesc = (view.findViewById(R.id.recommendDesc));
//        mViewLoading = (view.findViewById(R.id.lav_dialog));
        mPtr = (view.findViewById(R.id.ptr_home));
        mViewBanner = (view.findViewById(R.id.view_home_banner));

        mRvTips = (view.findViewById(R.id.rv_home_tips));
        // 消息
        homeMessage = (view.findViewById(R.id.homeMessage));
        //秒杀
        mLlSecKill = (view.findViewById(R.id.ll_home_seckill));//秒杀的布局,可能不存在秒杀
        mTvSecKillMore = (view.findViewById(R.id.tv_home_seckill_more));//更多秒杀
        mRvSecKill = (view.findViewById(R.id.rv_home_sec_kill));//秒杀的列表

        mLlSpecial = (view.findViewById(R.id.linearLayout_special));
        //spikeTimeEnd = view.findViewById(R.id.view_item_spike_time_end);

        mLlGroup = (view.findViewById(R.id.ll_home_group));//组合的布局
        mTvGroupMore = (view.findViewById(R.id.tv_home_group_more));//更多组合
        mRvGroup = (view.findViewById(R.id.rv_home_group));//组合的列表

        mLlRecommend = (view.findViewById(R.id.ll_home_recommend));
        mRvRecommed = (view.findViewById(R.id.rv_home_recommend));//推荐的列表

        mTvSearch = FVHelper.fv(view, R.id.tv_home_top_search);
        mViewNotice = (view.findViewById(R.id.view_home_notice));
        mViewScroll = (view.findViewById(R.id.view_home_scroll));
        mViewMessageNum = (view.findViewById(R.id.view_mine_message_num));//消息数量
        // 精选分类
        imSelectionOne = (view.findViewById(R.id.imSelectionOne));
        imSelectionTwo = (view.findViewById(R.id.imSelectionTwo));
        imSelectionThree = (view.findViewById(R.id.imSelectionThree));
        imSelectionFour = (view.findViewById(R.id.imSelectionFour));
        imSelectionFive = (view.findViewById(R.id.imSelectionFive));
        imSelectionSix = (view.findViewById(R.id.imSelectionSix));
        //特惠
        mRvSpecial = (view.findViewById(R.id.rv_discount_group));
        specialTitle = (view.findViewById(R.id.discount_title));
        specialContent = (view.findViewById(R.id.discount_tesc));

        spikeTimeKill = (view.findViewById(R.id.down_time_kill));
        spikeTimePreKill = (view.findViewById(R.id.down_time_pre));
        mRvSecKillPre = (view.findViewById(R.id.recyclerView_kill_pre));
        mRlPreKill = (view.findViewById(R.id.relativeLayout_pre_kill));
        mTvPreContent = (view.findViewById(R.id.tv_kill_pre));
        tvSpecialMore = (view.findViewById(R.id.tv_home_discount_more));//更多特惠
        mTvSpikeLine = (view.findViewById(R.id.spike_line));
        mTvSpikeLineTwo = (view.findViewById(R.id.spike_line_two));
        rootview = (view.findViewById(R.id.rootview));
        tv_home_top_location = (view.findViewById(R.id.tv_home_top_location));
        ll_classify = (view.findViewById(R.id.ll_classify));
        ll_home_notice = (view.findViewById(R.id.ll_home_notice));
    }

    @Override
    public void initViews(View view) {
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void setViewData() {
        requestUpdate();
        token = UserInfoHelper.getUserId(mActivity);
        version = AppConstant.VERSION;
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getContext())
                .setMessage("获取数据中")
                .setCancelable(false)
                .setCancelOutside(false);
        dialog = loadBuilder.create();

        mTypedialog = new AlertDialog.Builder(mActivity, R.style.DialogStyle).create();
        mTypedialog.setCancelable(false);
        mLayoutManagerNewSecKillPre = new LinearLayoutManager(getActivity());
        mLayoutManagerNewSecKillPre.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvSecKillPre.setLayoutManager(mLayoutManagerNewSecKillPre);
        mAdapterHomeSecKillPre = new HomeSecKillPreAdapter(getContext(), secKill2);
        mRvSecKillPre.setAdapter(mAdapterHomeSecKillPre);

        //添加分隔线
        DividerItemDecorationTwo dividerPreKillDecoration = new DividerItemDecorationTwo(mActivity,
                DividerItemDecoration.HORIZONTAL_LIST);
        dividerPreKillDecoration.setDivider(R.drawable.app_divider);
        mRvSecKillPre.addItemDecoration(dividerPreKillDecoration);


        mAdapterHomeSecKillPre.setOnclick(new HomeSecKillPreAdapter.OnClick() {
            @Override
            public void shoppingCartOnClick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    IndexHomeModel.DataBean.NewSecKillBean.SecKill info;
                    if (mNewSecKill.size() > 1) {
                        info = mNewSecKill.get(1).kills.get(position);
                    } else {
                        info = mNewSecKill.get(0).kills.get(position);
                    }
                    addCar(info.activeId, "", 2, "1");

                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                mAdapterHomeSecKillPre.notifyDataSetChanged();
            }
        });


        mLayoutManagerNewSecKill = new LinearLayoutManager(getActivity());
        mLayoutManagerNewSecKill.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvSecKill.setLayoutManager(mLayoutManagerNewSecKill);
        mAdatpterHomeNewSecKill = new HomeNewSecKillAdapter(getContext(), secKill1);
        mRvSecKill.setAdapter(mAdatpterHomeNewSecKill);
        //添加分隔线
        DividerItemDecorationTwo dividerNewKillDecoration = new DividerItemDecorationTwo(mActivity,
                DividerItemDecoration.HORIZONTAL_LIST);
        dividerNewKillDecoration.setDivider(R.drawable.app_divider);
        mRvSecKill.addItemDecoration(dividerNewKillDecoration);


        mAdatpterHomeNewSecKill.setOnclick(new HomeNewSecKillAdapter.OnClick() {
            @Override
            public void shoppingCartOnClick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {

                    IndexHomeModel.DataBean.NewSecKillBean.SecKill info = mNewSecKill.get(0).kills.get(position);
                    addCar(info.activeId, "", 2, "1");
                    Log.i("ccc", "shoppingCartOnClick: " + info.activeId);
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                mAdatpterHomeNewSecKill.notifyDataSetChanged();
            }
        });


        //特惠专区
        mLayoutManagerSpecial = new LinearLayoutManager(getActivity());
        mLayoutManagerSpecial.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvSpecial.setLayoutManager(mLayoutManagerSpecial);
        mAdapterHomeSpecial = new HomeSpecialOfferAdapter(getContext(), mOfferList);
        mRvSpecial.setAdapter(mAdapterHomeSpecial);
        //添加分隔线
        DividerItemDecorationTwo dividerSpecialDecoration = new DividerItemDecorationTwo(mActivity,
                DividerItemDecoration.HORIZONTAL_LIST);
        dividerSpecialDecoration.setDivider(R.drawable.app_divider);
        mRvSpecial.addItemDecoration(dividerSpecialDecoration);

        mAdapterHomeSpecial.setOnClick(new HomeSpecialOfferAdapter.OnClick() {
            @Override
            public void shoppingCartOnClick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    IndexHomeModel.DataBean.OfferListBean info = mOfferList.get(position);
                    addCar(info.activeId, "", 11, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                mAdapterHomeSpecial.notifyDataSetChanged();
            }
        });


        mLayoutManagerGroup = new LinearLayoutManager(getActivity());
        mLayoutManagerGroup.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvGroup.setLayoutManager(mLayoutManagerGroup);
        mAdapterHomeGroup = new HomeGroupAdapter(getContext(), mListGroup);
        mRvGroup.setAdapter(mAdapterHomeGroup);
        //添加分隔线
        DividerItemDecorationTwo dividerItemDecorationMRvGroup = new DividerItemDecorationTwo(mActivity,
                DividerItemDecoration.HORIZONTAL_LIST);
        dividerItemDecorationMRvGroup.setDivider(R.drawable.app_divider);
        mRvGroup.addItemDecoration(dividerItemDecorationMRvGroup);


        mAdapterHomeGroup.setOnClick(new HomeGroupAdapter.OnClick() {
            @Override
            public void shoppingCartOnClick(int position) {   //超值组合(团购)
                IndexHomeModel.DataBean.TeamListBean info = mListGroup.get(position);

                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    addCar(info.activeId, "", 3, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }
                mAdapterHomeGroup.notifyDataSetChanged();
            }
        });


        mLayoutManagerRecommend = new GridLayoutManager(getContext(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mLayoutManagerRecommend.setOrientation(LinearLayoutManager.VERTICAL);

        //添加分隔线
        DividerItemDecoration dividerItemDecorationMRvRecommed = new DividerItemDecoration(mActivity,
                DividerItemDecoration.HORIZONTAL_LIST);
        dividerItemDecorationMRvRecommed.setDivider(R.drawable.app_divider);
        mRvRecommed.addItemDecoration(dividerItemDecorationMRvRecommed);


        mLayoutManagerTips = new GridLayoutManager(getContext(), 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRvTips.setLayoutManager(mLayoutManagerTips);
        mAdapterHomeTip = new HomeTipAdapter(getContext(),mListTip);
        mRvTips.setAdapter(mAdapterHomeTip);
        mPtr.disableWhenHorizontalMove(true);
        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                indexHome( version, clientType);
                getNewProductList(1+"", pageSize);
            }
        });


        mViewScroll.registerOnScrollViewScrollToBottom(new ScrollBottomScrollView.OnScrollBottomListener() {
            @Override
            public void srollToBottom() {
                if (mModelIndexHome != null) {
                    if (mModelIndexHome.data != null) {
                        if (mModelIndexHome.data.prodPage != null) {
                            dialog.show();
                            pageNum++;
                            indexHome(version, clientType);
                        }
                    }
                } else {
                    pageNum = 1;
                    indexHome(version, clientType);
                }

                if(homeNewRecommendModel1!=null) {
                    if(homeNewRecommendModel1.getData()!=null) {
                        if(homeNewRecommendModel1.getData().isHasNextPage()) {
                            pageNum++;
                            getNewProductList(pageNum+"", pageSize);
                        }
                    }
                }

            }
        });

        pageNum = 1;
        indexHome(version, clientType);
        getCustomerPhone();
        if (token != null && StringHelper.notEmptyAndNull(token)) {
            requestOrderNum();
        }
    }




    @Override
    public void setClickEvent() {
        mTvSearch.setOnClickListener(noDoubleClickListener);
        mTvGroupMore.setOnClickListener(noDoubleClickListener);
        mTvSecKillMore.setOnClickListener(noDoubleClickListener);
        homeMessage.setOnClickListener(noDoubleClickListener);
        imSelectionOne.setOnClickListener(noDoubleClickListener);
        imSelectionTwo.setOnClickListener(noDoubleClickListener);
        imSelectionThree.setOnClickListener(noDoubleClickListener);
        imSelectionFour.setOnClickListener(noDoubleClickListener);
        imSelectionFive.setOnClickListener(noDoubleClickListener);
        imSelectionSix.setOnClickListener(noDoubleClickListener);
        tvSpecialMore.setOnClickListener(noDoubleClickListener);
        tv_home_top_location.setOnClickListener(noDoubleClickListener);

        mViewNotice.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                //跳转公告列表
                startActivity(NoticeListActivity.getIntent(getContext(), NoticeListActivity.class));
            }
        });


    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        String banner_url = slider.getBundle().getString("banner_url");
        Log.e(TAG, "onSliderClick: " + banner_url);
        if (StringHelper.notEmptyAndNull(banner_url)) {
            Intent intent = new Intent(getActivity(), NewWebViewActivity.class);
            intent.putExtra("URL", banner_url);
            intent.putExtra("TYPE", 2);
            intent.putExtra("name", "");
            startActivity(intent);
        }
    }

    @Override
    public void onStop() {
        mViewBanner.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == 102) {
                int newPosition = data.getIntExtra("NewPosition", 5);//NewPosition
                Log.e(TAG, "onActivityResult: " + newPosition);
                if (newPosition > 0) {
                    mViewMessageNum.setVisibility(View.VISIBLE);
                    mViewMessageNum.setText("  " + newPosition + "  ");
                } else {
                    mViewMessageNum.setVisibility(View.GONE);
                }
            }
        }
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mTvSearch) {
                Intent intent = new Intent(getActivity(), SearchStartActivity.class);
                intent.putExtra(AppConstant.SEARCHTYPE, AppConstant.HOME_SEARCH);
//                intent.putExtra("flag", "first");
//                intent.putExtra("good_buy", "");


                getActivity().startActivity(intent);
            } else if (view == tv_home_top_location) {
                //选择城市
                if (mModelIndexHome.data.cityName != null && StringHelper.notEmptyAndNull(mModelIndexHome.data.cityName)) {

                } else {
                    startActivity(new Intent(mActivity, ChangeCityActivity.class));
                }


            } else if (view == mTvGroupMore) {
                Intent intent = new Intent(getActivity(), HomeGoodsListActivity.class);
                intent.putExtra(AppConstant.PAGETYPE, AppConstant.GROUPTYPE);
                getActivity().startActivity(intent);

            } else if (view == tvSpecialMore) {
                //特惠更多
                Intent intent = new Intent(getActivity(), HomeGoodsListActivity.class);
                intent.putExtra(AppConstant.PAGETYPE, AppConstant.SPECIAL);
                getActivity().startActivity(intent);

            } else if (view == mTvSecKillMore) {
                //秒杀特惠更多
                Intent intent = new Intent(getActivity(), HomeGoodsListActivity.class);
                intent.putExtra(AppConstant.PAGETYPE, AppConstant.SECONDTYPE);
                getActivity().startActivity(intent);
            } else if (view == homeMessage) { // 消息
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(getActivity()))) {
                    Intent intent = new Intent(getActivity(), MessageCenterActivity.class);
                    startActivityForResult(intent, 101);

                } else {
                    AppHelper.showMsg(getActivity(), "请先登录");
                    startActivity(LoginActivity.getIntent(getActivity(), LoginActivity.class));
                }

            } else if (view == imSelectionOne) {
                IntentClass(productIdOne, titleOne);
            } else if (view == imSelectionTwo) {
                IntentClass(productIdTwo, titleTwo);
            } else if (view == imSelectionThree) {
                IntentClass(productIdThree, titleThree);
            } else if (view == imSelectionFour) {
                IntentClass(productIdFour, titleFour);
            } else if (view == imSelectionFive) {
                IntentClass(productIdFive, titleFive);
            } else if (view == imSelectionSix) {
                IntentClass(productIdSix, titleSix);
            }
        }
    };

    // 跳转精选分类传参
    private void IntentClass(int productId, String title) {
        Intent intent = new Intent(getActivity(), SelectionGoodsActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("title", title);
        startActivity(intent);
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
                        }

                    }
                });
    }

    // 更新购物车角标
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
     * 提交授权码
     * 传shopTypeId
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
                            UserInfoHelper.saveUserId(mActivity, updateUserInvitationModel.getData());
                            dialog.show();
                            pageNum = 1;
                            requestOrderNum();
                            indexHome(version, clientType);
                            UserInfoHelper.saveUserHomeRefresh(getContext(), "home_has_refresh");
                        } else {
                            AppHelper.showMsg(mActivity, updateUserInvitationModel.getMessage());
                        }
                    }
                });
    }

    /***
     *多规格新品列表接口
     */
    private void getNewProductList(String pageNum1, String pageSize1) {
        IndexHomeAPI.getRecommendData(mActivity, pageNum1,pageSize1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeNewRecommendModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeNewRecommendModel homeNewRecommendModel) {
                        homeNewRecommendModel1 = homeNewRecommendModel;
                        List<HomeNewRecommendModel.DataBean.ListBean> list = homeNewRecommendModel.getData().getList();
                        mRvRecommed.setLayoutManager(mLayoutManagerRecommend);
                        mAdapterHomeRecommendNew = new HomeRecommendAdapter(R.layout.item_home_recommendnew_new, list);
                        mRvRecommed.setAdapter(mAdapterHomeRecommendNew);
                    }
                });
    }

    /**
     * 获得首页数据（除新品外）
     */

    private void indexHome( String version, String clientType) {
        IndexHomeAPI.requestData(getActivity(), version, clientType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IndexHomeModel>() {
                    @Override
                    public void onCompleted() {
                        mPtr.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPtr.refreshComplete();
                    }

                    @Override
                    public void onNext(IndexHomeModel indexHomeModel) {
                        if (indexHomeModel.success) {
                            invitationCode = indexHomeModel.data.invitationCode;
                            if (indexHomeModel.data.shopFlag == 1 && !isShowed) {
                                isShowed = true;
//                                if (UserInfoHelper.getIsregister(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getIsregister(mActivity))) {
//
//                                } else {
                                    showSelectType();
//                                }
                            }

                            if (indexHomeModel.data.cityName != null && StringHelper.notEmptyAndNull(indexHomeModel.data.cityName)) {
//                                tv_home_top_location.setText(indexHomeModel.data.cityName);
                            } else {

                                if (UserInfoHelper.getCity(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getCity(mActivity))) {
                                    tv_home_top_location.setText(UserInfoHelper.getCity(mActivity));
                                    Log.i("city11", "city: " + UserInfoHelper.getCity(mActivity));
                                }
                            }
                            dialog.dismiss();
                            //请求数据成功
                            mModelIndexHome = indexHomeModel;
                            mListNotice.clear();
                            Log.i("aaaaa", "onNext: " + indexHomeModel.data.offerList.size() + indexHomeModel.data.iconList.size());
                            //
                            if (pageNum == 1) {


                                if (indexHomeModel.data.iconList != null) {
                                    //提示
                                    mListTip.clear();
                                    mListTip.addAll(indexHomeModel.data.iconList);

                                    mAdapterHomeTip.notifyDataSetChanged();
                                }
                                if (indexHomeModel.data.offerList != null && indexHomeModel.data.offerList.size() > 0) {
                                    //特惠
                                    mLlSpecial.setVisibility(View.VISIBLE);
                                    mOfferList.clear();
                                    //
                                    mOfferList.addAll(indexHomeModel.data.offerList);
                                    mAdapterHomeSpecial.notifyDataSetChanged();
                                    specialTitle.setText(indexHomeModel.data.offerTitle);


                                    specialContent.setText(indexHomeModel.data.offerDesc);


                                } else {

                                    mLlSpecial.setVisibility(View.GONE);
                                }


                                if (indexHomeModel.data.secKillList != null && indexHomeModel.data.secKillList.size() > 0) {

                                    mLlSecKill.setVisibility(View.VISIBLE);
                                    long currentTime = 0;
                                    long startTime = 0;
                                    long endTime = 0;
                                    long currentTimePre = 0;
                                    long startTimePre = 0;
                                    long endTimePre = 0;


                                    secKill1.clear();
                                    secKill2.clear();


                                    //秒杀
                                    mNewSecKill.clear();

                                    mNewSecKill.addAll(indexHomeModel.data.secKillList);
                                    if (indexHomeModel.data.secKillList.size() > 1) {

                                        for (int i = 0; i < indexHomeModel.data.secKillList.size(); i++) {
                                            if (indexHomeModel.data.secKillList.get(i).flag == 1) {
                                                secKill1.addAll(indexHomeModel.data.secKillList.get(i).kills);
                                                mAdatpterHomeNewSecKill.notifyDataSetChanged();

                                                spikeTimeKill.setVisibility(View.VISIBLE);
                                                mRvSecKill.setVisibility(View.VISIBLE);
                                                mTvSpikeLine.setVisibility(View.VISIBLE);
                                                mTvSpikeLineTwo.setVisibility(View.VISIBLE);
                                                currentTime = Long.parseLong(indexHomeModel.data.secKillList.get(i).currentTime);
                                                startTime = Long.parseLong(indexHomeModel.data.secKillList.get(i).startTime);
                                                endTime = Long.parseLong(indexHomeModel.data.secKillList.get(i).endTime);

                                            } else {
                                                if (indexHomeModel.data.secKillList.get(i).flag == 0) {

                                                    secKill2.addAll(indexHomeModel.data.secKillList.get(i).kills);
                                                    mAdapterHomeSecKillPre.notifyDataSetChanged();

                                                    currentTimePre = Long.parseLong(indexHomeModel.data.secKillList.get(i).currentTime);
                                                    startTimePre = Long.parseLong(indexHomeModel.data.secKillList.get(i).startTime);
                                                    endTimePre = Long.parseLong(indexHomeModel.data.secKillList.get(i).endTime);
                                                    mTvPreContent.setText(indexHomeModel.data.killTrailDesc);
                                                }
                                            }
                                        }
                                    } else {
                                        if (indexHomeModel.data.secKillList.get(0).flag == 1) {
                                            secKill1.addAll(indexHomeModel.data.secKillList.get(0).kills);
                                            mAdatpterHomeNewSecKill.notifyDataSetChanged();
                                            mRlPreKill.setVisibility(View.GONE);
                                            spikeTimeKill.setVisibility(View.VISIBLE);
                                            killDesc.setVisibility(View.VISIBLE);
                                            mRvSecKill.setVisibility(View.VISIBLE);
                                            mTvSpikeLine.setVisibility(View.VISIBLE);
                                            mTvSpikeLineTwo.setVisibility(View.VISIBLE);
                                            currentTime = Long.parseLong(indexHomeModel.data.secKillList.get(0).currentTime);
                                            startTime = Long.parseLong(indexHomeModel.data.secKillList.get(0).startTime);
                                            endTime = Long.parseLong(indexHomeModel.data.secKillList.get(0).endTime);

                                        } else {
                                            if (indexHomeModel.data.secKillList.get(0).flag == 0) {
                                                mRlPreKill.setVisibility(View.VISIBLE);
                                                secKill2.addAll(indexHomeModel.data.secKillList.get(0).kills);
                                                mAdapterHomeSecKillPre.notifyDataSetChanged();
                                                killDesc.setVisibility(View.GONE);
                                                spikeTimeKill.setVisibility(View.GONE);
                                                mRvSecKill.setVisibility(View.GONE);
                                                mTvSpikeLine.setVisibility(View.GONE);
                                                mTvSpikeLineTwo.setVisibility(View.GONE);
                                                currentTimePre = Long.parseLong(indexHomeModel.data.secKillList.get(0).currentTime);
                                                startTimePre = Long.parseLong(indexHomeModel.data.secKillList.get(0).startTime);
                                                endTimePre = Long.parseLong(indexHomeModel.data.secKillList.get(0).endTime);
                                                mTvPreContent.setText(indexHomeModel.data.killTrailDesc);
                                            }
                                        }

                                    }


                                    spikeTimeKill.setBackTheme(false);
                                    spikeTimeKill.setTime(true, currentTime, startTime, endTime);
                                    spikeTimeKill.changeTextColor(ContextCompat.getColor(getContext(), R.color.app_color_white));
                                    spikeTimeKill.changeColon(ContextCompat.getColor(getContext(), R.color.app_bg_colon));
                                    spikeTimeKill.start();

                                    spikeTimePreKill.setBackTheme(false);
                                    spikeTimePreKill.setTime(true, currentTimePre, startTimePre, endTimePre);
                                    spikeTimePreKill.changeTextColor(ContextCompat.getColor(getContext(), R.color.app_color_white));
                                    spikeTimePreKill.changeColon(ContextCompat.getColor(getContext(), R.color.app_bg_colon));
                                    spikeTimePreKill.start();


                                } else {
                                    mLlSecKill.setVisibility(View.GONE);
                                }

                                if (indexHomeModel.data.prodPage.list != null && indexHomeModel.data.prodPage.list.size() > 0) {
                                    //新品上市
                                    mLlRecommend.setVisibility(View.VISIBLE);
                                    mListRecommend.clear();
                                    mListRecommend.addAll(indexHomeModel.data.prodPage.list);
                                    mAdapterHomeRecommendNew.notifyDataSetChanged();
                                } else {
                                    mLlRecommend.setVisibility(View.GONE);
                                }
                                if (indexHomeModel.data.teamList != null && indexHomeModel.data.teamList.size() > 0) {
                                    //超值组合
                                    Log.i("wee", "onNext: " + indexHomeModel.data.teamList);
                                    mLlGroup.setVisibility(View.VISIBLE);
                                    mListGroup.clear();
                                    mListGroup.addAll(indexHomeModel.data.teamList);
                                    mAdapterHomeGroup.notifyDataSetChanged();
                                } else {
                                    mLlGroup.setVisibility(View.GONE);
                                }
                                if (indexHomeModel.data.bannerList.size() > 0) {
                                    mViewBanner.setVisibility(View.VISIBLE);
                                    mListBanner.clear();
                                    mListBanner.addAll(indexHomeModel.data.bannerList);
                                    initBanner();
                                } else {
                                    mViewBanner.setVisibility(View.GONE);
                                }

                                if (indexHomeModel.data.indexNoticeList.size() > 0) {
                                    ll_home_notice.setVisibility(View.VISIBLE);
                                    for (int i = 0; i < indexHomeModel.data.indexNoticeList.size(); i++) {
                                        mListNotice.add(indexHomeModel.data.indexNoticeList.get(i).noticeTitle);
                                    }
                                    mViewNotice.startWithList(mListNotice);
                                } else {
                                    ll_home_notice.setVisibility(View.GONE);
                                }
                            } else {
                                mListRecommend.addAll(indexHomeModel.data.prodPage.list);
                                mAdapterHomeRecommendNew.notifyDataSetChanged();
                                // dialog.dismiss();
                            }

                            if (indexHomeModel.data.classicList != null && indexHomeModel.data.classicList.size() > 0) {


                                ll_classify.setVisibility(View.VISIBLE);

                                if (indexHomeModel.data.classicList.size() == 1) {
                                    imSelectionOne.setVisibility(View.VISIBLE);
                                    imSelectionTwo.setVisibility(View.GONE);
                                    imSelectionThree.setVisibility(View.GONE);
                                    imSelectionFour.setVisibility(View.GONE);
                                    imSelectionFive.setVisibility(View.GONE);
                                    imSelectionSix.setVisibility(View.GONE);

                                    productIdOne = indexHomeModel.data.classicList.get(0).id;

                                    if (indexHomeModel.data.classicList.get(0).title != null) {
                                        titleOne = indexHomeModel.data.classicList.get(0).title;
                                    } else {
                                        titleOne = "";
                                    }

                                    if (indexHomeModel.data.classicList.get(0).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(0).img).into(imSelectionOne);
                                    } else {
                                        imSelectionOne.setImageDrawable(null);
                                    }


                                } else if (indexHomeModel.data.classicList.size() == 2) {
                                    imSelectionOne.setVisibility(View.VISIBLE);
                                    imSelectionTwo.setVisibility(View.VISIBLE);
                                    imSelectionThree.setVisibility(View.GONE);
                                    imSelectionFour.setVisibility(View.GONE);
                                    imSelectionFive.setVisibility(View.GONE);
                                    imSelectionSix.setVisibility(View.GONE);

                                    productIdOne = indexHomeModel.data.classicList.get(0).id;
                                    productIdTwo = indexHomeModel.data.classicList.get(1).id;

                                    if (indexHomeModel.data.classicList.get(0).title != null) {
                                        titleOne = indexHomeModel.data.classicList.get(0).title;
                                    } else {
                                        titleOne = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(1).title != null) {
                                        titleTwo = indexHomeModel.data.classicList.get(1).title;
                                    } else {
                                        titleTwo = "";
                                    }

                                    if (indexHomeModel.data.classicList.get(0).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(0).img).into(imSelectionOne);
                                    } else {
                                        imSelectionOne.setImageDrawable(null);
                                    }


                                    if (indexHomeModel.data.classicList.get(1).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(1).img).into(imSelectionTwo);
                                    } else {
                                        imSelectionTwo.setImageDrawable(null);
                                    }

                                } else if (indexHomeModel.data.classicList.size() == 3) {
                                    imSelectionOne.setVisibility(View.VISIBLE);
                                    imSelectionTwo.setVisibility(View.VISIBLE);
                                    imSelectionThree.setVisibility(View.VISIBLE);
                                    imSelectionFour.setVisibility(View.GONE);
                                    imSelectionFive.setVisibility(View.GONE);
                                    imSelectionSix.setVisibility(View.GONE);

                                    productIdOne = indexHomeModel.data.classicList.get(0).id;
                                    productIdTwo = indexHomeModel.data.classicList.get(1).id;
                                    productIdThree = indexHomeModel.data.classicList.get(2).id;

                                    if (indexHomeModel.data.classicList.get(0).title != null) {
                                        titleOne = indexHomeModel.data.classicList.get(0).title;
                                    } else {
                                        titleOne = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(1).title != null) {
                                        titleTwo = indexHomeModel.data.classicList.get(1).title;
                                    } else {
                                        titleTwo = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(2).title != null) {
                                        titleThree = indexHomeModel.data.classicList.get(2).title;
                                    } else {
                                        titleThree = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(0).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(0).img).into(imSelectionOne);
                                    } else {
                                        imSelectionOne.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(1).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(1).img).into(imSelectionTwo);
                                    } else {
                                        imSelectionTwo.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(2).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(2).img).into(imSelectionThree);
                                    } else {
                                        imSelectionThree.setImageDrawable(null);
                                    }

                                } else if (indexHomeModel.data.classicList.size() == 4) {
                                    imSelectionOne.setVisibility(View.VISIBLE);
                                    imSelectionTwo.setVisibility(View.VISIBLE);
                                    imSelectionThree.setVisibility(View.VISIBLE);
                                    imSelectionFour.setVisibility(View.VISIBLE);
                                    imSelectionFive.setVisibility(View.GONE);
                                    imSelectionSix.setVisibility(View.GONE);

                                    productIdOne = indexHomeModel.data.classicList.get(0).id;
                                    productIdTwo = indexHomeModel.data.classicList.get(1).id;
                                    productIdThree = indexHomeModel.data.classicList.get(2).id;
                                    productIdFour = indexHomeModel.data.classicList.get(3).id;

                                    if (indexHomeModel.data.classicList.get(0).title != null) {
                                        titleOne = indexHomeModel.data.classicList.get(0).title;
                                    } else {
                                        titleOne = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(1).title != null) {
                                        titleTwo = indexHomeModel.data.classicList.get(1).title;
                                    } else {
                                        titleTwo = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(2).title != null) {
                                        titleThree = indexHomeModel.data.classicList.get(2).title;
                                    } else {
                                        titleThree = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(3).title != null) {
                                        titleFour = indexHomeModel.data.classicList.get(3).title;
                                    } else {
                                        titleFour = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(0).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(0).img).into(imSelectionOne);
                                    } else {
                                        imSelectionOne.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(1).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(1).img).into(imSelectionTwo);
                                    } else {
                                        imSelectionTwo.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(2).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(2).img).into(imSelectionThree);
                                    } else {
                                        imSelectionThree.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(3).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(3).img).into(imSelectionFour);
                                    } else {
                                        imSelectionFour.setImageDrawable(null);
                                    }

                                } else if (indexHomeModel.data.classicList.size() == 5) {
                                    imSelectionOne.setVisibility(View.VISIBLE);
                                    imSelectionTwo.setVisibility(View.VISIBLE);
                                    imSelectionThree.setVisibility(View.VISIBLE);
                                    imSelectionFour.setVisibility(View.VISIBLE);
                                    imSelectionFive.setVisibility(View.VISIBLE);
                                    imSelectionSix.setVisibility(View.GONE);

                                    productIdOne = indexHomeModel.data.classicList.get(0).id;
                                    productIdTwo = indexHomeModel.data.classicList.get(1).id;
                                    productIdThree = indexHomeModel.data.classicList.get(2).id;
                                    productIdFour = indexHomeModel.data.classicList.get(3).id;
                                    productIdFive = indexHomeModel.data.classicList.get(4).id;

                                    if (indexHomeModel.data.classicList.get(0).title != null) {
                                        titleOne = indexHomeModel.data.classicList.get(0).title;
                                    } else {
                                        titleOne = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(1).title != null) {
                                        titleTwo = indexHomeModel.data.classicList.get(1).title;
                                    } else {
                                        titleTwo = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(2).title != null) {
                                        titleThree = indexHomeModel.data.classicList.get(2).title;
                                    } else {
                                        titleThree = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(3).title != null) {
                                        titleFour = indexHomeModel.data.classicList.get(3).title;
                                    } else {
                                        titleFour = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(4).title != null) {
                                        titleFive = indexHomeModel.data.classicList.get(4).title;
                                    } else {
                                        titleFive = "";
                                    }


                                    if (indexHomeModel.data.classicList.get(0).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(0).img).into(imSelectionOne);
                                    } else {
                                        imSelectionOne.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(1).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(1).img).into(imSelectionTwo);
                                    } else {
                                        imSelectionTwo.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(2).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(2).img).into(imSelectionThree);
                                    } else {
                                        imSelectionThree.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(3).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(3).img).into(imSelectionFour);
                                    } else {
                                        imSelectionFour.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(4).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(4).img).into(imSelectionFive);
                                    } else {
                                        imSelectionFive.setImageDrawable(null);
                                    }
                                } else if (indexHomeModel.data.classicList.size() == 6) {
                                    imSelectionOne.setVisibility(View.VISIBLE);
                                    imSelectionTwo.setVisibility(View.VISIBLE);
                                    imSelectionThree.setVisibility(View.VISIBLE);
                                    imSelectionFour.setVisibility(View.VISIBLE);
                                    imSelectionFive.setVisibility(View.VISIBLE);
                                    imSelectionSix.setVisibility(View.VISIBLE);

                                    productIdOne = indexHomeModel.data.classicList.get(0).id;
                                    productIdTwo = indexHomeModel.data.classicList.get(1).id;
                                    productIdThree = indexHomeModel.data.classicList.get(2).id;
                                    productIdFour = indexHomeModel.data.classicList.get(3).id;
                                    productIdFive = indexHomeModel.data.classicList.get(4).id;
                                    productIdSix = indexHomeModel.data.classicList.get(5).id;

                                    if (indexHomeModel.data.classicList.get(0).title != null) {
                                        titleOne = indexHomeModel.data.classicList.get(0).title;
                                    } else {
                                        titleOne = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(1).title != null) {
                                        titleTwo = indexHomeModel.data.classicList.get(1).title;
                                    } else {
                                        titleTwo = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(2).title != null) {
                                        titleThree = indexHomeModel.data.classicList.get(2).title;
                                    } else {
                                        titleThree = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(3).title != null) {
                                        titleFour = indexHomeModel.data.classicList.get(3).title;
                                    } else {
                                        titleFour = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(4).title != null) {
                                        titleFive = indexHomeModel.data.classicList.get(4).title;
                                    } else {
                                        titleFive = "";
                                    }
                                    if (indexHomeModel.data.classicList.get(5).title != null) {
                                        titleSix = indexHomeModel.data.classicList.get(5).title;
                                    } else {
                                        titleSix = "";
                                    }

                                    if (indexHomeModel.data.classicList.get(0).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(0).img).into(imSelectionOne);
                                    } else {
                                        imSelectionOne.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(1).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(1).img).into(imSelectionTwo);
                                    } else {
                                        imSelectionTwo.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(2).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(2).img).into(imSelectionThree);
                                    } else {
                                        imSelectionThree.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(3).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(3).img).into(imSelectionFour);
                                    } else {
                                        imSelectionFour.setImageDrawable(null);
                                    }
                                    if (indexHomeModel.data.classicList.get(4).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(4).img).into(imSelectionFive);
                                    } else {
                                        imSelectionFive.setImageDrawable(null);
                                    }

                                    if (indexHomeModel.data.classicList.get(5).img != null) {
                                        Glide.with(mActivity).load(indexHomeModel.data.classicList.get(5).img).into(imSelectionSix);
                                    } else {
                                        imSelectionSix.setImageDrawable(null);
                                    }
                                }



                            } else {
                                ll_classify.setVisibility(View.GONE);
                            }
                            if (indexHomeModel.data.prodPage.hasNextPage) {
                                mAdapterHomeRecommendNew.loadMoreComplete();
                            } else {
                                mAdapterHomeRecommendNew.loadMoreEnd();
                            }
                            //精选分类

                            setTitle(indexHomeModel);


                            mPtr.setVisibility(View.VISIBLE);


                        } else {
                            if (indexHomeModel.code == AppConstant.ANOTHER_PLACE_LOGIN) {
                                TwoDeviceHelper.logoutAndToHome(getActivity());
                            } else {
                                AppHelper.showMsg(mActivity, indexHomeModel.message);
                            }
                        }


                    }
                });
    }

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
                        Log.i("ccca", "onError: " + "网络错误");
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
                                    Log.i("ddda", "onItemClick: " + isSelected);
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

    private void initBanner() {
        mViewBanner.removeAllSliders();
        if (mListBanner.size() > 0) {
            for (int i = 0; i < mListBanner.size(); i++) {

                //图片轮播
                Log.i("wwwwwa", "initBanner: " + mListBanner.get(i).bannerDetailUrl);
                DefaultSliderView defaultSliderView = new DefaultSliderView(getContext());
                defaultSliderView.image(mListBanner.get(i).bannerUrl);
                defaultSliderView.setScaleType(BaseSliderView.ScaleType.Fit);

                defaultSliderView.setOnSliderClickListener(this);
                defaultSliderView.bundle(new Bundle());
                defaultSliderView.getBundle().putString("banner_url", mListBanner.get(i).bannerDetailUrl);
                mViewBanner.addSlider(defaultSliderView);

            }
        }

        mViewBanner.setPresetTransformer(SliderLayout.Transformer.Default);
        mViewBanner.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mViewBanner.setCustomAnimation(new DescriptionAnimation());
        mViewBanner.startAutoCycle(10000, 8000, true);
    }


    private void setTitle(IndexHomeModel indexHomeModel) {
        killTitle.setText(indexHomeModel.data.killTitle);

        killDesc.setText(indexHomeModel.data.killDesc);
        teamTitle.setText(indexHomeModel.data.teamTitle);
        teamDesc.setText(indexHomeModel.data.teamDesc);
        recommendTitle.setText(indexHomeModel.data.recommendTitle);
        recommendDesc.setText(indexHomeModel.data.recommendDesc);
        tvSelectionTitle.setText(indexHomeModel.data.classicTitle);
        tvSelectionContent.setText(indexHomeModel.data.classicDesc);


    }


    //更新消息数据
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
            mViewMessageNum.setVisibility(View.VISIBLE);
            mViewMessageNum.setText("  " + mModelMyOrderNum.getData().getNotice() + "  ");
        } else {
            mViewMessageNum.setVisibility(View.GONE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mPtr.autoRefresh();

     /*   pageNum = 1;
        requestOrderNum();
        indexHome("", pageSize, version, clientType);*/


        //


        String userHomeRefresh = UserInfoHelper.getUserHomeRefresh(getContext());

        if (StringHelper.notEmptyAndNull(userHomeRefresh)) {
            if (token != null && StringHelper.notEmptyAndNull(token)) {
                requestOrderNum();
            }
        } else {
            //需要刷新数据
            pageNum = 1;

            indexHome(version, clientType);
            UserInfoHelper.saveUserHomeRefresh(getContext(), "home_has_refresh");
            if (token != null && StringHelper.notEmptyAndNull(token)) {
                requestOrderNum();
            }

        }


    }

    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
