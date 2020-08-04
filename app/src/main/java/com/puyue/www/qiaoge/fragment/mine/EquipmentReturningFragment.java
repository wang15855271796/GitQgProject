package com.puyue.www.qiaoge.fragment.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.EquipmentReturningAdapter;
import com.puyue.www.qiaoge.api.mine.order.MyOrderListAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/11.
 */

public class EquipmentReturningFragment extends BaseFragment {

    private PtrClassicFrameLayout mPtr;
    private RecyclerView mRv;

    private List<MyOrdersModel.DataBean.ListBean> mListData = new ArrayList<>();
    private EquipmentReturningAdapter mAdapterEquipmentReturning;

    private int orderStatus = 9;//归还中
    private MyOrdersModel mModelEquipmentReturning;
    private ImageView mIvNoData;
private int orderDeliveryType;
    @Override
    public int setLayoutId() {
        return R.layout.fragment_equipmeng_returning;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void findViewById(View view) {
        mPtr = ((PtrClassicFrameLayout) view.findViewById(R.id.ptr_equipment_returning));
        mRv = ((RecyclerView) view.findViewById(R.id.rv_equipment_returning));
        mIvNoData = ((ImageView) view.findViewById(R.id.iv_equipment_returning_no_data));
    }

    @Override
    public void setViewData() {
        if (UserInfoHelper.getDeliverType(mActivity)!=null&&StringHelper.notEmptyAndNull(UserInfoHelper.getDeliverType(mActivity))){
            orderDeliveryType=Integer.parseInt(UserInfoHelper.getDeliverType(mActivity));
        }
        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                requestOrdersList(9);
            }
        });
        mAdapterEquipmentReturning = new EquipmentReturningAdapter(R.layout.item_equipment_returning, mListData);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    mPtr.setEnabled(false);
                } else {
                    mPtr.setEnabled(true);
                }
            }
        });
        mRv.setAdapter(mAdapterEquipmentReturning);
        requestOrdersList(9);
    }

    @Override
    public void setClickEvent() {

    }

    /**
     * 获取数据
     */
    private void requestOrdersList(int orderStatus) {
        MyOrderListAPI.requestOrderList(getContext(), orderStatus, 1, 10,orderDeliveryType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrdersModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyOrdersModel myOrdersModel) {
                        mPtr.refreshComplete();
                        logoutAndToHome(getContext(), myOrdersModel.code);
                        mModelEquipmentReturning = myOrdersModel;
                        if (mModelEquipmentReturning.success) {
                            updateList();
                        } else {
                            AppHelper.showMsg(getContext(), mModelEquipmentReturning.message);
                        }
                    }
                });
    }

    private void updateList() {
        if (mModelEquipmentReturning.data.list != null && mModelEquipmentReturning.data.list.size() > 0) {
            mRv.setVisibility(View.VISIBLE);
            mIvNoData.setVisibility(View.GONE);
            mListData.clear();
            mListData.addAll(mModelEquipmentReturning.data.list);
            mAdapterEquipmentReturning.notifyDataSetChanged();
        } else {
            mRv.setVisibility(View.GONE);
            mIvNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            requestOrdersList(9);
        }
    }
}
