package com.puyue.www.qiaoge.activity.mine.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.CommonH5Activity;
import com.puyue.www.qiaoge.activity.HomeActivity;
import com.puyue.www.qiaoge.api.home.GetCustomerPhoneAPI;
import com.puyue.www.qiaoge.api.mine.login.RegisterAPI;
import com.puyue.www.qiaoge.api.mine.login.RegisterAgreementAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.GoToMineEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.mine.login.RegisterAgreementModel;
import com.puyue.www.qiaoge.model.mine.login.RegisterModel;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2019/11/22
 * 设置登录密码界面
 */
public class RegisterStep1Activity extends BaseSwipeActivity implements View.OnClickListener {
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_password_sure)
    EditText et_password_sure;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.cb_register)
    CheckBox cb_register;
    @BindView(R.id.iv_one)
    ImageView iv_one;
    @BindView(R.id.iv_two)
    ImageView iv_two;
    @BindView(R.id.toolbar_register)
    Toolbar toolbar_register;
    @BindView(R.id.tv_register_agreement)
    TextView tv_register_agreement;
    @BindView(R.id.tv_register_secret)
    TextView tv_register_secret;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.et_author)
    EditText et_author;
    private String phone;
    private String yzm;
    String token1;
    private boolean isStarFirst = true;
    private boolean isStarSecond = true;
    private String password;
    private String passwordSure;
    private RegisterModel mModelRegister;
    private String mUrlAgreement;
    private RegisterAgreementModel mModelRegisterAgreement;
    private String mCustomerCell;
    private AlertDialog mDialog;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {


        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_registers);

    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
        tv_register.setOnClickListener(this);
        cb_register.setOnClickListener(this);
        iv_one.setOnClickListener(this);
        iv_two.setOnClickListener(this);
        tv_register_agreement.setOnClickListener(this);
        tv_register_secret.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        toolbar_register.setOnClickListener(this);
    }

    @Override
    public void setViewData() {

        if(getIntent().getStringExtra("phone")!=null) {
            phone = getIntent().getStringExtra("phone");
            Log.d("wodesfgndufu...",phone);
        }

        if(getIntent().getStringExtra("yzm")!=null) {
            yzm = getIntent().getStringExtra("yzm");
            Log.d("wodesfgndufu........",yzm);
        }

        if(getIntent().getStringExtra("token1")!=null) {
            token1 = getIntent().getStringExtra("token1");
            Log.d("wodesfgndufu........",token1);
        }



        requestRegisterAgreement();
        getCustomerPhone();
        tv_phone.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_phone.getPaint().setAntiAlias(true);//抗锯齿
        tv_register_secret.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_register_secret.getPaint().setAntiAlias(true);//抗锯齿
        tv_register_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_register_agreement.getPaint().setAntiAlias(true);//抗锯齿


    }

    /**
     * 获取客服电话
     */
    private void getCustomerPhone() {
        GetCustomerPhoneAPI.requestData(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCustomerPhoneModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetCustomerPhoneModel getCustomerPhoneModel) {
                        if (getCustomerPhoneModel.isSuccess()) {
                            mCustomerCell = getCustomerPhoneModel.getData();
                            tv_phone.setText(mCustomerCell);
                        } else {
                            AppHelper.showMsg(mContext, getCustomerPhoneModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 注册协议接口
     */
    private void requestRegisterAgreement() {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            AppHelper.showMsg(mContext, "网络不给力!");
        } else {
            RegisterAgreementAPI.requestRegisterAgreement(mContext)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<RegisterAgreementModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(RegisterAgreementModel registerAgreementModel) {
                            mModelRegisterAgreement = registerAgreementModel;
                            if (mModelRegisterAgreement.success) {
                                mUrlAgreement = mModelRegisterAgreement.data;
                            } else {
                                AppHelper.showMsg(mContext, mModelRegisterAgreement.message);
                            }
                        }
                    });
        }
    }


    @Override
    public void setClickEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.toolbar_register:
                finish();
                break;

            case R.id.tv_phone:
                if (StringHelper.notEmptyAndNull(mCustomerCell)) {
                    showPhoneDialog(mCustomerCell);
                }
                break;

            case R.id.tv_register:
                password = et_password.getText().toString();
                passwordSure = et_password_sure.getText().toString();
                if(cb_register.isChecked()) {
                    if(password !=null && passwordSure !=null) {
                        if(password.equals(passwordSure)) {
                        if(password.length()>6&& passwordSure.length()>6) {
                            if (StringHelper.isLetterDigit(et_password.getText().toString())) {
                                updateCheckCode();

                            } else {
                                AppHelper.showMsg(mContext, "密码由6-16位数字与字母组成");
                            }
                        } else {
                            AppHelper.showMsg(mContext, "密码位数不足!");
                        }
                    }else {
                        AppHelper.showMsg(mContext, "两次密码不一致!");
                    }
                }else {
                    AppHelper.showMsg(mContext, "密码不能为空");
                }

                }else {
                    AppHelper.showMsg(mContext, "请选择注册协议");
                }
                break;

            case R.id.cb_register:
                break;

            case R.id.iv_one:
                //显示为星号或者显示数字
                if (isStarFirst) {
                    //现在显示的星星,点击变成数字
                    isStarFirst = false;
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_password.setSelection(et_password.getText().length());
                    iv_one.setImageResource(R.mipmap.ic_eye_open);
                } else {
                    //现在不是星星,点击变成星星
                    isStarFirst = true;
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_password.setSelection(et_password.getText().length());
                    iv_one.setImageResource(R.mipmap.ic_login_hide);
                }
                break;

            case R.id.tv_register_secret:
                String url = "https://shaokao.qoger.com/apph5/html/yszc.html";
                Intent intent = new Intent(mContext, NewWebViewActivity.class);
                intent.putExtra("URL", url);
                intent.putExtra("TYPE", 1);
                intent.putExtra("name", "协议");
                startActivity(intent);
                break;

            case R.id.iv_two:
                //显示为星号或者显示数字
                if (isStarSecond) {
                    //现在显示的星星,点击变成数字
                    isStarSecond = false;
                    et_password_sure.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_password_sure.setSelection(et_password_sure.getText().length());
                    iv_two.setImageResource(R.mipmap.ic_eye_open);
                } else {
                    //现在不是星星,点击变成星星
                    isStarSecond = true;
                    et_password_sure.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_password_sure.setSelection(et_password_sure.getText().length());
                    iv_two.setImageResource(R.mipmap.ic_login_hide);
                }
                break;

            case R.id.tv_register_agreement:
                startActivity(CommonH5Activity.getIntent(mContext, CommonH5Activity.class, mUrlAgreement));
                break;

        }

    }

    /**
     * 拨打电话弹窗
     * @param cell
     */
    private void showPhoneDialog(String cell) {
        mDialog = new AlertDialog.Builder(mActivity).create();
        mDialog.show();
        mDialog.getWindow().setContentView(R.layout.dialog_call_phone);
        TextView mTvCell = (TextView) mDialog.getWindow().findViewById(R.id.tv_dialog_call_phone_phone);
        mTvCell.setText(cell);
        mDialog.getWindow().findViewById(R.id.tv_dialog_call_phone_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().findViewById(R.id.tv_dialog_call_phone_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet(mContext)) {
                    AppHelper.showMsg(mContext, "当前设备不具备拨号功能");
                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cell));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                mDialog.dismiss();
            }
        });
    }

    /**
     * 平板返回 True，手机返回 False
     */
    private boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    /**
     * 注册
     */
    private void updateCheckCode() {
        if (!NetWorkHelper.isNetworkAvailable(mContext)) {
            AppHelper.showMsg(mContext, "网络不给力!");
        } else {
            //这里请求注册成功之后直接登录成功,返回的token存储下来,就代表着用户已经登录了
            if(yzm==null) {
                RegisterAPI.requestRegister(mContext, phone,token1,passwordSure, "000000", et_author.getText().toString(),"")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<RegisterModel>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                //  dialog.dismiss();
                            }

                            @Override
                            public void onNext(RegisterModel registerModel) {
                                mModelRegister = registerModel;
                                if (mModelRegister.success) {
                                    updateRegister();
                                } else {
                                    //  dialog.dismiss();
                                    AppHelper.showMsg(mContext, mModelRegister.message);
                                    finish();
                                }
                            }
                        });
            }else {
                RegisterAPI.requestRegister(mContext, phone,"",passwordSure, yzm, et_author.getText().toString(),"")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<RegisterModel>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                //  dialog.dismiss();
                            }

                            @Override
                            public void onNext(RegisterModel registerModel) {

                                mModelRegister = registerModel;
                                if (mModelRegister.success) {
                                    //这里注册完成也就直接登录成功,本地存储token
                                    updateRegister();
                                } else {
                                    //  dialog.dismiss();
                                    AppHelper.showMsg(mContext, mModelRegister.message);
                                    finish();
                                }
                            }
                        });
            }
            }

    }

    /**
     * 注册成功
     */
    private void updateRegister() {
        AppHelper.showMsg(mContext, "注册成功");
        UserInfoHelper.saveUserId(mContext, mModelRegister.data.token);
        UserInfoHelper.saveUserCell(mContext, mModelRegister.data.userBaseInfoVO.phone);
        UserInfoHelper.saveUserType(mContext, String.valueOf(mModelRegister.data.userBaseInfoVO.type));
        //注册成功同时登录成功,需要首页和市场页刷新数据
        UserInfoHelper.saveUserHomeRefresh(mContext, "");
        UserInfoHelper.saveUserMarketRefresh(mContext, "");
        startActivity(HomeActivity.getIntent(mContext, HomeActivity.class));
        EventBus.getDefault().post(new GoToMineEvent());

        finish();
    }
}
