package com.puyue.www.qiaoge.activity;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.GetPhoneInfoListener;
import com.chuanglan.shanyan_sdk.listener.InitListener;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.puyue.www.qiaoge.CustomViewPager;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.TabEntity;
import com.puyue.www.qiaoge.activity.home.ChangeCityActivity;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.EvaluationActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.LogoutsEvent;
import com.puyue.www.qiaoge.activity.mine.login.RegisterActivity;
import com.puyue.www.qiaoge.activity.mine.login.RegisterMessageActivity;
import com.puyue.www.qiaoge.adapter.cart.ChooseSpecAdapter;
import com.puyue.www.qiaoge.adapter.cart.ImageViewAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsRecommendAdapter;
import com.puyue.www.qiaoge.adapter.mine.ViewPagerAdapters;
import com.puyue.www.qiaoge.api.PostLoadAmountAPI;
import com.puyue.www.qiaoge.api.SendJsPushAPI;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.ClickCollectionAPI;
import com.puyue.www.qiaoge.api.home.GetAllCommentListByPageAPI;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.api.home.QueryHomePropupAPI;
import com.puyue.www.qiaoge.api.home.SendLocationAPI;
import com.puyue.www.qiaoge.api.mine.GetShareInfoAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.ChooseDialog;
import com.puyue.www.qiaoge.dialog.CouponDialog;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.GoToMineEvent;
import com.puyue.www.qiaoge.event.LogoutEvent;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.fragment.cart.CartFragment;
import com.puyue.www.qiaoge.fragment.cart.NumEvent;
import com.puyue.www.qiaoge.fragment.cart.ReduceNumEvent;
import com.puyue.www.qiaoge.fragment.home.HomeFragmentsss;
import com.puyue.www.qiaoge.fragment.market.MarketsFragment;
import com.puyue.www.qiaoge.fragment.mine.MineFragment;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.CollapsingToolbarLayoutStateHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.home.ChoiceSpecModel;
import com.puyue.www.qiaoge.model.home.ClickCollectionModel;
import com.puyue.www.qiaoge.model.home.GetAddressModel;
import com.puyue.www.qiaoge.model.home.GetAllCommentListByPageModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.GuessModel;
import com.puyue.www.qiaoge.model.home.HasCollectModel;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;
import com.puyue.www.qiaoge.model.mine.GetShareInfoModle;
import com.puyue.www.qiaoge.popupwindow.HomePopuWindow;
import com.puyue.www.qiaoge.utils.LoginUtil;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/5/20
 */
public class Test1Activity extends BaseSwipeActivity{

    private int productId;
    private byte businessType = 1;

    private GoodsRecommendAdapter adapterRecommend;
    @BindView(R.id.recyclerViewImage)
    RecyclerView recyclerViewImage;
    @BindView(R.id.recyclerViewRecommend)
    RecyclerView recyclerViewRecommend;


    public List<GetProductDetailModel.DataBean.ProdSpecsBean> prodSpecs;

    GuessModel searchResultsModel;
    //猜你喜欢集合
    private List<GuessModel.DataBean> searchList = new ArrayList<>();
    //图片详情集合
    private List<String> detailList = new ArrayList<>();
    private ImageViewAdapter imageViewAdapter;
    private List<String> detailPic;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();

            productId = bundle.getInt(AppConstant.ACTIVEID);

            if (!TextUtils.isEmpty(bundle.getString("equipment"))) {
                businessType = 7;
            }
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
        setContentView(R.layout.test2);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Log.d("wddsdd.........",productId+"");
        getProductDetail(productId);





    }

    @Override
    public void setClickEvent() {

    }


    /**
     * 获取详情
     */
    private void getProductDetail(final int productId) {
        GetProductDetailAPI.requestDatas(mContext,productId)
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
                            if(model.getData()!=null) {
                                detailPic = model.getData().getDetailPic();
                                imageViewAdapter = new ImageViewAdapter(R.layout.item_imageview,detailPic);
                                recyclerViewImage.setLayoutManager(new LinearLayoutManager(mActivity));
                                recyclerViewImage.setAdapter(imageViewAdapter);
                                Log.d("dwdasass.....","sffs");
                            }



                            getProductList();


                        } else {
                            ToastUtil.showErroMsg(mActivity,model.getMessage());
                        }
                    }
                });
    }


    /**
     * 推荐
     **/
    private void getProductList() {

        RecommendApI.getLikeList(mContext,productId+"")
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
                                adapterRecommend = new GoodsRecommendAdapter(R.layout.item_goods_recommend, searchList);
                                LinearLayoutManager linearLayoutManagerCoupons = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
                                recyclerViewRecommend.setLayoutManager(linearLayoutManagerCoupons);
                                recyclerViewRecommend.setAdapter(adapterRecommend);
                            }

                        } else {
                            ToastUtil.showSuccessMsg(mContext, recommendModel.getMessage());
                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBuss(ReduceNumEvent event) {
        //刷新UI


    }
}
