package com.puyue.www.qiaoge.adapter.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.ProductListAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/5
 * 降价
 */
public class ReductionProductActivity extends BaseSwipeActivity implements View.OnClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    CommonAdapter commonAdapter;
    int pageNum = 1;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    int pageSize = 10;
    @BindView(R.id.tv_title)
    TextView tv_title;
    ProductNormalModel productNormalModel;
    private String cell; // 客服电话
    private AlertDialog mTypedialog;
    private boolean isFirst = true;
    int isSelected;
    boolean isChecked = false;
    int shopTypeId;

    //降价集合
    private List<ProductNormalModel.DataBean.ListBean> list = new ArrayList<>();
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.new_product);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLayout.autoRefresh();
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        setTranslucentStatus();
        commonAdapter = new CommonAdapter(R.layout.item_noresult_recommend, list, new CommonAdapter.Onclick() {
            @Override
            public void addDialog() {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mContext))) {
                    if(UserInfoHelper.getUserType(mContext).equals(AppConstant.USER_TYPE_RETAIL)) {
                        if (StringHelper.notEmptyAndNull(cell)) {
                            AppHelper.showAuthorizationDialog(mContext, cell, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (StringHelper.notEmptyAndNull(AppHelper.getAuthorizationCode()) && AppHelper.getAuthorizationCode().length() == 6) {
                                        AppHelper.hideAuthorizationDialog();
//                                        if (UserInfoHelper.getIsregister(mActivity) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getIsregister(mActivity))) {
                                            showSelectType(AppHelper.getAuthorizationCode());
//                                        }
                                    } else {
                                        AppHelper.showMsg(mContext, "请输入完整授权码");
                                    }
                                }
                            });
                        }
                    }
                }else {
                    AppHelper.showMsg(mContext, "请先登录");
                    mContext.startActivity(LoginActivity.getIntent(mContext, LoginActivity.class));
                }

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(commonAdapter);
        iv_back.setOnClickListener(this);
        tv_title.setText("降价商品");
        getProductsList(pageNum,pageSize,"reduct");


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                list.clear();
                getProductsList(1,pageSize,"reduct");
                refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (productNormalModel.getData()!=null) {
                    if(productNormalModel.getData().isHasNextPage()) {
                        pageNum++;
                        getProductsList(pageNum, 10,"reduct");
                        refreshLayout.finishLoadMore();


                    }else {
                        refreshLayout.finishLoadMoreWithNoMoreData();

                    }
                }
            }
        });

    }

    /**
     * 获取店铺类型
     * @param authorizationCode
     */
    private void showSelectType(String authorizationCode) {
        GetRegisterShopAPI.requestData(mActivity, authorizationCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetRegisterShopModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ccca", e.getMessage());
                    }

                    @Override
                    public void onNext(GetRegisterShopModel getRegisterShopModel) {
                        UserInfoHelper.saveIsRegister(mActivity, "is_register_type");
                        if (getRegisterShopModel.isSuccess()) {
                            isFirst = true;
                            List<GetRegisterShopModel.DataBean> mList = new ArrayList<>();
                            mList.addAll(getRegisterShopModel.getData());
                            mTypedialog.show();
                            Window window = mTypedialog.getWindow();
                            window.setContentView(R.layout.select_type);
                            WindowManager.LayoutParams attributes = window.getAttributes();
                            attributes.width = LinearLayout.LayoutParams.MATCH_PARENT;
                            attributes.height = LinearLayout.LayoutParams.MATCH_PARENT;
                            window.setAttributes(attributes);
                            RecyclerView rl_type = window.findViewById(R.id.rl_type);
                            TextView tv_ok = window.findViewById(R.id.tv_ok);
                            rl_type.setLayoutManager(new GridLayoutManager(mActivity, 3));
                            RegisterShopAdapterTwo mRegisterAdapterType = new RegisterShopAdapterTwo(mActivity, mList);
                            rl_type.setAdapter(mRegisterAdapterType);
                            mRegisterAdapterType.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    isSelected = position;
                                    mRegisterAdapterType.selectPosition(position);

                                    shopTypeId = mList.get(isSelected).getId();
                                    isChecked = true;
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });

                            tv_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (isChecked) {
                                        mTypedialog.dismiss();
                                        updateUserInvitation(authorizationCode, shopTypeId);
                                    } else {
                                        AppHelper.showMsg(mActivity, "请选择店铺类型");
                                    }
                                }
                            });
                        }
                    }
                });
    }

    /**
     * 填写邀请码
     * @param authorizationCode
     * @param shopTypeId
     */
    private void updateUserInvitation(String authorizationCode, int shopTypeId) {
        UpdateUserInvitationAPI.requestData(mActivity, authorizationCode,shopTypeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateUserInvitationModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateUserInvitationModel updateUserInvitationModel) {
                        if (updateUserInvitationModel.isSuccess()) {
                            UserInfoHelper.saveUserType(mActivity, AppConstant.USER_TYPE_WHOLESALE);
                            UserInfoHelper.saveUserId(mActivity, updateUserInvitationModel.getData());
                            pageNum = 1;
                            getProductsList(pageNum,10,"reduct");
                            UserInfoHelper.saveUserHomeRefresh(mContext, "home_has_refresh");
                        } else {
                            AppHelper.showMsg(mActivity, updateUserInvitationModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 常用清单(王涛)
     * @param pageNum
     * @param pageSize
     * @param
     */

    private void getProductsList(int pageNum, int pageSize, String type) {
        ProductListAPI.requestData(mContext, pageNum, pageSize,type,null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductNormalModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProductNormalModel getCommonProductModel) {
                        if (getCommonProductModel.isSuccess()) {
                            productNormalModel = getCommonProductModel;
                            if (getCommonProductModel.getData().getList().size() > 0) {
                                list.addAll(getCommonProductModel.getData().getList());
                                commonAdapter.notifyDataSetChanged();
                                List<ProductNormalModel.DataBean.ListBean> list = getCommonProductModel.getData().getList();
                                if (pageNum == 1) {
                                    commonAdapter.setNewData(list);
                                } else {
                                    commonAdapter.addData(list);
                                }
                            }
                            //判断是否有下一页
                            if (!getCommonProductModel.getData().isHasNextPage()) {
                                commonAdapter.loadMoreEnd(false);
                            } else {
                                commonAdapter.loadMoreComplete();
                            }

                        } else {
                            AppHelper.showMsg(mActivity, getCommonProductModel.getMessage());
                        }
                    }
                });
    }

    @Override
    public void setViewData() {


        getCustomerPhone();
        mTypedialog = new AlertDialog.Builder(mActivity, R.style.DialogStyle).create();
        mTypedialog.setCancelable(false);
    }

    /**
     * 获取客服电话
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

    @Override
    public void setClickEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
