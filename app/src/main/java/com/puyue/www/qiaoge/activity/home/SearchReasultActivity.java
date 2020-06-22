package com.puyue.www.qiaoge.activity.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.adapter.home.RegisterShopAdapterTwo;
import com.puyue.www.qiaoge.adapter.home.SearchReasultAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchResultAdapter;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.GetRegisterShopAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.dialog.CouponDialog;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.OnItemClickListener;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.utils.LoginUtil;
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
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/13.
 */

public class SearchReasultActivity extends BaseSwipeActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.tv_search_cancel)
    TextView tv_search_cancel;
    @BindView(R.id.tv_activity_result)
    TextView tv_activity_result;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    SearchReasultAdapter searchReasultAdapter;
    String searchWord;
    int pageNum = 1;
    int pageSize = 10;
    public View view;
    private SearchResultAdapter searchResultAdapter;
    SearchResultsModel searchResultsModel;
    //搜索集合
    private List<SearchResultsModel.DataBean.SearchProdBean.ListBean> searchList = new ArrayList<>();
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_seach_reasult);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        refreshLayout.autoRefresh();
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_search_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tv_activity_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SearchStartActivity.class);
                startActivity(intent);
                finish();
            }
        });


            refreshLayout.setEnableLoadMore(false);
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    pageNum = 1;
                    searchList.clear();
                    getRecommendList(1,pageSize);
                    refreshLayout.finishRefresh();
                }
            });

            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    if(searchResultsModel.getData() != null) {
                        if(searchResultsModel.getData().getSearchProd().isHasNextPage()) {
                            pageNum++;
                            getRecommendList(pageNum, 10);
                            refreshLayout.finishLoadMore();      //加载完成
                        }else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                    }
                }
            });




                        //搜索Adapter
        searchReasultAdapter = new SearchReasultAdapter(R.layout.item_noresult_recommend, searchList, new SearchReasultAdapter.Onclick() {
            @Override
            public void addDialog() {
                if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(SearchReasultActivity.this))) {

                }else {
                    initDialog();
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(searchReasultAdapter);

    }


    @Override
    public void setViewData() {
        view = View.inflate(mContext, R.layout.item_head, null);
        searchWord = getIntent().getStringExtra(AppConstant.SEARCHWORD);
        tv_activity_result.setText(searchWord);


    }


    /**
     * 获取推荐列表
     */
    private void getRecommendList(int pageNum,int pageSize) {
        RecommendApI.requestData(mContext,searchWord,pageNum,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResultsModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchResultsModel recommendModel) {
                        if (recommendModel.isSuccess()) {
                            searchResultsModel = recommendModel;
                            if(recommendModel.getData().getSearchProd()!=null) {
                                searchList.addAll(searchResultsModel.getData().getSearchProd().getList());
                                searchReasultAdapter.notifyDataSetChanged();
                                refreshLayout.setEnableLoadMore(true);
                            }

                            if(recommendModel.getData().getRecommendProd().size()!=0) {
                                searchResultAdapter = new SearchResultAdapter(R.layout.item_noresult_recommend, recommendModel.getData().getRecommendProd(), new SearchResultAdapter.Onclick() {
                                    @Override
                                    public void addDialog() {
                                        if (StringHelper.notEmptyAndNull(UserInfoHelper.getUserId(SearchReasultActivity.this))) {

                                        }else {
                                            initDialog();
                                        }
                                    }
                                });
                                searchResultAdapter.addHeaderView(view);
                                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                                recyclerView.setAdapter(searchResultAdapter);
                            }



                        } else {
                            AppHelper.showMsg(mContext, recommendModel.getMessage());
                        }
                    }
                });
    }
    CouponDialog couponDialog;
    private void initDialog() {
        couponDialog = new CouponDialog(mActivity) {
            @Override
            public void Login() {
                startActivity(LoginActivity.getIntent(mActivity, LoginActivity.class));
                dismiss();
            }

            @Override
            public void Register() {
                LoginUtil.initRegister(getContext());
                dismiss();
            }
        };
        couponDialog.show();
    }

    @Override
    public void setClickEvent() {

    }
}
