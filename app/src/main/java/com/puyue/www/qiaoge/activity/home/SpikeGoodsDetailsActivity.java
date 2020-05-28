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
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CartActivity;
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
import com.puyue.www.qiaoge.api.home.SpikeActiveQueryAPI;
import com.puyue.www.qiaoge.api.home.SpikeActiveQueryByIdAPI;
import com.puyue.www.qiaoge.api.mine.GetShareInfoAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseModel;
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
import com.puyue.www.qiaoge.model.home.GuessModel;
import com.puyue.www.qiaoge.model.home.HasCollectModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.home.SpecialGoodModel;
import com.puyue.www.qiaoge.model.home.SpikeActiveQueryByIdModel;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;
import com.puyue.www.qiaoge.model.mine.GetShareInfoModle;
import com.puyue.www.qiaoge.utils.DateUtils;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;
import com.puyue.www.qiaoge.view.StarBarView;
import com.puyue.www.qiaoge.view.scrollview.Util;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.puyue.www.qiaoge.utils.DateUtils.DATE_FORMAT_Second_CHINESE;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/12.
 * 秒杀商品详情
 */

public class SpikeGoodsDetailsActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
    private Banner banner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvOldPrice;
    private TextView mTvInven;
    private TextView mTvVolume;
    TextView tv_prices;
    private TextView mTvDesc;
    private ImageView mTvSub;
    private TextView mTvAmount;
    private ImageView mTvAdd;
    private TextView mTvGroupAmount;
    private TextView mTvTotalMoney;
    TextView old_price;
    private LinearLayout mLlCustomer;
    //  private LinearLayout mLlCollection;
    private TextView mTvCollection;
    private ImageView mIvCollection;
    private LinearLayout mLlCar;
    private ImageView mIvCar;
    private TextView mTvCarNum;
    private TextView mTvCarAmount;
    private TextView mTvFee;
    private TextView mTvAddCar;
    private ImageView buyImg;
    ProgressBar pb;
    private List<View> mListView = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private String productName;
    //详情
    private RecyclerView mRvDetail;
    private GoodsDetailAdapter mAdapterDetail;
    private List<GoodsDetailModel> mListDetail = new ArrayList<>();
    //评价
    private RecyclerView mRvEvaluation;
    private NestedScrollView mSvEmpty;
    private GoodsEvaluationAdapter mAdapterEvaluation;
    private List<CommentOrderQueryModel.DataBean.ListBean> mListEvaluation = new ArrayList<>();
    private StarBarView sbv_star_bar;
    private TextView tv_status;

    //推荐
    private RecyclerView mRvRecommend;
    private GoodsRecommendAdapter mAdapterRecommend;
    TextView tv_limit_num;
    private int activeId;
    private byte businessType = 2;
    private int pageNum = 1;
    private int pageSize = 10;
    private double price = 0;
    private int amount = 0;
    private String unit;
    private boolean isCollection = false;
    private int Inventory = 0;
    SnapUpCountDownTimerView tv_cut_down;
    private String cell;
    TextView tv_num;
    TextView tv_name;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayoutStateHelper state;
    GuessModel searchResultsModel;
    //搜索集合
    private List<GuessModel.DataBean> searchList = new ArrayList<>();

    //推荐
    private RecyclerView recyclerViewRecommend;
    private GoodsRecommendAdapter adapterRecommend;
    private List<GetProductListModel.DataBean.ListBean> listRecommend = new ArrayList<>();
    // 商品详情
    private RecyclerView recyclerViewImage;
    private GoodsDetailAdapter mAdapterImage;
    private List<GoodsDetailModel> mListDetailImage = new ArrayList<>();
    // 分享
    private ImageView ImageViewShare;
    private String mShareTitle;
    private String mShareDesc;
    private String mShareIcon;
    private String mShareUrl;
    TextView tv_spec;
    TextView tv_total;
    private RelativeLayout linearLayoutShare;
    private String current;
    private String start;
    private boolean time;
    TextView tv_time;
    private Date date;
    private Date currents;
    private Date starts;
    private long currentTime;
    private long startTime;
    private long endTime;
    private int warnMe;

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
            activeId = bundle.getInt(AppConstant.ACTIVEID);
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
        setContentView(R.layout.activity_spike_detail);
    }

    @Override
    public void findViewById() {
        tv_cut_down = FVHelper.fv(this, R.id.tv_cut_down);
        tv_total = FVHelper.fv(this, R.id.tv_total);
        tv_num = FVHelper.fv(this, R.id.tv_num);
        tv_name = FVHelper.fv(this, R.id.tv_name);
        tv_spec = FVHelper.fv(this, R.id.tv_spec);
        tv_time = FVHelper.fv(this, R.id.tv_time);
        tv_limit_num = FVHelper.fv(this, R.id.tv_limit_num);
        mIvBack = FVHelper.fv(this, R.id.iv_activity_back);
//        mSvTime = FVHelper.fv(this, R.id.view_details_seckill);
        banner = FVHelper.fv(this, R.id.banner);
        tv_prices = FVHelper.fv(this, R.id.tv_prices);
        mTvTitle = FVHelper.fv(this, R.id.tv_title);
        mTvPrice = FVHelper.fv(this, R.id.tv_price);
        old_price = FVHelper.fv(this, R.id.old_price);
        mTvOldPrice = FVHelper.fv(this, R.id.tv_activity_spike_old_price);
        mTvInven = FVHelper.fv(this, R.id.tv_activity_spike_inve);
        mTvVolume = FVHelper.fv(this, R.id.tv_activity_spike_volume);
        pb = FVHelper.fv(this, R.id.pb);
        mTvDesc = FVHelper.fv(this, R.id.tv_activity_spike_desc);
        mTvSub = FVHelper.fv(this, R.id.tv_activity_spike_sub);
        mTvAmount = FVHelper.fv(this, R.id.tv_activity_spike_amount);
        mTvAdd = FVHelper.fv(this, R.id.tv_activity_spike_add);
        linearLayoutShare = FVHelper.fv(this, R.id.rl_share);
        mLlCustomer = FVHelper.fv(this, R.id.ll_include_common_customer);
        //mLlCollection = FVHelper.fv(this, R.id.ll_include_common_collection);
        mTvCollection = FVHelper.fv(this, R.id.tv_include_common_collection);
        mIvCollection = FVHelper.fv(this, R.id.iv_include_common_collection);//收藏
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
        mSvEmpty = FVHelper.fv(viewEvaluation, R.id.nsv_item_empty);
        mListView.add(viewEvaluation);
        //推荐
        View viewRecommend = LayoutInflater.from(this).inflate(R.layout.item_viewpager, null);
        mRvRecommend = FVHelper.fv(viewRecommend, R.id.rv_item_viewpager);
        mListView.add(viewRecommend);
        recyclerViewRecommend = (RecyclerView) findViewById(R.id.recyclerViewRecommend);
        recyclerViewImage = (RecyclerView) findViewById(R.id.recyclerViewImage);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageViewShare = (ImageView) findViewById(R.id.ImageViewShare);
        sbv_star_bar = findViewById(R.id.sbv_star_bar);
        tv_status = findViewById(R.id.tv_status);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
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
        spikeActiveQueryById();
        commentOrderQuery(pageNum, pageSize);

        getCustomerPhone();

        adapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, searchList);
        LinearLayoutManager linearLayoutManagerCoupons = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommend.setLayoutManager(linearLayoutManagerCoupons);
        recyclerViewRecommend.setAdapter(adapterRecommend);

    }

    /**
     * 获取订阅状态
     * @param activeId
     */
    private void getState(int activeId,int warnMe) {
        SpikeActiveQueryAPI.requestData(mContext,activeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel seckillListModel) {
                        if(seckillListModel.success) {
                            if(warnMe==0) {
                                mTvAddCar.setText("     添加提醒     ");
                            }else {
                                mTvAddCar.setText("     取消提醒     ");
                            }
                        }else {
                            ToastUtil.showSuccessMsg(mContext,seckillListModel.message);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
            hasCollectState(activeId, businessType);
            getCartNum();
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mTvSub.setOnClickListener(noDoubleClickListener);
        mTvAdd.setOnClickListener(noDoubleClickListener);
        //  mIvCollection.setOnClickListener(noDoubleClickListener);
        mLlCustomer.setOnClickListener(noDoubleClickListener);
        mTvAddCar.setOnClickListener(noDoubleClickListener);
        mLlCar.setOnClickListener(noDoubleClickListener);
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
            } else if (view == mTvSub) {
                //减少
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (price != 0) {
                        //价格不为0表示获取数据成功
                        if (amount > 1) {
                            amount--;
                            mTvAmount.setText(String.valueOf(amount));
                            mTvGroupAmount.setText(amount + unit);
                            mTvTotalMoney.setText("￥" + BigDecimalUtils.mul(price, amount, 2));
                        }
                    }
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mTvAdd) {
                //增加
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (price != 0 && Inventory != 0) {
                        //价格不为0表示获取数据成功
                        amount++;
                        mTvAmount.setText(String.valueOf(amount));
                        mTvGroupAmount.setText(amount + unit);
                        mTvTotalMoney.setText("￥" + BigDecimalUtils.mul(price, amount, 2));
                    }
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }

            } else if (view == mIvCollection) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if (isCollection) {
                        //取消收藏
                        clickCollection(activeId, businessType, (byte) 0);
                    } else {
                        clickCollection(activeId, businessType, (byte) 1);
                    }
                } else {
                    AppHelper.showMsg(mContext, "请先登录");
                    startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }
            } else if (view == mTvAddCar) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if(currentTime>startTime) {
                        //秒杀开始
                        addCar();
                        mTvAddCar.setText("加入购物车");
                        mTvAddCar.setBackgroundResource(R.drawable.selector_once_buy);
                    }else {
                        //未开始
                        getState(activeId,warnMe);
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
     * 加入购物车
     */
    private void addCar() {
        AddCartAPI.requestData(mContext, activeId, null, businessType, String.valueOf(amount))
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
                            AppHelper.showMsg(mContext, "成功加入购物车");
                            getCartNum();
                            setAnim(mTvAddCar);

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

    /**
     * 秒杀详情接口
     **/
    private void spikeActiveQueryById() {
        SpikeActiveQueryByIdAPI.requestData(mContext, activeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SpecialGoodModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        AppHelper.showMsg(mContext, "错误");

                    }

                    @Override
                    public void onNext(SpecialGoodModel spikeActiveQueryByIdModel) {

                        if (spikeActiveQueryByIdModel.isSuccess()) {
                            productName =  spikeActiveQueryByIdModel.getData().getActiveName();
                            mTvTitle.setText(spikeActiveQueryByIdModel.getData().getActiveName());
                            mTvPrice.setText(spikeActiveQueryByIdModel.getData().getShowPrice());
                            tv_total.setText("总计"+spikeActiveQueryByIdModel.getData().getTotalNum());
                            mTvOldPrice.setText(spikeActiveQueryByIdModel.getData().getOldPrice());
                            mTvOldPrice.getPaint().setAntiAlias(true);//抗锯齿
                            tv_num.setText(spikeActiveQueryByIdModel.getData().getCartNum());
                            mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
                            mTvInven.setText(spikeActiveQueryByIdModel.getData().getRemainNum());
                            old_price.setText(spikeActiveQueryByIdModel.getData().getShowOldPrice());
                            old_price.getPaint().setAntiAlias(true);//抗锯齿
                            old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                            mTvVolume.setText(spikeActiveQueryByIdModel.getData().getSaleVolume());
                            pb.setProgress(Integer.parseInt(spikeActiveQueryByIdModel.getData().getProgress()));
                            pb.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.shape_progress2));
                            tv_spec.setText(spikeActiveQueryByIdModel.getData().getSpec());
                            tv_limit_num.setText(spikeActiveQueryByIdModel.getData().getLimitNum());
                            tv_name.setText(spikeActiveQueryByIdModel.getData().getActTypeName());
                            mTvDesc.setText(spikeActiveQueryByIdModel.getData().getIntroduction());
                            tv_prices.setText(spikeActiveQueryByIdModel.getData().getPrice());
                            warnMe = spikeActiveQueryByIdModel.getData().getWarnMe();
                            if(currentTime>startTime) {
                                //秒杀开始
                                addCar();
                                mTvAddCar.setText("加入购物车");
                                mTvAddCar.setBackgroundResource(R.drawable.selector_once_buy);
                            }else {
                                //未开始
                                getState(activeId,warnMe);
                            }


                            currentTime = spikeActiveQueryByIdModel.getData().getCurrentTime();
                            startTime = spikeActiveQueryByIdModel.getData().getStartTime();
                            endTime = spikeActiveQueryByIdModel.getData().getEndTime();
                            String current = DateUtils.formatDate(currentTime, "MM月dd日HH时mm分ss秒");
                            String start = DateUtils.formatDate(startTime, "MM月dd日HH时mm分ss秒");
                            try {
                                currents = Utils.stringToDate(current, "MM月dd日HH时mm分ss秒");
                                starts = Utils.stringToDate(start, "MM月dd日HH时mm分ss秒");
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            boolean exceed24 = DateUtils.isExceed24(currents, starts);
                            if(exceed24) {
                                //大于24
                                tv_time.setText(start+"开抢");
                            }else {
                                //小于24
                                if(startTime !=0&& endTime !=0) {
                                    tv_cut_down.setTime(true, currentTime, startTime, endTime);
                                    tv_cut_down.changeBackGrounds(ContextCompat.getColor(mContext, R.color.color333333));
                                    tv_cut_down.changeTypeColor(Color.WHITE);
                                    tv_time.setVisibility(View.INVISIBLE);
                                    tv_cut_down.start();
                                    tv_cut_down.setVisibility(View.VISIBLE);


                                }else {
                                    tv_time.setVisibility(View.INVISIBLE);
                                    tv_cut_down.setVisibility(View.INVISIBLE);
                                }
                            }

                            if (spikeActiveQueryByIdModel.getData().getTopPics() != null) {
                                //设置banner样式
                                banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                                //设置图片加载器
                                banner.setImageLoader(new GlideImageLoader());
                                //设置图片集合
                                images.addAll(spikeActiveQueryByIdModel.getData().getTopPics());
                                banner.setImages(images);
                                //设置banner动画效果
                                banner.setBannerAnimation(Transformer.DepthPage);
                                //设置自动轮播，默认为true
                                banner.isAutoPlay(true);
                                //设置轮播时间
                                banner.setDelayTime(3000);
                                //设置指示器位置（当banner模式中有指示器时）
                                banner.setIndicatorGravity(BannerConfig.RIGHT);
                                //banner设置方法全部调用完毕时最后调用
                                banner.start();
                            }
                            //banner设置点击监听
                            banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    AppHelper.showPhotoDetailDialog(mContext, images, position);
                                }
                            });
                            getProductList();
                            //填充详情
                            mListDetail.clear();
//                            if (spikeActiveQueryByIdModel.getData().picDetail != null) {
//                                for (int i = 0; i < spikeActiveQueryByIdModel.data.picDetail.size(); i++) {
//                                    GoodsDetailModel goodsDetailModel = new GoodsDetailModel(GoodsDetailModel.typeIv);
//                                    goodsDetailModel.content = spikeActiveQueryByIdModel.data.picDetail.get(i);
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
//
//
//                            }
                            mAdapterDetail.notifyDataSetChanged();
                            //判断秒杀情况显示
//                            Inventory = Integer.parseInt(spikeActiveQueryByIdModel.data.inventory);
                            //先判断有没有卖完,还有没有库存
//                            if (Inventory > 0) {
//                                //商品还有库存
//                                if ("NOT_START".equals(spikeActiveQueryByIdModel.data.type)) {
//                                    // NOT_START("未开始"
//
//                                    mTvAddCar.setEnabled(false);
//                                    mTvAddCar.setBackgroundResource(R.drawable.app_car);
//                                } else if ("STARTED".equals(spikeActiveQueryByIdModel.data.type)) {
//                                    // STARTED("进行中"
//
//                                    mTvAddCar.setEnabled(true);
//                                    mTvAddCar.setBackgroundResource(R.drawable.selector_once_buy);
//
//                                } else if ("OVER".equals(spikeActiveQueryByIdModel.data.type)) {
//                                    //  OVER("已结束"
//
//                                    mTvAddCar.setEnabled(false);
//                                    mTvAddCar.setBackgroundResource(R.drawable.app_car);
//                                }
//                            } else {
//                                //没有库存了,商品已售完
//                                mTvAddCar.setEnabled(false);
//                                mTvAddCar.setBackgroundResource(R.drawable.app_car);
//                            }
//                            price = Double.parseDouble(spikeActiveQueryByIdModel.data.price);
//                            unit = spikeActiveQueryByIdModel.data.combinationPrice.replaceAll("[^\u4e00-\u9fa5]", "");
                            mTvGroupAmount.setText(amount + unit);
                            mTvTotalMoney.setText("￥" + BigDecimalUtils.mul(price, amount, 2));
                        } else {
                            AppHelper.showMsg(mContext, spikeActiveQueryByIdModel.getMessage());
                        }

                    }
                });
    }

    /**
     * 获取评价
     **/
    private void commentOrderQuery(int pageNum, int pageSize) {
        CommentOrderQueryAPI.requestData(mContext, pageNum, pageSize, activeId, (byte) 2)
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
                            if (commentOrderQueryModel.data.list != null) {
                                mAdapterEvaluation.addData(commentOrderQueryModel.data.list);
                                if (mAdapterEvaluation.getData().size() == 0) {
                                    mRvEvaluation.setVisibility(View.GONE);
                                    mSvEmpty.setVisibility(View.VISIBLE);
                                }
                            } else {
                                mRvEvaluation.setVisibility(View.GONE);
                                mSvEmpty.setVisibility(View.VISIBLE);
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

    /**
     * 推荐
     **/
    private void getProductList() {
        RecommendApI.getLikeList(mContext,activeId+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GuessModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GuessModel recommendModel) {
                        if (recommendModel.isSuccess()) {
                            searchResultsModel = recommendModel;
                            if(recommendModel.getData()!=null) {
                                searchList.addAll(recommendModel.getData());
                                adapterRecommend.notifyDataSetChanged();

                            }

                        } else {
                            AppHelper.showMsg(mContext, recommendModel.getMessage());
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

                    }

                    @Override
                    public void onNext(ClickCollectionModel clickCollectionModel) {
                        if (clickCollectionModel.success) {
                            if (!isCollection) {
                                isCollection = true;
                                mIvCollection.setImageResource(R.mipmap.icon_collection_fill);
                                AppHelper.showMsg(mContext, "收藏成功");
                            } else {
                                isCollection = false;
                                mIvCollection.setImageResource(R.mipmap.icon_collection_null);
                                AppHelper.showMsg(mContext, "已取消收藏");
                            }
                        } else {
                            AppHelper.showMsg(mContext, clickCollectionModel.message);
                        }

                    }
                });
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
        WindowManager.LayoutParams lp = window.getAttributes();
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

        TextView mTvInviteCancel = dialogView.findViewById(R.id.tv_invite_dialog_cancel);
        dialog.show();
        mLlInviteQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMWeb umWeb = new UMWeb(mShareUrl);
                umWeb.setDescription(mShareDesc);
                umWeb.setThumb(new UMImage(SpikeGoodsDetailsActivity.this, mShareIcon));
                umWeb.setTitle(mShareTitle);

                new ShareAction(SpikeGoodsDetailsActivity.this)
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
                    umWeb.setThumb(new UMImage(SpikeGoodsDetailsActivity.this, mShareIcon));
                    umWeb.setTitle(mShareTitle);
                    new ShareAction(SpikeGoodsDetailsActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    Toast.makeText(SpikeGoodsDetailsActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
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
                    umWeb.setThumb(new UMImage(SpikeGoodsDetailsActivity.this, mShareIcon));
                    umWeb.setTitle(mShareTitle);
                    new ShareAction(SpikeGoodsDetailsActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    Toast.makeText(SpikeGoodsDetailsActivity.this, "数据不全!", Toast.LENGTH_SHORT).show();
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


    // 接获取分享内容
    private void requestGoodsList() {
        GetShareInfoAPI.requestGetShareInfoService(mContext, activeId, 2)
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
                Toast.makeText(SpikeGoodsDetailsActivity.this, " 收藏成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SpikeGoodsDetailsActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
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
}
