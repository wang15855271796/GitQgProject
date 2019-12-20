package com.puyue.www.qiaoge.activity.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.AddressAdapter;
import com.puyue.www.qiaoge.api.mine.address.AddressListAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.AddressEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/12/20
 */
public class ChooseAddressActivity extends BaseSwipeActivity implements View.OnClickListener {
    @BindView(R.id.tv_tip)
    TextView tv_tip;
    @BindView(R.id.rl_tip)
    RelativeLayout rl_tip;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private AddressModel mModelAddress;
    TextView tv_area_default;
    List<AddressModel.DataBean> list = new ArrayList<>();
    private AddressListAdapter addressListAdapter;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.choose_activity);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        tv_tip.setOnClickListener(this);

        addressListAdapter = new AddressListAdapter(R.layout.item_address_list,list);
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        recycleView.setAdapter(addressListAdapter);

    }

    @Override
    public void setViewData() {
        requestAddressList();
    }

    /**
     * 获取地址列表
     */
    private void requestAddressList() {
        AddressListAPI.requestAddressModel(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddressModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddressModel addressModel) {
                        mModelAddress = addressModel;
                        if (mModelAddress.success) {
                            list.addAll(addressModel.data);
                            addressListAdapter.notifyDataSetChanged();

                            tv_area_default.setText(addressModel.data.get(0).isDefault);
                            EventBus.getDefault().post(new AddressEvent());
                        } else {
                            AppHelper.showMsg(mContext, mModelAddress.message);
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
            case R.id.tv_tip:
                rl_tip.setVisibility(View.GONE);
                break;
        }
    }
}
