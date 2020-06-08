package com.puyue.www.qiaoge.activity.mine.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.login.SendCodeAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/8.
 */
//根据传过来的参数来判断用户是在修改支付密码还是设置支付密码
//现在还需要传一个参数来判断是来修改登录密码还是修改支付密码的
public class EditAccountInputPhoneActivity extends BaseSwipeActivity {
    public static final String TYPE = "type";
    public static final String SOURCE = "source";
    private String mType;
    private String mSource;
    private ImageView mIvBack;
    private EditText mEdit;
    private RelativeLayout mRlDelete;
    private Button mBtnNext;
    private TextView mTvTitle;
    private TextView mTvText;
    private int mCode;

    private BaseModel mModelSendCode;

    public static Intent getIntent(Context context, Class<?> cls, String type, String source) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(TYPE, type);
        intent.putExtra(SOURCE, source);
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
        mSource = getIntent().getStringExtra(SOURCE);
        if (savedInstanceState != null) {
            mType = savedInstanceState.getString(TYPE);
            mSource = savedInstanceState.getString(SOURCE);
        }
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit_pay_password_input_phone);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_pp_input_phone_back);
        mEdit = (EditText) findViewById(R.id.edit_pp_input_phone);
        mRlDelete = (RelativeLayout) findViewById(R.id.rl_pp_input_phone_delete);
        mBtnNext = (Button) findViewById(R.id.btn_pp_input_phone_next);
        mTvTitle = (TextView) findViewById(R.id.tv_pp_input_phone_title);
        mTvText = (TextView) findViewById(R.id.tv_input_phone_edit_text);
    }

    @Override
    public void setViewData() {
        mBtnNext.setEnabled(false);
        mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
        if (StringHelper.notEmptyAndNull(mSource)) {
            if (mSource.equals("account")) {
                mTvTitle.setText("修改登录账号");
                mTvText.setText("新手机号");
                mEdit.setHint("请输入新的手机号");
                mCode = 4;
            } else if (mSource.equals("forget")) {
                mTvTitle.setText("忘记密码");
                mTvText.setText("手机号");
                mEdit.setHint("请输入手机号");
                mCode = 3;
            }
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(noDoubleClickListener);
        mRlDelete.setOnClickListener(noDoubleClickListener);
        mBtnNext.setOnClickListener(noDoubleClickListener);
        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringHelper.notEmptyAndNull(mEdit.getText().toString())) {
                    mBtnNext.setEnabled(true);
                    mBtnNext.setTextColor(getResources().getColor(R.color.app_color_white));
                } else {
                    mBtnNext.setEnabled(false);
                    mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
                }
            }
        });
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View view) {
            if (view == mIvBack) {
                finish();
            } else if (view == mRlDelete) {
                mEdit.setText("");
            } else if (view == mBtnNext) {

                if (mEdit.getText().toString().length() == 11) {
                    if (StringHelper.notEmptyAndNull(mSource)) {
                    /*    if (mEdit.equals(UserInfoHelper.getUserCell(mContext))) {*/
                            //来修改登录账号的
                            //或者是忘记了密码,来修改某个账号的密码的
                            //在这里先请求接口发送验证码,发送成功才跳转
                            startActivity(EditPasswordInputCodeActivity.getIntent(mContext, EditPasswordInputCodeActivity.class, mType, mEdit.getText().toString(), mSource,"",0,0));
                            finish();
                            // requestSendCode();
                       /* } else {

                            AppHelper.showMsg(mContext, "手机号未注册");
                        }*/

                    }
                } else {
                    AppHelper.showMsg(mContext, "手机号位数错误");
                }
            }
        }
    };

    private void requestSendCode() {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            AppHelper.showMsg(mContext, "网络不给力!");
        } else {
            SendCodeAPI.requestSendCode(mContext, mEdit.getText().toString(), mCode)
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
                                // AppHelper.showMsg(mContext, "发送验证码成功!");
                                Log.i("tiaozhuan", "onNext: fasong ");
                                startActivity(EditPasswordInputCodeActivity.getIntent(mContext, EditPasswordInputCodeActivity.class, mType, mEdit.getText().toString(), mSource,"",0,0));
                                finish();
                            } else {
                                AppHelper.showMsg(mContext, mModelSendCode.message);
                            }
                        }
                    });
        }
    }
}
