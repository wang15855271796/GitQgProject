package com.puyue.www.qiaoge.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.adapter.home.NoticeAdapter;
import com.puyue.www.qiaoge.api.home.NoticeAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;

import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.NoticeListModel;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Request;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/9.
 */

public class NoticeListActivity extends BaseSwipeActivity {

    private Toolbar mToolbar;
    private PtrClassicFrameLayout mPtr;
    private RecyclerView mRv;
    private ImageView mIvNoData;

    private int pageNum = 1;

    private NoticeAdapter mAdapterNotice;
    private List<NoticeListModel.DataBean.ListBean> mList = new ArrayList<>();
    private NoticeListModel mModelNoticeList;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_notice_list);
    }

    @Override
    public void findViewById() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_notice_list);
        mPtr = (PtrClassicFrameLayout) findViewById(R.id.ptr_notice_list);
        mRv = (RecyclerView) findViewById(R.id.rv_notice_list);
        mIvNoData = (ImageView) findViewById(R.id.iv_notice_list_no_data);
    }

    @Override
    public void setViewData() {
        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                requestNoticeList();
            }
        });
        mAdapterNotice = new NoticeAdapter(R.layout.item_notice, mList);
        mAdapterNotice.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringHelper.notEmptyAndNull(mList.get(position).url)) {
                    startActivity(CommonH5Activity.getIntent(mContext, CommonH5Activity.class, mList.get(position).url));
                }
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
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
        mRv.setAdapter(mAdapterNotice);
        mAdapterNotice.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                requestNoticeList();
            }
        }, mRv);
        pageNum = 1;
        requestNoticeList();
    }

    private void requestNoticeList() {
        NoticeAPI.requestNotice(mContext, pageNum, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NoticeListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NoticeListModel noticeListModel) {
                        mPtr.refreshComplete();
                        mModelNoticeList = noticeListModel;
                        if (mModelNoticeList.success) {


                            updateNoticeList();
                        } else {
                            AppHelper.showMsg(mContext, mModelNoticeList.message);
                        }
                    }
                });
    }

    private void updateNoticeList() {
        if (pageNum == 1) {
            if (mModelNoticeList.data.list != null && mModelNoticeList.data.list.size() > 0) {
                mRv.setVisibility(View.VISIBLE);
                Log.i("wwaaa", "updateNoticeList: "+mModelNoticeList.data.list);
                mIvNoData.setVisibility(View.GONE);
                mList.clear();
                mList.addAll(mModelNoticeList.data.list);
                mAdapterNotice.notifyDataSetChanged();
            } else {
                mRv.setVisibility(View.GONE);
                mIvNoData.setVisibility(View.VISIBLE);
            }
        } else {
            mList.addAll(mModelNoticeList.data.list);
            mAdapterNotice.notifyDataSetChanged();
        }
        if (mModelNoticeList.data.hasNextPage) {
            //还有下一页数据
            mAdapterNotice.loadMoreComplete();
        } else {
            //没有下一页数据了
            mAdapterNotice.loadMoreEnd();
        }
    }

    @Override
    public void setClickEvent() {
        mToolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
    }
}
