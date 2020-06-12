package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.adapter.CouponListAdapter;
import com.puyue.www.qiaoge.api.home.IndexHomeAPI;
import com.puyue.www.qiaoge.api.home.QueryHomePropupAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.event.CouponListModel;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;
import com.puyue.www.qiaoge.popupwindow.HomePopuWindow;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/4/17
 */
public class CouponListDialog extends Dialog {
    Context mContext;
    public RecyclerView recyclerView;
    ImageView iv_close;
    TextView tv_use;
    private LinearLayout rootview;
    CouponListModel couponListModel;
    private CouponListAdapter couponListAdapter;
    List<CouponListModel.DataBean.GiftsBean> lists;
    public CouponListDialog(@NonNull Context context, CouponListModel couponListModel, List<CouponListModel.DataBean.GiftsBean> lists) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.dialog_coupon_list);
        mContext = context;
        this.lists= lists;
        this.couponListModel = couponListModel;
        initView();
        initAction();

    }

    private void initView() {
        tv_use = (TextView) findViewById(R.id.tv_use);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        couponListAdapter = new CouponListAdapter(R.layout.item_home_coupon_list,lists);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(couponListAdapter);
        tv_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, HomeActivity.class));
                EventBus.getDefault().post(new GoToMarketEvent());
                getClose();
            }
        });
    }

    /**
     * 关闭优惠券弹窗
     */
    private void getClose() {
        IndexHomeAPI.getCouponClose(mContext,couponListModel.getData().getId()+"")
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
                        if(baseModel.success) {
                            dismiss();
                            QueryHomePropup();
                        }else {
                            AppHelper.showMsg(mContext,baseModel.message);
                        }
                    }
                });
    }

    private void initAction() {

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClose();
            }
        });
    }

    // 首页弹窗
    private void QueryHomePropup() {
        QueryHomePropupAPI.requestQueryHomePropup(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QueryHomePropupModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QueryHomePropupModel queryHomePropupModel) {
                        if (queryHomePropupModel.isSuccess()) {
                            QueryHomePropupModel.DataBean.HomePropupBean homePropup = queryHomePropupModel.getData().getHomePropup();
                            HomeActivityDialog homeActivityDialog = new HomeActivityDialog(mContext,homePropup);

                            if (queryHomePropupModel.getData().isPropup()) {
                                homeActivityDialog.show();
                            }else {
//                                QueryHomePropup();
                            }
                        } else {
                            AppHelper.showMsg(mContext, queryHomePropupModel.getMessage());
                        }
                    }
                });
    }
}
