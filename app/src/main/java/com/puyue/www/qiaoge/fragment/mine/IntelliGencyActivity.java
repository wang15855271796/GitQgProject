package com.puyue.www.qiaoge.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.subaccount.MineAccountAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.mine.order.IntellGencyModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/12/30
 */
public class IntelliGencyActivity extends BaseSwipeActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<IntellGencyModel.DataBean> data;
    List<IntellGencyModel.DataBean> list = new ArrayList<>();
    private IntelliGencyAdapter intelliGencyAdapter;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_intelligency);


    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);

        intelliGencyAdapter = new IntelliGencyAdapter(R.layout.item_intellgency,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(intelliGencyAdapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getList();
    }

    /**
     * 获取资质列表
     */
    private void getList() {
        MineAccountAPI.getData(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IntellGencyModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IntellGencyModel intellGencyModel) {
                        list.clear();
                        if (intellGencyModel.isSuccess()) {
                            data = intellGencyModel.getData();
                            list.addAll(data);
                            intelliGencyAdapter.notifyDataSetChanged();
                        } else {
                            AppHelper.showMsg(mActivity, intellGencyModel.getMessage());
                        }
                    }
                });
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
}
