package com.puyue.www.qiaoge.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.RegisterActivity;
import com.puyue.www.qiaoge.activity.mine.login.RegisterMessageActivity;
import com.puyue.www.qiaoge.api.home.ProductListAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.dialog.CouponDialog;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
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
public class CommonFragment extends BaseFragment {
    private Unbinder bind;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    CommonListAdapter commonListAdapter;
    int pageNum = 1;
    int pageSize = 10;
    ProductNormalModel productNormalModel;
    String flag = "commonBuy";
    View emptyView;
    CouponDialog couponDialog;
    //新品集合
    private List<ProductNormalModel.DataBean.ListBean> list = new ArrayList<>();

    public static CommonFragment getInstance() {
        CommonFragment fragment = new CommonFragment();
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
        commonListAdapter = new CommonListAdapter(flag,R.layout.item_team_list, list, new CommonListAdapter.Onclick() {
            @Override
            public void addDialog() {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(mActivity))) {

                }else {
                    initDialog();
                }

            }
        });
        emptyView = View.inflate(mActivity, R.layout.layout_empty, null);
        commonListAdapter.setEmptyView(emptyView);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity,2));
        recyclerView.setAdapter(commonListAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                list.clear();
                commonListAdapter.notifyDataSetChanged();
                getProductsList(1,pageSize,"commonBuy");
                refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (productNormalModel.getData()!=null) {
                    if(productNormalModel.getData().isHasNextPage()) {
                        pageNum++;
                        getProductsList(pageNum, 10,"commonBuy");
                        refreshLayout.finishLoadMore();
                    }else {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
            }
        });
    }


    /**
     * 常用清单列表(王涛)
     * @param pageNums
     * @param pageSize
     * @param
     */

    private void getProductsList(int pageNums, int pageSize, String type) {
        Log.d("dsdfddsss.........",type);
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
                            if(getCommonProductModel.getData().getList().size()>0) {
                                list.addAll(getCommonProductModel.getData().getList());
                                commonListAdapter.notifyDataSetChanged();
                            }
                        }else {
                            AppHelper.showMsg(mActivity,getCommonProductModel.getMessage());
                        }

                        refreshLayout.setEnableLoadMore(true);
                    }
                });
    }

    @Override
    public void findViewById(View view) {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCommon(BackEvent event) {
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
