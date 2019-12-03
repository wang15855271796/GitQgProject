package com.puyue.www.qiaoge.activity.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.api.mine.FeedbackAPI;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/3.
 */

public class FeedBackActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
    private EditText mEditText;
    private EditText mEditName;
    private EditText mEditPhone;
    private EditText mEditQQEmail;
    private EditText mEditWX;
    private Button mBtnNext;

    private BaseModel mModelFeedback;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_feedback);
    }


    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_feedback_back);
        mEditText = (EditText) findViewById(R.id.edit_feedback);

        mEditName = (EditText) findViewById(R.id.edit_feedback_name);
        mEditPhone = (EditText) findViewById(R.id.edit_feedback_phone);
        mEditQQEmail = (EditText) findViewById(R.id.edit_feedback_qq_email);
        mEditWX = (EditText) findViewById(R.id.edit_feedback_wx);
        mBtnNext = (Button) findViewById(R.id.btn_feedback);
    }

    @Override
    public void setViewData() {
        mBtnNext.setEnabled(false);
        mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
    }

    @Override
    public void setClickEvent() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringHelper.notEmptyAndNull(mEditText.getText().toString())) {
                    mBtnNext.setEnabled(true);
                    mBtnNext.setTextColor(getResources().getColor(R.color.app_color_white));
                } else {
                    mBtnNext.setEnabled(false);
                    mBtnNext.setTextColor(getResources().getColor(R.color.app_btn_unable));
                }
            }
        });

        mBtnNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
//                if (StringHelper.notEmptyAndNull(mEditText.getText().toString())) {
//                    if (StringHelper.notEmptyAndNull(mEditName.getText().toString())) {
//                        //提交意见,其余几个选项都是可填的,也直接传给后台即可,没有就传空字符串
//                        //三个联系方式必须填写一个,名字可以不填
//                        if (!StringHelper.notEmptyAndNull(mEditPhone.getText().toString())
//                                && !StringHelper.notEmptyAndNull(mEditQQEmail.getText().toString())
//                                && !StringHelper.notEmptyAndNull(mEditWX.getText().toString())) {
//                            //三个联系方式都为空
//                           AppHelper.showMsg(mContext, "请至少填写一项联系方式");
//                        } else {
//                            //有一个联系方式即可
//                            requestFeedback();
//                        }
//                    } else {
//                        AppHelper.showMsg(mContext, "请先输入姓名");
//                    }
//                } else {
//                    AppHelper.showMsg(mContext, "请先输入意见");
//                }
                if (StringHelper.notEmptyAndNull(mEditText.getText().toString())) {
                    requestFeedback();
                    finish();
                } else {
                    AppHelper.showMsg(mContext, "请先输入意见");
                }
            }
        });
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
    }

    //这个接口现在其他四个参数，直接传空是否可以？如果不成功就要改需求。反馈成功。
    private void requestFeedback() {
        FeedbackAPI.requestFeedback(mContext,
                mEditText.getText().toString(),
                mEditName.getText().toString(),
                mEditPhone.getText().toString(),
                mEditQQEmail.getText().toString(),
                mEditWX.getText().toString())
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
                        logoutAndToHome(mContext, baseModel.code);
                        mModelFeedback = baseModel;
                        if (mModelFeedback.success) {
                            //反馈信息成功
                            AppHelper.showMsg(mContext, "反馈成功");
                            finish();
                        } else {
                          AppHelper.showMsg(mContext, mModelFeedback.message);
                        }
                    }
                });
    }
}
