package com.puyue.www.qiaoge.activity.home;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.home.GetAllCommentListByPageAdapter;
import com.puyue.www.qiaoge.api.home.GetAllCommentListByPageAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.model.home.GetAllCommentListByPageModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${daff}
 * on 2018/10/2 3
 * 备注  评估详情
 */
public class EvaluationActivity extends BaseSwipeActivity {

    private RecyclerView mRvEvaluation;
    private NestedScrollView mLlEmpty;
    private GetAllCommentListByPageAdapter mAdapterEvaluation;
    private List<GetAllCommentListByPageModel.DataBean.ListBean> mListEvaluation = new ArrayList<>();
    private int productId;
    private int pageNum = 1;
    private int pageSize = 10;
    private byte businessType = 1;
    private ImageView backImageView;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_evaluation);
    }

    @Override
    public void findViewById() {
        backImageView= (ImageView) findViewById(R.id.iv_pp_input_code_back);
        mRvEvaluation = FVHelper.fv(this, R.id.rv_item_viewpager);
        mLlEmpty = FVHelper.fv(this, R.id.nsv_item_empty);
    }

    @Override
    public void setViewData() {
        productId=getIntent().getIntExtra("productId",0);
        businessType= (byte) getIntent().getIntExtra("businessType",1);

        //设置评论数据
        mAdapterEvaluation = new GetAllCommentListByPageAdapter(R.layout.item_goods_evaluation, mListEvaluation);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvEvaluation.setLayoutManager(linearLayoutManager);

        mRvEvaluation.setAdapter(mAdapterEvaluation);
        mAdapterEvaluation.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                getAllCommentList(pageNum, pageSize, productId, businessType);
            }
        }, mRvEvaluation);
        getAllCommentList(pageNum, pageSize, productId, businessType);
    }

    @Override
    public void setClickEvent() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * 获取评价
     */
    private void getAllCommentList(final int pageNum, int pageSize, int businessId, byte businessType) {
        GetAllCommentListByPageAPI.requestData(mContext, pageNum, pageSize, businessId, businessType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAllCommentListByPageModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetAllCommentListByPageModel model) {
                        if (model.success) {
                            if (model.data.list != null) {


                                if (pageNum == 1) {
                                    mAdapterEvaluation.setNewData(model.data.list);
                                } else {
                                    mAdapterEvaluation.addData(model.data.list);
                                }
                                if (mAdapterEvaluation.getData().size() == 0) {
                                    mRvEvaluation.setVisibility(View.GONE);
                                    mLlEmpty.setVisibility(View.VISIBLE);
                                }
                            } else {
                                mRvEvaluation.setVisibility(View.GONE);
                                mLlEmpty.setVisibility(View.VISIBLE);
                            }
                            if (model.data.hasNextPage) {
                                mAdapterEvaluation.loadMoreComplete();
                            } else {
                                mAdapterEvaluation.loadMoreEnd(false);
                            }
                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }
                    }
                });

    }


}
