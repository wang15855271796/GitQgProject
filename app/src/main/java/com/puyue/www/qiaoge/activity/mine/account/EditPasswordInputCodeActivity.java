package com.puyue.www.qiaoge.activity.mine.account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.MyConfireOrdersActivity;
import com.puyue.www.qiaoge.api.mine.login.ChangeLoginPhoneAPI;
import com.puyue.www.qiaoge.api.mine.login.CheckCommonCodeAPI;
import com.puyue.www.qiaoge.api.mine.login.SendCodeAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.login.ChangeLoginPhoneModel;
import com.puyue.www.qiaoge.model.mine.login.CheckPasswordCodeModel;
import com.puyue.www.qiaoge.utils.EnCodeUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/8.
 */

public class EditPasswordInputCodeActivity extends BaseSwipeActivity {
    public static final String TYPE = "type";
    public static final String TEL = "tel";
    public static final String SOURCE = "source";
    public static final String FORGETPSW = "forgetPsw";
    public static final String ORDERTYPE = "orderDeliveryType";
    public static final String PAYAMOUNT = "payAmount";
    private String mType;
    private String mTel;
    private String mSource;
    private String forgetPsw;
    private boolean isSendingCode = true;
    private CountDownTimer countDownTimer;
    private ImageView mIvBack;
    private TextView mTvTitle;
    private EditText mEditCode;
    private TextView mTvSendCode;
    private Button mBtnNext;

    private int mCode;
    private BaseModel mModelSendCode;
    private BaseModel mModelUpdatePhone;
    private CheckPasswordCodeModel mModelCheckPasswordCode;

    private CheckPasswordCodeModel mModelCheckCode;
    private ChangeLoginPhoneModel mModelChangeLoginPhone;

    private String orderId;

    private int orderDeliveryType;
    private double payAmount;

    public static Intent getIntent(Context context, Class<?> cls, String type, String tel, String source,String forgetPsw,int orderDeliveryType,double payAmount) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(TYPE, type);
        intent.putExtra(TEL, tel);
        intent.putExtra(SOURCE, source);
        intent.putExtra(FORGETPSW, forgetPsw);
        intent.putExtra(ORDERTYPE, orderDeliveryType);
        intent.putExtra(PAYAMOUNT, payAmount);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        mType = getIntent().getStringExtra(TYPE);
        mTel = getIntent().getStringExtra(TEL);
        mSource = getIntent().getStringExtra(SOURCE);
        forgetPsw = getIntent().getStringExtra(FORGETPSW);
        forgetPsw = getIntent().getStringExtra(FORGETPSW);
       orderDeliveryType=getIntent().getIntExtra(ORDERTYPE,0);
       payAmount=getIntent().getDoubleExtra(PAYAMOUNT,0);

