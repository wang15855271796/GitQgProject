package com.puyue.www.qiaoge.activity.mine;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.SubAccountAdapter;
import com.puyue.www.qiaoge.api.mine.login.SendCodeAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountAddAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountDeleteAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountDisableAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountEnableAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountListAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.SubAccountModel;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/8.
 */

public class SubAccountActivity extends BaseSwipeActivity {

    private ImageView mIvBack;
    private TextView mTvNone;
    private RecyclerView mRvSubAccount;
    private Button mBtnAdd;

    private List<SubAccountModel.DataBean> mList = new ArrayList<>();
    private SubAccountAdapter mAdapterSubAccount;
    private PtrClassicFrameLayout mPtr;
    private SubAccountModel mModelSubAccount;

    private BaseModel mModelAddSubAccount;
    private BaseModel mModelDisableSubAccount;
    private BaseModel mModelDeleteSubAccount;
    private BaseModel mModelEnableSubAccount;

    private boolean isSendingCode = false;
    private BaseModel mModelSendCode;
    private CountDownTimer countDownTimer;

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_sub_account);
    }

    @Override
    public void findViewById() {
        mIvBack = (ImageView) findViewById(R.id.iv_sub_account_back);
        mTvNone = (TextView) findViewById(R.id.tv_sub_account_none);//没有子账号的显示
        mRvSubAccount = (RecyclerView) findViewById(R.id.rv_sub_account);//有子账号的显示
        mBtnAdd = (Button) findViewById(R.id.btn_sub_account_add);//增加子账号
        mPtr = (PtrClassicFrameLayout) findViewById(R.id.ptr_sub_account);


    }

    @Override
    public void setViewData() {
        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                requestSubAccountList();
            }
        });
        mAdapterSubAccount = new SubAccountAdapter(mContext, mList);
        mAdapterSubAccount.setOnItemClickListener(new SubAccountAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(View view, int position, String flag) {
                //以下这四个操作,操作完成之后都需要刷新列表
                //现在需要在进行操作之前弹框让用户确定进行操作
                //禁用,删除,启用
                showEditSubAccountDialog(position, flag);
            }

            @Override
            public void onEventLongClick(View view, int position, String flag) {

            }
        });
        mRvSubAccount.setLayoutManager(new LinearLayoutManager(mContext));
        mRvSubAccount.setAdapter(mAdapterSubAccount);
        requestSubAccountList();
    }

    private void showEditSubAccountDialog(final int position, final String flag) {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.CommonDialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_sub_account);
        TextView mTvText = (TextView) window.findViewById(R.id.tv_dialog_sub_account);
        TextView mTvClose = (TextView) window.findViewById(R.id.tv_sub_account_close);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_sub_account_confirm);
        if (flag.equals("disable")) {
            mTvText.setText("确定禁用该子账号吗?");
        } else if (flag.equals("delete")) {
            //删除该子账号
            mTvText.setText("确定删除该子账号吗?");
        } else if (flag.equals("enable")) {
            //恢复被禁用的子账号
            mTvText.setText("确定启用该子账号吗?");
        }
        mTvClose.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (flag.equals("disable")) {
                    //禁用该子账号,将点击的这个item的手机号传给后台
//                    requestDisableSubAccount(mList.get(position).loginPhone);
                } else if (flag.equals("delete")) {
                    //删除该子账号
                    requestDeleteSubAccount(String.valueOf(mModelSubAccount.data.get(position).subId));
                } else if (flag.equals("enable")) {
                    //恢复被禁用的子账号
//                    requestEnableSubAccount(mList.get(position).loginPhone);
                }
                alertDialog.dismiss();
            }
        });
    }


    /**
     * 删除子账户
     * @param subId
     */
    private void requestDeleteSubAccount(String subId) {
        SubAccountDeleteAPI.requestDeleteSubAccount(mContext, subId)
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
                        mModelDeleteSubAccount = baseModel;
                        if (mModelDeleteSubAccount.success) {
                            //删除某个账号成功
                            AppHelper.showMsg(mContext, "删除成功");
                            mPtr.autoRefresh();
                        } else {
                            AppHelper.showMsg(mContext, mModelDeleteSubAccount.message);
                        }
                    }
                });
    }

    /**
     * 子账户列表
     */
    private void requestSubAccountList() {
        SubAccountListAPI.requestSubAccountList(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SubAccountModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SubAccountModel subAccountModel) {
                        mPtr.refreshComplete();
                        mModelSubAccount = subAccountModel;
                        if (mModelSubAccount.success) {
                            updateSubAccountList();
                        } else {
                            AppHelper.showMsg(mContext, mModelSubAccount.message);
                        }
                    }
                });
    }

    private void updateSubAccountList() {
        if (mModelSubAccount.data.size() > 0 && mModelSubAccount.data != null) {
            mTvNone.setVisibility(View.GONE);
            mRvSubAccount.setVisibility(View.VISIBLE);
            mPtr.setVisibility(View.VISIBLE);
            mList.clear();
            mList.addAll(mModelSubAccount.data);
            mAdapterSubAccount.notifyDataSetChanged();
        } else {
            mTvNone.setVisibility(View.VISIBLE);
            mRvSubAccount.setVisibility(View.GONE);
            mPtr.setVisibility(View.GONE);
        }
    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        mBtnAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
//                showAddAccountDialog();
                Intent intent = new Intent(mContext,AddSubAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private AlertDialog alertDialog;

    private void showAddAccountDialog() {
        alertDialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setView(new EditText(mContext));
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_add_sub_account);
        //获取版本号
        String version = AppHelper.getVersion(getApplicationContext());

        final EditText mEditRemarks = (EditText) window.findViewById(R.id.edit_add_sub_account_remarks);
        final EditText mEditPhone = (EditText) window.findViewById(R.id.edit_add_sub_account_phone);
        final EditText mEditPasswordOnce = (EditText) window.findViewById(R.id.edit_add_sub_account_password_once);
        final EditText mEditPasswordSecond = (EditText) window.findViewById(R.id.edit_add_sub_account_password_second);
        final TextView mTVSendCode = (TextView) window.findViewById(R.id.tv_send_phone_code);
        final EditText mEtGetCode = (EditText) window.findViewById(R.id.ed_edit_phone_code);

        mEditPasswordOnce.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mEditPasswordSecond.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        TextView mTvCancel = (TextView) window.findViewById(R.id.tv_add_sub_account_cancel);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_add_sub_account_confirm);

        mTVSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditPhone.getText().toString().length() == 11) {
                    //获取到手机号,对这个手机号发送验证码
                    if (!isSendingCode) {
                        //现在不在倒计时中,可以重新发送验证码
                        requestSendCode(mEditPhone.getText().toString(), mTVSendCode);
                    }
                } else {
                    AppHelper.showMsg(mActivity, "手机号位数错误");
                }
            }
        });

        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (StringHelper.notEmptyAndNull(mEditRemarks.getText().toString())
                        && StringHelper.notEmptyAndNull(mEditPhone.getText().toString())
                        && StringHelper.notEmptyAndNull(mEditPasswordOnce.getText().toString())
                        && StringHelper.notEmptyAndNull(mEditPasswordSecond.getText().toString())
                        && StringHelper.notEmptyAndNull(mEtGetCode.getText().toString())) {
                    if (mEditPasswordOnce.getText().toString().equals(mEditPasswordSecond.getText().toString())) {
                        //两次输入的密码一致才能请求注册
                        //两次密码一致之后判断密码是不是6-16位字母与数字的组合,如果是纯数字或者纯字母,不允许往下走
                        if (mEditPasswordOnce.getText().toString().length() >= 6
                                && mEditPasswordSecond.getText().toString().length() >= 6) {
                            if (StringHelper.isLetterDigit(mEditPasswordOnce.getText().toString())) {

                                //添加一个子账号,添加子账号会默认注册一个账号
//                                requestAddSubAccount(mEditPhone.getText().toString(), mEditRemarks.getText().toString(), mEditPasswordOnce.getText().toString(), mEtGetCode.getText().toString(), version);
                                //alertDialog.dismiss();
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
        mTvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

//    private void requestAddSubAccount(String phone, String name, String pwd, String gsc, String ver) {
//        SubAccountAddAPI.requestAddSubAccount(mContext, phone, name, pwd, gsc, ver)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<BaseModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(BaseModel baseModel) {
//                        mModelAddSubAccount = baseModel;
//                        if (mModelAddSubAccount.success) {
//                            //添加子账号成功,刷新列表
//                            AppHelper.showMsg(mContext, "添加成功");
//                            alertDialog.dismiss();
//                            mPtr.autoRefresh();
//                        } else {
//                            AppHelper.showMsg(mContext, mModelAddSubAccount.message);
//                        }
//                    }
//                });
//    }

    /**
     * 发送验证码
     */
    public void requestSendCode(String mEditPhone, TextView mTvSendCode) {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            AppHelper.showMsg(mContext, "网络不给力!");
        } else {
            //注册发送验证码,type为7
            SendCodeAPI.requestSendCode(mContext, mEditPhone, 7)
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

                                handleCountDown(mTvSendCode);
                            } else {

                                AppHelper.showMsg(mActivity, "手机号已注册");
                            }
                        }
                    });


        }


    }

    /**
     * 倒计时
     *
     * @param mTvSendCode
     */
    public void handleCountDown(TextView mTvSendCode) {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isSendingCode = true;
                mTvSendCode.setEnabled(false);
                mTvSendCode.setText(millisUntilFinished / 1000 + "秒后" + "重新发送");

                mTvSendCode.setTextColor(Color.parseColor("#A7A7A7"));
            }

            @Override
            public void onFinish() {
                isSendingCode = false;
                mTvSendCode.setText("发送验证码");
                mTvSendCode.setTextColor(Color.parseColor("#F56D23"));
                mTvSendCode.setEnabled(true);
            }
        }.start();
    }
}
