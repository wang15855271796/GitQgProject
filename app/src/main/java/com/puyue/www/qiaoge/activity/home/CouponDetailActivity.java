package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.SpecialGoodAdapter;
import com.puyue.www.qiaoge.adapter.home.TeamActiveQueryAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.home.GetMoreSpecialAPI;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.TwoDeviceHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.home.SpecialMoreGoodModel;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/11
 */
public class CouponDetailActivity extends BaseSwipeActivity {
    @BindView(R.id.rv_detail)
    RecyclerView rv_detail;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    //折扣集合
    List<SpecialMoreGoodModel.DataBean> couponList = new ArrayList<>();

    private SpecialGoodAdapter couponDetailAdapter;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.common_detail);

    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        tv_title.setText("折扣专区");
        setTranslucentStatus();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void setViewData() {
        getCouponList();

        //优惠Adapter
        couponDetailAdapter = new SpecialGoodAdapter(R.layout.item_special_good, couponList, new SpecialGoodAdapter.Onclick() {
            @Override
            public void addCarOnclick(int position) {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {
                    SpecialMoreGoodModel.DataBean dataBean = couponList.get(position);
                    addCar(dataBean.getActiveId(), "", 11, "1");
                } else {
                    AppHelper.showMsg(mActivity, "请先登录");
                    startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                }

                couponDetailAdapter.notifyDataSetChanged();
            }
        });


        rv_detail.setLayoutManager(new LinearLayoutManager(mContext));
        rv_detail.setAdapter(couponDetailAdapter);

    }

    /**
     * 获取折扣列表
     */
    private void getCouponList() {
        GetMoreSpecialAPI.RequestMoreSpecial(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SpecialMoreGoodModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SpecialMoreGoodModel specialMoreGoodModel) {
                        if (specialMoreGoodModel.isSuccess()) {
                            couponList.clear();
                            if (!specialMoreGoodModel.getData().isEmpty()) {
                                couponList.addAll(specialMoreGoodModel.getData());
                                couponDetailAdapter.notifyDataSetChanged();
                            }
                            couponDetailAdapter.notifyDataSetChanged();

                        } else {
                            AppHelper.showMsg(mContext, specialMoreGoodModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 添加购物车
     * @param businessId
     * @param productCombinationPriceVOList
     * @param businessType
     * @param totalNum
     */
    private void addCar(int businessId, String productCombinationPriceVOList, int businessType, String totalNum) {
        AddCartAPI.requestData(mActivity, businessId, productCombinationPriceVOList, businessType, String.valueOf(totalNum))
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
                            AppHelper.showMsg(mActivity, "成功加入购物车");
//                            getCartNum();
                        } else {
                            AppHelper.showMsg(mActivity, addCartModel.message);
                            Log.e("Crash", "onNext: " + addCartModel.message);
                        }

                    }
                });
    }

    @Override
    public void setClickEvent() {
    }
}
