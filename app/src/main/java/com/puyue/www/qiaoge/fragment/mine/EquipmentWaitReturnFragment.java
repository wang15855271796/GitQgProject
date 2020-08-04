package com.puyue.www.qiaoge.fragment.mine;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.OrderDetailActivity;
import com.puyue.www.qiaoge.adapter.mine.EquipmentWaitReturnAdapter;
import com.puyue.www.qiaoge.api.mine.order.MyOrderListAPI;
import com.puyue.www.qiaoge.api.mine.order.ReturnEquipmentOrderListByIdAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnEquipmentOrderListByIdModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//待归还有个全选和一键归还功能
public class EquipmentWaitReturnFragment extends BaseFragment {

    private RecyclerView mRv;
    private PtrClassicFrameLayout mPtr;
    private RelativeLayout mRlAllSelect;
    private CheckBox mCbAllSelect;
    private TextView mTvReturn;

    private List<MyOrdersModel.DataBean.ListBean> mListData = new ArrayList<>();
    //用于存储条目的选择状态
    private Map<Integer, Boolean> isCheck = new HashMap<>();
    private EquipmentWaitReturnAdapter mAdapterEquipmentWaitReturn;
    private boolean isAllSelected = false;

    private int orderStatus = 8;//待归还
    private MyOrdersModel mModelMyOrder;
    private List<String> equipmentId = new ArrayList<>();
    private ReturnEquipmentOrderListByIdModel mModelReturnEquipment;
    private ImageView mIvNoData;
    private LinearLayout mLlBottom;

    private int orderDeliveryType;
    @Override
    public int setLayoutId() {
        return R.layout.fragment_equipmeng_wait_return;
    }

    @Override
    public void initViews(View view) {
    }

    @Override
    public void findViewById(View view) {
        mRv = ((RecyclerView) view.findViewById(R.id.rv_equipment_wait_return));
        mPtr = ((PtrClassicFrameLayout) view.findViewById(R.id.ptr_equipment_wait_return));
        mRlAllSelect = ((RelativeLayout) view.findViewById(R.id.rl_equipment_all_select));
        mCbAllSelect = ((CheckBox) view.findViewById(R.id.cb_equipment_all_select));
        mTvReturn = ((TextView) view.findViewById(R.id.tv_equipment_return));
        mIvNoData = ((ImageView) view.findViewById(R.id.iv_equipment_wait_return_no_data));
        mLlBottom = ((LinearLayout) view.findViewById(R.id.ll_equipment_wait_return_bottom));
    }

    @Override
    public void setViewData() {
        if (UserInfoHelper.getDeliverType(mActivity)!=null&&StringHelper.notEmptyAndNull(UserInfoHelper.getDeliverType(mActivity))){
            orderDeliveryType=Integer.parseInt(UserInfoHelper.getDeliverType(mActivity));
        }
        //请求未归还设备的数据,这是分页的,先获取到设备列表,然后在代码中创建size相同的"选择状态"列表
        //并且注意,在刷新和加载新数据的时候,都要重新构造"选择状态"的列表
        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                requestOrdersList(8);
            }
        });
        mAdapterEquipmentWaitReturn = new EquipmentWaitReturnAdapter(R.layout.item_equipment_wait_return, mListData, isCheck);
        mAdapterEquipmentWaitReturn.setOnItemClickListener(new EquipmentWaitReturnAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(View view, int position, String type) {
                if (type.equals("check")) {
                    if (isCheck.get(position)) {
                        //如果取消，则设置map集合中为false
                        isCheck.put(position, false);
                    } else {
                        //如果选中，则设置map集合中为true
                        isCheck.put(position, true);
                    }
                    mAdapterEquipmentWaitReturn.notifyDataSetChanged();
                } else if (type.equals("jump")) {
                    Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                    intent.putExtra(AppConstant.ORDERID, mListData.get(position).orderId);
                    intent.putExtra(AppConstant.ORDERSTATE, "");
                    intent.putExtra(AppConstant.RETURNPRODUCTMAINID, "");
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onEventLongClick(View view, int position, String type) {

            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mPtr.setEnabled(topRowVerticalPosition >= 0);
            }
        });
        mRv.setAdapter(mAdapterEquipmentWaitReturn);
        requestOrdersList(8);
    }

    @Override
    public void setClickEvent() {
        mRlAllSelect.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (isAllSelected) {
                    //正在被全选
                    mCbAllSelect.setChecked(false);
                    for (int i = 0; i < isCheck.size(); i++) {
                        isCheck.put(i, false);
                    }
                    mAdapterEquipmentWaitReturn.notifyDataSetChanged();
                    isAllSelected = false;
                } else {
                    //没有全选中
                    mCbAllSelect.setChecked(true);
                    for (int i = 0; i < isCheck.size(); i++) {
                        isCheck.put(i, true);
                    }
                    mAdapterEquipmentWaitReturn.notifyDataSetChanged();
                    isAllSelected = true;
                }
            }
        });
        mTvReturn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                equipmentId.clear();
                for (int i = 0; i < isCheck.size(); i++) {
                    if (isCheck.get(i)) {
                        //这个商品被选中
                        equipmentId.add("\"" + mListData.get(i).orderId + "\"");
                    }
                }
                if (equipmentId != null && equipmentId.size() > 0) {
                    //有设备被选中
                    returnEquipmentOrder(equipmentId.toString());
                } else {
                    AppHelper.showMsg(mActivity, "请先选中设备");
                }
            }
        });
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
                        mModelMyOrder = myOrdersModel;
                        if (mModelMyOrder.success) {
                            //获取到当前设备的数据
                            updateEquipmentWaitReturn();
                        } else {
                            AppHelper.showMsg(getContext(), mModelMyOrder.message);
                        }
                    }
                });
    }

    private void updateEquipmentWaitReturn() {
        if (mModelMyOrder.data.list != null && mModelMyOrder.data.list.size() > 0) {
            mRv.setVisibility(View.VISIBLE);
            mLlBottom.setVisibility(View.VISIBLE);
            mIvNoData.setVisibility(View.GONE);
            mListData.clear();
            mListData.addAll(mModelMyOrder.data.list);
            //默认所有设备都是不被选中的状态
            isCheck.clear();
            for (int i = 0; i < mListData.size(); i++) {
                isCheck.put(i, false);
            }
            mAdapterEquipmentWaitReturn.notifyDataSetChanged();
        } else {
            mRv.setVisibility(View.GONE);
            mLlBottom.setVisibility(View.GONE);
            mIvNoData.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设备归还接口
     */
    private void returnEquipmentOrder(String orderIds) {
        ReturnEquipmentOrderListByIdAPI.requestData(getActivity(), orderIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnEquipmentOrderListByIdModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        Toast.makeText(mActivity, "错误");
                    }

                    @Override
                    public void onNext(ReturnEquipmentOrderListByIdModel returnEquipmentOrderListByIdModel) {
                        mModelReturnEquipment = returnEquipmentOrderListByIdModel;
                        if (mModelReturnEquipment.success) {
                            AppHelper.showMsg(mActivity, "设备归还成功");
                            //刷新数据
                            mPtr.autoRefresh();
                        } else {
                           AppHelper.showMsg(mActivity, mModelReturnEquipment.message);
                        }
                    }
                });
    }
}
