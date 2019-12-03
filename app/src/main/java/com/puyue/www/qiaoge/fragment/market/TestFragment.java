package com.puyue.www.qiaoge.fragment.market;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.market.ClassIfyModel;
import com.puyue.www.qiaoge.api.market.MarketGoodSelcetAPI;
import com.puyue.www.qiaoge.api.market.MarketRightModel;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.TwoDeviceHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TestFragment extends BaseFragment {

    public RecyclerView rv_market_detail;
    private ClassifyAdapter classifyAdapter;
    private List<ClassIfyModel.DataBean> list_left_copy = new ArrayList<>();
    ClassIfyModel classIfyModel;
    @Override
    public int setLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    public void initViews(View view) {
        rv_market_detail = view.findViewById(R.id.rv_market_detail);
        getDate();
    }

    private void getDate() {

        MarketGoodSelcetAPI.getClassifyRight(mActivity, 1, 12, -5, 0, "", "", "", "", "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MarketRightModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MarketRightModel marketGoodSelectModel) {
//                        ProdAdapter prodAdapter = new ProdAdapter(R.layout.item_prod,marketGoodSelectModel.getData().getBrandProd());
                        rv_market_detail.setLayoutManager(new LinearLayoutManager(getActivity()));
//                        rv_market_detail.setAdapter(prodAdapter);

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
