package com.puyue.www.qiaoge.activity.mine.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.login.ChangeLoginPasswordAPI;
import com.puyue.www.qiaoge.api.mine.login.ChangePayPasswordAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/8.
 */

public class EditPasswordInputPasswordActivity extends BaseSwipeActivity {
    public static final String TEL = "tel";
    public static final String TYPE = "type";
    public static final String SOURCE = "source";
    public static final String CODE = "code";
    public static final String ORDERTYPE = "orderDeliveryType";
    public static final String PAYAMOUNT = "payAmount";
    private String mType;
    private String mTel;
    private String mSource;
    private String mCode;
    private ImageView mIvBack;
    private TextView mTvTitle;
    private EditText mEditOnce;
    private EditText mEditSecond;
    private Button mBtnNext;

    private BaseModel mModelChangeLoginPassword;
    private BaseModel mModelChangePayPassword;

    private int orderDeliveryType;
    private double payAmount;
    public static Intent getIntent(Context context, Class<?> cls, String type, String tel, String source, String code,int orderDeliveryType,double payAmount) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(TYPE, type);
        intent.putExtra(TEL, tel);
        intent.putExtra(SOURCE, source);
        intent.putExtra(CODE, code);
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
        mCode = getIntent().getStringExtra(CODE);
        payAmount=getIntent().getDoubleExtra(PAYAMOUNT,0);
        orderDeliveryType=getIntent().getIntExtra(ORDERTYPE,0);
        if (savedInstanceState != null) {
            mType = savedInstanceState.getString(TYPE);
            mTel = savedInstanceState.getString(TEL);
            mSource = savedInstanceState.getString(SOURCE);
            mCode = savedInstanceState.getString(CODE);
            orderDeliveryType = savedInstanceState.getInt(ORDERTYPE,orderDeliveryType);
            payAmount = savedInstanceState.getDouble(PAYAMOUNT,payAmount);
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(TEL, mTel);
        outState.putString(TYPE, mType);
        outState.putString(SOURCE, mSource);
        outState.putString(CODE, mCode);
        outState.putInt(ORDERTYPE, orderDeliveryType);
        outState.putDouble(PAYAMOUNT,payAmount);

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit_pay_password_input_password);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_pp_input_password_back);
        mTvTitle = (TextView) findViewById(R.id.tv_pp_input_password_title);
        mEditOnce = (EditText) findViewById(R.id.edit_pp_input_password_once);
        mEditSecond = (EditText) findViewById(R.id.edit_pp_input_password_second);
        mBtnNext = (Button) findViewById(R.id.btn_edit_pp_input_password_next);
    }

    @Override
    public void setViewData() {

        //mEditOnce.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //  mEditSecond.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if (StringHelper.notEmptyAndNull(mType) && StringHelper.notEmptyAndNull(mSource)) {
            if (mSource.equals("pay")) {
                //来操作支付密码的
                if (mType.equals("0")) {
                    mTvTitle.setText("设置支付密码");
                } else if (mType.equals("1")) {
                    mTvTitle.setText("修改支付密码");
                }
            } else if (mSource.equals("login")) {
                //来修改登录密码的
                mTvTitle.setText("修改登录密码");
            } else if (mSource.equals("forget")) {
                //忘记密码的流程走到这里,给某个用户输入的手机号(账号)修改密码
                mTvTitle.setText("忘记密码");
            }
        }
        mBtnNext.setEnabled(false);
        mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mBtnNext.setOnClickListener(noDoubleClickListener);
        mEditOnce.addTextChangedListener(textWatcher);
        mEditSecond.addTextChangedListener(textWatcher);
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mBtnNext) {
                if (mEditOnce.getText().toString().equals(mEditSecond.getText().toString())) {
                    //两次输入的密码一致才能请求注册
                    //两次密码一致之后判断密码是不是6-16位字母与数字的组合,如果是纯数字或者纯字母,不允许往下走
                    if (mEditOnce.getText().toString().length() >= 6 && mEditSecond.getText().toString().length() >= 6) {
                        if (StringHelper.isLetterDigit(mEditOnce.getText().toString())) {
                            requestNewPayPassword();

                        } else {
                            AppHelper.showMsg(mContext, "密码由6-16位数字与字母组成");
                        }
                    } else {
                        AppHelper.showMsg(mContext, "密码位数不足!");
                    }
                } else {
                    AppHelper.showMsg(mContext, "两次密码不一致!");
                }
            }
        }
    };

    private void requestNewPayPassword() {
        //请求接口更换最新的密码之后,跳转到结果界面
        //这里要根据来源判断是在修改什么密码
        if (StringHelper.notEmptyAndNull(mType) && StringHelper.notEmptyAndNull(mSource)) {
            if (mSource.equals("pay")) {
                //来操作支付密码的
                if (mType.equals("0")) {
                    //调接口设置交易密码
                    requestChangePayPassword(5);
                } else if (mType.equals("1")) {
                    //调接口修改交易密码
                    requestChangePayPassword(6);
                }
            } else if (mSource.equals("login")) {
                //调接口来修改登录密码
                requestChangeLoginPassword();
            } else if (mSource.equals("forget")) {
                //调接口给忘记密码的用户修改登录密码
                requestChangeLoginPassword();
            }
        }
    }

    private void requestChangePayPassword(int smsType) {
        ChangePayPasswordAPI.requestChangePayPassword(mContext, mTel, smsType, mCode, mEditOnce.getText().toString())
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
                        mModelChangePayPassword = baseModel;
                        if (mModelChangePayPassword.success) {
                            //设置或者修改交易密码成功
                            startActivity(EditAccountOrPasswordResultActivity.getIntent(mContext, EditAccountOrPasswordResultActivity.class, mType, mTel, mSource,orderDeliveryType,payAmount));
                            finish();
                        } else {
                            AppHelper.showMsg(mContext, mModelChangePayPassword.message);
                        }
                    }
                });
    }

    private void requestChangeLoginPassword() {
        //在这里获取到用户手机号,用户的验证码,以及在这个界面获取到的新的登录密码,调接口将用户的登录密码修改过来
        ChangeLoginPasswordAPI.requestChangeLoginPassword(mContext, mTel, mCode, mEditOnce.getText().toString())
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
                        mModelChangeLoginPassword = baseModel;
                        if (mModelChangeLoginPassword.success) {
                            //修改登录密码成功,跳转修改成功界面
                            startActivity(EditAccountOrPasswordResultActivity.getIntent(mContext, EditAccountOrPasswordResultActivity.class,
                                    mType, mTel, mSource,orderDeliveryType,payAmount));

                            finish();
                        } else {
                            AppHelper.showMsg(mContext, mModelChangeLoginPassword.message);
                        }
                    }
                });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //禁止空格输入
            if (s.toString().contains(" ")) {
                String[] str = s.toString().split(" ");
                String str1 = "";
                for (int i = 0; i < str.length; i++) {
                    str1 += str[i];
                }
                mEditOnce.setText(str1);
                mEditSecond.setText(str1);
                mEditOnce.setSelection(start);
                mEditSecond.setSelection(start);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (StringHelper.notEmptyAndNull(mEditOnce.getText().toString())
                    && StringHelper.notEmptyAndNull(mEditSecond.getText().toString())) {
                mBtnNext.setEnabled(true);
                mBtnNext.setTextColor(getResources().getColor(R.color.app_color_white));
            } else {
                mBtnNext.setEnabled(false);
                mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
            }

            if (s.length() > 0) {
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (c >= 0x4e00 && c <= 0X9fff) { // 根据字节码判断
                        // 如果是中文，则清除输入的字符，否则保留
                        s.delete(i, i + 1);
                    }
                }

            }
        }
    };
}
