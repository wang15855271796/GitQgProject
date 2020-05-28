package com.puyue.www.qiaoge.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountAddAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.dialog.AmountMaxDialog;
import com.puyue.www.qiaoge.dialog.AmountSetDialog;
import com.puyue.www.qiaoge.event.SetAmountMaxEvent;
import com.puyue.www.qiaoge.event.SetAmountMaxsEvent;
import com.puyue.www.qiaoge.event.SetAmountsEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.AccountDetailModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.swipe3)
    SwitchCompat swipe3;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_commit)
    TextView tv_commit;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_amount_remind)
    TextView tv_amount_remind;
    @BindView(R.id.et_amount)
    TextView et_amount;
    private AmountSetDialog amountSetDialog;
    private String subId;
    private String inPoint;
    private String inBalance;
    private String inGift;
    private AmountMaxDialog amountMaxDialog;
    AccountDetailModel accountDetailModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        if(getIntent().getStringExtra("subId")!=null) {
            subId = getIntent().getStringExtra("subId");
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        tv_amount_remind.setOnClickListener(this);
        EventBus.getDefault().register(this);

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

//        swipe3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) {
//                    SharedPreferencesUtil.saveString(mActivity,"notification","0");
//                }else {
//                    SharedPreferencesUtil.saveString(mActivity,"notification","1");
//                }
//                if(isChecked) {
//                    amountSetDialog = new AmountSetDialog(mContext) {
//                        @Override
//                        public void Confirm() {
//                            amountSetDialog.dismiss();
//                        }
//                    };
//                    amountSetDialog.show();
//                }
//            }
//        });

        swipe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swipe3.isChecked()) {
                    amountSetDialog = new AmountSetDialog(mContext) {
                        @Override
                        public void Confirm() {
                            amountSetDialog.dismiss();
                        }
                    };
                    amountSetDialog.show();
                }else {
                    SharedPreferencesUtil.saveString(mContext,"notification","0");
                }
            }
        });




        et_amount.setOnClickListener(this);
        tv_commit.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                String inGift = SharedPreferencesUtil.getString(mActivity, "inGift");
                String inBalance = SharedPreferencesUtil.getString(mActivity, "inBalance");
                String inPoint = SharedPreferencesUtil.getString(mActivity, "inPoint");
                String amount_limit = SharedPreferencesUtil.getString(mActivity, "amount_limit");
                String amount = SharedPreferencesUtil.getString(mActivity, "amount");
                String notification = SharedPreferencesUtil.getString(mActivity, "notification");
                String warn_amount = SharedPreferencesUtil.getString(mActivity, "warn_amount");
                editSubAccount(subId,inPoint,inBalance,inGift,amount_limit,amount,notification,warn_amount);
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
                            accountDetailModels = baseModel;
                            tv_name.setText(baseModel.getData().getName());
                            tv_phone.setText(baseModel.getData().getPhone());
                            inPoint = String.valueOf(baseModel.getData().getInPoint());
                            inGift = String.valueOf(baseModel.getData().getInGift());
                            inBalance = String.valueOf(baseModel.getData().getInBalance());
                            if(SharedPreferencesUtil.getString(mActivity, "amount_limit").equals("0")) {
                                et_amount.setText("");
                                SharedPreferencesUtil.saveString(mContext,"amount","0");
                            }else {
                                et_amount.setText(baseModel.getData().getAmount());
                                SharedPreferencesUtil.saveString(mContext,"amount",baseModel.getData().getAmount());
                            }

                            tv_amount_remind.setText(baseModel.getData().getWarnAmount());

                            if(baseModel.getData().getNotification().equals("0")) {
                                swipe3.setChecked(false);
                            }else {
                                swipe3.setChecked(true);
                            }

                            if(baseModel.getData().getNotification().equals("0")) {
                                tv_amount_remind.setText("可设置金额提醒");
                            }else if(baseModel.getData().getNotification().equals("1")) {
                                tv_amount_remind.setText("任意金额提醒");
                            }else {
                                tv_amount_remind.setText("满"+baseModel.getData().getWarnAmount()+"提醒");
                            }

                            if(baseModel.getData().getInBalance()==1) {
                                swipe1.setChecked(false);
                            }else {
                                swipe1.setChecked(true);
                            }

                            if(baseModel.getData().getInGift()==1) {
                                swipe2.setChecked(false);
                            }else {
                                swipe2.setChecked(true);
                            }

                            if(baseModel.getData().getInPoint()==1) {
                                swipe.setChecked(false);
                            }else {
                                swipe.setChecked(true);
                            }
                        } else {
                            AppHelper.showMsg(mContext, baseModel.getMessage());
                        }
                    }
                });
    }

    /**
     *编辑子账户
     */
    private void editSubAccount(String subId, String inPoints, String inBalances, String inGifts, String amount_limit, String amount, String notification, String warn_amount) {
        SubAccountAddAPI.editAccount(mContext, subId,inPoints,inBalances,inGifts,amount_limit,amount,notification,warn_amount)
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
                            ToastUtil.showSuccessMsg(mActivity,"成功");
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

            case R.id.et_amount:
                amountMaxDialog = new AmountMaxDialog(mContext) {
                    @Override
                    public void Confirm() {
                        amountMaxDialog.dismiss();
                    }

                    @Override
                    public void Cancle() {
                        dismiss();
                    }
                };

                amountMaxDialog.show();
                break;

            case R.id.tv_amount_remind:
//                amountSetDialog = new AmountSetDialog(mContext) {
//                    @Override
//                    public void Confirm() {
//                        amountSetDialog.dismiss();
//                    }
//                };
//
//                amountSetDialog.show();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAmount(SetAmountEvent event) {
        if(SharedPreferencesUtil.getString(mActivity,"flag").equals("2")) {
            if(event.amount.length()==0) {
                tv_amount_remind.setText("请添加金额");
            }else {
                tv_amount_remind.setText("满"+event.amount+"元消费提醒");
                SharedPreferencesUtil.saveString(mContext,"amount_limit","1");
                SharedPreferencesUtil.saveString(mContext,"warn_amount",event.amount);
                SharedPreferencesUtil.saveString(mContext,"notification","2");
                swipe3.setChecked(true);
            }

        }else {
            swipe3.setChecked(false);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAmounts(SetAmountsEvent event) {
        if(SharedPreferencesUtil.getString(mActivity,"flag").equals("1")) {
            tv_amount_remind.setText("任意金额提醒");
            swipe3.setChecked(true);
            SharedPreferencesUtil.saveString(mContext,"amount_limit","1");
            SharedPreferencesUtil.saveString(mContext,"warn_amount","0");
            SharedPreferencesUtil.saveString(mContext,"notification","1");

        }

        if(SharedPreferencesUtil.getString(mActivity,"flag").equals("0")) {
            tv_amount_remind.setText("添加金额提醒");
            swipe3.setChecked(false);
            SharedPreferencesUtil.saveString(mContext,"amount_limit","0");
            SharedPreferencesUtil.saveString(mContext,"notification","0");
            SharedPreferencesUtil.saveString(mContext,"warn_amount","0");
        }

    }

    //设置最大金额
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAmountss(SetAmountMaxEvent event) {
        et_amount.setText(event.amount);
        SharedPreferencesUtil.saveString(mContext,"amount",event.amount);
        SharedPreferencesUtil.saveString(mContext,"amount_limit","1");
    }

    //设置最大金额
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAmountsss(SetAmountMaxsEvent event) {
        et_amount.setText(event.amount);
        SharedPreferencesUtil.saveString(mContext,"amount","0");
        SharedPreferencesUtil.saveString(mContext,"amount_limit","0");
    }


}
