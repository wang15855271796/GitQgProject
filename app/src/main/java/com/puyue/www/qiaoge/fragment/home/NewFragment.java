package com.puyue.www.qiaoge.fragment.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dcloud.android.annotation.NonNull;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.RegisterActivity;
import com.puyue.www.qiaoge.activity.mine.login.RegisterMessageActivity;
import com.puyue.www.qiaoge.adapter.home.CommonsAdapter;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.ProductListAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.CouponDialog;
import com.puyue.www.qiaoge.event.BackEvent;
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
import com.puyue.www.qiaoge.utils.LoginUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/1/5
 */
public class NewFragment extends BaseFragment {

    private Unbinder bind;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    CommonsAdapter adapterNewArrival;
    public int pageNum = 1;
    ProductNormalModel productNormalModel;
    private String cell; // 客服电话
    private AlertDialog mTypedialog;
    private boolean isFirst = true;
    int isSelected;
    boolean isChecked = false;
    int shopTypeId;
    String flag = "new";
    View emptyView;
    CouponDialog couponDialog;
    //新品集合
    private List<ProductNormalModel.DataBean.ListBean> list = new ArrayList<>();
    private NewAdapter newAdapter;

    public static NewFragment getInstance() {
        NewFragment fragment = new NewFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int setLayoutId() {
        return R.layout.list;
    }


    @Override
    public void initViews(View view) {

        if(!EventBus.getDefault().isRegistered(this)) {//加上判断
            EventBus.getDefault().register(this);
        }
        bind = ButterKnife.bind(this, view);
        refreshLayout.setEnableLoadMore(false);
        emptyView = View.inflate(mActivity, R.layout.layout_empty, null);
        getProductsList(pageNum,11,"new");
        newAdapter = new NewAdapter(flag,R.layout.item_team_list, list, new NewAdapter.Onclick() {
            @Override
            public void addDialog() {
                if(StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {

                }else {
                    initDialog();
                }

            }
        });

        recyclerView.setLayoutManager(new MyGrideLayoutManager(mActivity,2));
        recyclerView.setAdapter(newAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                list.clear();
                getProductsList(1,10,"new");
                newAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
                iv_back.setVisibility(View.GONE);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    if (productNormalModel.getData()!=null) {
                        if(productNormalModel.getData().isHasNextPage()) {
                            pageNum++;
                            getProductsList(pageNum, 10,"new");
                            refreshLayout.finishLoadMore();

                        }else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                    }
                refreshLayout.finishLoadMore();
                iv_back.setVisibility(View.VISIBLE);
                }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });

    }


    /**
     * 新品列表(王涛)
     * @param
     * @param pageSize
     * @param
     */

    private void getProductsList(int pageNums, int pageSize, String type) {

        ProductListAPI.requestData(mActivity, pageNums, pageSize,type,null)
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

                        productNormalModel = getCommonProductModel;
                        if (getCommonProductModel.isSuccess()) {
                            newAdapter.notifyDataSetChanged();
                            if(getCommonProductModel.getData().getList().size()>0) {
                                list.addAll(getCommonProductModel.getData().getList());
                                newAdapter.notifyDataSetChanged();
                            }

                            refreshLayout.setEnableLoadMore(true);

                        }
                        else {
                            AppHelper.showMsg(mActivity, getCommonProductModel.getMessage());
                        }
                    }
                });
    }

    @Override
    public void findViewById(View view) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBusss(BackEvent event) {
        refreshLayout.autoRefresh();
    }

    @Override
    public void setViewData() {
    }

    @Override
    public void setClickEvent() {

    }

    /**
     * 提示用户去登录还是注册的弹窗
     */
    private void initDialog() {
        couponDialog = new CouponDialog(mActivity) {
            @Override
            public void Login() {
                startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                dismiss();
            }

            @Override
            public void Register() {
                startActivity(RegisterActivity.getIntent(mActivity, RegisterMessageActivity.class));
                LoginUtil.initRegister(mActivity);
                dismiss();
            }
        };
        couponDialog.show();
    }
}
