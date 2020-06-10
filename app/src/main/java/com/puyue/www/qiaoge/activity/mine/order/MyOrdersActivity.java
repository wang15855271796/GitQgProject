package com.puyue.www.qiaoge.activity.mine.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.xtablayout.XTabLayout;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.adapter.mine.MyOrdersViewPagerAdapter;
import com.puyue.www.qiaoge.base.BaseActivity;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.GoToMineEvent;
import com.puyue.www.qiaoge.fragment.mine.order.AllOrderFragment;
import com.puyue.www.qiaoge.fragment.mine.order.DeliveryOrderFragment;
import com.puyue.www.qiaoge.fragment.mine.order.EvaluatedOrderFragment;
import com.puyue.www.qiaoge.fragment.mine.order.PaymentOrderFragment;
import com.puyue.www.qiaoge.fragment.mine.order.ReceivedOrderFragment;
import com.puyue.www.qiaoge.fragment.mine.order.ReturnOrderFragment;
import com.puyue.www.qiaoge.fragment.mine.order.TestFragment;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.yanzhenjie.sofia.Sofia;

import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
//"我的"界面的"我的订单","待收货","待付款"种种都是跳转这个界面
public class MyOrdersActivity extends BaseActivity {
    public static final String TYPE = "type";
    private List<String> mListTitles = new ArrayList<>();
    private List<String> mListType = new ArrayList<>();
    private List<BaseFragment> mListFragment = new ArrayList<>();
    private String mType;//根据这个type来判断用户之前点击的是哪一类,就跳转哪一类
    private ImageView mIvBack;
    private XTabLayout mTab;
    private ViewPager mViewPager;
    private MyOrdersViewPagerAdapter mAdapterViewPager;
    private TextView tv_deliver_order;
    private TextView tv_order_sufficiency;
    private TextView tv_line_one;
    private TextView tv_line_two;

    private int orderDeliveryType;

