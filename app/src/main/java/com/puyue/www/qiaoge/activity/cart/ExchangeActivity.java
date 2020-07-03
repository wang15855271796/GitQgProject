package com.puyue.www.qiaoge.activity.cart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.CouponsAdapter;
import com.puyue.www.qiaoge.adapter.CouponListsAdapter;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/6/26
 */
public class ExchangeActivity extends BaseSwipeActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    int num = 0;
    private List<String> list = new ArrayList<>();
    private CouponListsAdapter couponListsAdapter;
    private ArrayList<String> mDatas = new ArrayList<>();

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_exchange);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        iv_back.setOnClickListener(this);
        list = initData();
        couponListsAdapter = new CouponListsAdapter(R.layout.item_coupon,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(couponListsAdapter);
        iv_add.setOnClickListener(this);
    }

    private List<String> initData() {
        for (int i = 0; i < SharedPreferencesUtil.getInt(mActivity,"nums"); i++) {
            mDatas.add("我是商品" + i);
        }
        return mDatas;
    }

    @Override
    public void setViewData() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void setClickEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_add:
                SharedPreferencesUtil.saveInt(mActivity,"nums",num);
                couponListsAdapter.addData(list.size());
                break;
        }
    }
}
