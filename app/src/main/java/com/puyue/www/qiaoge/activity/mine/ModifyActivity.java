package com.puyue.www.qiaoge.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountAddAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.AccountDetailModel;

import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/12/18
 */
public class ModifyActivity extends BaseSwipeActivity {
    @BindView(R.id.swipe)
    SwitchCompat swipe;
    @BindView(R.id.swipe1)
    SwitchCompat swipe1;
    @BindView(R.id.swipe2)
    SwitchCompat swipe2;
    @BindView(R.id.tv_commit)
    TextView tv_commit;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    private String subId;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.modify);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {
        ButterKnife.bind(this);
        subId = getIntent().getStringExtra("subId");

        SharedPreferencesUtil.getString(mActivity,"inPoint");
        SharedPreferencesUtil.getString(mActivity,"inBalance");
        SharedPreferencesUtil.getString(mActivity,"inGift");

        getSubDetail();

        swipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    SharedPreferencesUtil.saveString(mActivity,"inPoint","0");
                }else {
                    SharedPreferencesUtil.saveString(mActivity,"inPoint","1");
                }
            }
        });


        swipe1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    SharedPreferencesUtil.saveString(mActivity,"inBalance","0");
                }else {
                    SharedPreferencesUtil.saveString(mActivity,"inBalance","1");
                }
            }
        });

        swipe2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    SharedPreferencesUtil.saveString(mActivity,"inGift","0");
                }else {
                    SharedPreferencesUtil.saveString(mActivity,"inGift","1");
                }
            }
        });

        tv_commit.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                editSubAccount(subId,SharedPreferencesUtil.getString(mActivity,"inPonit")
                        ,SharedPreferencesUtil.getString(mActivity,"inBalance"),SharedPreferencesUtil.getString(mActivity,"inGift"));

            }
        });

    }

    /**
     * 获取子账户详情
     */
    private void getSubDetail() {
        SubAccountAddAPI.getSubAccount(mContext, subId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccountDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AccountDetailModel baseModel) {
                        if (baseModel.isSuccess()) {
                            tv_name.setText(baseModel.getData().getName());
                            tv_phone.setText(baseModel.getData().getPhone());
                        } else {
                            AppHelper.showMsg(mContext, baseModel.getMessage());
                        }
                    }
                });
    }

    /**
     *编辑子账户
     */
    private void editSubAccount(String subId, String inPonit, String inBalance, String inGift) {
        SubAccountAddAPI.editAccount(mContext, subId,inPonit,inBalance,inGift)
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
                            AppHelper.showMsg(mContext, "修改成功");
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });
    }

    @Override
    public void setClickEvent() {

    }
}
