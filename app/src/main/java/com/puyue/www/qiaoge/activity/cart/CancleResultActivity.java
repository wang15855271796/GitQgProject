package com.puyue.www.qiaoge.activity.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.login.SendCodeAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/6/5
 */
public class CancleResultActivity extends BaseSwipeActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.tv_yzm)
    TextView tv_yzm;
    @BindView(R.id.ll_yzm)
    RelativeLayout ll_yzm;
    @BindView(R.id.et_yzm)
    EditText et_yzm;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.tv_commit)
    TextView tv_commit;
    private String phone;
    private BaseModel mModelSendCode;
    CountDownTimer countDownTimer;
    boolean isSendingCode;
    String reason;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_result);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        if(getIntent().getStringExtra("reason")!=null) {
            reason = getIntent().getStringExtra("reason");

        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StringHelper.notEmptyAndNull(et_phone.getText().toString())){
                    phone = et_phone.getText().toString();
                }

                sendCode(phone);
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringHelper.notEmptyAndNull(et_pwd.getText().toString())
                        && StringHelper.notEmptyAndNull(et_phone.getText().toString())
                        && StringHelper.notEmptyAndNull(et_yzm.getText().toString())&&
                        StringHelper.notEmptyAndNull(reason)) {
                        getData(et_phone.getText().toString(),et_yzm.getText().toString(),et_pwd.getText().toString(),reason);

                }
            }
        });
    }

    private void getData(String phone, String yzm, String pwd, String reason) {
        SendCodeAPI.requestsCode(mContext,phone,yzm,reason,pwd)
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
                            ToastUtil.showSuccessMsg(mContext, "成功!");
                            finish();
                        } else {
                            ToastUtil.showSuccessMsg(mContext, mModelSendCode.message);
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

    /**
     * 发送验证码
     * @param phone
     */
    private void sendCode(String phone) {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            ToastUtil.showSuccessMsg(mContext, "网络不给力!");
        } else {
            SendCodeAPI.requestSendCode(mContext,phone,11)
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
