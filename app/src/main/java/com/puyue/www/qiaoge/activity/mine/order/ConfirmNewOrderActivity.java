package com.puyue.www.qiaoge.activity.mine.order;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.fragment.order.ConfirmOrderDeliverFragment;
import com.puyue.www.qiaoge.fragment.order.ConfirmOrderSufficiencyFragment;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

/**
 * Created by ${王文博} on 2019/7/18
 * 最新版确认订单，增加门店自提
 */

public class ConfirmNewOrderActivity extends BaseSwipeActivity {

    private static final String TAB_DELIVER = "tab_deliver";
    private static final String TAB_SUFFICIENCY = "tab_sufficiency";
    private FrameLayout fr_confirm_oder;
    private TextView tv_deliver_order;
    private TextView tv_sufficiency_order;
    private TextView tv_deliver_order_two;
    private TextView tv_sufficiency_order_two;


    private Fragment mFragmentDeliver;//送货上门
    private Fragment mFragmentSufficiency;//自提货物
    private FragmentTransaction mFragmentTransaction;
    private Toolbar toolbar;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.confirm_new_order_acitivity);
    }

    @Override
    public void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        fr_confirm_oder = findViewById(R.id.fr_confirm_oder);
        tv_deliver_order = findViewById(R.id.tv_deliver_order);
        tv_sufficiency_order = findViewById(R.id.tv_sufficiency_order);
        tv_deliver_order_two = findViewById(R.id.tv_deliver_order_two);
        tv_sufficiency_order_two = findViewById(R.id.tv_sufficiency_order_two);
    }

    @Override
    public void setViewData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        switchTab(TAB_DELIVER);
        setTranslucentStatus();
    }

    @Override
    public void setClickEvent() {
        toolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
//                startActivity(new Intent(mContext, HomeActivity.class));
                finish();
            }
        });
        tv_deliver_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_sufficiency_order.setVisibility(View.VISIBLE);
                tv_sufficiency_order_two.setVisibility(View.GONE);
                tv_deliver_order_two.setVisibility(View.GONE);
                switchTab(TAB_DELIVER);
            }
        });

        tv_sufficiency_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_sufficiency_order.setVisibility(View.GONE);
                tv_deliver_order.setVisibility(View.GONE);
                tv_deliver_order_two.setVisibility(View.VISIBLE);
                tv_sufficiency_order_two.setVisibility(View.VISIBLE);
                switchTab(TAB_SUFFICIENCY);
            }
        });
        tv_deliver_order_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_deliver_order_two.setVisibility(View.GONE);
                tv_deliver_order.setVisibility(View.VISIBLE);
                tv_sufficiency_order.setVisibility(View.VISIBLE);
                tv_sufficiency_order_two.setVisibility(View.GONE);
                switchTab(TAB_DELIVER);

            }
        });
        tv_sufficiency_order_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_deliver_order_two.setVisibility(View.VISIBLE);
                tv_deliver_order.setVisibility(View.GONE);
                tv_sufficiency_order.setVisibility(View.GONE);
                switchTab(TAB_SUFFICIENCY);
            }
        });
    }

    private void switchTab(String tab) {
        //开始事务
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mFragmentDeliver != null) {
            mFragmentTransaction.hide(mFragmentDeliver);
        }

        if (mFragmentSufficiency != null) {
            mFragmentTransaction.hide(mFragmentSufficiency);
        }

        switch (tab) {
            case TAB_DELIVER:
                if (mFragmentDeliver == null) {

                    mFragmentDeliver = new ConfirmOrderDeliverFragment();
                    mFragmentTransaction.add(R.id.fr_confirm_oder, mFragmentDeliver);

                } else {
                    mFragmentTransaction.show(mFragmentDeliver);

                }


                break;

            case TAB_SUFFICIENCY:
                if (mFragmentSufficiency == null) {
                    mFragmentSufficiency = new ConfirmOrderSufficiencyFragment();
                    mFragmentTransaction.add(R.id.fr_confirm_oder, mFragmentSufficiency);
                } else {
                    mFragmentTransaction.show(mFragmentSufficiency);
                }
                break;
        }
        //提交事务
        mFragmentTransaction.commitAllowingStateLoss();
    }


    protected void setTranslucentStatus() {
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
}