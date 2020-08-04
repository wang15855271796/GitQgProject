package com.puyue.www.qiaoge.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.MySubOrderActivity;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.ReturnGoodDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.SelfSufficiencyOrderDetailActivity;
import com.puyue.www.qiaoge.adapter.SubAccountListAdapter;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountAddAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountListAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.MessageDialog;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.event.MessageEvent;
import com.puyue.www.qiaoge.event.SubAccountListModel;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.AccountDetailModel;
import com.puyue.www.qiaoge.model.mine.MessageModel;
import com.puyue.www.qiaoge.model.mine.SubAccountModel;
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
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/4/9
 * 子账户订单列表
 */
public class SubAccountListActivity extends BaseSwipeActivity implements View.OnClickListener{
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_read)
    TextView tv_read;
    int pageSize = 10;
    int pageNum = 1;
    List<SubAccountListModel.DataBean.ListBean> lists = new ArrayList<>();
    private SubAccountListAdapter subAccountAdapter;
    SubAccountListModel subAccountListModels;
    View emptyView;
    private MessageDialog messageDialog;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.sub_account_list);
    }

    @Override
    public void findViewById() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setViewData() {
        ButterKnife.bind(this);
        getSubAccountList(pageNum,pageSize);
        EventBus.getDefault().register(this);
        emptyView = View.inflate(mActivity, R.layout.layout_empty, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        subAccountAdapter = new SubAccountListAdapter(R.layout.item_sub_account_order,lists);
        recyclerView.setAdapter(subAccountAdapter);
        subAccountAdapter.setEmptyView(emptyView);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                lists.clear();
                getSubAccountList(pageNum,pageSize);
                subAccountAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (subAccountListModels.getData()!=null) {
                    if(subAccountListModels.getData().isHasNextPage()) {
                        pageNum++;
                        getSubAccountList(pageNum,pageSize);
                        refreshLayout.finishLoadMore();
                    }else {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
                refreshLayout.finishLoadMore();

            }
        });

        tv_read.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }



    /**
     * 获取子账户消息列表
     */
    private void getSubAccountList(int pageNum,int pageSize) {
        SubAccountAddAPI.listAccount(mContext, pageNum,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SubAccountListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SubAccountListModel subAccountListModel) {
                        if (subAccountListModel.isSuccess()) {
                            if(subAccountListModel.getData()!=null&&subAccountListModel.getData().getList().size()>0) {
                                subAccountListModels = subAccountListModel;
                                List<SubAccountListModel.DataBean.ListBean> list = subAccountListModel.getData().getList();
                                lists.addAll(list);
                                subAccountAdapter.notifyDataSetChanged();
                            }
                        } else {
                            AppHelper.showMsg(mContext, subAccountListModel.getMessage());
                        }
                    }
                });
    }

    @Override
    public void setClickEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_read:
                messageDialog = new MessageDialog(mContext) {
                    @Override
                    public void Confirm() {
                        getReadStat();
                    }

                    @Override
                    public void Cancle() {
                        dismiss();
                    }
                };
                messageDialog.show();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 全部已读
     */
    private void getReadStat() {
        SubAccountAddAPI.read(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.success) {
                            AppHelper.showMsg(mContext, baseModel.message);
                            EventBus.getDefault().post(new MessageEvent());
                            subAccountAdapter.notifyDataSetChanged();
                            refreshLayout.autoRefresh();
                            messageDialog.dismiss();

                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updates(BackEvent backEvent) {
        refreshLayout.autoRefresh();
    }

}
