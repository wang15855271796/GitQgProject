package com.puyue.www.qiaoge.activity.mine.coupons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ChooseCouponsAdapter;
import com.puyue.www.qiaoge.api.home.GetCommentListByPageAPI;
import com.puyue.www.qiaoge.api.mine.coupon.userChooseDeductAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.ActivityResultHelper;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.GetCommentListByPageModel;
import com.puyue.www.qiaoge.model.mine.coupons.UserChooseDeductModel;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author daff
 * @date 2018/9/23.
 * 备注  选择优惠劵
 */
public class ChooseCouponsActivity extends BaseSwipeActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayout data;
    private LinearLayout noData;
    private String proActAmount;
    private String teamAmount;
    private String killAmount;
    private String prodAmount;
    private String giftDetailNo="";

    private ChooseCouponsAdapter adapter;

    private List<UserChooseDeductModel.DataBean.AllBean> list = new ArrayList<>();


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.acticity_choose_coupons);
    }

    @Override
    public void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        data = (LinearLayout) findViewById(R.id.data);
        noData = (LinearLayout) findViewById(R.id.noData);
    }

    @Override
    public void setViewData() {
        proActAmount = getIntent().getStringExtra("proActAmount");
        teamAmount = getIntent().getStringExtra("teamAmount");
        killAmount = getIntent().getStringExtra("killAmount");
        prodAmount = getIntent().getStringExtra("prodAmount");
        giftDetailNo = getIntent().getStringExtra("giftDetailNo");

        userChooseDeduct();
        setRecyclerView();
    }

    private void setRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new ChooseCouponsAdapter(R.layout.item_choose_copons, list, new ChooseCouponsAdapter.ImageOnclick() {

            @Override
            public void Onclick(int position, String giftDetailNo) {
                UserChooseDeductModel.DataBean.AllBean info = list.get(position);
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        list.get(i).setFlag(!list.get(i).isFlag());

                        if (list.get(i).isFlag()) {
                            Intent intent = new Intent();
                            intent.putExtra("giftDetailNo", info.getGiftDetailNo());
                            setResult(ActivityResultHelper.ChOOSE_COUPONS_RESULT_CODE, intent);
                            finish();
                            Log.d("-选中---->","------"+info.getGiftDetailNo());
                        } else {
                            Intent intent = new Intent();
                            intent.putExtra("giftDetailNo", "");
                            setResult(ActivityResultHelper.ChOOSE_COUPONS_RESULT_CODE, intent);
                            Log.d("-没选中---->","--giftDetailNo----");
                            finish();
                        }
                    } else {
                        list.get(i).setFlag(false);
                    }
                }

                adapter.notifyDataSetChanged();

            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

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


    private void userChooseDeduct() {
        userChooseDeductAPI.requestData(mContext, proActAmount, teamAmount,
                killAmount, prodAmount,"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserChooseDeductModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserChooseDeductModel model) {
                        if (model.success) {
                            if (model.getData().getAll().size() > 0) {
                                data.setVisibility(View.VISIBLE);
                                noData.setVisibility(View.GONE);
                                list.addAll(model.getData().getAll());
                                for (int i = 0; i < list.size(); i++) {
                                    if (model.getData().getAll().get(i).getGiftDetailNo().equals(giftDetailNo)) {
                                        model.getData().getAll().get(i).setFlag(true);

                                    } else {
                                        model.getData().getAll().get(i).setFlag(false);

                                    }
                                }

                            }else {
                                data.setVisibility(View.GONE);
                                noData.setVisibility(View.VISIBLE);

                            }
                            adapter.notifyDataSetChanged();


                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }

                    }
                });
    }


}
