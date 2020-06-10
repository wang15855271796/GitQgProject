package com.puyue.www.qiaoge.activity.mine.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.login.SendCodeAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.utils.EnCodeUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/23
 */
public class RegisterMessageActivity extends BaseSwipeActivity implements View.OnClickListener {
    @BindView(R.id.et_yzm)
    EditText et_yzm;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.ll_yzm)
    RelativeLayout ll_yzm;
    @BindView(R.id.tv_yzm)
    TextView tv_yzm;
    @BindView(R.id.toolbar_register)
    Toolbar toolbar_register;
    @BindView(R.id.tv_next)
    TextView tv_next;
    private BaseModel mModelSendCode;
    private CountDownTimer countDownTimer;
    private boolean isSendingCode = true;
    private String phone;
    String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTykrDv1TEKVjDeE29kVLo5M7mctlE65WlHSMN8RVL1iA9jXsF9SMNH1AErs2lqxpv18fd3TOAw0pBaG+cXOxApKdvRDKgxyuHnONOBzxr6EyWOQlRZt94auL1ESVbLdvYa7+cISkVe+MphfQh7uI/64tGQ34aRNmvFKv9PEeBTQIDAQAB";
    private String phones;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_message_register);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        ll_yzm.setOnClickListener(this);
        toolbar_register.setOnClickListener(this);
        tv_next.setOnClickListener(this);
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }

    /**
     * 验证手机号码
     * @param username
     * @return
     */
    private int checkPhoneNum(String username){
        if (TextUtils.isEmpty(username)){
            return 2;
        }else if (!username.matches("^[1][0-9]{10}$")){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_yzm:
                phone = et_phone.getText().toString();
                try {
                    phones = EnCodeUtil.encryptByPublicKey(phone, publicKeyStr);
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

            case R.id.toolbar_register:
                finish();

                break;

            case R.id.tv_next:
                String yzms = et_yzm.getText().toString();
                String phones = et_phone.getText().toString();
                if(!yzms.isEmpty()&&!phones.isEmpty()) {
                    SendCodeAPI.requestSendCodes(mContext,phones,yzms)
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
                                        Intent intent = new Intent(mContext,RegisterStep1Activity.class);
                                        intent.putExtra("phone",phones);
                                        intent.putExtra("yzm",yzms);
                                        startActivity(intent);
                                    } else {
                                        ToastUtil.showSuccessMsg(mContext, mModelSendCode.message);
                                    }
                                }
                            });

                }else {
                    ToastUtil.showSuccessMsg(mContext,"请填写完信息");
                }

                break;
        }
    }

    /**
     * 发送验证码
     */
    private void requestSendCode(String phone) {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            ToastUtil.showSuccessMsg(mContext, "网络不给力!");
        } else {
            SendCodeAPI.requestSendCode(mContext,phone,2)
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

}
