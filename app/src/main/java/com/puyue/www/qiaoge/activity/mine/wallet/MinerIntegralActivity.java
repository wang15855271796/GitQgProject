package com.puyue.www.qiaoge.activity.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.HomeUseActivity;
import com.puyue.www.qiaoge.activity.mine.order.ConfirmOrderNewActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyConfireOrdersActivity;
import com.puyue.www.qiaoge.adapter.mine.MinerIntegralAdapter;
import com.puyue.www.qiaoge.api.mine.PointApI;
import com.puyue.www.qiaoge.api.mine.order.GenerateOrderAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.model.mine.order.GenerateOrderModel;
import com.puyue.www.qiaoge.model.mine.wallet.MinerIntegralModel;

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
 * Created by ${daff}
 * on 2018/10/19
 * 备注 我的积分
 */
public class MinerIntegralActivity extends BaseSwipeActivity {

    private ImageView imageBreak;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecyclerView recyclerView;
    private MinerIntegralAdapter adapter;
    private List<MinerIntegralModel.DataBean.AppPointVOListBean> list = new ArrayList<>();
    private int pageNum=1;
    private LinearLayout linearLayoutData;
    private TextView tvIntegral;
    private ImageView pointButton;
    private String Url;



    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_mine_integral);
    }

    @Override
    public void findViewById() {
        imageBreak = (ImageView) findViewById(R.id.imageBreak);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.ptrClassicFrameLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutData = (LinearLayout) findViewById(R.id.linearLayoutData);
        tvIntegral = (TextView) findViewById(R.id.tvIntegral);
        pointButton = (ImageView) findViewById(R.id.pointButton);
    }

    @Override
    public void setViewData() {


        adapter = new MinerIntegralAdapter(R.layout.item_mine_integral, list);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                requestPoint();
            }
        }, recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        requestPoint();
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                list.clear();
                requestPoint();


            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    ptrClassicFrameLayout  .setEnabled(false);
                } else {
                    ptrClassicFrameLayout.setEnabled(true);
                }
            }
        });
    }


    // 获取积分
    private void requestPoint() {
        PointApI.requestPointService(mContext, pageNum, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MinerIntegralModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MinerIntegralModel minerIntegralModel) {
                        ptrClassicFrameLayout.refreshComplete();
                        if (minerIntegralModel.isSuccess()) {
                            updateOrderList(minerIntegralModel);
                        } else {
                            AppHelper.showMsg(mActivity, minerIntegralModel.getMessage());
                        }
                    }
                });
    }

    private void updateOrderList(MinerIntegralModel minerIntegralModel) {
        Url = minerIntegralModel.getData().getPointMallUrl();
        tvIntegral.setText(minerIntegralModel.getData().getPoint() + "");
        if (pageNum == 1) {
            //第一次加载
            Log.d("----->","------");
            if (minerIntegralModel.getData().getAppPointVOList() != null &&minerIntegralModel.getData().getAppPointVOList().size() > 0) {
                ptrClassicFrameLayout.setVisibility(View.VISIBLE);
                linearLayoutData.setVisibility(View.GONE);
                list.clear();
                list.addAll(minerIntegralModel.getData().getAppPointVOList());
                adapter.notifyDataSetChanged();

            } else {
                ptrClassicFrameLayout.setVisibility(View.GONE);
                linearLayoutData.setVisibility(View.VISIBLE);

            }
        } else {
            //加载更多数据
            list.addAll(minerIntegralModel.getData().getAppPointVOList());
            adapter.notifyDataSetChanged();

        }
        if (minerIntegralModel.getData().isNext()) {
            //有下一页数据



            adapter.loadMoreComplete();
        } else {
            //没有下一页数据了
            adapter.loadMoreEnd();
        }
    }

    @Override
    public void setClickEvent() {
        imageBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MinerIntegralActivity.this, NewWebViewActivity.class);
                intent.putExtra("URL", Url);
                intent.putExtra("TYPE",1);
                intent.putExtra("name","");
                startActivity(intent);

            }
        });
    }
}
