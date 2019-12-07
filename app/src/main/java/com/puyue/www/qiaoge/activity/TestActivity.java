package com.puyue.www.qiaoge.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.EvaluationActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.SearchReasultAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchResultAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsDetailAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsRecommendAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.cart.AddMountChangeAPI;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.ClickCollectionAPI;
import com.puyue.www.qiaoge.api.home.GetAllCommentListByPageAPI;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
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
import com.puyue.www.qiaoge.helper.CollapsingToolbarLayoutStateHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.AddMountReduceModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.ChoiceSpecModel;
import com.puyue.www.qiaoge.model.home.ClickCollectionModel;
import com.puyue.www.qiaoge.model.home.GetAllCommentListByPageModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.HasCollectModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;
import com.puyue.www.qiaoge.model.mine.GetShareInfoModle;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
 * Created by ${王涛} on 2019/9/29
 */
public class TestActivity extends BaseSwipeActivity{
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.test);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }

//    private LinearLayout mIvBack;
//    private Banner mBanner;
//    private TextView mTvTitle;
//    private TextView mTvPrice;
//    private TextView mTvInven;
//    private TextView mTvVolume;
//    private String productName;
//    private ImageView buyImg;
//    private int mount;
//
//    private TextView mTvSpec;
//    private TextView mTvPlace;
//    private TextView mTvDesc;
//
//    private LinearLayout mLlChoice;
//
//    private boolean clicked = false;
//    private LinearLayout mLlCustomer;
//    // private LinearLayout mLlCollection;
//    private TextView mTvCollection;
//    private ImageView mIvCollection;
//    private LinearLayout mLlCar;
//    private ImageView mIvCar;
//    private TextView mTvCarNum;
//    private TextView mTvAmount;
//    private TextView mTvFee;
//    private TextView mTvAddCar;
//
//    private LinearLayout linearLayoutCollection;
//    private LinearLayout linearLayoutShare;
//
//    private List<String> images = new ArrayList<>();
//
//    private int productId;
//    private int pageNum = 1;
//    private int pageSize = 10;
//    private byte businessType = 1;
//    private byte productType = 1;
//    private boolean isCollection = false;
//    private int inventory = 0;
//    private int productCombinationPriceId;
//
//    private List<ChoiceSpecModel> account = new ArrayList<>();
//    private String totalMoney = "0";
//    private String cell;
//    private String type;
//
//    private AppBarLayout appBarLayout;
//    private CollapsingToolbarLayoutStateHelper state;
//    private Toolbar toolbar;
//    private TextView textViewTitle;
//    //用户评论
//    private TextView userEvaluationNum;
//    private TextView goodsEvaluationNumber;
//    private TextView goodsEvaluationTime;
//    private TextView goodsEvaluationContent;
//    private TextView goodsEvaluationReply;
//    //推荐
//    private RecyclerView recyclerViewRecommend;
//    private GoodsRecommendAdapter adapterRecommend;
//    private List<GetProductListModel.DataBean.ListBean> listRecommend = new ArrayList<>();
//    // 商品详情
//    private RecyclerView recyclerViewImage;
//    private GoodsDetailAdapter mAdapterImage;
//    private List<GoodsDetailModel> mListDetailImage = new ArrayList<>();
//
//    private List<GetProductDetailModel.DataBean.ProductCombinationPriceListBean> mProductList = new ArrayList<>();
//
//    private LinearLayout linearLayoutEvaluation;// 评价布局
//    private LinearLayout linearLayoutEvaluationNoData;
//    private ImageView ImageViewShare; //分享
//    private String mShareTitle;
//    private String mShareDesc;
//    private String mShareIcon;
//    private String mShareUrl;
//    private int typeIntent;
//    private LinearLayout linearLayoutOnclick;
//
//    private TextView textSpec;
//    private TextView mTvInventoryString;
//
//    class MyHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Bitmap bitmap = msg.getData().getParcelable("bitmap");
//            buyImg.setImageBitmap(bitmap);
//        }
//    }
//
//    @Override
//    public boolean handleExtra(Bundle savedInstanceState) {
//        if (getIntent() != null && getIntent().getExtras() != null) {
//            Bundle bundle = getIntent().getExtras();
//            productId = bundle.getInt(AppConstant.ACTIVEID);
//            if (!TextUtils.isEmpty(bundle.getString("equipment"))) {
//                businessType = 7;
//                productType = 7;
//
//            }
//        }
//        return false;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        handleExtra(savedInstanceState);
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void setContentView() {
//        settranslucentStatus();
//        setContentView(R.layout.activity_common_details);
//    }
//
//    @Override
//    public void findViewById() {
//        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
//        textViewTitle = FVHelper.fv(this, R.id.textViewTitle);
//        toolbar = FVHelper.fv(this, R.id.toolbar);
//        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
//        mBanner = FVHelper.fv(this, R.id.banner_activity_common);
//        mTvTitle = FVHelper.fv(this, R.id.tv_activity_common_title);
//        mTvPrice = FVHelper.fv(this, R.id.tv_activity_common_price);
//        mTvInven = FVHelper.fv(this, R.id.tv_activity_common_inven);
//        mTvVolume = FVHelper.fv(this, R.id.tv_activity_common_volume);
//        mTvSpec = FVHelper.fv(this, R.id.tv_activity_common_spec);
//        mTvPlace = FVHelper.fv(this, R.id.tv_activity_common_place);
//        mTvDesc = FVHelper.fv(this, R.id.tv_activity_common_desc);
//        mLlChoice = FVHelper.fv(this, R.id.ll_activity_common_choice);
//        mLlCustomer = FVHelper.fv(this, R.id.ll_include_common_customer);
//        mTvCollection = FVHelper.fv(this, R.id.tv_include_common_collection);
//        mIvCollection = FVHelper.fv(this, R.id.iv_include_common_collection);
//        mLlCar = FVHelper.fv(this, R.id.ll_include_common_car);
//        mIvCar = FVHelper.fv(this, R.id.iv_include_common_car);
//        mTvCarNum = FVHelper.fv(this, R.id.tv_include_common_car_number);
//        mTvAmount = FVHelper.fv(this, R.id.tv_include_common_amount);
//        mTvFee = FVHelper.fv(this, R.id.tv_include_common_fee);
//        mTvAddCar = FVHelper.fv(this, R.id.tv_include_common_add_car);
//        linearLayoutCollection = FVHelper.fv(this, R.id.linearLayout_collection);
//        linearLayoutShare = FVHelper.fv(this, R.id.linearLayout_share);
//        // mTvInventoryString = FVHelper.fv(this, R.id.inventoryString);
//
//        userEvaluationNum = (TextView) findViewById(R.id.userEvaluationNum);
//        goodsEvaluationNumber = (TextView) findViewById(R.id.goodsEvaluationNumber);
//        goodsEvaluationTime = (TextView) findViewById(R.id.goodsEvaluationTime);
//        goodsEvaluationContent = (TextView) findViewById(R.id.goodsEvaluationContent);
//        goodsEvaluationReply = (TextView) findViewById(R.id.goodsEvaluationReply);
//
//        recyclerViewRecommend = (RecyclerView) findViewById(R.id.recyclerViewRecommend);
//        recyclerViewImage = (RecyclerView) findViewById(R.id.recyclerViewImage);
//        linearLayoutEvaluation = (LinearLayout) findViewById(R.id.linearLayoutEvaluation);
//        linearLayoutEvaluationNoData = (LinearLayout) findViewById(R.id.linearLayoutEvaluationNoData);
//        ImageViewShare = (ImageView) findViewById(R.id.ImageViewShare);
//        linearLayoutOnclick = (LinearLayout) findViewById(R.id.linearLayoutOnclick);
//        textSpec = (TextView) findViewById(R.id.textSpec);
//
//
//    }
//
//    @Override
//    public void setViewData() {
//        typeIntent = getIntent().getIntExtra("type", 1);
//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        //获取数据
//        getProductDetail(productId, productType);
//        getCustomerPhone();
//        getAllCommentList(pageNum, pageSize, productId, businessType);
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//            hasCollectState(productId, businessType);
//            getProductDetail(productId, productType);
//            getCartNum();
//        } else {
//            mIvCollection.setImageResource(R.mipmap.icon_collection_null);
//        }
//    }
//
//
//    @Override
//    public void setClickEvent() {
//        mIvBack.setOnClickListener(noDoubleClickListener);
//        linearLayoutCollection.setOnClickListener(noDoubleClickListener);
//        mTvAddCar.setOnClickListener(noDoubleClickListener);
//        mLlCar.setOnClickListener(noDoubleClickListener);
//        mLlCustomer.setOnClickListener(noDoubleClickListener);
//        linearLayoutOnclick.setOnClickListener(noDoubleClickListener);
//        linearLayoutShare.setOnClickListener(noDoubleClickListener);
//        state = CollapsingToolbarLayoutStateHelper.EXPANDED;
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.app_color_white), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
//                toolbar.getBackground().setAlpha((int) (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange() * 255));
//
//                // 展开折叠改变状态
//                if (state != CollapsingToolbarLayoutStateHelper.EXPANDED) { //展开的状态
//                    state = CollapsingToolbarLayoutStateHelper.EXPANDED;//修改状态标记为展开
//                    textViewTitle.setVisibility(View.GONE);
//                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
//                    if (state != CollapsingToolbarLayoutStateHelper.INTERNEDIATE) {
//                        textViewTitle.setVisibility(View.VISIBLE);
//                        state = CollapsingToolbarLayoutStateHelper.INTERNEDIATE;
//                    }
//
//                }
//            }
//        });
//
//    }
//
//    public int changeAlpha(int color, float fraction) {
//        int red = Color.red(color);
//        int green = Color.green(color);
//        int blue = Color.blue(color);
//        int alpha = (int) (Color.alpha(color) * fraction);
//        return Color.argb(alpha, red, green, blue);
//    }
//
//    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
//        @Override
//        public void onNoDoubleClick(View view) {
//            if (view == mIvBack) {
//                finish();
//            } else if (view == linearLayoutCollection) {
//                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//                    if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
//                        //这个用户是零售用户
//                        if ("批发".equals(type)) {
//                            if (StringHelper.notEmptyAndNull(cell)) {
//                                AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
//                                            AppHelper.hideAuthorizationDialog();
//                                            updateUserInvitation(AppHelper.getAuthorizationCode());
//                                        } else {
//                                            AppHelper.showMsg(mContext, "请输入完整授权码");
//                                        }
//                                    }
//                                });
//                            }
//                        } else {
//                            if (isCollection) {
//                                //取消收藏
//                                clickCollection(productId, businessType, (byte) 0);
//                            } else {
//                                clickCollection(productId, businessType, (byte) 1);
//                            }
//                        }
//                    } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
//                        //这个用户是批发用户
//                        if (isCollection) {
//                            //取消收藏
//                            clickCollection(productId, businessType, (byte) 0);
//                        } else {
//                            clickCollection(productId, businessType, (byte) 1);
//                        }
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, "请先登录");
//                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
//                }
//            } else if (view == mTvAddCar) {
//                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//                    if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
//                        //这个用户是零售用户
//                        if ("批发".equals(type)) {
//                            if (StringHelper.notEmptyAndNull(cell)) {
//                                AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
//                                            AppHelper.hideAuthorizationDialog();
//                                            updateUserInvitation(AppHelper.getAuthorizationCode());
//                                        } else {
//                                            AppHelper.showMsg(mActivity, "请输入完整授权码");
//                                        }
//                                    }
//                                });
//                            }
//                        } else {
//                            if (Double.parseDouble(totalMoney) == 0) {
//                                //未选择数量
//                                AppHelper.showMsg(mContext, "请选择数量");
//                            } else {
//                                addCart();
//                            }
//                        }
//                    } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
//                        //这个用户是批发用户
//                        if (Double.parseDouble(totalMoney) == 0) {
//                            //未选择数量
//                            AppHelper.showMsg(mContext, "请选择数量");
//                        } else {
//                            addCart();
//                        }
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, "请先登录");
//                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
//                }
//            } else if (view == mLlCar) {
//                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//                    if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
//                        //这个用户是零售用户
//                        if ("批发".equals(type)) {
//                            if (StringHelper.notEmptyAndNull(cell)) {
//                                AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
//                                            AppHelper.hideAuthorizationDialog();
//                                            updateUserInvitation(AppHelper.getAuthorizationCode());
//                                        } else {
//                                            AppHelper.showMsg(CommonGoodsDetailActivity.this, "请输入完整授权码");
//                                        }
//                                    }
//                                });
//                            }
//                        } else {
//                            // startActivity(new Intent(mContext,HomeActivity.class));
//                            startActivityForResult(new Intent(mContext, CartActivity.class), 21);
//                        }
//                    } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
//                        //这个用户是批发用户
//                        // startActivity(new Intent(mContext,HomeActivity.class));
//                        startActivityForResult(new Intent(mContext, CartActivity.class), 21);
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, "请先登录");
//                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
//                }
//            } else if (view == mLlCustomer) {
//                if (StringHelper.notEmptyAndNull(cell)) {
//                    AppHelper.showPhoneDialog(mContext, cell);
//                } else {
//                    AppHelper.showMsg(mContext, "获取客服号码失败");
//                }
//            } else if (view == linearLayoutOnclick) {
//                Intent intent = new Intent(CommonGoodsDetailActivity.this, EvaluationActivity.class);
//                intent.putExtra("productId", productId);
//                intent.putExtra("businessType", businessType);
//                startActivity(intent);
//
//            } else if (view == linearLayoutShare) {
//                requestGoodsList();
//
//
//            }
//        }
//    };
//
//    /**
//     * 获取详情
//     */
//    private void getProductDetail(final int productId, int productType) {
//        GetProductDetailAPI.requestData(mContext, productId, productType)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GetProductDetailModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        Toast.makeText(CommonGoodsDetailActivity.this, "错误", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(GetProductDetailModel model) {
//                        logoutAndToHome(mContext, model.code);
//                        if (model.success) {
//
//                            productName = model.data.productName;
//                            mTvTitle.setText(model.data.productName);
//                            mTvPrice.setText("￥" + model.data.unitPrice);
//
//                            textSpec.setText("/" + model.data.unitName);
//                            inventory = model.data.inventory;
//                            if (inventory <= 0) {
//                                mTvAddCar.setEnabled(false);
//                                mTvAddCar.setBackgroundResource(R.drawable.app_car);
//                            } else {
//                                mTvAddCar.setEnabled(true);
//                                mTvAddCar.setBackgroundResource(R.drawable.selector_once_buy);
//                            }
//
//                            mTvInven.setText(model.data.inventoryString);
//                            mTvVolume.setText(model.data.monthSalesVolumeString);
//                            mTvSpec.setText(model.data.spec);
//                            mTvPlace.setText(model.data.originPlace);
//                            mTvDesc.setText(model.data.intrduction);
//                            //  mTvInventoryString.setText(model.data.inventoryString);
//                            //填充banner
//                            images.clear();
//                            if (model.data.carouselList != null) {
//                                //设置banner样式
//                                mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
//                                //设置图片加载器
//                                mBanner.setImageLoader(new GlideImageLoader());
//                                //设置图片集合
//                                images.addAll(model.data.carouselList);
//                                mBanner.setImages(images);
//                                //设置banner动画效果
//                                mBanner.setBannerAnimation(Transformer.DepthPage);
//                                //设置自动轮播，默认为true
//                                mBanner.isAutoPlay(true);
//                                //设置轮播时间
//                                mBanner.setDelayTime(3000);
//                                //设置指示器位置（当banner模式中有指示器时）
//                                mBanner.setIndicatorGravity(BannerConfig.RIGHT);
//                                //banner设置方法全部调用完毕时最后调用
//                                mBanner.start();
//                            }
//
//                            //banner设置点击监听
//                            mBanner.setOnBannerListener(new OnBannerListener() {
//                                @Override
//                                public void OnBannerClick(int position) {
//                                    AppHelper.showPhotoDetailDialog(mContext, images, position);
//                                }
//                            });
//                            //动态添加view
//                            if (model.data.productCombinationPriceList != null) {
//                                mProductList = model.data.productCombinationPriceList;
//                                addViewForChoice(model.data.productCombinationPriceList);
//                            }
//
//                            for (int i = 0; i < mProductList.size(); i++) {
//                                productCombinationPriceId = mProductList.get(i).productCombinationPriceId;
//                            }
//
//                            getProductList();
//                            //填充详情
//                            mListDetailImage.clear();
//                            if (model.data.detailList != null) {
//
//                                for (int i = 0; i < model.data.detailList.size(); i++) {
//                                    GoodsDetailModel goodsDetailModel = new GoodsDetailModel(GoodsDetailModel.typeIv);
//                                    goodsDetailModel.content = model.data.detailList.get(i);
//                                    mListDetailImage.add(goodsDetailModel);
//                                }
//
//                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
//                                    @Override
//                                    public boolean canScrollVertically() {
//                                        return false;
//                                    }
//                                };
//                                mAdapterImage = new GoodsDetailAdapter(mListDetailImage);
//                                recyclerViewImage.setLayoutManager(linearLayoutManager);
//                                recyclerViewImage.setAdapter(mAdapterImage);
//                            }
//                            type = model.data.type;
//
//
//                        } else {
////                            AppHelper.showMsg(mContext, model.message);
//                        }
//
//                    }
//                });
//    }
//
//    private int amount;
//
//    /**
//     * 通过list的大小设置控件的多少,
//     */
//    private void addViewForChoice(List<GetProductDetailModel.DataBean.ProductCombinationPriceListBean> mList) {
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);       //LayoutInflater inflater1=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        mLlChoice.removeAllViews();
//
//        for (int i = 0; i < mList.size(); i++) {
//            View view = inflater.inflate(R.layout.item_choice_spec, null);
//            view.setLayoutParams(lp);
//
//
//            mLlChoice.addView(view);
//
//            setViewDataAndCount(view, mList.get(i), i);
//
//
//        }
//    }
//
//    String num;
//
//    /**
//     * 对动态添加的view设置监听和数据,
//     */
//    private void setViewDataAndCount(View view, final GetProductDetailModel.DataBean.ProductCombinationPriceListBean bean, final int i) {
//        TextView mTvPrice = FVHelper.fv(view, R.id.tv_item_group_price);
//        mTvPrice.setText(bean.showPrice);
//        final TextView mTvAmount1 = FVHelper.fv(view, R.id.tv_item_amount);
//        if (typeIntent == 1) {
//            TextView tvOriginalPrice = FVHelper.fv(view, R.id.tvOriginalPrice);
//            tvOriginalPrice.setText(bean.oldPrice);
//            tvOriginalPrice.setVisibility(View.VISIBLE);
//            tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
//
//        } else {
//            TextView tvOriginalPrice = FVHelper.fv(view, R.id.tvOriginalPrice);
//            tvOriginalPrice.setVisibility(View.GONE);
//        }
//        int productCombinationId;
//        final ChoiceSpecModel model = new ChoiceSpecModel();
//        model.productCombinationPriceId = bean.productCombinationPriceId;
//        model.totalNum = 0;
//        productCombinationId = model.productCombinationPriceId;
//        model.num = bean.num;
//        model.productUnitName = bean.productUnitName;
//        if (model.num == 0) {
//            model.num = 1;
//        }
//        account.add(model);
//
//      /*  //数量可编辑
//        mTvAmount1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//
//                        if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
//                            //这个用户是零售用户
//                            if ("批发".equals(type)) {
//                                if (StringHelper.notEmptyAndNull(cell)) {
//                                    AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
//                                                AppHelper.hideAuthorizationDialog();
//                                                updateUserInvitation(AppHelper.getAuthorizationCode());
//                                            } else {
//                                                AppHelper.showMsg(CommonGoodsDetailActivity.this, "请输入完整授权码");
//                                            }
//                                        }
//                                    });
//                                }
//                            }
//                        } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
//                            //这个用户是批发用户
//                            if (inventory != 0) {
//
//                                mTvAmount1.addTextChangedListener(new TextWatcher() {
//                                    @Override
//                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                                    }
//
//                                    @Override
//                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                                        num = mTvAmount1.getText().toString();
//                                        Log.i("cdcdcd", "onTextChanged: "+num);
//                                    }
//
//                                    @Override
//                                    public void afterTextChanged(Editable s) {
//
//
//                                    }
//                                });
//                                if (num == null) {
//
//                                } else {
//                                    amount = Integer.parseInt(num);
//                                }
//                                Log.i("ccccc", "onFocusChange: " + amount);
//                                account.get(i).totalNum = amount;
//
//                                Log.i("dddddd", "onFocusChange: " + account.get(i).totalNum);
//
//                                mTvAmount1.setText(amount + "");
//                                // totalMoney = totalMoney + Double.parseDouble(bean.price);
//                                BigDecimal totalMon = new BigDecimal(totalMoney);
//                                totalMon = totalMon.add(new BigDecimal(bean.price));
//                                totalMoney = totalMon.toString();
//
//
//                            }
//
//                        }
//                    } else {
//                        AppHelper.showMsg(mContext, "请先登录");
//                        startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
//                    }
//
//                }
//            }
//
//        });*/
//
//
//        view.findViewById(R.id.tv_item_sub).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//                    //减少
//                    if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
//                        //这个用户是零售用户
//                        if ("批发".equals(type)) {
//                            if (StringHelper.notEmptyAndNull(cell)) {
//                                AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
//                                            AppHelper.hideAuthorizationDialog();
//                                            updateUserInvitation(AppHelper.getAuthorizationCode());
//                                        } else {
//                                            AppHelper.showMsg(CommonGoodsDetailActivity.this, "请输入完整授权码");
//                                        }
//                                    }
//                                });
//                            }
//                        } else {
//                            amount = Integer.parseInt(mTvAmount1.getText().toString());
//                            if (amount != 0) {
//                                amount--;
//                                account.get(i).totalNum = amount;
//                                mTvAmount1.setText(amount + "");
////                    totalMoney = totalMoney - Double.parseDouble(bean.price);
//                                BigDecimal totalMon = new BigDecimal(totalMoney);
//                                totalMon = totalMon.subtract(new BigDecimal(bean.price));
//                                totalMoney = totalMon.toString();
//
//
//                            }
//                        }
//                    } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
//                        //这个用户是批发用户
//                        amount = Integer.parseInt(mTvAmount1.getText().toString());
//                        if (amount != 0) {
//                            amount--;
//                            account.get(i).totalNum = amount;
//                            mTvAmount1.setText(amount + "");
////                    totalMoney = totalMoney - Double.parseDouble(bean.price);
//                            BigDecimal totalMon = new BigDecimal(totalMoney);
//                            totalMon = totalMon.subtract(new BigDecimal(bean.price));
//
//                        }
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, "请先登录");
//                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
//                }
//            }
//        });
//        view.findViewById(R.id.tv_item_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
//                    //增加
//                    if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
//                        //这个用户是零售用户
//                        if ("批发".equals(type)) {
//                            if (StringHelper.notEmptyAndNull(cell)) {
//                                AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
//                                            AppHelper.hideAuthorizationDialog();
//                                            updateUserInvitation(AppHelper.getAuthorizationCode());
//                                        } else {
//                                            AppHelper.showMsg(CommonGoodsDetailActivity.this, "请输入完整授权码");
//                                        }
//                                    }
//                                });
//                            }
//                        } else {
//                            if (inventory != 0) {
//                                amount = Integer.parseInt(mTvAmount1.getText().toString());
//                                amount++;
//                                account.get(i).totalNum = amount;
//                                mTvAmount1.setText(amount + "");
////                totalMoney = totalMoney + Double.parseDouble(bean.price);
//                                BigDecimal totalMon = new BigDecimal(totalMoney);
//                                totalMon = totalMon.add(new BigDecimal(bean.price));
//                                totalMoney = totalMon.toString();
//                            }
//                        }
//                    } else if (UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_WHOLESALE)) {
//                        //这个用户是批发用户
//                        int amountAdd;
//                        if (inventory != 0) {
//                            if (!clicked) {
//                                clicked = true;
//                                amount = Integer.parseInt(mTvAmount1.getText().toString());
//                                amountAdd = amount + 1;
//                                AddMountChangeAPI.AddMountChangeService(mContext, businessType, productId, amountAdd, productCombinationId)
//                                        .subscribeOn(Schedulers.io())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe(new Subscriber<AddMountReduceModel>() {
//                                            @Override
//                                            public void onCompleted() {
//                                                clicked = false;
//                                            }
//
//                                            @Override
//                                            public void onError(Throwable e) {
//
//                                            }
//
//                                            @Override
//                                            public void onNext(AddMountReduceModel addMountReduceModel) {
//                                                if (addMountReduceModel.getCode() == 1) {
//                                                    amount++;
//                                                    account.get(i).totalNum = amount;
//                                                    mTvAmount1.setText(amount + "");
////                totalMoney = totalMoney + Double.parseDouble(bean.price);
//                                                    BigDecimal totalMon = new BigDecimal(totalMoney);
//                                                    totalMon = totalMon.add(new BigDecimal(bean.price));
//                                                    totalMoney = totalMon.toString();
//                                                } else {
//                                                    AppHelper.showMsg(mContext, addMountReduceModel.getMessage());
//                                                    mTvAmount1.setText(amount + "");
//                                                    BigDecimal totalMon = new BigDecimal(totalMoney);
//                                                    totalMon = totalMon.add(new BigDecimal(bean.price));
//                                                    totalMoney = totalMon.toString();
//                                                }
//                                            }
//
//
//                                        });
//                            }
//
//                        }
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, "请先登录");
//                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
//                }
//
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 15) {
//            account.clear();
//        }
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }
//
//    /**
//     * 获取评价
//     */
//    private void getAllCommentList(final int pageNum, int pageSize, int businessId, byte businessType) {
//        GetAllCommentListByPageAPI.requestData(mContext, pageNum, pageSize, businessId, businessType)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GetAllCommentListByPageModel>() {
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
//                    public void onNext(GetAllCommentListByPageModel model) {
//                        if (model.success) {
//
//                            if (model.data.list != null && model.data.list.size() > 0) {
//
//                                linearLayoutEvaluationNoData.setVisibility(View.GONE);
//                                linearLayoutEvaluation.setVisibility(View.VISIBLE);
//                                goodsEvaluationContent.setText(model.data.list.get(0).commentContent + "");
//                                if (model.data.list.get(0).replayContent != null || !TextUtils.isEmpty(model.data.list.get(0).replayContent)) {
//                                    goodsEvaluationReply.setText(model.data.list.get(0).replayContent + "");
//                                    goodsEvaluationReply.setVisibility(View.VISIBLE);
//                                } else {
//                                    goodsEvaluationReply.setVisibility(View.GONE);
//                                }
//
//                                goodsEvaluationTime.setText(model.data.list.get(0).commentDate + "");
//                                goodsEvaluationNumber.setText(model.data.list.get(0).customerPhone);
//                                userEvaluationNum.setText("用户评论(" + model.data.total + ")");
//
//                            } else {
//                                linearLayoutEvaluationNoData.setVisibility(View.VISIBLE);
//                                linearLayoutEvaluation.setVisibility(View.GONE);
//                            }
//
//                        } else {
//                            AppHelper.showMsg(mContext, model.message);
//                        }
//                    }
//                });
//
//    }
//
//    /**
//     * 推荐
//     **/
//    private void getProductList() {
//        PublicRequestHelper.getProductList(mContext, 1, 12, "recommendNew", productName, null, null, null, null, new OnHttpCallBack<GetProductListModel>() {
//            @Override
//            public void onSuccessful(GetProductListModel getProductListModel) {
//                if (getProductListModel.success) {
//                    listRecommend.clear();
//                    if (getProductListModel.data.list != null) {
//
//                        adapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, listRecommend);
//                        recyclerViewRecommend.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false));
//                        recyclerViewRecommend.setAdapter(adapterRecommend);
//                        // mAdapterRecommend.addData(getProductListModel.data.list);
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
//    }
//
//
//    /**
//     * 获取收藏状态
//     */
//    private void hasCollectState(int businessId, byte businessType) {
//        PublicRequestHelper.hasCollectState(mContext, businessId, businessType, new OnHttpCallBack<HasCollectModel>() {
//            @Override
//            public void onSuccessful(HasCollectModel hasCollectModel) {
//                if (hasCollectModel.success) {
//                    isCollection = hasCollectModel.data;
//                    if (isCollection) {
//                        //已收藏
//                        mIvCollection.setImageResource(R.mipmap.icon_collection_fill);
//                    } else {
//                        mIvCollection.setImageResource(R.mipmap.icon_collection_null);
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, hasCollectModel.message);
//                }
//            }
//
//            @Override
//            public void onFaild(String errorMsg) {
//
//            }
//        });
//    }
//
//    /**
//     * 点击收藏
//     */
//    private void clickCollection(int businessId, byte businessType, byte type) {
//        ClickCollectionAPI.requestData(mContext, businessId, businessType, type)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ClickCollectionModel>() {
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
//                    public void onNext(ClickCollectionModel clickCollectionModel) {
//                        if (clickCollectionModel.success) {
//                            if (!isCollection) {
//                                isCollection = true;
//                                mIvCollection.setImageResource(R.mipmap.icon_collection_fill);
//                                mTvCollection.setText("已收藏");
//                            } else {
//                                isCollection = false;
//                                mIvCollection.setImageResource(R.mipmap.icon_collection_null);
//                                mTvCollection.setText("收藏");
//                            }
//                        } else {
//                            AppHelper.showMsg(mContext, clickCollectionModel.message);
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * 加入购物车
//     */
//    private void addCart() {
//        //productCombinationPriceVOList (包含productCombinationPriceId 组合售价Id|Integer|必填，totalNum 每个组合的数量|Integer
//        if (businessType == 7) {
//            //这个详情页是设备详情,可以加入购物车和直接购买,这个时候,list.toString传null,totalNum传设备数量
//            String productCombinationPriceVOList = "";
//            String totalNum = String.valueOf(account.get(0).totalNum);
//            requestAddCart(productCombinationPriceVOList, totalNum);
//        } else {
//            //这个详情页是普通商品详情,加入购物车和直接购买需要将json转为string上传,totalNum传""
//            JSONArray array = new JSONArray();
//            try {
//                for (int i = 0; i < account.size(); i++) {
//                    if (account.get(i).totalNum != 0) {
//                        JSONObject object = new JSONObject();
//                        object.put("productCombinationPriceId", account.get(i).productCombinationPriceId);
//                        object.put("totalNum", account.get(i).totalNum);
//                        array.put(object);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            String productCombinationPriceVOList = array.toString();
//            String totalNum = "";
//            requestAddCart(productCombinationPriceVOList, totalNum);
//        }
//    }
//
//    private void requestAddCart(String productCombinationPriceVOList, String tNum) {
//        AddCartAPI.requestData(mContext, productId, productCombinationPriceVOList, businessType, tNum)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<AddCartModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        Toast.makeText(mContext, "错误", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(AddCartModel addCartModel) {
//                        if (addCartModel.success) {
//
//                            getCartNum();
//                            setAnim(mTvAddCar);
//                            AppHelper.showMsg(mContext, "成功加入购物车");
//
//                        } else {
//                            AppHelper.showMsg(mContext, addCartModel.message);
//                        }
//                    }
//                });
//    }
//
//    public void returnBitMap(String src) {
//        MyHandler myHandler = new MyHandler();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(src);
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.setDoInput(true);
//                    connection.connect();
//                    InputStream input = connection.getInputStream();
//                    Bitmap origin = BitmapFactory.decodeStream(input);
//
//                    int width = origin.getWidth();
//                    int height = origin.getHeight();
//
//
//                    Matrix matrix = new Matrix();
//                    matrix.preScale(0.1f, 0.1f);
//                    Bitmap bitmap = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
//                    Message message = new Message();
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("bitmap", bitmap);
//                    message.setData(bundle);
//                    myHandler.sendMessage(message);
//                    origin.recycle();
//                } catch (IOException e) {
//
//                }
//            }
//        }).start();
//    }
//
//
//    public void setAnim(View view) {
//        // TODO Auto-generated method stub
//        int[] start_location = new int[2];// 一个整型数组用来存储按钮在屏幕的X,Y坐标
//        view.getLocationInWindow(start_location);// 购买按钮在屏幕中的坐标
//        buyImg = new ImageView(this);// 动画的小圆圈
//
//
//        returnBitMap(images.get(0));
//
//        setAnim(buyImg, start_location);
//    }
//
//    private ViewGroup createAnimLayout() {
//        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();// 获得Window界面的最顶层
//        LinearLayout animLayout = new LinearLayout(this);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        animLayout.setLayoutParams(lp);
//        //animLayout.setId();
//        animLayout.setBackgroundResource(android.R.color.transparent);
//        rootView.addView(animLayout);
//        return animLayout;
//    }
//
//    private View addViewToAnimLayout(final ViewGroup vp, final View view, int[] location) {
//        int x = location[0];
//        int y = location[1];
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        lp.leftMargin = x;
//        lp.topMargin = y;
//        view.setLayoutParams(lp);
//        return view;
//    }
//
//    private ViewGroup anim_mask_layout;
//
//    public void setAnim(final View v, int[] start_location) {
//
//
//        anim_mask_layout = null;
//        anim_mask_layout = createAnimLayout();
//        anim_mask_layout.addView(v);
//
//        // 当前位置
//        float[] currentPosition = new float[2];
//        int[] controlPosition = new int[2];
//        View view = addViewToAnimLayout(anim_mask_layout, v, start_location);
//        int[] end_location = new int[2];// 存储动画结束位置的X,Y坐标
//        mIvCar.getLocationInWindow(end_location);// 将购物车的位置存储起来
//
//        // 计算位移
//        int endX = end_location[0] - start_location[0];// 动画位移的X坐标
//        int endY = end_location[1] - start_location[1];// 动画位移的y坐标
//
//        controlPosition[0] = (start_location[0] + end_location[0]) / 2;
//        controlPosition[1] = ((start_location[1] + 100) / 2);
//
//        int total = 0;
//        for (int i = 0; i < account.size(); i++) {
//            if (account.get(i).totalNum != 0) {
//                total += account.get(i).totalNum;
//            }
//        }
//
//
//        Path path = new Path();
//        path.moveTo(start_location[0], start_location[1]);
//        path.quadTo(controlPosition[0], controlPosition[1], end_location[0], end_location[1]);
//        PathMeasure pathMeasure = new PathMeasure();
//        // false表示path路径不闭合
//        pathMeasure.setPath(path, false);
//        // ofFloat是一个生成器
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
//        // 匀速线性插值器
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setDuration(400);
//
//        valueAnimator.setRepeatCount(0);
//        // valueAnimator.setRepeatMode();
//        valueAnimator.addUpdateListener(animation -> {
//            float value = (Float) animation.getAnimatedValue();
//            pathMeasure.getPosTan(value, currentPosition, null);
//            buyImg.setX(currentPosition[0]);
//            buyImg.setY(currentPosition[1]);
//        });
//        valueAnimator.start();
///*
//        TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
//        translateAnimationX.setInterpolator(new LinearInterpolator());// 设置此动画的加速曲线。默认为一个线性插值。
//        translateAnimationX.setRepeatCount(0);// 动画重复的次数
//        translateAnimationX.setFillAfter(true);
//
//        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
//        translateAnimationY.setInterpolator(new AccelerateInterpolator());
//        translateAnimationY.setRepeatCount(0);// 动画重复次数
//        translateAnimationY.setFillAfter(true);
//        AnimationSet set = new AnimationSet(false);
//        set.setFillAfter(false);
//        set.addAnimation(translateAnimationX);
//        set.addAnimation(translateAnimationY);
//        set.setDuration(1000);
//        view.startAnimation(set);*/
//
//
//        valueAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                v.setVisibility(View.VISIBLE);
//
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                v.setVisibility(View.GONE);
//
//                valueAnimator.cancel();
//                animation.cancel();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//
//            }
//        });/*{
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//                // TODO Auto-generated method stub
//                v.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                // TODO Auto-generated method stub
//                v.setVisibility(View.GONE);
//
//            }
//        });*/
//
//        ObjectAnimator anim = ObjectAnimator//
//                .ofFloat(view, "scale", 1.0F, 1.5F, 1.0f)//
//                .setDuration(500);//
//        anim.setStartDelay(1000);
//        anim.start();
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float cVal = (Float) animation.getAnimatedValue();
//             /*   mTvAddCar.setScaleX(cVal);
//                mTvAddCar.setScaleY(cVal);*/
//                mIvCar.setScaleX(cVal);
//                mIvCar.setScaleY(cVal);
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        //开始轮播
//        mBanner.startAutoPlay();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        //结束轮播
//        mBanner.stopAutoPlay();
//    }
//
//    /**
//     * 获取角标数据
//     */
//    private void getCartNum() {
//        PublicRequestHelper.getCartNum(mContext, new OnHttpCallBack<GetCartNumModel>() {
//            @Override
//            public void onSuccessful(GetCartNumModel getCartNumModel) {
//                if (getCartNumModel.isSuccess()) {
//                    if (Integer.valueOf(getCartNumModel.getData().getNum()) > 0) {
//                        mIvCar.setImageResource(R.mipmap.ic_buy_car_fill);
//                        mTvCarNum.setVisibility(View.VISIBLE);
//                        mTvCarNum.setText(getCartNumModel.getData().getNum());
//                        mTvAmount.setText("￥" + getCartNumModel.getData().getTotalPrice() + "元");
//                        mTvAmount.setTextColor(Color.parseColor("#000000"));
//                        mTvFee.setVisibility(View.VISIBLE);
//                        mTvFee.setText("满" + getCartNumModel.getData().getSendAmount() + "元免配送费");
//                    } else {
//                        mIvCar.setImageResource(R.mipmap.ic_buy_car);
//                        mTvCarNum.setVisibility(View.GONE);
//                        mTvAmount.setText("未选购商品");
//                        mTvAmount.setTextColor(Color.parseColor("#A7A7A7"));
//                        mTvFee.setVisibility(View.GONE);
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, getCartNumModel.getMessage());
//                }
//            }
//
//            @Override
//            public void onFaild(String errorMsg) {
//
//            }
//        });
//    }
//
//    /**
//     * 获取客服电话
//     */
//    private void getCustomerPhone() {
//        PublicRequestHelper.getCustomerPhone(mContext, new OnHttpCallBack<GetCustomerPhoneModel>() {
//            @Override
//            public void onSuccessful(GetCustomerPhoneModel getCustomerPhoneModel) {
//                if (getCustomerPhoneModel.isSuccess()) {
//                    cell = getCustomerPhoneModel.getData();
//                } else {
//                    AppHelper.showMsg(mContext, getCustomerPhoneModel.getMessage());
//                }
//            }
//
//            @Override
//            public void onFaild(String errorMsg) {
//            }
//        });
//    }
//
//    /**
//     * 提交验证码
//     */
//    private void updateUserInvitation(String code) {
//        UpdateUserInvitationAPI.requestData(mContext, code)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<UpdateUserInvitationModel>() {
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
//                    public void onNext(UpdateUserInvitationModel updateUserInvitationModel) {
//                        if (updateUserInvitationModel.isSuccess()) {
//                            UserInfoHelper.saveUserType(mContext, AppConstant.USER_TYPE_WHOLESALE);
//                            getProductDetail(productId, productType);
//                        } else {
//                            AppHelper.showMsg(mContext, updateUserInvitationModel.getMessage());
//                        }
//                    }
//                });
//    }
//
//    protected void settranslucentStatus() {
//        // 5.0以上系统状态栏透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//    }
//
//
//    // 分享
//    private void showInviteDialog() {
//        final Dialog dialog = new Dialog(mContext, R.style.Theme_Light_Dialog);
//        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_invite, null);
//        //获得dialog的window窗口
//        Window window = dialog.getWindow();
//        //设置dialog在屏幕底部
//        window.setGravity(Gravity.BOTTOM);
//        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
//        window.setWindowAnimations(R.style.dialogStyle);
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        //获得window窗口的属性
//        android.view.WindowManager.LayoutParams lp = window.getAttributes();
//        //设置窗口宽度为充满全屏
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        //设置窗口高度为包裹内容
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        //将设置好的属性set回去
//        window.setAttributes(lp);
//        //将自定义布局加载到dialog上
//        dialog.setContentView(dialogView);
//        LinearLayout mLlInviteQQ = dialogView.findViewById(R.id.ll_invite_dialog_qq);
//        LinearLayout mLlInviteWxCircle = dialogView.findViewById(R.id.ll_invite_dialog_wxcircle);
//        LinearLayout mLlInviteWeChat = dialogView.findViewById(R.id.ll_invite_dialog_wechat);
//        TextView mTvInviteText = dialogView.findViewById(R.id.tv_invite_dialog_text);
//        TextView mTvInviteCancel = dialogView.findViewById(R.id.tv_invite_dialog_cancel);
//        dialog.show();
//        mLlInviteQQ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                UMWeb umWeb = new UMWeb(mShareUrl);
//                umWeb.setDescription(mShareDesc);
//                umWeb.setThumb(new UMImage(CommonGoodsDetailActivity.this, mShareIcon));
//                umWeb.setTitle(mShareTitle);
//
//                new ShareAction(CommonGoodsDetailActivity.this)
//                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
//                        .withMedia(umWeb)//分享内容
//                        .setCallback(umShareListener)//回调监听器
//                        .share();
//            }
//        });
//
//
//        mLlInviteWeChat.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View view) {
//                if (StringHelper.notEmptyAndNull(mShareTitle)
//                        && StringHelper.notEmptyAndNull(mShareDesc)
//                        && StringHelper.notEmptyAndNull(mShareIcon)
//                        && StringHelper.notEmptyAndNull(mShareUrl)) {
//
//                    UMWeb umWeb = new UMWeb(mShareUrl);
//                    umWeb.setDescription(mShareDesc);
//                    umWeb.setThumb(new UMImage(CommonGoodsDetailActivity.this, mShareIcon));
//                    umWeb.setTitle(mShareTitle);
//                    new ShareAction(CommonGoodsDetailActivity.this)
//                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
//                            .withMedia(umWeb)//分享内容
//                            .setCallback(umShareListener)//回调监听器
//                            .share();
//                } else {
//                    Toast.makeText(CommonGoodsDetailActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
//                }
//                dialog.dismiss();
//            }
//        });
//        mLlInviteWxCircle.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View view) {
//                if (StringHelper.notEmptyAndNull(mShareTitle)
//                        && StringHelper.notEmptyAndNull(mShareDesc)
//                        && StringHelper.notEmptyAndNull(mShareIcon)
//                        && StringHelper.notEmptyAndNull(mShareUrl)) {
//                    UMWeb umWeb = new UMWeb(mShareUrl);
//                    umWeb.setDescription(mShareDesc);
//                    umWeb.setThumb(new UMImage(CommonGoodsDetailActivity.this, mShareIcon));
//                    umWeb.setTitle(mShareTitle);
//                    new ShareAction(CommonGoodsDetailActivity.this)
//                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
//                            .withMedia(umWeb)//分享内容
//                            .setCallback(umShareListener)//回调监听器
//                            .share();
//                } else {
//                    Toast.makeText(CommonGoodsDetailActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
//                }
//                dialog.dismiss();
//            }
//        });
//
//        mTvInviteCancel.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View view) {
//                dialog.dismiss();
//            }
//        });
//    }
//
//
//    // 获取分享内容
//    private void requestGoodsList() {
//        GetShareInfoAPI.requestGetShareInfoService(mContext, productId, 1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GetShareInfoModle>() {
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
//                    public void onNext(GetShareInfoModle getShareInfoModle) {
//                        if (getShareInfoModle.isSuccess()) {
//                            mShareTitle = getShareInfoModle.getData().getTitle();
//                            mShareDesc = getShareInfoModle.getData().getDesc();
//                            mShareIcon = getShareInfoModle.getData().getIcon();
//                            mShareUrl = getShareInfoModle.getData().getPageUrl();
//                            showInviteDialog();
//
//                        }
//
//
//                    }
//                });
//    }
//
//    /**
//     * 分享回调
//     */
//    private UMShareListener umShareListener = new UMShareListener() {
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//        }
//
//        @Override
//        public void onResult(SHARE_MEDIA platform) {
//            if (platform.name().equals("WEIXIN_FAVORITE")) {
//                Toast.makeText(CommonGoodsDetailActivity.this, " 收藏成功", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(CommonGoodsDetailActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, Throwable t) {
////            Toast.makeText(MyInviteActivity.this, " 分享失败啦", Toast.LENGTH_SHORT).show();
////            if (t != null) {
////            }
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform) {
////            Toast.makeText(MyInviteActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
//        }
//    };

}
