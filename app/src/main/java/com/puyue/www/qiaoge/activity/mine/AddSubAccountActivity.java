package com.puyue.www.qiaoge.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.login.SendCodeAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountAddAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.dialog.AmountMaxDialog;
import com.puyue.www.qiaoge.dialog.AmountSetDialog;
import com.puyue.www.qiaoge.dialog.XieYiDialog;
import com.puyue.www.qiaoge.event.BackEvent;
import com.puyue.www.qiaoge.event.SetAmountMaxEvent;
import com.puyue.www.qiaoge.event.SetAmountMaxsEvent;
import com.puyue.www.qiaoge.event.SetAmountsEvent;
import com.puyue.www.qiaoge.event.UpDateNumEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.utils.EnCodeUtil;
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
public class AddSubAccountActivity extends BaseSwipeActivity implements View.OnClickListener {
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_yzm)
    EditText et_yzm;
    @BindView(R.id.et_set_psd)
    EditText et_set_psd;
    @BindView(R.id.et_sure_psd)
    EditText et_sure_psd;
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.swipe)
    SwitchCompat swipe;
    @BindView(R.id.swipe1)
    SwitchCompat swipe1;
    @BindView(R.id.swipe2)
    SwitchCompat swipe2;
    @BindView(R.id.swipe3)
    SwitchCompat swipe3;
    @BindView(R.id.ll_yzm)
    RelativeLayout ll_yzm;
    @BindView(R.id.tv_yzm)
    TextView tv_yzm;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_amount)
    TextView et_amount;
    @BindView(R.id.tv_amount_remind)
    TextView tv_amount_remind;
    private BaseModel mModelAddSubAccount;
    private String phone;
    boolean isSendingCode;
    private BaseModel mModelSendCode;
    CountDownTimer countDownTimer;
    private AmountSetDialog amountSetDialog;
    private AmountMaxDialog amountMaxDialog;
    String amountRemind;
    String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTykrDv1TEKVjDeE29kVLo5M7mctlE65WlHSMN8RVL1iA9jXsF9SMNH1AErs2lqxpv18fd3TOAw0pBaG+cXOxApKdvRDKgxyuHnONOBzxr6EyWOQlRZt94auL1ESVbLdvYa7+cISkVe+MphfQh7uI/64tGQ34aRNmvFKv9PEeBTQIDAQAB";
    private String phones;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.add_sub_account);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        ll_yzm.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_amount_remind.setOnClickListener(this);
        et_amount.setOnClickListener(this);
        //关闭1 打开0
        SharedPreferencesUtil.saveString(mActivity,"inPoint","1");
        SharedPreferencesUtil.saveString(mActivity,"inBalance","1");
        SharedPreferencesUtil.saveString(mActivity,"inGift","1");


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
//                //0 不提醒 1 任意提醒 2 达到金额提醒
//                if(isChecked) {
//                    SharedPreferencesUtil.saveString(mActivity,"notification","0");
//                }else {
//                    SharedPreferencesUtil.saveString(mActivity,"notification","1");
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


        tv_add.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                String inPoint = SharedPreferencesUtil.getString(mActivity, "inPoint");
                String inBalance = SharedPreferencesUtil.getString(mActivity, "inBalance");
                String inGift = SharedPreferencesUtil.getString(mActivity, "inGift");
                String amount = et_amount.getText().toString();
                SharedPreferencesUtil.saveString(mActivity, "amount",amount);
                String amount_limit = SharedPreferencesUtil.getString(mActivity, "amount_limit");
                String notification = SharedPreferencesUtil.getString(mActivity, "notification");
                if (StringHelper.notEmptyAndNull(et_name.getText().toString())
                        && StringHelper.notEmptyAndNull(et_phone.getText().toString())
                        && StringHelper.notEmptyAndNull(et_set_psd.getText().toString())
                        && StringHelper.notEmptyAndNull(et_sure_psd.getText().toString())
                        && StringHelper.notEmptyAndNull(et_yzm.getText().toString())) {
                    if (et_set_psd.getText().toString().equals(et_sure_psd.getText().toString())) {
                        //两次输入的密码一致才能请求注册
                        //两次密码一致之后判断密码是不是6-16位字母与数字的组合,如果是纯数字或者纯字母,不允许往下走
                        if (et_set_psd.getText().toString().length() >= 6
                                && et_sure_psd.getText().toString().length() >= 6) {
                            if (StringHelper.isLetterDigit(et_set_psd.getText().toString())) {
                                //添加一个子账号,添加子账号会默认注册一个账号

                                requestAddSubAccount(et_phone.getText().toString(), et_name.getText().toString(),
                                        et_set_psd.getText().toString(), et_yzm.getText().toString(), inPoint, inBalance, inGift,amount_limit,amount,notification,amountRemind);
                            } else {
                                AppHelper.showMsg(mContext, "密码由6-16位数字与字母组成");
                            }
                        } else {
                            AppHelper.showMsg(mContext, "密码位数不足!");
                        }
                    } else {
                        AppHelper.showMsg(mContext, "两次密码不一致!");
                    }
                } else {
                    AppHelper.showMsg(mContext, "数据不全!");
                }

            }
        });
    }

    /**
     * 添加子账户  SubAccountAddAPI.requestAddSubAccount(mContext, phone, name, pwd, yzm, inPonit,inBalance,inGift)
     */
    private void requestAddSubAccount(String phone, String name, String pwd, String yzm, String inPoint, String inBalance, String inGift,String amount_limit, String amount, String notification, String warn_amount) {
        SubAccountAddAPI.requestAddSubAccount(mContext, phone, name, pwd, yzm, inPoint,inBalance,inGift,amount_limit,amount,notification,warn_amount)
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
                        mModelAddSubAccount = baseModel;
                        if (mModelAddSubAccount.success) {
                            //添加子账号成功,刷新列表
                            ToastUtil.showSuccessMsg(mContext,"添加成功");
                            EventBus.getDefault().post(new BackEvent());
                            finish();
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_yzm:
                phone = et_phone.getText().toString();
                try {
                    phones = EnCodeUtil.encryptByPublicKey(phone,publicKeyStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int result = checkPhoneNum(phone);
                if (result == 2) {
                    Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else if (result == 1) {
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    requestSendCode(phones);
                }
                break;

            case R.id.iv_back:
                finish();
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
        }
    }

    /**
     * 发送验证码
     * @param phone
     */


    private void requestSendCode(String phone) {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            ToastUtil.showSuccessMsg(mContext, "网络不给力!");
        } else {
            SendCodeAPI.requestSendCode(mContext,phone,7)
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
                            mModelSendCode = baseModel;
                            if (mModelSendCode.success) {
                                ToastUtil.showSuccessMsg(mContext, "发送验证码成功!");
                                handleCountDown();

                            } else {
                                ToastUtil.showSuccessMsg(mContext, mModelSendCode.message);
                            }
                        }
                    });
        }
    }

    /**
     * 倒计时
     */
    private void handleCountDown() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isSendingCode = true;
                ll_yzm.setEnabled(false);
                tv_yzm.setEnabled(false);
                tv_yzm.setText(millisUntilFinished / 1000 + "秒后" + "\n重新发送验证码");
                tv_yzm.setTextColor(Color.parseColor("#A7A7A7"));

            }

            @Override
            public void onFinish() {
                isSendingCode = false;
                ll_yzm.setEnabled(true);
                tv_yzm.setText("点击发送验证码");
                tv_yzm.setEnabled(true);
                tv_yzm.setTextColor(Color.parseColor("#232131"));
            }
        }.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAmount(SetAmountEvent event) {
        if(SharedPreferencesUtil.getString(mActivity,"flag").equals("2")) {
            if(event.amount.length()==0) {
                tv_amount_remind.setText("请添加金额");
            }else {
                tv_amount_remind.setText("满"+event.amount+"元消费提醒");
                swipe3.setChecked(true);
                amountRemind = event.amount;
                //0开启
                SharedPreferencesUtil.saveString(mContext,"notification","2");
            }

        }else {
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAmounts(SetAmountsEvent event) {
        if(SharedPreferencesUtil.getString(mActivity,"flag").equals("1")) {
            tv_amount_remind.setText("任意金额提醒");
            swipe3.setChecked(true);
            amountRemind = "0";
            SharedPreferencesUtil.saveString(mContext,"notification","1");
        }

        if(SharedPreferencesUtil.getString(mActivity,"flag").equals("0")) {
            tv_amount_remind.setText("添加金额提醒");
            swipe3.setChecked(false);
            amountRemind = "0";
            SharedPreferencesUtil.saveString(mContext,"notification","0");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAmountss(SetAmountMaxEvent event) {
        et_amount.setText(event.amount);
        SharedPreferencesUtil.saveString(mContext,"amount",event.amount);
        SharedPreferencesUtil.saveString(mContext,"amount_limit","1");
//        swipe3.setChecked(true);
//        SharedPreferencesUtil.saveString(mContext,"notifaction","0");
    }

    //设置最大金额
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAmountsss(SetAmountMaxsEvent event) {
        et_amount.setText(event.amount);
        SharedPreferencesUtil.saveString(mContext,"amount",null);
        SharedPreferencesUtil.saveString(mContext,"amount_limit","0");
    }
    /**
     * 检验号码
     * @param phone
     * @return
     */
    private int checkPhoneNum(String phone) {
        if (TextUtils.isEmpty(phone)){
            return 2;
        }else if (!phone.matches("^[1][0-9]{10}$")){
            return 1;
        }else {
            return 0;
        }
    }
}