    public static Intent getIntent(Context context, Class<?> cls, String type) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(TYPE, type);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        mType = getIntent().getStringExtra(TYPE);
        orderDeliveryType = getIntent().getIntExtra("orderDeliveryType", 0);
        if (savedInstanceState != null) {
            mType = savedInstanceState.getString(TYPE);

        }
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_orders);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_my_orders_back);
        mTab = (XTabLayout) findViewById(R.id.tab_my_orders);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_my_orders);
        tv_line_one = findViewById(R.id.tv_line_one);
        tv_line_two = findViewById(R.id.tv_line_two);
        tv_deliver_order = findViewById(R.id.tv_deliver_order);
        tv_order_sufficiency = findViewById(R.id.tv_order_sufficiency);
        tv_line_one.setVisibility(View.GONE);
        tv_line_two.setVisibility(View.GONE);
    }

    @Override
    public void setViewData() {
        Sofia.with(mActivity).statusBarDarkFont()
                .statusBarBackground(Color.parseColor("#ffffff"));
        UserInfoHelper.saveDeliverType(mActivity, orderDeliveryType + "");
        if (orderDeliveryType == 0) {
            tv_deliver_order.setTextColor(Color.parseColor("#FF000000"));
            tv_line_one.setVisibility(View.VISIBLE);
            tv_line_one.setBackgroundColor(Color.parseColor("#FFFF680A"));
            tv_line_two.setVisibility(View.GONE);
            tv_order_sufficiency.setTextColor(Color.parseColor("#FFA1A1A1"));
            initView();
        } else if (orderDeliveryType == 1) {
            tv_order_sufficiency.setTextColor(Color.parseColor("#FF000000"));
            tv_line_two.setVisibility(View.VISIBLE);
            tv_line_two.setBackgroundColor(Color.parseColor("#FFFF680A"));
            tv_line_one.setVisibility(View.GONE);
            tv_deliver_order.setTextColor(Color.parseColor("#FFA1A1A1"));
            initViewSelf();
        }
    }

    private void initView() {

        mTab.removeAllTabs();

        mListTitles.clear();
        mListFragment.clear();
        mViewPager.removeAllViews();

        mListType.clear();

        mListTitles.addAll(Arrays.asList("全部", "待付款", "待发货", "待收货", "待评价", "退货"));

        mListType.addAll(Arrays.asList(AppConstant.ALL, AppConstant.PAYMENT, AppConstant.DELIVERY, AppConstant.RECEIVED, AppConstant.EVALUATED, AppConstant.RETURN));



        mListFragment.add(new AllOrderFragment());
        mListFragment.add(new PaymentOrderFragment());
        mListFragment.add(new DeliveryOrderFragment());
        mListFragment.add(new ReceivedOrderFragment());
        mListFragment.add(new EvaluatedOrderFragment());
        mListFragment.add(new ReturnOrderFragment());

        mAdapterViewPager = new MyOrdersViewPagerAdapter(getSupportFragmentManager(), mListTitles, mListFragment);


    mViewPager.setOffscreenPageLimit(5);
        mAdapterViewPager.clear(mViewPager);
        mViewPager.setAdapter(mAdapterViewPager);
        mTab.setupWithViewPager(mViewPager);
        if (StringHelper.notEmptyAndNull(mType)) {
            if (mType.equals(AppConstant.ALL)) {
                //全部订单
                mTab.getTabAt(0).select();
            } else if (mType.equals(AppConstant.PAYMENT)) {
                //待付款
                mTab.getTabAt(1).select();
            } else if (mType.equals(AppConstant.DELIVERY)) {
                //待发货
                mTab.getTabAt(2).select();
            } else if (mType.equals(AppConstant.RECEIVED)) {
                //待收货

                mTab.getTabAt(3).select();
            } else if (mType.equals(AppConstant.EVALUATED)) {
                //待评价
                mTab.getTabAt(4).select();

            } else if (mType.equals(AppConstant.RETURN)) {
                //退货

                mTab.getTabAt(5).select();
            }
        }
    }

    private void initViewSelf() {

        mTab.removeAllTabs();
        mListTitles.clear();
        mListFragment.clear();
        mViewPager.removeAllViews();
        mListType.clear();



        mListTitles.addAll(Arrays.asList("全部", "待付款", "待提货", "待评价", "退货"));

        mListType.addAll(Arrays.asList(AppConstant.ALL, AppConstant.PAYMENT, AppConstant.DELIVERY, AppConstant.EVALUATED, AppConstant.RETURN));


        mListFragment.add(new AllOrderFragment());
        mListFragment.add(new PaymentOrderFragment());
        mListFragment.add(new DeliveryOrderFragment());
        mListFragment.add(new EvaluatedOrderFragment());
        mListFragment.add(new ReturnOrderFragment());

        mAdapterViewPager = new MyOrdersViewPagerAdapter(getSupportFragmentManager(), mListTitles, mListFragment);



        mViewPager.setOffscreenPageLimit(5);
        mAdapterViewPager.clear(mViewPager);

        mViewPager.setAdapter(mAdapterViewPager);
        mTab.setupWithViewPager(mViewPager);
        if (StringHelper.notEmptyAndNull(mType)) {
            if (mType.equals(AppConstant.ALL)) {
                //全部订单
                mTab.getTabAt(0).select();
            } else if (mType.equals(AppConstant.PAYMENT)) {
                //待付款
                mTab.getTabAt(1).select();

            } else if (mType.equals(AppConstant.DELIVERY)) {
                //待提货
                mTab.getTabAt(2).select();
            }else if (mType.equals(AppConstant.EVALUATED)) {
                //待评价
                mTab.getTabAt(3).select();
            } else if (mType.equals(AppConstant.RETURN)) {
                //退货
                mTab.getTabAt(4).select();
            }
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                backEvent();
            }
        });

        tv_deliver_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDeliveryType == 1) {
                    tv_deliver_order.setTextColor(Color.parseColor("#FF000000"));
                    tv_line_one.setVisibility(View.VISIBLE);
                    tv_line_one.setBackgroundColor(Color.parseColor("#FFFF680A"));
                    tv_line_two.setVisibility(View.GONE);
                    tv_order_sufficiency.setTextColor(Color.parseColor("#FFA1A1A1"));
                    orderDeliveryType = 0;
                    UserInfoHelper.saveDeliverType(mActivity, orderDeliveryType + "");
                    initView();
                }

            }
        });
        tv_order_sufficiency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDeliveryType == 0) {
                    orderDeliveryType = 1;
                    tv_order_sufficiency.setTextColor(Color.parseColor("#FF000000"));
                    tv_line_two.setVisibility(View.VISIBLE);
                    tv_line_two.setBackgroundColor(Color.parseColor("#FFFF680A"));
                    tv_line_one.setVisibility(View.GONE);
                    tv_deliver_order.setTextColor(Color.parseColor("#FFA1A1A1"));
                    UserInfoHelper.saveDeliverType(mActivity, orderDeliveryType + "");
                    initViewSelf();
                }

            }
        });

    }

    private void backEvent() {
        startActivity(HomeActivity.getIntent(mContext, HomeActivity.class));
        EventBus.getDefault().post(new GoToMineEvent());
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backEvent();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
