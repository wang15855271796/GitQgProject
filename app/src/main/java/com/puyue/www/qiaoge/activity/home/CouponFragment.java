package com.puyue.www.qiaoge.activity.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.TeamActiveQueryAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
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
 * 折扣列表页
 */
public class CouponFragment extends BaseFragment {
    private boolean isFirst = true;
    private Unbinder bind;
    @BindView(R.id.recyclerView)
    RecyclerView recycleView;

    private String cell; // 客服电话
    private AlertDialog mTypedialog;
    int isSelected;
    int shopTypeId;
    //折扣集合
    List<TeamActiveQueryModel.DataBean> couponList = new ArrayList<>();
    private CouponsAdapter couponsAdapter;
    private List<TeamActiveQueryModel.DataBean.ActivesBean> actives;

    public static CouponFragment getInstance() {
        CouponFragment fragment = new CouponFragment();
        return fragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_team;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCustomerPhone();
    }

    @Override
    public void initViews(View view) {
        bind = ButterKnife.bind(this, view);
        getCustomerPhone();
        couponsAdapter = new CouponsAdapter(R.layout.item_coupon_list, couponList, new CouponsAdapter.Onclick() {
            @Override
            public void addDialog() {
                showPhoneDialog(cell);
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        recycleView.setAdapter(couponsAdapter);

//        couponsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(mActivity,SpecialGoodDetailActivity.class);
//                intent.putExtra(AppConstant.ACTIVEID,633);
//                mActivity.startActivity(intent);
//            }
//        });
        getCouponList();
//        getCustomerPhone();
    }

    /**
     * 弹出电话号码
     */
    private AlertDialog mDialog;
    TextView tv_phone;
    public void showPhoneDialog(final String cell) {
        mDialog = new AlertDialog.Builder(mActivity).create();
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_shouye_tip);
        tv_phone = mDialog.getWindow().findViewById(R.id.tv_phone);
        tv_phone.setText(cell);
        mDialog.getWindow().findViewById(R.id.tv_dialog_call_phone_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    /**
     * @param
     */

    private void getCustomerPhone() {
        PublicRequestHelper.getCustomerPhone(mActivity, new OnHttpCallBack<GetCustomerPhoneModel>() {
            @Override
            public void onSuccessful(GetCustomerPhoneModel getCustomerPhoneModel) {
                if (getCustomerPhoneModel.isSuccess()) {
                    cell = getCustomerPhoneModel.getData();
                } else {
                    AppHelper.showMsg(mActivity, getCustomerPhoneModel.getMessage());
                }
            }

            @Override
            public void onFaild(String errorMsg) {
            }
        });
    }


    /**
     * 选择店铺类型
     * @param authorizationCode
     */
//    private void showSelectType(String authorizationCode) {
//        GetRegisterShopAPI.requestData(mActivity, authorizationCode)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GetRegisterShopModel>() {
//                    @Override
//                    public void onCompleted() {
////                        ptr.refreshComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        ptr.refreshComplete();
//                    }
//
//                    @Override
//                    public void onNext(GetRegisterShopModel getRegisterShopModel) {
//                        UserInfoHelper.saveIsRegister(mActivity, "is_register_type");
//                        if (getRegisterShopModel.isSuccess()) {
//                            isFirst = true;
//                            List<GetRegisterShopModel.DataBean> mList = new ArrayList<>();
//                            mList.addAll(getRegisterShopModel.getData());
//                            mTypedialog.show();
//                            Window window = mTypedialog.getWindow();
//                            window.setContentView(R.layout.select_type);
//                            WindowManager.LayoutParams attributes = window.getAttributes();
//                            attributes.width = LinearLayout.LayoutParams.MATCH_PARENT;
//                            attributes.height = LinearLayout.LayoutParams.MATCH_PARENT;
//                            window.setAttributes(attributes);
//                            Log.i("cccao.......",attributes+"");
//                            RecyclerView rl_type = window.findViewById(R.id.rl_type);
//                            TextView tv_ok = window.findViewById(R.id.tv_ok);
//                            rl_type.setLayoutManager(new GridLayoutManager(mActivity, 3));
//                            RegisterShopAdapterTwo mRegisterAdapterType = new RegisterShopAdapterTwo(mActivity, mList);
//                            rl_type.setAdapter(mRegisterAdapterType);
//                            mRegisterAdapterType.setOnItemClickListener(new OnItemClickListener() {
//                                @Override
//                                public void onItemClick(View view, int position) {
//                                    isSelected = position;
//                                    mRegisterAdapterType.selectPosition(position);
//
//                                    shopTypeId = mList.get(isSelected).getId();
//                                    isChecked = true;
//                                }
//
//                                @Override
//                                public void onItemLongClick(View view, int position) {
//
//                                }
//                            });
//
//                            tv_ok.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if (isChecked) {
//                                        mTypedialog.dismiss();
//                                        updateUserInvitation(authorizationCode, shopTypeId);
//                                    } else {
//                                        AppHelper.showMsg(mActivity, "请选择店铺类型");
//                                    }
//                                }
//                            });
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 获取客服电话
//     */
//    private void getCustomerPhone() {
//        PublicRequestHelper.getCustomerPhone(mActivity, new OnHttpCallBack<GetCustomerPhoneModel>() {
//            @Override
//            public void onSuccessful(GetCustomerPhoneModel getCustomerPhoneModel) {
//                if (getCustomerPhoneModel.isSuccess()) {
//                    cell = getCustomerPhoneModel.getData();
//                } else {
//                    AppHelper.showMsg(mActivity, getCustomerPhoneModel.getMessage());
//                }
//            }
//
//            @Override
//            public void onFaild(String errorMsg) {
//            }
//        });
//    }

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
                                couponsAdapter.notifyDataSetChanged();

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
        mTypedialog = new AlertDialog.Builder(mActivity, R.style.DialogStyle).create();
        mTypedialog.setCancelable(false);
    }

    @Override
    public void setClickEvent() {

    }
}
