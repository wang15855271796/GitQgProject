package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;
import com.puyue.www.qiaoge.utils.DateUtils;
import com.puyue.www.qiaoge.utils.Utils;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/12/25
 */
public class CouponFragment1 extends BaseFragment {
    private Unbinder bind;
    @BindView(R.id.recyclerView)
    RecyclerView recycleView;
    TeamActiveQueryModel teamActiveQueryModels;
    //折扣集合
    List<TeamActiveQueryModel.DataBean> couponList = new ArrayList<>();
    private Coupon1Adapter coupon1Adapter;

    public static CouponFragment1 getInstance() {
        CouponFragment1 fragment = new CouponFragment1();
        return fragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_team;
    }

    @Override
    public void initViews(View view) {

        bind = ButterKnife.bind(this, view);
        coupon1Adapter = new Coupon1Adapter(R.layout.item_coupons_list, couponList, new Coupon1Adapter.Onclick() {
            @Override
            public void addDialog() {

            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        recycleView.setAdapter(coupon1Adapter);
        getCouponList();

    }

    /**
     * 获取折扣列表
     */
    private void getCouponList() {
        TeamActiveQueryAPI.requestData(mActivity,11+"",1+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TeamActiveQueryModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TeamActiveQueryModel teamActiveQueryModel) {
                        teamActiveQueryModels = teamActiveQueryModel;
                        if (teamActiveQueryModel.isSuccess()) {
                            couponList.clear();
                            if (teamActiveQueryModel.getData() != null) {
                                couponList.addAll(teamActiveQueryModel.getData());
                                Log.d("sdfdfdfgdgdfgd.....",couponList.size()+"");
                                coupon1Adapter.notifyDataSetChanged();

                            }
                        }
                    }
                });
    }

    @Override
    public void findViewById(View view) {

    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
}
