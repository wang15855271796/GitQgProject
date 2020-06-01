package com.puyue.www.qiaoge.activity.mine.coupons;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.adapter.coupon.MyCouponsAdapter;
import com.puyue.www.qiaoge.adapter.home.NoticeAdapter;
import com.puyue.www.qiaoge.api.home.NoticeAPI;
import com.puyue.www.qiaoge.api.mine.coupon.MyCouponsAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;

import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.NoticeListModel;
import com.puyue.www.qiaoge.model.mine.coupons.queryUserDeductByStateModel;
import com.puyue.www.qiaoge.model.mine.coupons.queryUserList;
import com.tencent.mm.opensdk.utils.Log;

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
 * Created by ${daff} on 2018/9/20
 * 我的优惠券
 */
public class MyCouponsActivity extends BaseSwipeActivity {

    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecyclerView recyclerView;
    private int pageNum = 1;
    private  LinearLayout data;
    private  LinearLayout noData;
    private Toolbar toolbar;
    private MyCouponsAdapter adapter;
    private List<queryUserDeductByStateModel.DataBean.ListBean> lists =new ArrayList<>();

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
            setContentView(R.layout.activity_my_coupons);
    }

    @Override
    public void findViewById() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        ptrClassicFrameLayout= (PtrClassicFrameLayout) findViewById(R.id.ptrClassicFrameLayout);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        data= (LinearLayout) findViewById(R.id.data);
        noData= (LinearLayout) findViewById(R.id.noData);
    }

    @Override
    public void setViewData() {
        pageNum = 1;
        requestMyCoupons();
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                requestMyCoupons();
            }
        });

        adapter = new MyCouponsAdapter(R.layout.item_my_coupons,lists,this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(UseOrNotUseActivity.getIntent(mContext, UseOrNotUseActivity.class));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    ptrClassicFrameLayout.setEnabled(false);
                } else {
                    ptrClassicFrameLayout.setEnabled(true);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                requestMyCoupons();
            }
        }, recyclerView);
    }

    @Override
    public void setClickEvent() {
        toolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
    }


    private void requestMyCoupons() {
        MyCouponsAPI.requestCoupons(mContext, pageNum, 10,"ENABLED")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<queryUserDeductByStateModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(queryUserDeductByStateModel info) {
                        ptrClassicFrameLayout.refreshComplete();

                        if (info.isSuccess()) {
                            updateNoticeList(info);
                        } else {
                            AppHelper.showMsg(mContext, info.getMessage());
                        }


                    }
                });
    }

    private void updateNoticeList(queryUserDeductByStateModel info) {

        if (pageNum == 1) {
            if (info.getData() != null && info.getData().getList().size() > 0) {
                data.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
                lists.clear();
                lists.addAll(info.getData().getList());
                adapter.notifyDataSetChanged();
            } else {
                data.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }

        } else {

            lists.addAll(info.getData().getList());
            adapter.notifyDataSetChanged();
        }
        if (info.getData().isHasNextPage()) {
            //还有下一页数据
            adapter.loadMoreComplete();
        } else {
            //没有下一页数据了
            adapter.loadMoreEnd();
        }
    }


}
