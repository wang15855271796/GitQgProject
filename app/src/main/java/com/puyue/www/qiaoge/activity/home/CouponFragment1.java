package com.puyue.www.qiaoge.activity.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import java.util.ArrayList;
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
        coupon1Adapter = new Coupon1Adapter(R.layout.item_coupon_list, couponList, new Coupon1Adapter.Onclick() {
            @Override
            public void addDialog() {

            }
        });
        recycleView.setLayoutManager(new GridLayoutManager(mActivity,2));
        recycleView.setAdapter(coupon1Adapter);
        getCouponList();

    }

    /**
     * 获取折扣列表
     */
    private void getCouponList() {
        TeamActiveQueryAPI.requestData(mActivity,11+"",0+"")
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
                        if (teamActiveQueryModel.isSuccess()) {
                            couponList.clear();
                            if (teamActiveQueryModel.getData() != null) {
                                couponList.addAll(teamActiveQueryModel.getData());
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
