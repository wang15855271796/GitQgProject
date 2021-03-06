package com.puyue.www.qiaoge.activity.home;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CartActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.cart.ChooseSpecAdapter;
import com.puyue.www.qiaoge.adapter.cart.ImageViewAdapter;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.adapter.home.SearchResultAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsRecommendAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.ClickCollectionAPI;
import com.puyue.www.qiaoge.api.home.GetAllCommentListByPageAPI;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.api.mine.GetShareInfoAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.ChooseDialog;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.CollapsingToolbarLayoutStateHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.ChoiceSpecModel;
import com.puyue.www.qiaoge.model.home.ClickCollectionModel;
import com.puyue.www.qiaoge.model.home.GetAllCommentListByPageModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.HasCollectModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;
import com.puyue.www.qiaoge.model.mine.GetShareInfoModle;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.view.FlowLayout;
import com.puyue.www.qiaoge.view.StarBarView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/19.
 */

public class CommonGoodsDetailActivity extends BaseSwipeActivity {
    private ImageView mIvBack;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private String productName;
    private ImageView buyImg;
    private TextView mTvSpec;
    private LinearLayout mLlCustomer;
    private TextView mTvCollection;
    private ImageView mIvCollection;
    private LinearLayout mLlCar;
    private ImageView mIvCar;
    private TextView mTvCarNum;
    private TextView mTvAmount;
    private TextView mTvFee;
    private TextView mTvAddCar;
    private RelativeLayout linearLayoutCollection;
    private RelativeLayout linearLayoutShare;
    private List<String> images = new ArrayList<>();
    private int productId;
    private int pageNum = 1;
    private int pageSize = 10;
    private byte businessType = 1;
    private boolean isCollection = false;
    private List<ChoiceSpecModel> account = new ArrayList<>();
    private String cell;
    private String type;
    private CollapsingToolbarLayoutStateHelper state;
    //用户评论
    private TextView userEvaluationNum;
    private TextView goodsEvaluationNumber;
    private TextView goodsEvaluationTime;
    private TextView goodsEvaluationContent;
    private TextView goodsEvaluationReply;
    private StarBarView sbv_star_bar;
    private TextView tv_status;
    //推荐
    private RecyclerView recyclerViewRecommend;
    private GoodsRecommendAdapter adapterRecommend;
    private List<GetProductListModel.DataBean.ListBean> listRecommend = new ArrayList<>();
    private List<GoodsDetailModel> mListDetailImage = new ArrayList<>();
    private LinearLayout linearLayoutEvaluation;// 评价布局
    private LinearLayout linearLayoutEvaluationNoData;
    private String mShareTitle;
    private String mShareDesc;
    private String mShareIcon;
    private String mShareUrl;
    private int typeIntent;
    private LinearLayout linearLayoutOnclick;
    private List<GetRegisterShopModel.DataBean> list = new ArrayList<>();
    int isSelected;
    int shopTypeId;
    boolean isChecked = false;
    RegisterShopAdapterTwo mRegisterAdapter;
    ChooseDialog chooseDialog;
    @BindView(R.id.tv_sale)
    TextView tv_sale;
    @BindView(R.id.fl_container)
    FlowLayout fl_container;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.recyclerViewImage)
    RecyclerView recyclerViewImage;
    @BindView(R.id.iv_flag)
    ImageView iv_flag;
    private AlertDialog mTypedialog;
    public List<GetProductDetailModel.DataBean.ProdSpecsBean> prodSpecs;
    private List<String> detailPic;
    private int productMainId;
    SearchResultsModel searchResultsModel;
    //搜索集合
    private List<SearchResultsModel.DataBean.SearchProdBean.ListBean> searchList = new ArrayList<>();
    //图片详情集合
    private List<String> detailList = new ArrayList<>();

    private boolean isFirst = true;
    private int productId1;
    private ChooseSpecAdapter chooseSpecAdapter;
    private ImageViewAdapter imageViewAdapter;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            productId = bundle.getInt(AppConstant.ACTIVEID);
            if (!TextUtils.isEmpty(bundle.getString("equipment"))) {
                businessType = 7;
            }
            Log.d("sswdqqqqqqqqddddddd,,,",productId+"");
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
        setContentView(R.layout.activity_common_details);
    }

    @Override
    public void findViewById() {
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
        mBanner = FVHelper.fv(this, R.id.banner_activity_common);
        mTvTitle = FVHelper.fv(this, R.id.tv_activity_common_title);
        mTvPrice = FVHelper.fv(this, R.id.tv_activity_common_price);
        mTvSpec = FVHelper.fv(this, R.id.tv_activity_common_spec);
        mLlCustomer = FVHelper.fv(this, R.id.ll_include_common_customer);
        mTvCollection = FVHelper.fv(this, R.id.tv_include_common_collection);
        mIvCollection = FVHelper.fv(this, R.id.iv_include_common_collection);
        mLlCar = FVHelper.fv(this, R.id.ll_include_common_car);
        mIvCar = FVHelper.fv(this, R.id.iv_include_common_car);
        mTvCarNum = FVHelper.fv(this, R.id.tv_include_common_car_number);
        mTvAmount = FVHelper.fv(this, R.id.tv_include_common_amount);
        mTvFee = FVHelper.fv(this, R.id.tv_include_common_fee);
        mTvAddCar = FVHelper.fv(this, R.id.tv_add_car);
        linearLayoutCollection = FVHelper.fv(this, R.id.linearLayout_collection);
        linearLayoutShare = FVHelper.fv(this, R.id.linearLayout_share);
        userEvaluationNum = (TextView) findViewById(R.id.userEvaluationNum);
        goodsEvaluationNumber = (TextView) findViewById(R.id.goodsEvaluationNumber);
        goodsEvaluationTime = (TextView) findViewById(R.id.goodsEvaluationTime);
        goodsEvaluationContent = (TextView) findViewById(R.id.goodsEvaluationContent);
        goodsEvaluationReply = (TextView) findViewById(R.id.goodsEvaluationReply);
        recyclerViewRecommend = (RecyclerView) findViewById(R.id.recyclerViewRecommend);
        linearLayoutEvaluation = (LinearLayout) findViewById(R.id.linearLayoutEvaluation);
        linearLayoutEvaluationNoData = (LinearLayout) findViewById(R.id.linearLayoutEvaluationNoData);
        linearLayoutOnclick = (LinearLayout) findViewById(R.id.linearLayoutOnclick);
        sbv_star_bar = findViewById(R.id.sbv_star_bar);
        tv_status = findViewById(R.id.tv_status);

    }

    @Override
    public void setViewData() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        typeIntent = getIntent().getIntExtra("type", 1);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //获取数据
        getProductDetail(productId);
        getCustomerPhone();
        getAllCommentList(pageNum, pageSize, productId, businessType);

        adapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, searchList);
        LinearLayoutManager linearLayoutManagerCoupons = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommend.setLayoutManager(linearLayoutManagerCoupons);
        recyclerViewRecommend.setAdapter(adapterRecommend);

        mTypedialog = new AlertDialog.Builder(mActivity, R.style.DialogStyle).create();
        mTypedialog.setCancelable(false);

        imageViewAdapter = new ImageViewAdapter(mContext,R.layout.item_imageview,detailList);
        recyclerViewImage.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewImage.setAdapter(imageViewAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            hasCollectState(productId, businessType);
            getProductDetail(productId);
            getCartNum();
        } else {
            mIvCollection.setImageResource(R.mipmap.icon_collection_null);
        }
    }


    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        linearLayoutCollection.setOnClickListener(noDoubleClickListener);
        mTvAddCar.setOnClickListener(noDoubleClickListener);
        mLlCar.setOnClickListener(noDoubleClickListener);
        mLlCustomer.setOnClickListener(noDoubleClickListener);
        linearLayoutOnclick.setOnClickListener(noDoubleClickListener);
        linearLayoutShare.setOnClickListener(noDoubleClickListener);
        state = CollapsingToolbarLayoutStateHelper.EXPANDED;
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == linearLayoutCollection) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
                        //这个用户是零售用户