        if (savedInstanceState != null) {
            mType = savedInstanceState.getString(TYPE);
            mTel = savedInstanceState.getString(TEL);
            mSource = savedInstanceState.getString(SOURCE);
            forgetPsw = savedInstanceState.getString(FORGETPSW);
            orderDeliveryType = savedInstanceState.getInt(ORDERTYPE,0);
            payAmount = savedInstanceState.getDouble(PAYAMOUNT,0);
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(TEL, mTel);
        outState.putString(TYPE, mType);
        outState.putString(SOURCE, mSource);
        outState.putString(FORGETPSW, forgetPsw);
        outState.putInt(ORDERTYPE, orderDeliveryType);
        outState.putDouble(PAYAMOUNT, payAmount);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit_pay_password_input_code);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_pp_input_code_back);
        mTvTitle = (TextView) findViewById(R.id.tv_pp_input_code_title);
        mEditCode = (EditText) findViewById(R.id.edit_edit_pp_code);
        mTvSendCode = (TextView) findViewById(R.id.tv_edit_pp_send_code);
        mBtnNext = (Button) findViewById(R.id.btn_edit_pp_input_code_next);
    }

    @Override
    public void setViewData() {
        orderId = UserInfoHelper.getOrderId(mContext);
      /*  if (UserInfoHelper.getDeliverType(mContext) != null && StringHelper.notEmptyAndNull(UserInfoHelper.getDeliverType(mContext))) {
            orderDeliveryType = Integer.parseInt(UserInfoHelper.getDeliverType(mContext));
        }*/

        //根据传值判断是来修改登录密码的还是来修改支付密码的
        //修改支付密码还分别有:设置支付密码和修改支付密码
        if (StringHelper.notEmptyAndNull(mType) && StringHelper.notEmptyAndNull(mSource)) {
            if (mSource.equals("pay")) {
                //来操作支付密码的
                if (mType.equals("0")) {
                    mTvTitle.setText("设置支付密码");
                    mCode = 5;
                } else if (mType.equals("1")) {
                    mTvTitle.setText("修改支付密码");
                    mCode = 6;
                }

                //  requestSendCode();
            } else if (mSource.equals("login")) {
                //来操作登录密码的,只有修改登录密码
                mTvTitle.setText("修改登录密码");
                mCode = 3;
                // requestSendCode();
            } else if (mSource.equals("account")) {
                //走的是修改账户的流程,在这里接收验证码
                mTvTitle.setText("修改登录账户");
                mCode = 4;
                //handleCountDown();
            } else if (mSource.equals("forget")) {
                //走的是忘记了密码,来这里修改某个账号的密码的
                mTvTitle.setText("忘记密码");
                //忘记密码的请求验证码也是重置密码的code
                mCode = 3;

                //handleCountDown();
            }
        }
        mBtnNext.setEnabled(false);
        mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
        //已进入这个界面就请求发送验证码,发送成功之后开始倒计时
        //这时候需要确定往哪个手机号上面发送验证码,虽然都是使用传过来的值mTel
        //但是逻辑要清楚,修改账户手机号会往新的手机号上发送验证码
        //修改登录密码或者修改(设置)支付密码会往用户的账号手机号发送验证码,都需要验证


    }

    private void requestSendCode() {
        String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTykrDv1TEKVjDeE29kVLo5M7mctlE65WlHSMN8RVL1iA9jXsF9SMNH1AErs2lqxpv18fd3TOAw0pBaG+cXOxApKdvRDKgxyuHnONOBzxr6EyWOQlRZt94auL1ESVbLdvYa7+cISkVe+MphfQh7uI/64tGQ34aRNmvFKv9PEeBTQIDAQAB";
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            AppHelper.showMsg(mContext, "网络不给力!");
        } else {
            String encrypt = null;
            try {
                encrypt = EnCodeUtil.encryptByPublicKey(mTel,publicKeyStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SendCodeAPI.requestSendCode(mContext, encrypt, mCode)
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
                                AppHelper.showMsg(mContext, "发送验证码成功!");
                                handleCountDown();
                            } else {
                                AppHelper.showMsg(mContext, mModelSendCode.message);
                            }
                        }
                    });
        }
    }

    public void handleCountDown() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isSendingCode = true;
                mTvSendCode.setText(millisUntilFinished / 1000 + "秒后" + "\n重新发送验证码");
                mTvSendCode.setTextColor(Color.parseColor("#A7A7A7"));
            }

            @Override
            public void onFinish() {
                isSendingCode = false;
                mTvSendCode.setText("点击发送验证码");
                mTvSendCode.setEnabled(true);
                mTvSendCode.setTextColor(Color.parseColor("#232131"));
            }
        }.start();
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mTvSendCode.setOnClickListener(noDoubleClickListener);
        mBtnNext.setOnClickListener(noDoubleClickListener);
        mEditCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringHelper.notEmptyAndNull(mEditCode.getText().toString())) {
                    mBtnNext.setEnabled(true);
                    mBtnNext.setTextColor(getResources().getColor(R.color.app_color_white));
                } else {
                    mBtnNext.setEnabled(false);
                    mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
                }
            }
        });
    }

    private double amount;
    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {



                if (forgetPsw!=null&&StringHelper.notEmptyAndNull(forgetPsw)) {
                    Intent intent = new Intent(mContext, MyConfireOrdersActivity.class);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("orderDeliveryType", orderDeliveryType);
                    intent.putExtra("payAmount", payAmount);


                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_from_right,
                            R.anim.slide_out_from_left);
                    finish();
                } else {
                    finish();
                }


            } else if (view == mTvSendCode) {
                //手动点击发送验证码
                requestSendCode();
                mTvSendCode.setEnabled(false);

                if (!isSendingCode) {
                    //现在不在倒计时中,可以重新发送验证码
                    requestSendCode();
                    mTvSendCode.setEnabled(true);
                }
            } else if (view == mBtnNext) {
                //验证验证码的正确性,然后,只有来修改登录密码或者修改(设置)支付密码的流程才需要跳转到输入新密码的界面
                if (StringHelper.notEmptyAndNull(mType) && StringHelper.notEmptyAndNull(mSource)) {
                    if (mSource.equals("pay")) {
                        //来操作支付密码的
                        //先检验验证码
                        if (mType.equals("0")) {
                            mTvTitle.setText("设置支付密码");
                            requestPayPasswordCodeRight(5);
                        } else if (mType.equals("1")) {
                            mTvTitle.setText("修改支付密码");
                            requestPayPasswordCodeRight(6);
                        }
                    } else if (mSource.equals("login")) {
                        //来操作登录密码的,只有修改登录密码,
                        //修改登录密码需要先验证验证码的正确性,然后再输入密码,然后再验证一次验证码
                        requestPayPasswordCodeRight(3);
                    } else if (mSource.equals("account")) {
                        //走的是修改账户的流程,验证新手机号和新手机号收到的验证码,验证成功跳转修改账户的结果页
                        requestPayPasswordCodeRight(4);
                    } else if (mSource.equals("forget")) {
                        //走的是忘记密码的重置密码的流程,需要验证手机号和验证码
                        requestPayPasswordCodeRight(3);
                    }
                }
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    /*    Intent intent = new Intent(mContext,MyConfireOrdersActivity.class);
        intent.putExtra("orderId",orderId);
        intent.putExtra("orderDeliveryType",orderDeliveryType);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right,
                R.anim.slide_out_from_left);
        finish();*/
    }

    private void requestPayPasswordCodeRight(final int type) {
        CheckCommonCodeAPI.requestCodeRight(mContext, mTel, mEditCode.getText().toString(), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CheckPasswordCodeModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CheckPasswordCodeModel checkPasswordCodeModel) {
                        mModelCheckCode = checkPasswordCodeModel;
                        if (mModelCheckCode.success) {
                            updateRightCode(type);
                        } else {
                            AppHelper.showMsg(mContext, mModelCheckCode.message);
                        }
                    }
                });
    }

    private void updateRightCode(int type) {
        if (mModelCheckCode.data) {

            AppHelper.showMsg(mContext, "验证成功");
            if (type == 5 || type == 6) {

                startActivity(EditPasswordInputPasswordActivity.getIntent(mContext, EditPasswordInputPasswordActivity.class, mType, mTel, mSource,
                        mEditCode.getText().toString(),orderDeliveryType,payAmount));
                finish();
            } else if (type == 3) {
                startActivity(EditPasswordInputPasswordActivity.getIntent(mContext, EditPasswordInputPasswordActivity.class, mType, mTel,
                        mSource, mEditCode.getText().toString(),orderDeliveryType,payAmount));

                finish();
            } else if (type == 4) {
                //修改账户
                requestChangeLoginPhone();
            }
        } else {
            Log.i("www", "updateRightCode: " + mModelCheckCode.data);
            AppHelper.showMsg(mContext, "验证码错误");
        }
    }

    private void requestChangeLoginPhone() {
        ChangeLoginPhoneAPI.requestChangeLoginPhone(mContext, mTel, mEditCode.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ChangeLoginPhoneModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ChangeLoginPhoneModel changeLoginPhoneModel) {
                        mModelChangeLoginPhone = changeLoginPhoneModel;
                        if (mModelChangeLoginPhone.success) {
                            //修改账户成功
                            startActivity(EditAccountOrPasswordResultActivity.getIntent(mContext, EditAccountOrPasswordResultActivity.class, mType, mTel, mSource,0,0));
                            finish();
                        } else {
                            AppHelper.showMsg(mContext, mModelChangeLoginPhone.message);
                        }
                    }
                });
    }
}
