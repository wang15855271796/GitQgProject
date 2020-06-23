package com.puyue.www.qiaoge.activity;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.puyue.www.qiaoge.view.IdeaScrollView;
import com.puyue.www.qiaoge.view.IdeaViewPager;
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

    @BindView(R.id.scrollView)
    IdeaScrollView scrollView;
    @BindView(R.id.viewPager)
    IdeaViewPager viewPager;
    @BindView(R.id.ll_head)
    LinearLayout ll_head;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private float currentPercentage = 0;
    private boolean isNeedScrollTo = true;



    @Override
    public boolean handleExtra(Bundle savedInstanceState) {

        return false;
    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_product_detail);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {
        ButterKnife.bind(this);
        ArrayList<Integer> araryDistance = new ArrayList<>();

        Rect rectangle= new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        scrollView.setViewPager(viewPager,getMeasureHeight(ll_head)-rectangle.top);
        radioGroup.setAlpha(0);
        radioGroup.check(radioGroup.getChildAt(0).getId());
        scrollView.setOnSelectedIndicateChangedListener(new IdeaScrollView.OnSelectedIndicateChangedListener() {
            @Override
            public void onSelectedChanged(int position) {
                isNeedScrollTo = false;
                radioGroup.check(radioGroup.getChildAt(position).getId());
                isNeedScrollTo = true;
            }
        });

        scrollView.setOnScrollChangedColorListener(new IdeaScrollView.OnScrollChangedColorListener() {
            @Override
            public void onChanged(float percentage) {
                int color = getAlphaColor(percentage>0.9f?1.0f:percentage);
                ll_head.setBackgroundDrawable(new ColorDrawable(color));
                radioGroup.setBackgroundDrawable(new ColorDrawable(color));
                radioGroup.setAlpha((percentage>0.9f?1.0f:percentage)*255);
                setRadioButtonTextColor(percentage);

            }

            @Override
            public void onChangedFirstColor(float percentage) {

            }

            @Override
            public void onChangedSecondColor(float percentage) {

            }
        });

        RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                for(int i=0;i<radioGroup.getChildCount();i++){
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    radioButton.setTextColor(radioButton.isChecked()?getRadioCheckedAlphaColor(currentPercentage):getRadioAlphaColor(currentPercentage));
                    if(radioButton.isChecked()&&isNeedScrollTo){
                        scrollView.setPosition(i);
                    }
                }
            }
        };
        radioGroup.setOnCheckedChangeListener(radioGroupListener);
    }

    private void setRadioButtonTextColor(float percentage) {
        if(Math.abs(percentage-currentPercentage)>=0.1f){
            for(int i=0;i<radioGroup.getChildCount();i++){
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked()?getRadioCheckedAlphaColor(percentage):getRadioAlphaColor(percentage));
            }
            this.currentPercentage = percentage;
        }
    }

    @Override
    public void setClickEvent() {

    }

    public int getAlphaColor(float f){
        return Color.argb((int) (f*255),0x09,0xc1,0xf4);
    }

    public int getLayerAlphaColor(float f){
        return Color.argb((int) (f*255),0x09,0xc1,0xf4);
    }

    public int getRadioCheckedAlphaColor(float f){
        return Color.argb((int) (f*255),0x44,0x44,0x44);
    }

    public int getRadioAlphaColor(float f){
        return Color.argb((int) (f*255),0xFF,0xFF,0xFF);
    }

    public int getMeasureHeight(View view){
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }


}