//                        if ("批发".equals(type)) {
                            Log.d("woshidashuju....",cell);
                            if (StringHelper.notEmptyAndNull(cell)) {
                                AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode())) {
                                            AppHelper.hideAuthorizationDialog();
                                            showDialog();
                                        } else {
                                            AppHelper.showMsg(mContext, "请输入完整授权码");
                                        }
                                    }
                                });
                            }

                    } else {
                        if (isCollection) {
                            //取消收藏
                            clickCollection(productId1, businessType, (byte)2);
                            AppHelper.showMsg(mContext,"取消收藏");
                        } else {
                            clickCollection(productId1, businessType, (byte) 1);
                            AppHelper.showMsg(mContext,"收藏成功");
                        }
                    }
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mTvAddCar) {
                if(StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if(chooseDialog==null) {
                        chooseDialog = new ChooseDialog(mContext,productId);
                    }
                }else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }


                chooseDialog.show();

            } else if (view == mLlCar) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if(UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
                        if (StringHelper.notEmptyAndNull(cell)) {
                            AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
                                        AppHelper.hideAuthorizationDialog();
//                                        if (UserInfoHelper.getIsregister(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getIsregister(mActivity))) {
                                            showSelectType(AppHelper.getAuthorizationCode());
//                                        }
                                    } else {
                                        AppHelper.showMsg(mContext, "请输入完整授权码");
                                    }
                                }
                            });
                        }
                    }
                    else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
                        //这个用户是批发用户
                        // startActivity(new Intent(mContext,HomeActivity.class));
                        startActivityForResult(new Intent(mContext, CartActivity.class), 21);
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
            } else if (view == linearLayoutOnclick) {
                Intent intent = new Intent(CommonGoodsDetailActivity.this, EvaluationActivity.class);
                intent.putExtra("productId", productId);
                intent.putExtra("businessType", businessType);
                startActivity(intent);

            } else if (view == linearLayoutShare) {
                requestGoodsList();


            }
        }
    };

    /**
     * 获取详情
     */
    private void getProductDetail(final int productId) {
        GetProductDetailAPI.requestData(mContext, productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetProductDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetProductDetailModel model) {
                        if (model.isSuccess()) {
                            detailList.addAll(model.getData().getDetailPic());
                            Log.d("swffsdfdssfsfdsfs...",detailList.size()+"");
                            imageViewAdapter.notifyDataSetChanged();
//                            getDetailImage(model);
                            productId1 = model.getData().getProductId();
                            productName = model.getData().getProductName();
                            mTvTitle.setText(productName);
                            mTvPrice.setText(model.getData().getMinMaxPrice());
                            if(model.getData().getTypeUrl()==null||model.getData().getTypeUrl().equals("")) {
                                iv_flag.setVisibility(View.GONE);
                            }else {
                                iv_flag.setVisibility(View.VISIBLE);
                                Glide.with(mContext).load(model.getData().getTypeUrl()).into(iv_flag);
                            }

                            mTvSpec.setText("规格:"+model.getData().getSpec());
                            tv_sale.setText(model.getData().getSalesVolume());
                            prodSpecs = model.getData().getProdSpecs();
                            tv_desc.setText("产品说明："+model.getData().getIntroduction());
                            //                                        if(chooseDialog==null){
//                                            productMainId = model.getData().getProductMainId();
//                                            chooseDialog = new ChooseDialog(mContext, productMainId);
//
//                                        }
//                                        chooseDialog.show();
                            chooseSpecAdapter = new ChooseSpecAdapter(mContext,prodSpecs, new ChooseSpecAdapter.Onclick() {
                                @Override
                                public void addDialog(int position) {
                                    if(StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                                        if(UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
                                            if (StringHelper.notEmptyAndNull(cell)) {
                                                AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
                                                            AppHelper.hideAuthorizationDialog();
//                                                        if (UserInfoHelper.getIsregister(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getIsregister(mActivity))) {
                                                            showSelectType(AppHelper.getAuthorizationCode());
//                                                        }
                                                        } else {
                                                            AppHelper.showMsg(mContext, "请输入完整授权码");
                                                        }
                                                    }
                                                });
                                            }
                                        }else {
                                            chooseSpecAdapter.selectPosition(position);
                                            if(chooseDialog==null){
                                                productMainId = model.getData().getProductMainId();
                                                chooseDialog = new ChooseDialog(mContext, productMainId);
                                            }
                                            chooseDialog.show();
                                        }
                                    }else {
                                        AppHelper.showMsg(mContext, "请先登录");
                                        startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                                    }

                                }
                            });

                            fl_container.setAdapter(chooseSpecAdapter);


                            //填充banner
                            images.clear();
                            mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                            mBanner.setImageLoader(new GlideImageLoader());
                            images.addAll(model.getData().getTopPic());
                            mBanner.setImages(images);
                            mBanner.setBannerAnimation(Transformer.DepthPage);
                            mBanner.isAutoPlay(true);
                            mBanner.setDelayTime(3000);
                            mBanner.setIndicatorGravity(BannerConfig.RIGHT);
                            mBanner.start();
                            //banner设置点击监听
                            mBanner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    AppHelper.showPhotoDetailDialog(mContext, images, position);
                                }
                            });

                            getProductList();

                            //填充详情
                            mListDetailImage.clear();
                        } else {
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

    /***
     * 获取底部详情图片
     * @param model
     */
    private void getDetailImage(GetProductDetailModel model) {
        detailPic = model.getData().getDetailPic();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 15) {
            account.clear();
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private float star;

    /**
     * 获取评价
     */
    private void getAllCommentList(final int pageNum, int pageSize, int businessId, byte businessType) {
        GetAllCommentListByPageAPI.requestData(mContext, pageNum, pageSize, businessId, businessType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAllCommentListByPageModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetAllCommentListByPageModel model) {
                        if (model.success) {

                            if (model.data.list != null && model.data.list.size() > 0) {

                                linearLayoutEvaluationNoData.setVisibility(View.GONE);
                                linearLayoutEvaluation.setVisibility(View.VISIBLE);
                                goodsEvaluationContent.setText(model.data.list.get(0).commentContent + "");
                                if (model.data.list.get(0).replayContent != null || !TextUtils.isEmpty(model.data.list.get(0).replayContent)) {
                                    goodsEvaluationReply.setText("翘歌客服: " + model.data.list.get(0).replayContent + "");
                                    goodsEvaluationReply.setVisibility(View.VISIBLE);
                                } else {
                                    goodsEvaluationReply.setVisibility(View.GONE);
                                }

                                goodsEvaluationTime.setText(model.data.list.get(0).commentDate + "");
                                goodsEvaluationNumber.setText(model.data.list.get(0).customerPhone);
                                userEvaluationNum.setText("用户评论(" + model.data.total + ")");
                                if (model.data.list.get(0).level != null && StringHelper.notEmptyAndNull(model.data.list.get(0).level)) {
                                    sbv_star_bar.setVisibility(View.VISIBLE);
                                    if (model.data.list.get(0).level.equals("5")) {
                                        star = 5.0f;
                                    } else if (model.data.list.get(0).level.equals("4")) {
                                        star = 4.0f;

                                    } else if (model.data.list.get(0).level.equals("3")) {
                                        star = 3.0f;

                                    } else if (model.data.list.get(0).level.equals("2")) {
                                        star = 2.0f;

                                    } else if (model.data.list.get(0).level.equals("1")) {
                                        star = 1.0f;
                                    }

                                    setStarName(tv_status, star);
                                    sbv_star_bar.setStarRating(star);

                                } else {
                                    sbv_star_bar.setVisibility(View.GONE);
                                }


                                sbv_star_bar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        sbv_star_bar.setStarSolid(star);

                                    }
                                });


                            } else {
                                linearLayoutEvaluationNoData.setVisibility(View.VISIBLE);
                                linearLayoutEvaluation.setVisibility(View.GONE);
                            }


                        } else {
                            ToastUtil.showSuccessMsg(mContext, model.message);
                        }
                    }
                });

    }

    /**
     * 设置星星文字
     */
    private void setStarName(TextView tv_content, float star_num) {
        if (star_num == 5.0f) {
            tv_content.setText("很满意");

        } else if (star_num == 4.0f) {
            tv_content.setText("满意");

        } else if (star_num == 3.0f) {
            tv_content.setText("一般");

        } else if (star_num == 2.0f) {
            tv_content.setText("不满意");

        } else if (star_num == 1.0f) {
            tv_content.setText("非常差");

        }

    }

    /**
     * 推荐
     **/
    private void getProductList() {
//        PublicRequestHelper.getProductList(mContext, 1, 12, "recommendNew", productName, null, null, null, null, new OnHttpCallBack<GetProductListModel>() {
//            @Override
//            public void onSuccessful(GetProductListModel getProductListModel) {
//                if (getProductListModel.success) {
//                    listRecommend.clear();
//                    if (getProductListModel.data.list != null) {
//                        adapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, listRecommend);
//                        recyclerViewRecommend.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false));
//                        recyclerViewRecommend.setAdapter(adapterRecommend);
//                        listRecommend.addAll(getProductListModel.data.list);
//                    } else {
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, getProductListModel.message);
//                }
//            }
//
//            @Override
//            public void onFaild(String errorMsg) {
//
//            }
//        });

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
                            ToastUtil.showSuccessMsg(mContext, recommendModel.getMessage());
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
                    } else {
                        mIvCollection.setImageResource(R.mipmap.icon_collection_null);
                    }
                } else {
                    ToastUtil.showSuccessMsg(mContext, hasCollectModel.message);
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

                    }

                    @Override
                    public void onNext(ClickCollectionModel clickCollectionModel) {
                        Log.d("swwwwwweeewwww...",clickCollectionModel.data+"");
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
                            Log.d("afddfdfdfefffff....",clickCollectionModel.message);
                        }
                    }
                });
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
        controlPosition[0] = (start_location[0] + end_location[0]) / 2;
        controlPosition[1] = ((start_location[1] + 100) / 2);

        int total = 0;
        for (int i = 0; i < account.size(); i++) {
            if (account.get(i).totalNum != 0) {
                total += account.get(i).totalNum;
            }
        }


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
        // valueAnimator.setRepeatMode();
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
                .setDuration(500);
        anim.setStartDelay(1000);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
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
        PublicRequestHelper.getCartNum(mContext, new OnHttpCallBack<GetCartNumModel>() {
            @Override
            public void onSuccessful(GetCartNumModel getCartNumModel) {
                if (getCartNumModel.isSuccess()) {
                    if (Integer.valueOf(getCartNumModel.getData().getNum()) > 0) {
                        mIvCar.setImageResource(R.mipmap.ic_buy_car_fill);
                        mTvCarNum.setVisibility(View.VISIBLE);
                        mTvCarNum.setText(getCartNumModel.getData().getNum());
                        mTvAmount.setText("￥" + getCartNumModel.getData().getTotalPrice() + "元");
                        mTvAmount.setTextColor(Color.parseColor("#000000"));
                        mTvFee.setVisibility(View.VISIBLE);
                        mTvFee.setText("满" + getCartNumModel.getData().getSendAmount() + "元免配送费");
                    } else {
                        mIvCar.setImageResource(R.mipmap.ic_buy_car);
                        mTvCarNum.setVisibility(View.GONE);
                        mTvAmount.setText("未选购商品");
                        mTvAmount.setTextColor(Color.parseColor("#A7A7A7"));
                        mTvFee.setVisibility(View.GONE);
                    }
                } else {
                    ToastUtil.showSuccessMsg(mContext, getCartNumModel.getMessage());
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
        PublicRequestHelper.getCustomerPhone(mContext, new OnHttpCallBack<GetCustomerPhoneModel>() {
            @Override
            public void onSuccessful(GetCustomerPhoneModel getCustomerPhoneModel) {
                if (getCustomerPhoneModel.isSuccess()) {
                    cell = getCustomerPhoneModel.getData();
                } else {
                    ToastUtil.showSuccessMsg(mContext, getCustomerPhoneModel.getMessage());
                }
            }

            @Override
            public void onFaild(String errorMsg) {
            }
        });
    }

    /**
     * 提交验证码
     */
    private void updateUserInvitation(String code, int typeId) {
        UpdateUserInvitationAPI.requestData(mContext, code, typeId)
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
                            UserInfoHelper.saveUserType(mContext, AppConstant.USER_TYPE_WHOLESALE);
                            getProductDetail(productId);
                        } else {
                            ToastUtil.showSuccessMsg(mContext, updateUserInvitationModel.getMessage());
                        }
                    }
                });
    }

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
                umWeb.setThumb(new UMImage(CommonGoodsDetailActivity.this, mShareIcon));
                umWeb.setTitle(mShareTitle);

                new ShareAction(CommonGoodsDetailActivity.this)
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
                    umWeb.setThumb(new UMImage(CommonGoodsDetailActivity.this, mShareIcon));
                    umWeb.setTitle(mShareTitle);
                    new ShareAction(CommonGoodsDetailActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    Toast.makeText(CommonGoodsDetailActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
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
                    umWeb.setThumb(new UMImage(CommonGoodsDetailActivity.this, mShareIcon));
                    umWeb.setTitle(mShareTitle);
                    new ShareAction(CommonGoodsDetailActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    Toast.makeText(CommonGoodsDetailActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
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
        GetShareInfoAPI.requestGetShareInfoService(mContext, productId1, 1)
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
                        Log.d("swwddsdvvvvvv...",getShareInfoModle.getMessage());
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
                Toast.makeText(CommonGoodsDetailActivity.this, " 收藏成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CommonGoodsDetailActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };

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
                                        ToastUtil.showSuccessMsg(mActivity, "请选择店铺类型");
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
                            ToastUtil.showSuccessMsg(mActivity, getRegisterShopModel.getMessage());
                        }
                    }
                });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBuss(ReduceNumEvent event) {
        //刷新UI
        getCartNum();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
