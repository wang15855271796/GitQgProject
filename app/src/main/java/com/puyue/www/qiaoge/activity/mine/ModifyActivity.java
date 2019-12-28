package com.puyue.www.qiaoge.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
public class ModifyActivity extends BaseSwipeActivity implements View.OnClickListener {
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
    @BindView(R.id.iv_back)
    ImageView iv_back;
    private String subId;
    private String inPoint;
    private String inBalance;
    private String inGift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if(getIntent().getStringExtra("subId")!=null) {
            subId = getIntent().getStringExtra("subId");
            Log.d("sswsweeeeee....",subId);


        }
        return false;
    }




    @Override
    public void setContentView() {
        setContentView(R.layout.modify);
        if(subId!=null) {
            getSubDetail();
        }
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setViewData() {

        ButterKnife.bind(this);
        iv_back.setOnClickListener(this);


        String inPoints = SharedPreferencesUtil.getString(mActivity, "inPoint");
        String inBalances = SharedPreferencesUtil.getString(mActivity, "inBalance");
        String inGifts = SharedPreferencesUtil.getString(mActivity, "inGift");
        Log.d("swddddddddds....",inPoints);
        Log.d("swdddddddddss....",inBalances);
        Log.d("swdddddddddsss....",inGifts);
//
//
        if(inPoints=="1") {
            swipe.setChecked(false);
        }else {
            swipe.setChecked(true);
        }

        if(inBalances=="1") {
            swipe1.setChecked(false);
        }else {
            swipe1.setChecked(true);
        }

        if(inGifts=="1") {
            swipe2.setChecked(false);
        }else {
            swipe2.setChecked(true);
        }

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

                String inPoint = SharedPreferencesUtil.getString(mActivity, "inPoint");
                String inBalance = SharedPreferencesUtil.getString(mActivity, "inBalance");
                String inGift = SharedPreferencesUtil.getString(mActivity, "inGift");
                editSubAccount(subId,inPoint,inBalance,inGift);

            }
        });


        getSubDetail();
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
    private void editSubAccount(String subId, String inPoints, String inBalances, String inGifts) {
        SubAccountAddAPI.editAccount(mContext, subId,inPoints,inBalances,inGifts)
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

                            finish();
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
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
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
